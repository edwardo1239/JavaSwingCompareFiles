import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

class SwingFC implements ActionListener {

    JTextField jtfFirst;
    JTextField jtfSecond;

    JButton jbtnComp;

    JLabel jlabFirst, jlabSecond;
    JLabel jlabResult;

    SwingFC() {

        JFrame jfrm = new JFrame("Compare Files");
        jfrm.setLayout(new FlowLayout());
        jfrm.setSize(200, 190);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the text field
        jtfFirst = new JTextField(14);
        jtfSecond = new JTextField(14);
        jtfFirst.setActionCommand("fileA");
        jtfSecond.setActionCommand("fileB");

        // create compare button
        JButton jbtnComp = new JButton("Compare");
        jbtnComp.addActionListener(this);

        // create the labels
        jlabFirst = new JLabel("First file: ");
        jlabSecond = new JLabel("Second file: ");
        jlabResult = new JLabel("");

        jfrm.add(jtfFirst);
        jfrm.add(jtfSecond);
        jfrm.add(jbtnComp);
        jfrm.add(jlabFirst);
        jfrm.add(jlabSecond);
        jfrm.add(jlabResult);

        jfrm.setVisible(true);
    }

    // compare button is pressed
    public void actionPerformed(ActionEvent ae) {
        int i = 0, j = 0;

        if (jtfFirst.getText().equals("")) {
            jlabResult.setText("First file name missing.");
            return;
        }
        if (jtfSecond.getText().equals("")) {
            jlabResult.setText("Second file is missing");
            return;
        }

        // compare files
        try (FileInputStream f1 = new FileInputStream(jtfFirst.getText());
                FileInputStream f2 = new FileInputStream(jtfSecond.getText())) {
            do {
                i = f1.read();
                j = f2.read();
                if (i != j)
                    break;
            } while (i != -1 && j != -1);

            if (i != j)
                jlabResult.setText("Files are not the same");
            else
                jlabResult.setText("FIles copare equal.");
        } catch (IOException exc) {
            jlabResult.setText("File Error.");
        }
    }
    public static void main(String args[]) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SwingFC();
            }
        });
    }

}
