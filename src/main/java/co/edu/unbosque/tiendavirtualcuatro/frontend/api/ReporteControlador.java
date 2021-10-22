package co.edu.unbosque.tiendavirtualcuatro.frontend.api;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.dto.TotalVentaPorClienteDTO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/reportes")
public class ReporteControlador extends ControladorBase {
	
	  @Autowired
	  private UsuarioDAO objUsrDao;
	  
		@Autowired
		private ClienteDAO objCliDao;	  
	  
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
      // .onStatus(status -> status == HttpStatus.NOT_FOUND,
      // response -> Mono.empty())
      .bodyToFlux(TotalVentaPorClienteDTO.class);
    List<TotalVentaPorClienteDTO> ventasCliente = ventasClienteFlux
      .collectList()
      .block();

    model.addAttribute("tituloPagina", "Ventas por Cliente - Reporte");
    model.addAttribute("ventasCliente", ventasCliente);

    Flux<Map> totalGlobalVentasFlux = crearWebClient().get()
      .uri(uriBuilder -> uriBuilder
        .pathSegment(getUri(), "totalglobalventas")
        .build())
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      // .onStatus(status -> status == HttpStatus.NOT_FOUND,
      // response -> Mono.empty())
      .bodyToFlux(Map.class);
    List<Map> totalGlobalVentas = totalGlobalVentasFlux.collectList()
      .block();
    model.addAttribute("totalGlobalVentas",
      totalGlobalVentas.get(0)
        .get("totalGlobalVentas"));
    return "reportes/ventasporcliente";
  }
  
  @GetMapping("/listausuarios")
  public String getUsuarioHome(@RequestParam Optional<String> cedula, Model model) {

    if (cedula.isPresent() && !cedula.get()
      .isBlank()) {

      UsuarioVO usuario = this.objUsrDao.getUsuarioPorCedula(
        Long.parseLong(cedula.get()));

      model.addAttribute("usuario", usuario);
    } else {
      model.addAttribute("usuario", new UsuarioVO());
    }
    List<UsuarioVO> usuarios = this.objUsrDao.listaDeUsuarios();
    model.addAttribute("usuarios", usuarios);
    return "/reportes/listausuarios";
  }
  
  @GetMapping("/listaclientes")
  public String getClienteHome(@RequestParam Optional<String> cedula, Model model) {

    if (cedula.isPresent() && !cedula.get()
      .isBlank()) {

      ClienteVO cliente = this.objCliDao.getClientePorCedula(
        Long.parseLong(cedula.get()));

      model.addAttribute("cliente", cliente);
    } else {
      model.addAttribute("cliente", new ClienteVO());
    }
    List<ClienteVO> clientes = this.objCliDao.listaDeClientes();
    model.addAttribute("clientes", clientes);
    return "/reportes/listaclientes";
  }
}
