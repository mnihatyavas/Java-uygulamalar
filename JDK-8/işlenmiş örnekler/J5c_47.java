// J5c_47.java: SimpleTableDemo (BasitTabloGösterisi) örneði.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class J5c_47 extends JPanel {
    private static boolean yazýlsýnMý = false;
        String[] tabloBaþlýklarý = {"TC No'su", "Adý ve Soyadý", "Ýkamet Þehri", "Doðum Tarihi", "Vefat Tarihi", "2018'de Yaþý", "Evli Mi", "Mezuniyet"};

    public J5c_47() {// Kurucu...
        super (new GridLayout (1,0));

        Object[][] tabloVerileri = {
            {new Long (Long.valueOf ("43882313080")), "Haným Amanat Yavaþ", "Yeþilyurt", "1 Ekim 1932", "4 Mayýs 2014", new Integer (82), new Boolean (true), "Yok"},
            {new Long (Long.valueOf ("43888312872")), "Memet Yavaþ", "Yeþilyurt", "1 Mart 1933", "30 Mart 2018", new Integer (85), new Boolean (true), "Ýlkokul"},
            {new Long (Long.valueOf ("18626504192")), "Hatice Yavaþ Kaçar", "Bursa", "1 Mart 1953", "Yok", new Integer (65), new Boolean (true), "Ýlkokul"},
            {new Long (Long.valueOf ("21290066668")), "Süheyla Yavaþ Özbay", "Yakýnca", "10 Mart 1954", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("13619672094"), "Zeliha Yavaþ Candan", "Antalya", "7 Aðustos 1954", "Yok", new Integer (64), new Boolean (true), "Ýlkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yavaþ", "Mersin", "17 Nisan 1957", "Yok", new Integer (61), new Boolean (false), "Üniversite"},
            {new Long ("14270300692"), "Songül Yavaþ Göktürk", "Malatya", "14 Mayýs 1959", "Yok", new Integer (59), new Boolean (true), "Üniversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yavaþ", "Bursa", "27 Nisan 1961", "Yok", new Integer (57), new Boolean (false), "Üniversite"},
            {new Long ("43873313372"), "Sevim Yavaþ", "Bursa", "1 Aðustos 1963", "Yok", new Integer (55), new Boolean (false), "Üniversite"}
        }; // Obj.. ifadesi sonu...

        final JTable tablo = new JTable (tabloVerileri, tabloBaþlýklarý);
        tablo.setPreferredScrollableViewportSize (new Dimension (1000, 80));
        tablo.setFillsViewportHeight (true); // Tablo verilerine yön oklarýyla gelip deðiþtirebilirsin...
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        if (yazýlsýnMý) {
            tablo.addMouseListener (new MouseAdapter() {// Eðer "yazýlsýnMI=true" ise tablo verisini her týklamayla yazdýrýrsýn...
                public void mouseClicked (MouseEvent olay) {tabloVerileriniYaz (tablo);}
            }); // tab.. ifadesi sonu...
        } // if kararý sonu...

        // Tabloyu sarmalayan kaydýrma panosunu yaratýp penceremize ekleyelim...
        JScrollPane kaydýrmaPanosu = new JScrollPane (tablo);
        add (kaydýrmaPanosu);
    } // J5c_47() kurucusu sonu...

    private void tabloVerileriniYaz (JTable tablo) {
        int satýrSayýsý = tablo.getRowCount();
        int sütunSayýsý = tablo.getColumnCount();
        javax.swing.table.TableModel model = tablo.getModel();

        System.out.print ("Tablo Ýçerik Deðerleri:\nSýraNo ");
        for (int j=0; j < sütunSayýsý; j++) System.out.print (tabloBaþlýklarý[j] + "  ");
        System.out.println ("\n======================================================================================================");
        for (int i=0; i < satýrSayýsý; i++) {
            System.out.print ((i+1) + ".satýr:");
            for (int j=0; j < sütunSayýsý; j++) System.out.print ("  " + model.getValueAt (i, j));
            System.out.println();
        } // dýþ-for döngüsü sonu...
        System.out.println ("======================================================================================================");
    } // tabloVerileriniYaz(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Basit Tablo Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_47 yeniÝçerikPanosu = new J5c_47(); // Kurucuyu çaðýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (200,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        yazýlsýnMý = args.length > 0 ? true : false;

        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_47 sýnýfý sonu...

/* Çýktý:
**  >java J5c_47 yaz  **
Tablo Ýçerik Deðerleri:
SýraNo TC No'su           Adý ve Soyadý        Ýkamet Þehri  Doðum Tarihi  Vefat Tarihi 2018'de Yaþý  Evli Mi  Mezuniyet
========================================================================================
1.satýr:  43882313080  Haným Amanat Yavaþ   Yeþilyurt   1 Ekim 1932      4 Mayýs 2014   82  true    Yok
2.satýr:  43888312872  Memet Yavaþ                Yeþilyurt   1 Mart 1933       30 Mart 2018    85  true    Ýlkokul
3.satýr:  18626504192  Hatice Yavaþ Kaçar       Bursa      1 Mart 1953        Yok                    65  true    Ýlkokul
4.satýr:  21290066668  Süheyla Yavaþ Özbay   Yakýnca 10 Mart 1954        Yok                    64  true    Ýlkokul
5.satýr:  13619672094  Zeliha Yavaþ Candan    Antalya    7 Aðustos 1954  Yok                    64  true    Ýlkokul
6.satýr:  43879313154  Mahmut Nihat Yavaþ     Mersin   17 Nisan 1957       Yok                    61  false  Üniversite
7.satýr:  14270300692  Songül Yavaþ Göktürk  Malatya  14 Mayýs 1959      Yok                    59  true    Üniversite
8.satýr:  43876313218  Mustafa Nedim Yavaþ   Bursa     27 Nisan 1961      Yok                    57  false  Üniversite
9.satýr:  43873313372  Sevim Yavaþ                  Bursa      1 Aðustos 1963  Yok                    55  false   Üniversite
===========================================================================================
*/