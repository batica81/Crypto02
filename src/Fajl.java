import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

/**
 * Created by voja on 2/21/17.
 */
public class Fajl {

    private byte[] originalFileContents;
    private byte[] encryptedFileContents;
    private byte[] plaintextFileContents;
    private String originalFileName;
    private String password;
    private byte[] hashedPassword;


    public Fajl(byte[] originalFileContents, String originalFileName) {
        this.originalFileContents = originalFileContents;
        this.originalFileName = originalFileName;
    }

    public byte[] getPlaintextFileContents() {
        return plaintextFileContents;
    }

    public void setPlaintextFileContents() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

        Encryptor en = new Encryptor();
        this.plaintextFileContents = en.decrypt(getHashedPassword(),getOriginalFileContents());
    }

    public byte[] getOriginalFileContents() {
        return originalFileContents;
    }

    public byte[] getEncryptedFileContents() {
        return encryptedFileContents;
    }

    public void setEncryptedFileContents() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {

        Encryptor en = new Encryptor();
        this.encryptedFileContents = en.encrypt(getHashedPassword(),getOriginalFileContents());
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setPassword(String password) {
        this.password = password;
        setHashedPassword();
    }

    public byte[] getHashedPassword() {
        return hashedPassword;
    }

    private void setHashedPassword() {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        this.hashedPassword =  (md.digest(password.getBytes()));
    }
}
