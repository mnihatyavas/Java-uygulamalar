// J5c_44x1.java: Rule (Cetvel) alt-örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.JComponent;

public class J5c_44x1 extends JComponent {
    public static final int ÝNÇ = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int YATAY = 0;
    public static final int DÝKEY = 1;
    public static final int EBAT = 35;

    public int yatayDikey;
    public boolean metrikMi;
    private int artýþ;
    private int birimler;

    public J5c_44x1 (int yatDik, boolean eh) {
        yatayDikey = yatDik;
        metrikMi = eh;
        artýþVeBirimleriKur();
    } // J5c_44x1(..) kurucusu sonu...

    public void metriðeÇevrilsinMi (boolean metrikMi) {
        this.metrikMi = metrikMi;
        artýþVeBirimleriKur();
        repaint();
    } // metriðeÇevrilsinMi(..) metodu sonu...

    private void artýþVeBirimleriKur() {
        if (metrikMi) {
            birimler = (int)((double)ÝNÇ / (double)2.54); // 1 inç = 2.54 sm...
            artýþ = birimler;
        }else {
            birimler = ÝNÇ;
            artýþ = birimler / 2;
        } // if-else kararý sonu...
    } // artýþVeBirimleriKur() metodu sonu...

    public boolean metrikMi() {return this.metrikMi;}
    public int artýþýAl() {return artýþ;}
    public void tercihiBoyuKur (int tb) {setPreferredSize (new Dimension (EBAT, tb));}
    public void tercihiEniKur (int te) {setPreferredSize (new Dimension (te, EBAT));}

    protected void paintComponent (Graphics g) {
        Rectangle cetvel = g.getClipBounds();

        // Cetvel içini kirli turuncu'yla boyayalým...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (cetvel.x, cetvel.y, cetvel.width, cetvel.height);

        // Cetvel sm/inç yazýsýný daha küçük ebatta yazalým...
        g.setFont (new Font ("SansSerif", Font.PLAIN, 10));
        g.setColor (Color.black);

        // Gereken birkaç deðiþken...
        int son = 0;
        int ilk = 0;
        int çentikUzunluðu = 0;
        String metin = null;

        // Ýlk ve son çentik konumlarýný hesaplamak için cetvelin yatay-enini/dikey-boyunu kullanalým...
        if (yatayDikey == YATAY) {
            ilk = (cetvel.x / artýþ) * artýþ;
            son = (((cetvel.x + cetvel.width) / artýþ) + 1) * artýþ;
        }else {
            ilk = (cetvel.y / artýþ) * artýþ;
            son = (((cetvel.y + cetvel.height) / artýþ) + 1) * artýþ;
        } // if-else kararý sonu...

        // Cetveldeki ilk 0 yanýna sm/inç yazýlacak...
        if (ilk == 0) {
            metin = Integer.toString (0) + (metrikMi ? " sm" : " inç");
            çentikUzunluðu = 10;
            if (yatayDikey == YATAY) {
                g.drawLine (0, EBAT-1, 0, EBAT-çentikUzunluðu-1); // Dik çentik...
                g.drawString (metin, 2, 21); // "0 sm"/"0 inç" metni...
            }else {
                g.drawLine (EBAT-1, 0, EBAT-çentikUzunluðu-1, 0); // Yatýk çentik
                g.drawString (metin, 9, 10); // "0 sm"/"0 inç" metni...
            } // iç if-else kararý sonu...
            metin = null;
            ilk = artýþ;
        } // dýþ-if kararý sonu...

        // Dik/yatýk kýsa/uzun çentikler ve 1-2-3.. rakamlar...
        for (int i = ilk; i < son; i += artýþ) {
            if (i % birimler == 0)  {// Ýnç'in uzun ve kýsa çentiði...
                çentikUzunluðu = 10;
                metin = Integer.toString (i / birimler);
            }else {
                çentikUzunluðu = 7;
                metin = null;
            } // if-else kararý sonu...

            if (çentikUzunluðu != 0) {
                if (yatayDikey == YATAY) {// dik çentikler...
                    g.drawLine (i, EBAT-1, i, EBAT-çentikUzunluðu-1);
                    if (metin != null) g.drawString (metin, i-3, 21);
                }else {// Yatýk çentikler...
                    g.drawLine (EBAT-1, i, EBAT-çentikUzunluðu-1, i);
                    if (metin != null) g.drawString (metin, 9, i+3);
                } // iç if-else kararý sonu...
            } // dýþ if kararý sonu...
        } // for döngüsü sonu...
    } // paintComponent(..) hazýr metodu sonu...
} // J5c_44x1 sýnýfý sonu...