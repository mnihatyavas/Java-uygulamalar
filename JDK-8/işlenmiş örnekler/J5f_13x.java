// J5f_13x.java: SpringUtilities (KaynakKullan�ml�klar�) alt-�rne�i.

import java.awt.Container;
import java.awt.Component;

import javax.swing.Spring;
import javax.swing.SpringLayout;

// SpringLayout/KaynakSerilim ile form ve �zgara tarz� yerle�tirmeler yapar...
public class J5f_13x {
    public static void �zgaraYap (Container kab, int sat�rSay�s�, int kolonSay�s�,
                int konumX, int konumY, int xAral�k, int yAral�k) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab SpringLayout kullanmal�!"); return;}

        Spring xAral�kSpr = Spring.constant (xAral�k);
        Spring yAral�kSpr = Spring.constant (yAral�k);
        Spring konumXSpr = Spring.constant (konumX);
        Spring konumYSpr = Spring.constant (konumY);
        int azamiPar�aSay�s� = sat�rSay�s� * kolonSay�s�;

        // Azami en/boy hesab�yla, her par�a h�cresi ayn� ebatta olacakt�r...
        Spring azamiEnSpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getWidth();
        Spring azamiBoySpr = kaynakSerilim.getConstraints (kab.getComponent (0)).getHeight();
        for (int i = 1; i < azamiPar�aSay�s�; i++) {
            SpringLayout.Constraints s�n�rlay�c�lar = kaynakSerilim.getConstraints (kab.getComponent (i));
            azamiEnSpr = Spring.max (azamiEnSpr, s�n�rlay�c�lar.getWidth());
            azamiBoySpr = Spring.max (azamiBoySpr, s�n�rlay�c�lar.getHeight());
        } // for d�ng�s� sonu...

        for (int i = 0; i < azamiPar�aSay�s�; i++) {
            SpringLayout.Constraints s�n�rlay�c�lar = kaynakSerilim.getConstraints (kab.getComponent (i));
            s�n�rlay�c�lar.setWidth (azamiEnSpr);
            s�n�rlay�c�lar.setHeight (azamiBoySpr);
        } // for d�ng�s� sonu...

        SpringLayout.Constraints sonS�n�rlay�c�lar = null;
        SpringLayout.Constraints sonSat�rS�n�rlay�c�lar� = null;
        for (int i = 0; i < azamiPar�aSay�s�; i++) {
            SpringLayout.Constraints s�n�rlay�c�lar = kaynakSerilim.getConstraints (kab.getComponent (i));
            if (i % kolonSay�s� == 0) {// Bir sonraki sat�ra ge�er...
                sonSat�rS�n�rlay�c�lar� = sonS�n�rlay�c�lar;
                s�n�rlay�c�lar.setX (konumXSpr);
            }else // x konumu bir �nceki kolona g�relidir...
                s�n�rlay�c�lar.setX (Spring.sum (sonS�n�rlay�c�lar.getConstraint (SpringLayout.EAST), xAral�kSpr));

            if (i / kolonSay�s� == 0) // �lk sat�r...
                s�n�rlay�c�lar.setY (konumYSpr);
            else // y konumu bir �nceki sat�ra g�relidir...
                s�n�rlay�c�lar.setY (Spring.sum (sonSat�rS�n�rlay�c�lar�.getConstraint (SpringLayout.SOUTH),yAral�kSpr));

            sonS�n�rlay�c�lar = s�n�rlay�c�lar;
        } // for d�ng�s� sonu...

        // Kab'�n ebat� kuruluyor...
        SpringLayout.Constraints kabS�n�rlay�c�lar� = kaynakSerilim.getConstraints(kab);
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.SOUTH, // Dikey ebat...
                Spring.sum (Spring.constant (yAral�k), sonS�n�rlay�c�lar.getConstraint (SpringLayout.SOUTH)));
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.EAST, // Yatay ebat...
                Spring.sum (Spring.constant (xAral�k), sonS�n�rlay�c�lar.getConstraint (SpringLayout.EAST)));
    } // �zgaraYap(..) metodu sonu...

    public static void kesifIzgaraYap (Container kab, int sat�rSay�s�, int kolonSay�s�,
            int konumX, int konumY, int xAral�k, int yAral�k) {
        SpringLayout kaynakSerilim;
        try {kaynakSerilim = (SpringLayout)kab.getLayout();
        }catch (ClassCastException ist) {System.err.println ("HATA: Kab, SpringLayout kullanmal�!"); return;}

        // Her bir par�a tek tek azami geni�ili�i ve aral���yla yatay hizaland�r�l�yor...
        Spring x = Spring.constant (konumX);
        for (int k = 0; k < kolonSay�s�; k++) {
            Spring en = Spring.constant (0);
            for (int s = 0; s < sat�rSay�s�; s++) en = Spring.max (en, h�creler��inS�n�rlay�c�lar�Al (s, k, kab, kolonSay�s�).getWidth());
            for (int s = 0; s < sat�rSay�s�; s++) {
                SpringLayout.Constraints s�n�rlay�c�lar = h�creler��inS�n�rlay�c�lar�Al (s, k, kab, kolonSay�s�);
                s�n�rlay�c�lar.setX (x);
                s�n�rlay�c�lar.setWidth (en);
            } // ��-for d�ng�s� sonu...
            x = Spring.sum (x, Spring.sum (en, Spring.constant (xAral�k)));
        } // D��-for d�ng�s� sonu...

        // Her bir par�a tek tek azami y�ksekli�i ve aral���yla dikey hizaland�r�l�yor...
        Spring y = Spring.constant (konumY);
        for (int s = 0; s < sat�rSay�s�; s++) {
            Spring boy = Spring.constant (0);
            for (int k = 0; k < kolonSay�s�; k++) boy = Spring.max (boy, h�creler��inS�n�rlay�c�lar�Al (s, k, kab, kolonSay�s�).getHeight());
            for (int k = 0; k < kolonSay�s�; k++) {
                SpringLayout.Constraints s�n�rlay�c�lar = h�creler��inS�n�rlay�c�lar�Al (s, k, kab, kolonSay�s�);
                s�n�rlay�c�lar.setY (y);
                s�n�rlay�c�lar.setHeight (boy);
            } // �� for d�ng�s� sonu...
            y = Spring.sum (y, Spring.sum (boy, Spring.constant (yAral�k)));
        } // D��- for d�ng�s� sonu...

        // Kab'�n ebat� kuruluyor...
        SpringLayout.Constraints kabS�n�rlay�c�lar� = kaynakSerilim.getConstraints (kab);
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.SOUTH, y);
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.EAST, x);
    } // kesifIzgaraYap(..) metodu sonu...

    private static SpringLayout.Constraints h�creler��inS�n�rlay�c�lar�Al (
                int sat�rNo, int kolonNo, Container kab, int par�aSay�s�) {
        SpringLayout kaynakSerilim = (SpringLayout)kab.getLayout();
        Component par�a = kab.getComponent (sat�rNo * par�aSay�s� + kolonNo);
        return kaynakSerilim.getConstraints (par�a);
    } // h�creler��inS�n�rlay�c�lar�Al(..) metodu sonu...

    // (Debug i�in) komponentlerin tercihi, asgari ve azami ebatlar�n� dos penceresine yazar...
    public static void ebatlar�Yaz (Component par�a) {
        System.out.println ("Asgari ebat = " + par�a.getMinimumSize());
        System.out.println ("Tercihi ebat = " + par�a.getPreferredSize());
        System.out.println ("Azami ebat = " + par�a.getMaximumSize());
    } // ebatlar�Yaz(..) metodu sonu...
} // J5f_13x s�n�f� sonu...