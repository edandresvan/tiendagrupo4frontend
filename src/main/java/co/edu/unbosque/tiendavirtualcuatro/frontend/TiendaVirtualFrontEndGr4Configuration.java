package co.edu.unbosque.tiendavirtualcuatro.frontend;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration

public class TiendaVirtualFrontEndGr4Configuration {
  private static final String URL = "http://localhost:5000";
  
   @Bean
      public WebClient myWebClient(WebClient.Builder webClientBuilder) {
          return webClientBuilder
                  .baseUrl(URL)
                  .defaultHeader(HttpHeaders.CONTENT_TYPE, "APPLICATION_JSON")
                  .defaultHeader(HttpHeaders.USER_AGENT, "tiendavirtualfrontendGrupo4")
                  .build();
      }

}

