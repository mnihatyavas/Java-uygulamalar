// J2c_15.java: FillerTest (DolguTesti) �rne�i.

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
        System.out.println (liste.size() + " elemanl� ArrayList d�k�m�==>");
        for (String eleman : liste) System.out.format ("%s%n", eleman);
    } // main(..) metodu sonu...
} // J2c_15 s�n�f� sonu...

/* ��kt�:
**  >java J2c_15  **
10 elemanl� ArrayList d�k�m�==>
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

**  >java J2c_15 "M.Nihat Yava�, 1957"  ** TEKRAR
10 elemanl� ArrayList d�k�m�==>
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
M.Nihat Yava�, 1957
*/