// J2b_11a.java: DoWhileDemo (DoWhileGösterimi) örneði.

class J2b_11a {
    public static void main (String[] args) {
        int say = 1;

        do {
            System.out.println ("do..while döngü tekrarý: " + say);
            say++;
        } while (say < 11);
    } // main(..) metodu sonu...
} // J2b_11a sýnýfý sonu...

/* Çýktý:
**  >java J2b_11a  **
do..while döngü tekrarý: 1
do..while döngü tekrarý: 2
do..while döngü tekrarý: 3
do..while döngü tekrarý: 4
do..while döngü tekrarý: 5
do..while döngü tekrarý: 6
do..while döngü tekrarý: 7
do..while döngü tekrarý: 8
do..while döngü tekrarý: 9
do..while döngü tekrarý: 10
*/