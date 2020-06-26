package in.ajay;

import java.security.KeyPairGenerator;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.*;
/*import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;*/

public class KeyPairGenerate {
	
	 
	 KeyAgreement keyAgreement;
	 byte[] sharedsecret;
	    
	 static String[] generateKeyPair() {
        KeyPairGenerator kpg = null;
        PublicKey publickey;
   	 	PrivateKey privatekey;
        try {
            kpg = KeyPairGenerator.getInstance("RSA");
            
            KeyPair kp = kpg.generateKeyPair();
            publickey = kp.getPublic();
            privatekey = kp.getPrivate();
            
            /*keyAgreement = KeyAgreement.getInstance("ECDH");
            keyAgreement.init(kp.getPrivate());*/
            
            byte[] byte_pubkey = publickey.getEncoded();
            //System.out.println("\nBYTE KEY::: " + byte_pubkey);
            String strPU = Base64.getEncoder().encodeToString(byte_pubkey);
            
            byte[] byte_prikey = privatekey.getEncoded();
            //System.out.println("\nBYTE KEY::: " + byte_pubkey);
            String strPR = Base64.getEncoder().encodeToString(byte_prikey);
            
            return new String[]{strPU,strPR};

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return new String[]{};
    }
}
