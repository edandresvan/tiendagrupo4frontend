package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import java.nio.charset.Charset;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.google.gson.Gson;

@ControllerAdvice
public class GlobalExceptionHandler {
  private static final Logger logger = LoggerFactory
    .getLogger(GlobalExceptionHandler.class);

  @ExceptionHandler(ServletRequestBindingException.class)
  public String servletRequestBindingException(
      ServletRequestBindingException e) {
    logger.error("ServletRequestBindingException occurred: " + e.getMessage());
    return "validation_error";
  }
  
  @ExceptionHandler(IllegalArgumentException.class)
  public void illegalArgumentException(IllegalArgumentException iae) {
    logger.error(iae.getMessage());
  }

  @ExceptionHandler(WebClientResponseException.class)
  public RedirectView webClientResponseException(WebClientResponseException e,
      RedirectAttributes redirectAttributes) {
    logger.error("Ocurrio un error de tipo WebClientResponseException: "
        + e.getMessage());
    logger.error("El reponse status es: " + e.getStatusCode());
    e.getHeaders()
      .forEach((key, value) -> {
        logger.info(String.format(
          "Header '%s' = %s", key, value.stream()
            .collect(Collectors.joining("|"))));
      });
    logger.error("metodo: " + e.getRequest()
      .getMethod());
    logger.error("URI: " + e.getRequest()
      .getURI()
      .toString());
    logger.error("Path: " + e.getRequest()
      .getURI()
      .getPath());
    logger.error("body " + e.getResponseBodyAsString());

    UriComponentsBuilder uriBuilder = UriComponentsBuilder
      .fromPath(e.getRequest()
        .getURI()
        .getPath());

    // Evitar procesar una respuesta 404 No Encontrado
    if (e.getStatusCode() != HttpStatus.NOT_FOUND) {
      Gson gson = new Gson();
      ErrorDetallado errorDetallado = gson.fromJson(
        e.getResponseBodyAsString(Charset.forName("UTF-8")),
        ErrorDetallado.class);
      redirectAttributes.addFlashAttribute("errores",
        errorDetallado.getErrores());

      if (e.getRequest()
        .getMethod() == HttpMethod.POST) {

        if (e.getRequest()
          .getURI()
          .getPath()
          .equalsIgnoreCase("proveedores")) {
          uriBuilder.pathSegment("nuevo");
        } else {
          //uriBuilder.pathSegment("index");
        }

      }

    }

    return new RedirectView(uriBuilder.toUriString(), true);

  }
}