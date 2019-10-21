// J5f_18.java: SpringDemo4 (KaynakGösterisi4) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.Spring;
import javax.swing.SwingUtilities;

public class J5f_18 {
    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Kaynak Gösterisi 4");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container içerikPanosu = çerçeve.getContentPane();
        içerikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        içerikPanosu.setLayout (kaynakSerilim);

        // Komponentleri/parçalarý yaratýp içerik panosuna ekleyelim...
        JLabel etiket = new JLabel ("Etiket: ");
        JTextField metinSatýrý = new JTextField ("Metin satýrý", 15);
        içerikPanosu.add (etiket);
        içerikPanosu.add (metinSatýrý);

        // Sýnýrlayýcýlarla etiketi içerik panosunda mutlak (5,5)'e konumlandýralým...
        SpringLayout.Constraints  etiketSýnýrlayýcýlarý = kaynakSerilim.getConstraints (etiket);
        etiketSýnýrlayýcýlarý.setX (Spring.constant (5));
        etiketSýnýrlayýcýlarý.setY (Spring.constant (5));

        // Sýnýrlayýcýlarla metin satýrýný etiketin saðýna/doðu ve içerik panosunun kuzeyine (+5/sum,5) konumlandýralým...
        SpringLayout.Constraints metinSatýrýSýnýrlayýcýlarý = kaynakSerilim.getConstraints (metinSatýrý);
        metinSatýrýSýnýrlayýcýlarý.setX (Spring.sum (Spring.constant (5), etiketSýnýrlayýcýlarý.getConstraint (SpringLayout.EAST)));
        metinSatýrýSýnýrlayýcýlarý.setY (Spring.constant (5));

        // Sýnýrlayýcýlarla içerik panosu (açýk) alanýný/ebatýný ayarlayalým...
        içerikPanosuEbatýnýKur (içerikPanosu, 50); // Sað ve altý biraz abartýlý tamponlayalým...

        çerçeve.setLocationRelativeTo (null);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void içerikPanosuEbatýnýKur (Container kab, int tampon) {
        SpringLayout kaynakSerilim = (SpringLayout)kab.getLayout();
        Component[] parçalar = kab.getComponents();
        Spring azamiKaynakBoyu = Spring.constant (0); // Ýlk parçanýn boyu farzedelim...
        SpringLayout.Constraints kabSýnýrlayýcýlarý = kaynakSerilim.getConstraints (kab);

        // Sýnýrlayýcýlarla kab'ýn saðýný/enini en saðdaki parça + tampon toplamýna ayarlayalým...
        Component saðdakiParça = parçalar[parçalar.length - 1];
        SpringLayout.Constraints saðSýnýrlayýcýlar = kaynakSerilim.getConstraints (saðdakiParça);
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.EAST,
                Spring.sum (Spring.constant (tampon), saðSýnýrlayýcýlar.getConstraint (SpringLayout.EAST)));

        // Sýnýrlayýcýlarla kab'ýn altýný/boyunu en boylu parça + tampon toplamýna ayarlayalým...
        for (int i = 0; i < parçalar.length; i++) {
            SpringLayout.Constraints altSýnýrlayýcýlar = kaynakSerilim.getConstraints (parçalar[i]);
            azamiKaynakBoyu = Spring.max (azamiKaynakBoyu, altSýnýrlayýcýlar.getConstraint (SpringLayout.SOUTH));
        } // for döngüsü sonu...
        kabSýnýrlayýcýlarý.setConstraint (SpringLayout.SOUTH,
                Spring.sum (Spring.constant (tampon), azamiKaynakBoyu));
   } // içerikPanosuEbatýnýKur(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5f_18 sýnýfý sonu...