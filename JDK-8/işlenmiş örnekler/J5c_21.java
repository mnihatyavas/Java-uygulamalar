// J5c_21.java: GenealogyExample (�ecereK�t���) �rne�i.

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JScrollPane;

/* �u dosyalar kullan�lmaktad�r:
 *    Person.java/J5c_21x1.java
 *    GenealogyTree.java/J5c_21x2.java
 *    GenealogyModel.java/J5c_21x3
 */
public class J5c_21 extends JPanel implements ActionListener {
    J5c_21x2 �ecereA�ac�;
    private static String ATALARI_G�STER_KOMUTU = "atalar�G�ster";

    public J5c_21() {// Kurucu...
        super (new BorderLayout());

        // Paneli ikili radyo-d��mesiyle kural�m...
        JRadioButton t�reyenleriG�sterD��mesi = new JRadioButton ("T�reyenleri g�ster", true);
        final JRadioButton atalar�G�sterD��mesi = new JRadioButton ("Atalar� g�ster");

        ButtonGroup d��meGrubu = new ButtonGroup();
        d��meGrubu.add (t�reyenleriG�sterD��mesi);
        d��meGrubu.add (atalar�G�sterD��mesi);

        t�reyenleriG�sterD��mesi.addActionListener (this);
        atalar�G�sterD��mesi.addActionListener (this);
        atalar�G�sterD��mesi.setActionCommand (ATALARI_G�STER_KOMUTU);

        JPanel d��mePaneli = new JPanel();
        d��mePaneli.add (t�reyenleriG�sterD��mesi);
        d��mePaneli.add (atalar�G�sterD��mesi);
        d��mePaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Soyk��� a�ac�n� kural�m...
        �ecereA�ac� = new J5c_21x2 (�ecereGrafi�iniAl());
        �ecereA�ac�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane kayd�rmaPanosu = new JScrollPane (�ecereA�ac�);
        kayd�rmaPanosu.setPreferredSize (new Dimension (200, 200));

        // Bu iki komponenti de "this" JPanel'li i�erik panosuna ekleyelim...
        add (d��mePaneli, BorderLayout.PAGE_START);
        add (kayd�rmaPanosu, BorderLayout.CENTER);
    } // J5c_21() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getActionCommand() == ATALARI_G�STER_KOMUTU) �ecereA�ac�.atalar�G�ster (true);
        else �ecereA�ac�.atalar�G�ster (false);
    } // actionPerformed(..) haz�r metodu sonu...
    
    // Model taraf�ndan kullan�lacak olan soy-k�t��� grafi�ini kural�m...
    public J5c_21x1 �ecereGrafi�iniAl() {
        // B�y�k dede'ler/nine'ler �eceresi...
        J5c_21x1 a1 = new J5c_21x1 ("Fatma (b�y�knine)");
        J5c_21x1 a2 = new J5c_21x1 ("Bekir (b�y�kdede)");
        J5c_21x1 a3 = new J5c_21x1 ("Ay�e (b�y�knine)");
        J5c_21x1 a4 = new J5c_21x1 ("Mustafa (b�y�kdede)");
        J5c_21x1 a5 = new J5c_21x1 ("Rebiye (b�y�knine)");
        J5c_21x1 a6 = new J5c_21x1 ("R��an (b�y�kdede)");

        // B�y�k baba'lar/anne'ler �eceresi...
        J5c_21x1 b1 = new J5c_21x1 ("Han�m (b�y�kanne)");
        J5c_21x1 b2 = new J5c_21x1 ("Memet (b�y�kbaba)");
        J5c_21x1 b3 = new J5c_21x1 ("Ahmet (b�y�kbaba)");
        J5c_21x1 b4 = new J5c_21x1 ("Ay�e (b�y�kanne)");
        J5c_21x1 b5 = new J5c_21x1 ("�mer (b�y�kbaba)");
        J5c_21x1 b6 = new J5c_21x1 ("L�tfiye (b�y�kanne)");
        J5c_21x1 b7 = new J5c_21x1 ("Mahmut (b�y�kbaba)");
        J5c_21x1 b8 = new J5c_21x1 ("Remziye (b�y�kanne)");
        J5c_21x1 b9 = new J5c_21x1 ("Ali (b�y�kbaba)");
        J5c_21x1 b10 = new J5c_21x1 ("M�cevher (b�y�kanne)");

        // Baba'lar/anne'ler �eceresi...
        J5c_21x1 c1 = new J5c_21x1 ("Hatice (anne)");
        J5c_21x1 c2 = new J5c_21x1 ("Bayram (baba)");
        J5c_21x1 c3 = new J5c_21x1 ("S�heyla (anne)");
        J5c_21x1 c4 = new J5c_21x1 ("Adil (baba)");
        J5c_21x1 c5 = new J5c_21x1 ("Zeliha (anne)");
        J5c_21x1 c6 = new J5c_21x1 ("Hamza (baba)");
        J5c_21x1 c7 = new J5c_21x1 ("Song�l (anne)");
        J5c_21x1 c8 = new J5c_21x1 ("Sefer (baba)");
        J5c_21x1 c9 = new J5c_21x1 ("Nihat (erkek)");
        J5c_21x1 c10 = new J5c_21x1 ("Nedim (erkek)");
        J5c_21x1 c11 = new J5c_21x1 ("Sevim (kad�n)");
        J5c_21x1 c12 = new J5c_21x1 ("Vedat (baba)");
        J5c_21x1 c13 = new J5c_21x1 ("Jale (anne)");
        J5c_21x1 c14 = new J5c_21x1 ("Erdal (baba)");
        J5c_21x1 c15 = new J5c_21x1 ("Mehmet (baba)");
        J5c_21x1 c16 = new J5c_21x1 ("Necla (anne)");
        J5c_21x1 c17 = new J5c_21x1 ("T�lay (anne)");
        J5c_21x1 c18 = new J5c_21x1 ("B�lent (baba)");
        J5c_21x1 c19 = new J5c_21x1 ("Sabri (baba)");
        J5c_21x1 c20 = new J5c_21x1 ("Mihriban (anne)");
        J5c_21x1 c21 = new J5c_21x1 ("Ali (baba)");
        J5c_21x1 c22 = new J5c_21x1 ("�ahin (baba)");
        J5c_21x1 c23 = new J5c_21x1 ("�nal (baba)");
        J5c_21x1 c24 = new J5c_21x1 ("�brahim (baba)");
        J5c_21x1 c25 = new J5c_21x1 ("Mehmet (baba)");
        J5c_21x1 c26 = new J5c_21x1 ("Zehra (anne)");
        J5c_21x1 c27 = new J5c_21x1 ("Mustafa (baba)");
        J5c_21x1 c28 = new J5c_21x1 ("Cafer (baba)");
        J5c_21x1 c29 = new J5c_21x1 ("Ay�e (baba)");

        // Gen� nesil...
        J5c_21x1 d1 = new J5c_21x1 ("Nurilh�da (kad�n)");
        J5c_21x1 d2 = new J5c_21x1 ("Y�cel (erkek)");
        J5c_21x1 d3 = new J5c_21x1 ("Serap (anne)");
        J5c_21x1 d4 = new J5c_21x1 ("Sema (anne)");
        J5c_21x1 d5 = new J5c_21x1 ("Selda (anne)");
        J5c_21x1 d6 = new J5c_21x1 ("Fatih (baba)");
        J5c_21x1 d7 = new J5c_21x1 ("Canan (anne)");
        J5c_21x1 d8 = new J5c_21x1 ("Zafer (baba)");
        J5c_21x1 d9 = new J5c_21x1 ("Belk�s (kad�n)");
        J5c_21x1 d10 = new J5c_21x1 ("Atilla (erkek)");
        J5c_21x1 d11 = new J5c_21x1 ("Hilal (anne)");
        J5c_21x1 d12 = new J5c_21x1 ("Seman�n-e�i (baba)");
        J5c_21x1 d13 = new J5c_21x1 ("Seldan�n-e�i (baba)");
        J5c_21x1 d14 = new J5c_21x1 ("Canan�n�n-e�i (baba)");
        J5c_21x1 d15 = new J5c_21x1 ("Zaferin-e�i (anne)");
        J5c_21x1 d16 = new J5c_21x1 ("Hilalin-e�i (baba)");
        J5c_21x1 d17 = new J5c_21x1 ("Serab�n-e�i (baba)");

        // Ufakl�klar...
        J5c_21x1 e1 = new J5c_21x1 ("Seman�n-k�z� (k�z)");
        J5c_21x1 e2 = new J5c_21x1 ("Seldan�n-o�lu-1 (o�lan)");
        J5c_21x1 e3 = new J5c_21x1 ("Seldan�n-o�lu-2 (o�lan)");
        J5c_21x1 e4 = new J5c_21x1 ("Mert (o�lan)");
        J5c_21x1 e5 = new J5c_21x1 ("Elif (k�z)");
        J5c_21x1 e6 = new J5c_21x1 ("�mer (o�lan)");
        J5c_21x1 e7 = new J5c_21x1 ("Ali (o�lan)");
        J5c_21x1 e8 = new J5c_21x1 ("�zcan (o�lan)");
        J5c_21x1 e9 = new J5c_21x1 ("Necat (o�lan)");

        // Aile ba�lar� (anne, baba ve �ocuklar)...
        J5c_21x1.aileyiBa�la (a1,a2, new J5c_21x1[] {b2});
        J5c_21x1.aileyiBa�la (a3,a4, new J5c_21x1[] {b1,b4,b6,b7,b9});
        J5c_21x1.aileyiBa�la (a5,a6, new J5c_21x1[] {b10});

        J5c_21x1.aileyiBa�la (b1,b2,new J5c_21x1[] {c1,c3,c5,c7,c9,c10,c11});
        J5c_21x1.aileyiBa�la (b10,b9,new J5c_21x1[] {c12,c13,c14});
        J5c_21x1.aileyiBa�la (b8,b7,new J5c_21x1[] {c15,c16,c17,c18,c19,c20,c21});
        J5c_21x1.aileyiBa�la (b6,b5,new J5c_21x1[] {c22,c23,c24,c25,c26,c27});
        J5c_21x1.aileyiBa�la (b4,b3,new J5c_21x1[] {c28,c29});

        J5c_21x1.aileyiBa�la (c1,c2,new J5c_21x1[] {d1,d2,d3});
        J5c_21x1.aileyiBa�la (c3,c4,new J5c_21x1[] {d4,d5,d6});
        J5c_21x1.aileyiBa�la (c5,c6,new J5c_21x1[] {d7,d8,d9});
        J5c_21x1.aileyiBa�la (c7,c8,new J5c_21x1[] {d10,d11});

        J5c_21x1.aileyiBa�la (d4,d12,new J5c_21x1[] {e1});
        J5c_21x1.aileyiBa�la (d5,d13,new J5c_21x1[] {e2,e3});
        J5c_21x1.aileyiBa�la (d7,d14,new J5c_21x1[] {e4,e5});
        J5c_21x1.aileyiBa�la (d15,d8,new J5c_21x1[] {e6});
        J5c_21x1.aileyiBa�la (d11,d16,new J5c_21x1[] {e7,e8});
        J5c_21x1.aileyiBa�la (d3,d17,new J5c_21x1[] {e9});

        return a1;
    } // �ecereGrafi�iniAl() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Soy K�t��� �rne�i");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_21 yeni��erikPanosu = new J5c_21();
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_21 s�n�f� sonu...