import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * Created by voja on 2/21/17.
 */
public class Encryptor {

    public byte [] encrypt(byte[] key, byte[] plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        byte [] iv = "1234567812345678".getBytes();
        aes.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(iv));
        return aes.doFinal(plaintext);
    }

    public byte [] decrypt(byte[] key, byte[] cyphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        byte [] iv = "1234567812345678".getBytes();
        aes.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(iv));
        return aes.doFinal(cyphertext);
    }
}
