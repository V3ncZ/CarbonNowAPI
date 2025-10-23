package br.com.carbonNow.carbonNowAPI.steps;

import br.com.carbonNow.carbonNowAPI.domain.UsuarioRole;
import br.com.carbonNow.carbonNowAPI.dto.UsuarioCadastroDto;
import com.networknt.schema.ValidationMessage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.bs.A;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import br.com.carbonNow.carbonNowAPI.service.CadastroUsuarioService;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@SpringBootTest
public class CadastroUsuarioSteps {

    private final CadastroUsuarioService cadastroUsuarioService = new CadastroUsuarioService();

    private UsuarioCadastroDto usuarioCadastroDto;
    private Response response;


    @Dado("que eu tenha os seguintes dados do usuário:")
    public void QueEuTenhaOsSeguintesDadosDoUsuario(DataTable dataTable) {
        Map<String, String> dados = dataTable.asMap(String.class, String.class);

        usuarioCadastroDto = new UsuarioCadastroDto(
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

    @Então("o status code da resposta de usuario deve ser {int}")
    public void OStatusCodeDaRespostaDeDadosDeUsuario(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @E("o arquivo de contrato esperado é o {string}")
    public void oCorpoDaRespostaDeveSeguirOSchema(String schemaPath) throws IOException, JSONException {
        cadastroUsuarioService.setContract(schemaPath);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato selecionado")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionado() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = cadastroUsuarioService.validateResponseAgainstSchema(response);
        Assert.assertTrue("O contrato está inválido!. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }


    @After
    public void limparDados() {
        if (response != null && response.getStatusCode() == 201) {
            Long id = response.jsonPath().getLong("id");
            cadastroUsuarioService.excluirUsuario(id);
        }
        System.out.println("Limpando dados de teste...");
    }

}
