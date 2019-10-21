// J5c_75.java: TextSamplerDemo (Metin�rnekleyiciG�steri) �rne�i.

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
 * J5c_75x.html=TextSamplerDemoHelp.html ("resim/K�rm�z�D�k.gif" resmini al�r),
 * "resim/Ku�.gif" resmi ve
 * "resim/ses.gif" resmi.
 */
public class J5c_75 extends JPanel implements ActionListener {
    private String yeniSat�r = "\n";

    protected static final String metinSat�r�Dizgesi = "JTextField";
    protected static final String �ifreSat�r�Dizgesi = "JPasswordField";
    protected static final String bi�imliMetinSat�r�Dizgesi = "JFormattedTextField";
    protected static final String butonDizgesi = "JButton";

    protected JLabel aksiyonEtiketi;

    public J5c_75() {// Kurucu...
        setLayout (new BorderLayout());

        // Bir metin sat�r� yarat�yoruz...
        JTextField metinSat�r� = new JTextField (15);
        metinSat�r�.setActionCommand (metinSat�r�Dizgesi);
        metinSat�r�.addActionListener (this); // Veri giri�ini dinleyiciye duyarlayal�m...

        // Bir �ifre sat�r� yarat�yoruz...
        JPasswordField �ifreSat�r� = new JPasswordField (15);
        �ifreSat�r�.setActionCommand (�ifreSat�r�Dizgesi);
        �ifreSat�r�.addActionListener (this); // �ifre giri�ini dinleyiciye duyarlayal�m...

        // Bir bi�imli metin sat�r� yarat�yoruz...
        JFormattedTextField bi�imliMetinSat�r� = new JFormattedTextField (Calendar.getInstance().getTime());
        bi�imliMetinSat�r�.setActionCommand (metinSat�r�Dizgesi);
        bi�imliMetinSat�r�.addActionListener (this); // Tarih giri�ini dinleyiciye duyarlayal�m...

        // Yukardaki veri giri� etiket ba�l�klar�n� yarat�yoruz...
        JLabel metinSat�r�Etiketi = new JLabel (metinSat�r�Dizgesi + ": ");
        metinSat�r�Etiketi.setLabelFor (metinSat�r�);
        JLabel �ifreSat�r�Etiketi = new JLabel (�ifreSat�r�Dizgesi + ": ");
        �ifreSat�r�Etiketi.setLabelFor (�ifreSat�r�);
        JLabel bi�imliMetinSat�r�Etiketi = new JLabel (bi�imliMetinSat�r�Dizgesi + ": ");
        bi�imliMetinSat�r�Etiketi.setLabelFor (bi�imliMetinSat�r�);

        // Aksiyon olaylar�na duyarl� mesaj veren etiketi de yarat�yoruz...
        aksiyonEtiketi = new JLabel ("Yukardaki bir veri giri� sat�r�na giri� ve Enter'lay�n!");
        aksiyonEtiketi.setBorder (BorderFactory.createEmptyBorder (10,0,0,0));

        // Yukardaki veri giri� sat�rlar�n�, etiketlerini ve aksiyon etiketini �zgara �antal�-s�n�rl� olarak panele serimleyelim...
        JPanel sol�stPanel = new JPanel();
        GridBagLayout �zgara�antas� = new GridBagLayout();
        GridBagConstraints �zgara�antas�S�n�rlamalar� = new GridBagConstraints();

        sol�stPanel.setLayout (�zgara�antas�);

        JLabel[] etiketler = {metinSat�r�Etiketi, �ifreSat�r�Etiketi, bi�imliMetinSat�r�Etiketi};
        JTextField[] veriGiri�Sat�rlar� = {metinSat�r�, �ifreSat�r�, bi�imliMetinSat�r�};
        komponentleriPaneleEkle (etiketler, veriGiri�Sat�rlar�, �zgara�antas�, sol�stPanel);

        �zgara�antas�S�n�rlamalar�.gridwidth = GridBagConstraints.REMAINDER;
        �zgara�antas�S�n�rlamalar�.anchor = GridBagConstraints.WEST;
        �zgara�antas�S�n�rlamalar�.weightx = 1.0;
        sol�stPanel.add (aksiyonEtiketi, �zgara�antas�S�n�rlamalar�);
        sol�stPanel.setBorder (
                BorderFactory.createCompoundBorder (
                        BorderFactory.createTitledBorder ("M�dahaleli Metin Sat�rlar�"),
                        BorderFactory.createEmptyBorder (5,5,5,5)));

        // Ba�lang�� metni i�eren m�dahaleli bir metin alan� yaratal�m...
        JTextArea metinAlan� = new JTextArea (
                "Bu m�dahaleli bir JTextArea metin alan�d�r. " +
                "Bir metin alan�n�n \"plain/tekd�ze\" bir metin komponenti olmas� demek, " +
                "ona istenilen herhangi bir ba�lang�� yaz� fonu �zelli�i verilebilse de " +
                "bu fonun t�m metne ayn� �ekilde uygulanmas� demektir."
        ); // JTe.. ifadesi sonu...
        metinAlan�.setFont (new Font ("Serif", Font.ITALIC, 16)); // Bu yaz� fonu art�k tekd�zedir...
        metinAlan�.setLineWrap (true); // Sat�r ta�mas� alta sars�n...
        metinAlan�.setWrapStyleWord (true); // Alta sarma kelime b�t�nl���yle olsun...
        metinAlan�.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // A��k renkler...
        JScrollPane alanKayd�rmaPanosu = new JScrollPane (metinAlan�);
        alanKayd�rmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        alanKayd�rmaPanosu.setPreferredSize (new Dimension (250, 250));
        alanKayd�rmaPanosu.setBorder (
                BorderFactory.createCompoundBorder (
                        BorderFactory.createCompoundBorder (
                                BorderFactory.createTitledBorder ("Tekd�ze Yat�k M�dahaleli Metin Alan�"),
                                BorderFactory.createEmptyBorder (5,5,5,5)),
                        alanKayd�rmaPanosu.getBorder()));

        // Sa� �stte m�dahalesiz bir edit�r panosu yaratal�m...
        JEditorPane edit�rPanosu = edit�rPanosuYarat();
        JScrollPane edit�rKayd�rmaPanosu = new JScrollPane (edit�rPanosu);
        edit�rKayd�rmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        edit�rKayd�rmaPanosu.setPreferredSize (new Dimension (250, 145));
        edit�rKayd�rmaPanosu.setMinimumSize (new Dimension (10, 10));

        // Sa� altta m�dahaleli bir metin panosu yaratal�m...
        JTextPane metinPanosu = metinPanosuYarat();
        JScrollPane panoKayd�rmaPanosu = new JScrollPane (metinPanosu);
        panoKayd�rmaPanosu.setVerticalScrollBarPolicy (
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        panoKayd�rmaPanosu.setPreferredSize (new Dimension (250, 155));
        panoKayd�rmaPanosu.setMinimumSize (new Dimension (10, 10));

        // Edit�r ve metin panolar�n� aras� d��me b�lmeli bir ay�rma panosuna koyal�m...
        JSplitPane ay�rmaPanosu = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, // Arada d��meli b�lme...
                edit�rKayd�rmaPanosu, // �st b�lmede edit�r panosu...
                panoKayd�rmaPanosu); // Alt b�lmede pano kayd�rma panosu...
        ay�rmaPanosu.setOneTouchExpandable (true); // D��meli esnemeli...
        ay�rmaPanosu.setResizeWeight (0.5);

        // 2 b�lmeli ay�rma panosunu yarataca��m�z sa� pano'ya ekleyelim...
        JPanel sa�Pano = new JPanel (new GridLayout (1,0));
        sa�Pano.add (ay�rmaPanosu);
        sa�Pano.setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createTitledBorder ("Stilli M�dahale(siz/li) Metin Alanlar�"),
                BorderFactory.createEmptyBorder (5,5,5,5)));

        // Sol ve sa� panolar� (super) i�erik panosuna ekleyelim...
        JPanel solPano = new JPanel (new BorderLayout());
        solPano.add (sol�stPanel, BorderLayout.PAGE_START);
        solPano.add (alanKayd�rmaPanosu, BorderLayout.CENTER);

        add (solPano, BorderLayout.LINE_START);
        add (sa�Pano, BorderLayout.LINE_END);
    } // 5c_75() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        String �nekStr = "Yapt���n�z veri giri�i: \"";
        if (metinSat�r�Dizgesi.equals (olay.getActionCommand())) {
            JTextField kaynak = (JTextField)olay.getSource();
            aksiyonEtiketi.setText (�nekStr + kaynak.getText() + "\"");
        }else if (�ifreSat�r�Dizgesi.equals (olay.getActionCommand())) {
            JPasswordField kaynak = (JPasswordField)olay.getSource();
            aksiyonEtiketi.setText (�nekStr + new String (kaynak.getPassword()) + "\"");
        }else if (butonDizgesi.equals (olay.getActionCommand()))
            Toolkit.getDefaultToolkit().beep();
    } // actionPerformed(..) haz�r metodu sonu...

    private void komponentleriPaneleEkle (
            JLabel[] etiketler,
            JTextField[] metinSat�rlar�,
            GridBagLayout �zgara�antas�,
            Container kab) {
        GridBagConstraints �zgara�antas�S�n�rlamalar� = new GridBagConstraints();
        �zgara�antas�S�n�rlamalar�.anchor = GridBagConstraints.EAST; // Do�uya demir at...
        int etiketSay�s� = etiketler.length;

        for (int i = 0; i < etiketSay�s�; i++) {
            // S�n�rlanan etiketleri kab'a ekleyelim...
            �zgara�antas�S�n�rlamalar�.gridwidth = GridBagConstraints.RELATIVE; // Izgara geni�li�i...
            �zgara�antas�S�n�rlamalar�.fill = GridBagConstraints.NONE; // Dolgu...
            �zgara�antas�S�n�rlamalar�.weightx = 0.0; // Yatay a��rl�k...
            kab.add (etiketler[i], �zgara�antas�S�n�rlamalar�);

            // S�n�rlanan metin sat�rlar�n� kab'a ekleyelim...
            �zgara�antas�S�n�rlamalar�.gridwidth = GridBagConstraints.REMAINDER;
            �zgara�antas�S�n�rlamalar�.fill = GridBagConstraints.HORIZONTAL;
            �zgara�antas�S�n�rlamalar�.weightx = 1.0;
            kab.add (metinSat�rlar�[i], �zgara�antas�S�n�rlamalar�);
        } // for d�ng�s� sonu...
    } // komponentleriPaneleEkle(..) metodu sonu...

    private JEditorPane edit�rPanosuYarat() {
        JEditorPane edit�rPanosu = new JEditorPane();
        edit�rPanosu.setEditable (false); // M�dahalesiz...
        URL yurelDosyas� = J5c_75.class.getResource ("J5c_75x.html");
        if (yurelDosyas� != null) {
            try {edit�rPanosu.setPage (yurelDosyas�);
            }catch (IOException ist) {System.err.println ("Okunamayan bozuk bir yurel dosyas�: [" + yurelDosyas� + "]");}
        }else System.out.println ("[" + yurelDosyas� + "] adl� html yurel dosyas� bulunamad�!");

        return edit�rPanosu;
    } // edit�rPanosuYarat() metodu sonu...

    private JTextPane metinPanosuYarat() {
        String[] ba�lang��Dizgeleri =
                { "Bu m�dahaleli bir JTextPane (metin panosu)'dur, ", // Normal yaz� fonu...
                  "yani di�er bir ", // Yat�k yaz� fonu...
                  "stilli ", // Koyu yaz� fonu...
                  "metin ", // K���k yaz� fonu...
                  "komponenti, ", // B�y�k yaz� fonu...
                  "ki g�m�l� komponentleri " + yeniSat�r, // Normal yaz� fonu...
                  " " + yeniSat�r, // T�klanunca bipleyen ses butonu...
                  "...ve g�m�l� resim ikonlar�n� destekler." + yeniSat�r, // Normal yaz� fonu...
                  " ", // Ku� resim ikonu...
                  yeniSat�r + "JTextPane metin panosu StyledEditorKit stilli edit�r arac� " + // Kalan� normal yaz� fonu...
                  "ve StyledDocument stilli d�k�man� kullanan JEditorPane edit�r panosunun " +
                  "bir alt s�n�f� olup, bu nesnelerle etkile�imli metodlar� kapsar."
                }; // Str.. dizisi sonu...

        String[] ba�lang��Stilleri ={"regular","italic","bold","small","large","regular","button","regular","icon","regular"};

        JTextPane metinPanosu = new JTextPane();
        StyledDocument stilliD�k�man = metinPanosu.getStyledDocument();
        d�k�manaStilleriEkle (stilliD�k�man);

        try {for (int i=0; i < ba�lang��Dizgeleri.length; i++)
                stilliD�k�man.insertString (stilliD�k�man.getLength(), ba�lang��Dizgeleri[i],
                        stilliD�k�man.getStyle (ba�lang��Stilleri[i]));
        }catch (BadLocationException ist) {System.err.println ("HATA: Ba�lang�� (stilli) metinleri metin alan�na yerle�tiremiyorum!");}
        metinPanosu.setBackground (new Color ( (int)(Math.random()*156+100), (int)(Math.random()*156+100), (int)(Math.random()*156+100) )); // A��k renkler...
        return metinPanosu;
    } // metinPanosuYarat() metodu sonu...

    protected void d�k�manaStilleriEkle (StyledDocument stilliD�k�man) {
        // Baz� stilleri d�k�mana ekleyelim...
        Style varsay�l� = StyleContext.getDefaultStyleContext().getStyle (StyleContext.DEFAULT_STYLE);

        Style normal = stilliD�k�man.addStyle ("regular", varsay�l�);
        StyleConstants.setFontFamily (varsay�l�, "SansSerif");

        Style stil = stilliD�k�man.addStyle ("italic", normal);
        StyleConstants.setItalic (stil, true);

        stil = stilliD�k�man.addStyle ("bold", normal);
        StyleConstants.setBold (stil, true);

        stil = stilliD�k�man.addStyle ("small", normal);
        StyleConstants.setFontSize (stil, 10);

        stil = stilliD�k�man.addStyle ("large", normal);
        StyleConstants.setFontSize (stil, 16);

        stil = stilliD�k�man.addStyle ("icon", normal);
        StyleConstants.setAlignment (stil, StyleConstants.ALIGN_CENTER);
        ImageIcon resim�konu = resim�konuYarat ("resim/Ku�.gif", "konu�kan papa�an");
        if (resim�konu != null) StyleConstants.setIcon (stil, resim�konu);

        stil = stilliD�k�man.addStyle ("button", normal);
        StyleConstants.setAlignment (stil, StyleConstants.ALIGN_CENTER);
        ImageIcon ses�konu = resim�konuYarat ("resim/ses.gif", "bip sesi");
        JButton d��me = new JButton();
        if (ses�konu != null) d��me.setIcon (ses�konu);
        else d��me.setText ("B���P");
        d��me.setCursor (Cursor.getDefaultCursor());
        d��me.setMargin (new Insets (0,0,0,0));
        d��me.setActionCommand (butonDizgesi);
        d��me.addActionListener (this); // Dinleyiciye duyarlayal�m...
        StyleConstants.setComponent (stil, d��me);
    } // d�k�manaStilleriEkle(..) metodu sonu...

    protected static ImageIcon resim�konuYarat (String yol, String izah) {
        URL resimYureli = J5c_75.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli, izah);
        else {System.out.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Metin �rnekleyici G�steri");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_75()); // Kurucuyu �a��r�r...
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz� yok...
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_75 s�n�f� sonu...