package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/reportes")
public class ReporteControlador {
  @GetMapping
  public String reporteHome(Model model) {
    return "reportes/index";
  }
}
