// J5c_80.java: TreeIconDemo (A�a��konuG�sterisi) �rne�i.

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTree;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import java.net.URL;

import java.io.IOException;

/* Gerekli dosyalar:
 * html/TreeDemoHelp.html
 *    html/arnold.html
 *    html/bloch.html
 *    html/chan.html
 *    html/jls.html
 *    html/swingtutorial.html
 *    html/tutorial.html
 *    html/tutorialcont.html
 *    html/vm.html
 *
 * resim/sa�.gif
 */
public class J5c_80 extends JPanel implements TreeSelectionListener {
    private JEditorPane edit�rPanosu;
    private JTree a�a�;
    private URL yard�mYureli;
    private static boolean yaz�ls�nM� = false;

    public J5c_80() {// Kurucu...
        super (new GridLayout (1,0));

        // A�a� d���mlerini yaratal�m...
        DefaultMutableTreeNode k�kD���m = new DefaultMutableTreeNode ("Java E�itim Serisi");
        d���mleriYarat (k�kD���m); // G�vde/kategori ve Yaprak/kitap d���mlerini yarat�r...

        // Tek se�imlik bir a�a� modeli yaratal�m...
        a�a� = new JTree (k�kD���m);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Yaprak d���mleri i�in bir resim ikonu kural�m...
        ImageIcon yaprak�konu = resim�konuYarat ("resim/sa�.gif");
        if (yaprak�konu != null) {
            DefaultTreeCellRenderer takdimci = new DefaultTreeCellRenderer();
            takdimci.setLeafIcon (yaprak�konu);
            a�a�.setCellRenderer (takdimci);
        } else System.err.println ("Yaprak ikonu nemevcut; varsay�l� kullan�lacak.");

        // A�a� t�klamalar�n� dinleyiciye duyarlayal�m...
        a�a�.addTreeSelectionListener (this);

        // A�ac� kayd�rma panosuna kural�m...
        JScrollPane a�a�Kayd�rmaPanosu = new JScrollPane (a�a�);

        // HTML dosya i�eriklerini g�r�nt�leyecek bir edit�r panosu yarat�p kayd�rmaya kural�m...
        edit�rPanosu = new JEditorPane();
        edit�rPanosu.setEditable (false); // M�dahalesiz...
        yard�mHTMLyiG�ster();
        JScrollPane edit�rKayd�rmaPanosu = new JScrollPane (edit�rPanosu);

        // Her iki kayd�rma panosunu dikey ara b�l�ml� bir ay�rma panosuna kural�m...
        JSplitPane ay�rmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ay�rmaPanosu.setTopComponent (a�a�Kayd�rmaPanosu); // �st b�l�m...
        ay�rmaPanosu.setBottomComponent (edit�rKayd�rmaPanosu); // Alt b�l�m...

        Dimension enk���kEbat = new Dimension (100, 50);
        edit�rKayd�rmaPanosu.setMinimumSize (enk���kEbat);
        a�a�Kayd�rmaPanosu.setMinimumSize (enk���kEbat);
        ay�rmaPanosu.setDividerLocation (100);
        ay�rmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Tamamlanm�� komponentli ay�rma panomuzu i�erik panosuna ekleyelim...
        add (ay�rmaPanosu);
    } // J5c_80() kurucusu sonu...

    private void d���mleriYarat (DefaultMutableTreeNode k�kD���m) {
        DefaultMutableTreeNode kategori = null; // G�vde d���mleri...
        DefaultMutableTreeNode kitap = null; // Yaprak d���mleri...

        // Kategori 1:
        kategori = new DefaultMutableTreeNode ("Java Programc�lar� i�in Kitaplar");
        k�kD���m.add (kategori);

        // Kategori 1, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itimi: Temeller �zerine K�sa Bir Kurs", "html/tutorial.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 2: (JDK/JavaDevelopmentKit: JavaGeli�imPaketi)
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itimi Devam Ediyor: JDK'n�n Kalan�", "html/tutorialcont.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 3: (JFC/JavaFrameComponents: Java�er�evePar�alar�)
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JFC Swing E�itimi: GUI'leri Kurmak ��in Bir Rehberi", "html/swingtutorial.html"));
        kategori.add (kitap); // GUI/GraphicsUnitInterface: GrafikBirimAray�z�...

        // Kategori 1, Kitap 4:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Etkili Java Programlama Dili Rehberi", "html/bloch.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 5:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("�leri Java Programlama Dili", "html/arnold.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 6:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geli�tiricileri Y�ll���", "html/chan.html"));
        kategori.add (kitap);

        // Kategori 2:
        kategori = new DefaultMutableTreeNode ("Java Kullan�c�lar� ��in Kitaplar");
        k�kD���m.add (kategori);

        // Kategori 2, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JVM/JavaVirtualMachine �zellikleri", "html/vm.html"));
        kategori.add (kitap); // JVM/JavaSanalMakine...

        // Kategori 2, Kitap 2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin �zellikleri", "html/jls.html"));
        kategori.add (kitap);

        // Kategori 2, Kitap 3:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java 1->9 S�r�mlerinin �zellikleri", "html/s�r�m.html"));
        kategori.add (kitap);

        // Kategori 3:
        kategori = new DefaultMutableTreeNode ("T�m Java E�itim Endeksi");
        k�kD���m.add (kategori);

        // Kategori 3, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Ger�ekten B�y�k Endeks", "html/reallybigindex.html"));
        kategori.add (kitap);
    } // d���mleriYarat(..) metodu sonu...

    private class KitapBilgisi {
        public String kitapAd�;
        public URL kitapYureli;

        public KitapBilgisi (String kitap, String yol) {// Kurucu...
            kitapAd� = kitap;
            kitapYureli = J5c_80.class.getResource (yol);
            if (kitapYureli == null) System.err.println ("[" + yol + "] adl� html dosyas�n� bulamad�m!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return kitapAd�;}
    } // KitapBilgisi s�n�f� sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        URL resimYureli = J5c_80.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    // TreeSelectionListener aray�z� haz�r metodudur...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode d���m = (DefaultMutableTreeNode)a�a�.getLastSelectedPathComponent();

        if (d���m == null) return;

        Object d���mBilgisi = d���m.getUserObject();
        if (d���m.isLeaf()) {
            KitapBilgisi kitap = (KitapBilgisi)d���mBilgisi;
            yurelHTMLyiG�ster (kitap.kitapYureli);
            if (yaz�ls�nM�) System.out.print (kitap.kitapYureli + ":\n--->");
        } else yurelHTMLyiG�ster (yard�mYureli); 
        if (yaz�ls�nM�) System.out.println (d���mBilgisi.toString());
    } // valueChanged(..) haz�r metodu sonu...

    private void yurelHTMLyiG�ster (URL yurel) {
        try {if (yurel != null) edit�rPanosu.setPage (yurel);
            else {// Yurel null ise...
                edit�rPanosu.setText ("HTML Dosyas� Bulunamad�!");
                if (yaz�ls�nM�) System.out.println ("Bir null URL g�sterme hatal� te�ebb�s�!");
            } // else karar� sonu...
        }catch (IOException ist) {System.err.println ("Bozuk bir: [" + yurel + "] dosyas�n� okuma te�ebb�s�!");}
    } // yurelHTMLyiG�ster(..) metodu sonu...

    private void yard�mHTMLyiG�ster() {
        String yol = "html/TreeDemoHelp.html";
        yard�mYureli = J5c_80.class.getResource (yol);
        if (yard�mYureli == null) System.err.println ("[" + yol + "] adl� yard�m HTML dosyas�n� bulamad�m!");
        else if (yaz�ls�nM�) System.out.println ("Yard�m yurel dosyas�: [" + yard�mYureli + "]");

        yurelHTMLyiG�ster (yard�mYureli);
    } // yard�mHTMLyiG�ster() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A�a� �konu G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_80 yeni��erikPanosu = new J5c_80(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        yaz�ls�nM� = args.length > 0? true : false;

        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_80 s�n�f� sonu...

/* ��kt�:
**  >java J5c_80 1  **
Yard�m yurel dosyas�: [file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html]
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/reallybigindex.html:
--->Ger�ekten B�y�k Endeks
T�m Java E�itim Endeksi
Java Kullan�c�lar� ��in Kitaplar
Java Programc�lar� i�in Kitaplar
Java E�itim Serisi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java E�itimi: Temeller �zerine K�sa Bir Kurs
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorialcont.html:
--->Java E�itimi Devam Ediyor: JDK'n�n Kalan�
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java E�itimi: Temeller �zerine K�sa Bir Kurs
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/swingtutorial.html:
--->JFC Swing E�itimi: GUI'leri Kurmak ��in Bir Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/bloch.html:
--->Etkili Java Programlama Dili Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/arnold.html:
--->�leri Java Programlama Dili
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/chan.html:
--->Java Geli�tiricileri Y�ll���
Java Programc�lar� i�in Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/vm.html:
--->JVM/JavaVirtualMachine �zellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/jls.html:
--->Java Dilinin �zellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/s%c3%bcr%c3%bcm.html:
--->Java 1->9 S�r�mlerinin �zellikleri
Java Kullan�c�lar� ��in Kitaplar
*/