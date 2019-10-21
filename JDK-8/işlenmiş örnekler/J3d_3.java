// J3d_3.java: EnvMap (ÇerveHaritasý) örneði.

import java.util.Map;

public class J3d_3 {
    public static void main (String[] args) {
        Map<String, String> çevreHaritasý = System.getenv();

        System.out.println ("Bilgisayarýnýzýn tüm sistem çevre deðerlerinin dökümü==>");
        for (String çevreAdý : çevreHaritasý.keySet()) {
            System.out.format ("%s = %s%n", çevreAdý, çevreHaritasý.get (çevreAdý));
        } // for-each döngüsü sonu...
    } // main(..) metodu sonu...
} // J3d_3 sýnýfý sonu...

/* Çýktý:
**  >java J3d_3  **
Bilgisayarýnýzýn tüm sistem çevre deðerlerinin dökümü==>
LOCALAPPDATA = C:\Users\pc\AppData\Local
PROCESSOR_LEVEL = 6
FP_NO_HOST_CHECK = NO
USERDOMAIN = pc-Bilgisayar
LOGONSERVER = \\PC-BILGISAYAR
PROMPT = $P$G
SESSIONNAME = Console
ALLUSERSPROFILE = C:\ProgramData
PROCESSOR_ARCHITECTURE = x86
PSModulePath = C:\Windows\system32\WindowsPowerShell\v1.0\Modules\
SystemDrive = C:
=ExitCode = 00000000
=C: = C:\Users\pc\Desktop\MyFiles\4. Dersler\JDK-8\uygulama
APPDATA = C:\Users\pc\AppData\Roaming
USERNAME = pc
windows_tracing_logfile = C:\BVTBin\Tests\installpackage\csilogfile.log
CommonProgramFiles = C:\Program Files\Common Files
Path = C:\ProgramData\Oracle\Java\javapath;C:\Program Files\Intel\TXE Components\TCS\;C:\Windows\system32;C:\Windows;C:\Windows\System32\Wbem;C:\Windows\System32\WindowsPowerShell\v1.0\;c:\program files\java\jdk1.8.0_121\bin
PATHEXT = .COM;.EXE;.BAT;.CMD;.VBS;.VBE;.JS;.JSE;.WSF;.WSH;.MSC
OS = Windows_NT
windows_tracing_flags = 3
COMPUTERNAME = PC-BILGISAYAR
PROCESSOR_REVISION = 3708
ComSpec = C:\Windows\system32\cmd.exe
ProgramData = C:\ProgramData
HOMEPATH = \Users\pc
SystemRoot = C:\Windows
TEMP = C:\Users\pc\AppData\Local\Temp
HOMEDRIVE = C:
PROCESSOR_IDENTIFIER = x86 Family 6 Model 55 Stepping 8, GenuineIntel
USERPROFILE = C:\Users\pc
TMP = C:\Users\pc\AppData\Local\Temp
ProgramFiles = C:\Program Files
PUBLIC = C:\Users\Public
NUMBER_OF_PROCESSORS = 2
windir = C:\Windows
=:: = ::\
*/