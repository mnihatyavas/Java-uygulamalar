// J5c_54.java: SpinnerDemo4 (SayaçGösterisi4) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/* Gerekli java dosyasý:
 *   J5c_51x2.java=SpringUtilities.java
 */
public class J5c_54 extends JPanel {
    public J5c_54() {// Kurucu...
        super (new SpringLayout());

        String[] sayaçBaþlýklarý = {"Grinin (0->255) Tonlarý: "};
        int sayaçSayýsý = sayaçBaþlýklarý.length;

        JSpinner sayaç = baþlýðýVeSayacýEkle (this, sayaçBaþlýklarý[0], new GriModeli (170));
        sayaç.setEditor (new GriEditörü (sayaç));

        // Penceremize komponentleri serimleyelim...
        J5c_51x2.kesifIzgaraYap (
                this, // Penceremiz...
                sayaçSayýsý, 2, // Satýr ve sütun sayýsý...
                10, 10,// Ýlk komponentin penceremizdeki ilk konumu...
                6, 10); // sol-sað ve üst-alt tampon...
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_54() kurucusu sonu...

    static protected JSpinner baþlýðýVeSayacýEkle (Container kab, String baþlýk, SpinnerModel sayaçModeli) {
        JLabel etiket = new JLabel (baþlýk);
        kab.add (etiket);
        etiket.setForeground (Color.white);

        JSpinner sayaç = new JSpinner (sayaçModeli);
        etiket.setLabelFor (sayaç);
        kab.add (sayaç);

        return sayaç;
    } // baþlýðýVeSayacýEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sayaç Gösterisi 4");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5c_54();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...

    class GriModeli extends SpinnerNumberModel {
        // Argümanlar: (ilkDeðer, enküçükDeðer, enbüyükDeðer, artýþ)...
        public GriModeli (int deðer) {super (deðer, 0, 255, 5);} // Kurucu...

        public int getIntValue() {
            Integer deðerim = (Integer)getValue();
            return deðerim.intValue();
        } // getIntValue() hazýr metodu sonu...

        public Color getColor() {
            int renk0_255 = getIntValue();
            return new Color (renk0_255, renk0_255, renk0_255);
        } // getColor() hazýr metodu sonu...
    } // GriModeli sýnýfý sonu...

    class GriEditörü extends JLabel implements ChangeListener {
        public GriEditörü (JSpinner sayaç) {// Kurucu...
            setOpaque (true);

            // GriModeli alýp sayaç editör zeminini (gri) renkleþtirelim...
            GriModeli modelim = (GriModeli)(sayaç.getModel());
            setBackground (modelim.getColor());
            sayaç.addChangeListener (this); // Sayaç deðiþimini dinleyelim...

            // Alet ipucunu yeni renk deðeriyle güncelleyelim...
            aletÝpucuMetniniGüncelle(sayaç);

            //Set size info.
            Dimension size = new Dimension(60, 60);
            setMinimumSize(size);
            setPreferredSize(size);
        } // GriEditörü(..) kurucusu sonu...

        protected void aletÝpucuMetniniGüncelle (JSpinner sayaç) {
            String aletÝpucuMetni = sayaç.getToolTipText();
            if (aletÝpucuMetni != null) {
                // Sayaçta ipucu metni varsa kullanalum...
                if (!aletÝpucuMetni.equals (getToolTipText())) setToolTipText (aletÝpucuMetni);
            }else {// Kendi ipucu metnimizi tanýmlayalým...
                GriModeli modelim = (GriModeli)(sayaç.getModel());
                int rgb = modelim.getIntValue();
                setToolTipText ("(" + rgb + "," + rgb + "," + rgb + ")");
            } // else kararý sonu...
        } // aletÝpucuMetniniGüncelle(..) metodu sonu...

        public void stateChanged (ChangeEvent olay) {
            JSpinner sayacým = (JSpinner)(olay.getSource());
            GriModeli modelim = (GriModeli)(sayacým.getModel());
            setBackground (modelim.getColor());
            aletÝpucuMetniniGüncelle (sayacým);
        } // stateChanged(..) hazýr metodu sonu...
    } // GriEditörü sýnýfý sonu...
} // J5c_54 sýnýfý sonu...