// J5e_19.java: TextAreaPrintingDemo (MetinAlan�Yazd�rmaG�sterisi) �rne�i.

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
 * html/yaz�lacak.txt
 * html/rehber.txt
 *    resim/yaz.png
 */
public class J5e_19 extends JFrame {
    private JTextArea rehberMetinalan�;
    private JLabel ba�l�kEtiketi;
    private JTextField ba�l�kMetinsat�r�;
    private JLabel sonlukEtiketi;
    private JTextField sonlukMetisat�r�;
    private JCheckBox etkile�im�enti�i;
    private JCheckBox arkaplan�enti�i;
    private JButton yazd�rD��mesi;
    private JTextArea yaz�lacakMetinalan�;
    private JScrollPane kayd�rmaPanosu;

    public J5e_19() {
        komponentleriKur();
        y�kle (rehberMetinalan�, "html/rehber.txt");
        y�kle (yaz�lacakMetinalan�, "html/yaz�lacak.txt");
    } // J5e_19() kurucusu sonu...

    private void komponentleriKur() {
        kayd�rmaPanosu = new JScrollPane();
        yaz�lacakMetinalan� = new JTextArea();
        ba�l�kEtiketi = new JLabel();
        ba�l�kMetinsat�r� = new JTextField();
        sonlukEtiketi = new JLabel();
        sonlukMetisat�r� = new JTextField();
        etkile�im�enti�i = new JCheckBox();
        yazd�rD��mesi = new JButton();
        rehberMetinalan� = new JTextArea();
        arkaplan�enti�i = new JCheckBox();

        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        setTitle ("Metin Alan Yazd�rma G�sterisi");

        rehberMetinalan�.setColumns (20);
        //rehberMetinalan�.setRows (5);
        rehberMetinalan�.setEditable (false); // M�dahalesiz...
        rehberMetinalan�.setLineWrap (true);
        rehberMetinalan�.setWrapStyleWord (true);
        rehberMetinalan�.setOpaque (false);

        yaz�lacakMetinalan�.setColumns (20);
        //yaz�lacakMetinalan�.setRows (5);
        yaz�lacakMetinalan�.setMargin (new Insets (2, 2, 2, 2));
        kayd�rmaPanosu.setViewportView (yaz�lacakMetinalan�);

        ba�l�kEtiketi.setText ("Sayfa Ba�l���:");
        ba�l�kMetinsat�r�.setText ("William Shakespeare");
        sonlukEtiketi.setText ("Sayfa Sonlu�u:");
        sonlukMetisat�r�.setText ("Sayfa {0}");

        etkile�im�enti�i.setSelected (true);
        etkile�im�enti�i.setText ("Yaz Ad�mlar� G�ster");
        etkile�im�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        arkaplan�enti�i.setSelected (true);
        arkaplan�enti�i.setText ("Arkaplanda Yazd�r");
        arkaplan�enti�i.setBorder (BorderFactory.createEmptyBorder (0, 0, 0, 0));

        yazd�rD��mesi.setIcon (new ImageIcon (getClass().getResource ("resim/yaz.png")));
        yazd�rD��mesi.setText ("Yazd�r!");
        yazd�rD��mesi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {yazd�r (olay);}
        }); // yaz.. ifadesi sonu...

        GroupLayout serilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (serilim);
        // Yatay grup serilimini kural�m...
        serilim.setHorizontalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                    .addComponent(rehberMetinalan�)
                    .addGroup(serilim.createSequentialGroup()
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (arkaplan�enti�i, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (etkile�im�enti�i, GroupLayout.PREFERRED_SIZE, 159, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent (yazd�rD��mesi, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup (serilim.createSequentialGroup()
                        .addGroup(serilim.createParallelGroup (GroupLayout.Alignment.TRAILING, false)
                            .addComponent (sonlukEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent (ba�l�kEtiketi, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 74, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING, false)
                            .addComponent (sonlukMetisat�r�)
                            .addComponent (ba�l�kMetinsat�r�, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE))))
                .addGap (15, 15, 15)
                .addComponent (kayd�rmaPanosu, GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
                .addContainerGap())
        ); // Yatay grup serilimi sonu...
        // Dikey grup serilimini kural�m...
        serilim.setVerticalGroup (
            serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
            .addGroup (serilim.createSequentialGroup()
                .addContainerGap()
                .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                    .addComponent (kayd�rmaPanosu, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                    .addGroup (GroupLayout.Alignment.TRAILING, serilim.createSequentialGroup()
                        .addComponent (rehberMetinalan�, GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                        .addGap (18, 18, 18)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (ba�l�kEtiketi)
                            .addComponent (ba�l�kMetinsat�r�, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.BASELINE)
                            .addComponent (sonlukEtiketi)
                            .addComponent (sonlukMetisat�r�, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                        .addGap (27, 27, 27)
                        .addGroup (serilim.createParallelGroup (GroupLayout.Alignment.LEADING)
                            .addGroup (serilim.createSequentialGroup()
                                .addComponent (etkile�im�enti�i, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap (LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(arkaplan�enti�i, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
                            .addComponent (yazd�rD��mesi, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        ); // Dikey grup serilimi sonu...

        pack();
    } // komponentleriKur() metodu sonu...

    private void y�kle (JTextArea metinalan�, String dosyaAd�) {
        try {metinalan�.read (new InputStreamReader (getClass().getResourceAsStream (dosyaAd�)), null);
        }catch (IOException ist) {ist.printStackTrace();}
    } // y�kle(..) metodu sonu...

    private void yazd�r (ActionEvent olay) {
        MessageFormat ba�l�k = bi�imiYarat (ba�l�kMetinsat�r�);
        MessageFormat sonluk = bi�imiYarat (sonlukMetisat�r�);
        boolean konu�al�mM� = etkile�im�enti�i.isSelected();
        boolean arkaplandaM� = arkaplan�enti�i.isSelected();

        Yazd�rmaG�revi g�rev = new Yazd�rmaG�revi (ba�l�k, sonluk, konu�al�mM�);
        if (arkaplandaM�) g�rev.execute(); else g�rev.run();
    } // yazd�r(..) metodu sonu...

    private MessageFormat bi�imiYarat (JTextField metinsat�r�) {
        String yaz�lacakMetinsat�r� = metinsat�r�.getText();
        if (yaz�lacakMetinsat�r� != null && yaz�lacakMetinsat�r�.length() > 0) {
            try {return new MessageFormat (yaz�lacakMetinsat�r�);
            }catch (IllegalArgumentException ist) {hata ("�zg�n�m yazd�ramam, ��nk� bu bi�imleme ge�ersiz!");}
        } // if karar� sonu...
        return null;
    } // bi�imiYarat(..) metodu sonu...

    private void hata (String msj) {mesaj (true, msj);}

    private void mesaj (boolean hataM�, String msj) {
        int tip = (hataM� ? JOptionPane.ERROR_MESSAGE : JOptionPane.INFORMATION_MESSAGE);
        JOptionPane.showMessageDialog (this, msj, "Yazd�r�l�yor", tip);
    } // mesaj(..) metodu sonu...

    private class Yazd�rmaG�revi extends SwingWorker<Object, Object> {
        private final MessageFormat bi�imliBa�l�k;
        private final MessageFormat bi�imliSonluk;
        private final boolean konu�al�mM�;
        private volatile boolean tamamland�M� = false;
        private volatile String msj;

        public Yazd�rmaG�revi (MessageFormat ba�l�k, MessageFormat sonluk, boolean konu�al�mM�) {
            this.bi�imliBa�l�k = ba�l�k;
            this.bi�imliSonluk = sonluk;
            this.konu�al�mM� = konu�al�mM�;
        } // Yazd�rmaG�revi(..) kurucusu sonu...

        @Override
        protected Object doInBackground() {
            try {tamamland�M� = yaz�lacakMetinalan�.print (bi�imliBa�l�k, bi�imliSonluk, true, null, null, konu�al�mM�);
                msj = "Yazd�rma " + (tamamland�M� ? "tamamland�." : "iptal edildi!");
            }catch (PrinterException ist) {msj = "�zg�n�m, bir yaz�c� hatas� olu�tu!";
            }catch (SecurityException ist) {msj = "�zg�n�m, g�venlik nedeniyle yaz�c�ya eri�emiyorum!";}
            return null;
        } // doInBackground() haz�r esge�me metodu sonu...

        @Override
        protected void done() {mesaj (!tamamland�M�, msj);}
    } // Yazd�rmaG�revi s�n�f� sonu...

    public static void yaratVeG�sterGUI() {
        JFrame �er�eve = new J5e_19(); // Kurucuyu �a��r�r...
        �er�eve.setLocationRelativeTo (null); // Ekran� (en/boy) ortalar...
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() haz�r sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5e_19 s�n�f� sonu...