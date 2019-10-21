// J5c_37.java: PopupMenuDemo (FýrlayanMenüGösterisi) örneði.

import java.awt.Color;
import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JPopupMenu;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.Timer;

/* Gereken resim dosyasý: resim/orta.gif
 *
 * J5c_33/MenüDemo'yla aynýdýr, ancak Timer zamanlayýcý her saniye
 * seçili menünün tam yolunu metin alanýna yansýtýr...
 */
public class J5c_37 implements ActionListener, ItemListener {
    JTextArea metinAlaný;
    JScrollPane kaydýrmaPanosu;
    String yeniSatýr = "\n";
    public final static int BÝR_SANÝYE = 1000;

    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu;
        JMenu menü, altMenü, altAltMenü;
        JMenuItem menüBirimi;
        JRadioButtonMenuItem rbMenüBirimi;
        JCheckBoxMenuItem cbMenüBirimi;

        // Menü çubuðunu yaratalým...
        menüÇubuðu = new JMenuBar();
        menüÇubuðu.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        //Menü-1'i yaratýp içeriklerini kuralým ve menü çubuðuna ekleyelim...
        menü = new JMenu ("Ýlk Menü");
        menü.setMnemonic (KeyEvent.VK_M);
        menü.getAccessibleContext().setAccessibleDescription ("Bu programda menü birimleri ve alt menüleri olan tek menü");
        menüÇubuðu.add (menü);

        // Grup-1 menü birimleri...
        menüBirimi = new JMenuItem ("Sadece metinli menü birimi", KeyEvent.VK_S);
        //menüBirimi.setMnemonic (KeyEvent.VK_S); // Gerekmez, zira kurucununda tanýmlandý...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1, ActionEvent.ALT_MASK)); // Heryerden Alt_1...
        menüBirimi.getAccessibleContext().setAccessibleDescription ("Bu menü birimi gerçekte baþkaca bir þey yapmaz");
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        ImageIcon ikon = resimÝkonunuYarat ("resim/orta.gif");
        menüBirimi = new JMenuItem ("Hem metinli hem de ikonlu menü birimi", ikon);
        menüBirimi.setMnemonic (KeyEvent.VK_H);
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        menüBirimi = new JMenuItem (ikon); // Sadece ikonlu menü birimi...
        //menüBirimi.setMnemonic (KeyEvent.VK_D); // Gerekmez, zira görünmüyor...
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK)); // Görünen ve heryerden Alt_2...
        menüBirimi.addActionListener (this);
        menü.add (menüBirimi);

        // Grup-2 radyo menü birimleri...
        menü.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbMenüBirimi = new JRadioButtonMenuItem ("Ýlk radyo butonlu menü birimi");
        rbMenüBirimi.setSelected (true); // Ýlk anda bu seçili/çentikli...
        rbMenüBirimi.setMnemonic (KeyEvent.VK_R);
        grup.add (rbMenüBirimi);
        rbMenüBirimi.addActionListener (this);
        menü.add (rbMenüBirimi);

        rbMenüBirimi = new JRadioButtonMenuItem ("Ýkinci radyo butonlu menü birimi");
        rbMenüBirimi.setMnemonic (KeyEvent.VK_C);
        grup.add (rbMenüBirimi);
        rbMenüBirimi.addActionListener (this);
        menü.add (rbMenüBirimi);

        // Grup-3 çentik menü birimleri...
        // Bu grup birimleri ItemListener'a duyarlý; çentikli/çentiksiz tespiti de yapýyor...
        menü.addSeparator();
        cbMenüBirimi = new JCheckBoxMenuItem ("Ýlk çentik kutulu menü birimi");
        cbMenüBirimi.setMnemonic (KeyEvent.VK_T);
        cbMenüBirimi.addItemListener (this);
        menü.add (cbMenüBirimi);

        cbMenüBirimi = new JCheckBoxMenuItem ("Ýkinci çentik kutulu menü birimi");
        cbMenüBirimi.setMnemonic (KeyEvent.VK_K);
        cbMenüBirimi.addItemListener (this);
        menü.add (cbMenüBirimi);

        // AltMenü...
        menü.addSeparator();
        altMenü = new JMenu ("Bir alt menü");
        altMenü.setMnemonic (KeyEvent.VK_A);
        menü.add (altMenü);

        menüBirimi = new JMenuItem ("Alt menüdeki ilk menü birimi");
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_3, ActionEvent.ALT_MASK)); // Heryerden Alt_3...
        menüBirimi.addActionListener (this);
        altMenü.add (menüBirimi);

        menüBirimi = new JMenuItem ("Alt menüdeki ikinci menü birimi");
        menüBirimi.addActionListener (this);
        altMenü.add (menüBirimi);

        // AltAltMenü...
        altMenü.addSeparator();
        altAltMenü = new JMenu ("Bir alt alt menü");
        altMenü.add (altAltMenü);

        menüBirimi = new JMenuItem ("Alt alt menünün tek menü birimi");
        menüBirimi.addActionListener (this);
        altAltMenü.add (menüBirimi);

        //Menü-2'yi içeriksiz yaratýp kuralým...
        menü = new JMenu ("Ýkinci Menü");
        menü.setMnemonic (KeyEvent.VK_N);
        menü.getAccessibleContext().setAccessibleDescription ("Bu menü þimdilik tamamen içeriksizdir");
        menüÇubuðu.add (menü);

        Timer zamanlayýcý = new Timer (BÝR_SANÝYE, new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                MenuElement[] yol = MenuSelectionManager.defaultManager().getSelectedPath();
                for (int i = 0; i < yol.length; i++ ) {
                    if (yol[i].getComponent() instanceof javax.swing.JMenuItem) {
                        JMenuItem birimMenü = (JMenuItem)yol[i].getComponent();
                        if ("".equals (birimMenü.getText())) metinAlaný.append ("SADECE_ÝKONÝK MENÜ BÝRÝMÝ-> ");
                        else metinAlaný.append (birimMenü.getText() + "-> ");
                    } // dýþ-if kararý sonu...
                } // for döngüsü sonu...
                if (yol.length > 0) metinAlaný.append (yeniSatýr);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // zamanlayýcý ifadesi sonu...

        zamanlayýcý.start(); // Her saniyede duyarlýdýr...
        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...

    // Kaydýrmalý metin alanlý içerik panosu/paneli yaratýr ve kurar...
    public Container içerikPanosunuYarat() {
        JPanel içerikPaneli = new JPanel (new BorderLayout());
        içerikPaneli.setOpaque (true);
        metinAlaný = new JTextArea (5, 30);
        metinAlaný.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlaný.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        metinAlaný.setEditable (false); // Müdahalesiz...
        kaydýrmaPanosu = new JScrollPane (metinAlaný);
        içerikPaneli.add (kaydýrmaPanosu, BorderLayout.CENTER);

        return içerikPaneli;
    } // içerikPanosunuYarat() metodu sonu...

    public void fýrlayanMenüyüYarat() {
        JMenuItem menüBirimi;

        // Fýrlayan menüyü yaratalým...
        JPopupMenu zýplayanMenü = new JPopupMenu ();

        // 2 adet menü birimini yaratýp fýrlayana ekleyelim...
        menüBirimi = new JMenuItem ("Fýrlayan menü birimi #1");
        menüBirimi.addActionListener (this);
        zýplayanMenü.add (menüBirimi);

        menüBirimi = new JMenuItem ("Fýrlayan menü birimi #2");
        menüBirimi.addActionListener (this);
        zýplayanMenü.add (menüBirimi);

        // Fýrlayan menü oluþmasý için metin alanýný fare týklamasýna duyarlý kýlalým...
        MouseListener fýrlayanDinleyici = new FýrlayanDinleyici (zýplayanMenü);
        metinAlaný.addMouseListener (fýrlayanDinleyici);
    } // fýrlayanMenüyüYarat() metodu sonu...

    class FýrlayanDinleyici extends MouseAdapter {
        JPopupMenu zýplayan;

        FýrlayanDinleyici (JPopupMenu zýplayanMenü) {zýplayan = zýplayanMenü;}
        // Fare sað-týklamaya duyarlý...
        public void mousePressed (MouseEvent olay) {fýrlayanGörünebilir (olay);}
        public void mouseReleased (MouseEvent olay) {fýrlayanGörünebilir (olay);}

        private void fýrlayanGörünebilir (MouseEvent olay) {
            if (olay.isPopupTrigger()) {zýplayan.show (olay.getComponent(), olay.getX(), olay.getY());}
        } // fýrlayanGörünebilir(..) metodu sonu...
    } // FýrlayanDinleyici sýnýfý sonu...

    // Týklanan menü biriminin (import) sýnýf kaynak tam adýný metin alanýna yansýtýr...
    public void actionPerformed (ActionEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Yeni bir aksiyon olayý tespit edildi." + yeniSatýr +
                "-->Olay kaynaðý: " + kaynak.getText() + " (bir <<" + kaynak.getClass().getName() + ">> sýnýfý tiplemesi)";
        metinAlaný.append (rapor + yeniSatýr);
        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
    } // actionPerformed(..) hazýr metodu sonu...

    // Týklanan çentikli menü biriminin (import) sýnýf kaynak tam adýný ve çentikli-seçildi/çentiksiz-seçilmedi durumunu metin alanýna yansýtýr...
    public void itemStateChanged (ItemEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Yeni bir birim olayý tespit edildi." + yeniSatýr +
                "-->Olay kaynaðý: " + kaynak.getText() + " (bir <<" + kaynak.getClass().getName() + ">> sýnýfý tiplemesi)" + yeniSatýr +
                "-->Yeni durum: " + ((olay.getStateChange() == ItemEvent.SELECTED) ? "seçildi/çentikli" : "seçilmedi/çentiksiz");
        metinAlaný.append (rapor + yeniSatýr);
        metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
    } // itemStateChanged(..) hazýr metodu sonu...

    protected static ImageIcon resimÝkonunuYarat (String yol) {
        java.net.URL resimYureli = J5c_37.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasý bulunamadý!"); return null;}
    } // resimÝkonunuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Menü Seçenekleri Yönetimi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Menü çubuðu ve içerik panosunu yaratýp kuralým...
        J5c_37 gösteri = new J5c_37();
        çerçeve.setJMenuBar (gösteri.menüÇubuðunuYarat());
        çerçeve.setContentPane (gösteri.içerikPanosunuYarat());

        // Fýrlayan menüyü yaratýp kuralým...
        gösteri.fýrlayanMenüyüYarat();


        çerçeve.setBounds (500,100, 450, 260);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_37 sýnýfý sonu...