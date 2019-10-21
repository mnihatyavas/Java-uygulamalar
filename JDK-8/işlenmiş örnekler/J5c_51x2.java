// J5c_51x2.java: SpringUtilities (SpringserilimMetodlarý) alt-örneði.

import java.awt.Container;
import java.awt.Component;

import javax.swing.Spring;
import javax.swing.SpringLayout;

// Bu program SpringLayout'la form veya ýzgara türü serilim yapar...
public class J5c_51x2 {
    // Komponentin enküçük, tercihi ve enbüyük ebatlarýný dos ekranýna yazar...
    public static void ebatlarýYaz (Component komponent) {
        System.out.println ("Enküçük ebat = " + komponent.getMinimumSize());
        System.out.println ("Tercihi ebat = " + komponent.getPreferredSize());
        System.out.println ("Enbüyük ebat = " + komponent.getMaximumSize());
    } // ebatlarýYaz(..) metodu sonu...

    /* Alttaki 2 metotda ebeveyn, komponentlerinin herbiri ýzgara (satýr, sütun) formunda,
     * baþlangýç konumdan itibaren enbüyük tercihi en ve boyda serimlenecek
     * þekilde yeterli büyüklükte oluþturulur.
     */
    public static void ýzgaraYap (Container ebeveyn, int satýrSayýsý, int sütunSayýsý, int ilkX, int ilkY, int xTampon, int yTampon) {
        SpringLayout springSerilim;
        try {springSerilim = (SpringLayout)ebeveyn.getLayout();
        }catch (ClassCastException ist) {System.err.println ("Bu metodun ilk argümaný SpringLayout kullanmalýdýr!"); return;}

        Spring xTamponSpring = Spring.constant (xTampon);
        Spring yTamponSpring = Spring.constant (yTampon);
        Spring ilkXSpring = Spring.constant (ilkX);
        Spring ilkYSpring = Spring.constant (ilkY);
        int enb = satýrSayýsý * sütunSayýsý; // enb(üyük): max(imum)...

        // Herbir hücreye ayný ebat tahsisi için ebeveyn komponent springlerinin
        // enbüyük en/boy'larýný hesaplayalým...
        Spring enbGeniþliktekiSpring = springSerilim.getConstraints (ebeveyn.getComponent (0)).getWidth();
        Spring enbYüksekliktekiSpring = springSerilim.getConstraints (ebeveyn.getComponent (0)).getWidth();
        for (int i = 1; i < enb; i++) {
            SpringLayout.Constraints karþýlaþtýr = springSerilim.getConstraints (ebeveyn.getComponent (i));
            enbGeniþliktekiSpring = Spring.max (enbGeniþliktekiSpring, karþýlaþtýr.getWidth());
            enbYüksekliktekiSpring = Spring.max (enbYüksekliktekiSpring, karþýlaþtýr.getHeight());
        } // for döngüsü sonu...

        // Elde edilen enbüyük en/boy Spring deðeri eþdeðer ebat için tümüne uygulanacaktýr...
        for (int i = 0; i < enb; i++) {
            SpringLayout.Constraints karþýlaþtýr = springSerilim.getConstraints (ebeveyn.getComponent (i));
            karþýlaþtýr.setWidth (enbGeniþliktekiSpring);
            karþýlaþtýr.setHeight (enbYüksekliktekiSpring);
        } // for döngüsü sonu...

        // Bir ýzgara formunda serilim için tüm hücrelerin x/y sýnýrlamalarý ayarlanýr...
        SpringLayout.Constraints sonKýstaslar = null;
        SpringLayout.Constraints sonSatýrKýstaslarý = null;
        for (int i = 0; i < enb; i++) {
            SpringLayout.Constraints karþýlaþtýr = springSerilim.getConstraints (ebeveyn.getComponent(i));
            if (i % sütunSayýsý == 0) {// Yeni bir satýr baþlangýcý...
                sonSatýrKýstaslarý = sonKýstaslar;
                karþýlaþtýr.setX (ilkXSpring);
            }else {// x konumu bir önceki hücre komponentine göredir...
                karþýlaþtýr.setX (Spring.sum (sonKýstaslar.getConstraint (SpringLayout.EAST), xTamponSpring));
            } // if-else kararý sonu...

            if (i / sütunSayýsý == 0) {// Ýlk satýr..
                karþýlaþtýr.setY (ilkYSpring);
            }else { // y konumu bir önceki satýra göredir...
                karþýlaþtýr.setY (Spring.sum (sonSatýrKýstaslarý.getConstraint (SpringLayout.SOUTH), yTamponSpring));
            } // i-else kararý sonu...
            sonKýstaslar = karþýlaþtýr;
        } // for döngüsü sonu...

        // Ebeveyn pencerenin ebatýný belirleyelim...
        SpringLayout.Constraints ebeKýstaslarý = springSerilim.getConstraints (ebeveyn);
        ebeKýstaslarý.setConstraint (SpringLayout.SOUTH, Spring.sum (Spring.constant (yTampon), sonKýstaslar.getConstraint (SpringLayout.SOUTH)));
        ebeKýstaslarý.setConstraint (SpringLayout.EAST, Spring.sum (Spring.constant (xTampon), sonKýstaslar.getConstraint (SpringLayout.EAST)));
    }  // ýzgaraYap(..) metodu sonu...

    public static void kesifIzgaraYap (Container ebeveyn, int satýrSayýsý, int sütunSayýsý, int ilkX, int ilkY, int xTampon, int yTampon) {
        SpringLayout springSerilim;
        try {springSerilim = (SpringLayout)ebeveyn.getLayout();
        }catch (ClassCastException ist) {System.err.println ("Bu metodun ilk argümaný SpringLayout kullanmalýdýr!"); return;}

        // Herbir sütundaki hücreleri ayný geniþlikle hizalayalým...
        Spring x = Spring.constant (ilkX);
        for (int c = 0; c < sütunSayýsý; c++) {
            Spring en = Spring.constant (0);
            for (int r = 0; r < satýrSayýsý; r++) en = Spring.max (en, hücreKýstaslarýnýAl (r, c, ebeveyn, sütunSayýsý).getWidth());
            for (int r = 0; r < satýrSayýsý; r++) {
                SpringLayout.Constraints kýstaslar = hücreKýstaslarýnýAl (r, c, ebeveyn, sütunSayýsý);
                kýstaslar.setX (x);
                kýstaslar.setWidth (en);
            } // iç-for döngüsü sonu...
            x = Spring.sum (x, Spring.sum (en, Spring.constant (xTampon)));
        } // dýþ-for döngüsü sonu...

        // Herbir satýrdaki hücreleri ayný yükseklikte hizalayalým...
        Spring y = Spring.constant (ilkY);
        for (int r = 0; r < satýrSayýsý; r++) {
            Spring boy = Spring.constant (0);
            for (int c = 0; c < sütunSayýsý; c++) boy = Spring.max (boy, hücreKýstaslarýnýAl (r, c, ebeveyn, sütunSayýsý).getHeight());
            for (int c = 0; c < sütunSayýsý; c++) {
                SpringLayout.Constraints kýstaslar = hücreKýstaslarýnýAl (r, c, ebeveyn, sütunSayýsý);
                kýstaslar.setY (y);
                kýstaslar.setHeight (boy);
            } // iç-for döngüsü sonu...
            y = Spring.sum (y, Spring.sum (boy, Spring.constant (yTampon)));
        } // dýþ-for döngüsü sonu...

        // Ebeveyn pencere ebatýný belirleyelim...
        SpringLayout.Constraints ebeKýstaslarý = springSerilim.getConstraints (ebeveyn);
        ebeKýstaslarý.setConstraint (SpringLayout.SOUTH, y);
        ebeKýstaslarý.setConstraint (SpringLayout.EAST, x);
    } // kesifIzgaraYap(..) metodu sonu...

    private static SpringLayout.Constraints hücreKýstaslarýnýAl (int r, int c, Container ebeveyn, int sütunSayýsý) {
        SpringLayout springSerilim = (SpringLayout) ebeveyn.getLayout();
        Component komponent = ebeveyn.getComponent (r * sütunSayýsý + c);
        return springSerilim.getConstraints (komponent);
    } // hücreKýstaslarýnýAl(..) metodu sonu...
} // J5c_51x2 sýnýfý sonu...