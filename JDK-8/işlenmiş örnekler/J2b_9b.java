// J2b_9b.java: ConditionalDemo2 (�artl�G�sterim2) �rne�i.

class J2b_9b {
    public static void main(String[] args){
        int de�er1;
        int de�er2;

        try {de�er1 = Integer.valueOf (args[0]); }catch (Exception ist) {de�er1 = 1;}
        try {de�er2 = Integer.valueOf (args[1]); }catch (Exception ist) {de�er2 = 2;}

        int sonu�;
        boolean bir�art = true;
        sonu� = bir�art ? de�er1 : de�er2;

        System.out.println ("�art do�ruysa ilk de�er: " + sonu�);
    } // main(..) metodu sonu...
} // J2b_9b s�n�f� sonu...

/* ��kt�:
**  >java J2b_9b  **
�art do�ruysa ilk de�er: 1

**  >java J2b_9b -15 8  ** TEKRAR
�art do�ruysa ilk de�er: -15
*/