/**
 * Created by voja on 2/21/17.
 */
public class ByteToHex {

    public static String byteToHexString(byte[] input) {
        String output = "";
        for (int i=0; i<input.length; ++i) {
            output += String.format("%02X", input[i]);
        }
        return output;
    }
}
