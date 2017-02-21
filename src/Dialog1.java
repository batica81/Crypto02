
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    private JPasswordField lozinkaPasswordField;
    private JButton enkriptujButton;

    public Dialog1() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    onOK();
                } catch (InvalidKeyException e1) {
                    e1.printStackTrace();
                } catch (BadPaddingException e1) {
                    e1.printStackTrace();
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (IllegalBlockSizeException e1) {
                    e1.printStackTrace();
                } catch (NoSuchPaddingException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
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

        enkriptujButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    OnenkriptujButton();
                } catch (InvalidKeyException e1) {
                    e1.printStackTrace();
                } catch (BadPaddingException e1) {
                    e1.printStackTrace();
                } catch (NoSuchAlgorithmException e1) {
                    e1.printStackTrace();
                } catch (IllegalBlockSizeException e1) {
                    e1.printStackTrace();
                } catch (NoSuchPaddingException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
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

    private Fajl radniFajl = null;

    private void onCancel() {

        System.out.println("You clicked CANCEL");
        dispose();
    }

    private Fajl fileLoader(){
        try {

            JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(contentPane);
            File file = fc.getSelectedFile();
            String filename = file.getName();

            byte[] fileData = new byte[(int) file.length()];
            DataInputStream dis = new DataInputStream(new FileInputStream(file));
            dis.readFully(fileData);
            Fajl tempFajl = new Fajl(fileData, filename);
            dis.close();
            return tempFajl;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void onOpen(){
        radniFajl = fileLoader();
    }

    private void OnenkriptujButton() throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, IOException {

        radniFajl.setPassword(lozinkaPasswordField.getText());
        radniFajl.setEncryptedFileContents();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(radniFajl.getOriginalFileName() + ".enc");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fos.write(radniFajl.getEncryptedFileContents());
        fos.close();
    }

    private void onOK() throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException, IOException {
        // DEKRIPTUJ

        radniFajl.setPassword(lozinkaPasswordField.getText());
        radniFajl.setPlaintextFileContents();

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(radniFajl.getOriginalFileName() + ".dec");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        fos.write(radniFajl.getPlaintextFileContents());
        fos.close();
    }
}
