// J5c_15x3.java: ImagePreview (ResimÖngörüntüleme) alt-örneði.

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
    ImageIcon ufakÝkon = null;
    File dosya = null;

    public J5c_15x3 (JFileChooser dosyaSeçici) {
        setPreferredSize (new Dimension (100, 50));
        dosyaSeçici.addPropertyChangeListener (this);
    } // J5c_15x3(..) kurucusu sonu...

    public void resmiYükle() {
        if (dosya == null) {ufakÝkon = null; return;}

        ImageIcon geçiciÝkon = new ImageIcon (dosya.getPath());
        if (geçiciÝkon != null) {
            if (geçiciÝkon.getIconWidth() > 90) ufakÝkon = new ImageIcon (geçiciÝkon.getImage().getScaledInstance (90, -1, Image.SCALE_DEFAULT));
            else ufakÝkon = geçiciÝkon;
        } // if kararý sonu...
    } // resmiYükle() metodu sonu...

    public void propertyChange (PropertyChangeEvent olay) {
        boolean güncelle = false;
        String özellik = olay.getPropertyName();

        // Eðer dizin deðiþtiyse herhangi bir resmi gösterme...
        if (JFileChooser.DIRECTORY_CHANGED_PROPERTY.equals (özellik)) {
            dosya = null;
            güncelle = true;

        // Eðer bir dosya seçildiyse, tipini kontrol edelim...
        } else if (JFileChooser.SELECTED_FILE_CHANGED_PROPERTY.equals (özellik)) {
            dosya = (File) olay.getNewValue();
            güncelle = true;
        } // if-else kararý sonu...

        // Güncelleme göre uygunsa ikonu öngörüntüleyelim...
        if (güncelle) {ufakÝkon = null;
            if (isShowing()) {
                resmiYükle();
                repaint();
        }} // iç-dýþ if kararý sonu...
    } // propertyChange(..) metodu sonu...

    protected void paintComponent (Graphics g) {
        if (ufakÝkon == null) resmiYükle();

        if (ufakÝkon != null) {
            int x = getWidth()/2 - ufakÝkon.getIconWidth()/2;
            int y = getHeight()/2 - ufakÝkon.getIconHeight()/2;

            if (y < 0) y = 0;
            if (x < 5) x = 5;

            ufakÝkon.paintIcon (this, g, x, y);
        } // dýþ if kararý sonu...
    } // paintComponent(..) metodu sonu...
} // J5c_15x3 sýnýfý sonu...