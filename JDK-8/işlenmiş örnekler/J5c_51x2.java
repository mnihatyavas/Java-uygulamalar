// J5c_51x2.java: SpringUtilities (SpringserilimMetodlar�) alt-�rne�i.

import java.awt.Container;
import java.awt.Component;

import javax.swing.Spring;
import javax.swing.SpringLayout;

// Bu program SpringLayout'la form veya �zgara t�r� serilim yapar...
public class J5c_51x2 {
    // Komponentin enk���k, tercihi ve enb�y�k ebatlar�n� dos ekran�na yazar...
    public static void ebatlar�Yaz (Component komponent) {
        System.out.println ("Enk���k ebat = " + komponent.getMinimumSize());
        System.out.println ("Tercihi ebat = " + komponent.getPreferredSize());
        System.out.println ("Enb�y�k ebat = " + komponent.getMaximumSize());
    } // ebatlar�Yaz(..) metodu sonu...

    /* Alttaki 2 metotda ebeveyn, komponentlerinin herbiri �zgara (sat�r, s�tun) formunda,
     * ba�lang�� konumdan itibaren enb�y�k tercihi en ve boyda serimlenecek
     * �ekilde yeterli b�y�kl�kte olu�turulur.
     */
    public static void �zgaraYap (Container ebeveyn, int sat�rSay�s�, int s�tunSay�s�, int ilkX, int ilkY, int xTampon, int yTampon) {
        SpringLayout springSerilim;
        try {springSerilim = (SpringLayout)ebeveyn.getLayout();
        }catch (ClassCastException ist) {System.err.println ("Bu metodun ilk arg�man� SpringLayout kullanmal�d�r!"); return;}

        Spring xTamponSpring = Spring.constant (xTampon);
        Spring yTamponSpring = Spring.constant (yTampon);
        Spring ilkXSpring = Spring.constant (ilkX);
        Spring ilkYSpring = Spring.constant (ilkY);
        int enb = sat�rSay�s� * s�tunSay�s�; // enb(�y�k): max(imum)...

        // Herbir h�creye ayn� ebat tahsisi i�in ebeveyn komponent springlerinin
        // enb�y�k en/boy'lar�n� hesaplayal�m...
        Spring enbGeni�liktekiSpring = springSerilim.getConstraints (ebeveyn.getComponent (0)).getWidth();
        Spring enbY�ksekliktekiSpring = springSerilim.getConstraints (ebeveyn.getComponent (0)).getWidth();
        for (int i = 1; i < enb; i++) {
            SpringLayout.Constraints kar��la�t�r = springSerilim.getConstraints (ebeveyn.getComponent (i));
            enbGeni�liktekiSpring = Spring.max (enbGeni�liktekiSpring, kar��la�t�r.getWidth());
            enbY�ksekliktekiSpring = Spring.max (enbY�ksekliktekiSpring, kar��la�t�r.getHeight());
        } // for d�ng�s� sonu...

        // Elde edilen enb�y�k en/boy Spring de�eri e�de�er ebat i�in t�m�ne uygulanacakt�r...
        for (int i = 0; i < enb; i++) {
            SpringLayout.Constraints kar��la�t�r = springSerilim.getConstraints (ebeveyn.getComponent (i));
            kar��la�t�r.setWidth (enbGeni�liktekiSpring);
            kar��la�t�r.setHeight (enbY�ksekliktekiSpring);
        } // for d�ng�s� sonu...

        // Bir �zgara formunda serilim i�in t�m h�crelerin x/y s�n�rlamalar� ayarlan�r...
        SpringLayout.Constraints sonK�staslar = null;
        SpringLayout.Constraints sonSat�rK�staslar� = null;
        for (int i = 0; i < enb; i++) {
            SpringLayout.Constraints kar��la�t�r = springSerilim.getConstraints (ebeveyn.getComponent(i));
            if (i % s�tunSay�s� == 0) {// Yeni bir sat�r ba�lang�c�...
                sonSat�rK�staslar� = sonK�staslar;
                kar��la�t�r.setX (ilkXSpring);
            }else {// x konumu bir �nceki h�cre komponentine g�redir...
                kar��la�t�r.setX (Spring.sum (sonK�staslar.getConstraint (SpringLayout.EAST), xTamponSpring));
            } // if-else karar� sonu...

            if (i / s�tunSay�s� == 0) {// �lk sat�r..
                kar��la�t�r.setY (ilkYSpring);
            }else { // y konumu bir �nceki sat�ra g�redir...
                kar��la�t�r.setY (Spring.sum (sonSat�rK�staslar�.getConstraint (SpringLayout.SOUTH), yTamponSpring));
            } // i-else karar� sonu...
            sonK�staslar = kar��la�t�r;
        } // for d�ng�s� sonu...

        // Ebeveyn pencerenin ebat�n� belirleyelim...
        SpringLayout.Constraints ebeK�staslar� = springSerilim.getConstraints (ebeveyn);
        ebeK�staslar�.setConstraint (SpringLayout.SOUTH, Spring.sum (Spring.constant (yTampon), sonK�staslar.getConstraint (SpringLayout.SOUTH)));
        ebeK�staslar�.setConstraint (SpringLayout.EAST, Spring.sum (Spring.constant (xTampon), sonK�staslar.getConstraint (SpringLayout.EAST)));
    }  // �zgaraYap(..) metodu sonu...

    public static void kesifIzgaraYap (Container ebeveyn, int sat�rSay�s�, int s�tunSay�s�, int ilkX, int ilkY, int xTampon, int yTampon) {
        SpringLayout springSerilim;
        try {springSerilim = (SpringLayout)ebeveyn.getLayout();
        }catch (ClassCastException ist) {System.err.println ("Bu metodun ilk arg�man� SpringLayout kullanmal�d�r!"); return;}

        // Herbir s�tundaki h�creleri ayn� geni�likle hizalayal�m...
        Spring x = Spring.constant (ilkX);
        for (int c = 0; c < s�tunSay�s�; c++) {
            Spring en = Spring.constant (0);
            for (int r = 0; r < sat�rSay�s�; r++) en = Spring.max (en, h�creK�staslar�n�Al (r, c, ebeveyn, s�tunSay�s�).getWidth());
            for (int r = 0; r < sat�rSay�s�; r++) {
                SpringLayout.Constraints k�staslar = h�creK�staslar�n�Al (r, c, ebeveyn, s�tunSay�s�);
                k�staslar.setX (x);
                k�staslar.setWidth (en);
            } // i�-for d�ng�s� sonu...
            x = Spring.sum (x, Spring.sum (en, Spring.constant (xTampon)));
        } // d��-for d�ng�s� sonu...

        // Herbir sat�rdaki h�creleri ayn� y�kseklikte hizalayal�m...
        Spring y = Spring.constant (ilkY);
        for (int r = 0; r < sat�rSay�s�; r++) {
            Spring boy = Spring.constant (0);
            for (int c = 0; c < s�tunSay�s�; c++) boy = Spring.max (boy, h�creK�staslar�n�Al (r, c, ebeveyn, s�tunSay�s�).getHeight());
            for (int c = 0; c < s�tunSay�s�; c++) {
                SpringLayout.Constraints k�staslar = h�creK�staslar�n�Al (r, c, ebeveyn, s�tunSay�s�);
                k�staslar.setY (y);
                k�staslar.setHeight (boy);
            } // i�-for d�ng�s� sonu...
            y = Spring.sum (y, Spring.sum (boy, Spring.constant (yTampon)));
        } // d��-for d�ng�s� sonu...

        // Ebeveyn pencere ebat�n� belirleyelim...
        SpringLayout.Constraints ebeK�staslar� = springSerilim.getConstraints (ebeveyn);
        ebeK�staslar�.setConstraint (SpringLayout.SOUTH, y);
        ebeK�staslar�.setConstraint (SpringLayout.EAST, x);
    } // kesifIzgaraYap(..) metodu sonu...

    private static SpringLayout.Constraints h�creK�staslar�n�Al (int r, int c, Container ebeveyn, int s�tunSay�s�) {
        SpringLayout springSerilim = (SpringLayout) ebeveyn.getLayout();
        Component komponent = ebeveyn.getComponent (r * s�tunSay�s� + c);
        return springSerilim.getConstraints (komponent);
    } // h�creK�staslar�n�Al(..) metodu sonu...
} // J5c_51x2 s�n�f� sonu...