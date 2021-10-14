package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ventas")
public class VentaController {
  @GetMapping
  public String ventaHome(Model model) {
    return "ventas/index";
  }
}
