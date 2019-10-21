// J5e_14.java: SplashDemo (SusýçramasýGösterisi) örneði.

import java.awt.Frame;
import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.MenuBar;
import java.awt.SplashScreen;
import java.awt.Graphics2D;
import java.awt.AlphaComposite;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class J5e_14 extends Frame implements ActionListener {
    public J5e_14() {// Kurucu...
        super ("Susýçramalý Ekran Gösterisi");
        setSize (300, 200);
        setLayout (new BorderLayout());
        Menu menü = new Menu ("Dosya");
        MenuItem menüBirimi = new MenuItem ("Çýk");
        menü.add (menüBirimi);
        menüBirimi.addActionListener (this); // "Çýk" menü birimini dinleyiciye duyarlayalým...
        this.addWindowListener (pencereyiKapat);

        MenuBar menüÇubuðu = new MenuBar();
        setMenuBar (menüÇubuðu);
        menüÇubuðu.add (menü);
        final SplashScreen suSýçramasý = SplashScreen.getSplashScreen();
        if (suSýçramasý == null) {
            System.out.println ("ÝKAZ: SplashScreen.getSplashScreen() namevcut sýnýfý 'null' döndürdü!");
            return;
        } // if kararý sonu...
        Graphics2D g = suSýçramasý.createGraphics();
        if (g == null) {System.out.println ("ÝKAZ: Susýçramalý grafik ekraný yaratýlamadý (null)!");
            return;
        } // if kararý sonu...
        for (int i=0; i<100; i++) {
            suSýçramalýÇerçeveyiSun (g, i);
            suSýçramasý.update();
            try {Thread.sleep (90);
            }catch (InterruptedException ist) {}
        } // for döngüsü sonu...
        suSýçramasý.close();
        setVisible (true);
        toFront();
    } // J5e_14() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {System.exit (0);}
    private static WindowListener pencereyiKapat = new WindowAdapter() {
        public void windowClosing (WindowEvent olay) {olay.getWindow().dispose();}
    }; // pri.. ifadesi sonu...

    static void suSýçramalýÇerçeveyiSun (Graphics2D g, int çerçeve) {
        final String[] bileþimler = {"foo", "bar", "baz"};
        g.setComposite (AlphaComposite.Clear);
        g.fillRect (120,140,200,40);
        g.setPaintMode();
        g.setColor (Color.BLACK);
        g.drawString ("Yüklüyor "+bileþimler[(çerçeve/5)%3] + "...", 120, 150);
    } // suSýçramalýÇerçeveyiSun(..) metodu sonu...

    public static void main (String args[]) {J5e_14 sýçrama = new J5e_14();} // Kurucuya...
} // J5e_14 sýnýfý sonu... 

/* Çýktý:
**  >java J5e_14  **
ÝKAZ: SplashScreen.getSplashScreen() namevcut sýnýfý 'null' döndürdü!
*/