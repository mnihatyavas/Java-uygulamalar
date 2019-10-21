// J2b_17c.java: SwitchDemo2 (SwitchGösterimi2) örneði.

class J2b_17c {
    public static void main (String[] args) {
        int ay;
        int yýl;
        int günSayýsý = 0;
        try {ay = Integer.valueOf (args[0]);} catch (Exception ist) {ay = 4;}
        try {yýl = Integer.valueOf (args[1]);} catch (Exception ist) {yýl = 2018;}

        switch (ay) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                günSayýsý = 31;
                break;
            case 4: case 6: case 9: case 11:
                günSayýsý = 30;
                break;
            case 2:
                if (((yýl % 4 == 0) && !(yýl % 100 == 0)) || (yýl % 400 == 0)) günSayýsý = 29;
                else günSayýsý = 28;
                break;
            default:
                System.out.println ("Geçersiz ay giriþi");
                System.exit (0);
                // break; // gereksiz...
        } // switch-case bloðu sonu...

        System.out.println (yýl + " yýlýnýn " + ay + ".ayýnýn gün sayýsý: " + günSayýsý);
    } // main(..) metodu sonu...
} // J2b_17c sýnýfý sonu...

/* Çýktý:
**  >java J2b_17c  **
2018 yýlýnýn 4.ayýnýn gün sayýsý: 30

**  >java J2b_17c 2  ** TEKRAR
2018 yýlýnýn 2.ayýnýn gün sayýsý: 28

**  >java J2b_17c 2 2000  ** TEKRAR
2000 yýlýnýn 2.ayýnýn gün sayýsý: 29

**  >java J2b_17c 20 2016  ** TEKRAR
Geçersiz ay giriþi
*/