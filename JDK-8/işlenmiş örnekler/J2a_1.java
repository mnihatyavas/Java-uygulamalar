// J2a_1.java: BicycleDemo (BisikletGösterisi) örneði.

class J2a_1 {
    public static void main (String[] args) {
        // 3 farklý bisiklet nesnesi yaratalým...
        J2a_1x sürüþ1 = new J2a_1x(); // Ýlk bisiklet...
        J2a_1x sürüþ2 = new J2a_1x(); // Ýkinci bisiklet...
        J2a_1x sürüþ3 = new J2a_1x(); // Üçüncü bisiklet...

        // Metodlarý 3 nesneye de uygulayalým...
        sürüþ1.açýyýDeðiþtir (50);
        sürüþ1.hýzlan (20);
        sürüþ1.vitesDeðiþtir (10); // Vites: 1-21...
        sürüþ1.durumlarýYaz();

        sürüþ2.açýyýDeðiþtir (45);
        sürüþ2.hýzlan (30);
        sürüþ2.vitesDeðiþtir (10);
        sürüþ2.açýyýDeðiþtir (30);
        sürüþ2.hýzlan (40);
        sürüþ2.vitesDeðiþtir (15);
        sürüþ2.durumlarýYaz();

        sürüþ3.açýyýDeðiþtir (20);
        sürüþ3.hýzlan (100);
        sürüþ3.vitesDeðiþtir (21);
        sürüþ3.durumlarýYaz();
    } // main(..) metodu sonu...
} // J2a_1 sýnýfý sonu...

/* Çýktý:
**  >java J2a_1  **
Vücut açýsý: [50] derece
Hýz: [20] km/s
Vites: [10]

Vücut açýsý: [30] derece
Hýz: [70] km/s
Vites: [15]

Vücut açýsý: [20] derece
Hýz: [100] km/s
Vites: [21]
*/