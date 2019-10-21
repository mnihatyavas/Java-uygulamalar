// J2e_6x1.java: Card (Kart) arayüzü alt-örneði.

public interface J2e_6x1 extends Comparable<J2e_6x1> {
    public enum Takým { 
        KARO (1, "Karo"), 
        MAÇA (2, "Maça"), 
        KUPA (3, "Kupa"), 
        SÝNEK (4, "Sinek");

        private final int deðer;
        private final String metin;
        Takým (int deðer, String metin) {
            this.deðer = deðer;
            this.metin = metin;
        } // Takým(..) enum kurucu sonu...

        public int deðerAl() {return deðer;}
        public String metinAl() {return metin;}
    } // Takým enum sonu...

    public enum Sayý {
        AS (1, "As"), // Dikkat edilirse AS burda 1 deðil 14 deðerli!.. Biz yine 1 yapalým...
        ÝKÝ (2, "Ýki"),
        ÜÇ (3, "Üç"), 
        DÖRT (4, "Dört"), 
        BEÞ (5, "Beþ"), 
        ALTI (6, "Altý"), 
        YEDÝ (7, "Yedi"),
        SEKÝZ (8, "Sekiz"), 
        DOKUZ (9, "Dokuz"), 
        ON (10, "On"), 
        OÐLAN (11, "Oðlan"),
        KIZ (12, "Kýz"), 
        PAPAZ (13, "Papaz");

        private final int deðer;
        private final String metin;
        Sayý (int deðer, String metin) {
            this.deðer = deðer;
            this.metin = metin;
        } // Sayý(..) enum kurucu sonu...

        public int deðerAl() {return deðer;}
        public String metinAl() {return metin;}
    } // Sayý enum sonu...

    public J2e_6x1.Takým takýmAl(); // gövdesiz soyut metodlar...
    public J2e_6x1.Sayý sayýAl();
    public int kýymaKodu();
} // J2e_6x1 arayüzü sonu...