package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class HomeControlador extends ControladorBase {
  @GetMapping({ "/", "/home" })
  public String home(Model model) {
    String name = "inicio";
    model.addAttribute("name", name);
    return "index";
  }
}