// J2b_11a.java: DoWhileDemo (DoWhileG�sterimi) �rne�i.

class J2b_11a {
    public static void main (String[] args) {
        int say = 1;

        do {
            System.out.println ("do..while d�ng� tekrar�: " + say);
            say++;
        } while (say < 11);
    } // main(..) metodu sonu...
} // J2b_11a s�n�f� sonu...

/* ��kt�:
**  >java J2b_11a  **
do..while d�ng� tekrar�: 1
do..while d�ng� tekrar�: 2
do..while d�ng� tekrar�: 3
do..while d�ng� tekrar�: 4
do..while d�ng� tekrar�: 5
do..while d�ng� tekrar�: 6
do..while d�ng� tekrar�: 7
do..while d�ng� tekrar�: 8
do..while d�ng� tekrar�: 9
do..while d�ng� tekrar�: 10
*/