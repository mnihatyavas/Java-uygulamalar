// J2c_7x2.java: Rectangle (Dikd�rtgen) alt-�rne�i.

public class J2c_7x2 {
    public int geni�lik = 0;
    public int y�kseklik = 0;
    public J2c_7x1 orijin;

    // 4 farkl� arg�manl� kurucu...
    public J2c_7x2() {orijin = new J2c_7x1 (0, 0);}
    public J2c_7x2 (J2c_7x1 nokta) {orijin = nokta;}
    public J2c_7x2 (int en, int boy) {orijin = new J2c_7x1 (0, 0); geni�lik = en; y�kseklik = boy;}
    public J2c_7x2 (J2c_7x1 nokta, int en, int boy) {orijin = nokta; geni�lik = en; y�kseklik = boy;}

    // Dikd�rtgeni ba�ka konuma ta��ma metodu...
    public void ta�� (int x, int y) {orijin.x = x; orijin.y = y;}

    // Dikd�rtgenin alan�n� hesaplama metodu...
    public int alan�Al() {return (geni�lik * y�kseklik);}
} // J2c_7x2 s�n�f� sonu...