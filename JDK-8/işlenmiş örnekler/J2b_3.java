// J2b_3.java: ArrayDemo (DizinGösterisi) örneði.

class J2b_3 {
    public static void main (String[] args) {
        int[] dizin; // Bir tamsayý dizini tanýmlanýyor...

        dizin = new int[10]; // Hafýzada 10 tamsayýlýk yer tahsis ediliyor...
           
        dizin[0] = 100; // Dizinin ilk elemaný atanýyor...
        dizin[1] = 200; // Dizinin ikinci elemaný atanýyor...
        dizin[2] = 300; // Ve diðer elemanlarý da atanýyor...
        dizin[3] = 400;
        dizin[4] = 500;
        dizin[5] = 600;
        dizin[6] = 700;
        dizin[7] = 800;
        dizin[8] = 900;
        dizin[9] = 1000;

        System.out.println ("Endeks 0'daki 1.eleman: " + dizin[0]);
        System.out.println ("Endeks 1'deki 2.eleman: " + dizin[1]);
        System.out.println ("Endeks 2'daki 3.eleman: " + dizin[2]);
        System.out.println ("Endeks 3'daki 4.eleman: " + dizin[3]);
        System.out.println ("Endeks 4'daki 5.eleman: " + dizin[4]);
        System.out.println ("Endeks 5'daki 6.eleman: " + dizin[5]);
        System.out.println ("Endeks 6'daki 7.eleman: " + dizin[6]);
        System.out.println ("Endeks 7'daki 8.eleman: " + dizin[7]);
        System.out.println ("Endeks 8'daki 9.eleman: " + dizin[8]);
        System.out.println ("Endeks 9'daki 10.eleman: " + dizin[9]);
    } // main(..) metodu sonu...
} // J2b_3 sýnýfý sonu...

/* Çýktý:
**  >java J2b_3  **
Endeks 0'daki 1.eleman: 100
Endeks 1'deki 2.eleman: 200
Endeks 2'daki 3.eleman: 300
Endeks 3'daki 4.eleman: 400
Endeks 4'daki 5.eleman: 500
Endeks 5'daki 6.eleman: 600
Endeks 6'daki 7.eleman: 700
Endeks 7'daki 8.eleman: 800
Endeks 8'daki 9.eleman: 900
Endeks 9'daki 10.eleman: 1000
*/