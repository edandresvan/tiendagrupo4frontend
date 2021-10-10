package co.edu.unbosque.tiendavirtualcuatro.frontend.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.client.WebClient;

@Controller
public class ControladorBase {
  
  protected static final Logger logger = LoggerFactory
      .getLogger(GlobalExceptionHandler.class);

  @Value("${servidorapi.url}")
  private String servidorApiUrl;

  private String uri;

  public ControladorBase() {
    setUri("");
  }

  public String getServidorApiUrl() {
    return this.servidorApiUrl;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getUri() {
    return this.uri;
  }

  public WebClient crearWebClient() {
    return WebClient.create(getServidorApiUrl());
  }

}
