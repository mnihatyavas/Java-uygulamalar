// J5c_75.java: TextSamplerDemo (MetinÖrnekleyiciGösteri) örneði.

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.Container;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JEditorPane;
import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.text.Style;
import javax.swing.text.StyleContext;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.text.BadLocationException;

import java.net.URL;
import java.util.Calendar;
import java.io.IOException;

/* Gereken dosyalar:
 * J5c_75x.html=TextSamplerDemoHelp.html ("resim/KýrmýzýDük.gif" resmini alýr),
 * "resim/Kuþ.gif" resmi ve
 * "resim/ses.gif" resmi.
 */
public class J5c_75 extends JPanel implements ActionListener {
    private String yeniSatýr = "\n";

    protected static final String metinSatýrýDizgesi = "JTextField";
    protected static final String þifreSatýrýDizgesi = "JPasswordField";
    protected static final String biçimliMetinSatýrýDizgesi = "JFormattedTextField";
    protected static final String butonDizgesi = "JButton";

    protected JLabel aksiyonEtiketi;

    public J5c_75() {// Kurucu...
        setLayout (new BorderLayout());

        // Bir metin satýrý yaratýyoruz...
        JTextField metinSatýrý = new JTextField (15);
        metinSatýrý.setActionCommand (metinSatýrýDizgesi);
        metinSatýrý.addActionListener (this); // Veri giriþini dinleyiciye duyarlayalým...

        // Bir þifre satýrý yaratýyoruz...
        JPasswordField þifreSatýrý = new JPasswordField (15);
        þifreSatýrý.setActionCommand (þifreSatýrýDizgesi);
        þifreSatýrý.addActionListener (this); // Þifre giriþini dinleyiciye duyarlayalým...

        // Bir biçimli metin satýrý yaratýyoruz...
        JFormattedTextField biçimliMetinSatýrý = new JFormattedTextField (Calendar.getInstance().getTime());
        biçimliMetinSatýrý.setActionCommand (metinSatýrýDizgesi);
        biçimliMetinSatýrý.addActionListener (this); // Tarih giriþini dinleyiciye duyarlayalým...

        // Yukardaki veri giriþ etiket baþlýklarýný yaratýyoruz...
        JLabel metinSatýrýEtiketi = new JLabel (metinSatýrýDizgesi + ": ");
        metinSatýrýEtiketi.setLabelFor (metinSatýrý);
        JLabel þifreSatýrýEtiketi = new JLabel (þifreSatýrýDizgesi + ": ");
        þifreSatýrýEtiketi.setLabelFor (þifreSatýrý);
        JLabel biçimliMetinSatýrýEtiketi = new JLabel (biçimliMetinSatýrýDizgesi + ": ");
        biçimliMetinSatýrýEtiketi.setLabelFor (biçimliMetinSatýrý);

        // Aksiyon olaylarýna duyarlý mesaj veren etiketi de yaratýyoruz...
        aksiyonEtiketi = new JLabel ("Yukardaki bir veri giriþ satýrýna giriþ ve Enter'layýn!");
        aksiyonEtiketi.setBorder (BorderFactory.createEmptyBorder (10,0,0,0));

        // Yukardaki veri giriþ satýrlarýný, etiketlerini ve aksiyon etiketini ýzgara çantalý-sýnýrlý olarak panele serimleyelim...
        JPanel solÜstPanel = new JPanel();
        GridBagLayout ýzgaraÇantasý = new GridBagLayout();
        GridBagConstraints ýzgaraÇantasýSýnýrlamalarý = new GridBagConstraints();

        solÜstPanel.setLayout (ýzgaraÇantasý);

        JLabel[] etiketler = {metinSatýrýEtiketi, þifreSatýrýEtiketi, biçimliMetinSatýrýEtiketi};
        JTextField[] veriGiriþSatýrlarý = {metinSatýrý, þifreSatýrý, biçimliMetinSatýrý};
        komponentleriPaneleEkle (etiketler, veriGiriþSatýrlarý, ýzgaraÇantasý, solÜstPanel);

        ýzgaraÇantasýSýnýrlamalarý.gridwidth = GridBagConstraints.REMAINDER;
        ýzgaraÇantasýSýnýrlamalarý.anchor = GridBagConstraints.WEST;
        ýzgaraÇantasýSýnýrlamalarý.weightx = 1.0;
        solÜstPanel.add (aksiyonEtiketi, ýzgaraÇantasýSýnýrlamalarý);
        solÜstPanel.setBorder (
                BorderFactory.createCompoundBorder (
                        BorderFactory.createTitledBorder ("Müdahaleli Metin Satýrlarý"),
                        BorderFactory.createEmptyBorder (5,5,5,5)));

        // Baþlangýç metni içeren müdahaleli bir metin alaný yaratalým...
        JTextArea metinAlaný = new JTextArea (
                "Bu müdahaleli bir JTextArea metin alanýdýr. " +
                "Bir metin alanýnýn \"plain/tekdüze\" bir metin komponenti olmasý demek, " +
                "ona istenilen herhangi bir baþlangýç yazý fonu özelliði verilebilse de " +
                "bu fonun tüm metne ayný þekilde uygulanmasý demektir."
        ); // JTe.. ifadesi sonu...
        metinAlaný.setFont (new Font ("Serif", Font.ITALIC, 16)); // Bu yazý fonu artýk tekdüzedir...
        metinAlaný.setLineWrap (true); // Satýr taþmasý alta sarsýn...
        metinAlaný.setWrapStyleWord (true); // Alta sarma kelime bütünlüðüyle olsun...
        metinAlaný.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Açýk renkler...
        JScrollPane alanKaydýrmaPanosu = new JScrollPane (metinAlaný);
        alanKaydýrmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        alanKaydýrmaPanosu.setPreferredSize (new Dimension (250, 250));
        alanKaydýrmaPanosu.setBorder (
                BorderFactory.createCompoundBorder (
                        BorderFactory.createCompoundBorder (
                                BorderFactory.createTitledBorder ("Tekdüze Yatýk Müdahaleli Metin Alaný"),
                                BorderFactory.createEmptyBorder (5,5,5,5)),
                        alanKaydýrmaPanosu.getBorder()));

        // Sað üstte müdahalesiz bir editör panosu yaratalým...
        JEditorPane editörPanosu = editörPanosuYarat();
        JScrollPane editörKaydýrmaPanosu = new JScrollPane (editörPanosu);
        editörKaydýrmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        editörKaydýrmaPanosu.setPreferredSize (new Dimension (250, 145));
        editörKaydýrmaPanosu.setMinimumSize (new Dimension (10, 10));

        // Sað altta müdahaleli bir metin panosu yaratalým...
        JTextPane metinPanosu = metinPanosuYarat();
        JScrollPane panoKaydýrmaPanosu = new JScrollPane (metinPanosu);
        panoKaydýrmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panoKaydýrmaPanosu.setPreferredSize (new Dimension (250, 155));
        panoKaydýrmaPanosu.setMinimumSize (new Dimension (10, 10));

        // Editör ve metin panolarýný arasý düðme bölmeli bir ayýrma panosuna koyalým...
        JSplitPane ayýrmaPanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, // Arada düðmeli bölme...
                editörKaydýrmaPanosu, // Üst bölmede editör panosu...
                panoKaydýrmaPanosu); // Alt bölmede pano kaydýrma panosu...
        ayýrmaPanosu.setOneTouchExpandable (true); // Düðmeli esnemeli...
        ayýrmaPanosu.setResizeWeight (0.5);

        // 2 bölmeli ayýrma panosunu yaratacaðýmýz sað pano'ya ekleyelim...
        JPanel saðPano = new JPanel (new GridLayout (1,0));
        saðPano.add (ayýrmaPanosu);
        saðPano.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createTitledBorder ("Stilli Müdahale(siz/li) Metin Alanlarý"),
                BorderFactory.createEmptyBorder (5,5,5,5)));

        // Sol ve sað panolarý (super) içerik panosuna ekleyelim...
        JPanel solPano = new JPanel (new BorderLayout());
        solPano.add (solÜstPanel, BorderLayout.PAGE_START);
        solPano.add (alanKaydýrmaPanosu, BorderLayout.CENTER);

        add (solPano, BorderLayout.LINE_START);
        add (saðPano, BorderLayout.LINE_END);
    } // 5c_75() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String önekStr = "Yaptýðýnýz veri giriþi: \"";
        if (metinSatýrýDizgesi.equals (olay.getActionCommand())) {
            JTextField kaynak = (JTextField)olay.getSource();
            aksiyonEtiketi.setText (önekStr + kaynak.getText() + "\"");
        }else if (þifreSatýrýDizgesi.equals (olay.getActionCommand())) {
            JPasswordField kaynak = (JPasswordField)olay.getSource();
            aksiyonEtiketi.setText (önekStr + new String (kaynak.getPassword()) + "\"");
        }else if (butonDizgesi.equals (olay.getActionCommand()))
            Toolkit.getDefaultToolkit().beep();
    } // actionPerformed(..) hazýr metodu sonu...

    private void komponentleriPaneleEkle (
            JLabel[] etiketler,
            JTextField[] metinSatýrlarý,
            GridBagLayout ýzgaraÇantasý,
            Container kab) {
        GridBagConstraints ýzgaraÇantasýSýnýrlamalarý = new GridBagConstraints();
        ýzgaraÇantasýSýnýrlamalarý.anchor = GridBagConstraints.EAST; // Doðuya demir at...
        int etiketSayýsý = etiketler.length;

        for (int i = 0; i < etiketSayýsý; i++) {
            // Sýnýrlanan etiketleri kab'a ekleyelim...
            ýzgaraÇantasýSýnýrlamalarý.gridwidth = GridBagConstraints.RELATIVE; // Izgara geniþliði...
            ýzgaraÇantasýSýnýrlamalarý.fill = GridBagConstraints.NONE; // Dolgu...
            ýzgaraÇantasýSýnýrlamalarý.weightx = 0.0; // Yatay aðýrlýk...
            kab.add (etiketler[i], ýzgaraÇantasýSýnýrlamalarý);

            // Sýnýrlanan metin satýrlarýný kab'a ekleyelim...
            ýzgaraÇantasýSýnýrlamalarý.gridwidth = GridBagConstraints.REMAINDER;
            ýzgaraÇantasýSýnýrlamalarý.fill = GridBagConstraints.HORIZONTAL;
            ýzgaraÇantasýSýnýrlamalarý.weightx = 1.0;
            kab.add (metinSatýrlarý[i], ýzgaraÇantasýSýnýrlamalarý);
        } // for döngüsü sonu...
    } // komponentleriPaneleEkle(..) metodu sonu...

    private JEditorPane editörPanosuYarat() {
        JEditorPane editörPanosu = new JEditorPane();
        editörPanosu.setEditable (false); // Müdahalesiz...
        URL yurelDosyasý = J5c_75.class.getResource ("J5c_75x.html");
        if (yurelDosyasý != null) {
            try {editörPanosu.setPage (yurelDosyasý);
            }catch (IOException ist) {System.err.println ("Okunamayan bozuk bir yurel dosyasý: [" + yurelDosyasý + "]");}
        }else System.out.println ("[" + yurelDosyasý + "] adlý html yurel dosyasý bulunamadý!");

        return editörPanosu;
    } // editörPanosuYarat() metodu sonu...

    private JTextPane metinPanosuYarat() {
        String[] baþlangýçDizgeleri =
                { "Bu müdahaleli bir JTextPane (metin panosu)'dur, ", // Normal yazý fonu...
                  "yani diðer bir ", // Yatýk yazý fonu...
                  "stilli ", // Koyu yazý fonu...
                  "metin ", // Küçük yazý fonu...
                  "komponenti, ", // Büyük yazý fonu...
                  "ki gömülü komponentleri " + yeniSatýr, // Normal yazý fonu...
                  " " + yeniSatýr, // Týklanunca bipleyen ses butonu...
                  "...ve gömülü resim ikonlarýný destekler." + yeniSatýr, // Normal yazý fonu...
                  " ", // Kuþ resim ikonu...
                  yeniSatýr + "JTextPane metin panosu StyledEditorKit stilli editör aracý " + // Kalaný normal yazý fonu...
                  "ve StyledDocument stilli dökümaný kullanan JEditorPane editör panosunun " +
                  "bir alt sýnýfý olup, bu nesnelerle etkileþimli metodlarý kapsar."
                }; // Str.. dizisi sonu...

        String[] baþlangýçStilleri ={"regular","italic","bold","small","large","regular","button","regular","icon","regular"};

        JTextPane metinPanosu = new JTextPane();
        StyledDocument stilliDöküman = metinPanosu.getStyledDocument();
        dökümanaStilleriEkle (stilliDöküman);

        try {for (int i=0; i < baþlangýçDizgeleri.length; i++)
                stilliDöküman.insertString (stilliDöküman.getLength(), baþlangýçDizgeleri[i],
                        stilliDöküman.getStyle (baþlangýçStilleri[i]));
        }catch (BadLocationException ist) {System.err.println ("HATA: Baþlangýç (stilli) metinleri metin alanýna yerleþtiremiyorum!");}
        metinPanosu.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // Açýk renkler...
        return metinPanosu;
    } // metinPanosuYarat() metodu sonu...

    protected void dökümanaStilleriEkle (StyledDocument stilliDöküman) {
        // Bazý stilleri dökümana ekleyelim...
        Style varsayýlý = StyleContext.getDefaultStyleContext().getStyle (StyleContext.DEFAULT_STYLE);

        Style normal = stilliDöküman.addStyle ("regular", varsayýlý);
        StyleConstants.setFontFamily (varsayýlý, "SansSerif");

        Style stil = stilliDöküman.addStyle ("italic", normal);
        StyleConstants.setItalic (stil, true);

        stil = stilliDöküman.addStyle ("bold", normal);
        StyleConstants.setBold (stil, true);

        stil = stilliDöküman.addStyle ("small", normal);
        StyleConstants.setFontSize (stil, 10);

        stil = stilliDöküman.addStyle ("large", normal);
        StyleConstants.setFontSize (stil, 16);

        stil = stilliDöküman.addStyle ("icon", normal);
        StyleConstants.setAlignment (stil, StyleConstants.ALIGN_CENTER);
        ImageIcon resimÝkonu = resimÝkonuYarat ("resim/Kuþ.gif", "konuþkan papaðan");
        if (resimÝkonu != null) StyleConstants.setIcon (stil, resimÝkonu);

        stil = stilliDöküman.addStyle ("button", normal);
        StyleConstants.setAlignment (stil, StyleConstants.ALIGN_CENTER);
        ImageIcon sesÝkonu = resimÝkonuYarat ("resim/ses.gif", "bip sesi");
        JButton düðme = new JButton();
        if (sesÝkonu != null) düðme.setIcon (sesÝkonu);
        else düðme.setText ("BÝÝÝP");
        düðme.setCursor (Cursor.getDefaultCursor());
        düðme.setMargin (new Insets (0,0,0,0));
        düðme.setActionCommand (butonDizgesi);
        düðme.addActionListener (this); // Dinleyiciye duyarlayalým...
        StyleConstants.setComponent (stil, düðme);
    } // dökümanaStilleriEkle(..) metodu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol, String izah) {
        URL resimYureli = J5c_75.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.out.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Metin Örnekleyici Gösteri");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_75()); // Kurucuyu çaðýrýr...
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazý yok...
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_75 sýnýfý sonu...