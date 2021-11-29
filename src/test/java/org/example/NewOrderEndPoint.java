package org.example;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.HashMap;

public class NewOrderEndPoint {

    public static String secret="PSg3zjiJFh5P2U9Kh6qGBG8UTBs";
    public static HashMap<String, String> map;
    public String payLoadMessage="{\n" +
            "    \"request\": \"/v1/order/new\",\n" +
            "    \"nonce\": \""+new Date().getTime() +"\",\n" +
            "    \"client_order_id\": 1,\n" +
            "    \"symbol\": \"btcusd\",\n" +
            "    \"amount\": \"5\",\n" +
            "    \"price\": \"3633.00\",\n" +
            "    \"side\": \"buy\",\n" +
            "    \"type\": \"exchange limit\"\n" +
            "}";

    // method to convert the Json payload into base64 string
    public String convertPayLoadtoBase64(){
        return Base64.encodeBase64String(payLoadMessage.getBytes());
    }

    // method to generate HMAC_SHA384 then into hex code
    public String createSignature() throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA384");
        SecretKeySpec skey=new SecretKeySpec(secret.getBytes(),"HmacSHA384");
        mac.init(skey);
        return Hex.encodeHexString(payLoadMessage.getBytes());

    }

    // Setting up request the URI, headers- will run once before every @test case

    @BeforeTest
    public void setUp() throws NoSuchAlgorithmException, InvalidKeyException{
        RestAssured.baseURI="https://api.gemini.com";
        map=new HashMap<String,String>();
        map.put("Content-Type","text/plain");
        map.put("X-GEMINI-APIKEY","account-DUx9nEgHEjqRIjjWL2QB");
        map.put("X-GEMINI-PAYLOAD",convertPayLoadtoBase64());
        map.put("X-GEMINI-SIGNATURE", createSignature());

    }

    // A positive test scenario to create a new order successfully and verify the headers and the response
    @Test
    public void createNewOrder(){
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/new")
                .then().log().all().statusCode(200).extract().response();
                 response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("symbol").equals("btcusd");
        jsonPath.get("price").equals("3633.00");
        jsonPath.get("original_amount").equals("5");
    }

    // A negative test scenario to create a new order successfully and verify the headers and the response
    @Test
    public void validate_invalid_endpoint(){
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/n")
                .then().log().all().statusCode(404).extract().response();
        response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("EndpointNotFound");
        jsonPath.get("message").equals("PI entry point `/v1/order/n` not found");
    }

    // A negative test scenario to try to create a new order with an api key that's not assigned with trader role and verify and the response- 403 status
    @Test
    public void validate_createOrder_with_NoTraderRole_APIkey(){
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/new")
                .then().log().all().statusCode(403).extract().response();
        response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("MissingRole");
        jsonPath.get("message").equals("To access this endpoint, you need to log in to the website and go to the settings page to assign one of these roles [FundManager] to API key "+map.get("X-GEMINI-API-KEY")+" which currently has roles [Trader]");
    }

    // A negative test scenario with invalid signature results in 400 - order shouldn't be created
    @Test
    public void validate_InvalidSignature_error(){
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/new")
                .then().log().all().statusCode(400).extract().response();
        //response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("InvalidSignature");
        jsonPath.get("message").equals("InvalidSignature");
    }

    // A test scenario with out passing the mandatory header API key and verify the response, order shouldn't be created
    @Test
    public void validate_MissingAPIkeyHeader_error(){
        map.remove("X-GEMINI-APIKEY");
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/new")
                .then().log().all().statusCode(400).extract().response();
        //response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("MissingApikeyHeader");
        jsonPath.get("message").equals("Must provide 'X-GEMINI-APIKEY' header");

    }

    // A test scenario with out passing the mandatory header payload and verify the response, order shouldn't be created

    @Test
    public void validate_Missingpayloadkey_Header_error(){
        map.remove("X-GEMINI-PAYLOAD");
        Response response = RestAssured.given().log().all().
                headers(map).
                when().post("v1/order/new")
                .then().log().all().statusCode(400).extract().response();
        //response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("MissingPayloadHeader");
        jsonPath.get("message").equals("Must provide 'X-GEMINI-PAYLOAD' header");

    }

    // A negative test scenario to validate the create order w/o nonce value in the payload request

    @Test
    public void validate_Missing_nonce_In_payload_error(){
        Response response = RestAssured.given().log().all().
                headers(map).
                when().body("{\n" +
                "    \"request\": \"/v1/order/new\",\n" +
                "    \"client_order_id\": 1,\n" +
                "    \"symbol\": \"btcusd\",\n" +
                "    \"amount\": \"5\",\n" +
                "    \"price\": \"3633.00\",\n" +
                "    \"side\": \"buy\",\n" +
                "    \"type\": \"exchange limit\"\n" +
                "}").
                post("v1/order/new")
                .then().log().all().statusCode(400).extract().response();
        //response.headers().equals(map);
        JsonPath jsonPath = response.jsonPath();
        jsonPath.get("result").equals("error");
        jsonPath.get("reason").equals("InvalidSignature");
        jsonPath.get("message").equals("InvalidSignature");

    }





}
