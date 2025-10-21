package br.com.carbonNow.carbonNowAPI.service;

import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import br.com.carbonNow.carbonNowAPI.hook.Hook;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import static io.restassured.RestAssured.given;

@Service

public class CadastroUsuarioService {

    private static final String BASE_URL = "http://localhost:8080/carbonnow";



    public Response cadastrarUsuario(Object usuarioCadastroDto, String endpoint) {
        String token = Hook.getToken();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(usuarioCadastroDto)
                .when()
                .post(BASE_URL + endpoint)
                .then()
                .extract()
                .response();
    }
}
