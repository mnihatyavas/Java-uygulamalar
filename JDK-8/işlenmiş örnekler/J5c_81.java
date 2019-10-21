// J5c_81.java: TreeIconDemo2 (AðaçÝkonuGösterisi2) örneði.

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
    private JEditorPane editörPanosu;
    private JTree aðaç;
    private URL yardýmYureli;
    private static boolean yazýlsýnMý = false;

    public J5c_81() {// Kurucu...
        super (new GridLayout (1,0));

        // Kök, gövde ve yaprak düðümlerini yaratalým...
        DefaultMutableTreeNode kökDüðüm = new DefaultMutableTreeNode ("Java Eðitim Eserleri Serisi");
        düðümleriYarat (kökDüðüm);

        // Beraberindekilerle kök düðümü tek seçim modelli bir aðaca kuralým...
        aðaç = new JTree (kökDüðüm);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Ýkonlu yapraklar ipuçlu olacak (ikonsuzlar deðil)...
        ToolTipManager.sharedInstance().registerComponent (aðaç);

        // Yaprak düðümlerinin bazýlarý için resim ikonu kullanýlacak...
        ImageIcon yaprakÝkonu = resimÝkonuYarat ("resim/orta.gif");
        if (yaprakÝkonu != null) aðaç.setCellRenderer (new Sunucum (yaprakÝkonu));
        else System.err.println ("Yaprak ikonu eksik; varsayýlý ikon kullanýlacak.");

        // Aðacýn kök, dal ve yaprak týklamalarýna duyarlanalým...
        aðaç.addTreeSelectionListener (this);

        // Geniþleyip taþabilecek aðaç için bir kaydýrma panosu yaratalým...
        JScrollPane aðaçKaydýrmaPanosu = new JScrollPane (aðaç);

        // HTML dosya içeriklerini gösterecek müdahalesiz bir editör panosunu kaydýrmalý yaratalým...
        editörPanosu = new JEditorPane();
        editörPanosu.setEditable (false); // Müdahalesiz...
        yardýmHTMLyiGöster();
        JScrollPane editörKaydýrmaPanosu = new JScrollPane (editörPanosu);

        // Heriki kaydýrma panosunu da bir ayýrma panosuna (dikey ara bölmeli) kuralým...
        JSplitPane ayýrmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ayýrmaPanosu.setTopComponent (aðaçKaydýrmaPanosu);
        ayýrmaPanosu.setBottomComponent (editörKaydýrmaPanosu);

        Dimension enküçükEbat = new Dimension (100, 50);
        editörKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        aðaçKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        ayýrmaPanosu.setDividerLocation (100);
        //aðaçKaydýrmaPanosu.setPreferredSize (new Dimension (100, 100)); 
        ayýrmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Ayýrma panomuzu içerik panosuna ekleyelim...
        add (ayýrmaPanosu);
    } // J5c_81() kurucusu sonu...

    private void düðümleriYarat (DefaultMutableTreeNode kökDüðüm) {
        DefaultMutableTreeNode dalDüðümü = null;
        DefaultMutableTreeNode yaprakDüðümü = null;

        // Dal 1:
        dalDüðümü = new DefaultMutableTreeNode ("Java Programcýlarý Ýçin Kitaplar");
        kökDüðüm.add(dalDüðümü);

        // Dal 1, Yaprak 1:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitiminde Temel Hususlar", "html/tutorial.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 1, Yaprak 2:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitiminde Ýleri Hususlar", "html/tutorialcont.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 1, Yaprak 2:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Swing Komponentlerle GUI'ler Kurma Rehberi", "html/swingtutorial.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 1, Yaprak 3:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Etkin Java Programcýlýðý Rehberi", "html/bloch.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 1, Yaprak 4:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java Programcýlýðýnda Ýleri Teknikler", "html/arnold.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 1, Yaprak 5:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geliþtiricileri Yýllýk Almanaðý", "html/chan.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 2:
        dalDüðümü = new DefaultMutableTreeNode ("Java Kullanýcýlarý Ýçin Kitaplar");
        kökDüðüm.add (dalDüðümü);

        // Dal 2, Yaprak 1:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("JVM Java Sanal Makine Özellikleri", "html/vm.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 2, Yaprak 2:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin Referanslarý", "html/jls.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 3:
        dalDüðümü = new DefaultMutableTreeNode ("A'dan-Z'ye Java");
        kökDüðüm.add (dalDüðümü);

        // Dal 3, Yaprak 1:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Java 1->9 Tüm Sürümleri Özellikleri", "html/sürüm.html"));
        dalDüðümü.add (yaprakDüðümü);

        // Dal 3, Yaprak 2:
        yaprakDüðümü = new DefaultMutableTreeNode (new KitapBilgisi ("Tüm Java Eðitim Konularý Endeksi", "html/reallybigindex.html"));
        dalDüðümü.add (yaprakDüðümü);
    } // düðümleriYarat(..) metodu sonu...

    private class KitapBilgisi {
        public String yaprakDüðümüAdý;
        public URL yaprakDüðümüYureli;

        public KitapBilgisi (String yaprakDüðümü, String yol) {
            yaprakDüðümüAdý = yaprakDüðümü;
            yaprakDüðümüYureli = J5c_81.class.getResource (yol);
            if (yaprakDüðümüYureli == null) System.err.println ("[" + yol + "] adlý HTML dosyasýný bulamadým!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return yaprakDüðümüAdý;}
    } // KitapBilgisi sýnýfý sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        URL resimYureli = J5c_81.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private class Sunucum extends DefaultTreeCellRenderer {
        Icon yaprakÝkonu;

        public Sunucum (Icon ikon) {yaprakÝkonu = ikon;} // Kurucu...

        public Component getTreeCellRendererComponent (
                JTree aðaç,
                Object deðer,
                boolean seçiliMi,
                boolean genleþmiþMi,
                boolean yaprakMý,
                int satýr,
                boolean odaklýMý) {

            super.getTreeCellRendererComponent (aðaç, deðer, seçiliMi, genleþmiþMi, yaprakMý, satýr, odaklýMý);
            if (yaprakMý && javaKitabýMý (deðer)) {
                setIcon (yaprakÝkonu);
                setToolTipText ("Bu ikonlu yaprak düðümü Java anahtar-kelimesini içermektedir");
            }else setToolTipText (null); // Ýpuçsuz...

            return this;
        } // getTreeCellRendererComponent(..) hazýr metodu sonu...

        protected boolean javaKitabýMý (Object deðer) {
            DefaultMutableTreeNode düðüm = (DefaultMutableTreeNode)deðer;
            KitapBilgisi düðümBilgisi = (KitapBilgisi)(düðüm.getUserObject());
            String baþlýk = düðümBilgisi.yaprakDüðümüAdý;
            if (baþlýk.indexOf ("Java") >= 0) return true;

            return false;
        } // javaKitabýMý(..) metodu sonu...
    } // Sunucum sýnýfý sonu...

    // TreeSelectionListener dinleyici arayüzünün hazýr metodudur...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode düðüm = (DefaultMutableTreeNode)aðaç.getLastSelectedPathComponent();

        if (düðüm == null) return;

        Object düðümBilgisi = düðüm.getUserObject();
        if (düðüm.isLeaf()) {
            KitapBilgisi yaprakDüðümü = (KitapBilgisi)düðümBilgisi;
            yurelDosyaÝçeriðiniGöster (yaprakDüðümü.yaprakDüðümüYureli);
            if (yazýlsýnMý) System.out.print (yaprakDüðümü.yaprakDüðümüYureli + ":\n--->");
        }else yurelDosyaÝçeriðiniGöster (yardýmYureli); 
        if (yazýlsýnMý) System.out.println (düðümBilgisi.toString());
    } // valueChanged(..) hazýr metodu sonu...

    private void yurelDosyaÝçeriðiniGöster (URL yurelDosyasý) {
        try {if (yurelDosyasý != null) editörPanosu.setPage (yurelDosyasý);
            else {// Eðer yurel null ise...
                editörPanosu.setText ("[" + yurelDosyasý + "] HTML URL dosyasý bulunamadý!");
                if (yazýlsýnMý) System.out.println ("Bir null HTML URL dosyasýný gösterme baþarýsýz teþebbüsü.");
            } // else kararý sonu...
        }catch (IOException ist) {System.err.println ("HATA: Baþarýsýz HTML URL dosya okuma teþebbüsü: [" + yurelDosyasý + "]");}
    } // yurelDosyaÝçeriðiniGöster(..) metodu sonu...

    private void yardýmHTMLyiGöster() {
        String yol = "html/TreeDemoHelp.html";
        yardýmYureli = J5c_81.class.getResource (yol);
        if (yardýmYureli == null) System.err.println ("[" + yol + "] adlý HTML yardým dosyasýný açamadým!");
        else if (yazýlsýnMý) System.out.println ("Yardým HTML URL dosyasý: [" + yardýmYureli + "]");

        yurelDosyaÝçeriðiniGöster (yardýmYureli);
    } // yardýmHTMLyiGöster() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Aðaç Ýkonu Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5c_81 yeniÝçerikPanosu = new J5c_81(); // Kurucuyu çaðýrýr...
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
} // J5c_81 sýnýfý sonu...

/* Çýktý:
**  >java J5c_81 1  **
Yardým HTML URL dosyasý: [file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html]
Java Eðitim Eserleri Serisi
Java Programcýlarý Ýçin Kitaplar
Java Kullanýcýlarý Ýçin Kitaplar
A'dan-Z'ye Java
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
--->Java Eðitiminde Temel Hususlar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorialcont.html:
--->Java Eðitiminde Ýleri Hususlar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/swingtutorial.html:
--->Swing Komponentlerle GUI'ler Kurma Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/bloch.html:
--->Etkin Java Programcýlýðý Rehberi
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/arnold.html:
--->Java Programcýlýðýnda Ýleri Teknikler
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/chan.html:
--->Java Geliþtiricileri Yýllýk Almanaðý
Java Programcýlarý Ýçin Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/vm.html:
--->JVM Java Sanal Makine Özellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/jls.html:
--->Java Dilinin Referanslarý
Java Kullanýcýlarý Ýçin Kitaplar
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/s%c3%bcr%c3%bcm.html:
--->Java 1->9 Tüm Sürümleri Özellikleri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/reallybigindex.html:
--->Tüm Java Eðitim Konularý Endeksi
A'dan-Z'ye Java
*/