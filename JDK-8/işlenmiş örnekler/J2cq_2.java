// J2cq_2.java: DataStructure (VeriYapýsý) örneði.

public class J2cq_2 {
    private final static int EBAT = 20;
    private int[] dizinÝnt = new int[EBAT];

    public J2cq_2() {for (int i = 0; i < EBAT; i++) dizinÝnt[i] = i*i;} // kurucu...
    public int ebat() {return EBAT;}
    public int al (int endeks) {return dizinÝnt[endeks];}
    
    interface Tarayýcý extends java.util.Iterator<Integer> {}
    
    private class ÇiftleriTarayýcý implements Tarayýcý {
        private int sonrakiEndeks = 0;

        public boolean hasNext() {return (sonrakiEndeks <= EBAT - 1);}        

        public Integer next() {
            Integer deðer = Integer.valueOf (dizinÝnt[sonrakiEndeks]);
            sonrakiEndeks += 2;
            return deðer;
        } // next() metodu sonu...
    } // ÇiftleriTarayýcý sýnýfý sonu...

    public Tarayýcý alÇiftleriTarayýcý() {return new ÇiftleriTarayýcý();}

    public void çiftleriYaz() {
        Tarayýcý tarayýcý = alÇiftleriTarayýcý();
        while (tarayýcý.hasNext()) {System.out.print (tarayýcý.next() + " ");}
        System.out.println();
    } // çiftleriYaz() metodu sonu...

    public void yaz (Tarayýcý tarayýcý) {
        while (tarayýcý.hasNext()) {System.out.print (tarayýcý.next() + " ");}
        System.out.println();
    } // yaz(..) metodu sonu...

    public void yaz (java.util.function.Function<Integer, Boolean> fonksiyon) {
        for (int i = 0; i < EBAT; i++) if (fonksiyon.apply(i)) System.out.print (dizinÝnt[i] + " ");
        System.out.println();
    } // yaz(..) metodu sonu...

    public static Boolean çiftEndeksMi (Integer endeks) {
        if (endeks % 2 == 0) return Boolean.TRUE;
        else return Boolean.FALSE;
    } // çiftEndeksMi(..) metodu sonu...

    public static Boolean tekEndeksMi (Integer endeks) {
        if (endeks % 2 == 0) return Boolean.FALSE;
        else return Boolean.TRUE;
    } // tekEndeksMi(..) metodu sonu...

    public static void main (String s[]) {
        J2cq_2 veriYapýsý = new J2cq_2();

        System.out.println ("çiftleriYaz()");
        veriYapýsý.çiftleriYaz();

        System.out.println ("\nTarayýcý alÇiftleriTarayýcý() metoduyla yaz(Tarayýcý)");
        veriYapýsý.yaz (veriYapýsý.alÇiftleriTarayýcý());

        System.out.println("\nAnonim sýnýflý ve tek endeksli yaz(Tarayýcý)");
        veriYapýsý.yaz (
            new J2cq_2.Tarayýcý() {
                private int sonrakiEndeks = 1;
                public boolean hasNext() {return (sonrakiEndeks <= veriYapýsý.ebat() - 1);}
                public Integer next() {
                    int deðer = veriYapýsý.al (sonrakiEndeks);
                    sonrakiEndeks += 2;
                    return deðer;
        }});

        System.out.println ("\nLambda ifadesiyle çiftleri ve tekleri yaz(Function)");
        veriYapýsý.yaz (endeks -> {
            if (endeks % 2 == 0) return Boolean.TRUE;
            else return Boolean.FALSE;
        });
        veriYapýsý.yaz (endeks -> {
            if (endeks % 2 == 0) return Boolean.FALSE;
            return Boolean.TRUE;
        });
        
        System.out.println ("\nMetod referans'la çiftleri ve tekleri yaz(Function)");
        veriYapýsý.yaz (J2cq_2::çiftEndeksMi);
        veriYapýsý.yaz (J2cq_2::tekEndeksMi);
    } // main(..) metodu sonu...
} // J2cq_2 sýnýfý sonu...

/* Çýktý:
**  >java J2cq_2  **
çiftleriYaz()
0 4 16 36 64 100 144 196 256 324

Tarayýcý alÇiftleriTarayýcý() metoduyla yaz(Tarayýcý)
0 4 16 36 64 100 144 196 256 324

Anonim sýnýflý ve tek endeksli yaz(Tarayýcý)
1 9 25 49 81 121 169 225 289 361

Lambda ifadesiyle çiftleri ve tekleri yaz(Function)
0 4 16 36 64 100 144 196 256 324
1 9 25 49 81 121 169 225 289 361

Metod referans'la çiftleri ve tekleri yaz(Function)
0 4 16 36 64 100 144 196 256 324
1 9 25 49 81 121 169 225 289 361
*/