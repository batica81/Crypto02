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
    private String originalFileName;
    private String encryptedFileName;
    private String password;
    private byte[] hashedPassword;

    public Fajl(byte[] originalFileContents, String originalFileName) {
        this.originalFileContents = originalFileContents;
        this.originalFileName = originalFileName;
    }



    public byte[] getOriginalFileContents() {
        return originalFileContents;
    }

    public void setOriginalFileContents(byte[] originalFileContents) {
        this.originalFileContents = originalFileContents;
    }

    public byte[] getEncryptedFileContents() {
        return encryptedFileContents;
    }



    public void setEncryptedFileContents() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {

        Encryptor en = new Encryptor();


        this.encryptedFileContents = en.encrypt(getHashedPassword(),getOriginalFileContents());
    }





    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getEncryptedFileName() {
        return encryptedFileName;
    }

    public void setEncryptedFileName(String encryptedFileName) {
        this.encryptedFileName = encryptedFileName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        setHashedPassword();
    }

    public byte[] getHashedPassword() {
        System.out.println(ByteToHex.byteToHexString(hashedPassword));
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
