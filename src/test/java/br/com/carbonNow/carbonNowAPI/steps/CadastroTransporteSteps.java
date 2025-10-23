package br.com.carbonNow.carbonNowAPI.steps;

import br.com.carbonNow.carbonNowAPI.dto.TransporteCadastroDto;
import br.com.carbonNow.carbonNowAPI.service.CadastroTransporteService;
import com.networknt.schema.ValidationMessage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.es.Dado;
import io.cucumber.java.it.Quando;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Entao;
import io.cucumber.java.pt.Então;
import io.restassured.response.Response;
import org.json.JSONException;
import org.junit.Assert;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class CadastroTransporteSteps {

    private final CadastroTransporteService cadastroTransporteService = new CadastroTransporteService();

    private TransporteCadastroDto transporteCadastroDto;
    private Response response;

    @Dado("que eu tenha os seguintes dados do transporte:")
    public void QueEuTenhaOsSeguintesDadosDoTransporte(DataTable dataTable) {
        Map<String, String> dados = dataTable.asMap(String.class, String.class);
        transporteCadastroDto = new TransporteCadastroDto(
                12L, //Sempre utilizar 12 pois é o ID do usuario de testes
                dados.get("nome"),
                Double.parseDouble(dados.get("distanciaEmKm")),
                java.time.LocalDate.parse(dados.get("dataDeUso")),
                Double.parseDouble(dados.get("emissaoDeCarbono")),
                Double.parseDouble(dados.get("emissaoPermitidaIso")),
                Boolean.parseBoolean(dados.get("conformeIso"))
        );
    }

    @Quando("eu enviar a requisição para o endpoint {string} de cadastrar transporte")
    public void EuEnviarARequisicaoParaoEndpointDeCadastrarTransporte(String endpoint) {
        response = cadastroTransporteService.cadastrarTransporte(transporteCadastroDto, endpoint);
    }

    @Entao("o status code da resposta de transporte deve ser {int}")
    public void OStatusCodeDaRespostaDeveSer(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode());
    }

    @E("o arquivo de contrato esperado é o {string} de transporte")
    public void oCorpoDaRespostaDeveSeguirOSchemaTransporte(String schemaPath) throws IOException, JSONException {
        cadastroTransporteService.setContract(schemaPath);
    }

    @Então("a resposta da requisição deve estar em conformidade com o contrato de transporte")
    public void aRespostaDaRequisiçãoDeveEstarEmConformidadeComOContratoSelecionadoDeTransporte() throws IOException, JSONException {
        Set<ValidationMessage> validateResponse = cadastroTransporteService.validateResponseAgainstSchema(response);
        Assert.assertTrue("O contrato está inválido!. Erros encontrados: " + validateResponse, validateResponse.isEmpty());
    }

    @After
    public void limparDados() {
        if (response != null && response.getStatusCode() == 201) {
            Long id = response.jsonPath().getLong("idTransporte");
            cadastroTransporteService.excluirTransporte(id);
        }
        System.out.println("Limpando dados de teste...");
    }
}
