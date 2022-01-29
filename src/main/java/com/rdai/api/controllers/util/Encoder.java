package com.rdai.api.controllers.util;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;

public class Encoder {

    private static final String LLAVE_ENCRIPTACION = "esto-es-una-llave-de-encriptacion";

    protected String encriptar(String texto) throws Exception{
        Key aesKey = new SecretKeySpec(LLAVE_ENCRIPTACION.getBytes(),"AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, aesKey);

        byte[] encriptado = cipher.doFinal(texto.getBytes());

        return Base64.getEncoder().encodeToString(encriptado);
    }


    protected   String desencriptar(String encrypted) throws Exception {
        byte[] encryptedBytes=Base64.getDecoder().decode(encrypted.replace("\n", ""));

        Key aesKey = new SecretKeySpec(LLAVE_ENCRIPTACION.getBytes(), "AES");

        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, aesKey);

        return new String(cipher.doFinal(encryptedBytes));
    }

}
