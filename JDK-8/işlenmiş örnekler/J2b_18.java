// J2b_18.java: UnaryDemo (TekliG�sterim) �rne�i.

class J2b_18 {
    public static void main (String[] args) {
        int sonu� = +1;
        System.out.println ("Sonu�=" + sonu�);

        sonu�--;
        System.out.println ("Sonu�=" + sonu�);

        sonu�++;
        System.out.println ("Sonu�=" + sonu�);

        sonu� = -sonu�;
        System.out.println ("Sonu�=" + sonu�);

        boolean ba�ar�l� = false;
        System.out.println ("Ba�ar�l� m�? " + ba�ar�l�);
        System.out.println ("Ba�ar�l� de�il mi? " + !ba�ar�l�);
    } // main(..) metodu sonu...
} // J2b_18 s�n�f� sonu...

/* ��kt�:
**  >java J2b_18  **
Sonu�=1
Sonu�=0
Sonu�=1
Sonu�=-1
Ba�ar�l� m�? false
Ba�ar�l� de�il mi? true
*/