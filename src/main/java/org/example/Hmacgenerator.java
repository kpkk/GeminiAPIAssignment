package org.example;

import com.sun.org.apache.xml.internal.security.algorithms.implementations.IntegrityHmac;
import com.sun.org.apache.xml.internal.security.signature.XMLSignatureException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

public class Hmacgenerator {

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {

        String secret="PSg3zjiJFh5P2U9Kh6qGBG8UTBs";
        Date d=new Date();
        long time = d.getTime();
        System.out.println(time);
        String message="{\n" +
                "    \"request\": \"/v1/order/new\",\n" +
                "    \"nonce\": \""+time+"\",\n" +
                "    \"client_order_id\": 1,\n" +
                "    \"symbol\": \"btcusd\",\n" +
                "    \"amount\": \"5\",\n" +
                "    \"price\": \"3633.00\",\n" +
                "    \"side\": \"buy\",\n" +
                "    \"type\": \"exchange limit\"\n" +
                "}";
        Mac mac = Mac.getInstance("HmacSHA384");
        SecretKeySpec skey=new SecretKeySpec(secret.getBytes(),"HmacSHA384");
        mac.init(skey);
        String s = Base64.encodeBase64String(mac.doFinal(message.getBytes()));
       String hex  = Hex.encodeHexString(s.getBytes());
        System.out.println(hex);


    }
}
