// J2b_14.java: InstanceofDemo (InstanceOfG�sterimi) �rne�i.

interface Aray�z�m {}
class Ebeveyn {}
class �ocuk extends Ebeveyn implements Aray�z�m {}

class J2b_14 {
    public static void main (String[] args) {
        Ebeveyn nesne1 = new Ebeveyn();
        Ebeveyn nesne2 = new �ocuk();

        System.out.println ("nesne1 instanceof Ebeveyn? ["+ (nesne1 instanceof Ebeveyn) + "]");
        System.out.println("nesne1 instanceof �ocuk? ["+ (nesne1 instanceof �ocuk) + "]");
        System.out.println("nesne1 instanceof Aray�z�m? ["+ (nesne1 instanceof Aray�z�m) + "]");
        System.out.println("nesne2 instanceof Ebeveyn? ["+ (nesne2 instanceof Ebeveyn) + "]");
        System.out.println("nesne2 instanceof �ocuk? ["+ (nesne2 instanceof �ocuk) + "]");
        System.out.println("nesne2 instanceof Aray�z�m? ["+ (nesne2 instanceof Aray�z�m) + "]");
    } // main(..) metodu sonu...
} // J2b_14 s�n�f� sonu...

/* ��kt�:
**  >java J2b_14  **
nesne1 instanceof Ebeveyn? [true]
nesne1 instanceof �ocuk? [false]
nesne1 instanceof Aray�z�m? [false]
nesne2 instanceof Ebeveyn? [true]
nesne2 instanceof �ocuk? [true]
nesne2 instanceof Aray�z�m? [true]
*/