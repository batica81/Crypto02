import com.sun.crypto.provider.SunJCE;
import org.bouncycastle.jcajce.util.MessageDigestUtils;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import sun.security.jca.JCAUtil;
import sun.security.krb5.internal.crypto.Aes256;

import javax.swing.*;
import java.awt.event.*;

import sun.security.*;

import java.io.*;
import java.security.*;
import javax.crypto.*;



public class Dialog1 extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextArea textArea1;
    private JButton openFileButton;
    private JFormattedTextField formattedTextField1;

    public Dialog1() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        openFileButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { onOpen(); }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    public String byteToHexString(byte[] input) {
        String output = "";
        for (int i=0; i<input.length; ++i) {
            output += String.format("%02X", input[i]);
        }
        return output;
    }


    private void onOK() {
        // add your code here

        String sample1 = textArea1.getText();

        Security.addProvider(new BouncyCastleProvider());


        try {
            MessageDigest md = MessageDigest.getInstance("SHA256");

            String result = byteToHexString(md.digest((sample1).getBytes()));

            System.out.println("Evo SHA256 hasha: " + result);

            formattedTextField1.setText(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }



        //dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        System.out.println("You clicked CANCEL");
        dispose();
    }

    private void onOpen(){
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(contentPane);
        File file = fc.getSelectedFile();

        FileReader fr = null;
        try {
            fr = new FileReader(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        try {

            int i;
            while ((i=fr.read()) != -1){
                System.out.print((char) i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }





    }

    public static void main(String[] args) {
//        Dialog1 dialog = new Dialog1();
//        dialog.pack();
//        dialog.setVisible(true);
//        System.exit(0);
    }
}
