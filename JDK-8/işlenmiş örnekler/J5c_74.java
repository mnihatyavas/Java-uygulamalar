// J5c_74.java: TextInputDemo (MetinGiri�iG�sterisi) �rne�i.

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

/* Gerekli java dosyas�:
 *  J5c_51x2.java=SpringUtilities.java
 */
public class J5c_74 extends JPanel implements ActionListener, FocusListener {
    JTextField caddeMetinSat�r�, il�eMetinSat�r�;
    JFormattedTextField postaKoduMetinSat�r�;
    JSpinner �ehirSayac�;
    boolean adresKurulduMu = false;
    Font normalYaz�Fonu, yat�kYaz�Fonu;
    JLabel adresYaftas�;
    final static int ARALIK = 10;

    public J5c_74() {// Kurucu...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));

        JPanel solYar� = new JPanel() {
            // (Veri giri� alanlar� ve butonlar�n) dikey elastikiyeti olmas�n...
            public Dimension getMaximumSize() {
                Dimension tercihi = getPreferredSize();
                return new Dimension (Integer.MAX_VALUE, tercihi.height);
        }}; // Jpa.. ifadesi sonu...
        solYar�.setLayout (new BoxLayout (solYar�, BoxLayout.PAGE_AXIS));
        solYar�.add (veriGiri�Alanlar�n�Yarat());
        solYar�.add (adresKurSilD��meleriniYarat());

        add (solYar�);
        add (adresG�r�nt�lemeyiYarat());
    } // J5c_74() kurucusu sonu...

    protected JComponent veriGiri�Alanlar�n�Yarat() {
        JPanel panel = new JPanel (new SpringLayout());

        String[] etiketDizgeleri = {"Mh.Cd.Sk.ve kap� no: ", "�l�e: ", "�ehir: ", "Posta kodu: "};

        JLabel[] etiketDizisi = new JLabel[etiketDizgeleri.length];
        JComponent[] veriGiri�Alanlar� = new JComponent[etiketDizgeleri.length];
        int veriGiri�AlanNo = 0;

        // Veri giri� alanlar�n� kural�m...
        caddeMetinSat�r�  = new JTextField();
        caddeMetinSat�r�.setColumns (20); // G�r�n�r 20 kolon ta�arsa yatay kayar...
        veriGiri�Alanlar�[veriGiri�AlanNo++] = caddeMetinSat�r�;

        il�eMetinSat�r� = new JTextField();
        il�eMetinSat�r�.setColumns (20);
        veriGiri�Alanlar�[veriGiri�AlanNo++] = il�eMetinSat�r�;

        String[] �ehirDizgeleri = �ehirDizgeleriniAl();
        �ehirSayac� = new JSpinner (new SpinnerListModel (�ehirDizgeleri));
        veriGiri�Alanlar�[veriGiri�AlanNo++] = �ehirSayac�;

        postaKoduMetinSat�r� = new JFormattedTextField (bi�imleyiciyiYarat ("#####"));
        veriGiri�Alanlar�[veriGiri�AlanNo++] = postaKoduMetinSat�r�;

        // Adres etiketleri veri giri�leriyle ilintilendirilip panele eklenecek...
        for (int i = 0; i < etiketDizgeleri.length; i++) {
            etiketDizisi[i] = new JLabel (etiketDizgeleri[i], JLabel.TRAILING); // Sa�a yana��k...
            etiketDizisi[i].setLabelFor (veriGiri�Alanlar�[i]);
            panel.add (etiketDizisi[i]);
            panel.add (veriGiri�Alanlar�[i]);

            // Herbir veri giri� alan� hareket ve odaklanma dinleyicilerine duyarlanacak...
            JTextField metinSat�r� = null;
            if (veriGiri�Alanlar�[i] instanceof JSpinner) metinSat�r� = metinSat�r�n�Al ((JSpinner)veriGiri�Alanlar�[i]);
            else metinSat�r� = (JTextField)veriGiri�Alanlar�[i];
            metinSat�r�.addActionListener (this);
            metinSat�r�.addFocusListener (this);
        } // for d�ng�s� sonu...
        J5c_51x2.kesifIzgaraYap (panel,
                                        etiketDizgeleri.length, 2,
                                        ARALIK, ARALIK, // ilk x,y konumu...
                                        ARALIK, ARALIK/2); // yatay-tampon, dikey-tampon...
        panel.setBackground (new Color ( (int)(Math.random()*56+200), (int)(Math.random()*56+200), (int)(Math.random()*56+200) )); // �ok a��k renkler...
        return panel;
    } // veriGiri�Alanlar�n�Yarat() metodu sonu...

    public String[] �ehirDizgeleriniAl() {
        String[] �ehirDizgeleri = {
            "Adana (01-322) AD",
            "Ad�yaman (02-416) AM",
            "Afyon (03-272) AF",
            "A�r� (04-472) A�",
            "Amasya (05-358) AS",
            "Ankara (06-312) AN",
            "Antalya (07-478) AT",
            "Artvin (08-466) AR",
            "Ayd�n (09-256) AY",
            "Bal�kesir (10-266) BK",
            "Bilecik (11-228) BC",
            "Bing�l (12-426) BN",
            "Bitlis (13-434) BT",
            "Bolu (14-374) BL",
            "Burdur (15-248) BR",
            "Bursa (16-224) BS",
            "�anakkale (17-286) �N",
            "�ank�r� (18-376) �K",
            "�orum (19-364) �R",
            "Denizli (20-258) DN",
            "Diyarbak�r (21-412) DY",
            "Edirne (22-284) ED",
            "Elaz�� (23-424) EL",
            "Erzincan (24-446) ER",
            "Erzurum (25-442) EZ",
            "Eski�ehir (26-222) ES",
            "Gaziantep (27-342) GZ",
            "Giresun (28-454) GR",
            "G�m��hane (29-456) GM",
            "Hakkari (30-436) HK",
            "Hatay (31-326) HT",
            "Isparta (32-246) IS",
            "��el (33-324) ��",
            "�stanbul-Avr (34-212) �S",
            "�stanbul-And (34-216) �S",
            "�zmir (35-232) �Z",
            "Kars (36-474) KR",
            "Kastamonu (37-366) KS",
            "Kayseri (38-352) KY",
            "K�rklareli (39-288)KK",
            "K�r�ehir (40-386) K�",
        }; // �ehirDizgeleri dizisi sonu...
        return �ehirDizgeleri;
    } // �ehirDizgeleriniAl() metodu sonu...

    // 5 haneli tamsay� giri�i sa�lar...
    protected MaskFormatter bi�imleyiciyiYarat (String kal�p) {
        MaskFormatter bi�imleyici = null;
        try {bi�imleyici = new MaskFormatter (kal�p);
        }catch (ParseException ist) {System.err.println ("Bi�imleyici hatal�: " + ist.getMessage()); System.exit (-1);}
        return bi�imleyici;
    } // bi�imleyiciyiYarat(..) metodu sonu...

    public JFormattedTextField metinSat�r�n�Al (JSpinner saya�) {
        JComponent edit�r = saya�.getEditor();
        if (edit�r instanceof JSpinner.DefaultEditor) return ((JSpinner.DefaultEditor)edit�r).getTextField();
        else {System.err.println ("Hatal� edit�r tipi: " + saya�.getEditor().getClass() + " varsay�l� (DefaultEditor) edit�r de�il!"); return null;}
    } // metinSat�r�n�Al(..) metodu sonu...

    protected JComponent adresKurSilD��meleriniYarat() {
        JPanel panel = new JPanel (new FlowLayout (FlowLayout.TRAILING));

        JButton d��me = new JButton ("Adresi kur");
        d��me.addActionListener (this);
        panel.add (d��me);

        d��me = new JButton ("Adresi sil");
        d��me.addActionListener (this);
        d��me.setActionCommand ("sil");
        panel.add (d��me);

        // Veri giri� alanlar� serilimi SpringLayout'�n aral��� olan 10 yerine
        // d��melerin serilimi FlowLayout i�in aral�k 10-5=5 olacak...
        panel.setBorder (BorderFactory.createEmptyBorder (0, 0, ARALIK-5, ARALIK-5));
        panel.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // A��k renkler...
        return panel;
    } // adresKurSilD��meleriniYarat() metodu sonu...

    protected JComponent adresG�r�nt�lemeyiYarat() {
        JPanel panel = new JPanel (new BorderLayout());
        adresYaftas� = new JLabel();
        adresYaftas�.setHorizontalAlignment (JLabel.CENTER);
        normalYaz�Fonu = adresYaftas�.getFont().deriveFont (Font.PLAIN, 16.0f);
        yat�kYaz�Fonu = normalYaz�Fonu.deriveFont (Font.ITALIC);
        g�r�nt�y�G�ncelle();

        // Bi�imli adresli yaftal� panel'i sa�Yar�'daki BorderLayout'a serimleyelim...
        panel.setBorder (BorderFactory.createEmptyBorder (
                ARALIK/2, // �ste 5 bo�luk...
                0, // Solda bo�luk yok...
                ARALIK/2, // Alta 5 bo�luk...
                0)); // Sa�da bo�luk yok...
        panel.add (new JSeparator (JSeparator.VERTICAL), BorderLayout.LINE_START);
        panel.add (adresYaftas�, BorderLayout.CENTER);
        panel.setPreferredSize (new Dimension (200,150));
        panel.setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        return panel;
    } // adresG�r�nt�lemeyiYarat() metodu sonu...

    protected void g�r�nt�y�G�ncelle() {
        adresYaftas�.setText (adresiBi�imlendir());
        if (adresKurulduMu) adresYaftas�.setFont (normalYaz�Fonu);
        else adresYaftas�.setFont (yat�kYaz�Fonu);
        adresYaftas�.setForeground (Color.WHITE);
    } // g�r�nt�y�G�ncelle() metodu sonu...

    protected String adresiBi�imlendir() {
        if (!adresKurulduMu) return "Hen�z kurulu bir adres yok!";

        String cadde = caddeMetinSat�r�.getText();
        String il�e = il�eMetinSat�r�.getText();
        String �ehir = (String)�ehirSayac�.getValue();
        String pkodu = postaKoduMetinSat�r�.getText();
        String bo�Dizge = "";

        if ((cadde == null) || bo�Dizge.equals (cadde)) cadde = "<em>(Mh.Cd.Sk.Kap�no girilmedi)</em>";
        if ((il�e == null) || bo�Dizge.equals (il�e)) il�e = "<em>(�l�e ad� girilmedi)</em>";
        if ((�ehir == null) || bo�Dizge.equals (�ehir)) �ehir = "<em>(�ehir ad� girilmedi)</em>";
        else {int k�saltmaEndeksi = �ehir.indexOf (')') + 2;
            �ehir = �ehir.substring (k�saltmaEndeksi, k�saltmaEndeksi + 2);
        } // else karar� sonu...
        if ((pkodu == null) || bo�Dizge.equals (pkodu)) pkodu = "";

        StringBuffer tampon = new StringBuffer();
        tampon.append ("<html><p align=center>");
        tampon.append (cadde);
        tampon.append ("<br>");
        tampon.append (il�e);
        tampon.append (" - ");
        tampon.append (�ehir);
        tampon.append (" ");
        tampon.append (pkodu);
        tampon.append ("</p></html>");

        return tampon.toString();
    } // adresiBi�imlendir() metodu sonu...

    // Kur/sil d��mesi t�kland���nda veya herhangibir metin sat�r� Enter'land���nda �a�r�l�r...
    public void actionPerformed (ActionEvent olay) {
        if ("sil".equals (olay.getActionCommand())) {
            adresKurulduMu = false;
            caddeMetinSat�r�.setText ("");
            il�eMetinSat�r�.setText ("");
            // PK bi�imli oldu�undan setText("") de�il null'lanmal�...
            postaKoduMetinSat�r�.setValue (null);
        }else adresKurulduMu = true;
        g�r�nt�y�G�ncelle();
    } // actionPerformed(..) haz�r metodu sonu...

    // Bir veri giri� alan�na t�kland���nda �a�r�l�r...
    public void focusGained (FocusEvent olay) {
        Component komponent = olay.getComponent();
        if (komponent instanceof JFormattedTextField) birazdanSe�elim (komponent);
        else if (komponent instanceof JTextField) ((JTextField)komponent).selectAll();
    } // focusGained(..) haz�r metodu sonu...

    // Bi�imli metin alan� odaklanma yan etkileri nedeniyle biraz sicimli run'la oyalanal�m...
    protected void birazdanSe�elim (Component par�a) {
        if (par�a instanceof JFormattedTextField) {
            final JFormattedTextField bi�imliAlan = (JFormattedTextField)par�a;
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {bi�imliAlan.selectAll();}
            }); // Swi.. ifadesi sonu...
        } // if karar� sonu...
    } // birazdanSe�elim(..) metodu sonu...

    // FocusListener aray�z� override/esge�me haz�r metodu...
    public void focusLost (FocusEvent ald�rma) {}

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Metin Giri�i G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_74()); // Referanss�z kurucu nesnesi...
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    }  // main(..) metodu sonu...
} // J5c_74 s�n�f� sonu...