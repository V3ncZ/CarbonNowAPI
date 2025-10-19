package steps;

import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.junit.Assert;
import service.CadastroUsuarioService;

import static br.com.carbonNow.carbonNowAPI.domain.UsuarioRole.ADMIN;

public class CadastroUsuarioSteps {

    private UsuarioCadastroDto usuarioCadastroDto;
    private Response response;

    private final CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();

    @Dado("que eu tenha os seguintes dados do usuário:")
    public void QueEuTenhaOsSeguintesDadosDoUsuario() {
        usuarioCadastroDto = new UsuarioCadastroDto(
                null,
                "Vinicius Cesar",
                "viniciuscesar@gmail.com",
                "Mudar@123456",
                ADMIN);
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar usuario")
    public void EuEnviarARequisicaoParaoEndpointDeCadastrarUsuario(String endpoint) {
        response = cadastroUsuarioService.cadastrarUsuario(usuarioCadastroDto);
    }

    @Então("o status code da resposta deve ser {int}")
    public void OStatusCodeDaRespostaDeDadosDeUsuario(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());

    }

}
