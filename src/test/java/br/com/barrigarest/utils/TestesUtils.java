package br.com.barrigarest.utils;

import io.restassured.RestAssured;

public class TestesUtils {

    //Metodo para pegar id da conta pelo nome
    public static Integer getIdContaNome(String nome){
        return RestAssured.get("/contas?nome="+nome)
                .then()
                .extract()
                .path("id[0]");
    }

    //Metodo para pegar id da movimentação pelo nome
    public static Integer getIdMovimentacaoNome(String desc){
        return RestAssured.get("/transacoes?descricao="+desc)
                .then()
                .extract()
                .path("id[0]");
    }




}
