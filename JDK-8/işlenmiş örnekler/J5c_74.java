// J5c_74.java: TextInputDemo (MetinGiriþiGösterisi) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.SpinnerListModel;
import javax.swing.JLabel;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.BoxLayout;
import javax.swing.SpringLayout;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;

import javax.swing.text.MaskFormatter;

import java.text.ParseException;

/* Gerekli java dosyasý:
 *  J5c_51x2.java=SpringUtilities.java
 */
public class J5c_74 extends JPanel implements ActionListener, FocusListener {
    JTextField caddeMetinSatýrý, ilçeMetinSatýrý;
    JFormattedTextField postaKoduMetinSatýrý;
    JSpinner þehirSayacý;
    boolean adresKurulduMu = false;
    Font normalYazýFonu, yatýkYazýFonu;
    JLabel adresYaftasý;
    final static int ARALIK = 10;

    public J5c_74() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        JPanel solYarý = new JPanel() {
            // (Veri giriþ alanlarý ve butonlarýn) dikey elastikiyeti olmasýn...
            public Dimension getMaximumSize() {
                Dimension tercihi = getPreferredSize();
                return new Dimension (Integer.MAX_VALUE, tercihi.height);
        }}; // Jpa.. ifadesi sonu...
        solYarý.setLayout (new BoxLayout (solYarý, BoxLayout.PAGE_AXIS));
        solYarý.add (veriGiriþAlanlarýnýYarat());
        solYarý.add (adresKurSilDüðmeleriniYarat());

        add (solYarý);
        add (adresGörüntülemeyiYarat());
    } // J5c_74() kurucusu sonu...

    protected JComponent veriGiriþAlanlarýnýYarat() {
        JPanel panel = new JPanel (new SpringLayout());

        String[] etiketDizgeleri = {"Mh.Cd.Sk.ve kapý no: ", "Ýlçe: ", "Þehir: ", "Posta kodu: "};

        JLabel[] etiketDizisi = new JLabel[etiketDizgeleri.length];
        JComponent[] veriGiriþAlanlarý = new JComponent[etiketDizgeleri.length];
        int veriGiriþAlanNo = 0;

        // Veri giriþ alanlarýný kuralým...
        caddeMetinSatýrý  = new JTextField();
        caddeMetinSatýrý.setColumns (20); // Görünür 20 kolon taþarsa yatay kayar...
        veriGiriþAlanlarý[veriGiriþAlanNo++] = caddeMetinSatýrý;

        ilçeMetinSatýrý = new JTextField();
        ilçeMetinSatýrý.setColumns (20);
        veriGiriþAlanlarý[veriGiriþAlanNo++] = ilçeMetinSatýrý;

        String[] þehirDizgeleri = þehirDizgeleriniAl();
        þehirSayacý = new JSpinner (new SpinnerListModel (þehirDizgeleri));
        veriGiriþAlanlarý[veriGiriþAlanNo++] = þehirSayacý;

        postaKoduMetinSatýrý = new JFormattedTextField (biçimleyiciyiYarat ("#####"));
        veriGiriþAlanlarý[veriGiriþAlanNo++] = postaKoduMetinSatýrý;

        // Adres etiketleri veri giriþleriyle ilintilendirilip panele eklenecek...
        for (int i = 0; i < etiketDizgeleri.length; i++) {
            etiketDizisi[i] = new JLabel (etiketDizgeleri[i], JLabel.TRAILING); // Saða yanaþýk...
            etiketDizisi[i].setLabelFor (veriGiriþAlanlarý[i]);
            panel.add (etiketDizisi[i]);
            panel.add (veriGiriþAlanlarý[i]);

            // Herbir veri giriþ alaný hareket ve odaklanma dinleyicilerine duyarlanacak...
            JTextField metinSatýrý = null;
            if (veriGiriþAlanlarý[i] instanceof JSpinner) metinSatýrý = metinSatýrýnýAl ((JSpinner)veriGiriþAlanlarý[i]);
            else metinSatýrý = (JTextField)veriGiriþAlanlarý[i];
            metinSatýrý.addActionListener (this);
            metinSatýrý.addFocusListener (this);
        } // for döngüsü sonu...
        J5c_51x2.kesifIzgaraYap (panel,
                                        etiketDizgeleri.length, 2,
                                        ARALIK, ARALIK, // ilk x,y konumu...
                                        ARALIK, ARALIK/2); // yatay-tampon, dikey-tampon...
        panel.setBackground (new Color ( (int)(Math.random()*56+200), (int)(Math.random()*56+200), (int)(Math.random()*56+200) )); // Çok açýk renkler...
        return panel;
    } // veriGiriþAlanlarýnýYarat() metodu sonu...

    public String[] þehirDizgeleriniAl() {
        String[] þehirDizgeleri = {
            "Adana (01-322) AD",
            "Adýyaman (02-416) AM",
            "Afyon (03-272) AF",
            "Aðrý (04-472) AÐ",
            "Amasya (05-358) AS",
            "Ankara (06-312) AN",
            "Antalya (07-478) AT",
            "Artvin (08-466) AR",
            "Aydýn (09-256) AY",
            "Balýkesir (10-266) BK",
            "Bilecik (11-228) BC",
            "Bingöl (12-426) BN",
            "Bitlis (13-434) BT",
            "Bolu (14-374) BL",
            "Burdur (15-248) BR",
            "Bursa (16-224) BS",
            "Çanakkale (17-286) ÇN",
            "Çankýrý (18-376) ÇK",
            "Çorum (19-364) ÇR",
            "Denizli (20-258) DN",
            "Diyarbakýr (21-412) DY",
            "Edirne (22-284) ED",
            "Elazýð (23-424) EL",
            "Erzincan (24-446) ER",
            "Erzurum (25-442) EZ",
            "Eskiþehir (26-222) ES",
            "Gaziantep (27-342) GZ",
            "Giresun (28-454) GR",
            "Gümüþhane (29-456) GM",
            "Hakkari (30-436) HK",
            "Hatay (31-326) HT",
            "Isparta (32-246) IS",
            "Ýçel (33-324) ÝÇ",
            "Ýstanbul-Avr (34-212) ÝS",
            "Ýstanbul-And (34-216) ÝS",
            "Ýzmir (35-232) ÝZ",
            "Kars (36-474) KR",
            "Kastamonu (37-366) KS",
            "Kayseri (38-352) KY",
            "Kýrklareli (39-288)KK",
            "Kýrþehir (40-386) KÞ",
        }; // þehirDizgeleri dizisi sonu...
        return þehirDizgeleri;
    } // þehirDizgeleriniAl() metodu sonu...

    // 5 haneli tamsayý giriþi saðlar...
    protected MaskFormatter biçimleyiciyiYarat (String kalýp) {
        MaskFormatter biçimleyici = null;
        try {biçimleyici = new MaskFormatter (kalýp);
        }catch (ParseException ist) {System.err.println ("Biçimleyici hatalý: " + ist.getMessage()); System.exit (-1);}
        return biçimleyici;
    } // biçimleyiciyiYarat(..) metodu sonu...

    public JFormattedTextField metinSatýrýnýAl (JSpinner sayaç) {
        JComponent editör = sayaç.getEditor();
        if (editör instanceof JSpinner.DefaultEditor) return ((JSpinner.DefaultEditor)editör).getTextField();
        else {System.err.println ("Hatalý editör tipi: " + sayaç.getEditor().getClass() + " varsayýlý (DefaultEditor) editör deðil!"); return null;}
    } // metinSatýrýnýAl(..) metodu sonu...

    protected JComponent adresKurSilDüðmeleriniYarat() {
        JPanel panel = new JPanel (new FlowLayout (FlowLayout.TRAILING));

        JButton düðme = new JButton ("Adresi kur");
        düðme.addActionListener (this);
        panel.add (düðme);

        düðme = new JButton ("Adresi sil");
        düðme.addActionListener (this);
        düðme.setActionCommand ("sil");
        panel.add (düðme);

        // Veri giriþ alanlarý serilimi SpringLayout'ýn aralýðý olan 10 yerine
        // düðmelerin serilimi FlowLayout için aralýk 10-5=5 olacak...
        panel.setBorder (BorderFactory.createEmptyBorder (0, 0, ARALIK-5, ARALIK-5));
        panel.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Açýk renkler...
        return panel;
    } // adresKurSilDüðmeleriniYarat() metodu sonu...

    protected JComponent adresGörüntülemeyiYarat() {
        JPanel panel = new JPanel (new BorderLayout());
        adresYaftasý = new JLabel();
        adresYaftasý.setHorizontalAlignment (JLabel.CENTER);
        normalYazýFonu = adresYaftasý.getFont().deriveFont (Font.PLAIN, 16.0f);
        yatýkYazýFonu = normalYazýFonu.deriveFont (Font.ITALIC);
        görüntüyüGüncelle();

        // Biçimli adresli yaftalý panel'i saðYarý'daki BorderLayout'a serimleyelim...
        panel.setBorder (BorderFactory.createEmptyBorder (
                ARALIK/2, // Üste 5 boþluk...
                0, // Solda boþluk yok...
                ARALIK/2, // Alta 5 boþluk...
                0)); // Saðda boþluk yok...
        panel.add (new JSeparator (JSeparator.VERTICAL), BorderLayout.LINE_START);
        panel.add (adresYaftasý, BorderLayout.CENTER);
        panel.setPreferredSize (new Dimension (200,150));
        panel.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        return panel;
    } // adresGörüntülemeyiYarat() metodu sonu...

    protected void görüntüyüGüncelle() {
        adresYaftasý.setText (adresiBiçimlendir());
        if (adresKurulduMu) adresYaftasý.setFont (normalYazýFonu);
        else adresYaftasý.setFont (yatýkYazýFonu);
        adresYaftasý.setForeground (Color.WHITE);
    } // görüntüyüGüncelle() metodu sonu...

    protected String adresiBiçimlendir() {
        if (!adresKurulduMu) return "Henüz kurulu bir adres yok!";

        String cadde = caddeMetinSatýrý.getText();
        String ilçe = ilçeMetinSatýrý.getText();
        String þehir = (String)þehirSayacý.getValue();
        String pkodu = postaKoduMetinSatýrý.getText();
        String boþDizge = "";

        if ((cadde == null) || boþDizge.equals (cadde)) cadde = "<em>(Mh.Cd.Sk.Kapýno girilmedi)</em>";
        if ((ilçe == null) || boþDizge.equals (ilçe)) ilçe = "<em>(Ýlçe adý girilmedi)</em>";
        if ((þehir == null) || boþDizge.equals (þehir)) þehir = "<em>(Þehir adý girilmedi)</em>";
        else {int kýsaltmaEndeksi = þehir.indexOf (')') + 2;
            þehir = þehir.substring (kýsaltmaEndeksi, kýsaltmaEndeksi + 2);
        } // else kararý sonu...
        if ((pkodu == null) || boþDizge.equals (pkodu)) pkodu = "";

        StringBuffer tampon = new StringBuffer();
        tampon.append ("<html><p align=center>");
        tampon.append (cadde);
        tampon.append ("<br>");
        tampon.append (ilçe);
        tampon.append (" - ");
        tampon.append (þehir);
        tampon.append (" ");
        tampon.append (pkodu);
        tampon.append ("</p></html>");

        return tampon.toString();
    } // adresiBiçimlendir() metodu sonu...

    // Kur/sil düðmesi týklandýðýnda veya herhangibir metin satýrý Enter'landýðýnda çaðrýlýr...
    public void actionPerformed (ActionEvent olay) {
        if ("sil".equals (olay.getActionCommand())) {
            adresKurulduMu = false;
            caddeMetinSatýrý.setText ("");
            ilçeMetinSatýrý.setText ("");
            // PK biçimli olduðundan setText("") deðil null'lanmalý...
            postaKoduMetinSatýrý.setValue (null);
        }else adresKurulduMu = true;
        görüntüyüGüncelle();
    } // actionPerformed(..) hazýr metodu sonu...

    // Bir veri giriþ alanýna týklandýðýnda çaðrýlýr...
    public void focusGained (FocusEvent olay) {
        Component komponent = olay.getComponent();
        if (komponent instanceof JFormattedTextField) birazdanSeçelim (komponent);
        else if (komponent instanceof JTextField) ((JTextField)komponent).selectAll();
    } // focusGained(..) hazýr metodu sonu...

    // Biçimli metin alaný odaklanma yan etkileri nedeniyle biraz sicimli run'la oyalanalým...
    protected void birazdanSeçelim (Component parça) {
        if (parça instanceof JFormattedTextField) {
            final JFormattedTextField biçimliAlan = (JFormattedTextField)parça;
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {biçimliAlan.selectAll();}
            }); // Swi.. ifadesi sonu...
        } // if kararý sonu...
    } // birazdanSeçelim(..) metodu sonu...

    // FocusListener arayüzü override/esgeçme hazýr metodu...
    public void focusLost (FocusEvent aldýrma) {}

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Metin Giriþi Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_74()); // Referanssýz kurucu nesnesi...
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    }  // main(..) metodu sonu...
} // J5c_74 sýnýfý sonu...