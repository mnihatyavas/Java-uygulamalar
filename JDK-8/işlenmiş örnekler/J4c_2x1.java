// J4c_2x1.java: TextEditor (MetinD�zeltici) alt-�rne�i.

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// Tercihen gerekebilecek altalt-program: J4c_2x2.java=FileHandler
public class J4c_2x1 extends JPanel implements ActionListener {
    private JTextArea metinAlan� = null;
    private JButton a�Butonu = null;
    private JButton saklaButonu = null;
    private JButton yeniSaklaButonu = null;

    public J4c_2x1() {
         metinAlan�Yarat();
         butonlar�Yarat();
        setBackground (new Color (200,200,0));
    } // J4c_2x1() kurucusu sonu...

    private void metinAlan�Yarat() {
        metinAlan� = new JTextArea ("Edit�r Alan�");
        metinAlan�.setLineWrap (true);
        metinAlan�.setWrapStyleWord (true);
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        kayd�rmaPanosu.setVerticalScrollBarPolicy (
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kayd�rmaPanosu.setPreferredSize (new Dimension (450, 250));
        metinAlan�.setBackground (new Color (0,200,200));
        add (kayd�rmaPanosu);
    } // metinAlan�Yarat() metodu sonu...

    private void butonlar�Yarat() {
        a�Butonu = new JButton ("A�");
        a�Butonu.addActionListener (this);
        a�Butonu.setBackground (new Color (200,0,200));
        add (a�Butonu);

        saklaButonu = new JButton ("Sakla");
        saklaButonu.addActionListener (this);
        saklaButonu.setBackground (new Color (200,0,200));
        add (saklaButonu);

        yeniSaklaButonu = new JButton ("YeniSakla");
        yeniSaklaButonu.addActionListener (this);
        yeniSaklaButonu.setBackground (new Color (200,0,200));
        add (yeniSaklaButonu);
    } // butonlar�Yarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {// J4c_2x2=FileHandler/DosyaY�neticisi...
        if (olay.getSource() == a�Butonu) {} //metinAlan�.setText (J4c_2x2.a�());
        else if (olay.getSource() == saklaButonu) {} //J4c_2x2.sakla (metinAlan�.getText());
        else if (olay.getSource() == yeniSaklaButonu) {} //J4c_2x2.yeniSakla (metinAlan�.getText());
    } // actionPerformed(..) metodu sonu...
} // J4c_2x1 s�n�f� sonu...