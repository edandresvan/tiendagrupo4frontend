package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Flux;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

  @Autowired
  private UsuarioDAO objUsrDao;

  @GetMapping
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
    return "/usuarios/index";
  }

  @GetMapping("/nuevo")
  public ModelAndView nuevoProveedor(Model model, HttpServletRequest request) {

    model.addAttribute("usuario", new UsuarioVO());

    return new ModelAndView("redirect:/usuarios/");
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

  @GetMapping("/buscar")
  public String consultar(Model model, @RequestParam int cedula_usuario) {
    String retorno = "";
    UsuarioVO c = new UsuarioVO();
    c.setCedula(cedula_usuario);
    objUsrDao = new UsuarioDAO();
    UsuarioVO usr = objUsrDao.consultar(c);
    if (usr != null) {
      model.addAttribute("usuario", usr);
      retorno = "buscar";
    } else {
      model.addAttribute("error", "Cedula de cliente incorrecta");
      retorno = "usuarios";
    }
    return retorno;
  }

  @PostMapping("/actualizarUsuario")
  public String editarUsuario(Model model,
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

      UsuarioVO usuarioEditado = objUsrDao.editarUsuario(usuario);

      if (usuarioEditado != null) {
        model.addAttribute("mensaje", "Usuario editado");
        System.out.println("mensaje: Usuario editado");

      }
    }
    return "redirect:/usuarios/";
  }

  @PostMapping("/borrar")
  public ModelAndView eliminarUsuario(
      @ModelAttribute("usuario") UsuarioVO usuario, ModelMap model) {

    this.objUsrDao.eliminarUsuario(usuario);
    return new ModelAndView("redirect:/usuarios");
  }
}
