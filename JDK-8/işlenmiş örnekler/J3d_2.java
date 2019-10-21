// J3d_2.java: Env (Çevre) örneði.

public class J3d_2 {
    public static void main (String[] args) {
        for (String argüman: args) {
            String çevreDeðeri = System.getenv (argüman);
            if (çevreDeðeri != null) System.out.format ("Argüman: [%s] = Argümanýn çevre deðeri: [%s]%n", argüman, çevreDeðeri);
            else System.out.format ("Argüman: [%s]'e çevre deðeri atanmadý.%n", argüman);
        } // for-each döngüsü sonu...
    } // main(..) metodu sonu...
} // J3d_2 sýnýfý sonu...

/* Çýktý:
**  >java J3d_2  **
// Çýktý yok...

**  >java J3d_2 M. Nihat Yavaþ, 17 04 1957, Yeþilyurt - Malatya, TR  ** TEKRAR
Argüman: [M.]'e çevre deðeri atanmadý.
Argüman: [Nihat]'e çevre deðeri atanmadý.
Argüman: [Yavaþ,]'e çevre deðeri atanmadý.
Argüman: [17]'e çevre deðeri atanmadý.
Argüman: [04]'e çevre deðeri atanmadý.
Argüman: [1957,]'e çevre deðeri atanmadý.
Argüman: [Yeþilyurt]'e çevre deðeri atanmadý.
Argüman: [-]'e çevre deðeri atanmadý.
Argüman: [Malatya,]'e çevre deðeri atanmadý.
Argüman: [TR]'e çevre deðeri atanmadý.

**  >java J3d_2 "M. Nihat Yavaþ, 17 04 1957, Yeþilyurt - Malatya, TR"  ** TEKRAR
Argüman: [M. Nihat Yavaþ, 17 04 1957, Yeþilyurt - Malatya, TR]'e çevre deðeri atanmadý.

**  >java J3d_2 path  ** TEKRAR
Argüman: [path] = Argümanýn çevre deðeri: [C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\TXE Components\TCS\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;c:\program files\java\jdk1.8.0_121\bin]

**  >java J3d_2 path username computername  ** TEKRAR
Argüman: [path] = Argümanýn çevre deðeri: [C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\TXE Components\TCS\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;c:\program files\java\jdk1.8.0_121\bin]
Argüman: [username] = Argümanýn çevre deðeri: [pc]
Argüman: [computername] = Argümanýn çevre deðeri: [PC-BILGISAYAR]
*/