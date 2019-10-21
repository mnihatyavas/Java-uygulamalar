// J8a_1.java: I18NSample (Uluslararasý18ÜlkeNumünesi) örneði.

import java.util.Locale;
import java.util.ResourceBundle;

// Gereken dosya: J8a_1x_en_US.java
public class J8a_1 {
    static public void main (String[] args) {
        String lisan;
        String ülke;

        if (args.length != 2) {
            lisan = new String ("en");
            ülke = new String ("US");
            System.out.println ("<Dil> ve <Ülke> argüman çiftini girebilirdin!");
        }else {
            lisan = new String (args[0]);
            ülke = new String (args[1]);
        } // if-else bloðu sonu...

      Locale yerel;
      ResourceBundle mesaj;
      yerel = new Locale (lisan, ülke);
      mesaj = ResourceBundle.getBundle ("J8a_1x", yerel);

      System.out.println (mesaj.getString ("greetings"));
      System.out.println (mesaj.getString ("inquiry"));
      System.out.println (mesaj.getString ("farewell"));
   } // main(..) metodu sonu...
} // J8a_1 sýnýfý sonu...

/* Çýktý:

*/