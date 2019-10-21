// J5c_79.java: TreeDemo (A�a�G�sterisi) �rne�i.

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;
/* Gerekli html dosyalar�:
 * html/TreeDemoHelp.html
 *    html/arnold.html
 *    html/bloch.html
 *    html/chan.html
 *    html/jls.html
 *    html/swingtutorial.html
 *    html/tutorial.html
 *    html/tutorialcont.html
 *    html/vm.html
 */
public class J5c_79 extends JPanel implements TreeSelectionListener {
    private JEditorPane edit�rPanosu;
    private JTree a�a�;
    private URL yard�mYureli;

    // A�a��daki 3 boolean� da ileti sat�r� arg�man giri�iyle "true" k�labilirsin...
    private static boolean yaz�ls�nM� = false;
    private static boolean sistemBakVeHissetKullan�ls�nM� = false;
    private static boolean sat�rStiliyleOynans�nM� = false;
    private static String sat�rStili = "Horizontal"; // Angled/A��l� ve None/Hi� da yap�labilir...

    public J5c_79() {// Kurucu...
        super (new GridLayout (1,0));

        // A�a� dallar�n� yaratal�m...
        DefaultMutableTreeNode g�vde = new DefaultMutableTreeNode ("Java Kitap Serileri");
        dallar�Yarat (g�vde);

        // Her keresinde tek se�imlik bir a�a� yaratal�m...
        a�a� = new JTree (g�vde);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // A�a� dal se�imlerini dinleyiciye duyarlayal�m...
        a�a�.addTreeSelectionListener (this);

        if (sat�rStiliyleOynans�nM�) {
            System.out.println ("Atanan yeni sat�r stili = " + sat�rStili);
            a�a�.putClientProperty ("JTree.sat�rStili", sat�rStili);
        } // if karar� sonu...

        // A��l�nca ta�acak a�a� dallar� i�in kayd�rma panosu yaratal�m...
        JScrollPane a�a�Kayd�rmaPanosu = new JScrollPane (a�a�);

        // Pencere alt�na HTML g�r�nt�leme m�dahalesiz ve kayd�rmal� edit�r panosu kural�m...
        edit�rPanosu = new JEditorPane();
        edit�rPanosu.setEditable (false); // M�dahalesiz...
        yard�m�G�ster();
        JScrollPane htmlKayd�rmaPanosu = new JScrollPane (edit�rPanosu);

        // �ki kayd�rma panosunu ara b�lmesi ay�rma panosuna yarat�p ekleyelim...
        JSplitPane ay�rmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ay�rmaPanosu.setTopComponent (a�a�Kayd�rmaPanosu);
        ay�rmaPanosu.setBottomComponent (htmlKayd�rmaPanosu);

        Dimension enk���kEbat = new Dimension (100, 50);
        htmlKayd�rmaPanosu.setMinimumSize (enk���kEbat);
        a�a�Kayd�rmaPanosu.setMinimumSize (enk���kEbat);
        ay�rmaPanosu.setDividerLocation (100); 
        ay�rmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Tam komponentli ay�rma panosunu i�erik panosuna ekleyelim...
        add (ay�rmaPanosu);
    } // J5c_79() kurucusu sonu...

    private void dallar�Yarat (DefaultMutableTreeNode g�vde) {
        DefaultMutableTreeNode katagori = null;
        DefaultMutableTreeNode kitap = null;

        // Birinci katagori dal�...
        katagori = new DefaultMutableTreeNode ("Java Programc�lar� i�in Kitaplar");
        g�vde.add (katagori);

        // Katagori-1 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itimi: Temel Bilgiler �zerine K�sa Bir Kurs", "html/tutorial.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itimi Devam Ediyor: JDK'nin Devam�", "html/tutorialcont.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-3:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JFC Swing E�itimi: GUI'leri Kurmak ��in Bir Rehber", "html/swingtutorial.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-4:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Etkili Java Programlama Dili Rehberi", "html/bloch.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-5:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("�leri Java Programlama Dili", "html/arnold.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-6:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geli�tiricileri Y�ll���", "html/chan.html"));
        katagori.add (kitap);

        // �kinci katagori dal�...
        katagori = new DefaultMutableTreeNode ("Java Kullan�c�lar� i�in Kitaplar");
        g�vde.add (katagori);

        // Katagori-2 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JVM Java Sanal Makine �zellikleri", "html/vm.html"));
        katagori.add(kitap);

        // Katagori-2 Kitap-2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin �zellikleri", "html/jls.html"));
        katagori.add(kitap);

        // ���nc� katagori dal�...
        katagori = new DefaultMutableTreeNode ("T�m Java E�itim Kitaplar�");
        g�vde.add (katagori);

        // Katagori-3 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("B�y�k E�itim Endeksi", "html/index.html"));
        katagori.add (kitap);
    } // dallar�Yarat(..) metodu sonu...

    // TreeSelectionListener aray�z� haz�r metodu...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode dal = (DefaultMutableTreeNode)a�a�.getLastSelectedPathComponent();

        if (dal == null) return;

        Object dalBilgisi = dal.getUserObject();
        if (dal.isLeaf()) {
            KitapBilgisi kitap = (KitapBilgisi)dalBilgisi;
            yurelDosyas�n�G�ster (kitap.kitapYureli);
            if (yaz�ls�nM�) System.out.print (kitap.kitapYureli + ":  \n    ");
        }else yurelDosyas�n�G�ster (yard�mYureli); 
        if (yaz�ls�nM�) System.out.println (dalBilgisi.toString());
    } // valueChanged(..) haz�r metodu sonu...

    private class KitapBilgisi {
        public String kitapAd�;
        public URL kitapYureli;

        public KitapBilgisi (String kitap, String dosyaAd�) {
            kitapAd� = kitap;
            kitapYureli = getClass().getResource (dosyaAd�);
            if (kitapYureli == null) System.err.println ("[" + dosyaAd� + "] adl� html dosyas�n� bulamad�m!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return kitapAd�;}
    } // KitapBilgisi s�n�f� sonu...

    private void yurelDosyas�n�G�ster (URL yurel) {
        try {if (yurel != null) edit�rPanosu.setPage (yurel);
            else {// null yurel ise
                edit�rPanosu.setText ("Dosya Bulunamad�");
                if (yaz�ls�nM�) System.out.println ("Bir null URL'i g�sterme ba�ar�s�z te�ebb�s�!");
            } // else karar� sonu...
        }catch (IOException ist) {System.err.println ("Bozuk bir yurel okunma hatal� te�ebb�s�: " + yurel);}
    } // yurelDosyas�n�G�ster(..) metodu sonu...

    private void yard�m�G�ster() {
        String yol = "html/TreeDemoHelp.html";
        yard�mYureli = getClass().getResource (yol);
        if (yard�mYureli == null) System.err.println ("[" + yol + "] adl� yard�m dosyas�n� bulamad�m!");
        else if (yaz�ls�nM�) System.out.println ("Yard�m URL dosyas�: " + yard�mYureli);

        yurelDosyas�n�G�ster (yard�mYureli);
    } // yard�m�G�ster() metodu sonu...
        
    private static void yaratVeG�sterGUI() {
        if (sistemBakVeHissetKullan�ls�nM�) {
            try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
            }catch (Exception ist) {System.err.println ("HATA: BakVeHisset sistemi kullan�lam�yor!");}
        } // if karar� sonu...

        // Pencereyi yarat�p kural�m...
        JFrame �er�eve = new JFrame ("A�a� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_79()); // Kurucuyu �a��r�r...
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        yaz�ls�nM� = args.length > 0? true : false;
        sistemBakVeHissetKullan�ls�nM� = args.length > 1? true : false;
        sat�rStiliyleOynans�nM� = args.length > 2? true : false;
        if (sat�rStiliyleOynans�nM�) sat�rStili = "Angled";

        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_79 s�n�f� sonu...

/* ��kt�:
**  >java J5c_79 1 1 1  **
Atanan yeni sat�r stili = Angled
Yard�m URL dosyas�: file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html
Java Programc�lar� i�in Kitaplar
Java Kullan�c�lar� i�in Kitaplar
T�m Java E�itim Kitaplar�
Java Kitap Serileri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/index.html:
    B�y�k E�itim Endeksi
T�m Java E�itim Kitaplar�
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
    Java E�itimi: Temel Bilgiler �zerine K�sa Bir Kurs
*/