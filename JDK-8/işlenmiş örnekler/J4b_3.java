// J4b_3.java: CustomProgress (GelenekselGeliþim) örneði.

//import javax.jnlp.DownloadServiceListener;
import java.awt.Color;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.ImageIcon;
import java.net.URL;

public class J4b_3 {//implements DownloadServiceListener
    JFrame çerçeve = null;
    JProgressBar geliþimÇubuðu = null;
    boolean uiYaratýldýMý = false;

    public J4b_3() {} // Kurucu...
    public void indirimBaþarýsýz (java.net.URL yurel, java.lang.String sürüm) {}
    public void geliþim (URL yurel, String sürüm, long þimdiyeDekOkunan, long üstPanellam, int hasýlaYüzdesi) {uiGeliþiminiGüncelle (hasýlaYüzdesi);}
    public void arþiviKatkýla (java.net.URL yurel, java.lang.String sürüm, int katkýYüzdesi, int hasýlaYüzdesi) {uiGeliþiminiGüncelle (hasýlaYüzdesi);}
    public  void geçerlileliyor (java.net.URL yurel, java.lang.String sürüm, long içerik, long üstPanellam,int hasýlaYüzdesi) {uiGeliþiminiGüncelle (hasýlaYüzdesi);}

    private void uiGeliþiminiGüncelle (int hasýlaYüzdesi) {
        if (hasýlaYüzdesi > 0 && hasýlaYüzdesi < 99) {
            if (!uiYaratýldýMý) {
                uiYaratýldýMý = true;
                yaratGUI();
            } // if!u.. kararý sonu...
            geliþimÇubuðu.setValue (hasýlaYüzdesi);
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {çerçeve.setVisible (true);}
            }); // Sw.. ifadesi sonu...
        }else SwingUtilities.invokeLater (new Runnable() {public void run() {çerçeve.setVisible (false); çerçeve.dispose();}});
    } // uiGeliþiminiGüncelle(..) metodu sonu...

    // Yol/resim bulunamazsa null/hiç, bulunursa bir ImageIcon döndür...
    protected static ImageIcon yaratImageIcon (String yol, String izah) {
        java.net.URL resimURL = J4b_3.class.getResource (yol);
        if (resimURL != null) return new ImageIcon (resimURL, izah);
        else {System.err.println ("[" + yol + "] resim dosyasý bulunamadý!"); return null;}
    } // yaratImageIcon(..) metodu sonu...

    private JPanel komponentleriYarat() {
        JPanel üstPanel = new JPanel();
        üstPanel.setBackground (Color.GRAY.darker());
        üstPanel.setLayout (new BorderLayout (20, 20));

        String etiketMetni = "<html><font color=red size=+2>JDK Dökümantasyonu</font><br/>Java aydýnlanmasý için tek maðaza duraðý!<br/></html>";
        JLabel etiket = new JLabel (etiketMetni);
        üstPanel.add (etiket, BorderLayout.NORTH);

        etiket = new JLabel();
        ImageIcon logo = yaratImageIcon ("duke_skateboard.jpg", "logo");
        etiket.setIcon (logo);
        üstPanel.add (etiket, BorderLayout.CENTER);

        etiket = new JLabel ("<html><font color=yellow size=-2>Uygulama yükleniyor...</font></html>");
        üstPanel.add (etiket, BorderLayout.EAST);

        geliþimÇubuðu = new JProgressBar (0, 100);
        geliþimÇubuðu.setValue (0);
        geliþimÇubuðu.setStringPainted (true);
        üstPanel.add (geliþimÇubuðu, BorderLayout.SOUTH);

        return üstPanel;
    } // komponentleriYarat() metodu sonu...

    private void yaratGUI() {
        JPanel üstPanel = komponentleriYarat();
        çerçeve = new JFrame();
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        çerçeve.getContentPane().add (üstPanel, BorderLayout.CENTER);
        çerçeve.setBounds (200,200,500,500);
        çerçeve.pack();
        uiGeliþiminiGüncelle(50);
    } // yaratGUI() metodu sonu...

    public static void main (String[] args) {
        J4b_3 nesne = new J4b_3();

        // Bu uygulamanýn GUI'sini yaratýp gösterecek güvenli bir sicim görevi programlayalým...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {nesne.yaratGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J4b_3 sýnýfý sonu...