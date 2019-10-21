// J5c_79.java: TreeDemo (AðaçGösterisi) örneði.

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
/* Gerekli html dosyalarý:
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
    private JEditorPane editörPanosu;
    private JTree aðaç;
    private URL yardýmYureli;

    // Aþaðýdaki 3 booleaný da ileti satýrý argüman giriþiyle "true" kýlabilirsin...
    private static boolean yazýlsýnMý = false;
    private static boolean sistemBakVeHissetKullanýlsýnMý = false;
    private static boolean satýrStiliyleOynansýnMý = false;
    private static String satýrStili = "Horizontal"; // Angled/Açýlý ve None/Hiç da yapýlabilir...

    public J5c_79() {// Kurucu...
        super (new GridLayout (1,0));

        // Aðaç dallarýný yaratalým...
        DefaultMutableTreeNode gövde = new DefaultMutableTreeNode ("Java Kitap Serileri");
        dallarýYarat (gövde);

        // Her keresinde tek seçimlik bir aðaç yaratalým...
        aðaç = new JTree (gövde);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Aðaç dal seçimlerini dinleyiciye duyarlayalým...
        aðaç.addTreeSelectionListener (this);

        if (satýrStiliyleOynansýnMý) {
            System.out.println ("Atanan yeni satýr stili = " + satýrStili);
            aðaç.putClientProperty ("JTree.satýrStili", satýrStili);
        } // if kararý sonu...

        // Açýlýnca taþacak aðaç dallarý için kaydýrma panosu yaratalým...
        JScrollPane aðaçKaydýrmaPanosu = new JScrollPane (aðaç);

        // Pencere altýna HTML görüntüleme müdahalesiz ve kaydýrmalý editör panosu kuralým...
        editörPanosu = new JEditorPane();
        editörPanosu.setEditable (false); // Müdahalesiz...
        yardýmýGöster();
        JScrollPane htmlKaydýrmaPanosu = new JScrollPane (editörPanosu);

        // Ýki kaydýrma panosunu ara bölmesi ayýrma panosuna yaratýp ekleyelim...
        JSplitPane ayýrmaPanosu = new JSplitPane (JSplitPane.VERTICAL_SPLIT);
        ayýrmaPanosu.setTopComponent (aðaçKaydýrmaPanosu);
        ayýrmaPanosu.setBottomComponent (htmlKaydýrmaPanosu);

        Dimension enküçükEbat = new Dimension (100, 50);
        htmlKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        aðaçKaydýrmaPanosu.setMinimumSize (enküçükEbat);
        ayýrmaPanosu.setDividerLocation (100); 
        ayýrmaPanosu.setPreferredSize (new Dimension (500, 300));

        // Tam komponentli ayýrma panosunu içerik panosuna ekleyelim...
        add (ayýrmaPanosu);
    } // J5c_79() kurucusu sonu...

    private void dallarýYarat (DefaultMutableTreeNode gövde) {
        DefaultMutableTreeNode katagori = null;
        DefaultMutableTreeNode kitap = null;

        // Birinci katagori dalý...
        katagori = new DefaultMutableTreeNode ("Java Programcýlarý için Kitaplar");
        gövde.add (katagori);

        // Katagori-1 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitimi: Temel Bilgiler Üzerine Kýsa Bir Kurs", "html/tutorial.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Eðitimi Devam Ediyor: JDK'nin Devamý", "html/tutorialcont.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-3:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JFC Swing Eðitimi: GUI'leri Kurmak Ýçin Bir Rehber", "html/swingtutorial.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-4:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Etkili Java Programlama Dili Rehberi", "html/bloch.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-5:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Ýleri Java Programlama Dili", "html/arnold.html"));
        katagori.add (kitap);

        // Katagori-1 Kitap-6:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Geliþtiricileri Yýllýðý", "html/chan.html"));
        katagori.add (kitap);

        // Ýkinci katagori dalý...
        katagori = new DefaultMutableTreeNode ("Java Kullanýcýlarý için Kitaplar");
        gövde.add (katagori);

        // Katagori-2 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("JVM Java Sanal Makine Özellikleri", "html/vm.html"));
        katagori.add(kitap);

        // Katagori-2 Kitap-2:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Java Dilinin Özellikleri", "html/jls.html"));
        katagori.add(kitap);

        // Üçüncü katagori dalý...
        katagori = new DefaultMutableTreeNode ("Tüm Java Eðitim Kitaplarý");
        gövde.add (katagori);

        // Katagori-3 Kitap-1:
        kitap = new DefaultMutableTreeNode (new KitapBilgisi ("Büyük Eðitim Endeksi", "html/index.html"));
        katagori.add (kitap);
    } // dallarýYarat(..) metodu sonu...

    // TreeSelectionListener arayüzü hazýr metodu...
    public void valueChanged (TreeSelectionEvent olay) {
        DefaultMutableTreeNode dal = (DefaultMutableTreeNode)aðaç.getLastSelectedPathComponent();

        if (dal == null) return;

        Object dalBilgisi = dal.getUserObject();
        if (dal.isLeaf()) {
            KitapBilgisi kitap = (KitapBilgisi)dalBilgisi;
            yurelDosyasýnýGöster (kitap.kitapYureli);
            if (yazýlsýnMý) System.out.print (kitap.kitapYureli + ":  \n    ");
        }else yurelDosyasýnýGöster (yardýmYureli); 
        if (yazýlsýnMý) System.out.println (dalBilgisi.toString());
    } // valueChanged(..) hazýr metodu sonu...

    private class KitapBilgisi {
        public String kitapAdý;
        public URL kitapYureli;

        public KitapBilgisi (String kitap, String dosyaAdý) {
            kitapAdý = kitap;
            kitapYureli = getClass().getResource (dosyaAdý);
            if (kitapYureli == null) System.err.println ("[" + dosyaAdý + "] adlý html dosyasýný bulamadým!");
        } // KitapBilgisi(..) kurucusu sonu...

        public String toString() {return kitapAdý;}
    } // KitapBilgisi sýnýfý sonu...

    private void yurelDosyasýnýGöster (URL yurel) {
        try {if (yurel != null) editörPanosu.setPage (yurel);
            else {// null yurel ise
                editörPanosu.setText ("Dosya Bulunamadý");
                if (yazýlsýnMý) System.out.println ("Bir null URL'i gösterme baþarýsýz teþebbüsü!");
            } // else kararý sonu...
        }catch (IOException ist) {System.err.println ("Bozuk bir yurel okunma hatalý teþebbüsü: " + yurel);}
    } // yurelDosyasýnýGöster(..) metodu sonu...

    private void yardýmýGöster() {
        String yol = "html/TreeDemoHelp.html";
        yardýmYureli = getClass().getResource (yol);
        if (yardýmYureli == null) System.err.println ("[" + yol + "] adlý yardým dosyasýný bulamadým!");
        else if (yazýlsýnMý) System.out.println ("Yardým URL dosyasý: " + yardýmYureli);

        yurelDosyasýnýGöster (yardýmYureli);
    } // yardýmýGöster() metodu sonu...
        
    private static void yaratVeGösterGUI() {
        if (sistemBakVeHissetKullanýlsýnMý) {
            try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
            }catch (Exception ist) {System.err.println ("HATA: BakVeHisset sistemi kullanýlamýyor!");}
        } // if kararý sonu...

        // Pencereyi yaratýp kuralým...
        JFrame çerçeve = new JFrame ("Aðaç Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_79()); // Kurucuyu çaðýrýr...
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        yazýlsýnMý = args.length > 0? true : false;
        sistemBakVeHissetKullanýlsýnMý = args.length > 1? true : false;
        satýrStiliyleOynansýnMý = args.length > 2? true : false;
        if (satýrStiliyleOynansýnMý) satýrStili = "Angled";

        SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_79 sýnýfý sonu...

/* Çýktý:
**  >java J5c_79 1 1 1  **
Atanan yeni satýr stili = Angled
Yardým URL dosyasý: file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/TreeDemoHelp.html
Java Programcýlarý için Kitaplar
Java Kullanýcýlarý için Kitaplar
Tüm Java Eðitim Kitaplarý
Java Kitap Serileri
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/index.html:
    Büyük Eðitim Endeksi
Tüm Java Eðitim Kitaplarý
file:/C:/Users/pc/Desktop/MyFiles/4.%20Dersler/JDK-8/uygulama/html/tutorial.html:
    Java Eðitimi: Temel Bilgiler Üzerine Kýsa Bir Kurs
*/