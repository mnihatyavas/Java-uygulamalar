// J2b_1.java: ArithmeticDemo (AritmetikG�steri) �rne�i.

class J2b_1 {
    public static void main (String[] args) {
        int sonu� = 1 + 2; // �imdi sonu� 3't�r...
        System.out.println ("1 + 2 = [" + sonu� + "]");
        int �nceki_sonu� = sonu�;

        sonu� = sonu� - 1; // �imdi sonu� 2'dir...
        System.out.println (�nceki_sonu� + " - 1 = [" + sonu� + "]");
        �nceki_sonu� = sonu�;

        sonu� = sonu� * 2; // �imdi sonu� 4't�r...
        System.out.println (�nceki_sonu� + " * 2 = [" + sonu� + "]");
        �nceki_sonu� = sonu�;

        sonu� = sonu� / 2; // �imdi sonu� 2'dir...
        System.out.println (�nceki_sonu� + " / 2 = [" + sonu� + "]");
        �nceki_sonu� = sonu�;

        sonu� = sonu� + 8; // �imdi sonu� 10'dur...
        System.out.println (�nceki_sonu� + " + 8 = [" + sonu� + "]");
        �nceki_sonu� = sonu�;

        sonu� = sonu� % 7; // �imdi sonu� 3't�r...
        System.out.println (�nceki_sonu� + " % 7 = [" + sonu� + "]");
    } // main(..) metodu sonu...
} // J2b_1

/* ��kt�:
**  >java J2b_1 **
1 + 2 = [3]
3 - 1 = [2]
2 * 2 = [4]
4 / 2 = [2]
2 + 8 = [10]
10 % 7 = [3]
*/