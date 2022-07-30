package br.com.barrigarest.tests;

import br.com.barrigarest.core.BaseTest;
import io.restassured.RestAssured;
import io.restassured.specification.FilterableRequestSpecification;
import org.junit.Test;
import static io.restassured.RestAssured.given;


public class AuthTest extends BaseTest {


    @Test
    public void naoDeveAcessarApiSemToken(){
        FilterableRequestSpecification req = (FilterableRequestSpecification) RestAssured.requestSpecification;
        req.removeHeader("Authorization");

        given()
        .when()
                .get("/contas")
        .then()
                .statusCode(401)
        ;
    }
}
