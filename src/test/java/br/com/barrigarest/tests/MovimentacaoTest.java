package br.com.barrigarest.tests;

import br.com.barrigarest.core.BaseTest;
import br.com.barrigarest.pojo.Movimentacao;
import br.com.barrigarest.utils.DataUtils;
import org.junit.Test;


import static br.com.barrigarest.utils.TestesUtils.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class MovimentacaoTest extends BaseTest {


    @Test
    public void DeveInserirMovimentacaoComSucesso(){
        Movimentacao mov = getMovimentacaoValida();

        given()
                .body(mov)
        .when()
                .post("/transacoes")
        .then()
                .statusCode(201)
                .body("descricao", is("Movimentação de receita"))
                .body("envolvido", is("financeiro"))
                .body("tipo", is("Receita"))

        ;
    }

    @Test
    public void DeveValidarCamposObrigatoriosNaMovimentacao(){
        given()
                .body("{}")
        .when()
                .post("/transacoes")
        .then()
                .statusCode(400)
                .body("$", hasSize(8))
                .body("msg", hasItems(
                        "Data da Movimentação é obrigatório",
                        "Data do pagamento é obrigatório",
                        "Descrição é obrigatório",
                        "Interessado é obrigatório",
                        "Valor é obrigatório",
                        "Valor deve ser um número",
                        "Conta é obrigatório",
                        "Situação é obrigatório"))
        ;
    }

    @Test
    public void NaoDeveInserirMovimentacaoComDataFutura(){
        Movimentacao mov = getMovimentacaoValida();
        mov.setData_transacao(DataUtils.getDataDiferencaDias(2));

        given()
                .body(mov)
        .when()
                .post("/transacoes")
        .then()
                .statusCode(400)
                .body("$", hasSize(1))
                .body("msg", hasItem(("Data da Movimentação deve ser menor ou igual à data atual")))
        ;
    }


    @Test
    public void NaoDeveRemoverContaComMovimentacao(){
        Integer CONTA_ID = getIdContaNome("Conta com movimentacao");

        given()
                .pathParam("id", CONTA_ID)
                .when()
                .delete("/contas/{id}")
                .then()
                .statusCode(500)
                .body("constraint", is("transacoes_conta_id_foreign"))
        ;
    }

    @Test
    public void DeveRemoverMovimentacao(){
        Integer MOV_ID = getIdMovimentacaoNome("Movimentacao para exclusao");

        given()
                .pathParam("id", MOV_ID)
        .when()
                .delete("/transacoes/{id}")
        .then()
                .statusCode(204)
        ;
    }

    //metodo para inserir movimentação
    private Movimentacao getMovimentacaoValida(){
        Movimentacao mov = new Movimentacao();
        mov.setConta_id(getIdContaNome("Conta para movimentacoes"));
        mov.setDescricao("Movimentação de receita");
        mov.setEnvolvido("financeiro");
        mov.setTipo("Receita");
        mov.setData_transacao(DataUtils.getDataDiferencaDias(-1));
        mov.setData_pagamento(DataUtils.getDataDiferencaDias(5));
        mov.setValor(200f);
        mov.setStatus(true);

        return mov;
    }






}
