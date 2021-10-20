package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dto.TotalVentaPorClienteDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/reportes")
public class ReporteControlador extends ControladorBase {
  
  public ReporteControlador() {
    super();
    setUri("reportes");
  }
  
  @GetMapping
  public String reporteHome(Model model) {
    return "reportes/index";
  }

  @GetMapping("/ventasporcliente")
  public String reporteVentasPorCliente(Model model) {
    Flux<TotalVentaPorClienteDTO> ventasClienteFlux = crearWebClient().get()
        .uri(uriBuilder -> uriBuilder
          .pathSegment(getUri(), "ventasporcliente")
          .build())
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        //.onStatus(status -> status == HttpStatus.NOT_FOUND,
        //  response -> Mono.empty())
        .bodyToFlux(TotalVentaPorClienteDTO.class);
      List<TotalVentaPorClienteDTO> ventasCliente = ventasClienteFlux.collectList()
        .block();

      model.addAttribute("tituloPagina", "Ventas por Cliente - Reporte");
      model.addAttribute("ventasCliente", ventasCliente);
    return "reportes/ventasporcliente";
  }
}
