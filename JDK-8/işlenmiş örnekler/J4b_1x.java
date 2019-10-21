// J4b_1x.java: WeatherData (HavaVerileri) alt-�rne�i.

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;

public class J4b_1x extends JPanel {
    public J4b_1x() {
        JLabel etiket = new JLabel ("D�nya �ehirleri F-S�cakl�klar� Tablosu");
        etiket.setBounds (10,10, 200,30); add (etiket);

        etiket = new JLabel ("Celcius = (5/9)*(F-32)");
        etiket.setBounds (10,60, 200,30); add (etiket);

        etiket = new JLabel ("Yakla��k C derece i�in F'den 30 d�� ve yar�s�n� al.");
        etiket.setBounds (10,100, 200,30); add (etiket);

        String[] s�tunAdlar� = {"�ehir", "F-S�cakl�k"};
        JTable tablo = new JTable (verileriAl(), s�tunAdlar�);

        tablo.setPreferredScrollableViewportSize (new Dimension (200, 150));
        tablo.setFillsViewportHeight (true);

        // Bir kayd�rma panosu yarat ve tabloyu ona ekle...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J4b_1x() kurucusu sonu...

    private Object[][]  verileriAl() {
        Object[][] veriler = {
            {"London", "55"},
            {"New York", "70"},
            {"Los Angeles", "80"},
            {"New Delhi", "95"},
            {"Tokyo", "60"},
            {"Seoul", "55"},
            {"Shanghai", "60"},
            {"Milan", "64"},
            {"Paris", "66"},
            {"Buenos Aires", "70"},
            {"Washington DC", "80"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Herhangibir �ehir", "68"},
            {"Herhangibir �ehir", "99"},
            {"Herhangibir �ehir", "60"},
            {"Herhangibir �ehir", "63"},
            {"Herhangibir �ehir", "65"},
            {"Herhangibir �ehir", "64"},
            {"Ottowa", "44"},
            {"Sacramento", "100"},
            }; // veriler nesnesi sonu...

        return veriler;
    } // verileriAl() metodu sonu...
} // J4b_1x s�n�f� sonu...