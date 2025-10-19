package service;

import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.springframework.test.context.ActiveProfiles;

import static io.restassured.RestAssured.given;

@ActiveProfiles("test")
public class CadastroUsuarioService {

    private static final String BASE_URL = "http://localhost:8080/carbonnow";

    public Response cadastrarUsuario(UsuarioCadastroDto usuario, String endpoint) {
        return given()
                .contentType(ContentType.JSON)
                .body(usuario)
                .when()
                .post(BASE_URL + endpoint);
    }
}
