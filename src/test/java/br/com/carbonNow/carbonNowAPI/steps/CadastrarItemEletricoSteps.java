package br.com.carbonNow.carbonNowAPI.steps;

import br.com.carbonNow.carbonNowAPI.dto.ItemEletricoCadastroDto;
import br.com.carbonNow.carbonNowAPI.service.CadastroItemEletricoService;
import com.networknt.schema.ValidationMessage;
import io.cucumber.java.After;
import io.cucumber.java.pt.*;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;

import java.io.IOException;
import java.util.Set;

public class CadastrarItemEletricoSteps {

    private final CadastroItemEletricoService cadastroItemEletricoService = new CadastroItemEletricoService();

    private ItemEletricoCadastroDto itemEletricoCadastroDto;
    private Response response;

    @Dado("que eu tenha os seguintes dados do item elétrico:")
    public void queEuTenhaOsSeguintesDadosDoItemEletrico(io.cucumber.datatable.DataTable dataTable) {
        java.util.Map<String, String> dados = dataTable.asMap(String.class, String.class);
        itemEletricoCadastroDto = new ItemEletricoCadastroDto(
                12L, //Sempre utilizar 12 pois é o ID do usuario de testes
                dados.get("nome"),
                Double.parseDouble(dados.get("consumoEmKw")),
                java.time.LocalDate.parse(dados.get("dataUso")),
                Double.parseDouble(dados.get("emissaoDeCarbono"))
        );
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar item elétrico")
    public void euEnviarARequisicaoParaOEndpointDeCadastrarItemEletrico(String endpoint){
        response = cadastroItemEletricoService.cadastrarItemEletrico(itemEletricoCadastroDto, endpoint);
    }

    @Entao("o status code da resposta de item eletrico deve ser {int}")
    public void oStatusCodeDaRespostaDeveSer(int statusCode) {
        org.junit.Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @E("o arquivo de contrato esperado é o {string} de item elétrico")
    public void oCorpoDaRespostaDeveSeguirOSchemaItemEletrico(String schemaPath) throws IOException, JSONException {
        cadastroItemEletricoService.setContract(schemaPath);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato de item elétrico")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionadoDeItemEletrico() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = cadastroItemEletricoService.validateResponseAgainstSchema(response);
        Assert.assertTrue("O contrato está inválido!. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @After
    public void limparDados() {
        if (response != null && response.getStatusCode() == 201) {
            Long id = response.jsonPath().getLong("idItemEletrico");
            cadastroItemEletricoService.excluirItemEletrico(id);
        }
        System.out.println("Limpando dados de teste...");
    }
}
