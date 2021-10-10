package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriBuilder;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping({ "/proveedores" })
public class ProveedorControlador extends ControladorBase {

  public ProveedorControlador() {
    super();
    setUri("/proveedores");
  }

  @GetMapping
  public String homeProveedores(Optional<String> nit, Model model) {
    Optional<String> parametroNit;
    if (nit.isPresent() && nit.get().isBlank()) {
      parametroNit = Optional.empty();
    } else {
      parametroNit = nit;
    }
    Flux<ProveedorVO> proveedoresFlux = crearWebClient().get()
      .uri( uriBuilder -> uriBuilder
        .path(getUri())
        .queryParamIfPresent("nit", parametroNit)
        .build() )
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
      response -> Mono.empty())
      .bodyToFlux(ProveedorVO.class);
    List<ProveedorVO> proveedores = proveedoresFlux.collectList()
      .block();

    model.addAttribute("tituloPagina", "Proveedores - Listado");
    model.addAttribute("proveedores", proveedores);
    
    if (nit.isPresent() && !nit.get().isBlank()) {
      if (proveedores.isEmpty()) {
        
        model.addAttribute("errores", "No hay proveedores con el NIT: " + nit.get());
      }
    }
    
    return "proveedores/index";
  }

  @GetMapping("/{nit}")
  public ModelAndView mostrarProveedor(@PathVariable String nit,
      ModelMap model, RedirectAttributes redirectAttributes) {

    Flux<ProveedorVO> proveedoresFlux = crearWebClient().get()
      .uri(uriBuilder -> uriBuilder.path(getUri() + "/{nit}")
        .build(nit))
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
        response -> Mono.empty())
      .bodyToFlux(ProveedorVO.class);
    List<ProveedorVO> proveedores = proveedoresFlux.collectList()
      .block();

    model.addAttribute("tituloPagina", "Proveedor - Editar");
    String rutaRedirigida = "";

    if (proveedores.isEmpty()) {
      redirectAttributes.addFlashAttribute("errores",
        List.of("No existe un proveedor con el NIT " + nit));
      rutaRedirigida = "redirect:" + getUri();
    } else {
      model.addAttribute("proveedor", proveedores.get(0));
      model.addAttribute("action", getUri() + "/put");
      model.addAttribute("method", "post");
      model.addAttribute("operacion", "editar");
      rutaRedirigida = "" + getUri() + "/editar";
    }
    // return "proveedores/editar";
    return new ModelAndView(rutaRedirigida);
  }

  @GetMapping("/nuevo")
  public String nuevoProveedor(Model model, HttpServletRequest request) {
    model.addAttribute("tituloPagina", "Proveedores - Nuevo");
    model.addAttribute("proveedor", new ProveedorVO());
    model.addAttribute("action", getUri());
    model.addAttribute("method", "post");

    return "proveedores/nuevo";
  }
  /*
  @GetMapping
  public String getProveedorPorNit(@RequestParam String nit, Model model) {
    Flux<ProveedorVO> proveedoresFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder.path(getUri() + "/{nit}")
          .build(nit))
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(status -> status == HttpStatus.NOT_FOUND,
          response -> Mono.empty())
        .bodyToFlux(ProveedorVO.class);
      List<ProveedorVO> proveedores = proveedoresFlux.collectList()
        .block();
      
      if (proveedores.isEmpty()) {
        model.addAttribute("errores",
          List.of("No existen proveedores con el NIT " + nit));
        
      }
      model.addAttribute("proveedores", proveedores);
      return "/proveedores/index";
  }
*/
  @PostMapping
  public ModelAndView crearProveedor(
      @ModelAttribute("proveedor") ProveedorVO proveedor, ModelMap model) {
    /*
     * Mono<List<Proveedor>> proveedoresFlujo =
     * crearWebClient().post().uri(getUri()).body(proveedor, Proveedor.class)
     * .retrieve().onStatus(estado -> estado == HttpStatus.BAD_REQUEST, response
     * -> Mono.just(new Exception())) .bodyToMono(new
     * ParameterizedTypeReference<List<Proveedor>>() { });
     */
    /*
     * Flux<Proveedor> proveedoresFlux =
     * crearWebClient().post().uri(getUri()).body(proveedor,
     * Proveedor.class).retrieve() .onStatus(estado -> estado ==
     * HttpStatus.BAD_REQUEST, response -> Mono.just(new
     * Exception("500 error!"))) .bodyToFlux(Proveedor.class);
     */
    Flux<ProveedorVO> proveedoresFlux = crearWebClient()
      .post()
      .uri(getUri())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(Mono.just(proveedor), ProveedorVO.class)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(ProveedorVO.class);

    // List<Proveedor> proveedores = new ArrayList<>();
    List<ProveedorVO> proveedores = proveedoresFlux.collectList()
      .block();

    model.addAttribute("proveedor", proveedores.get(0));
    // return "proveedores/editar"; + proveedores.get(0).getNit();
    return new ModelAndView(
      "redirect:" + this.getUri() + "/" + proveedores.get(0)
        .getNit(),
      model);
  }

  @PostMapping("/put")
  public ModelAndView editarProveedor(
      @ModelAttribute("proveedor") ProveedorVO proveedor, ModelMap model) {
    Flux<ProveedorVO> proveedoresFlux = crearWebClient().put()
      .uri(getUri() + "/" + proveedor.getNit())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(Mono.just(proveedor), ProveedorVO.class)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(ProveedorVO.class);

    List<ProveedorVO> proveedores = proveedoresFlux.collectList()
      .block();

    // model.addAttribute("tituloPagina", "Proveedores - Editar");
    model.addAttribute("proveedor", proveedores.get(0));
    // return "proveedores/editar"; + proveedores.get(0).getNit();
    return new ModelAndView(
      "redirect:" + this.getUri() + "/" + proveedores.get(0)
        .getNit(),
      model);

  }

  @PostMapping("/delete")
  public ModelAndView eliminarProveedor(
      @ModelAttribute("proveedor") ProveedorVO proveedor, ModelMap model) {
    Flux<ProveedorVO> proveedoresFlux = crearWebClient().delete()
      .uri(getUri() + "/" + proveedor.getNit())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(ProveedorVO.class);

    List<ProveedorVO> proveedores = proveedoresFlux.collectList()
      .block();

    return new ModelAndView("redirect:" + this.getUri());
  }
}
