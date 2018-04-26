/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author Yuu
 */
public class Encrypt implements Serializable {

    private static final String base64SecretBytes = "WzENWuNHMXhUJfxZzHHA90oIPlZilTQTAAyv/BoKm1Q=";

    public static String EncryptPwd(String pwd) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(pwd.getBytes());
        byte[] pwdEncrypted = md.digest();
        return DatatypeConverter.printHexBinary(pwdEncrypted).toUpperCase();
    }

    public static String generateToken(String phone, String mail) {
        String id = UUID.randomUUID().toString().replace("-", "");
        Date now = new Date();
        Date exp = new Date(System.currentTimeMillis() + (1000 * 60 * 5)); // 5 mins

        String token = Jwts.builder()
                .setId(id)
                .setAudience(phone)
                .setSubject(mail)
                .setNotBefore(now)
                .setExpiration(exp)
                .signWith(SignatureAlgorithm.HS256, base64SecretBytes)
                .compact();

        return token;
    }
    
    public static String [] verifyToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser().setSigningKey(base64SecretBytes).parseClaimsJws(token).getBody();
        } catch (ExpiredJwtException e) {
            return null;
        }
        return new String [] {claims.getAudience(), claims.getSubject()};
    }
   
}
