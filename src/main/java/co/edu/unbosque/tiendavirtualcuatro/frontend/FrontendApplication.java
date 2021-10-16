package co.edu.unbosque.tiendavirtualcuatro.frontend;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.StreamingHttpOutputMessage;

import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.ClienteDAO;
import co.edu.unbosque.tiendavirtualcuatro.frontend.dao.UsuarioDAO;


@SpringBootApplication
public class FrontendApplication {

  /**
   * Ayuda a cargar los datos del archivo application.properties y otras mas.
   */
  @Autowired
  private Environment env;

  /**
   * Ejecuta acciones posteriores a la inicialización de la aplicación.
   */
  @PostConstruct
  public void init() {
    // Configurar los datos necesarios para las clases que se conectan a los 
    // servicios del backend
    UsuarioDAO.urlBackend = env.getProperty("servidorapi.url");
    ClienteDAO.urlBackend = env.getProperty("servidorapi.url");
  }
  
	public static void main(String[] args) {
		SpringApplication.run(FrontendApplication.class, args);
	}
	
}
