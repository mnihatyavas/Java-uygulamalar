// J5i_6.java: InternalFrameEventDemo (ÝçÇerçeveOlayýGösterisi) örneði.

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
    JTextArea kayýtlarýGöster;
    JDesktopPane masaüstüPanosu;
    JInternalFrame içÇerçeve;
    JInternalFrame dinlenenÝçÇerçeve;
    static final String GÖSTER = "göster";
    static final String TEMÝZLE = "temizle";
    String yeniSatýr = "\n";
    static final int en = 500;
    static final int boy = 300;

    public J5i_6 (String baþlýk) {// Kurucu...
        super (baþlýk);

        // Masaüstü panosu ve eklenecek diðer komponentleri kuralým...
        masaüstüPanosu = new JDesktopPane();
        masaüstüPanosu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), (float)Math.random()) );
        masaüstüPanosu.putClientProperty ("JDesktopPane.dragMode", "outline");
        // Masaüstü panosu ebatýný tanýmlayalým...
        masaüstüPanosu.setPreferredSize (new Dimension (en, boy));
        setContentPane (masaüstüPanosu);
        komponentleriKur();
        masaüstüPanosu.add (içÇerçeve);
        Dimension göstericiEbatý = içÇerçeve.getSize();
        içÇerçeve.setSize (en, göstericiEbatý.height);
    } // J5i_6(..) kurucusu sonu...

    // Olay yaratýcý iç çerçeve kayýtlarýný gösteren olay seyircisi iç çerçeveyi kurar...
    protected void komponentleriKur() {
        JButton gösterDüðmesi = new JButton ("Ýç çerçeveyi göster");
        gösterDüðmesi.setActionCommand (GÖSTER);
        gösterDüðmesi.addActionListener (this); // Dinleyiciye duyarlý...

        JButton temizleDüðmesi = new JButton ("Olay kayýtlarýný temizle");
        temizleDüðmesi.setActionCommand (TEMÝZLE);
        temizleDüðmesi.addActionListener (this); // Dinleyiciye duyarlý...

        kayýtlarýGöster = new JTextArea (3, 30);
        kayýtlarýGöster.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        kayýtlarýGöster.setForeground (Color.WHITE);
        kayýtlarýGöster.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýrýcý = new JScrollPane (kayýtlarýGöster);
        // Kaydýrýcýya tercihi ve enaz ebat tanýmlayalým...
        kaydýrýcý.setPreferredSize (new Dimension (200, 75));
        kaydýrýcý.setMinimumSize (new Dimension (10, 10));

        içÇerçeve = new JInternalFrame ("Olay Seyredici",
                true, // Ebatlanabilir...
                false, // Kapatýlamaz...
                false, // Büyütülemez...
                true); // Ýkonlaþtýrýlabilir...

        JPanel panel = new JPanel();
        panel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        panel.setLayout (new BoxLayout (panel, BoxLayout.PAGE_AXIS));
        gösterDüðmesi.setAlignmentX (CENTER_ALIGNMENT);
        panel.add (gösterDüðmesi);
        panel.add (Box.createRigidArea (new Dimension (0, 5)));
        panel.add (kaydýrýcý);
        panel.add (Box.createRigidArea (new Dimension (0, 5)));
        temizleDüðmesi.setAlignmentX (CENTER_ALIGNMENT);
        panel.add (temizleDüðmesi);

        içÇerçeve.setContentPane (panel);
        içÇerçeve.pack();
        içÇerçeve.setVisible (true);
    } // komponentleriKur() metodu sonu...

    public void internalFrameClosing (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve KAPATILIYOR", olay);}
    public void internalFrameClosed (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve KAPATILDI", olay);}
    public void internalFrameOpened (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve AÇILDI", olay);}
    public void internalFrameIconified (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve ÝKONLAÞTIRILDI", olay);}
    public void internalFrameDeiconified (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve ÝKONSUZLAÞTIRILDI", olay);}
    public void internalFrameActivated (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve AKTÝFLEÞTÝRÝLDÝ", olay);}
    public void internalFrameDeactivated (InternalFrameEvent olay) {mesajKaydý ("Ýç çerçeve AKTÝFSÝZLEÞTÝRÝLDÝ", olay);}

    void mesajKaydý (String önek, InternalFrameEvent olay) {
        String kayýt = önek + ": " + olay.getSource();
        kayýtlarýGöster.append (kayýt + yeniSatýr);
        kayýtlarýGöster.setCaretPosition (kayýtlarýGöster.getDocument().getLength());
    } // mesajKaydý(..) metodu sonu...

    // Göster ve temizle düðmelerini yönetelim...
    public void actionPerformed (ActionEvent olay) {
        if (GÖSTER.equals (olay.getActionCommand())) {// Göster düðmesi týklanmýþsa...
            // Namevcutsa yeni bir olay yaratýcý iç çerçeve yaratalým...
            if (dinlenenÝçÇerçeve == null) {
                dinlenenÝçÇerçeve = new JInternalFrame ("Olay Yaratýcý",
                        true, // Ebatlanabilir...
                        true, // Kapatýlabilir...
                        true, // Azamileþtirilebilir...
                        true); // Ýkonlaþtýrýlabilir...
                // Kapatmada silmeyelim, gizleyelim (aktifsizleþtirme)...
                dinlenenÝçÇerçeve.setDefaultCloseOperation (WindowConstants.HIDE_ON_CLOSE);
                // Yukarda tanýmlý hazýr iç çerçeve metodlarý olaylarýna duyarlayalým...
                dinlenenÝçÇerçeve.addInternalFrameListener (this);
                // Olay seyredici gibi bu iç çerçeveyi de masaüstü panosuna ekleyelim...
                masaüstüPanosu.add(dinlenenÝçÇerçeve);
                // Ebatlayýp (paketleme yerine) konumlandýralým...
                dinlenenÝçÇerçeve.setSize (300, 100);
                dinlenenÝçÇerçeve.setLocation (
                        en/2 - dinlenenÝçÇerçeve.getWidth()/2,
                        boy - dinlenenÝçÇerçeve.getHeight());
            } //iç-if kararý sonu
            dinlenenÝçÇerçeve.setVisible (true);
        }else kayýtlarýGöster.setText(""); // Temizle düðmesi týklanmýþsa...
    } // actionPerformed(..) hazýr metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame.setDefaultLookAndFeelDecorated (true);
        JFrame çerçeve = new J5i_6 ("Ýç Çerçeve Olayý Gösterisi"); // Kurucuyu çaðýrýr...
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.pack();
        çerçeve.setLocation (500,100);
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_6 sýnýfý sonu...