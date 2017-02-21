
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.security.Security;

public class Main {

    public static void main(String[] args) {


        Security.addProvider(new BouncyCastleProvider());
        System.out.println("Initializing...");

        Dialog1 dialog = new Dialog1();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);

    }
}
