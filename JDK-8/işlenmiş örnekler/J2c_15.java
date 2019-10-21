// J2c_15.java: FillerTest (DolguTesti) örneði.

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class J2c_15 {
    public static void main (String[] args) {
        List<String> liste = new ArrayList<String>();
        for (int i = 0; i < 10; i++) liste.add ("");

        String dolgu;
        try {dolgu = args[0];}catch (Exception ist) {dolgu = "*****************";}

        Collections.fill (liste, dolgu);
        System.out.println (liste.size() + " elemanlý ArrayList dökümü==>");
        for (String eleman : liste) System.out.format ("%s%n", eleman);
    } // main(..) metodu sonu...
} // J2c_15 sýnýfý sonu...

/* Çýktý:
**  >java J2c_15  **
10 elemanlý ArrayList dökümü==>
*****************
*****************
*****************
*****************
*****************
*****************
*****************
*****************
*****************
*****************

**  >java J2c_15 "M.Nihat Yavaþ, 1957"  ** TEKRAR
10 elemanlý ArrayList dökümü==>
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
M.Nihat Yavaþ, 1957
*/