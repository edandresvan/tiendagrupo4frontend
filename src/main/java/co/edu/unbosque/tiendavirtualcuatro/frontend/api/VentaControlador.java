package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProductoVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.VentaVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/ventas")
public class VentaControlador extends ControladorBase {
  public VentaControlador() {
    super();
    setUri("/ventas");
  }

  @GetMapping
  public ModelAndView getVentaHome(ModelMap model) {
    model.addAttribute("venta", new VentaVO());
    
    Flux<VentaVO> ventasFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder
          .path(getUri())          
          .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .onStatus(status -> status == HttpStatus.NOT_FOUND,
          response -> Mono.empty())
        .bodyToFlux(VentaVO.class);
      List<VentaVO> ventas = ventasFlux.collectList()
        .block();
    
      model.addAttribute("ventas", ventas);
      
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
      model.addAttribute("venta", ventas.get(0));
    }
    return new ModelAndView("/ventas/index", model);

  }
}
