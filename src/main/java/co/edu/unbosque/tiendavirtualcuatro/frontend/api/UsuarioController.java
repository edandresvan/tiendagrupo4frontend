package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.ArrayList;
import java.util.List;
import java.lang.reflect.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioDAO objUsrDao;

  @GetMapping
  public String homeUsuarios(Model model) {
    
    List<UsuarioVO> usuarios = this.objUsrDao.listaDeUsuarios();
    model.addAttribute("usuarios", usuarios);
    return "/usuarios/index";
  }

  @GetMapping("/nuevo")
  public String nuevoUsuario(Model model) {

    model.addAttribute("tituloPagina", "Usuario - Nuevo");
    model.addAttribute("usuario", new UsuarioVO());
    model.addAttribute("action", "/usuarios/registrarusuario");
    model.addAttribute("method", "post");
    return "/usuarios/nuevo";
  }

  @PostMapping("/registrarusuario")
  public String crearUsuario(Model model,
      @ModelAttribute("usuario") UsuarioVO usuario) {
    boolean valido = false;
    if (usuario.getCedula() <= 1) {
      System.out.println("mensaje: cedula no puede ser menor que 1");
    } else if (usuario.getUsuario() == null || usuario.getUsuario()
                                                      .isBlank()) {
      System.out.println("mensaje: usuario (nickname) no puede ser vacio");

    } else if (usuario.getNombre() == null || usuario.getNombre()
                                                     .isBlank()) {
      System.out.println("mensaje: nombre no puede ser vacio");
    } else if (usuario.getEmail() == null || usuario.getEmail()
                                                    .isBlank()) {
      System.out.println("mensaje: email no puede ser vacio");
    } else if (usuario.getPassword() == null || usuario.getPassword()
                                                       .isBlank()) {
      System.out.println("mensaje: password no puede ser vacio");
    } else if (usuario.getRol() == null || usuario.getRol()
                                                  .isBlank()) {
      System.out.println("mensaje: rol no puede ser vacio");
    } else if (!(usuario.getRol()
                       .equals("administrador")
        || usuario.getRol()
                   .equals("usuario"))) {
      
      System.out.println(
          "mensaje: rol solo puede ser 'administrador' o 'usuario'");
    } else {

      objUsrDao = new UsuarioDAO();

      UsuarioVO usuarioCreado = objUsrDao.crearUsuario(usuario);

      if (usuarioCreado != null) {
        model.addAttribute("mensaje", "Usuario Creado");
        System.out.println("mensaje: Usuario Creado");

      }
    }
    return "redirect:/usuarios/";

  }
}
