package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.gson.Gson;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProductoVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping({ "/productos" })
public class ProductoControlador extends ControladorBase {

  public ProductoControlador() {
    super();
    setUri("/productos");
  }

  @GetMapping
  public String homeProveedores(Optional<String> codigo, Model model) {
    Optional<String> parametroCodigo;
    if (codigo.isPresent() && codigo.get()
      .isBlank()) {
      parametroCodigo = Optional.empty();
    } else {
      parametroCodigo = codigo;
    }
    Flux<ProductoVO> productosFlux = crearWebClient().get()
      .uri(uriBuilder -> uriBuilder
        .path(getUri())
        .queryParamIfPresent("codigo", parametroCodigo)
        .build())
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
        response -> Mono.empty())
      .bodyToFlux(ProductoVO.class);
    List<ProductoVO> productos = productosFlux.collectList()
      .block();

    for (ProductoVO producto : productos) {
      Flux<ProveedorVO> proveedoresFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder
          .path("/proveedores")
          .queryParam("nit", producto.getNitProveedor())
          .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(status -> status == HttpStatus.NOT_FOUND,
          response -> Mono.empty())
        .bodyToFlux(ProveedorVO.class);
      ProveedorVO proveedor = proveedoresFlux.collectList()
        .block()
        .get(0);
      producto.setProveedor(proveedor);
    }

    model.addAttribute("tituloPagina", "Productos - Listado");
    model.addAttribute("productos", productos);

    if (codigo.isPresent() && !codigo.get()
      .isBlank()) {
      if (productos.isEmpty()) {

        model.addAttribute("errores",
          "No hay productos con el Código: " + codigo.get());
      }
    }

    return getUri() + "/index";
  }

  @PostMapping("/cargarcsv")
  public ModelAndView cargarCsv(
      @RequestParam("archivocsv") MultipartFile archivoCsv,
      RedirectAttributes redirectAttributes) throws IllegalArgumentException, Exception {

    if (!archivoCsv.isEmpty()) {
      if (!archivoCsv.isEmpty()) {

        List<ProductoVO> productosParaAgregar = new ArrayList<>();
        try (Scanner scanner = new Scanner(archivoCsv.getInputStream());) {
          int indiceFila = 0;
          while (scanner.hasNextLine()) {
            productosParaAgregar.add(getProductoDesdeLineaCsv(scanner.nextLine()));
            indiceFila++;
          }

          crearWebClient().delete()
            .uri(getUri())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToFlux(ProductoVO.class)
            .collectList()
            .block();

          for (ProductoVO producto : productosParaAgregar) {

            Flux<ProductoVO> productosFlux = crearWebClient()
              .post()
              .uri("/productos")
              .header(HttpHeaders.CONTENT_TYPE,
                MediaType.APPLICATION_JSON_VALUE)
              .body(Mono.just(producto), ProductoVO.class)
              .accept(MediaType.APPLICATION_JSON)
              .retrieve()
              .bodyToFlux(ProductoVO.class);
            List<ProductoVO> productos = productosFlux.collectList()
              .block();
            redirectAttributes.addFlashAttribute("mensajeExito",
              "Productos Agregados");
          }

        } catch (IllegalArgumentException iae) {
          throw new IllegalArgumentException(
            String.format("Error en la línea CSV {%s}: ", iae.getMessage()));
        }
      }
    }

    return new ModelAndView("redirect:/productos");
  }

  public ProductoVO getProductoDesdeLineaCsv(String linea) 
      throws IllegalArgumentException {
    ProductoVO producto = new ProductoVO();
    try (Scanner scannerLinea = new Scanner(linea)) {
      scannerLinea.useDelimiter(",");
      int indiceColumna = 0;
      while (scannerLinea.hasNext()) {
        String dato = scannerLinea.next();
        if (indiceColumna == 0) {
          producto.setCodigo(Long.parseLong(dato));
        } else if (indiceColumna == 1) {
          producto.setNombre(dato);
        } else if (indiceColumna == 2) {
          producto.setNitProveedor(Long.parseLong(dato));
        } else if (indiceColumna == 3) {
          producto.setPrecioCompra(Double.parseDouble(dato));
        } else if (indiceColumna == 4) {
          producto.setIvaCompra(Double.parseDouble(dato));
        } else if (indiceColumna == 5) {
          producto.setPrecioVenta(Double.parseDouble(dato));
        } else {
          throw new IllegalArgumentException(
            String.format("Error: Hay un atributo es inválido para un producto: Columna: {%s} = {%s}", 
              indiceColumna, dato));
        }
        indiceColumna++;
      }
    }
    return producto;
  }
}
