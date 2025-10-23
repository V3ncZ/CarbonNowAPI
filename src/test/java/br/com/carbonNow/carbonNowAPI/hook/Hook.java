package br.com.carbonNow.carbonNowAPI.hook;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import lombok.Getter;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

public class Hook {
    @Getter
    private static String token;

    @Before(order = 0)
    public void gerarTokenSeNecessario() {
        if (token == null) {
            token = autenticarUsuarioExistente("teste@gmail.com", "Mudar@1234");
            System.out.println("Token obtido: " + token);
        }
    }

    private String autenticarUsuarioExistente(String email, String senha) {
        String url = "http://localhost:8080/auth/login";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> requestBody = Map.of(
                "email", email,
                "senha", senha
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(requestBody, headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                url, HttpMethod.POST, request, Map.class
        );

        return (String) response.getBody().get("token");
    }


}