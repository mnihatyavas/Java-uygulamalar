// J5c_80.java: TreeIconDemo (AðaçÝkonuGösterisi) örneði.

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
 * resim/sað.gif
 */
public class J5c_80 extends JPanel implements TreeSelectionListener {
    private JEditorPane editörPanosu;
    private JTree aðaç;
    private URL yardýmYureli;
    private static boolean yazýlsýnMý = false;

    public J5c_80() {// Kurucu...
        super (new GridLayout (1,0));

        // Aðaç düðümlerini yaratalým...
        DefaultMutableTreeNode kökDüðüm = new DefaultMutableTreeNode ("Java Eðitim Serisi");
        düðümleriYarat (kökDüðüm); // Gövde/kategori ve Yaprak/kitap düðümlerini yaratýr...

        // Tek seçimlik bir aðaç modeli yaratalým...
        aðaç = new JTree (kökDüðüm);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Yaprak düðümleri için bir resim ikonu kuralým...
        ImageIcon yaprakÝkonu = resimÝkonuYarat ("resim/sað.gif");
        if (yaprakÝkonu != null) {
            DefaultTreeCellRenderer takdimci = new DefaultTreeCellRenderer();
            takdimci.setLeafIcon (yaprakÝkonu);
            aðaç.setCellRenderer (takdimci);
        } else System.err.println ("Yaprak ikonu nemevcut; varsayýlý kullanýlacak.");

        // Aðaç týklamalarýný dinleyiciye duyarlayalým...
        aðaç.addTreeSelectionListener (this);

        // Aðacý kaydýrma panosuna kuralým...
        JScrollPane aðaçKaydýrmaPanosu = new JScrollPane (aðaç);

        // HTML dosya içeriklerini görüntüleyecek bir editör panosu yaratýp kaydýrmaya kuralým...
        editörPanosu = new JEditorPane();
        editörPanosu.setEditable (false); // Müdahalesiz...
        yardýmHTMLyiGöster();
        JScrollPane editörKaydýrmaPanosu = new JScrollPane (editörPanosu);

        // Her iki kaydýrma panosunu dikey ara bölümlü bir ayýrma panosuna kuralým...
        JSplitPane ayýrmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ayýrmaPanosu.setTopComponent (aðaçKaydýrmaPanosu); // Üst bölüm...
        ayýrmaPanosu.setBottomComponent (editörKaydýrmaPanosu); // Alt bölüm...

        Dimension enküçükEbat = new Dimension (100, 50);
        editörKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        aðaçKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        ayýrmaPanosu.setDividerLocation (100);
        ayýrmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Tamamlanmýþ komponentli ayýrma panomuzu içerik panosuna ekleyelim...
        add (ayýrmaPanosu);
    } // J5c_80() kurucusu sonu...

    private void düðümleriYarat (DefaultMutableTreeNode kökDüðüm) {
        DefaultMutableTreeNode kategori = null; // Gövde düðümleri...
        DefaultMutableTreeNode kitap = null; // Yaprak düðümleri...

        // Kategori 1:
        kategori = new DefaultMutableTreeNode ("Java Programcýlarý için Kitaplar");
        kökDüðüm.add (kategori);

        // Kategori 1, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitimi: Temeller Üzerine Kýsa Bir Kurs", "html/tutorial.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 2: (JDK/JavaDevelopmentKit: JavaGeliþimPaketi)
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitimi Devam Ediyor: JDK'nýn Kalaný", "html/tutorialcont.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 3: (JFC/JavaFrameComponents: JavaÇerçeveParçalarý)
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JFC Swing Eðitimi: GUI'leri Kurmak Ýçin Bir Rehberi", "html/swingtutorial.html"));
        kategori.add (kitap); // GUI/GraphicsUnitInterface: GrafikBirimArayüzü...

        // Kategori 1, Kitap 4:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Etkili Java Programlama Dili Rehberi", "html/bloch.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 5:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Ýleri Java Programlama Dili", "html/arnold.html"));
        kategori.add (kitap);

        // Kategori 1, Kitap 6:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geliþtiricileri Yýllýðý", "html/chan.html"));
        kategori.add (kitap);

        // Kategori 2:
        kategori = new DefaultMutableTreeNode ("Java Kullanýcýlarý Ýçin Kitaplar");
        kökDüðüm.add (kategori);

        // Kategori 2, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JVM/JavaVirtualMachine Özellikleri", "html/vm.html"));
        kategori.add (kitap); // JVM/JavaSanalMakine...

        // Kategori 2, Kitap 2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin Özellikleri", "html/jls.html"));
        kategori.add (kitap);

        // Kategori 2, Kitap 3:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java 1->9 Sürümlerinin Özellikleri", "html/sürüm.html"));
        kategori.add (kitap);

        // Kategori 3:
        kategori = new DefaultMutableTreeNode ("Tüm Java Eðitim Endeksi");
        kökDüðüm.add (kategori);

        // Kategori 3, Kitap 1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Gerçekten Büyük Endeks", "html/reallybigindex.html"));
        kategori.add (kitap);
    } // düðümleriYarat(..) metodu sonu...

    private class KitapBilgisi {
        public String kitapAdý;
        public URL kitapYureli;

        public KitapBilgisi (String kitap, String yol) {// Kurucu...
            kitapAdý = kitap;
            kitapYureli = J5c_80.class.getResource (yol);
            if (kitapYureli == null) System.err.println ("[" + yol + "] adlý html dosyasýný bulamadým!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return kitapAdý;}
    } // KitapBilgisi sýnýfý sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        URL resimYureli = J5c_80.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    // TreeSelectionListener arayüzü hazýr metodudur...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode düðüm = (DefaultMutableTreeNode)aðaç.getLastSelectedPathComponent();

        if (düðüm == null) return;

        Object düðümBilgisi = düðüm.getUserObject();
        if (düðüm.isLeaf()) {
            KitapBilgisi kitap = (KitapBilgisi)düðümBilgisi;
            yurelHTMLyiGöster (kitap.kitapYureli);
            if (yazýlsýnMý) System.out.print (kitap.kitapYureli + ":\n--->");
        } else yurelHTMLyiGöster (yardýmYureli); 
        if (yazýlsýnMý) System.out.println (düðümBilgisi.toString());
    } // valueChanged(..) hazýr metodu sonu...

    private void yurelHTMLyiGöster (URL yurel) {
        try {if (yurel != null) editörPanosu.setPage (yurel);
            else {// Yurel null ise...
                editörPanosu.setText ("HTML Dosyasý Bulunamadý!");
                if (yazýlsýnMý) System.out.println ("Bir null URL gösterme hatalý teþebbüsü!");
            } // else kararý sonu...
        }catch (IOException ist) {System.err.println ("Bozuk bir: [" + yurel + "] dosyasýný okuma teþebbüsü!");}
    } // yurelHTMLyiGöster(..) metodu sonu...

    private void yardýmHTMLyiGöster() {
        String yol = "html/TreeDemoHelp.html";
        yardýmYureli = J5c_80.class.getResource (yol);
        if (yardýmYureli == null) System.err.println ("[" + yol + "] adlý yardým HTML dosyasýný bulamadým!");
        else if (yazýlsýnMý) System.out.println ("Yardým yurel dosyasý: [" + yardýmYureli + "]");

        yurelHTMLyiGöster (yardýmYureli);
    } // yardýmHTMLyiGöster() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Aðaç Ýkonu Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_80 yeniÝçerikPanosu = new J5c_80(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        yazýlsýnMý = args.length > 0? true : false;

        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_80 sýnýfý sonu...

/* Çýktý:
**  >java J5c_80 1  **
Yardým yurel dosyasý: [file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html]
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/reallybigindex.html:
--->Gerçekten Büyük Endeks
Tüm Java Eðitim Endeksi
Java Kullanýcýlarý Ýçin Kitaplar
Java Programcýlarý için Kitaplar
Java Eðitim Serisi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java Eðitimi: Temeller Üzerine Kýsa Bir Kurs
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorialcont.html:
--->Java Eðitimi Devam Ediyor: JDK'nýn Kalaný
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java Eðitimi: Temeller Üzerine Kýsa Bir Kurs
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/swingtutorial.html:
--->JFC Swing Eðitimi: GUI'leri Kurmak Ýçin Bir Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/bloch.html:
--->Etkili Java Programlama Dili Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/arnold.html:
--->Ýleri Java Programlama Dili
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/chan.html:
--->Java Geliþtiricileri Yýllýðý
Java Programcýlarý için Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/vm.html:
--->JVM/JavaVirtualMachine Özellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/jls.html:
--->Java Dilinin Özellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/s%c3%bcr%c3%bcm.html:
--->Java 1->9 Sürümlerinin Özellikleri
Java Kullanýcýlarý Ýçin Kitaplar
*/