// J7b_1.java: Birthday (Do�umg�n�) �rne�i.

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

// Bug�nden, girece�iniz do�umg�n�ne kalan ay-g�n ve toplam g�n hesaplan�r...
public class J7b_1 {
    public static void main (String[] arg�man) {
        LocalDate do�umg�n� = LocalDate.of (1957, Month.APRIL, 17);
        if (arg�man.length == 3) do�umg�n� = LocalDate.of (
            Integer.valueOf (arg�man[0]), Month.valueOf (arg�man[1].toUpperCase()), Integer.valueOf (arg�man[2]));
        else System.out.println ("Do�um g�n�n� 3'l� arg�man olarak [1981 apr�l/may 12] �eklinde girebilirdin!");
        LocalDate bug�n = LocalDate.now();

        LocalDate gelecekDo�umg�n� = do�umg�n�.withYear (bug�n.getYear());

        // E�er do�umg�n� bu y�l ge�mi�se, gelecek do�umg�n� y�l� bir art�r�l�r...
        if (gelecekDo�umg�n�.isBefore (bug�n) || gelecekDo�umg�n�.isEqual (bug�n))
            gelecekDo�umg�n� = gelecekDo�umg�n�.plusYears (1);

        Period kalanAyG�n = Period.between (bug�n, gelecekDo�umg�n�);
        long kalanG�n = ChronoUnit.DAYS.between (bug�n, gelecekDo�umg�n�);
        System.out.println ("\nBug�n�n tarihi: " + bug�n + " ve gelecek do�um g�n�: " + gelecekDo�umg�n�);
        System.out.println ("Bir sonraki do�um g�n�ne hala " + kalanAyG�n.getMonths() + " ay ve " +
                kalanAyG�n.getDays() + " g�n kald�. (Toplam " + kalanG�n + " g�n)");
    } // main(..) metodu sonu...
} // J7b_1 s�n�f� sonu...

/* ��kt�:
**  >java J7b_1  **
Do�um g�n�n� 3'l� arg�man olarak [1981 apr�l/may 12] �eklinde girebilirdin!

Bug�n�n tarihi: 2018-10-27 ve gelecek do�um g�n�: 2019-04-17
Bir sonraki do�um g�n�ne hala 5 ay ve 21 g�n kald�. (Toplam 172 g�n)

**  >java J7b_1 1960 apr�l 25  ** TEKRAR-1
Bug�n�n tarihi: 2018-10-27 ve gelecek do�um g�n�: 2019-04-25
Bir sonraki do�um g�n�ne hala 5 ay ve 29 g�n kald�. (Toplam 180 g�n)

**  >java J7b_1 1960 december 1  ** TEKRAR-2
Bug�n�n tarihi: 2018-10-27 ve gelecek do�um g�n�: 2018-12-01
Bir sonraki do�um g�n�ne hala 1 ay ve 4 g�n kald�. (Toplam 35 g�n)
*/