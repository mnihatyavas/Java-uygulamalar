// J2b_14.java: InstanceofDemo (InstanceOfGösterimi) örneði.

interface Arayüzüm {}
class Ebeveyn {}
class Çocuk extends Ebeveyn implements Arayüzüm {}

class J2b_14 {
    public static void main (String[] args) {
        Ebeveyn nesne1 = new Ebeveyn();
        Ebeveyn nesne2 = new Çocuk();

        System.out.println ("nesne1 instanceof Ebeveyn? ["+ (nesne1 instanceof Ebeveyn) + "]");
        System.out.println("nesne1 instanceof Çocuk? ["+ (nesne1 instanceof Çocuk) + "]");
        System.out.println("nesne1 instanceof Arayüzüm? ["+ (nesne1 instanceof Arayüzüm) + "]");
        System.out.println("nesne2 instanceof Ebeveyn? ["+ (nesne2 instanceof Ebeveyn) + "]");
        System.out.println("nesne2 instanceof Çocuk? ["+ (nesne2 instanceof Çocuk) + "]");
        System.out.println("nesne2 instanceof Arayüzüm? ["+ (nesne2 instanceof Arayüzüm) + "]");
    } // main(..) metodu sonu...
} // J2b_14 sýnýfý sonu...

/* Çýktý:
**  >java J2b_14  **
nesne1 instanceof Ebeveyn? [true]
nesne1 instanceof Çocuk? [false]
nesne1 instanceof Arayüzüm? [false]
nesne2 instanceof Ebeveyn? [true]
nesne2 instanceof Çocuk? [true]
nesne2 instanceof Arayüzüm? [true]
*/