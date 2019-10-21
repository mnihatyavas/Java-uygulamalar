// J2c_7x2.java: Rectangle (Dikdörtgen) alt-örneði.

public class J2c_7x2 {
    public int geniþlik = 0;
    public int yükseklik = 0;
    public J2c_7x1 orijin;

    // 4 farklý argümanlý kurucu...
    public J2c_7x2() {orijin = new J2c_7x1 (0, 0);}
    public J2c_7x2 (J2c_7x1 nokta) {orijin = nokta;}
    public J2c_7x2 (int en, int boy) {orijin = new J2c_7x1 (0, 0); geniþlik = en; yükseklik = boy;}
    public J2c_7x2 (J2c_7x1 nokta, int en, int boy) {orijin = nokta; geniþlik = en; yükseklik = boy;}

    // Dikdörtgeni baþka konuma taþýma metodu...
    public void taþý (int x, int y) {orijin.x = x; orijin.y = y;}

    // Dikdörtgenin alanýný hesaplama metodu...
    public int alanýAl() {return (geniþlik * yükseklik);}
} // J2c_7x2 sýnýfý sonu...