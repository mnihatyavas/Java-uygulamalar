// J2a_1x.java: Bicycle (Bisiklet) alt-örneði.

class J2a_1x {
    int açý = 90;
    int hýz = 0;
    int vites = 1;

    void açýyýDeðiþtir (int yeniDeðer) {açý = yeniDeðer;}
    void vitesDeðiþtir (int yeniDeðer) {vites = yeniDeðer;}
    void hýzlan (int artýþ) {hýz += artýþ;}
    void frenle (int azalýþ) {hýz -= azalýþ;}
    void durumlarýYaz() {System.out.println ("Vücut açýsý: [" + açý + "] derece\nHýz: [" + hýz + "] km/s\nVites: [" + vites + "]\n");}
} // J2a_1x sýnýfý sonu...