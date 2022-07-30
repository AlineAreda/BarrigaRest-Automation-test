package br.com.barrigarest.core;

import io.restassured.http.ContentType;

public interface Constantes {

    String APP_BASE_URL = "https://barrigarest.wcaquino.me/";
    Integer APP_PORTA = 443;
    String APP_BASE_PATH = "";

   ContentType APP_CONTENT_TYPE = ContentType.JSON;
   Long MAX_TIMEOUT = 5000L;
}
