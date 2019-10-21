// J5e_19.java: TextAreaPrintingDemo (MetinAlanýYazdýrmaGösterisi) örneði.

import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.print.PrinterException;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.SwingWorker;

import java.io.InputStreamReader;
import java.io.IOException;

import java.text.MessageFormat;
//import javax.xml.transform.Source;

/* Gerekli dosyalar:
 * html/yazýlacak.txt
 * html/rehber.txt
 *    resim/yaz.png
 */
public class J5e_19 extends JFrame {
    private JTextArea rehberMetinalaný;
    private JLabel baþlýkEtiketi;
    private JTextField baþlýkMetinsatýrý;
    private JLabel sonlukEtiketi;
    private JTextField sonlukMetisatýrý;
    private JCheckBox etkileþimÇentiði;
    private JCheckBox arkaplanÇentiði;
    private JButton yazdýrDüðmesi;
    private JTextArea yazýlacakMetinalaný;
    private JScrollPane kaydýrmaPanosu;

    public J5e_19() {
        komponentleriKur();
        yükle (rehberMetinalaný, "html/rehber.txt");
        yükle (yazýlacakMetinalaný, "html/yazýlacak.txt");
    } // J5e_19() kurucusu sonu...

    private void komponentleriKur() {
        kaydýrmaPanosu = new JScrollPane();
        yazýlacakMetinalaný = new JTextArea();
        baþlýkEtiketi = new JLabel();
        baþlýkMetinsatýrý = new JTextField();
        sonlukEtiketi = new JLabel();
        sonlukMetisatýrý = new JTextField();
        etkileþimÇentiði = new JCheckBox();
        yazdýrDüðmesi = new JButton();
        rehberMetinalaný = new JTextArea();
        arkaplanÇentiði = new JCheckBox();

        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        setTitle ("Metin Alan Yazdýrma Gösterisi");

        rehberMetinalaný.setColumns (20);
        //rehberMetinalaný.setRows (5);
        rehberMetinalaný.setEditable (false); // Müdahalesiz...
        rehberMetinalaný.setLineWrap (true);
        rehberMetinalaný.setWrapStyleWord (true);
        rehberMetinalaný.setOpaque (false);

        yazýlacakMetinalaný.setColumns (20);
        //yazýlacakMetinalaný.setRows (5);
        yazýlacakMetinalaný.setMargin (new Insets (2, 2, 2, 2));
        kaydýrmaPanosu.setViewportView (yazýlacakMetinalaný);

        baþlýkEtiketi.setText ("Sayfa Baþlýðý:");
        baþlýkMetinsatýrý.setText ("William Shakespeare");
        sonlukEtiketi.setText ("Sayfa Sonluðu:");
        sonlukMetisatýrý.setText ("Sayfa {0}");

        etkileþimÇentiði.setSelected (true);
        etkileþimÇentiði.setText ("Yaz Adýmlarý Göster");
        etkileþimÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        arkaplanÇentiði.setSelected (true);
        arkaplanÇentiði.setText ("Arkaplanda Yazdýr");
        arkaplanÇentiði.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        yazdýrDüðmesi.setIcon (new ImageIcon (getClass().getResource ("resim/yaz.png")));
        yazdýrDüðmesi.setText ("Yazdýr!");
        yazdýrDüðmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {yazdýr (olay);}
        }); // yaz.. ifadesi sonu...

        GroupLayout serilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (serilim);
        // Yatay grup serilimini kuralým...
        serilim.setHorizontalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addComponent(rehberMetinalaný)
                    .addGroup(serilim.createSequentialGroup()
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (arkaplanÇentiði, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (etkileþimÇentiði, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (yazdýrDüðmesi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup (serilim.createSequentialGroup()
                        .addGroup(serilim.createParallelGroup (GroupLayout.Alignment.TRAILING, false)
                            .addComponent (sonlukEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (baþlýkEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (sonlukMetisatýrý)
                            .addComponent (baþlýkMetinsatýrý, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))))
                .addGap (15, 15, 15)
                .addComponent (kaydýrmaPanosu, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        ); // Yatay grup serilimi sonu...
        // Dikey grup serilimini kuralým...
        serilim.setVerticalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (kaydýrmaPanosu, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addGroup (GroupLayout.Alignment.TRAILING, serilim.createSequentialGroup()
                        .addComponent (rehberMetinalaný, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGap (18, 18, 18)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (baþlýkEtiketi)
                            .addComponent (baþlýkMetinsatýrý, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (sonlukEtiketi)
                            .addComponent (sonlukMetisatýrý, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap (27, 27, 27)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                            .addGroup (serilim.createSequentialGroup()
                                .addComponent (etkileþimÇentiði, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arkaplanÇentiði, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                            .addComponent (yazdýrDüðmesi, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        ); // Dikey grup serilimi sonu...

        pack();
    } // komponentleriKur() metodu sonu...

    private void yükle (JTextArea metinalaný, String dosyaAdý) {
        try {metinalaný.read (new InputStreamReader (getClass().getResourceAsStream (dosyaAdý)), null);
        }catch (IOException ist) {ist.printStackTrace();}
    } // yükle(..) metodu sonu...

    private void yazdýr (ActionEvent olay) {
        MessageFormat baþlýk = biçimiYarat (baþlýkMetinsatýrý);
        MessageFormat sonluk = biçimiYarat (sonlukMetisatýrý);
        boolean konuþalýmMý = etkileþimÇentiði.isSelected();
        boolean arkaplandaMý = arkaplanÇentiði.isSelected();

        YazdýrmaGörevi görev = new YazdýrmaGörevi (baþlýk, sonluk, konuþalýmMý);
        if (arkaplandaMý) görev.execute(); else görev.run();
    } // yazdýr(..) metodu sonu...

    private MessageFormat biçimiYarat (JTextField metinsatýrý) {
        String yazýlacakMetinsatýrý = metinsatýrý.getText();
        if (yazýlacakMetinsatýrý != null && yazýlacakMetinsatýrý.length() > 0) {
            try {return new MessageFormat (yazýlacakMetinsatýrý);
            }catch (IllegalArgumentException ist) {hata ("Üzgünüm yazdýramam, çünkü bu biçimleme geçersiz!");}
        } // if kararý sonu...
        return null;
    } // biçimiYarat(..) metodu sonu...

    private void hata (String msj) {mesaj (true, msj);}

    private void mesaj (boolean hataMý, String msj) {
        int tip = (hataMý ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog (this, msj, "Yazdýrýlýyor", tip);
    } // mesaj(..) metodu sonu...

    private class YazdýrmaGörevi extends SwingWorker<Object, Object> {
        private final MessageFormat biçimliBaþlýk;
        private final MessageFormat biçimliSonluk;
        private final boolean konuþalýmMý;
        private volatile boolean tamamlandýMý = false;
        private volatile String msj;

        public YazdýrmaGörevi (MessageFormat baþlýk, MessageFormat sonluk, boolean konuþalýmMý) {
            this.biçimliBaþlýk = baþlýk;
            this.biçimliSonluk = sonluk;
            this.konuþalýmMý = konuþalýmMý;
        } // YazdýrmaGörevi(..) kurucusu sonu...

        @Override
        protected Object doInBackground() {
            try {tamamlandýMý = yazýlacakMetinalaný.print (biçimliBaþlýk, biçimliSonluk, true, null, null, konuþalýmMý);
                msj = "Yazdýrma " + (tamamlandýMý ? "tamamlandý." : "iptal edildi!");
            }catch (PrinterException ist) {msj = "Üzgünüm, bir yazýcý hatasý oluþtu!";
            }catch (SecurityException ist) {msj = "Üzgünüm, güvenlik nedeniyle yazýcýya eriþemiyorum!";}
            return null;
        } // doInBackground() hazýr esgeçme metodu sonu...

        @Override
        protected void done() {mesaj (!tamamlandýMý, msj);}
    } // YazdýrmaGörevi sýnýfý sonu...

    public static void yaratVeGösterGUI() {
        JFrame çerçeve = new J5e_19(); // Kurucuyu çaðýrýr...
        çerçeve.setLocationRelativeTo (null); // Ekraný (en/boy) ortalar...
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() hazýr sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_19 sýnýfý sonu...