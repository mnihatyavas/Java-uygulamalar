// J8d_3.java: DateFormatSymbolsDemo (TarihBi�imlemeSembolleriG�sterisi) �rne�i.

import java.util.Locale;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;

public class J8d_3 {
    static public void haftan�nG�nleriniDe�i�tirerekG�ster() {
        SimpleDateFormat tarihBi�imi;
        DateFormatSymbols tarihSembolleri;
        String[] varsay�l�G�nler;
        String[] de�i�tirilenG�nler;

        System.out.println ("Yerel: 'en_US'");
        tarihSembolleri = new DateFormatSymbols (new Locale ("en","US"));
        varsay�l�G�nler = tarihSembolleri.getWeekdays();
        for (int i = 0; i < varsay�l�G�nler.length; i++) System.out.print (varsay�l�G�nler[i] + "  ");
        System.out.println();
        varsay�l�G�nler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < varsay�l�G�nler.length; i++) System.out.print (varsay�l�G�nler[i] + "  ");
        System.out.println();
        String[] b�y�k3HarfliG�nlerABD = {"", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        tarihSembolleri.setShortWeekdays (b�y�k3HarfliG�nlerABD);
        de�i�tirilenG�nler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < de�i�tirilenG�nler.length; i++) System.out.print (de�i�tirilenG�nler[i] + "  ");
        System.out.println();
        tarihBi�imi = new SimpleDateFormat ("E", tarihSembolleri);
        System.out.println ("Haftan�n akt�el g�n�: " + tarihBi�imi.format (new Date()));

        System.out.println ("\nYerel: 'tr_TR'");
        tarihSembolleri = new DateFormatSymbols (new Locale ("tr","TR"));
        varsay�l�G�nler = tarihSembolleri.getWeekdays();
        for (int i = 0; i < varsay�l�G�nler.length; i++) System.out.print (varsay�l�G�nler[i] + "  ");
        System.out.println();
        varsay�l�G�nler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < varsay�l�G�nler.length; i++) System.out.print (varsay�l�G�nler[i] + "  ");
        System.out.println();
        String[] b�y�k3HarfliG�nlerTR = {"", "PAZ", "PZT", "SAL", "�AR", "PER", "CUM", "CMT"};
        tarihSembolleri.setShortWeekdays (b�y�k3HarfliG�nlerTR);
        de�i�tirilenG�nler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < de�i�tirilenG�nler.length; i++) System.out.print (de�i�tirilenG�nler[i] + "  ");
        System.out.println();
        tarihBi�imi = new SimpleDateFormat ("EEEE", tarihSembolleri);
        System.out.println ("Haftan�n akt�el g�n�: " + tarihBi�imi.format (new Date()));
    } // haftan�nG�nleriniDe�i�tirerekG�ster() metodu sonu...

    static public void main (String[] args) {haftan�nG�nleriniDe�i�tirerekG�ster();}
} // J8d_3 s�n�f� sonu...

/* ��kt�:
**  >java J8d_3  **
Yerel: 'en_US'
  Sunday  Monday  Tuesday  Wednesday  Thursday  Friday  Saturday
  Sun  Mon  Tue  Wed  Thu  Fri  Sat
  SUN  MON  TUE  WED  THU  FRI  SAT
Haftan�n akt�el g�n�: MON

Yerel: 'tr_TR'
  Pazar  Pazartesi  Sal�  �ar�amba  Per�embe  Cuma  Cumartesi
  Paz  Pzt  Sal  �ar  Per  Cum  Cmt
  PAZ  PZT  SAL  �AR  PER  CUM  CMT
Haftan�n akt�el g�n�: Pazartesi
*/