// J5c_15x3.java: ImagePreview (Resim�ng�r�nt�leme) alt-�rne�i.

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JComponent;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

import java.io.File;

public class J5c_15x3 extends JComponent implements PropertyChangeListener {
    ImageIcon ufak�kon = null;
    File dosya = null;

    public J5c_15x3 (JFileChooser dosyaSe�ici) {
        setPreferredSize (new Dimension (100, 50));
        dosyaSe�ici.addPropertyChangeListener (this);
    } // J5c_15x3(..) kurucusu sonu...

    public void resmiY�kle() {
        if (dosya == null) {ufak�kon = null; return;}

        ImageIcon ge�ici�kon = new ImageIcon (dosya.getPath());
        if (ge�ici�kon != null) {
            if (ge�ici�kon.getIconWidth() > 90) ufak�kon = new ImageIcon (ge�ici�kon.getImage().getScaledInstance (90, -1, Image.SCALE_DEFAULT));
            else ufak�kon = ge�ici�kon;
        } // if karar� sonu...
    } // resmiY�kle() metodu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        boolean g�ncelle = false;
        String �zellik = olay.getPropertyName();

        // E�er dizin de�i�tiyse herhangi bir resmi g�sterme...
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals (�zellik)) {
            dosya = null;
            g�ncelle = true;

        // E�er bir dosya se�ildiyse, tipini kontrol edelim...
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals (�zellik)) {
            dosya = (File) olay.getNewValue();
            g�ncelle = true;
        } // if-else karar� sonu...

        // G�ncelleme g�re uygunsa ikonu �ng�r�nt�leyelim...
        if (g�ncelle) {ufak�kon = null;
            if (isShowing()) {
                resmiY�kle();
                repaint();
        }} // i�-d�� if karar� sonu...
    } // propertyChange(..) metodu sonu...

    protected void paintComponent (Graphics g) {
        if (ufak�kon == null) resmiY�kle();

        if (ufak�kon != null) {
            int x = getWidth()/2 - ufak�kon.getIconWidth()/2;
            int y = getHeight()/2 - ufak�kon.getIconHeight()/2;

            if (y < 0) y = 0;
            if (x < 5) x = 5;

            ufak�kon.paintIcon (this, g, x, y);
        } // d�� if karar� sonu...
    } // paintComponent(..) metodu sonu...
} // J5c_15x3 s�n�f� sonu...