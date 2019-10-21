/* J4a_10.java: MathApplet (MatematikApleti) �rne�i.
 *
 * <applet code="J4a_10.class" width="300" height="300"></applet>
 */

/* Gereken dosyalar:
 *   J4a_10x1.java=Calculator.java
 *   J4a_10x2.java=DateHelper.java
 */
import java.applet.Applet;

public class J4a_10 extends Applet{
    public String kullan�c�Ad� = null;

    public void init() {
        try {Runtime.getRuntime().exec ("C:\\Windows\\System32\\calc.exe");
        }catch (Exception ist){ist.printStackTrace();}
    } // init() metodu sonu...

    public String selamla() {return "Merhaba " + kullan�c�Ad� + "!";}
    public J4a_10x1 hesapla() {return new J4a_10x1();} // J4a_10x1=Calculator...
    public J4a_10x2 randevula�() {return new J4a_10x2();} // J4a_10x2=DateHelper...
    public void yazd�r (String metin) {System.out.println (metin);}
} // J4a_10 s�n�f� sonu...