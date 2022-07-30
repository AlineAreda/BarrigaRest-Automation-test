package br.com.barrigarest.tests;

import br.com.barrigarest.core.BaseTest;
import io.restassured.RestAssured;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class SaldoTest extends BaseTest {


    @Test
    public void DeveCalcularSaldoContas(){
        Integer CONTA_ID = getIdContaNome("Conta para saldo");

        given()
        .when()
                .get("/saldo")
        .then()
                .statusCode(200)
                .body("find{it.conta_id == "+CONTA_ID+"}.saldo", is("534.00"))
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
