// J5c_37.java: PopupMenuDemo (F�rlayanMen�G�sterisi) �rne�i.

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

/* Gereken resim dosyas�: resim/orta.gif
 *
 * J5c_33/Men�Demo'yla ayn�d�r, ancak Timer zamanlay�c� her saniye
 * se�ili men�n�n tam yolunu metin alan�na yans�t�r...
 */
public class J5c_37 implements ActionListener, ItemListener {
    JTextArea metinAlan�;
    JScrollPane kayd�rmaPanosu;
    String yeniSat�r = "\n";
    public final static int B�R_SAN�YE = 1000;

    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u;
        JMenu men�, altMen�, altAltMen�;
        JMenuItem men�Birimi;
        JRadioButtonMenuItem rbMen�Birimi;
        JCheckBoxMenuItem cbMen�Birimi;

        // Men� �ubu�unu yaratal�m...
        men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        //Men�-1'i yarat�p i�eriklerini kural�m ve men� �ubu�una ekleyelim...
        men� = new JMenu ("�lk Men�");
        men�.setMnemonic (KeyEvent.VK_M);
        men�.getAccessibleContext().setAccessibleDescription ("Bu programda men� birimleri ve alt men�leri olan tek men�");
        men��ubu�u.add (men�);

        // Grup-1 men� birimleri...
        men�Birimi = new JMenuItem ("Sadece metinli men� birimi", KeyEvent.VK_S);
        //men�Birimi.setMnemonic (KeyEvent.VK_S); // Gerekmez, zira kurucununda tan�mland�...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_1, ActionEvent.ALT_MASK)); // Heryerden Alt_1...
        men�Birimi.getAccessibleContext().setAccessibleDescription ("Bu men� birimi ger�ekte ba�kaca bir �ey yapmaz");
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        ImageIcon ikon = resim�konunuYarat ("resim/orta.gif");
        men�Birimi = new JMenuItem ("Hem metinli hem de ikonlu men� birimi", ikon);
        men�Birimi.setMnemonic (KeyEvent.VK_H);
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        men�Birimi = new JMenuItem (ikon); // Sadece ikonlu men� birimi...
        //men�Birimi.setMnemonic (KeyEvent.VK_D); // Gerekmez, zira g�r�nm�yor...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_2, ActionEvent.ALT_MASK)); // G�r�nen ve heryerden Alt_2...
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        // Grup-2 radyo men� birimleri...
        men�.addSeparator();
        ButtonGroup grup = new ButtonGroup();

        rbMen�Birimi = new JRadioButtonMenuItem ("�lk radyo butonlu men� birimi");
        rbMen�Birimi.setSelected (true); // �lk anda bu se�ili/�entikli...
        rbMen�Birimi.setMnemonic (KeyEvent.VK_R);
        grup.add (rbMen�Birimi);
        rbMen�Birimi.addActionListener (this);
        men�.add (rbMen�Birimi);

        rbMen�Birimi = new JRadioButtonMenuItem ("�kinci radyo butonlu men� birimi");
        rbMen�Birimi.setMnemonic (KeyEvent.VK_C);
        grup.add (rbMen�Birimi);
        rbMen�Birimi.addActionListener (this);
        men�.add (rbMen�Birimi);

        // Grup-3 �entik men� birimleri...
        // Bu grup birimleri ItemListener'a duyarl�; �entikli/�entiksiz tespiti de yap�yor...
        men�.addSeparator();
        cbMen�Birimi = new JCheckBoxMenuItem ("�lk �entik kutulu men� birimi");
        cbMen�Birimi.setMnemonic (KeyEvent.VK_T);
        cbMen�Birimi.addItemListener (this);
        men�.add (cbMen�Birimi);

        cbMen�Birimi = new JCheckBoxMenuItem ("�kinci �entik kutulu men� birimi");
        cbMen�Birimi.setMnemonic (KeyEvent.VK_K);
        cbMen�Birimi.addItemListener (this);
        men�.add (cbMen�Birimi);

        // AltMen�...
        men�.addSeparator();
        altMen� = new JMenu ("Bir alt men�");
        altMen�.setMnemonic (KeyEvent.VK_A);
        men�.add (altMen�);

        men�Birimi = new JMenuItem ("Alt men�deki ilk men� birimi");
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_3, ActionEvent.ALT_MASK)); // Heryerden Alt_3...
        men�Birimi.addActionListener (this);
        altMen�.add (men�Birimi);

        men�Birimi = new JMenuItem ("Alt men�deki ikinci men� birimi");
        men�Birimi.addActionListener (this);
        altMen�.add (men�Birimi);

        // AltAltMen�...
        altMen�.addSeparator();
        altAltMen� = new JMenu ("Bir alt alt men�");
        altMen�.add (altAltMen�);

        men�Birimi = new JMenuItem ("Alt alt men�n�n tek men� birimi");
        men�Birimi.addActionListener (this);
        altAltMen�.add (men�Birimi);

        //Men�-2'yi i�eriksiz yarat�p kural�m...
        men� = new JMenu ("�kinci Men�");
        men�.setMnemonic (KeyEvent.VK_N);
        men�.getAccessibleContext().setAccessibleDescription ("Bu men� �imdilik tamamen i�eriksizdir");
        men��ubu�u.add (men�);

        Timer zamanlay�c� = new Timer (B�R_SAN�YE, new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                MenuElement[] yol = MenuSelectionManager.defaultManager().getSelectedPath();
                for (int i = 0; i < yol.length; i++ ) {
                    if (yol[i].getComponent() instanceof javax.swing.JMenuItem) {
                        JMenuItem birimMen� = (JMenuItem)yol[i].getComponent();
                        if ("".equals (birimMen�.getText())) metinAlan�.append ("SADECE_�KON�K MEN� B�R�M�-> ");
                        else metinAlan�.append (birimMen�.getText() + "-> ");
                    } // d��-if karar� sonu...
                } // for d�ng�s� sonu...
                if (yol.length > 0) metinAlan�.append (yeniSat�r);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // zamanlay�c� ifadesi sonu...

        zamanlay�c�.start(); // Her saniyede duyarl�d�r...
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...

    // Kayd�rmal� metin alanl� i�erik panosu/paneli yarat�r ve kurar...
    public Container i�erikPanosunuYarat() {
        JPanel i�erikPaneli = new JPanel (new BorderLayout());
        i�erikPaneli.setOpaque (true);
        metinAlan� = new JTextArea (5, 30);
        metinAlan�.setForeground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );
        metinAlan�.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        metinAlan�.setEditable (false); // M�dahalesiz...
        kayd�rmaPanosu = new JScrollPane (metinAlan�);
        i�erikPaneli.add (kayd�rmaPanosu, BorderLayout.CENTER);

        return i�erikPaneli;
    } // i�erikPanosunuYarat() metodu sonu...

    public void f�rlayanMen�y�Yarat() {
        JMenuItem men�Birimi;

        // F�rlayan men�y� yaratal�m...
        JPopupMenu z�playanMen� = new JPopupMenu ();

        // 2 adet men� birimini yarat�p f�rlayana ekleyelim...
        men�Birimi = new JMenuItem ("F�rlayan men� birimi #1");
        men�Birimi.addActionListener (this);
        z�playanMen�.add (men�Birimi);

        men�Birimi = new JMenuItem ("F�rlayan men� birimi #2");
        men�Birimi.addActionListener (this);
        z�playanMen�.add (men�Birimi);

        // F�rlayan men� olu�mas� i�in metin alan�n� fare t�klamas�na duyarl� k�lal�m...
        MouseListener f�rlayanDinleyici = new F�rlayanDinleyici (z�playanMen�);
        metinAlan�.addMouseListener (f�rlayanDinleyici);
    } // f�rlayanMen�y�Yarat() metodu sonu...

    class F�rlayanDinleyici extends MouseAdapter {
        JPopupMenu z�playan;

        F�rlayanDinleyici (JPopupMenu z�playanMen�) {z�playan = z�playanMen�;}
        // Fare sa�-t�klamaya duyarl�...
        public void mousePressed (MouseEvent olay) {f�rlayanG�r�nebilir (olay);}
        public void mouseReleased (MouseEvent olay) {f�rlayanG�r�nebilir (olay);}

        private void f�rlayanG�r�nebilir (MouseEvent olay) {
            if (olay.isPopupTrigger()) {z�playan.show (olay.getComponent(), olay.getX(), olay.getY());}
        } // f�rlayanG�r�nebilir(..) metodu sonu...
    } // F�rlayanDinleyici s�n�f� sonu...

    // T�klanan men� biriminin (import) s�n�f kaynak tam ad�n� metin alan�na yans�t�r...
    public void actionPerformed (ActionEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Yeni bir aksiyon olay� tespit edildi." + yeniSat�r +
                "-->Olay kayna��: " + kaynak.getText() + " (bir <<" + kaynak.getClass().getName() + ">> s�n�f� tiplemesi)";
        metinAlan�.append (rapor + yeniSat�r);
        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...

    // T�klanan �entikli men� biriminin (import) s�n�f kaynak tam ad�n� ve �entikli-se�ildi/�entiksiz-se�ilmedi durumunu metin alan�na yans�t�r...
    public void itemStateChanged (ItemEvent olay) {
        JMenuItem kaynak = (JMenuItem)(olay.getSource());
        String rapor = "Yeni bir birim olay� tespit edildi." + yeniSat�r +
                "-->Olay kayna��: " + kaynak.getText() + " (bir <<" + kaynak.getClass().getName() + ">> s�n�f� tiplemesi)" + yeniSat�r +
                "-->Yeni durum: " + ((olay.getStateChange() == ItemEvent.SELECTED) ? "se�ildi/�entikli" : "se�ilmedi/�entiksiz");
        metinAlan�.append (rapor + yeniSat�r);
        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
    } // itemStateChanged(..) haz�r metodu sonu...

    protected static ImageIcon resim�konunuYarat (String yol) {
        java.net.URL resimYureli = J5c_37.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas� bulunamad�!"); return null;}
    } // resim�konunuYarat(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Men� Se�enekleri Y�netimi G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // Men� �ubu�u ve i�erik panosunu yarat�p kural�m...
        J5c_37 g�steri = new J5c_37();
        �er�eve.setJMenuBar (g�steri.men��ubu�unuYarat());
        �er�eve.setContentPane (g�steri.i�erikPanosunuYarat());

        // F�rlayan men�y� yarat�p kural�m...
        g�steri.f�rlayanMen�y�Yarat();


        �er�eve.setBounds (500,100, 450, 260);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_37 s�n�f� sonu...