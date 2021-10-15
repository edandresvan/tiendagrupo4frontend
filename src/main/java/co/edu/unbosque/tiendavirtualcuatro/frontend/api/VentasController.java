package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.VentasDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.VentasVO;

@Controller
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	  private VentasDAO objUsrDao;	
	  private ClienteDAO objcliDao;
	
	  @GetMapping
	  public String getVentaHome(@RequestParam Optional<String> cedula, Model model) {

	    if (cedula.isPresent() && !cedula.get()
	      .isBlank()) {

		   ClienteVO cliente = this.objcliDao.getClientePorCedula(
	        Long.parseLong(cedula.get()));

	      model.addAttribute("cliente", cliente);
	    } else {
	      model.addAttribute("cliente", new ClienteVO());
	    }
	    List<VentasVO> ventas = this.objUsrDao.listaDeVentas();
	    model.addAttribute("ventas", ventas);
	    return "/ventas/index";
	  }
}
