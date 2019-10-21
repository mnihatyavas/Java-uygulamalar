// J3b_12a.java: Format (bi�imleme) �rne�i.

public class J3b_12a {
    public static void main (String[] args) {
        System.out.format ("1.bi�imleme: [%f]%n2.bi�imleme: [%1$+020.10f]%n3.bi�imleme: [%1$+030.20f]%n", Math.PI);
    } // main(..) metodu sonu...
} // J3b_12a s�n�f� sonu...

/* ��kt�:
**  >java J3b_12a  **
1.bi�imleme: [3,141593]
2.bi�imleme: [+00000003,1415926536]
3.bi�imleme: [+00000003,14159265358979300000]
*/