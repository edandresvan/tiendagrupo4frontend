package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.google.gson.Gson;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.reflect.TypeToken;
 

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
  
  public List<UsuarioVO> listaDeUsuarios() {
		
		try {

          URL url = new URL(URL+"/usuarios/listaDeUsuarios");//your url i.e fetch data from .
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
          Type tipoListaUsuario = new TypeToken<ArrayList<UsuarioVO>>(){}.getType();
          
          ArrayList<UsuarioVO> usuarios = gson.fromJson(json, tipoListaUsuario);
          return usuarios;
          
      } catch (Exception e) {
          System.out.println("Exception in NetClientGet:- " + e);
      }
		return null;
	}
  
	public UsuarioVO consultar(UsuarioVO usr) {
		WebClient webClient =  WebClient.create(URL);
		UsuarioVO objUsuario = null;
		Mono<UsuarioVO> response = webClient.get().uri(URL+"/buscar/?cedulau="+usr.getCedula()).
			retrieve().bodyToMono(UsuarioVO.class);
		objUsuario = response.block();
		
		return objUsuario;
	}  

}
