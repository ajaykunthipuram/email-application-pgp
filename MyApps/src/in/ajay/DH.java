package in.ajay;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyAgreement;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.spec.DHPublicKeySpec;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.ECNamedCurveTable;
import org.bouncycastle.jce.ECPointUtil;
//import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.jce.spec.ECNamedCurveParameterSpec;
import org.bouncycastle.jce.spec.ECNamedCurveSpec;
import org.hibernate.boot.model.source.internal.hbm.Helper;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;
import java.util.*;
import java.io.*;
import java.util.zip.*;
import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import java.math.BigInteger; 

import java.security.MessageDigest;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.*;
import java.security.*;
import javax.crypto.spec.*;
import java.nio.charset.*;

import com.mysql.cj.protocol.Security;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
//import org.apache.commons.codec.binary.Base64;

public class DH {
	
	static KeyAgreement keyAgreement;
	static byte[] sharedsecret;
    static String ALGO = "AES";
    
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    public static byte[] encrypt(byte[] data, String key, String type)throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if(type.equals("pub"))
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(key));
        else if(type.equals("pri"))
            cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(key));
        return cipher.doFinal((data));
    }

    public static byte[] decrypt(byte[] data , String key , String type) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if(type.equals("pub"))
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey(key));
        else if(type.equals("pri"))
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(key));
        return cipher.doFinal((data));

    }
    
    public static byte[] encrypt(byte[] plainText,SecretKey secreteKey,String alg,String mode){
        try {
            final IvParameterSpec iv;
            if(alg.equals("AES")) iv = new IvParameterSpec(new byte[16]);
            else iv = new IvParameterSpec(new byte[8]);
            SecretKeySpec skeySpec = new SecretKeySpec(secreteKey.getEncoded(), alg);
            String type=alg+"/"+mode+"/"+"PKCS5Padding";
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            byte[] encrypted = cipher.doFinal(plainText);
            return (encrypted);
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static byte[] decrypt(byte[] encrypted,SecretKey secreteKey,String alg,String mode) {
        try {
            final IvParameterSpec iv;
            if(alg.equals("AES")) iv = new IvParameterSpec(new byte[16]);
            else iv = new IvParameterSpec(new byte[8]);
            SecretKeySpec skeySpec = new SecretKeySpec(secreteKey.getEncoded(), alg);
            String type=alg+"/"+mode+"/"+"PKCS5Padding";
            Cipher cipher = Cipher.getInstance(type);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
            byte[] original = cipher.doFinal(encrypted);
            //Base64.decodeBase64(encrypted)
            return (original);
        } 
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;    
    }
    
    public static String messageDigest(String input){ 
        try { 
            MessageDigest md = MessageDigest.getInstance("SHA-1"); 
            byte[] messageDigest = md.digest(input.getBytes()); 
            BigInteger num = new BigInteger(1, messageDigest);
            String hashtext = num.toString(16); 
            while (hashtext.length() < 32) { 
                hashtext = "0" + hashtext; 
            }
            return hashtext; 
        }
        catch (NoSuchAlgorithmException e){ 
            throw new RuntimeException(e); 
        } 
    }

    public static String deflateBase64(String text) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try (Writer writer = new OutputStreamWriter(new DeflaterOutputStream(baos))) {
                writer.write(text);
            }
            return Base64.getEncoder().encodeToString(baos.toByteArray());
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    public static String inflateBase64(String base64) {
        try (Reader reader = new InputStreamReader(
                new InflaterInputStream(
                        new ByteArrayInputStream(
                                Base64.getDecoder().decode(base64))))) {
            StringWriter sw = new StringWriter();
            char[] chars = new char[1024];
            for (int len; (len = reader.read(chars)) > 0; )
                sw.write(chars, 0, len);
            return sw.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
    
    static ArrayList<byte[]> makeAgreement(String receiverPublicKey,String senderPrivateKey,String message) throws NoSuchProviderException, InvalidKeySpecException, InvalidKeyException {
        try {
        	byte[] msg = message.getBytes();
            System.out.println();

            //1. MESSAGE DIGEST
            byte[] messageDigest = messageDigest(new String(msg)).getBytes();
            //System.out.println("1");

            //2. ENCRYPT USING SENDER'S PRIVATE KEY
            byte[] digitalSignature = encrypt(messageDigest, senderPrivateKey, "pri");
            //System.out.println("2");

            //3. APPEND MESSAGE AND DIGITAL SIGNATURE
            int len = msg.length;
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            output.write(msg);
            output.write(digitalSignature);
            byte[] appendMsg = output.toByteArray();
            //System.out.println("3");
            //System.out.println(appendMsg);
            //System.out.println();
            //System.out.println(new String(appendMsg));

            //4. Compress 
            byte[] compressedMsg = deflateBase64(new String(appendMsg)).getBytes();
            //System.out.println("4");
            //System.out.println(new String(compressedMsg));

            //5. ENCRYPT USNIG SYMMETRIC KEY
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256);
            SecretKey secretKey = keyGenerator.generateKey();
            //System.out.println(secretKey);
            System.out.println("MESSAGE");
            System.out.println(new String(compressedMsg));
            byte[] encryptedString = encrypt(compressedMsg,secretKey,"AES","CBC");
            byte[] encryptedMsg = (encryptedString);
            
            

            //6. ENCRYPT SYMMETRIC KEY USING RECEIVER'S PUBLIC KEY
            byte[] symKey = (secretKey.getEncoded());
            byte[] enKey = (encrypt(symKey, receiverPublicKey, "pub"));
            System.out.println("KEY");
            System.out.println(new String(symKey));
            //String enmsg = new String(encryptedMsg, Charset.forName("UTF-8"));
            //String enkey = new String(enKey, Charset.forName("UTF-8"));

            //7. UPLOAD 5 AND 6 TO DATABASE
            // enKey , encryptedMsg , messageDigest 
            ArrayList<byte[]> ret = new ArrayList<byte[]>();
            ret.add(enKey);
            ret.add(encryptedMsg);
            ret.add(messageDigest);
            String l = Integer.toString(len);
            byte[] ll = l.getBytes();
            ret.add(ll);
            return ret;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<byte[]>();
    }

}
