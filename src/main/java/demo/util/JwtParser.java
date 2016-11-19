package demo.util;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class JwtParser {

    private static final Logger LOG = LoggerFactory.getLogger(JwtParser.class);

    @Value("${security.oauth2.client.privateKey}")
    private String privateKey;

    @Value("${security.oauth2.client.clientSecret}")
    private String clientSecret;

    //Sample method to validate and read the JWT
    public void parseJWT(String jwt) {
        try {
            LOG.info("jwt={}", jwt);
            //This line will throw an exception if it is not a signed JWS (as expected)
            Claims claims = Jwts.parser()
                    .setSigningKey(getHmacKeyString(clientSecret)).parseClaimsJws(jwt).getBody();
                //.setSigningKey(DatatypeConverter.parseBase64Binary(clientSecret)).parseClaimsJws(jwt).getBody();
            //jwtSecret.getBytes("UTF-8")
            LOG.info("ID ={}", claims.getId());
            LOG.info("Subject={} ", claims.getSubject());
            LOG.info("Issuer={} ", claims.getIssuer());
            LOG.info("Expiration{} ", claims.getExpiration());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void getHMACKey(String key){

        final Charset charSet = Charset.forName("US-ASCII");
        Mac sha256_HMAC = null;
        try{
            sha256_HMAC = Mac.getInstance("HmacSHA256");

        }catch(NoSuchAlgorithmException e){

        }

        final SecretKeySpec secret_key = new javax.crypto.spec.SecretKeySpec(charSet.encode(key).array(), "HmacSHA256");
        try {
            sha256_HMAC.init(secret_key);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private String getHmacKeyString(String clientSecret){
        String result = null;

        try {
            // Generate a key for the HMAC-MD5 keyed-hashing algorithm; see RFC 2104
            // In practice, you would save this key.
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacMD5");
            SecretKey key = keyGen.generateKey();

            // Create a MAC object using HMAC-MD5 and initialize with key
            Mac mac = Mac.getInstance(key.getAlgorithm());
            mac.init(key);

            String str = "This message will be digested";

            // Encode the string into bytes using utf-8 and digest it
            byte[] utf8 = str.getBytes("UTF8");
            byte[] digest = mac.doFinal(utf8);

            // If desired, convert the digest into a string
            result = new sun.misc.BASE64Encoder().encode(digest);
        } catch (InvalidKeyException e) {
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }

        return result;
    }
}
