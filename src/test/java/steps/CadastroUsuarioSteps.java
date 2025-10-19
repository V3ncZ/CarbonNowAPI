package steps;

import br.com.carbonNow.carbonNowAPI.domain.UsuarioRole;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.junit.Assert;
import service.CadastroUsuarioService;

import java.util.Map;

import static br.com.carbonNow.carbonNowAPI.domain.UsuarioRole.ADMIN;

public class CadastroUsuarioSteps {

    private UsuarioCadastroDto usuarioCadastroDto;
    private Response response;

    private final CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();

    @Dado("que eu tenha os seguintes dados do usuário:")
    public void QueEuTenhaOsSeguintesDadosDoUsuario(DataTable dataTable) {
        Map<String, String> dados = dataTable.asMap(String.class, String.class);

        usuarioCadastroDto = new UsuarioCadastroDto(
                null,
                dados.get("nome"),
                dados.get("email"),
                dados.get("senha"),
                UsuarioRole.valueOf(dados.get("role"))
        );
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar usuario")
    public void EuEnviarARequisicaoParaoEndpointDeCadastrarUsuario(String endpoint) {
        response = cadastroUsuarioService.cadastrarUsuario(usuarioCadastroDto, endpoint);
    }

    @Então("o status code da resposta deve ser {int}")
    public void OStatusCodeDaRespostaDeDadosDeUsuario(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());

    }

}
