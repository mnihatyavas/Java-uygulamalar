// J2c_3.java: Calculator (Hesapmakinesi) örneði.

public class J2c_3 {
      interface SayýMatematiði {
        double iþlem (double a, double b);   
    } // SayýMatematiði arayüzü sonu...
  
    public double hesapla (double a, double b, SayýMatematiði iþlemci) {
        return iþlemci.iþlem (a, b);
    } // hesapla(..) metodu sonu...
 
    public static void main (String... args) {
        double sayý1, sayý2;
        try {sayý1= Double.valueOf (args[0]);}catch (Exception ist) {sayý1 = 40;}
        try {sayý2= Double.valueOf (args[1]);}catch (Exception ist) {sayý2 = 35;}
    
        J2c_3 uygulamam = new J2c_3();

        SayýMatematiði toplama = (a, b) -> a + b;
        SayýMatematiði çýkarma = (a, b) -> a - b;
        SayýMatematiði çarpma = (a, b) -> a * b;
        SayýMatematiði bölme = (a, b) -> a / b;
        SayýMatematiði kalan = (a, b) -> a % b;

        System.out.println (sayý1 + " + " + sayý2 + " = " + uygulamam.hesapla (sayý1, sayý2, toplama));
        System.out.println (sayý1 + " - " + sayý2 + " = " + uygulamam.hesapla (sayý1, sayý2, çýkarma));
        System.out.println (sayý1 + " * " + sayý2 + " = " + uygulamam.hesapla (sayý1, sayý2, çarpma));
        System.out.println (sayý1 + " / " + sayý2 + " = " + uygulamam.hesapla (sayý1, sayý2, bölme));
        System.out.println (sayý1 + " % " + sayý2 + " = " + uygulamam.hesapla (sayý1, sayý2, kalan));
    } // main(..) metodu sonu...
} // J2c_3 sýnýfý sonu...

/* Çýktý:
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