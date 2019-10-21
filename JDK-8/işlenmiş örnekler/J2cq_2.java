// J2cq_2.java: DataStructure (VeriYap�s�) �rne�i.

public class J2cq_2 {
    private final static int EBAT = 20;
    private int[] dizin�nt = new int[EBAT];

    public J2cq_2() {for (int i = 0; i < EBAT; i++) dizin�nt[i] = i*i;} // kurucu...
    public int ebat() {return EBAT;}
    public int al (int endeks) {return dizin�nt[endeks];}
    
    interface Taray�c� extends java.util.Iterator<Integer> {}
    
    private class �iftleriTaray�c� implements Taray�c� {
        private int sonrakiEndeks = 0;

        public boolean hasNext() {return (sonrakiEndeks <= EBAT - 1);}        

        public Integer next() {
            Integer de�er = Integer.valueOf (dizin�nt[sonrakiEndeks]);
            sonrakiEndeks += 2;
            return de�er;
        } // next() metodu sonu...
    } // �iftleriTaray�c� s�n�f� sonu...

    public Taray�c� al�iftleriTaray�c�() {return new �iftleriTaray�c�();}

    public void �iftleriYaz() {
        Taray�c� taray�c� = al�iftleriTaray�c�();
        while (taray�c�.hasNext()) {System.out.print (taray�c�.next() + " ");}
        System.out.println();
    } // �iftleriYaz() metodu sonu...

    public void yaz (Taray�c� taray�c�) {
        while (taray�c�.hasNext()) {System.out.print (taray�c�.next() + " ");}
        System.out.println();
    } // yaz(..) metodu sonu...

    public void yaz (java.util.function.Function<Integer, Boolean> fonksiyon) {
        for (int i = 0; i < EBAT; i++) if (fonksiyon.apply(i)) System.out.print (dizin�nt[i] + " ");
        System.out.println();
    } // yaz(..) metodu sonu...

    public static Boolean �iftEndeksMi (Integer endeks) {
        if (endeks % 2 == 0) return Boolean.TRUE;
        else return Boolean.FALSE;
    } // �iftEndeksMi(..) metodu sonu...

    public static Boolean tekEndeksMi (Integer endeks) {
        if (endeks % 2 == 0) return Boolean.FALSE;
        else return Boolean.TRUE;
    } // tekEndeksMi(..) metodu sonu...

    public static void main (String s[]) {
        J2cq_2 veriYap�s� = new J2cq_2();

        System.out.println ("�iftleriYaz()");
        veriYap�s�.�iftleriYaz();

        System.out.println ("\nTaray�c� al�iftleriTaray�c�() metoduyla yaz(Taray�c�)");
        veriYap�s�.yaz (veriYap�s�.al�iftleriTaray�c�());

        System.out.println("\nAnonim s�n�fl� ve tek endeksli yaz(Taray�c�)");
        veriYap�s�.yaz (
            new J2cq_2.Taray�c�() {
                private int sonrakiEndeks = 1;
                public boolean hasNext() {return (sonrakiEndeks <= veriYap�s�.ebat() - 1);}
                public Integer next() {
                    int de�er = veriYap�s�.al (sonrakiEndeks);
                    sonrakiEndeks += 2;
                    return de�er;
        }});

        System.out.println ("\nLambda ifadesiyle �iftleri ve tekleri yaz(Function)");
        veriYap�s�.yaz (endeks -> {
            if (endeks % 2 == 0) return Boolean.TRUE;
            else return Boolean.FALSE;
        });
        veriYap�s�.yaz (endeks -> {
            if (endeks % 2 == 0) return Boolean.FALSE;
            return Boolean.TRUE;
        });
        
        System.out.println ("\nMetod referans'la �iftleri ve tekleri yaz(Function)");
        veriYap�s�.yaz (J2cq_2::�iftEndeksMi);
        veriYap�s�.yaz (J2cq_2::tekEndeksMi);
    } // main(..) metodu sonu...
} // J2cq_2 s�n�f� sonu...

/* ��kt�:
**  >java J2cq_2  **
�iftleriYaz()
0 4 16 36 64 100 144 196 256 324

Taray�c� al�iftleriTaray�c�() metoduyla yaz(Taray�c�)
0 4 16 36 64 100 144 196 256 324

Anonim s�n�fl� ve tek endeksli yaz(Taray�c�)
1 9 25 49 81 121 169 225 289 361

Lambda ifadesiyle �iftleri ve tekleri yaz(Function)
0 4 16 36 64 100 144 196 256 324
1 9 25 49 81 121 169 225 289 361

Metod referans'la �iftleri ve tekleri yaz(Function)
0 4 16 36 64 100 144 196 256 324
1 9 25 49 81 121 169 225 289 361
*/