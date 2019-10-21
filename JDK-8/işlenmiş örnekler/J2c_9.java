// J2c_9.java: EnumTest (SabiteTesti) örneði.

// Gereken dosya: J2c_9x.java; Day
public class J2c_9 {
    J2c_9x gün; // J2c_9x.java/Day/Gün...
    
    public J2c_9 (J2c_9x gün) {this.gün = gün;} // Sýnýf kurucusu...
    
    public void günlereYorum() {
        switch (gün) {
            case PAZARTESÝ:
                System.out.println ("Pazartesiler, ilk iþgünü ve berbatlar.");
                break;
            case CUMA:
                System.out.println ("Cumalar, haftasonu arifesi ve iyiceler.");
                break;
            case CUMARTESÝ: case PAZAR:
                System.out.println ("Haftasonu günü, eniyisi.");
                break;
            default:
                System.out.println ("Haftaortasý günler, eh þöyle böyle.");
                break;
        } // switch-case bloðu sonu...
    } // günlereYorum() metodu sonu...

    public static void main (String[] args) {
        J2c_9 ilkGün = new J2c_9 (J2c_9x.PAZARTESÝ); ilkGün.günlereYorum();
        J2c_9 üçüncüGün = new J2c_9 (J2c_9x.ÇARÞAMBA); üçüncüGün.günlereYorum();
        J2c_9 beþinciGün = new J2c_9 (J2c_9x.CUMA); beþinciGün.günlereYorum();
        J2c_9 altýncýGün = new J2c_9 (J2c_9x.CUMARTESÝ); altýncýGün.günlereYorum();
        J2c_9 yedinciGün = new J2c_9 (J2c_9x.PAZAR); yedinciGün.günlereYorum();
    } // main(..) metodu sonu...
} // J2c_9 sýnýfý sonu...

/* Çýktý:
**  >java J2c_9  **
Pazartesiler, ilk iþgünü ve berbatlar.
Haftaortasý günler, eh þöyle böyle.
Cumalar, haftasonu arifesi ve iyiceler.
Haftasonu günü, eniyisi.
Haftasonu günü, eniyisi.
*/