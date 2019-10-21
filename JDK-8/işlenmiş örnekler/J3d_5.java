// J3d_5.java: PropertiesTest (�zelliklerinTesti) �rne�i.

import java.io.FileInputStream;
import java.util.Properties;

public class J3d_5 {
    public static void main(String[] args) throws Exception {
        // �evre�zellikleri.txt dosyas�na istedi�iniz sistem �evre �zelliklerini listeleyin...
        FileInputStream fis = new FileInputStream ( "�evre�zellikleri.txt");
        Properties �zelliklerListesi = new Properties (System.getProperties());
        �zelliklerListesi.load (fis);

        // Sistem �zelliklerini de�i�tir...
        System.setProperties (�zelliklerListesi);

        // Yeni �zellikleri yans�t...
        System.getProperties().list (System.out);
    } // main(..) metodu sonu...
} // J3d_5 s�n�f� sonu...

/* ��kt�:
**  >java J3d_5  **
-- listing properties --
java.vendor=Oracle Corporation
sun.java.launcher=SUN_STANDARD
windows_tracing_logfile=
NUMBER_OF_PROCESSORS=
sun.management.compiler=HotSpot Client Compiler
os.name=Windows 7
sun.boot.class.path=C:\Program Files\Java\jre1.8.0_121\li...
PATHEXT=
sun.desktop=windows
java.vm.specification.vendor=Oracle Corporation
TEMP=
java.runtime.version=1.8.0_121-b13
user.name=pc
ProgramFiles=
PROMPT=
user.language=tr
sun.boot.library.path=C:\Program Files\Java\jre1.8.0_121\bin
LOCALAPPDATA=
java.version=1.8.0_121
PROCESSOR_LEVEL=
user.timezone=
USERNAME=
sun.arch.data.model=32
java.endorsed.dirs=C:\Program Files\Java\jre1.8.0_121\li...
sun.cpu.isalist=pentium_pro+mmx pentium_pro pentium+m...
sun.jnu.encoding=Cp1254
file.encoding.pkg=sun.io
HOMEDRIVE=
file.separator=\
java.specification.name=Java Platform API Specification
java.class.version=52.0
user.country=TR
ALLUSERSPROFILE=
java.home=C:\Program Files\Java\jre1.8.0_121
java.vm.info=mixed mode, sharing
os.version=6.1
TMP=
path.separator=;
java.vm.version=25.121-b13
user.variant=
FP_NO_HOST_CHECK=
java.awt.printerjob=sun.awt.windows.WPrinterJob
CommonProgramFiles=
sun.io.unicode.encoding=UnicodeLittle
ComSpec=
HOMEPATH=
awt.toolkit=sun.awt.windows.WToolkit
sun.stdout.encoding=cp857
COMPUTERNAME=
user.script=
PUBLIC=
user.home=C:\Users\pc
ProgramData=
java.specification.vendor=Oracle Corporation
USERPROFILE=
java.library.path=C:\ProgramData\Oracle\Java\javapath;C...
java.vendor.url=http://java.oracle.com/
APPDATA=
java.vm.vendor=Oracle Corporation
java.runtime.name=Java(TM) SE Runtime Environment
sun.java.command=J3d_5
java.class.path=.
Path=
java.vm.specification.name=Java Virtual Machine Specification
PROCESSOR_ARCHITECTURE=
PROCESSOR_IDENTIFIER=
SESSIONNAME=
java.vm.specification.version=1.8
sun.cpu.endian=little
sun.os.patch.level=Service Pack 1
OS=
java.io.tmpdir=C:\Users\pc\AppData\Local\Temp\
java.vendor.url.bug=http://bugreport.sun.com/bugreport/
SystemRoot=
os.arch=x86
java.awt.graphicsenv=sun.awt.Win32GraphicsEnvironment
windows_tracing_flags=
java.ext.dirs=C:\Program Files\Java\jre1.8.0_121\li...
user.dir=C:\Users\pc\Desktop\MyFiles\4. Dersle...
LOGONSERVER=
USERDOMAIN=
line.separator=

java.vm.name=Java HotSpot(TM) Client VM
PROCESSOR_REVISION=
windir=
sun.stderr.encoding=cp857
file.encoding=Cp1254
SystemDrive=
java.specification.version=1.8
PSModulePath=
=::
*/