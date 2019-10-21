// J4c_2x1.java: TextEditor (MetinDüzeltici) alt-örneði.

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
    private JTextArea metinAlaný = null;
    private JButton açButonu = null;
    private JButton saklaButonu = null;
    private JButton yeniSaklaButonu = null;

    public J4c_2x1() {
         metinAlanýYarat();
         butonlarýYarat();
        setBackground (new Color (200,200,0));
    } // J4c_2x1() kurucusu sonu...

    private void metinAlanýYarat() {
        metinAlaný = new JTextArea ("Editör Alaný");
        metinAlaný.setLineWrap (true);
        metinAlaný.setWrapStyleWord (true);
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        kaydýrmaPanosu.setVerticalScrollBarPolicy (
            JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kaydýrmaPanosu.setPreferredSize (new Dimension (450, 250));
        metinAlaný.setBackground (new Color (0,200,200));
        add (kaydýrmaPanosu);
    } // metinAlanýYarat() metodu sonu...

    private void butonlarýYarat() {
        açButonu = new JButton ("Aç");
        açButonu.addActionListener (this);
        açButonu.setBackground (new Color (200,0,200));
        add (açButonu);

        saklaButonu = new JButton ("Sakla");
        saklaButonu.addActionListener (this);
        saklaButonu.setBackground (new Color (200,0,200));
        add (saklaButonu);

        yeniSaklaButonu = new JButton ("YeniSakla");
        yeniSaklaButonu.addActionListener (this);
        yeniSaklaButonu.setBackground (new Color (200,0,200));
        add (yeniSaklaButonu);
    } // butonlarýYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {// J4c_2x2=FileHandler/DosyaYöneticisi...
        if (olay.getSource() == açButonu) {} //metinAlaný.setText (J4c_2x2.aç());
        else if (olay.getSource() == saklaButonu) {} //J4c_2x2.sakla (metinAlaný.getText());
        else if (olay.getSource() == yeniSaklaButonu) {} //J4c_2x2.yeniSakla (metinAlaný.getText());
    } // actionPerformed(..) metodu sonu...
} // J4c_2x1 sýnýfý sonu...