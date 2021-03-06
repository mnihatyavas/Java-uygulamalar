// J5c_53.java: SpinnerDemo3 (Saya�G�sterisi3) �rne�i.

/* �ncekilerden fark�: Herbir Ay/Y�l saya� de�i�iminde o sayac�n
 * mevsimsel renginin de de�i�mesidir.
 * Gereken java dosyalar�:
 *   J5c_51x1.java=CyclingSpinnerListModel.java
 *   J5c_51x2.java=SpringUtilities.java
 */
import java.awt.Color;
import java.awt.Container;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.JComponent;
import javax.swing.BorderFactory;
import javax.swing.SpringLayout;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerDateModel;

import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.util.Calendar;
import java.util.Date;
import java.text.DateFormatSymbols;

public class J5c_53 extends JPanel implements ChangeListener {
    protected Calendar takvim;
    protected JSpinner tarihSayac�;

    protected Color �LKBAHAR_RENG� = new Color (0, 204, 51); // Hafif mavimtrak ye�il...
    protected Color YAZ_RENG� = Color.RED; // K�rm�z�...
    protected Color SONBAHAR_RENG� = new Color (255, 153, 0); // A��k sar�mtrak k�rm�z�...
    protected Color KI�_RENG� = Color.CYAN; // Camg�be�i a��k mavisi...

    public J5c_53 (boolean ayY�l�EtkilesinMi) {// Kurucu...
        super (new SpringLayout());

        String[] saya�Adlar� = {"Ay ad�: ", "Y�l rakam�: ", "Ay/Y�l rakam�: "};
        int saya�Say�s� = saya�Adlar�.length;
        takvim = Calendar.getInstance();
        JFormattedTextField bi�imliMetinSat�r� = null;

        // �lk (ay adl�) saya� ad� ve saya� �iftini yarat�p pencereye ekleyelim...
        String[] ayAdlar� = ayAdlar�n�Al();
        SpinnerListModel ayAd�Modeli = null;
        if (ayY�l�EtkilesinMi) // Ay->y�l ba�lant�l� kendi modelimizi kullanaca��z...
            ayAd�Modeli = new J5c_51x1 (ayAdlar�);
        else // Ay->y�l ba�lant�s�z standart modeli kullanaca��z...
            ayAd�Modeli = new SpinnerListModel (ayAdlar�);

        JSpinner saya� = ba�l�kl�Sayac�Ekle (this, saya�Adlar�[0], ayAd�Modeli);
        // Sayac�n ay ad�n� sa�a hizalayal�m...
        bi�imliMetinSat�r� = saya�MetniniAl (saya�);
        if (bi�imliMetinSat�r� != null ) {
            //bi�imliMetinSat�r�.setColumns (8); // �htiyac�m�zdan biraz fazla yer ay�ral�m...
            bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.RIGHT);
        } // if karar� sonu...
        bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        bi�imliMetinSat�r�.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // �kinci (y�l rakaml�) saya� ad� ve saya� �iftini yarat�p pencereye ekleyelim...
        int �imdikiY�l = takvim.get (Calendar.YEAR);
        SpinnerModel y�lRakam�Modeli = new SpinnerNumberModel (
                �imdikiY�l, // Ba�lang�� de�eri...
                �imdikiY�l - 100, // Enk���k de�er...
                �imdikiY�l + 100, // Enb�y�k de�er...
                1); // Art�� de�eri...
        // E�er devridaim yap�yorsak bu modeli ayAd�Modeli'ne ba��nt�layal�m...
        if (ayAd�Modeli instanceof J5c_51x1) ((J5c_51x1)ayAd�Modeli).setLinkedModel (y�lRakam�Modeli);
        saya� = ba�l�kl�Sayac�Ekle (this, saya�Adlar�[1], y�lRakam�Modeli);
        // 4 haneli y�l rakam�n�n binler ayrac� olmamal�...
        saya�.setEditor (new JSpinner.NumberEditor (saya�, "#"));
        // Sayac�n y�l rakam�n�n sa�a hizal� oldu�undan emin olal�m...
        bi�imliMetinSat�r� = saya�MetniniAl (saya�);
        if (bi�imliMetinSat�r� != null ) {
            //bi�imliMetinSat�r�.setColumns (8); // �htiyac�m�zdan biraz fazla yer ay�ral�m...
            bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.RIGHT);
        } // if karar� sonu...
        bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        bi�imliMetinSat�r�.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        // ���nc� (ay/y�l rakaml�) saya� ad� ve saya� �iftini yarat�p pencereye ekleyelim...
        Date �imdikiTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, -100);
        Date enk���kTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, 200);
        Date enb�y�kTarih = takvim.getTime();
        SpinnerDateModel ayY�lRakaml�TarihModeli = new SpinnerDateModel (
                �imdikiTarih, // Ba�lang�� tarihi...
                enk���kTarih, // -100 y�l...
                enb�y�kTarih, // +100 y�l...
                Calendar.YEAR); // Y�l art���...
        tarihSayac� = saya� = ba�l�kl�Sayac�Ekle (this, saya�Adlar�[2], ayY�lRakaml�TarihModeli);
        saya�.setEditor (new JSpinner.DateEditor (saya�, "MM/yyyy"));
        // Ay/y�l rakamlar�n� sayac�n bi�imli metin sat�r�nda sa�a yaslayal�m...
        bi�imliMetinSat�r� = saya�MetniniAl (saya�);
        if (bi�imliMetinSat�r� != null ) {
            bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.RIGHT);
            bi�imliMetinSat�r�.setBorder (BorderFactory.createEmptyBorder (1,1,1,3));
        } // if karar� sonu...
        saya�.setBorder (BorderFactory.createLineBorder (Color.BLACK, 1));
        // Ay/y�l yaz� rengini mevsimlere g�re de�i�tirelim...
        mevsimRenginiKoy (ayY�lRakaml�TarihModeli.getDate());
        //bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...

        // Saya� de�i�ikli�ini dinleyelim...
        tarihSayac�.addChangeListener (this);

        // Pencere komponentlerini �zgara formunda SpringLayout'la serimleyelim...
        J5c_51x2.kesifIzgaraYap (
                this, // Penceremiz...
                saya�Say�s�, 2, // Sat�r ve s�tun say�s�...
                10, 10, // �lk ba�l�kl� sayac�n penceredeki konumu...
                6, 10); // Komponenler aras� tampon (sol-sa�, �st-alt)...

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_53(..) kurucusu sonu...

    // DateFormatSymbols()'un son endeks i�eri�i bo�sa, o eleman� silelim...
    static protected String[] ayAdlar�n�Al() {
        String[] aylar = new DateFormatSymbols().getMonths();
        int sonEndeks = aylar.length - 1;

        if (aylar[sonEndeks] == null || aylar[sonEndeks].length() <= 0) {// Son endeks bo� ve silinmeli...
            String[] ayAdlar� = new String[sonEndeks];
            System.arraycopy (aylar, 0, ayAdlar�, 0, sonEndeks);
            return ayAdlar�;
        }else return aylar; // Son endeks bo� de�il, ay ad� i�eriyor; kals�n...
    } // ayAdlar�n�Al() metodu sonu...

    static protected JSpinner ba�l�kl�Sayac�Ekle (Container kab, String ba�l�k, SpinnerModel saya�Modeli) {
        JLabel etiket = new JLabel (ba�l�k);
        kab.add (etiket);
        etiket.setForeground (Color.white);

        JSpinner saya� = new JSpinner (saya�Modeli);
        etiket.setLabelFor (saya�);
        kab.add (saya�);

        return saya�;
    } // ba�l�kl�Sayac�Ekle(..) metodu sonu...

    /* Edit�r� kulland��� bi�imli metin sat�r� i�eri�ini veya
     * edit�r JSpinner.DefaultEdit�r soyundan de�ilse null d�nd�r�r.*/
    public JFormattedTextField saya�MetniniAl (JSpinner saya�) {
        JComponent edit�r = saya�.getEditor();
        if (edit�r instanceof JSpinner.DefaultEditor) return ((JSpinner.DefaultEditor)edit�r).getTextField();
        else {System.err.println ("Hatal� edit�r �e�idi: [" + saya�.getEditor().getClass() + "] edit�r� JSpinner.DefaultEditor soyundan de�il!"); return null;}
    } // saya�MetniniAl(..) metodu sonu...

    // Tip de�i�kenlerinde tan�mlanan kuzey yar�mk�re geleneksel mevsim renklerini ay/y�l rakamlar�na koyar...
    protected void mevsimRenginiKoy (Date tarih) {
        takvim.setTime (tarih);
        int ayRakam� = takvim.get (Calendar.MONTH);
        JFormattedTextField bi�imliMetinSat�r� = saya�MetniniAl (tarihSayac�);
        if (bi�imliMetinSat�r� == null) return;

        switch (ayRakam�) {
            case 2: 
            case 3:
            case 4: // �lkbahar (Mart, Nisan, May�s)...
                     bi�imliMetinSat�r�.setForeground (�LKBAHAR_RENG�);
                     break;
            case 5: case 6: case 7: // Yaz (Haziran, Temmuz, A�ustos)...
                     bi�imliMetinSat�r�.setForeground (YAZ_RENG�);
                     break;
            case 8: case 9: case10: // Sonbahar (Eyl�l, Ekim, Kas�m)...
                     bi�imliMetinSat�r�.setForeground (SONBAHAR_RENG�);
                     break;
            default: // Kalan/K�� (Aral�k, Ocak, �ubat)...
                     bi�imliMetinSat�r�.setForeground (KI�_RENG�);
        } // swith(..) karar blo�u sonu...
    } // mevsimRenginiKoy(..) metodu sonu...

    // Ay/y�l de�i�ikli�ini mevsim rengi kontrol switch blo�una test ettirir...
    public void stateChanged (ChangeEvent olay) {
        SpinnerModel ayY�lRakaml�TarihModeli = tarihSayac�.getModel();
        if (ayY�lRakaml�TarihModeli instanceof SpinnerDateModel)
            mevsimRenginiKoy (((SpinnerDateModel)ayY�lRakaml�TarihModeli).getDate());
    } //stateChanged(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Saya� G�sterisi 3");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_53 (true)); // "ayY�l�EtkilesinMi=true" ile kurucuyu �a��r...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                // Koyu yaz� fonlu metal kullan�m� kapat�l�yor...
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
        }}); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_53 s�n�f� sonu...