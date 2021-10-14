package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;



@Controller
@RequestMapping("/clientes")
public class ClienteController {
	
	@Autowired
	  private ClienteDAO objUsrDao;

	  @GetMapping
	  public String getClienteHome(Model model) {
	    model.addAttribute("cliente", new ClienteVO());
	    return "/clientes/index";
	  }
	  
	  @GetMapping("/nuevo")
	  public ModelAndView nuevoProveedor(Model model, HttpServletRequest request) {

	    model.addAttribute("cliente", new ClienteVO());

	    return new ModelAndView("redirect:/clientes/");
	  }

	  @PostMapping("/registrarcliente")
	  public String crearCliente(Model model,
	      @ModelAttribute("cliente") ClienteVO cliente) {
	    boolean valido = false;
	    if (cliente.getCedula() <= 1) {
	      System.out.println("mensaje: la cedula no puede ser menor que 1");
	    } else if (cliente.getDireccion() == null || cliente.getDireccion()
	                                                      .isBlank()) {
	      System.out.println("mensaje: la direccion no puede ser vacio");

	    } else if (cliente.getEmail() == null || cliente.getEmail()
	                                                     .isBlank()) {
	      System.out.println("mensaje: el email no puede ser vacio");
	    } else if (cliente.getNombre() == null || cliente.getNombre()
	                                                    .isBlank()) {
	      System.out.println("mensaje: el nombre no puede ser vacio");
	    } else if (cliente.getTelefono() == null || cliente.getTelefono().isBlank())
	    	                                              
	      System.out.println("mensaje: el telefono no puede ser vacio");
	    	   
	     else {

	      objUsrDao = new ClienteDAO();

	      ClienteVO clienteCreado = objUsrDao.crearCliente(cliente);

	      if (clienteCreado != null) {
	        model.addAttribute("mensaje", "Cliente Creado");
	        System.out.println("mensaje: Cliente Creado");
	      }
	    }
	    return "redirect:/clientes/";
	  }
}
