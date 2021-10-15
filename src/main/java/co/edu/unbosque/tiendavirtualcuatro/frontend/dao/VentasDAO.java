package co.edu.unbosque.tiendavirtualcuatro.frontend.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import co.edu.unbosque.tiendavirtualcuatro.frontend.model.ClienteVO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.model.VentasVO;

@Service
public class VentasDAO {
	private static final String URL = "http://localhost:5000";	
	  @Autowired
	  private WebClient webClient;
	  
	  public ArrayList<VentasVO> listaDeVentas() {

		    try {

		      URL url = new URL(URL + "/ventas/listaDeVentas");// your url i.e fetch
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
		      Type tipoListaVenta = new TypeToken<ArrayList<VentasVO>>() {
		      }.getType();

		      ArrayList<VentasVO> ventas = gson.fromJson(json, tipoListaVenta);
		      return ventas;

		    } catch (Exception e) {
		      System.out.println("Exception in NetClientGet:- " + e);
		    }
		    return null;
		  }
}
