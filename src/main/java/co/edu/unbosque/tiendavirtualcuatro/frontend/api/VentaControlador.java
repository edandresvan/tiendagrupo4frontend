package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.DetalleVentaVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProductoVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.VentaVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/ventas")
public class VentaControlador extends ControladorBase {
  
  @Autowired
  private UsuarioDAO usuarioDAO = new UsuarioDAO();
  
  @Autowired
  private ClienteDAO objUsrDao;
  
  public VentaControlador() {
    super();
    setUri("/ventas");
  }

  @GetMapping
  public ModelAndView getVentaHome(ModelMap model) {
    VentaVO ventaNueva = new VentaVO();
    
    ventaNueva.setDetalleVentas(new ArrayList<>(List.of(
      new DetalleVentaVO(), new DetalleVentaVO(), new DetalleVentaVO()
        )));
    model.addAttribute("venta", ventaNueva);
    
    Flux<VentaVO> ventasExistentesFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder
          .path(getUri())          
          .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(status -> status == HttpStatus.NOT_FOUND,
          response -> Mono.empty())
        .bodyToFlux(VentaVO.class);
      List<VentaVO> ventasExistentes = ventasExistentesFlux.collectList()
        .block();
    
      model.addAttribute("ventasExistentes", ventasExistentes);
      
    return new ModelAndView("ventas/index", model);
  }

  @GetMapping("/{codigo}")
  public ModelAndView mostrarVenta(@PathVariable String codigo,
      ModelMap model, HttpServletRequest request,
      RedirectAttributes redirectAttributes) {

    Flux<VentaVO> ventasFlux = crearWebClient().get()
      .uri(uriBuilder -> uriBuilder.path(getUri() + "/{codigo}")
        .build(codigo))
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
        response -> Mono.empty())
      .bodyToFlux(VentaVO.class);
    List<VentaVO> ventas = ventasFlux.collectList()
      .block();

    if (ventas.isEmpty()) {
      redirectAttributes.addFlashAttribute("errores",
        List.of("No existe una venta con el codigo " + codigo));
      model.addAttribute("venta" ,new VentaVO() );
      return new ModelAndView("redirect:/ventas");
    } else {
      VentaVO ventaCreada = ventas.get(0);
      Map<String, Object> datosAdicionales = new HashMap<>();
      
      ClienteVO cliente = this.objUsrDao.getClientePorCedula(ventaCreada.getCedulaCliente());     
      datosAdicionales.put("clienteNombre", cliente.getNombre());
      
      model.addAttribute("venta", ventaCreada);
      model.addAttribute("datosAdicionales", datosAdicionales);
    }
    
    
    Flux<VentaVO> ventasExistentesFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder
          .path(getUri())          
          .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(status -> status == HttpStatus.NOT_FOUND,
          response -> Mono.empty())
        .bodyToFlux(VentaVO.class);
      List<VentaVO> ventasExistentes = ventasExistentesFlux.collectList()
        .block();
    
      model.addAttribute("ventasExistentes", ventasExistentes);
    
    return new ModelAndView("/ventas/index", model);

  }
  
  @PostMapping
  public RedirectView crearVenta(
      @ModelAttribute("venta") VentaVO venta,
      RedirectAttributes redirectAttributes,
      HttpServletRequest request) {
    
    Principal principal = request.getUserPrincipal();
    String usuarioAlias = principal.getName();
    UsuarioVO usuario = this.usuarioDAO.getUsuarioPorAliasUsuario(usuarioAlias);
    
    venta.setCedulaUsuario(usuario.getCedula());
   
    
    Flux<VentaVO> ventasFlux = crearWebClient()
      .post()
      .uri(getUri())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .body(Mono.just(venta), ProveedorVO.class)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToFlux(VentaVO.class);

    List<VentaVO> ventas = ventasFlux.collectList()
      .block();
    if (ventas.get(0) != null) {
      
      redirectAttributes.addFlashAttribute("mensajeExito", "Venta creada");
    }
    
    return new RedirectView(
      "/ventas/" + ventas.get(0)
        .getCodigo(),
      true);
  }

}
