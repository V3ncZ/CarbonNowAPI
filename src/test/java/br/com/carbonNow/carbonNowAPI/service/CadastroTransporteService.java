package br.com.carbonNow.carbonNowAPI.service;

import br.com.carbonNow.carbonNowAPI.hook.Hook;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;
import com.networknt.schema.ValidationMessage;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

public class CadastroTransporteService {

    private static final String BASE_URL = "http://localhost:8080/carbonnow";

    String schemasPath = "src/test/resources/schemas/";

    JSONObject jsonSchema;

    private final ObjectMapper mapper = new ObjectMapper();

    public Response cadastrarTransporte(Object transporteCadastroDto, String endpoint) {
        String token = Hook.getToken();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .body(transporteCadastroDto)
                .when()
                .post(BASE_URL + endpoint)
                .then()
                .extract()
                .response();
    }

    public Response excluirTransporte(Long id) {
        String token = Hook.getToken();
        return RestAssured
                .given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + token)
                .when()
                .delete(BASE_URL + "/deletarTransporte/" + id)
                .then()
                .extract()
                .response();
    }

    private JSONObject loadJsonFromFile(String filePath) throws IOException, JSONException {
        // Lê todos os bytes do arquivo e transforma em String
        String jsonContent = Files.readString(Paths.get(filePath));

        // Cria o tokener a partir do conteúdo real do JSON
        JSONTokener tokener = new JSONTokener(jsonContent);

        // Retorna o objeto JSON carregado corretamente
        return new JSONObject(tokener);
    }

    public void setContract(String contract) throws IOException, JSONException {
        switch (contract) {
            case "Cadastro de transporte bem sucedido" -> jsonSchema = loadJsonFromFile(schemasPath + "cadastro-transporte-schema.json");
            default -> throw new IllegalStateException("Unexpected contract" + contract);
        }
    }

    public Set<ValidationMessage> validateResponseAgainstSchema(Response response) throws IOException, JSONException {
        JSONObject jsonResponse = new JSONObject(response.getBody().asString());
        JsonSchemaFactory schemaFactory = JsonSchemaFactory.getInstance(SpecVersion.VersionFlag.V4);
        JsonSchema schema = schemaFactory.getSchema(jsonSchema.toString());
        JsonNode jsonResponseNode = mapper.readTree(jsonResponse.toString());
        Set<ValidationMessage> schemaValidationErrors = schema.validate(jsonResponseNode);
        return schemaValidationErrors;
    }
}
