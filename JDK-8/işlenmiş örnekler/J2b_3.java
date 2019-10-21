// J2b_3.java: ArrayDemo (DizinG�sterisi) �rne�i.

class J2b_3 {
    public static void main (String[] args) {
        int[] dizin; // Bir tamsay� dizini tan�mlan�yor...

        dizin = new int[10]; // Haf�zada 10 tamsay�l�k yer tahsis ediliyor...
           
        dizin[0] = 100; // Dizinin ilk eleman� atan�yor...
        dizin[1] = 200; // Dizinin ikinci eleman� atan�yor...
        dizin[2] = 300; // Ve di�er elemanlar� da atan�yor...
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
} // J2b_3 s�n�f� sonu...

/* ��kt�:
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