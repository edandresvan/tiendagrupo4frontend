package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
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
      Mono<ClienteVO> response = webClient.post().uri(URL + "/clientes/registrarCliente")
          .body(Mono.just(clienteVO), ClienteVO.class).retrieve().bodyToMono(ClienteVO.class);

      clienteVO = response.block();
      return clienteVO;

    } catch (WebClientResponseException e) {
      e.getMessage();
      System.out.println("---->" + e.getMessage());
      return null;
    }
   }
}
