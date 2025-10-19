package service;

import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CadastroUsuarioService {
    private static final String BASE_URL = "https://localhost:8080/carbonnow";

    public Response cadastrarUsuario(UsuarioCadastroDto usuario) {
        return given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post(BASE_URL + "/cadastrarUsuario");
    }
}
