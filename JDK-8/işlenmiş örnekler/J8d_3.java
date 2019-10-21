// J8d_3.java: DateFormatSymbolsDemo (TarihBiçimlemeSembolleriGösterisi) örneði.

import java.util.Locale;
import java.util.Date;

import java.text.SimpleDateFormat;
import java.text.DateFormatSymbols;

public class J8d_3 {
    static public void haftanýnGünleriniDeðiþtirerekGöster() {
        SimpleDateFormat tarihBiçimi;
        DateFormatSymbols tarihSembolleri;
        String[] varsayýlýGünler;
        String[] deðiþtirilenGünler;

        System.out.println ("Yerel: 'en_US'");
        tarihSembolleri = new DateFormatSymbols (new Locale ("en","US"));
        varsayýlýGünler = tarihSembolleri.getWeekdays();
        for (int i = 0; i < varsayýlýGünler.length; i++) System.out.print (varsayýlýGünler[i] + "  ");
        System.out.println();
        varsayýlýGünler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < varsayýlýGünler.length; i++) System.out.print (varsayýlýGünler[i] + "  ");
        System.out.println();
        String[] büyük3HarfliGünlerABD = {"", "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        tarihSembolleri.setShortWeekdays (büyük3HarfliGünlerABD);
        deðiþtirilenGünler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < deðiþtirilenGünler.length; i++) System.out.print (deðiþtirilenGünler[i] + "  ");
        System.out.println();
        tarihBiçimi = new SimpleDateFormat ("E", tarihSembolleri);
        System.out.println ("Haftanýn aktüel günü: " + tarihBiçimi.format (new Date()));

        System.out.println ("\nYerel: 'tr_TR'");
        tarihSembolleri = new DateFormatSymbols (new Locale ("tr","TR"));
        varsayýlýGünler = tarihSembolleri.getWeekdays();
        for (int i = 0; i < varsayýlýGünler.length; i++) System.out.print (varsayýlýGünler[i] + "  ");
        System.out.println();
        varsayýlýGünler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < varsayýlýGünler.length; i++) System.out.print (varsayýlýGünler[i] + "  ");
        System.out.println();
        String[] büyük3HarfliGünlerTR = {"", "PAZ", "PZT", "SAL", "ÇAR", "PER", "CUM", "CMT"};
        tarihSembolleri.setShortWeekdays (büyük3HarfliGünlerTR);
        deðiþtirilenGünler = tarihSembolleri.getShortWeekdays();
        for (int i = 0; i < deðiþtirilenGünler.length; i++) System.out.print (deðiþtirilenGünler[i] + "  ");
        System.out.println();
        tarihBiçimi = new SimpleDateFormat ("EEEE", tarihSembolleri);
        System.out.println ("Haftanýn aktüel günü: " + tarihBiçimi.format (new Date()));
    } // haftanýnGünleriniDeðiþtirerekGöster() metodu sonu...

    static public void main (String[] args) {haftanýnGünleriniDeðiþtirerekGöster();}
} // J8d_3 sýnýfý sonu...

/* Çýktý:
**  >java J8d_3  **
Yerel: 'en_US'
  Sunday  Monday  Tuesday  Wednesday  Thursday  Friday  Saturday
  Sun  Mon  Tue  Wed  Thu  Fri  Sat
  SUN  MON  TUE  WED  THU  FRI  SAT
Haftanýn aktüel günü: MON

Yerel: 'tr_TR'
  Pazar  Pazartesi  Salý  Çarþamba  Perþembe  Cuma  Cumartesi
  Paz  Pzt  Sal  Çar  Per  Cum  Cmt
  PAZ  PZT  SAL  ÇAR  PER  CUM  CMT
Haftanýn aktüel günü: Pazartesi
*/