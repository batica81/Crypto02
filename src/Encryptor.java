import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;

/**
 * Created by voja on 2/21/17.
 */
public class Encryptor {

    public byte [] encrypt(byte[] key, byte[] plaintext, byte[] encIV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        aes.init(Cipher.ENCRYPT_MODE, k, new IvParameterSpec(encIV));
        return aes.doFinal(plaintext);
    }

    public byte [] decrypt(byte[] key, byte[] cyphertext, byte[] decIV) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException {

        Cipher aes = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec k = new SecretKeySpec(key,"AES");
        aes.init(Cipher.DECRYPT_MODE, k, new IvParameterSpec(decIV));
        return aes.doFinal(cyphertext);
    }
}
