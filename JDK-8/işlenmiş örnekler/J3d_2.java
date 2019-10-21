// J3d_2.java: Env (�evre) �rne�i.

public class J3d_2 {
    public static void main (String[] args) {
        for (String arg�man: args) {
            String �evreDe�eri = System.getenv (arg�man);
            if (�evreDe�eri != null) System.out.format ("Arg�man: [%s] = Arg�man�n �evre de�eri: [%s]%n", arg�man, �evreDe�eri);
            else System.out.format ("Arg�man: [%s]'e �evre de�eri atanmad�.%n", arg�man);
        } // for-each d�ng�s� sonu...
    } // main(..) metodu sonu...
} // J3d_2 s�n�f� sonu...

/* ��kt�:
**  >java J3d_2  **
// ��kt� yok...

**  >java J3d_2 M. Nihat Yava�, 17 04 1957, Ye�ilyurt - Malatya, TR  ** TEKRAR
Arg�man: [M.]'e �evre de�eri atanmad�.
Arg�man: [Nihat]'e �evre de�eri atanmad�.
Arg�man: [Yava�,]'e �evre de�eri atanmad�.
Arg�man: [17]'e �evre de�eri atanmad�.
Arg�man: [04]'e �evre de�eri atanmad�.
Arg�man: [1957,]'e �evre de�eri atanmad�.
Arg�man: [Ye�ilyurt]'e �evre de�eri atanmad�.
Arg�man: [-]'e �evre de�eri atanmad�.
Arg�man: [Malatya,]'e �evre de�eri atanmad�.
Arg�man: [TR]'e �evre de�eri atanmad�.

**  >java J3d_2 "M. Nihat Yava�, 17 04 1957, Ye�ilyurt - Malatya, TR"  ** TEKRAR
Arg�man: [M. Nihat Yava�, 17 04 1957, Ye�ilyurt - Malatya, TR]'e �evre de�eri atanmad�.

**  >java J3d_2 path  ** TEKRAR
Arg�man: [path] = Arg�man�n �evre de�eri: [C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\TXE Components\TCS\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;c:\program files\java\jdk1.8.0_121\bin]

**  >java J3d_2 path username computername  ** TEKRAR
Arg�man: [path] = Arg�man�n �evre de�eri: [C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\TXE Components\TCS\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;c:\program files\java\jdk1.8.0_121\bin]
Arg�man: [username] = Arg�man�n �evre de�eri: [pc]
Arg�man: [computername] = Arg�man�n �evre de�eri: [PC-BILGISAYAR]
*/