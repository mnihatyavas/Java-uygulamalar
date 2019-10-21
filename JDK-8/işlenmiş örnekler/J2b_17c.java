// J2b_17c.java: SwitchDemo2 (SwitchG�sterimi2) �rne�i.

class J2b_17c {
    public static void main (String[] args) {
        int ay;
        int y�l;
        int g�nSay�s� = 0;
        try {ay = Integer.valueOf (args[0]);} catch (Exception ist) {ay = 4;}
        try {y�l = Integer.valueOf (args[1]);} catch (Exception ist) {y�l = 2018;}

        switch (ay) {
            case 1: case 3: case 5:
            case 7: case 8: case 10:
            case 12:
                g�nSay�s� = 31;
                break;
            case 4: case 6: case 9: case 11:
                g�nSay�s� = 30;
                break;
            case 2:
                if (((y�l % 4 == 0) && !(y�l % 100 == 0)) || (y�l % 400 == 0)) g�nSay�s� = 29;
                else g�nSay�s� = 28;
                break;
            default:
                System.out.println ("Ge�ersiz ay giri�i");
                System.exit (0);
                // break; // gereksiz...
        } // switch-case blo�u sonu...

        System.out.println (y�l + " y�l�n�n " + ay + ".ay�n�n g�n say�s�: " + g�nSay�s�);
    } // main(..) metodu sonu...
} // J2b_17c s�n�f� sonu...

/* ��kt�:
**  >java J2b_17c  **
2018 y�l�n�n 4.ay�n�n g�n say�s�: 30

**  >java J2b_17c 2  ** TEKRAR
2018 y�l�n�n 2.ay�n�n g�n say�s�: 28

**  >java J2b_17c 2 2000  ** TEKRAR
2000 y�l�n�n 2.ay�n�n g�n say�s�: 29

**  >java J2b_17c 20 2016  ** TEKRAR
Ge�ersiz ay giri�i
*/