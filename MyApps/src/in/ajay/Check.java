package in.ajay;

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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Check {
	static String privateKey;
	static String publicKey;
	public static String check(String from , byte[] encryptedMsg, byte[] enKey , String md ,int l ) throws Exception{
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");//.newInstance();
			//Connection con = DriverManager.getConnection(url,username,password);
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC","root","");
			
			System.out.println("connected");
			
			String sql1 = "SELECT PRIKEY FROM PUBLIC WHERE EMAIL = ?";
			PreparedStatement ps1 = con.prepareStatement(sql1);
			ps1.setString(1,LoginServlet.currentUser);
			ResultSet rs1 = ps1.executeQuery();
			rs1.next();
			//System.out.println("yep ok");
			privateKey = (String)rs1.getString(1);
			
			String sql2 = "SELECT PUBKEY FROM PUBLIC WHERE EMAIL = ?";
			PreparedStatement ps2 = con.prepareStatement(sql2);
			ps2.setString(1,from);
			ResultSet rs2 = ps2.executeQuery();
			
			//System.out.println("pub query ok");
			
			//RECEIVER'S PUBLIC-KEY
			rs2.next();
			publicKey = rs2.getString(1);
			
			return dec(privateKey, publicKey, encryptedMsg, enKey, md, l);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return "";
	}
	static String dec(String receiverPrivateKey, String senderPublicKey, byte[] encryptedMsg, byte[] enKey, String md, int len)throws Exception{
		System.out.println("receiver");


        byte[] deSymKey = (decrypt(enKey, receiverPrivateKey, "pri"));
        System.out.println("RECEIVED KEY");
        System.out.println(new String(deSymKey));
        SecretKey originalKey = new SecretKeySpec(deSymKey, 0, deSymKey.length, "AES");
        //System.out.println(originalKey);
        byte[] decryptedMsg = decrypt(encryptedMsg,originalKey,"AES","CBC");
        System.out.println("RECEIVED MESSAGE");
        System.out.println(new String(decryptedMsg));

        //3. UNZIP
        byte[] decompressedMsg = inflateBase64(new String(decryptedMsg)).getBytes();
        

        //4. TAKE DIGITAL SIGNATURE AND DECRYPT WITH SENDER'S PUBLIC KEY
        // len
        int len2 = decompressedMsg.length;
        byte[] receivedDigitalSignature = Arrays.copyOfRange(decompressedMsg, len, len2);
        

        //5. COMPUTE MESSAGE DIGEST
        byte[] receivedMsg = Arrays.copyOfRange(decompressedMsg, 0, len);
        
       System.out.println(new String(receivedMsg));
        byte[] receivedMsgMessageDigest = messageDigest(new String(receivedMsg)).getBytes();
        

        //6. CHECK FOR INTEGRITY
        
        System.out.println(Arrays.equals(md.getBytes(), receivedMsgMessageDigest) + "Good");
        return new String(receivedMsg);
	}
	
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

    /*public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());

    }*/

    public static byte[] encrypt(byte[] data, String key, String type)throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if(type.equals("pub"))
            cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(key));
        else if(type.equals("pri"))
            cipher.init(Cipher.ENCRYPT_MODE, getPrivateKey(key));
        return cipher.doFinal((data));
    }

    /*public static String decrypt(byte[] data, PrivateKey privateKey) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return new String(cipher.doFinal(data));
    }

    public static String decrypt(String data, String base64PrivateKey) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()), getPrivateKey(base64PrivateKey));
    }*/

    public static byte[] decrypt(byte[] data , String key , String type) throws Exception{
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        if(type.equals("pub"))
            cipher.init(Cipher.DECRYPT_MODE, getPublicKey(key));
        else if(type.equals("pri"))
            cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(key));
        return cipher.doFinal((data));

    }

    public static String savePrivateKey(PrivateKey priv) throws Exception {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        PKCS8EncodedKeySpec spec = fact.getKeySpec(priv,PKCS8EncodedKeySpec.class);
        byte[] packed = spec.getEncoded();
        String base64encodedString = Base64.getEncoder().encodeToString(packed);
        return base64encodedString;

    }

    public static String savePublicKey(PublicKey publ) throws Exception {
        KeyFactory fact = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = fact.getKeySpec(publ,X509EncodedKeySpec.class);
        String base64encodedString = Base64.getEncoder().encodeToString(spec.getEncoded());
        return base64encodedString;
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
}
