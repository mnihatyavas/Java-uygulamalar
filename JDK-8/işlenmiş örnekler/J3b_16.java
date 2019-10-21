// J3b_16.java: RhymingWords (KafiyeliKelimeler) örneði.

import java.io.IOException;

import java.io.FileReader;
import java.io.Reader;
import java.io.BufferedReader;

import java.io.PipedWriter;
import java.io.PipedReader;
import java.io.PrintWriter;
import java.io.FileWriter;

/* Gereken dosyalar:
 *   J3b_16x1.java=ReverseThread
 *   J3b_16x2.java=SortThread
 */
public class J3b_16 {
    public static void main (String[] args) throws IOException {
        String dosyaAdý = args.length > 0? args[0] : "kelimeler.txt";

        FileReader kelimeler = new FileReader (dosyaAdý);

        // Tersleme ve sýralamayý yapalým...
        Reader kafiyeliKelimeler = tersle (sýrala (tersle (kelimeler)));

        System.out.println ("Terslenen->Sýralanan->Terslenen kelimelerin Listesi:");
        BufferedReader oku1 = new BufferedReader (kafiyeliKelimeler);
        String satýr;
        while ((satýr = oku1.readLine()) != null) System.out.println (satýr);
        oku1.close();

        System.out.println ("\nOrijinal kelimelerin Listesi:");
        BufferedReader oku2 = new BufferedReader (new FileReader (dosyaAdý));
        while ((satýr = oku2.readLine()) != null) System.out.println (satýr);
        oku2.close();
    } // main(..) metodu sonu...

    public static Reader tersle (Reader kaynak) throws IOException {
        BufferedReader oku = new BufferedReader (kaynak);

        PipedWriter borulaYaz = new PipedWriter();
        PipedReader borulaOku = new PipedReader (borulaYaz);
        PrintWriter yaz = new PrintWriter (borulaYaz);

        new J3b_16x1 (yaz, oku).start(); // J3b_16x1=ReverseThread/TersleyenSicim...

        return borulaOku;
    } // tersle(..) metodu sonu...

    public static Reader sýrala (Reader kaynak) throws IOException {
        BufferedReader oku = new BufferedReader (kaynak);

        PipedWriter borulaYaz = new PipedWriter();
        PipedReader borulaOku = new PipedReader (borulaYaz);
        PrintWriter yaz = new PrintWriter (borulaYaz);

        new J3b_16x2 (yaz, oku).start(); // J3b_16x2=SortThread/SýralayanSicim...

        return borulaOku;
    } // sýrala(..) metodu sonu...
} // J3b_16 sýnýfý sonu...

/* Çýktý:
**  >java J3b_16 **
Terslenen->Sýralanan->Terslenen kelimelerin Listesi:
Java // Son harflerden itibaren küçükten büyüðe sýralý...
interface
image
language
communicate
integrate
native
string
network
stream
program
application
animation
exception
primer
container
user
graphics
threads
tools
class
bolts
nuts
object
applet
environment
development
argument
component
input
output
anatomy
security

Orijinal kelimelerin Listesi:
anatomy
animation
applet
application
argument
bolts
class
communicate
component
container
development
environment
exception
graphics
image
input
integrate
interface
Java
language
native
network
nuts
object
output
primer
program
security
stream
string
threads
tools
user
*/