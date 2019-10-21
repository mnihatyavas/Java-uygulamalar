// J3d_4.java: PassEnv (AktarÇevre) örneði.

import java.util.Map;

public class J3d_4 {
    public static void main (String[] args) throws java.io.IOException {
        System.out.println (System.getProperty ("java.class.path"));
        ProcessBuilder pb = new ProcessBuilder ("java", "Env", "DRAGONBALLS");
        Map<String, String> çevreHaritasý = pb.environment();
        çevreHaritasý.put ("DRAGONBALLS", "7");
        pb.start();
    } // main(..) metodu sonu...
} // J3d_4 sýnýfý sonu...

/* Çýktý:
C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama>java J3d_4
. // Baþka çýktý yok. Herhalde mevcut olsaydý DRAGONBALLS oyununu baþlatacaktý...
*/