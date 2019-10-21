// J5c_7.java: ComboBoxDemo (AçýlýrKutu Gösterimi) örneði.

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

// Gereken 5 resim dosyasý: Kuþ/Kedi/Köpek/Tavþan/Domuz.gif
public class J5c_7 extends JPanel implements ActionListener {
    JLabel resimEtiketi;

    public J5c_7() {// Kurucu...
        super (new BorderLayout());

        String[] hayvanlarStr = { "Kuþ", "Kedi", "Köpek", "Tavþan", "Domuz" };

        // Açýlýr kutuyu yaratýp, öncelikli 3 endeksli tavþaný seçelim...
        JComboBox hayvanListesi = new JComboBox (hayvanlarStr);
        hayvanListesi.setSelectedIndex (3);
        hayvanListesi.addActionListener (this);

        // Resim etiketini kuralým...
        resimEtiketi = new JLabel();
        resimEtiketi.setFont (resimEtiketi.getFont().deriveFont (Font.ITALIC));
        resimEtiketi.setHorizontalAlignment (JLabel.CENTER);
        etiketiGüncelle (hayvanlarStr[hayvanListesi.getSelectedIndex()]);
        resimEtiketi.setBorder (BorderFactory.createEmptyBorder (10,0,0,0));

        // Büyüklük olarak en büyük resmin ebatýný alalým...
        resimEtiketi.setPreferredSize (new Dimension(125, 129+10));

        add(hayvanListesi, BorderLayout.PAGE_START);
        add(resimEtiketi, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        // Zemin rengini rasgele yapalým...
        setBackground (new Color ((int)(Math.random()*255), (int)(Math.random()*255), (int)(Math.random()*255)));
    } // J5c_7() kurucusu sonu...

    // AçýlýrKutu'yu dinleyelim...
    public void actionPerformed (ActionEvent olay) {
        JComboBox açýlýrKutu = (JComboBox)olay.getSource();
        String hayvanAdý = (String)açýlýrKutu.getSelectedItem();
        etiketiGüncelle (hayvanAdý);
    } // actionPerformed(..) metodu sonu...

    protected void etiketiGüncelle (String ad) {
        ImageIcon ikon = resimÝkonuYarat ("resim/" + ad + ".gif");
        resimEtiketi.setIcon (ikon);
        resimEtiketi.setToolTipText ("Elle çizilmiþ boyalý bir " + ad.toLowerCase() + " resmi.");
        if (ikon != null) resimEtiketi.setText (null);
        else resimEtiketi.setText ("Resim bulunamadý!");
    } // etiketiGüncelle(..) metodu sonu...

    // Resim bulunursa onu, yoksa null döndür...
    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_7.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("AçýlýrKutu Gösterimi");
        çerçeve.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        JComponent newContentPane = new J5c_7();
        newContentPane.setOpaque(true); //content panes must be opaque
        çerçeve.setContentPane(newContentPane);

        //Display the window.
        çerçeve.pack();
        çerçeve.setVisible(true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_7 sýnýfý sonu...