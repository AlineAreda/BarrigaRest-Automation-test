package br.com.barrigarest.tests;

import br.com.barrigarest.core.BaseTest;
import io.restassured.RestAssured;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ContasTest extends BaseTest {



    @Test
    public void DeveIncluirContaComSucesso(){
        given()
                .body("{ \"nome\": \"conta pagamento\"}")
        .when()
                .post("/contas")
        .then()
                .statusCode(201)
                .body("nome", is("conta pagamento"));

    }
    @Test
    public void DeveAlterarContaComSucesso(){
        Integer CONTA_ID = getIdContaNome("Conta para alterar");

        given()
                .pathParam("id", CONTA_ID)
                .body("{ \"nome\": \"Conta alterada\"}")
        .when()
                .put("/contas/{id}")
        .then()
                .statusCode(200)
                        .body("nome", is("Conta alterada"));

    }

    @Test
    public void NaoDeveIncluirContaComNomeRepetido(){

        given()
                .body("{ \"nome\": \"Conta mesmo nome\"}")
        .when()
                .post("/contas")
        .then()
                .statusCode(400)
                .body("error", is("Já existe uma conta com esse nome!"))
        ;
    }

    //Metodo para pegar id da conta pelo nome
    public Integer getIdContaNome(String nome){
      return RestAssured.get("/contas?nome="+nome)
      .then()
          .extract()
              .path("id[0]");
    }
}
