// J2e_6x1.java: Card (Kart) aray�z� alt-�rne�i.

public interface J2e_6x1 extends Comparable<J2e_6x1> {
    public enum Tak�m { 
        KARO (1, "Karo"), 
        MA�A (2, "Ma�a"), 
        KUPA (3, "Kupa"), 
        S�NEK (4, "Sinek");

        private final int de�er;
        private final String metin;
        Tak�m (int de�er, String metin) {
            this.de�er = de�er;
            this.metin = metin;
        } // Tak�m(..) enum kurucu sonu...

        public int de�erAl() {return de�er;}
        public String metinAl() {return metin;}
    } // Tak�m enum sonu...

    public enum Say� {
        AS (1, "As"), // Dikkat edilirse AS burda 1 de�il 14 de�erli!.. Biz yine 1 yapal�m...
        �K� (2, "�ki"),
        �� (3, "��"), 
        D�RT (4, "D�rt"), 
        BE� (5, "Be�"), 
        ALTI (6, "Alt�"), 
        YED� (7, "Yedi"),
        SEK�Z (8, "Sekiz"), 
        DOKUZ (9, "Dokuz"), 
        ON (10, "On"), 
        O�LAN (11, "O�lan"),
        KIZ (12, "K�z"), 
        PAPAZ (13, "Papaz");

        private final int de�er;
        private final String metin;
        Say� (int de�er, String metin) {
            this.de�er = de�er;
            this.metin = metin;
        } // Say�(..) enum kurucu sonu...

        public int de�erAl() {return de�er;}
        public String metinAl() {return metin;}
    } // Say� enum sonu...

    public J2e_6x1.Tak�m tak�mAl(); // g�vdesiz soyut metodlar...
    public J2e_6x1.Say� say�Al();
    public int k�ymaKodu();
} // J2e_6x1 aray�z� sonu...