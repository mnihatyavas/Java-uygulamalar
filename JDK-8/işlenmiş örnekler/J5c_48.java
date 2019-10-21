// J5c_48.java: SimpleTableSelectionDemo (BasitTabloSe�imiG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

// �nceki �rnekten (J5c_47/SimpleTableDemo) fark� se�ilen tablo sat�r�n�n raporlanmas�d�r...
public class J5c_48 extends JPanel {
    private static boolean YAZILSINMI = false;
    private boolean KOLON_SE��LS�NM� = true;
    private boolean SATIR_SE��LS�NM� = true;

    String[] tabloBa�l�klar� = {"TC No'su", "Ad� ve Soyad�", "�kamet �ehri", "Do�um Tarihi", "Vefat Tarihi", "2018'de Ya��", "Evli Mi", "Mezuniyet"};

    public J5c_48() {// Kurucu...
        super (new GridLayout (1,0));

        Object[][] tabloVerileri = {
            {new Long (Long.valueOf ("43882313080")), "Han�m Amanat Yava�", "Ye�ilyurt", "1 Ekim 1932", "4 May�s 2014", new Integer (82), new Boolean (true), "Yok"},
            {new Long (Long.valueOf ("43888312872")), "Memet Yava�", "Ye�ilyurt", "1 Mart 1933", "30 Mart 2018", new Integer (85), new Boolean (true), "�lkokul"},
            {new Long (Long.valueOf ("18626504192")), "Hatice Yava� Ka�ar", "Bursa", "1 Mart 1953", "Yok", new Integer (65), new Boolean (true), "�lkokul"},
            {new Long (Long.valueOf ("21290066668")), "S�heyla Yava� �zbay", "Yak�nca", "10 Mart 1954", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("13619672094"), "Zeliha Yava� Candan", "Antalya", "7 A�ustos 1954", "Yok", new Integer (64), new Boolean (true), "�lkokul"},
            {new Long ("43879313154"), "Mahmut Nihat Yava�", "Mersin", "17 Nisan 1957", "Yok", new Integer (61), new Boolean (false), "�niversite"},
            {new Long ("14270300692"), "Song�l Yava� G�kt�rk", "Malatya", "14 May�s 1959", "Yok", new Integer (59), new Boolean (true), "�niversite"},
            {new Long ("43876313218"), "Mustafa Nedim Yava�", "Bursa", "27 Nisan 1961", "Yok", new Integer (57), new Boolean (false), "�niversite"},
            {new Long ("43873313372"), "Sevim Yava�", "Bursa", "1 A�ustos 1963", "Yok", new Integer (55), new Boolean (false), "�niversite"}
        }; // Obj.. ifadesi sonu...

        final JTable tablo = new JTable (tabloVerileri, tabloBa�l�klar�);
        tablo.setPreferredScrollableViewportSize (new Dimension (1000, 80));
        tablo.setFillsViewportHeight (true); // Tablo verilerine y�n oklar�yla gelip de�i�tirebilirsin...
        tablo.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        tablo.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        tablo.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        if (SATIR_SE��LS�NM�) {// true yapt�k...
            ListSelectionModel sat�rSe�imModeli = tablo.getSelectionModel();
            sat�rSe�imModeli.addListSelectionListener (new ListSelectionListener() {
                public void valueChanged (ListSelectionEvent olay) {
                    if (olay.getValueIsAdjusting()) return;

                    ListSelectionModel listeSe�imModeli = (ListSelectionModel)olay.getSource();
                    if (listeSe�imModeli.isSelectionEmpty()) System.out.println ("Hi�bir sat�r se�ilmedi.");
                    else {int se�ilenSat�r = listeSe�imModeli.getMinSelectionIndex();
                        System.out.println ("�imdi " + se�ilenSat�r + ".sat�r se�ildi.");
                    } // else karar� sonu...
                } // valueChanged(..) haz�r metodu sonu...
            }); // sat.. ifadesi sonu...
        }else tablo.setRowSelectionAllowed (false);

        if (KOLON_SE��LS�NM�) {// Bunu da true yapt�k...
            if (SATIR_SE��LS�NM�) tablo.setCellSelectionEnabled (true);
            // Her ikisi de true olunca tek bir cell/h�cre se�ilebilir demektir...
            tablo.setColumnSelectionAllowed (true);
            ListSelectionModel s�tunSe�imModeli = tablo.getColumnModel().getSelectionModel();
            s�tunSe�imModeli.addListSelectionListener(new ListSelectionListener() {
                public void valueChanged (ListSelectionEvent olay) {
                    if (olay.getValueIsAdjusting()) return;

                    ListSelectionModel listeSe�imModeli = (ListSelectionModel)olay.getSource();
                    if (listeSe�imModeli.isSelectionEmpty()) System.out.println ("Hi�bir s�tun se�ilmedi.");
                    else {int se�ilenS�tun = listeSe�imModeli.getMinSelectionIndex();
                        System.out.println ("�imdi " + se�ilenS�tun + ".s�tun se�ildi.");
                    } // else karar� sonu...
                } // valueChanged(..) haz�r metodu sonu...
            }); // s�t.. ifadesi sonu...
        } // d��-if karar� sonu...

        if (YAZILSINMI) {
            tablo.addMouseListener (new MouseAdapter() {// E�er "yaz�ls�nMI=true" ise tablo verisini her t�klamayla yazd�r�rs�n...
                public void mouseClicked (MouseEvent olay) {tabloVerileriniYaz (tablo);}
            }); // tab.. ifadesi sonu...
        } // if karar� sonu...

        // Tabloyu sarmalayan kayd�rma panosunu yarat�p penceremize ekleyelim...
        JScrollPane kayd�rmaPanosu = new JScrollPane (tablo);
        add (kayd�rmaPanosu);
    } // J5c_48() kurucusu sonu...

    private void tabloVerileriniYaz (JTable tablo) {
        int sat�rSay�s� = tablo.getRowCount();
        int s�tunSay�s� = tablo.getColumnCount();
        javax.swing.table.TableModel model = tablo.getModel();

        System.out.print ("\nTablo ��erik De�erleri:\nS�raNo ");
        for (int j=0; j < s�tunSay�s�; j++) System.out.print (tabloBa�l�klar�[j] + "  ");
        System.out.println ("\n======================================================================================================");
        for (int i=0; i < sat�rSay�s�; i++) {
            System.out.print ((i+1) + ".sat�r:");
            for (int j=0; j < s�tunSay�s�; j++) System.out.print ("  " + model.getValueAt (i, j));
            System.out.println();
        } // d��-for d�ng�s� sonu...
        System.out.println ("======================================================================================================\n");
    } // tabloVerileriniYaz(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Basit Tablo Se�imi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_48 yeni��erikPanosu = new J5c_48(); // Kurucuyu �a��r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (200,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        YAZILSINMI = args.length > 0 ? true : false;

        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_48 s�n�f� sonu...

/* ��kt�:
**  >java J5c_48 yaz  **
�imdi 0.s�tun se�ildi.
�imdi 1.s�tun se�ildi.
�imdi 2.s�tun se�ildi.
�imdi 7.sat�r se�ildi.
�imdi 2.s�tun se�ildi.

Tablo ��erik De�erleri:
S�raNo TC No'su           Ad� ve Soyad�        �kamet �ehri  Do�um Tarihi  Vefat Tarihi 2018'de Ya��  Evli Mi  Mezuniyet
========================================================================================
1.sat�r:  43882313080  Han�m Amanat Yava�   Ye�ilyurt   1 Ekim 1932      4 May�s 2014   82  true    Yok
2.sat�r:  43888312872  Memet Yava�                Ye�ilyurt   1 Mart 1933       30 Mart 2018    85  true    �lkokul
3.sat�r:  18626504192  Hatice Yava� Ka�ar       Bursa      1 Mart 1953        Yok                    65  true    �lkokul
4.sat�r:  21290066668  S�heyla Yava� �zbay   Yak�nca 10 Mart 1954        Yok                    64  true    �lkokul
5.sat�r:  13619672094  Zeliha Yava� Candan    Antalya    7 A�ustos 1954  Yok                    64  true    �lkokul
6.sat�r:  43879313154  Mahmut Nihat Yava�     Mersin   17 Nisan 1957       Yok                    61  false  �niversite
7.sat�r:  14270300692  Song�l Yava� G�kt�rk  Malatya  14 May�s 1959      Yok                    59  true    �niversite
8.sat�r:  43876313218  Mustafa Nedim Yava�   Bursa     27 Nisan 1961      Yok                    57  false  �niversite
9.sat�r:  43873313372  Sevim Yava�                  Bursa      1 A�ustos 1963  Yok                    55  false   �niversite
===========================================================================================

�imdi 5.sat�r se�ildi.
�imdi 3.s�tun se�ildi.
�imdi 4.sat�r se�ildi.
�imdi 3.sat�r se�ildi.
*/