// J4b_3.java: CustomProgress (GelenekselGeli�im) �rne�i.

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
    JFrame �er�eve = null;
    JProgressBar geli�im�ubu�u = null;
    boolean uiYarat�ld�M� = false;

    public J4b_3() {} // Kurucu...
    public void indirimBa�ar�s�z (java.net.URL yurel, java.lang.String s�r�m) {}
    public void geli�im (URL yurel, String s�r�m, long �imdiyeDekOkunan, long �stPanellam, int has�laY�zdesi) {uiGeli�iminiG�ncelle (has�laY�zdesi);}
    public void ar�iviKatk�la (java.net.URL yurel, java.lang.String s�r�m, int katk�Y�zdesi, int has�laY�zdesi) {uiGeli�iminiG�ncelle (has�laY�zdesi);}
    public  void ge�erlileliyor (java.net.URL yurel, java.lang.String s�r�m, long i�erik, long �stPanellam,int has�laY�zdesi) {uiGeli�iminiG�ncelle (has�laY�zdesi);}

    private void uiGeli�iminiG�ncelle (int has�laY�zdesi) {
        if (has�laY�zdesi > 0 && has�laY�zdesi < 99) {
            if (!uiYarat�ld�M�) {
                uiYarat�ld�M� = true;
                yaratGUI();
            } // if!u.. karar� sonu...
            geli�im�ubu�u.setValue (has�laY�zdesi);
            SwingUtilities.invokeLater (new Runnable() {
                public void run() {�er�eve.setVisible (true);}
            }); // Sw.. ifadesi sonu...
        }else SwingUtilities.invokeLater (new Runnable() {public void run() {�er�eve.setVisible (false); �er�eve.dispose();}});
    } // uiGeli�iminiG�ncelle(..) metodu sonu...

    // Yol/resim bulunamazsa null/hi�, bulunursa bir ImageIcon d�nd�r...
    protected static ImageIcon yaratImageIcon (String yol, String izah) {
        java.net.URL resimURL = J4b_3.class.getResource (yol);
        if (resimURL != null) return new ImageIcon (resimURL, izah);
        else {System.err.println ("[" + yol + "] resim dosyas� bulunamad�!"); return null;}
    } // yaratImageIcon(..) metodu sonu...

    private JPanel komponentleriYarat() {
        JPanel �stPanel = new JPanel();
        �stPanel.setBackground (Color.GRAY.darker());
        �stPanel.setLayout (new BorderLayout (20, 20));

        String etiketMetni = "<html><font color=red size=+2>JDK D�k�mantasyonu</font><br/>Java ayd�nlanmas� i�in tek ma�aza dura��!<br/></html>";
        JLabel etiket = new JLabel (etiketMetni);
        �stPanel.add (etiket, BorderLayout.NORTH);

        etiket = new JLabel();
        ImageIcon logo = yaratImageIcon ("duke_skateboard.jpg", "logo");
        etiket.setIcon (logo);
        �stPanel.add (etiket, BorderLayout.CENTER);

        etiket = new JLabel ("<html><font color=yellow size=-2>Uygulama y�kleniyor...</font></html>");
        �stPanel.add (etiket, BorderLayout.EAST);

        geli�im�ubu�u = new JProgressBar (0, 100);
        geli�im�ubu�u.setValue (0);
        geli�im�ubu�u.setStringPainted (true);
        �stPanel.add (geli�im�ubu�u, BorderLayout.SOUTH);

        return �stPanel;
    } // komponentleriYarat() metodu sonu...

    private void yaratGUI() {
        JPanel �stPanel = komponentleriYarat();
        �er�eve = new JFrame();
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        �er�eve.getContentPane().add (�stPanel, BorderLayout.CENTER);
        �er�eve.setBounds (200,200,500,500);
        �er�eve.pack();
        uiGeli�iminiG�ncelle(50);
    } // yaratGUI() metodu sonu...

    public static void main (String[] args) {
        J4b_3 nesne = new J4b_3();

        // Bu uygulaman�n GUI'sini yarat�p g�sterecek g�venli bir sicim g�revi programlayal�m...
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {nesne.yaratGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J4b_3 s�n�f� sonu...