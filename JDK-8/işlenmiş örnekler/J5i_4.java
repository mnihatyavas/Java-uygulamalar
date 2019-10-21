// J5i_4.java: DocumentEventDemo (D�k�manOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.text.Document;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

public class J5i_4 extends JPanel implements ActionListener {
    JTextField metinSat�r�;
    JTextArea metinAlan�;
    JTextArea g�steriAlan�;

    public J5i_4() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout �zgara�antaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        JButton d��me = new JButton ("Temizle");
        d��me.addActionListener (this); // Dinleyiciye duyarlayal�m...

        metinSat�r� = new JTextField (20);
        metinSat�r�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinSat�r�.addActionListener (new MetinDinleyicim()); // Kendi metin dinleyicime duyarlayal�m...
        metinSat�r�.getDocument().addDocumentListener (new D�k�manDinleyicim()); // Kendi d�k�man dinleyicime duyarlayal�m...
        metinSat�r�.getDocument().putProperty ("ad", "Metin Sat�r�");

        metinAlan� = new JTextArea ("Metin sat�r� ve metin alan� aras�nda kes-kopya-yap��t�r yap�labilir.");
        metinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        metinAlan�.getDocument().addDocumentListener (new D�k�manDinleyicim()); // Kendi d�k�man dinleyicime duyarlayal�m...
        metinAlan�.getDocument().putProperty ("ad", "Metin Alan�");

        JScrollPane kayd�rma1 = new JScrollPane (metinAlan�);
        kayd�rma1.setPreferredSize (new Dimension (200, 75));

        g�steriAlan� = new JTextArea();
        g�steriAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        g�steriAlan�.setForeground (Color.WHITE);
        g�steriAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�rma2 = new JScrollPane (g�steriAlan�);
        kayd�rma2.setPreferredSize (new Dimension (200, 75));

        s�n�rlay�c�lar.gridx = 0;
        s�n�rlay�c�lar.gridy = 0;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.fill = GridBagConstraints.HORIZONTAL;
        �zgara�antaSerilim.setConstraints (metinSat�r�, s�n�rlay�c�lar);
        add (metinSat�r�);

        s�n�rlay�c�lar.gridx = 0;
        s�n�rlay�c�lar.gridy = 1;
        s�n�rlay�c�lar.weightx = 0.0;
        s�n�rlay�c�lar.gridheight = 2;
        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        �zgara�antaSerilim.setConstraints (kayd�rma1, s�n�rlay�c�lar);
        add (kayd�rma1);

        s�n�rlay�c�lar.gridx = 1;
        s�n�rlay�c�lar.gridy = 0;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 1.0;
        �zgara�antaSerilim.setConstraints (kayd�rma2, s�n�rlay�c�lar);
        add (kayd�rma2);

        s�n�rlay�c�lar.gridx = 1;
        s�n�rlay�c�lar.gridy = 2;
        s�n�rlay�c�lar.weightx = 0.0;
        s�n�rlay�c�lar.gridheight = 1;
        s�n�rlay�c�lar.weighty = 0.0;
        s�n�rlay�c�lar.fill = GridBagConstraints.HORIZONTAL;
        �zgara�antaSerilim.setConstraints (d��me, s�n�rlay�c�lar);
        add (d��me);

        setPreferredSize (new Dimension (450, 250));
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_4() kurucusu sonu...

    // Metin sat�r� ve alan� de�i�ikliklerini y�netir...
    class MetinDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            int se�ili�lk = metinAlan�.getSelectionStart();
            int se�iliSon = metinAlan�.getSelectionEnd();

            metinAlan�.replaceRange (metinSat�r�.getText(), se�ili�lk, se�iliSon);
            metinSat�r�.selectAll();
        } // actionPerformed(..) haz�r metodu sonu...
    } // MetinDinleyicim s�n�f� sonu...

    // Metin sat�r� ve alan� de�i�ikliklerini kayda ge�er...
    class D�k�manDinleyicim implements DocumentListener {
        final String yeniSat�r = "\n";

        public void insertUpdate (DocumentEvent olay) {kay�t�G�ncelle (olay, "'na sokuldu");}
        public void removeUpdate (DocumentEvent olay) {kay�t�G�ncelle (olay, "'ndan silindi");}
        public void changedUpdate (DocumentEvent olay) {} // Ald�rma...

        public void kay�t�G�ncelle (DocumentEvent olay, String hareket) {
            Document d�k�man = (Document)olay.getDocument();
            int de�i�enUzunluk = olay.getLength();
            g�steriAlan�.append (de�i�enUzunluk + " karakter" + ((de�i�enUzunluk == 1) ? " " : "ler ")
                    + d�k�man.getProperty ("ad") + hareket + "."+ yeniSat�r
                    + "---mevcut Metin uzunlu�u = " + d�k�man.getLength() + yeniSat�r);
            g�steriAlan�.setCaretPosition (g�steriAlan�.getDocument().getLength());
        } // kay�t�G�ncelle(..) metodu sonu...
    } // D�k�manDinleyicim s�n�f� sonu...

    // Temizle d��mesi y�netimi...
    public void actionPerformed (ActionEvent olay) {
        g�steriAlan�.setText("");
        metinSat�r�.requestFocus();
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("D�k�man Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_4(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_4 s�n�f� sonu...