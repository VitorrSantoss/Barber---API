package com.vitorsantos.barbearia_api.config;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class SwaggerAutoOpen {

  @EventListener(ApplicationReadyEvent.class)
  public void abrirSwagger() {
    String url = "http://localhost:8080/swagger-ui/index.html";

    try {
      new ProcessBuilder("cmd", "/c", "start", "", url).start();
      System.out.println("Abrindo Swagger em: " + url);
    } catch (Exception e) {
      System.out.println("Não consegui abrir o navegador automaticamente.");
      System.out.println("Acesse manualmente: " + url);
      e.printStackTrace();
    }
  }
}