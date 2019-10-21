// J8a_1.java: I18NSample (Uluslararas�18�lkeNum�nesi) �rne�i.

import java.util.Locale;
import java.util.ResourceBundle;

// Gereken dosya: J8a_1x_en_US.java
public class J8a_1 {
    static public void main (String[] args) {
        String lisan;
        String �lke;

        if (args.length != 2) {
            lisan = new String ("en");
            �lke = new String ("US");
            System.out.println ("<Dil> ve <�lke> arg�man �iftini girebilirdin!");
        }else {
            lisan = new String (args[0]);
            �lke = new String (args[1]);
        } // if-else blo�u sonu...

      Locale yerel;
      ResourceBundle mesaj;
      yerel = new Locale (lisan, �lke);
      mesaj = ResourceBundle.getBundle ("J8a_1x", yerel);

      System.out.println (mesaj.getString ("greetings"));
      System.out.println (mesaj.getString ("inquiry"));
      System.out.println (mesaj.getString ("farewell"));
   } // main(..) metodu sonu...
} // J8a_1 s�n�f� sonu...

/* ��kt�:

*/