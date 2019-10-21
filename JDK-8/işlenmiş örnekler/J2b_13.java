// J2b_13.java: IfElseDemo /IfElseGösterimi) örneði.

class J2b_13 {
    public static void main (String[] args) {
        int testNotu;
        char derece;

        try {testNotu = Integer.valueOf (args[0]);}catch (Exception ist) {testNotu = 50;}
        if (testNotu < 0 || testNotu > 100) testNotu = 50;

        if (testNotu >= 90) {derece = 'A';
        }else if (testNotu >= 80) {derece = 'B';
        }else if (testNotu >= 70) {derece = 'C';
        }else if (testNotu >= 60) {derece = 'D';
        }else {derece = 'F';
        } // if-else-if kararý sonu..

        System.out.println ("Test Notu: [" + testNotu + "] ve denk gelen Derecesi: [" + derece + "]");
    } // main(..) metodu sonu...
} // J2b_13 sýnýfý sonu...

/* Çýktý:
**  >java J2b_13  **
Test Notu: [50] ve denk gelen Derecesi: [F]

**  >java J2b_13 120  ** TEKRAR
Test Notu: [50] ve denk gelen Derecesi: [F]

**  >java J2b_13 -45  ** TEKRAR
Test Notu: [50] ve denk gelen Derecesi: [F]

**  >java J2b_13 AA  ** TEKRAR
Test Notu: [50] ve denk gelen Derecesi: [F]

**  >java J2b_13 87  ** TEKRAR
Test Notu: [87] ve denk gelen Derecesi: [B]
*/