package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.UsuarioVO;
import reactor.core.publisher.Mono;

@Service
public class ClienteDAO {
  private static final String URL = "http://localhost:5000";

  @Autowired
  private WebClient webClient;

  public ClienteVO crearCliente(ClienteVO clienteVO) {

    try {
      WebClient webClient = WebClient.create(URL);
      ClienteVO objCliente = null;
      Mono<ClienteVO> response = webClient.post()
        .uri(URL + "/clientes/registrarCliente")
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
  public ClienteVO consultar(ClienteVO usr) {
	    WebClient webClient = WebClient.create(URL);
	    ClienteVO objCliente = null;
	    Mono<ClienteVO> response = webClient.get()
	      .uri(URL + "/buscar/?cedulausr=" + usr.getCedula())
	      .retrieve()
	      .bodyToMono(ClienteVO.class);
	    objCliente = response.block();

	    return objCliente;
	  }
  public ClienteVO getClientePorCedula(long cedula) {
	    WebClient webClient = WebClient.create(URL);

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
	      WebClient webClient = WebClient.create(URL);
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
	    WebClient.create(URL)
	        .delete()
	        .uri("/clientes/borrar/" + cliente.getCedula())
	        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
	        .accept(MediaType.APPLICATION_JSON)
	        .retrieve()
	        .bodyToMono(UsuarioVO.class)
	        .block();
	  }
}
