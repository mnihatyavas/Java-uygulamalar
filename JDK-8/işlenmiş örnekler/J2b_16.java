// J2b_16.java: PrePostDemo (�nceSonraG�sterimi) �rne�i.

class J2b_16 {
    public static void main (String[] args) {
        int i = 3;
        i++;
        System.out.println (i); // 4

        ++i;			   
        System.out.println (i); // 5

        System.out.println (++i); // 6

        System.out.println (i++); // 6, ama i=7...

        System.out.println (i); // 7
    } // main(..) metodu sonu...
} // J2b_16 s�n�f� sonu...

/* ��kt�:
**  >java J2b_16  **
4
5
6
6
7
*/