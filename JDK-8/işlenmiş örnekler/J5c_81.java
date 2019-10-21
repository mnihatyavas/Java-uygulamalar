// J5c_81.java: TreeIconDemo2 (A�a��konuG�sterisi2) �rne�i.

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Component;

import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ToolTipManager;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JTree;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.tree.DefaultTreeCellRenderer;

import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;

import java.net.URL;

import java.io.IOException;

/* Gereken dosyalar:
 * html/TreeDemoHelp.html
 * html/arnold.html
 * html/bloch.html
 * html/chan.html
 * html/jls.html
 * html/swingtutorial.html
 * html/tutorial.html
 * html/tutorialcont.html
 * html/vm.html
 * html/reallybigindex.html
 *
 * resim/orta.gif
 */
public class J5c_81 extends JPanel implements TreeSelectionListener {
    private JEditorPane edit�rPanosu;
    private JTree a�a�;
    private URL yard�mYureli;
    private static boolean yaz�ls�nM� = false;

    public J5c_81() {// Kurucu...
        super (new GridLayout (1,0));

        // K�k, g�vde ve yaprak d���mlerini yaratal�m...
        DefaultMutableTreeNode k�kD���m = new DefaultMutableTreeNode ("Java E�itim Eserleri Serisi");
        d���mleriYarat (k�kD���m);

        // Beraberindekilerle k�k d���m� tek se�im modelli bir a�aca kural�m...
        a�a� = new JTree (k�kD���m);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // �konlu yapraklar ipu�lu olacak (ikonsuzlar de�il)...
        ToolTipManager.sharedInstance().registerComponent (a�a�);

        // Yaprak d���mlerinin baz�lar� i�in resim ikonu kullan�lacak...
        ImageIcon yaprak�konu = resim�konuYarat ("resim/orta.gif");
        if (yaprak�konu != null) a�a�.setCellRenderer (new Sunucum (yaprak�konu));
        else System.err.println ("Yaprak ikonu eksik; varsay�l� ikon kullan�lacak.");

        // A�ac�n k�k, dal ve yaprak t�klamalar�na duyarlanal�m...
        a�a�.addTreeSelectionListener (this);

        // Geni�leyip ta�abilecek a�a� i�in bir kayd�rma panosu yaratal�m...
        JScrollPane a�a�Kayd�rmaPanosu = new JScrollPane (a�a�);

        // HTML dosya i�eriklerini g�sterecek m�dahalesiz bir edit�r panosunu kayd�rmal� yaratal�m...
        edit�rPanosu = new JEditorPane();
        edit�rPanosu.setEditable (false); // M�dahalesiz...
        yard�mHTMLyiG�ster();
        JScrollPane edit�rKayd�rmaPanosu = new JScrollPane (edit�rPanosu);

        // Heriki kayd�rma panosunu da bir ay�rma panosuna (dikey ara b�lmeli) kural�m...
        JSplitPane ay�rmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ay�rmaPanosu.setTopComponent (a�a�Kayd�rmaPanosu);
        ay�rmaPanosu.setBottomComponent (edit�rKayd�rmaPanosu);

        Dimension enk���kEbat = new Dimension (100, 50);
        edit�rKayd�rmaPanosu.setMinimumSize (enk���kEbat);
        a�a�Kayd�rmaPanosu.setMinimumSize (enk���kEbat);
        ay�rmaPanosu.setDividerLocation (100);
        //a�a�Kayd�rmaPanosu.setPreferredSize (new Dimension (100, 100)); 
        ay�rmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Ay�rma panomuzu i�erik panosuna ekleyelim...
        add (ay�rmaPanosu);
    } // J5c_81() kurucusu sonu...

    private void d���mleriYarat (DefaultMutableTreeNode k�kD���m) {
        DefaultMutableTreeNode dalD���m� = null;
        DefaultMutableTreeNode yaprakD���m� = null;

        // Dal 1:
        dalD���m� = new DefaultMutableTreeNode ("Java Programc�lar� ��in Kitaplar");
        k�kD���m.add(dalD���m�);

        // Dal 1, Yaprak 1:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itiminde Temel Hususlar", "html/tutorial.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 1, Yaprak 2:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java E�itiminde �leri Hususlar", "html/tutorialcont.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 1, Yaprak 2:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Swing Komponentlerle GUI'ler Kurma Rehberi", "html/swingtutorial.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 1, Yaprak 3:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Etkin Java Programc�l��� Rehberi", "html/bloch.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 1, Yaprak 4:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java Programc�l���nda �leri Teknikler", "html/arnold.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 1, Yaprak 5:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geli�tiricileri Y�ll�k Almana��", "html/chan.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 2:
        dalD���m� = new DefaultMutableTreeNode ("Java Kullan�c�lar� ��in Kitaplar");
        k�kD���m.add (dalD���m�);

        // Dal 2, Yaprak 1:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("JVM Java Sanal Makine �zellikleri", "html/vm.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 2, Yaprak 2:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin Referanslar�", "html/jls.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 3:
        dalD���m� = new DefaultMutableTreeNode ("A'dan-Z'ye Java");
        k�kD���m.add (dalD���m�);

        // Dal 3, Yaprak 1:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("Java 1->9 T�m S�r�mleri �zellikleri", "html/s�r�m.html"));
        dalD���m�.add (yaprakD���m�);

        // Dal 3, Yaprak 2:
        yaprakD���m� = new DefaultMutableTreeNode (new KitapBilgisi ("T�m Java E�itim Konular� Endeksi", "html/reallybigindex.html"));
        dalD���m�.add (yaprakD���m�);
    } // d���mleriYarat(..) metodu sonu...

    private class KitapBilgisi {
        public String yaprakD���m�Ad�;
        public URL yaprakD���m�Yureli;

        public KitapBilgisi (String yaprakD���m�, String yol) {
            yaprakD���m�Ad� = yaprakD���m�;
            yaprakD���m�Yureli = J5c_81.class.getResource (yol);
            if (yaprakD���m�Yureli == null) System.err.println ("[" + yol + "] adl� HTML dosyas�n� bulamad�m!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return yaprakD���m�Ad�;}
    } // KitapBilgisi s�n�f� sonu...

    protected static ImageIcon resim�konuYarat (String yol) {
        URL resimYureli = J5c_81.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adl� resim dosyas�n� bulamad�m!"); return null;}
    } // resim�konuYarat(..) metodu sonu...

    private class Sunucum extends DefaultTreeCellRenderer {
        Icon yaprak�konu;

        public Sunucum (Icon ikon) {yaprak�konu = ikon;} // Kurucu...

        public Component getTreeCellRendererComponent (
                JTree a�a�,
                Object de�er,
                boolean se�iliMi,
                boolean genle�mi�Mi,
                boolean yaprakM�,
                int sat�r,
                boolean odakl�M�) {

            super.getTreeCellRendererComponent (a�a�, de�er, se�iliMi, genle�mi�Mi, yaprakM�, sat�r, odakl�M�);
            if (yaprakM� && javaKitab�M� (de�er)) {
                setIcon (yaprak�konu);
                setToolTipText ("Bu ikonlu yaprak d���m� Java anahtar-kelimesini i�ermektedir");
            }else setToolTipText (null); // �pu�suz...

            return this;
        } // getTreeCellRendererComponent(..) haz�r metodu sonu...

        protected boolean javaKitab�M� (Object de�er) {
            DefaultMutableTreeNode d���m = (DefaultMutableTreeNode)de�er;
            KitapBilgisi d���mBilgisi = (KitapBilgisi)(d���m.getUserObject());
            String ba�l�k = d���mBilgisi.yaprakD���m�Ad�;
            if (ba�l�k.indexOf ("Java") >= 0) return true;

            return false;
        } // javaKitab�M�(..) metodu sonu...
    } // Sunucum s�n�f� sonu...

    // TreeSelectionListener dinleyici aray�z�n�n haz�r metodudur...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode d���m = (DefaultMutableTreeNode)a�a�.getLastSelectedPathComponent();

        if (d���m == null) return;

        Object d���mBilgisi = d���m.getUserObject();
        if (d���m.isLeaf()) {
            KitapBilgisi yaprakD���m� = (KitapBilgisi)d���mBilgisi;
            yurelDosya��eri�iniG�ster (yaprakD���m�.yaprakD���m�Yureli);
            if (yaz�ls�nM�) System.out.print (yaprakD���m�.yaprakD���m�Yureli + ":\n--->");
        }else yurelDosya��eri�iniG�ster (yard�mYureli); 
        if (yaz�ls�nM�) System.out.println (d���mBilgisi.toString());
    } // valueChanged(..) haz�r metodu sonu...

    private void yurelDosya��eri�iniG�ster (URL yurelDosyas�) {
        try {if (yurelDosyas� != null) edit�rPanosu.setPage (yurelDosyas�);
            else {// E�er yurel null ise...
                edit�rPanosu.setText ("[" + yurelDosyas� + "] HTML URL dosyas� bulunamad�!");
                if (yaz�ls�nM�) System.out.println ("Bir null HTML URL dosyas�n� g�sterme ba�ar�s�z te�ebb�s�.");
            } // else karar� sonu...
        }catch (IOException ist) {System.err.println ("HATA: Ba�ar�s�z HTML URL dosya okuma te�ebb�s�: [" + yurelDosyas� + "]");}
    } // yurelDosya��eri�iniG�ster(..) metodu sonu...

    private void yard�mHTMLyiG�ster() {
        String yol = "html/TreeDemoHelp.html";
        yard�mYureli = J5c_81.class.getResource (yol);
        if (yard�mYureli == null) System.err.println ("[" + yol + "] adl� HTML yard�m dosyas�n� a�amad�m!");
        else if (yaz�ls�nM�) System.out.println ("Yard�m HTML URL dosyas�: [" + yard�mYureli + "]");

        yurelDosya��eri�iniG�ster (yard�mYureli);
    } // yard�mHTMLyiG�ster() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A�a� �konu G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_81 yeni��erikPanosu = new J5c_81(); // Kurucuyu �a��r�r...
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
} // J5c_81 s�n�f� sonu...

/* ��kt�:
**  >java J5c_81 1  **
Yard�m HTML URL dosyas�: [file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html]
Java E�itim Eserleri Serisi
Java Programc�lar� ��in Kitaplar
Java Kullan�c�lar� ��in Kitaplar
A'dan-Z'ye Java
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java E�itiminde Temel Hususlar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorialcont.html:
--->Java E�itiminde �leri Hususlar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/swingtutorial.html:
--->Swing Komponentlerle GUI'ler Kurma Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/bloch.html:
--->Etkin Java Programc�l��� Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/arnold.html:
--->Java Programc�l���nda �leri Teknikler
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/chan.html:
--->Java Geli�tiricileri Y�ll�k Almana��
Java Programc�lar� ��in Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/vm.html:
--->JVM Java Sanal Makine �zellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/jls.html:
--->Java Dilinin Referanslar�
Java Kullan�c�lar� ��in Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/s%c3%bcr%c3%bcm.html:
--->Java 1->9 T�m S�r�mleri �zellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/reallybigindex.html:
--->T�m Java E�itim Konular� Endeksi
A'dan-Z'ye Java
*/