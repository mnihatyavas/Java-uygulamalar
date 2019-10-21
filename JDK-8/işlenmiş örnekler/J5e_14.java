// J5e_14.java: SplashDemo (Sus��ramas�G�sterisi) �rne�i.

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
        super ("Sus��ramal� Ekran G�sterisi");
        setSize (300, 200);
        setLayout (new BorderLayout());
        Menu men� = new Menu ("Dosya");
        MenuItem men�Birimi = new MenuItem ("��k");
        men�.add (men�Birimi);
        men�Birimi.addActionListener (this); // "��k" men� birimini dinleyiciye duyarlayal�m...
        this.addWindowListener (pencereyiKapat);

        MenuBar men��ubu�u = new MenuBar();
        setMenuBar (men��ubu�u);
        men��ubu�u.add (men�);
        final SplashScreen suS��ramas� = SplashScreen.getSplashScreen();
        if (suS��ramas� == null) {
            System.out.println ("�KAZ: SplashScreen.getSplashScreen() namevcut s�n�f� 'null' d�nd�rd�!");
            return;
        } // if karar� sonu...
        Graphics2D g = suS��ramas�.createGraphics();
        if (g == null) {System.out.println ("�KAZ: Sus��ramal� grafik ekran� yarat�lamad� (null)!");
            return;
        } // if karar� sonu...
        for (int i=0; i<100; i++) {
            suS��ramal��er�eveyiSun (g, i);
            suS��ramas�.update();
            try {Thread.sleep (90);
            }catch (InterruptedException ist) {}
        } // for d�ng�s� sonu...
        suS��ramas�.close();
        setVisible (true);
        toFront();
    } // J5e_14() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {System.exit (0);}
    private static WindowListener pencereyiKapat = new WindowAdapter() {
        public void windowClosing (WindowEvent olay) {olay.getWindow().dispose();}
    }; // pri.. ifadesi sonu...

    static void suS��ramal��er�eveyiSun (Graphics2D g, int �er�eve) {
        final String[] bile�imler = {"foo", "bar", "baz"};
        g.setComposite (AlphaComposite.Clear);
        g.fillRect (120,140,200,40);
        g.setPaintMode();
        g.setColor (Color.BLACK);
        g.drawString ("Y�kl�yor "+bile�imler[(�er�eve/5)%3] + "...", 120, 150);
    } // suS��ramal��er�eveyiSun(..) metodu sonu...

    public static void main (String args[]) {J5e_14 s��rama = new J5e_14();} // Kurucuya...
} // J5e_14 s�n�f� sonu... 

/* ��kt�:
**  >java J5e_14  **
�KAZ: SplashScreen.getSplashScreen() namevcut s�n�f� 'null' d�nd�rd�!
*/