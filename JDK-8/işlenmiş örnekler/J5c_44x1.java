// J5c_44x1.java: Rule (Cetvel) alt-�rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;

import javax.swing.JComponent;

public class J5c_44x1 extends JComponent {
    public static final int �N� = Toolkit.getDefaultToolkit().getScreenResolution();
    public static final int YATAY = 0;
    public static final int D�KEY = 1;
    public static final int EBAT = 35;

    public int yatayDikey;
    public boolean metrikMi;
    private int art��;
    private int birimler;

    public J5c_44x1 (int yatDik, boolean eh) {
        yatayDikey = yatDik;
        metrikMi = eh;
        art��VeBirimleriKur();
    } // J5c_44x1(..) kurucusu sonu...

    public void metri�e�evrilsinMi (boolean metrikMi) {
        this.metrikMi = metrikMi;
        art��VeBirimleriKur();
        repaint();
    } // metri�e�evrilsinMi(..) metodu sonu...

    private void art��VeBirimleriKur() {
        if (metrikMi) {
            birimler = (int)((double)�N� / (double)2.54); // 1 in� = 2.54 sm...
            art�� = birimler;
        }else {
            birimler = �N�;
            art�� = birimler / 2;
        } // if-else karar� sonu...
    } // art��VeBirimleriKur() metodu sonu...

    public boolean metrikMi() {return this.metrikMi;}
    public int art���Al() {return art��;}
    public void tercihiBoyuKur (int tb) {setPreferredSize (new Dimension (EBAT, tb));}
    public void tercihiEniKur (int te) {setPreferredSize (new Dimension (te, EBAT));}

    protected void paintComponent (Graphics g) {
        Rectangle cetvel = g.getClipBounds();

        // Cetvel i�ini kirli turuncu'yla boyayal�m...
        g.setColor (new Color (230, 163, 4));
        g.fillRect (cetvel.x, cetvel.y, cetvel.width, cetvel.height);

        // Cetvel sm/in� yaz�s�n� daha k���k ebatta yazal�m...
        g.setFont (new Font ("SansSerif", Font.PLAIN, 10));
        g.setColor (Color.black);

        // Gereken birka� de�i�ken...
        int son = 0;
        int ilk = 0;
        int �entikUzunlu�u = 0;
        String metin = null;

        // �lk ve son �entik konumlar�n� hesaplamak i�in cetvelin yatay-enini/dikey-boyunu kullanal�m...
        if (yatayDikey == YATAY) {
            ilk = (cetvel.x / art��) * art��;
            son = (((cetvel.x + cetvel.width) / art��) + 1) * art��;
        }else {
            ilk = (cetvel.y / art��) * art��;
            son = (((cetvel.y + cetvel.height) / art��) + 1) * art��;
        } // if-else karar� sonu...

        // Cetveldeki ilk 0 yan�na sm/in� yaz�lacak...
        if (ilk == 0) {
            metin = Integer.toString (0) + (metrikMi ? " sm" : " in�");
            �entikUzunlu�u = 10;
            if (yatayDikey == YATAY) {
                g.drawLine (0, EBAT-1, 0, EBAT-�entikUzunlu�u-1); // Dik �entik...
                g.drawString (metin, 2, 21); // "0 sm"/"0 in�" metni...
            }else {
                g.drawLine (EBAT-1, 0, EBAT-�entikUzunlu�u-1, 0); // Yat�k �entik
                g.drawString (metin, 9, 10); // "0 sm"/"0 in�" metni...
            } // i� if-else karar� sonu...
            metin = null;
            ilk = art��;
        } // d��-if karar� sonu...

        // Dik/yat�k k�sa/uzun �entikler ve 1-2-3.. rakamlar...
        for (int i = ilk; i < son; i += art��) {
            if (i % birimler == 0)  {// �n�'in uzun ve k�sa �enti�i...
                �entikUzunlu�u = 10;
                metin = Integer.toString (i / birimler);
            }else {
                �entikUzunlu�u = 7;
                metin = null;
            } // if-else karar� sonu...

            if (�entikUzunlu�u != 0) {
                if (yatayDikey == YATAY) {// dik �entikler...
                    g.drawLine (i, EBAT-1, i, EBAT-�entikUzunlu�u-1);
                    if (metin != null) g.drawString (metin, i-3, 21);
                }else {// Yat�k �entikler...
                    g.drawLine (EBAT-1, i, EBAT-�entikUzunlu�u-1, i);
                    if (metin != null) g.drawString (metin, 9, i+3);
                } // i� if-else karar� sonu...
            } // d�� if karar� sonu...
        } // for d�ng�s� sonu...
    } // paintComponent(..) haz�r metodu sonu...
} // J5c_44x1 s�n�f� sonu...