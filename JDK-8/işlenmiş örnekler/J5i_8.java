// J5i_8.java: ListDataEventDemo (ListeVerisiOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.List;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JSplitPane;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListDataListener;
import javax.swing.event.ListDataEvent;

import java.net.URL;

/* Gereken resim dosyalar�:
 *   resim/�ste.gif
 *   resim/Alta.gif
 */
public class J5i_8 extends JPanel implements ListSelectionListener {
    private JList liste;
    private DefaultListModel listeModeli;
    private static final String ekleDizgesi = "Ekle";
    private static final String silDizgesi = "Sil";
    private static final String yukar�Dizgesi = "Yukar�";
    private static final String a�a��Dizgesi = "A�a��";
    private JButton ekleD��mesi;
    private JButton silD��mesi;
    private JButton yukar�D��mesi;
    private JButton a�a��D��mesi;
    private JTextField yerAd�;
    private JTextArea kay�tD�k�m�;
    static private String yeniSat�r = "\n";

    public J5i_8() {// Kurucu...
        super (new BorderLayout());

        // Liste modelini yarat�p doldural�m...
        listeModeli = new DefaultListModel();
        listeModeli.addElement ("Whistler, Canada");
        listeModeli.addElement ("Jackson Hole, Wyoming");
        listeModeli.addElement ("Squaw Valley, California");
        listeModeli.addElement ("Telluride, Colorado");
        listeModeli.addElement ("Taos, New Mexico");
        listeModeli.addElement ("Snowbird, Utah");
        listeModeli.addElement ("Chamonix, France");
        listeModeli.addElement ("Banff, Canada");
        listeModeli.addElement ("Arapahoe Basin, Colorado");
        listeModeli.addElement ("Kirkwood, California");
        listeModeli.addElement ("Sun Valley, Idaho");
        listeModeli.addElement ("Toroslar, Mersin");
        listeModeli.addElement ("Temenyeri Park�, Bursa");
        listeModeli.addElement ("Banaz� Gedi�i, Malatya");
        listeModeli.addListDataListener (new ListeDinleyicim()); // Liste dinleyicisine duyarl�...

        // Listeyi modelden yarat�p kayd�rac�na koyal�m...
        liste = new JList (listeModeli);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        liste.setSelectedIndex (0);
        liste.addListSelectionListener (this); // Liste Se�iciye duyarl�...
        JScrollPane listeKayd�rac� = new JScrollPane (liste);

        // Liste de�i�tirme d��melerini (ekle-sil-yukar�-a�a��) yaratal�m...
        ekleD��mesi = new JButton (ekleDizgesi);
        ekleD��mesi.setActionCommand (ekleDizgesi);
        ekleD��mesi.addActionListener (new EkleDinleyicim());

        silD��mesi = new JButton (silDizgesi);
        silD��mesi.setActionCommand (silDizgesi);
        silD��mesi.addActionListener (new SilDinleyicim());

        ImageIcon ikon = resim�konuYarat ("resim/�ste");
        if (ikon != null) {
            yukar�D��mesi = new JButton (ikon);
            yukar�D��mesi.setMargin (new Insets (0,0,0,0));
        }else yukar�D��mesi = new JButton ("Bir �ste ta��");
        yukar�D��mesi.setToolTipText ("Akt�el se�ili liste eleman�n� bir �st konuma kayd�r�r.");
        yukar�D��mesi.setActionCommand (yukar�Dizgesi);
        yukar�D��mesi.addActionListener (new Yukar�A�a��Dinleyicim()); //�zel dinleyicime duyarl�...

        ikon = resim�konuYarat ("resim/Alta");
        if (ikon != null) {
            a�a��D��mesi = new JButton (ikon);
            a�a��D��mesi.setMargin (new Insets (0,0,0,0));
        }else a�a��D��mesi = new JButton ("Bir alta ta��");
        a�a��D��mesi.setToolTipText ("Akt�el se�ili liste eleman�n� bir alt konuma kayd�r�r.");
        a�a��D��mesi.setActionCommand (a�a��Dizgesi);
        a�a��D��mesi.addActionListener (new Yukar�A�a��Dinleyicim()); //�zel dinleyicime duyarl�...

        JPanel yukar�A�a��Paneli = new JPanel (new GridLayout (2, 1));
        yukar�A�a��Paneli.add (yukar�D��mesi);
        yukar�A�a��Paneli.add (a�a��D��mesi);

        // Yeni yer adlar� /se�ebilece�iniz/girebilece�iniz metin sat�r�n� yaratal�m...
        yerAd� = new JTextField (15);
        yerAd�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f) );
        yerAd�.addActionListener (new EkleDinleyicim()); // �zel dinleyicime duyarl�...
        String isim = listeModeli.getElementAt (liste.getSelectedIndex()).toString();
        yerAd�.setText (isim);

        // Metin sat�rl� d��meleri varsay�l� FlowLayout serilimle d��me paneline ekleyelim...
        JPanel d��mePaneli = new JPanel();
        d��mePaneli.add (yerAd�);
        d��mePaneli.add (ekleD��mesi);
        d��mePaneli.add (silD��mesi);
        d��mePaneli.add (yukar�A�a��Paneli);

        // Kay�t d�k�mlerini sergileyece�imiz metin alanl� kayd�rac� yaratal�m...
        kay�tD�k�m� = new JTextArea (10, 20);
        kay�tD�k�m�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kay�tD�k�m�.setForeground (Color.WHITE);
        JScrollPane d�k�mKayd�rac� = new JScrollPane (kay�tD�k�m�);

        // D�k�m kayd�rac� ve liste i�in b�lmeli/paravanl� bir pano yaratal�m...
        JSplitPane paravanl�Pano = new JSplitPane (
                JSplitPane.VERTICAL_SPLIT, listeKayd�rac�, d�k�mKayd�rac�);
        paravanl�Pano.setResizeWeight (0.5);

        // Sonu� komponentleri i�erik panosuna ekleyelim...
        add (d��mePaneli, BorderLayout.PAGE_START);
        add (paravanl�Pano, BorderLayout.CENTER);
    } // J5i_8() kurucusu sonu....

    class ListeDinleyicim implements ListDataListener {
        public void contentsChanged (ListDataEvent olay) {
            kay�tD�k�m�.append ("Liste eleman� konumu de�i�ti: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSat�r); 
            kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
        } // contentsChanged(..) haz�r metodu sonu...

        public void intervalAdded (ListDataEvent olay) {
            kay�tD�k�m�.append ("Eklenen eleman konumu: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSat�r); 
            kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
        } // intervalAdded(..) haz�r metodu sonu...

        public void intervalRemoved (ListDataEvent olay) {
            kay�tD�k�m�.append ("Silinen eleman-lar aral���: "
                    + olay.getIndex0() + ", " + olay.getIndex1() + yeniSat�r); 
            kay�tD�k�m�.setCaretPosition (kay�tD�k�m�.getDocument().getLength());
        } // intervalRemoved(..) haz�r metodu sonu...
    } // ListeDinleyicim s�n�f� sonu...

    public void valueChanged (ListSelectionEvent olay) {
        if (olay.getValueIsAdjusting() == false) {// Liste kayd�ra� hareketi de�ilse...

            if (liste.getSelectedIndex() == -1) {
            // Se�im yok (t�m liste silinmi�) ise Sil Yukar� A�a�� d��meleri aktifsizlenir...
                silD��mesi.setEnabled (false);
                yukar�D��mesi.setEnabled (false);
                a�a��D��mesi.setEnabled (false);
                yerAd�.setText ("");
            }else if (liste.getSelectedIndices().length > 1) {
            // �oklu se�im iseYukar�-A�a�� d��meleri aktifsizle�ir...
                silD��mesi.setEnabled (true);
                yukar�D��mesi.setEnabled (false);
                a�a��D��mesi.setEnabled (false);
            }else {// Tekli se�im ise t�m d��meler aktiftir...
                silD��mesi.setEnabled (true);
                yukar�D��mesi.setEnabled (true);
                a�a��D��mesi.setEnabled (true);
                yerAd�.setText (liste.getSelectedValue().toString());
            } // i�-if-else.. karar� sonu...
        } // D��-if karar� sonu...
    } // valueChanged(..) haz�r metodu sonu...

    class EkleDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            if (yerAd�.getText().equals("")) {// Metin sat�r� bo�ken Ekle t�klan�rsa bipler...
                Toolkit.getDefaultToolkit().beep();
                return;
            } // if karar� sonu...

            int endeks = liste.getSelectedIndex();
            int ebat = listeModeli.getSize();

            // Listeden se�ili yoksa veya son eleman se�iliyse, Ekle liste sonuna ekler...
            if (endeks == -1 || (endeks+1 == ebat)) {
                listeModeli.addElement (yerAd�.getText());
                liste.setSelectedIndex (ebat);

            // De�ilse, Ekle se�ilenden sonraya ekler ve yeni eleman� se�er...
            }else {
                listeModeli.insertElementAt (yerAd�.getText(), endeks+1);
                liste.setSelectedIndex (endeks+1);
            } // if-else karar� sonu...
        } // actionPerformed(..) haz�r metodu sonu...
    } // EkleDinleyicim s�n�f� sonu...

    class SilDinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            // Listede mevcut ve se�ili tekli-�oklu elemanlar� siler...
            ListSelectionModel se�meModeli = liste.getSelectionModel();
            int ilkSe�ilen = se�meModeli.getMinSelectionIndex();
            int sonSe�ilen = se�meModeli.getMaxSelectionIndex();
            listeModeli.removeRange (ilkSe�ilen, sonSe�ilen);

            int ebat = listeModeli.size();

            if (ebat == 0) {// T�m liste silindiyse Sil-Yukar�-A�a�� d��meleri etkisizle�tirilir...
                silD��mesi.setEnabled (false);
                yukar�D��mesi.setEnabled (false);
                a�a��D��mesi.setEnabled (false);
            }else {// Silmeden sonra se�ilen ilk (sonuncuysa bir �nceki) olmal�...
                if (ilkSe�ilen == listeModeli.getSize()) ilkSe�ilen--;
                liste.setSelectedIndex (ilkSe�ilen);
            } // if-else karar� sonu...
        } // actionPerformed(..) haz�r metodu sonu...
    } // SilDinleyicim s�n�f� sonu...

    protected static ImageIcon resim�konuYarat (String resminAd�) {
        String resminYolu = resminAd� + ".gif";
        URL resminYureli = J5i_8.class.getResource (resminYolu);

        if (resminYureli == null) {
            System.err.println (resminYolu + " adl� resim bulunamad�!");
            return null;
        }else return new ImageIcon (resminYureli);
    } // resim�konuYarat(..) metodu sonu...

    class Yukar�A�a��Dinleyicim implements ActionListener {
        public void actionPerformed (ActionEvent olay) {
            int ta��nacakEndeks = liste.getSelectedIndex();

            if (olay.getActionCommand().equals (yukar�Dizgesi)) {// Yukar� ta��...
                if (ta��nacakEndeks != 0) {// �lk endeks ise ald�rma...
                    de�i�Toku� (ta��nacakEndeks, ta��nacakEndeks-1);
                    liste.setSelectedIndex (ta��nacakEndeks-1);
                    liste.ensureIndexIsVisible (ta��nacakEndeks-1);
                } // i�-if karar� sonu...
            }else {// A�a�� ta��...
                if (ta��nacakEndeks != listeModeli.getSize()-1) {// Son endeks ise ald�rma...
                    de�i�Toku� (ta��nacakEndeks, ta��nacakEndeks+1);
                    liste.setSelectedIndex (ta��nacakEndeks+1);
                    liste.ensureIndexIsVisible (ta��nacakEndeks+1);
                } // i�-if karar� sonu...
            } // if-else karar� sonu...
        } // actionPerformed(..) haz�r metodu sonu...
    } // Yukar�A�a��Dinleyicim s�n�f� sonu...

    private void de�i�Toku� (int a, int b) {
        Object aNesnesi = listeModeli.getElementAt (a);
        Object bNesnesi = listeModeli.getElementAt (b);
        listeModeli.set (a, bNesnesi);
        listeModeli.set (b, aNesnesi);
    } // de�i�Toku�(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Liste Verisi Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_8(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        yeni��erikPanosu.setMinimumSize (new Dimension (
                yeni��erikPanosu.getPreferredSize().width, 100));
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible(true);
    } // yaratVeG�sterGUI() netodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..)
} // J5i_8 s�n�f� sonu...