// J5i_6.java: InternalFrameEventDemo (���er�eveOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.WindowConstants;
import javax.swing.event.InternalFrameListener;
import javax.swing.event.InternalFrameEvent;

public class J5i_6 extends JFrame implements InternalFrameListener, ActionListener {
    JTextArea kay�tlar�G�ster;
    JDesktopPane masa�st�Panosu;
    JInternalFrame i��er�eve;
    JInternalFrame dinlenen���er�eve;
    static final String G�STER = "g�ster";
    static final String TEM�ZLE = "temizle";
    String yeniSat�r = "\n";
    static final int en = 500;
    static final int boy = 300;

    public J5i_6 (String ba�l�k) {// Kurucu...
        super (ba�l�k);

        // Masa�st� panosu ve eklenecek di�er komponentleri kural�m...
        masa�st�Panosu = new JDesktopPane();
        masa�st�Panosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) );
        masa�st�Panosu.putClientProperty ("JDesktopPane.dragMode", "outline");
        // Masa�st� panosu ebat�n� tan�mlayal�m...
        masa�st�Panosu.setPreferredSize (new Dimension (en, boy));
        setContentPane (masa�st�Panosu);
        komponentleriKur();
        masa�st�Panosu.add (i��er�eve);
        Dimension g�stericiEbat� = i��er�eve.getSize();
        i��er�eve.setSize (en, g�stericiEbat�.height);
    } // J5i_6(..) kurucusu sonu...

    // Olay yarat�c� i� �er�eve kay�tlar�n� g�steren olay seyircisi i� �er�eveyi kurar...
    protected void komponentleriKur() {
        JButton g�sterD��mesi = new JButton ("�� �er�eveyi g�ster");
        g�sterD��mesi.setActionCommand (G�STER);
        g�sterD��mesi.addActionListener (this); // Dinleyiciye duyarl�...

        JButton temizleD��mesi = new JButton ("Olay kay�tlar�n� temizle");
        temizleD��mesi.setActionCommand (TEM�ZLE);
        temizleD��mesi.addActionListener (this); // Dinleyiciye duyarl�...

        kay�tlar�G�ster = new JTextArea (3, 30);
        kay�tlar�G�ster.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kay�tlar�G�ster.setForeground (Color.WHITE);
        kay�tlar�G�ster.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�r�c� = new JScrollPane (kay�tlar�G�ster);
        // Kayd�r�c�ya tercihi ve enaz ebat tan�mlayal�m...
        kayd�r�c�.setPreferredSize (new Dimension (200, 75));
        kayd�r�c�.setMinimumSize (new Dimension (10, 10));

        i��er�eve = new JInternalFrame ("Olay Seyredici",
                true, // Ebatlanabilir...
                false, // Kapat�lamaz...
                false, // B�y�t�lemez...
                true); // �konla�t�r�labilir...

        JPanel panel = new JPanel();
        panel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        g�sterD��mesi.setAlignmentX (CENTER_ALIGNMENT);
        panel.add (g�sterD��mesi);
        panel.add (Box.createRigidArea (new Dimension (0, 5)));
        panel.add (kayd�r�c�);
        panel.add (Box.createRigidArea (new Dimension (0, 5)));
        temizleD��mesi.setAlignmentX (CENTER_ALIGNMENT);
        panel.add (temizleD��mesi);

        i��er�eve.setContentPane (panel);
        i��er�eve.pack();
        i��er�eve.setVisible (true);
    } // komponentleriKur() metodu sonu...

    public void internalFrameClosing (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve KAPATILIYOR", olay);}
    public void internalFrameClosed (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve KAPATILDI", olay);}
    public void internalFrameOpened (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve A�ILDI", olay);}
    public void internalFrameIconified (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve �KONLA�TIRILDI", olay);}
    public void internalFrameDeiconified (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve �KONSUZLA�TIRILDI", olay);}
    public void internalFrameActivated (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve AKT�FLE�T�R�LD�", olay);}
    public void internalFrameDeactivated (InternalFrameEvent olay) {mesajKayd� ("�� �er�eve AKT�FS�ZLE�T�R�LD�", olay);}

    void mesajKayd� (String �nek, InternalFrameEvent olay) {
        String kay�t = �nek + ": " + olay.getSource();
        kay�tlar�G�ster.append (kay�t + yeniSat�r);
        kay�tlar�G�ster.setCaretPosition (kay�tlar�G�ster.getDocument().getLength());
    } // mesajKayd�(..) metodu sonu...

    // G�ster ve temizle d��melerini y�netelim...
    public void actionPerformed (ActionEvent olay) {
        if (G�STER.equals (olay.getActionCommand())) {// G�ster d��mesi t�klanm��sa...
            // Namevcutsa yeni bir olay yarat�c� i� �er�eve yaratal�m...
            if (dinlenen���er�eve == null) {
                dinlenen���er�eve = new JInternalFrame ("Olay Yarat�c�",
                        true, // Ebatlanabilir...
                        true, // Kapat�labilir...
                        true, // Azamile�tirilebilir...
                        true); // �konla�t�r�labilir...
                // Kapatmada silmeyelim, gizleyelim (aktifsizle�tirme)...
                dinlenen���er�eve.setDefaultCloseOperation (WindowConstants.HIDE_ON_CLOSE);
                // Yukarda tan�ml� haz�r i� �er�eve metodlar� olaylar�na duyarlayal�m...
                dinlenen���er�eve.addInternalFrameListener (this);
                // Olay seyredici gibi bu i� �er�eveyi de masa�st� panosuna ekleyelim...
                masa�st�Panosu.add(dinlenen���er�eve);
                // Ebatlay�p (paketleme yerine) konumland�ral�m...
                dinlenen���er�eve.setSize (300, 100);
                dinlenen���er�eve.setLocation (
                        en/2 - dinlenen���er�eve.getWidth()/2,
                        boy - dinlenen���er�eve.getHeight());
            } //i�-if karar� sonu
            dinlenen���er�eve.setVisible (true);
        }else kay�tlar�G�ster.setText(""); // Temizle d��mesi t�klanm��sa...
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame �er�eve = new J5i_6 ("�� �er�eve Olay� G�sterisi"); // Kurucuyu �a��r�r...
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.pack();
        �er�eve.setLocation (500,100);
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_6 s�n�f� sonu...