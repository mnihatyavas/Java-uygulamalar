// J2cq_4.java: NumberHolderDisplay (SayıTutanıGöster) örneği.

// Gereken dosya: J2cq_4x.java; NumberHolder
public class J2cq_4 {
    public static void main(String[] args) {
        J2cq_4x birSayıTutan = new J2cq_4x();

        birSayıTutan.sayıInt = 1;
        birSayıTutan.sayıFloat = 2.3f;

        System.out.println ("birSayıTutan.sayıInt = " + birSayıTutan.sayıInt);
        System.out.println ("birSayıTutan.sayıFloat = " + birSayıTutan.sayıFloat);
    } // main(..) metodu sonu...
} // J2cq_4 sınıfı sonu...

/* Çıktı:
**  >java J2cq_4  **
birSayıTutan.sayıInt = 1
birSayıTutan.sayıFloat = 2.3
*/