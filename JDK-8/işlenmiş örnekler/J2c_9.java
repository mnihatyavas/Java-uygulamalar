// J2c_9.java: EnumTest (SabiteTesti) �rne�i.

// Gereken dosya: J2c_9x.java; Day
public class J2c_9 {
    J2c_9x g�n; // J2c_9x.java/Day/G�n...
    
    public J2c_9 (J2c_9x g�n) {this.g�n = g�n;} // S�n�f kurucusu...
    
    public void g�nlereYorum() {
        switch (g�n) {
            case PAZARTES�:
                System.out.println ("Pazartesiler, ilk i�g�n� ve berbatlar.");
                break;
            case CUMA:
                System.out.println ("Cumalar, haftasonu arifesi ve iyiceler.");
                break;
            case CUMARTES�: case PAZAR:
                System.out.println ("Haftasonu g�n�, eniyisi.");
                break;
            default:
                System.out.println ("Haftaortas� g�nler, eh ��yle b�yle.");
                break;
        } // switch-case blo�u sonu...
    } // g�nlereYorum() metodu sonu...

    public static void main (String[] args) {
        J2c_9 ilkG�n = new J2c_9 (J2c_9x.PAZARTES�); ilkG�n.g�nlereYorum();
        J2c_9 ���nc�G�n = new J2c_9 (J2c_9x.�AR�AMBA); ���nc�G�n.g�nlereYorum();
        J2c_9 be�inciG�n = new J2c_9 (J2c_9x.CUMA); be�inciG�n.g�nlereYorum();
        J2c_9 alt�nc�G�n = new J2c_9 (J2c_9x.CUMARTES�); alt�nc�G�n.g�nlereYorum();
        J2c_9 yedinciG�n = new J2c_9 (J2c_9x.PAZAR); yedinciG�n.g�nlereYorum();
    } // main(..) metodu sonu...
} // J2c_9 s�n�f� sonu...

/* ��kt�:
**  >java J2c_9  **
Pazartesiler, ilk i�g�n� ve berbatlar.
Haftaortas� g�nler, eh ��yle b�yle.
Cumalar, haftasonu arifesi ve iyiceler.
Haftasonu g�n�, eniyisi.
Haftasonu g�n�, eniyisi.
*/