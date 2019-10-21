// J5c_15.java: FileChooserDemo2 (DosyaSe�iciG�sterimi2) �rne�i.

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.io.File;

/* Gerekli dosyalar:
 *   ImageFileView.java/J5c_15x2.java
 *   ImageFilter.java/J5c_15x1.java
 *   ImagePreview.java/J5c_15x3.java
 *   Utils.java/J5c_15x4.java
 *   resim/jpgIcon.gif (ImageFileView.java kullan�yor.)
 *   resim/gifIcon.gif (ImageFileView.java kullan�yor.)
 *   resim/tiffIcon.gif (ImageFileView.java kullan�yor.)
 *   resim/pngIcon.png (ImageFileView.java kullan�yor.)
 */
public class J5c_15 extends JPanel implements ActionListener {
    static private String yeniSat�r = "\n";
    private JTextArea metinAlan�;
    private JFileChooser dosyaSe�ici;

    public J5c_15() {// Kurucu...
        super (new BorderLayout());

        metinAlan� = new JTextArea (5,20);
        metinAlan�.setMargin (new Insets (5,5,5,5));
        metinAlan�.setEditable (false);
        metinAlan�.setBackground (Color.CYAN);
        metinAlan�.setForeground (new Color (0.7865f,0.0f,0.5f));
        JScrollPane maKayd�rmaPanosu = new JScrollPane (metinAlan�);

        JButton ekleD��mesi = new JButton ("�li�tir...");
        ekleD��mesi.addActionListener (this);

        add (ekleD��mesi, BorderLayout.PAGE_START);
        add (maKayd�rmaPanosu, BorderLayout.CENTER);
    } // J5c_15() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        // Dosya se�iciyi kurup filitre, ikon ve �ng�r�nt� dosyalar�n� ekleyelim...
        if (dosyaSe�ici == null) {
            dosyaSe�ici = new JFileChooser();

            // Geleneksel bir (resim) dosya filitresini ekleyip varsay�l�y� etkisizleyelim...
            dosyaSe�ici.addChoosableFileFilter (new J5c_15x1());
            dosyaSe�ici.setAcceptAllFileFilterUsed (false);

            // Dosya tipleri (.jpg/.jpeg/.gif/.tif/.tiff/.png) ikonlar�n� ekleyelim...
            dosyaSe�ici.setFileView (new J5c_15x2());

            // T�klanan filitreli resim dosyas�n�n�n �ng�r�nt� panosunu ekleyelim...
            dosyaSe�ici.setAccessory (new J5c_15x3 (dosyaSe�ici));
        } // if karar� sonu...

        // Dosya se�ici diyalo�unu g�sterelim...
        int d�nenDe�er = dosyaSe�ici.showDialog (J5c_15.this, "�li�tir");

        // Se�ilen �ng�r�nt�l� resim dosyas�n� ekle veya vazge�...
        if (d�nenDe�er == JFileChooser.APPROVE_OPTION) {
            File dosya = dosyaSe�ici.getSelectedFile();
            metinAlan�.append ("[" + dosya.getName() + "] resim dosyas�n� ili�tiriyor (dese de bekleme)." + yeniSat�r);
        }else metinAlan�.append ("Kullan�c� ili�tirme i�leminden vazge�ti." + yeniSat�r);

        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());

        // Dosya se�icideki se�iliyi bir sonraki se�im i�in null'la (bo�alt)...
        dosyaSe�ici.setSelectedFile (null);
    } // actionPerformed(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame("Dosya Se�ici G�sterimi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_15());
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_15 s�n�f� sonu...