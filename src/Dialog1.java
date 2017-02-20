

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.security.*;


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

        String sample1 = textArea1.getText();

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

        System.out.println("You clicked CANCEL");
        dispose();
    }

    private void onOpen(){
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(contentPane);
        File file = fc.getSelectedFile();

        try {

            byte[] fileData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(fileData);

            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            String result = byteToHexString(md.digest(fileData));

            formattedTextField1.setText(result);

            System.out.println("Evo SHA256 hasha fajla: " + result);

            dis.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {

    }
}
