// J2c_3.java: Calculator (Hesapmakinesi) �rne�i.

public class J2c_3 {
      interface Say�Matemati�i {
        double i�lem (double a, double b);   
    } // Say�Matemati�i aray�z� sonu...
  
    public double hesapla (double a, double b, Say�Matemati�i i�lemci) {
        return i�lemci.i�lem (a, b);
    } // hesapla(..) metodu sonu...
 
    public static void main (String... args) {
        double say�1, say�2;
        try {say�1= Double.valueOf (args[0]);}catch (Exception ist) {say�1 = 40;}
        try {say�2= Double.valueOf (args[1]);}catch (Exception ist) {say�2 = 35;}
    
        J2c_3 uygulamam = new J2c_3();

        Say�Matemati�i toplama = (a, b) -> a + b;
        Say�Matemati�i ��karma = (a, b) -> a - b;
        Say�Matemati�i �arpma = (a, b) -> a * b;
        Say�Matemati�i b�lme = (a, b) -> a / b;
        Say�Matemati�i kalan = (a, b) -> a % b;

        System.out.println (say�1 + " + " + say�2 + " = " + uygulamam.hesapla (say�1, say�2, toplama));
        System.out.println (say�1 + " - " + say�2 + " = " + uygulamam.hesapla (say�1, say�2, ��karma));
        System.out.println (say�1 + " * " + say�2 + " = " + uygulamam.hesapla (say�1, say�2, �arpma));
        System.out.println (say�1 + " / " + say�2 + " = " + uygulamam.hesapla (say�1, say�2, b�lme));
        System.out.println (say�1 + " % " + say�2 + " = " + uygulamam.hesapla (say�1, say�2, kalan));
    } // main(..) metodu sonu...
} // J2c_3 s�n�f� sonu...

/* ��kt�:
**  >java J2c_3 **
40.0 + 35.0 = 75.0
40.0 - 35.0 = 5.0
40.0 * 35.0 = 1400.0
40.0 / 35.0 = 1.1428571428571428
40.0 % 35.0 = 5.0

**  >java J2c_3 46.98 39.6412  ** TEKRAR
46.98 + 39.6412 = 86.62119999999999
46.98 - 39.6412 = 7.338799999999999
46.98 * 39.6412 = 1862.3435759999998
46.98 / 39.6412 = 1.1851306216764377
46.98 % 39.6412 = 7.338799999999999

**  >java J2c_3 -46.98 -39.6412  ** TEKRAR
-46.98 + -39.6412 = -86.62119999999999
-46.98 - -39.6412 = -7.338799999999999
-46.98 * -39.6412 = 1862.3435759999998
-46.98 / -39.6412 = 1.1851306216764377
-46.98 % -39.6412 = -7.338799999999999
*/