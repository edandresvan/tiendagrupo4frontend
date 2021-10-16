package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Mono;

@Service
public class ClienteDAO {
  
  /**
   * URL del servidor del backend.
   */
  public static String urlBackend;

  @Autowired
  private WebClient webClient;

  public ClienteVO crearCliente(ClienteVO clienteVO) {

    try {
      WebClient webClient = WebClient.create(urlBackend);
      ClienteVO objCliente = null;
      Mono<ClienteVO> response = webClient.post()
        .uri(urlBackend + "/clientes/registrarCliente")
        .body(Mono.just(clienteVO), ClienteVO.class)
        .retrieve()
        .bodyToMono(ClienteVO.class);

      objCliente = response.block();
      return objCliente;

    } catch (WebClientResponseException e) {
      e.getMessage();
      System.out.println("---->" + e.getMessage());
      return null;
    }     
  }
  
  public List<ClienteVO> listaDeClientes() {

	    try {

	      URL url = new URL(urlBackend + "/clientes/listaDeClientes");// your url i.e fetch
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
	      Type tipoListaCliente = new TypeToken<ArrayList<ClienteVO>>() {
	      }.getType();

	      ArrayList<ClienteVO> clientes = gson.fromJson(json, tipoListaCliente);
	      return clientes;

	    } catch (Exception e) {
	      System.out.println("Exception in NetClientGet:- " + e);
	    }
	    return null;
	  }  
  
  public ClienteVO consultar(ClienteVO usr) {
	    WebClient webClient = WebClient.create(urlBackend);
	    ClienteVO objCliente = null;
	    Mono<ClienteVO> response = webClient.get()
	      .uri(urlBackend + "/buscar/?cedulausr=" + usr.getCedula())
	      .retrieve()
	      .bodyToMono(ClienteVO.class);
	    objCliente = response.block();

	    return objCliente;
	  }
  public ClienteVO getClientePorCedula(long cedula) {
	    WebClient webClient = WebClient.create(urlBackend);

	    Mono<ClienteVO> clienteMono = webClient
	      .get()
	      .uri(uriBuilder -> uriBuilder
	        .path("/clientes/buscar")
	        .queryParam("cedulausr", cedula)
	        .build())
	      .accept(MediaType.APPLICATION_JSON)
	      .retrieve()
	      .onStatus(status -> status == HttpStatus.NOT_FOUND,
	        response -> Mono.empty())
	      .bodyToMono(ClienteVO.class);
	    ClienteVO cliente = clienteMono.block();
	    return cliente;
	  }
  public ClienteVO editarCliente(ClienteVO clienteVO) {

	    try {
	      WebClient webClient = WebClient.create(urlBackend);
	      ClienteVO objCliente = null;
	      Mono<ClienteVO> response = webClient
	          .put()
	        .uri("/clientes/actualizarCliente")
	        .body(Mono.just(clienteVO), ClienteVO.class)
	        .retrieve()
	        .bodyToMono(ClienteVO.class);

	      objCliente = response.block();
	      return objCliente;

	    } catch (WebClientResponseException e) {
	      e.getMessage();
	      System.out.println("---->" + e.getMessage());
	      return null;
	    }
  }
  public void eliminarCliente(ClienteVO cliente) {
	    WebClient.create(urlBackend)
	        .delete()
	        .uri("/clientes/borrar/" + cliente.getCedula())
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	        .accept(MediaType.APPLICATION_JSON)
	        .retrieve()
	        .bodyToMono(ClienteVO.class)
	        .block();
	  }
}
