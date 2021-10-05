package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Mono;

@Service
public class UsuarioDAO {
private static final String URL = "http://localhost:5000";
  
  @Autowired
  private WebClient webClient;
  
  public UsuarioVO crearUsuario(UsuarioVO usuarioVO) {

    try {
      WebClient webClient = WebClient.create(URL);
      UsuarioVO objUsuario = null;
      Mono<UsuarioVO> response = webClient.post().uri(URL + "/usuarios/registrarUsuario")
          .body(Mono.just(usuarioVO), UsuarioVO.class).retrieve().bodyToMono(UsuarioVO.class);

      objUsuario = response.block();
      return objUsuario;

    } catch (WebClientResponseException e) {
      e.getMessage();
      System.out.println("---->" + e.getMessage());
      return null;
    }

  }
}
