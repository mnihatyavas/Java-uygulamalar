// J2bq_1.java: NestedIf (��i�eIf) �rne�i.

class J2bq_1 {
    public static void main (String[] args) {
        int say� = 0;
        try {say� = Integer.valueOf (args[0]);
        }catch (Exception ist) {System.out.println ("Yanl�� say� giri�i!"); System.exit (0);}

        if (say� >= 0)
            if (say� == 0) System.out.println ("Say� s�f�r'd�r.");
            else System.out.println ("Say� pozitiftir.");
        else System.out.println ("Say� negatiftir.");
    } // main(..) metodu sonu...
} // J2bq_1 s�n�f� sonu...

/* ��kt�:
**  >java J2bq_1  **
Yanl�� say� giri�i!

**  >java J2bq_1 say�  ** TEKRAR
Yanl�� say� giri�i!

**  >java J2bq_1 -0  ** TEKRAR
Say� s�f�r'd�r.

**  >java J2bq_1 -29  ** TEKRAR
Say� negatiftir.

**  >java J2bq_1 9  ** TEKRAR
Say� pozitiftir.
*/