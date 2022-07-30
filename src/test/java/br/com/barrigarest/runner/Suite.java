package br.com.barrigarest.runner;

import br.com.barrigarest.core.BaseTest;
import br.com.barrigarest.tests.AuthTest;
import br.com.barrigarest.tests.ContasTest;
import br.com.barrigarest.tests.MovimentacaoTest;
import br.com.barrigarest.tests.SaldoTest;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(org.junit.runners.Suite.class)
@org.junit.runners.Suite.SuiteClasses({
        ContasTest.class,
        MovimentacaoTest.class,
        SaldoTest.class,
        AuthTest.class

})


public class Suite extends BaseTest {
    @BeforeClass
    public static void login(){
        Map<String, String> login = new HashMap<>();
        login.put("email", "areda.qa@gmail.com");
        login.put("senha", "test3qa");

        String TOKEN = given()
                .body(login)
        .when()
                .post("/signin")
        .then()
                .statusCode(200)
            .extract()
                .path("token");

        RestAssured.requestSpecification.header("Authorization", "JWT " +  TOKEN);

        RestAssured.get("/reset").then().statusCode(200);
    }
}
