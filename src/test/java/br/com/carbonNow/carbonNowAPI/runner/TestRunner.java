package br.com.carbonNow.carbonNowAPI.runner;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
// REMOVIDOS imports de Spring, pois esta classe só deve rodar o Cucumber

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        // CORRIGIDO: Usando pacote Java (ponto) e direcionando para o raiz.
        glue =  {"br.com.carbonNow.carbonNowAPI"},
        tags = "@regressivo",
        plugin = {"html:target/cucumber-reports.html"}
)
public class TestRunner {
}
