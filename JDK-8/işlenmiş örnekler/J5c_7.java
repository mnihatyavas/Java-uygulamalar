// J5c_7.java: ComboBoxDemo (A��l�rKutu G�sterimi) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;

// Gereken 5 resim dosyas�: Ku�/Kedi/K�pek/Tav�an/Domuz.gif
public class J5c_7 extends JPanel implements ActionListener {
    JLabel resimEtiketi;

    public J5c_7() {// Kurucu...
        super (new BorderLayout());

        String[] hayvanlarStr = { "Ku�", "Kedi", "K�pek", "Tav�an", "Domuz" };

        // A��l�r kutuyu yarat�p, �ncelikli 3 endeksli tav�an� se�elim...
        JComboBox hayvanListesi = new JComboBox (hayvanlarStr);
        hayvanListesi.setSelectedIndex (3);
        hayvanListesi.addActionListener (this);

        // Resim etiketini kural�m...
        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        etiketiG�ncelle (hayvanlarStr[hayvanListesi.getSelectedIndex()]);
        resimEtiketi.setBorder (BorderFactory.createEmptyBorder (10,0,0,0));

        // B�y�kl�k olarak en b�y�k resmin ebat�n� alal�m...
        resimEtiketi.setPreferredSize (new Dimension(125, 129+10));

        add(hayvanListesi, BorderLayout.PAGE_START);
        add(resimEtiketi, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Zemin rengini rasgele yapal�m...
        setBackground (new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
    } // J5c_7() kurucusu sonu...

    // A��l�rKutu'yu dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        JComboBox a��l�rKutu = (JComboBox)olay.getSource();
        String hayvanAd� = (String)a��l�rKutu.getSelectedItem();
        etiketiG�ncelle (hayvanAd�);
    } // actionPerformed(..) metodu sonu...

    protected void etiketiG�ncelle (String ad) {
        ImageIcon ikon = resim�konuYarat ("resim/" + ad + ".gif");
        resimEtiketi.setIcon (ikon);
        resimEtiketi.setToolTipText ("Elle �izilmi� boyal� bir " + ad.toLowerCase() + " resmi.");
        if (ikon != null) resimEtiketi.setText (null);
        else resimEtiketi.setText ("Resim bulunamad�!");
    } // etiketiG�ncelle(..) metodu sonu...

    // Resim bulunursa onu, yoksa null d�nd�r...
    protected static ImageIcon resim�konuYarat (String yol) {
        java.net.URL resimYureli = J5c_7.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A��l�rKutu G�sterimi");
        �er�eve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new J5c_7();
        newContentPane.setOpaque(true); //content panes must be opaque
        �er�eve.setContentPane(newContentPane);

        //Display the window.
        �er�eve.pack();
        �er�eve.setVisible(true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_7 s�n�f� sonu...