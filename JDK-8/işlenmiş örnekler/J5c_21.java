// J5c_21.java: GenealogyExample (ÞecereKütüðü) örneði.

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

/* Þu dosyalar kullanýlmaktadýr:
 *    Person.java/J5c_21x1.java
 *    GenealogyTree.java/J5c_21x2.java
 *    GenealogyModel.java/J5c_21x3
 */
public class J5c_21 extends JPanel implements ActionListener {
    J5c_21x2 þecereAðacý;
    private static String ATALARI_GÖSTER_KOMUTU = "atalarýGöster";

    public J5c_21() {// Kurucu...
        super (new BorderLayout());

        // Paneli ikili radyo-düðmesiyle kuralým...
        JRadioButton türeyenleriGösterDüðmesi = new JRadioButton ("Türeyenleri göster", true);
        final JRadioButton atalarýGösterDüðmesi = new JRadioButton ("Atalarý göster");

        ButtonGroup düðmeGrubu = new ButtonGroup();
        düðmeGrubu.add (türeyenleriGösterDüðmesi);
        düðmeGrubu.add (atalarýGösterDüðmesi);

        türeyenleriGösterDüðmesi.addActionListener (this);
        atalarýGösterDüðmesi.addActionListener (this);
        atalarýGösterDüðmesi.setActionCommand (ATALARI_GÖSTER_KOMUTU);

        JPanel düðmePaneli = new JPanel();
        düðmePaneli.add (türeyenleriGösterDüðmesi);
        düðmePaneli.add (atalarýGösterDüðmesi);
        düðmePaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // Soyküðü aðacýný kuralým...
        þecereAðacý = new J5c_21x2 (þecereGrafiðiniAl());
        þecereAðacý.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        JScrollPane kaydýrmaPanosu = new JScrollPane (þecereAðacý);
        kaydýrmaPanosu.setPreferredSize (new Dimension (200, 200));

        // Bu iki komponenti de "this" JPanel'li içerik panosuna ekleyelim...
        add (düðmePaneli, BorderLayout.PAGE_START);
        add (kaydýrmaPanosu, BorderLayout.CENTER);
    } // J5c_21() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        if (olay.getActionCommand() == ATALARI_GÖSTER_KOMUTU) þecereAðacý.atalarýGöster (true);
        else þecereAðacý.atalarýGöster (false);
    } // actionPerformed(..) hazýr metodu sonu...
    
    // Model tarafýndan kullanýlacak olan soy-kütüðü grafiðini kuralým...
    public J5c_21x1 þecereGrafiðiniAl() {
        // Büyük dede'ler/nine'ler þeceresi...
        J5c_21x1 a1 = new J5c_21x1 ("Fatma (büyüknine)");
        J5c_21x1 a2 = new J5c_21x1 ("Bekir (büyükdede)");
        J5c_21x1 a3 = new J5c_21x1 ("Ayþe (büyüknine)");
        J5c_21x1 a4 = new J5c_21x1 ("Mustafa (büyükdede)");
        J5c_21x1 a5 = new J5c_21x1 ("Rebiye (büyüknine)");
        J5c_21x1 a6 = new J5c_21x1 ("Rüþan (büyükdede)");

        // Büyük baba'lar/anne'ler þeceresi...
        J5c_21x1 b1 = new J5c_21x1 ("Haným (büyükanne)");
        J5c_21x1 b2 = new J5c_21x1 ("Memet (büyükbaba)");
        J5c_21x1 b3 = new J5c_21x1 ("Ahmet (büyükbaba)");
        J5c_21x1 b4 = new J5c_21x1 ("Ayþe (büyükanne)");
        J5c_21x1 b5 = new J5c_21x1 ("Ömer (büyükbaba)");
        J5c_21x1 b6 = new J5c_21x1 ("Lütfiye (büyükanne)");
        J5c_21x1 b7 = new J5c_21x1 ("Mahmut (büyükbaba)");
        J5c_21x1 b8 = new J5c_21x1 ("Remziye (büyükanne)");
        J5c_21x1 b9 = new J5c_21x1 ("Ali (büyükbaba)");
        J5c_21x1 b10 = new J5c_21x1 ("Mücevher (büyükanne)");

        // Baba'lar/anne'ler þeceresi...
        J5c_21x1 c1 = new J5c_21x1 ("Hatice (anne)");
        J5c_21x1 c2 = new J5c_21x1 ("Bayram (baba)");
        J5c_21x1 c3 = new J5c_21x1 ("Süheyla (anne)");
        J5c_21x1 c4 = new J5c_21x1 ("Adil (baba)");
        J5c_21x1 c5 = new J5c_21x1 ("Zeliha (anne)");
        J5c_21x1 c6 = new J5c_21x1 ("Hamza (baba)");
        J5c_21x1 c7 = new J5c_21x1 ("Songül (anne)");
        J5c_21x1 c8 = new J5c_21x1 ("Sefer (baba)");
        J5c_21x1 c9 = new J5c_21x1 ("Nihat (erkek)");
        J5c_21x1 c10 = new J5c_21x1 ("Nedim (erkek)");
        J5c_21x1 c11 = new J5c_21x1 ("Sevim (kadýn)");
        J5c_21x1 c12 = new J5c_21x1 ("Vedat (baba)");
        J5c_21x1 c13 = new J5c_21x1 ("Jale (anne)");
        J5c_21x1 c14 = new J5c_21x1 ("Erdal (baba)");
        J5c_21x1 c15 = new J5c_21x1 ("Mehmet (baba)");
        J5c_21x1 c16 = new J5c_21x1 ("Necla (anne)");
        J5c_21x1 c17 = new J5c_21x1 ("Tülay (anne)");
        J5c_21x1 c18 = new J5c_21x1 ("Bülent (baba)");
        J5c_21x1 c19 = new J5c_21x1 ("Sabri (baba)");
        J5c_21x1 c20 = new J5c_21x1 ("Mihriban (anne)");
        J5c_21x1 c21 = new J5c_21x1 ("Ali (baba)");
        J5c_21x1 c22 = new J5c_21x1 ("Þahin (baba)");
        J5c_21x1 c23 = new J5c_21x1 ("Ünal (baba)");
        J5c_21x1 c24 = new J5c_21x1 ("Ýbrahim (baba)");
        J5c_21x1 c25 = new J5c_21x1 ("Mehmet (baba)");
        J5c_21x1 c26 = new J5c_21x1 ("Zehra (anne)");
        J5c_21x1 c27 = new J5c_21x1 ("Mustafa (baba)");
        J5c_21x1 c28 = new J5c_21x1 ("Cafer (baba)");
        J5c_21x1 c29 = new J5c_21x1 ("Ayþe (baba)");

        // Genç nesil...
        J5c_21x1 d1 = new J5c_21x1 ("Nurilhüda (kadýn)");
        J5c_21x1 d2 = new J5c_21x1 ("Yücel (erkek)");
        J5c_21x1 d3 = new J5c_21x1 ("Serap (anne)");
        J5c_21x1 d4 = new J5c_21x1 ("Sema (anne)");
        J5c_21x1 d5 = new J5c_21x1 ("Selda (anne)");
        J5c_21x1 d6 = new J5c_21x1 ("Fatih (baba)");
        J5c_21x1 d7 = new J5c_21x1 ("Canan (anne)");
        J5c_21x1 d8 = new J5c_21x1 ("Zafer (baba)");
        J5c_21x1 d9 = new J5c_21x1 ("Belkýs (kadýn)");
        J5c_21x1 d10 = new J5c_21x1 ("Atilla (erkek)");
        J5c_21x1 d11 = new J5c_21x1 ("Hilal (anne)");
        J5c_21x1 d12 = new J5c_21x1 ("Semanýn-eþi (baba)");
        J5c_21x1 d13 = new J5c_21x1 ("Seldanýn-eþi (baba)");
        J5c_21x1 d14 = new J5c_21x1 ("Cananýnýn-eþi (baba)");
        J5c_21x1 d15 = new J5c_21x1 ("Zaferin-eþi (anne)");
        J5c_21x1 d16 = new J5c_21x1 ("Hilalin-eþi (baba)");
        J5c_21x1 d17 = new J5c_21x1 ("Serabýn-eþi (baba)");

        // Ufaklýklar...
        J5c_21x1 e1 = new J5c_21x1 ("Semanýn-kýzý (kýz)");
        J5c_21x1 e2 = new J5c_21x1 ("Seldanýn-oðlu-1 (oðlan)");
        J5c_21x1 e3 = new J5c_21x1 ("Seldanýn-oðlu-2 (oðlan)");
        J5c_21x1 e4 = new J5c_21x1 ("Mert (oðlan)");
        J5c_21x1 e5 = new J5c_21x1 ("Elif (kýz)");
        J5c_21x1 e6 = new J5c_21x1 ("Ömer (oðlan)");
        J5c_21x1 e7 = new J5c_21x1 ("Ali (oðlan)");
        J5c_21x1 e8 = new J5c_21x1 ("Özcan (oðlan)");
        J5c_21x1 e9 = new J5c_21x1 ("Necat (oðlan)");

        // Aile baðlarý (anne, baba ve çocuklar)...
        J5c_21x1.aileyiBaðla (a1,a2, new J5c_21x1[] {b2});
        J5c_21x1.aileyiBaðla (a3,a4, new J5c_21x1[] {b1,b4,b6,b7,b9});
        J5c_21x1.aileyiBaðla (a5,a6, new J5c_21x1[] {b10});

        J5c_21x1.aileyiBaðla (b1,b2,new J5c_21x1[] {c1,c3,c5,c7,c9,c10,c11});
        J5c_21x1.aileyiBaðla (b10,b9,new J5c_21x1[] {c12,c13,c14});
        J5c_21x1.aileyiBaðla (b8,b7,new J5c_21x1[] {c15,c16,c17,c18,c19,c20,c21});
        J5c_21x1.aileyiBaðla (b6,b5,new J5c_21x1[] {c22,c23,c24,c25,c26,c27});
        J5c_21x1.aileyiBaðla (b4,b3,new J5c_21x1[] {c28,c29});

        J5c_21x1.aileyiBaðla (c1,c2,new J5c_21x1[] {d1,d2,d3});
        J5c_21x1.aileyiBaðla (c3,c4,new J5c_21x1[] {d4,d5,d6});
        J5c_21x1.aileyiBaðla (c5,c6,new J5c_21x1[] {d7,d8,d9});
        J5c_21x1.aileyiBaðla (c7,c8,new J5c_21x1[] {d10,d11});

        J5c_21x1.aileyiBaðla (d4,d12,new J5c_21x1[] {e1});
        J5c_21x1.aileyiBaðla (d5,d13,new J5c_21x1[] {e2,e3});
        J5c_21x1.aileyiBaðla (d7,d14,new J5c_21x1[] {e4,e5});
        J5c_21x1.aileyiBaðla (d15,d8,new J5c_21x1[] {e6});
        J5c_21x1.aileyiBaðla (d11,d16,new J5c_21x1[] {e7,e8});
        J5c_21x1.aileyiBaðla (d3,d17,new J5c_21x1[] {e9});

        return a1;
    } // þecereGrafiðiniAl() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Soy Kütüðü Örneði");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        J5c_21 yeniÝçerikPanosu = new J5c_21();
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_21 sýnýfý sonu...