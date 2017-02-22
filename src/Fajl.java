import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Arrays;

/**
 * Created by voja on 2/21/17.
 */
public class Fajl {

    private byte[] originalFileContents;
    private byte[] encryptedFileContents;
    private byte[] plaintextFileContents;
    private byte[] encIV;
    private String originalFileName;
    private String password;
    private byte[] hashedPassword;


    public Fajl(byte[] originalFileContents, String originalFileName) {
        this.originalFileContents = originalFileContents;
        this.originalFileName = originalFileName;
        setEncIV();
    }

    public byte[] getEncIV() {
        return encIV;
    }

    public void setEncIV() {
        SecureRandom sr = new SecureRandom();
        byte[] values = new byte[16];
        sr.nextBytes(values);
        this.encIV = values;
    }

    public byte[] getPlaintextFileContents() {
        return plaintextFileContents;
    }

    public void setPlaintextFileContents() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException {
        byte [] tmpfile = getOriginalFileContents();
        byte[] DecIV = Arrays.copyOfRange(tmpfile, 0, 16);
        byte[] cyphertext = Arrays.copyOfRange(tmpfile, 16, tmpfile.length);
        Encryptor en = new Encryptor();
        this.plaintextFileContents = en.decrypt(getHashedPassword(),cyphertext,DecIV);
    }

    public byte[] getOriginalFileContents() {
        return originalFileContents;
    }

    public byte[] getEncryptedFileContents() {
        return encryptedFileContents;
    }

    public void setEncryptedFileContents() throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, IOException {

        Encryptor en = new Encryptor();
        byte[] tmpfile = en.encrypt(getHashedPassword(),getOriginalFileContents(),getEncIV());
        byte[] tmpivfile = getEncIV();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(tmpivfile);
        outputStream.write(tmpfile);
        this.encryptedFileContents = outputStream.toByteArray();
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
