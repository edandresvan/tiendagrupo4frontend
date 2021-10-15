package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;

import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.tiendavirtualcuatro.frontend.api.GlobalExceptionHandler;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ProveedorVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Flux;
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
      Mono<UsuarioVO> response = webClient.post()
        .uri(URL + "/usuarios/registrarUsuario")
        .body(Mono.just(usuarioVO), UsuarioVO.class)
        .retrieve()
        .bodyToMono(UsuarioVO.class);

      objUsuario = response.block();
      return objUsuario;

    } catch (WebClientResponseException e) {
      e.getMessage();
      System.out.println("---->" + e.getMessage());
      return null;
    }
  }

  public List<UsuarioVO> listaDeUsuarios() {

    try {

      URL url = new URL(URL + "/usuarios/listaDeUsuarios");// your url i.e fetch
                                                           // data from .
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      conn.setRequestMethod("GET");
      conn.setRequestProperty("Accept", "application/json");
      if (conn.getResponseCode() != 200) {
        throw new RuntimeException("Failed : HTTP Error code : "
            + conn.getResponseCode());
      }
      InputStreamReader in = new InputStreamReader(conn.getInputStream());
      BufferedReader br = new BufferedReader(in);
      String json = br.readLine();
      conn.disconnect();
      Gson gson = new Gson();
      Type tipoListaUsuario = new TypeToken<ArrayList<UsuarioVO>>() {
      }.getType();

      ArrayList<UsuarioVO> usuarios = gson.fromJson(json, tipoListaUsuario);
      return usuarios;

    } catch (Exception e) {
      System.out.println("Exception in NetClientGet:- " + e);
    }
    return null;
  }

  public UsuarioVO consultar(UsuarioVO usr) {
    WebClient webClient = WebClient.create(URL);
    UsuarioVO objUsuario = null;
    Mono<UsuarioVO> response = webClient.get()
      .uri(URL + "/buscar/?cedulau=" + usr.getCedula())
      .retrieve()
      .bodyToMono(UsuarioVO.class);
    objUsuario = response.block();

    return objUsuario;
  }

  public UsuarioVO getUsuarioPorCedula(long cedula) {
    WebClient webClient = WebClient.create(URL);

    Mono<UsuarioVO> usuarioMono = webClient
      .get()
      .uri(uriBuilder -> uriBuilder
        .path("/usuarios/buscar")
        .queryParam("cedulau", cedula)
        .build())
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
        response -> Mono.empty())
      .bodyToMono(UsuarioVO.class);
    UsuarioVO usuario = usuarioMono.block();
    return usuario;
  }

  public UsuarioVO editarUsuario(UsuarioVO usuarioVO) {

    try {
      WebClient webClient = WebClient.create(URL);
      UsuarioVO objUsuario = null;
      Mono<UsuarioVO> response = webClient
        .put()
        .uri("/usuarios/actualizarUsuario")
        .body(Mono.just(usuarioVO), UsuarioVO.class)
        .retrieve()
        .bodyToMono(UsuarioVO.class);

      objUsuario = response.block();
      return objUsuario;

    } catch (WebClientResponseException e) {
      e.getMessage();
      System.out.println("---->" + e.getMessage());
      return null;
    }
  }

  public void eliminarUsuario(UsuarioVO usuario) {
    WebClient.create(URL)
      .delete()
      .uri("/usuarios/borrar/" + usuario.getCedula())
      .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .bodyToMono(UsuarioVO.class)
      .block();

  }

  public UsuarioVO getUsuarioPorAliasUsuario(String aliasUsuario) {
    Map<String, String> aliasUsuarioMap = new HashMap<>();
    aliasUsuarioMap.put("usuario", aliasUsuario);

    WebClient webClient = WebClient.create(URL);

    Mono<UsuarioVO> usuarioMono = webClient
      .post()
      .uri(uriBuilder -> uriBuilder
        .path("/usuarios/getporaliasusuario")
        .build())
      .contentType(MediaType.APPLICATION_JSON)
      .body(Mono.just(aliasUsuarioMap), Map.class)
      .accept(MediaType.APPLICATION_JSON)
      .retrieve()
      .onStatus(status -> status == HttpStatus.NOT_FOUND,
        response -> Mono.empty())
      .bodyToMono(UsuarioVO.class);

    Logger logger = LoggerFactory
        .getLogger(GlobalExceptionHandler.class);
    logger.info("aliasUsuario: " + aliasUsuarioMap.get("usuario"));
    
    UsuarioVO usuario = usuarioMono.block();
    return usuario;
  }

}
