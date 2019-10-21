// J5c_72.java: TextDemo (MetinG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

public class J5c_72 extends JPanel implements ActionListener {
    protected JTextField metinSat�r�;
    protected JTextArea metinAlan�;
    private final static String yeniSat�r = "\n";

    public J5c_72() {// Kurucu...
        super (new GridBagLayout());

        metinSat�r� = new JTextField (20);
        metinSat�r�.addActionListener (this); // Enter'lay�nca dinleyiciye duyarlayal�m...
        metinSat�r�.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu zemin renkleri...
        metinSat�r�.setForeground (Color.WHITE);


        metinAlan� = new JTextArea (5, 20);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        metinAlan�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k zemin renkleri...
        metinAlan�.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu yaz� renkleri...

        // Her iki komponenti de s�n�rlay�c�l�lar�yla birlikte i�erik paneline ekleyelim...
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;

        s�n�rlay�c�lar.fill = GridBagConstraints.HORIZONTAL;
        add (metinSat�r�, s�n�rlay�c�lar);

        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 1.0;
        add (kayd�rmaPanosu, s�n�rlay�c�lar);
    } // J5c_72() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String metin = metinSat�r�.getText();
        metinAlan�.append (metin + yeniSat�r);
        metinSat�r�.selectAll();
        // Metin alan�na yeni eklenen daima g�r�n�r k�l�ns�n...
        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Metin G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_72()); // Referanss�z nesne yarat�m� kurucuyu �a��r�r...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_72 s�n�f� sonu...