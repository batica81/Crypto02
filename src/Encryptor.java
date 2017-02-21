import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * Created by voja on 2/21/17.
 */
public class Encryptor {

    public byte [] encrypt(byte[] key, byte[] plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher aes = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        aes.init(Cipher.ENCRYPT_MODE, k);
        return aes.doFinal(plaintext);
    }

    public byte [] decrypt(byte[] key, byte[] cyphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {

        Cipher aes = Cipher.getInstance("AES");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        aes.init(Cipher.DECRYPT_MODE, k);
        return aes.doFinal(cyphertext);
    }
}
