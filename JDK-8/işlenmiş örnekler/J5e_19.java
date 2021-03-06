// J5e_19.java: TextAreaPrintingDemo (MetinAlanıYazdırmaGösterisi) örneği.

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
 * html/yazılacak.txt
 * html/rehber.txt
 *    resim/yaz.png
 */
public class J5e_19 extends JFrame {
    private JTextArea rehberMetinalanı;
    private JLabel başlıkEtiketi;
    private JTextField başlıkMetinsatırı;
    private JLabel sonlukEtiketi;
    private JTextField sonlukMetisatırı;
    private JCheckBox etkileşimÇentiği;
    private JCheckBox arkaplanÇentiği;
    private JButton yazdırDüğmesi;
    private JTextArea yazılacakMetinalanı;
    private JScrollPane kaydırmaPanosu;

    public J5e_19() {
        komponentleriKur();
        yükle (rehberMetinalanı, "html/rehber.txt");
        yükle (yazılacakMetinalanı, "html/yazılacak.txt");
    } // J5e_19() kurucusu sonu...

    private void komponentleriKur() {
        kaydırmaPanosu = new JScrollPane();
        yazılacakMetinalanı = new JTextArea();
        başlıkEtiketi = new JLabel();
        başlıkMetinsatırı = new JTextField();
        sonlukEtiketi = new JLabel();
        sonlukMetisatırı = new JTextField();
        etkileşimÇentiği = new JCheckBox();
        yazdırDüğmesi = new JButton();
        rehberMetinalanı = new JTextArea();
        arkaplanÇentiği = new JCheckBox();

        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        setTitle ("Metin Alan Yazdırma Gösterisi");

        rehberMetinalanı.setColumns (20);
        //rehberMetinalanı.setRows (5);
        rehberMetinalanı.setEditable (false); // Müdahalesiz...
        rehberMetinalanı.setLineWrap (true);
        rehberMetinalanı.setWrapStyleWord (true);
        rehberMetinalanı.setOpaque (false);

        yazılacakMetinalanı.setColumns (20);
        //yazılacakMetinalanı.setRows (5);
        yazılacakMetinalanı.setMargin (new Insets (2, 2, 2, 2));
        kaydırmaPanosu.setViewportView (yazılacakMetinalanı);

        başlıkEtiketi.setText ("Sayfa Başlığı:");
        başlıkMetinsatırı.setText ("William Shakespeare");
        sonlukEtiketi.setText ("Sayfa Sonluğu:");
        sonlukMetisatırı.setText ("Sayfa {0}");

        etkileşimÇentiği.setSelected (true);
        etkileşimÇentiği.setText ("Yaz Adımları Göster");
        etkileşimÇentiği.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        arkaplanÇentiği.setSelected (true);
        arkaplanÇentiği.setText ("Arkaplanda Yazdır");
        arkaplanÇentiği.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        yazdırDüğmesi.setIcon (new ImageIcon (getClass().getResource ("resim/yaz.png")));
        yazdırDüğmesi.setText ("Yazdır!");
        yazdırDüğmesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {yazdır (olay);}
        }); // yaz.. ifadesi sonu...

        GroupLayout serilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (serilim);
        // Yatay grup serilimini kuralım...
        serilim.setHorizontalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addComponent(rehberMetinalanı)
                    .addGroup(serilim.createSequentialGroup()
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (arkaplanÇentiği, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (etkileşimÇentiği, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (yazdırDüğmesi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup (serilim.createSequentialGroup()
                        .addGroup(serilim.createParallelGroup (GroupLayout.Alignment.TRAILING, false)
                            .addComponent (sonlukEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (başlıkEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (sonlukMetisatırı)
                            .addComponent (başlıkMetinsatırı, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))))
                .addGap (15, 15, 15)
                .addComponent (kaydırmaPanosu, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        ); // Yatay grup serilimi sonu...
        // Dikey grup serilimini kuralım...
        serilim.setVerticalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (kaydırmaPanosu, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addGroup (GroupLayout.Alignment.TRAILING, serilim.createSequentialGroup()
                        .addComponent (rehberMetinalanı, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGap (18, 18, 18)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (başlıkEtiketi)
                            .addComponent (başlıkMetinsatırı, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (sonlukEtiketi)
                            .addComponent (sonlukMetisatırı, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap (27, 27, 27)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                            .addGroup (serilim.createSequentialGroup()
                                .addComponent (etkileşimÇentiği, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arkaplanÇentiği, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                            .addComponent (yazdırDüğmesi, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        ); // Dikey grup serilimi sonu...

        pack();
    } // komponentleriKur() metodu sonu...

    private void yükle (JTextArea metinalanı, String dosyaAdı) {
        try {metinalanı.read (new InputStreamReader (getClass().getResourceAsStream (dosyaAdı)), null);
        }catch (IOException ist) {ist.printStackTrace();}
    } // yükle(..) metodu sonu...

    private void yazdır (ActionEvent olay) {
        MessageFormat başlık = biçimiYarat (başlıkMetinsatırı);
        MessageFormat sonluk = biçimiYarat (sonlukMetisatırı);
        boolean konuşalımMı = etkileşimÇentiği.isSelected();
        boolean arkaplandaMı = arkaplanÇentiği.isSelected();

        YazdırmaGörevi görev = new YazdırmaGörevi (başlık, sonluk, konuşalımMı);
        if (arkaplandaMı) görev.execute(); else görev.run();
    } // yazdır(..) metodu sonu...

    private MessageFormat biçimiYarat (JTextField metinsatırı) {
        String yazılacakMetinsatırı = metinsatırı.getText();
        if (yazılacakMetinsatırı != null && yazılacakMetinsatırı.length() > 0) {
            try {return new MessageFormat (yazılacakMetinsatırı);
            }catch (IllegalArgumentException ist) {hata ("Üzgünüm yazdıramam, çünkü bu biçimleme geçersiz!");}
        } // if kararı sonu...
        return null;
    } // biçimiYarat(..) metodu sonu...

    private void hata (String msj) {mesaj (true, msj);}

    private void mesaj (boolean hataMı, String msj) {
        int tip = (hataMı ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog (this, msj, "Yazdırılıyor", tip);
    } // mesaj(..) metodu sonu...

    private class YazdırmaGörevi extends SwingWorker<Object, Object> {
        private final MessageFormat biçimliBaşlık;
        private final MessageFormat biçimliSonluk;
        private final boolean konuşalımMı;
        private volatile boolean tamamlandıMı = false;
        private volatile String msj;

        public YazdırmaGörevi (MessageFormat başlık, MessageFormat sonluk, boolean konuşalımMı) {
            this.biçimliBaşlık = başlık;
            this.biçimliSonluk = sonluk;
            this.konuşalımMı = konuşalımMı;
        } // YazdırmaGörevi(..) kurucusu sonu...

        @Override
        protected Object doInBackground() {
            try {tamamlandıMı = yazılacakMetinalanı.print (biçimliBaşlık, biçimliSonluk, true, null, null, konuşalımMı);
                msj = "Yazdırma " + (tamamlandıMı ? "tamamlandı." : "iptal edildi!");
            }catch (PrinterException ist) {msj = "Üzgünüm, bir yazıcı hatası oluştu!";
            }catch (SecurityException ist) {msj = "Üzgünüm, güvenlik nedeniyle yazıcıya erişemiyorum!";}
            return null;
        } // doInBackground() hazır esgeçme metodu sonu...

        @Override
        protected void done() {mesaj (!tamamlandıMı, msj);}
    } // YazdırmaGörevi sınıfı sonu...

    public static void yaratVeGösterGUI() {
        JFrame çerçeve = new J5e_19(); // Kurucuyu çağırır...
        çerçeve.setLocationRelativeTo (null); // Ekranı (en/boy) ortalar...
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() hazır sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_19 sınıfı sonu...