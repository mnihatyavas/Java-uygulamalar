// J3d_4.java: PassEnv (Aktar�evre) �rne�i.

import java.util.Map;

public class J3d_4 {
    public static void main (String[] args) throws java.io.IOException {
        System.out.println (System.getProperty ("java.class.path"));
        ProcessBuilder pb = new ProcessBuilder ("java", "Env", "DRAGONBALLS");
        Map<String, String> �evreHaritas� = pb.environment();
        �evreHaritas�.put ("DRAGONBALLS", "7");
        pb.start();
    } // main(..) metodu sonu...
} // J3d_4 s�n�f� sonu...

/* ��kt�:
C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama>java J3d_4
. // Ba�ka ��kt� yok. Herhalde mevcut olsayd� DRAGONBALLS oyununu ba�latacakt�...
*/