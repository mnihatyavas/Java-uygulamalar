// J5f_18.java: SpringDemo4 (KaynakG�sterisi4) �rne�i.

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
    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kaynak G�sterisi 4");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        Container i�erikPanosu = �er�eve.getContentPane();
        i�erikPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()));
        SpringLayout kaynakSerilim = new SpringLayout();
        i�erikPanosu.setLayout (kaynakSerilim);

        // Komponentleri/par�alar� yarat�p i�erik panosuna ekleyelim...
        JLabel etiket = new JLabel ("Etiket: ");
        JTextField metinSat�r� = new JTextField ("Metin sat�r�", 15);
        i�erikPanosu.add (etiket);
        i�erikPanosu.add (metinSat�r�);

        // S�n�rlay�c�larla etiketi i�erik panosunda mutlak (5,5)'e konumland�ral�m...
        SpringLayout.Constraints  etiketS�n�rlay�c�lar� = kaynakSerilim.getConstraints (etiket);
        etiketS�n�rlay�c�lar�.setX (Spring.constant (5));
        etiketS�n�rlay�c�lar�.setY (Spring.constant (5));

        // S�n�rlay�c�larla metin sat�r�n� etiketin sa��na/do�u ve i�erik panosunun kuzeyine (+5/sum,5) konumland�ral�m...
        SpringLayout.Constraints metinSat�r�S�n�rlay�c�lar� = kaynakSerilim.getConstraints (metinSat�r�);
        metinSat�r�S�n�rlay�c�lar�.setX (Spring.sum (Spring.constant (5), etiketS�n�rlay�c�lar�.getConstraint (SpringLayout.EAST)));
        metinSat�r�S�n�rlay�c�lar�.setY (Spring.constant (5));

        // S�n�rlay�c�larla i�erik panosu (a��k) alan�n�/ebat�n� ayarlayal�m...
        i�erikPanosuEbat�n�Kur (i�erikPanosu, 50); // Sa� ve alt� biraz abart�l� tamponlayal�m...

        �er�eve.setLocationRelativeTo (null);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void i�erikPanosuEbat�n�Kur (Container kab, int tampon) {
        SpringLayout kaynakSerilim = (SpringLayout)kab.getLayout();
        Component[] par�alar = kab.getComponents();
        Spring azamiKaynakBoyu = Spring.constant (0); // �lk par�an�n boyu farzedelim...
        SpringLayout.Constraints kabS�n�rlay�c�lar� = kaynakSerilim.getConstraints (kab);

        // S�n�rlay�c�larla kab'�n sa��n�/enini en sa�daki par�a + tampon toplam�na ayarlayal�m...
        Component sa�dakiPar�a = par�alar[par�alar.length - 1];
        SpringLayout.Constraints sa�S�n�rlay�c�lar = kaynakSerilim.getConstraints (sa�dakiPar�a);
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.EAST,
                Spring.sum (Spring.constant (tampon), sa�S�n�rlay�c�lar.getConstraint (SpringLayout.EAST)));

        // S�n�rlay�c�larla kab'�n alt�n�/boyunu en boylu par�a + tampon toplam�na ayarlayal�m...
        for (int i = 0; i < par�alar.length; i++) {
            SpringLayout.Constraints altS�n�rlay�c�lar = kaynakSerilim.getConstraints (par�alar[i]);
            azamiKaynakBoyu = Spring.max (azamiKaynakBoyu, altS�n�rlay�c�lar.getConstraint (SpringLayout.SOUTH));
        } // for d�ng�s� sonu...
        kabS�n�rlay�c�lar�.setConstraint (SpringLayout.SOUTH,
                Spring.sum (Spring.constant (tampon), azamiKaynakBoyu));
   } // i�erikPanosuEbat�n�Kur(..) metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5f_18 s�n�f� sonu...