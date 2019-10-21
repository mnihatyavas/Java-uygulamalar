// J5c_54.java: SpinnerDemo4 (Saya�G�sterisi4) �rne�i.

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

/* Gerekli java dosyas�:
 *   J5c_51x2.java=SpringUtilities.java
 */
public class J5c_54 extends JPanel {
    public J5c_54() {// Kurucu...
        super (new SpringLayout());

        String[] saya�Ba�l�klar� = {"Grinin (0->255) Tonlar�: "};
        int saya�Say�s� = saya�Ba�l�klar�.length;

        JSpinner saya� = ba�l���VeSayac�Ekle (this, saya�Ba�l�klar�[0], new GriModeli (170));
        saya�.setEditor (new GriEdit�r� (saya�));

        // Penceremize komponentleri serimleyelim...
        J5c_51x2.kesifIzgaraYap (
                this, // Penceremiz...
                saya�Say�s�, 2, // Sat�r ve s�tun say�s�...
                10, 10,// �lk komponentin penceremizdeki ilk konumu...
                6, 10); // sol-sa� ve �st-alt tampon...
        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_54() kurucusu sonu...

    static protected JSpinner ba�l���VeSayac�Ekle (Container kab, String ba�l�k, SpinnerModel saya�Modeli) {
        JLabel etiket = new JLabel (ba�l�k);
        kab.add (etiket);
        etiket.setForeground (Color.white);

        JSpinner saya� = new JSpinner (saya�Modeli);
        etiket.setLabelFor (saya�);
        kab.add (saya�);

        return saya�;
    } // ba�l���VeSayac�Ekle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Saya� G�sterisi 4");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5c_54();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...

    class GriModeli extends SpinnerNumberModel {
        // Arg�manlar: (ilkDe�er, enk���kDe�er, enb�y�kDe�er, art��)...
        public GriModeli (int de�er) {super (de�er, 0, 255, 5);} // Kurucu...

        public int getIntValue() {
            Integer de�erim = (Integer)getValue();
            return de�erim.intValue();
        } // getIntValue() haz�r metodu sonu...

        public Color getColor() {
            int renk0_255 = getIntValue();
            return new Color (renk0_255, renk0_255, renk0_255);
        } // getColor() haz�r metodu sonu...
    } // GriModeli s�n�f� sonu...

    class GriEdit�r� extends JLabel implements ChangeListener {
        public GriEdit�r� (JSpinner saya�) {// Kurucu...
            setOpaque (true);

            // GriModeli al�p saya� edit�r zeminini (gri) renkle�tirelim...
            GriModeli modelim = (GriModeli)(saya�.getModel());
            setBackground (modelim.getColor());
            saya�.addChangeListener (this); // Saya� de�i�imini dinleyelim...

            // Alet ipucunu yeni renk de�eriyle g�ncelleyelim...
            alet�pucuMetniniG�ncelle(saya�);

            //Set size info.
            Dimension size = new Dimension(60, 60);
            setMinimumSize(size);
            setPreferredSize(size);
        } // GriEdit�r�(..) kurucusu sonu...

        protected void alet�pucuMetniniG�ncelle (JSpinner saya�) {
            String alet�pucuMetni = saya�.getToolTipText();
            if (alet�pucuMetni != null) {
                // Saya�ta ipucu metni varsa kullanalum...
                if (!alet�pucuMetni.equals (getToolTipText())) setToolTipText (alet�pucuMetni);
            }else {// Kendi ipucu metnimizi tan�mlayal�m...
                GriModeli modelim = (GriModeli)(saya�.getModel());
                int rgb = modelim.getIntValue();
                setToolTipText ("(" + rgb + "," + rgb + "," + rgb + ")");
            } // else karar� sonu...
        } // alet�pucuMetniniG�ncelle(..) metodu sonu...

        public void stateChanged (ChangeEvent olay) {
            JSpinner sayac�m = (JSpinner)(olay.getSource());
            GriModeli modelim = (GriModeli)(sayac�m.getModel());
            setBackground (modelim.getColor());
            alet�pucuMetniniG�ncelle (sayac�m);
        } // stateChanged(..) haz�r metodu sonu...
    } // GriEdit�r� s�n�f� sonu...
} // J5c_54 s�n�f� sonu...