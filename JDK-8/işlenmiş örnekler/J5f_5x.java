// J5f_5x.java: BLDComponent (BoxLayoutDemoKSGKomponenti) alt-�rne�i.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

class J5f_5x extends JComponent implements MouseListener {
    private Color normalRenk;
    private final Dimension tercihiEbat;
    private String kutuAd�;
    private boolean azamiEbatS�n�rl�M�;
    private boolean ebatG�sterilsinMi;

    public J5f_5x (// Kurucu arg�manlar� listesi...
            float hizalamaX,
            float renk,
            int boy,
            boolean s�n�rl�M�,
            boolean ebatG�sterilsinMi,
            String kutuAd�) {
        this.kutuAd� = kutuAd�;
        this.azamiEbatS�n�rl�M� = s�n�rl�M�;
        this.ebatG�sterilsinMi = ebatG�sterilsinMi;
        setAlignmentX (hizalamaX);
        normalRenk = Color.getHSBColor (renk, 0.84f, 0.65f); // HSB:HueSaturationBrightness...
        tercihiEbat = new Dimension (boy*2, boy); // En boyun 2 misli olsun...
        addMouseListener (this); // Kutu komponentlerini fareye (mousePressed) duyarlayal�m...
    } // J5f_5x(..) kurucusu sonu...

    public void mousePressed (MouseEvent olay) {
        int en = getWidth();
        float hizalama = (float)(olay.getX()) / (float)en;

        // Yatay yeniden hizalanmalar 0.1'er de�i�sin...
        int ge�ici = Math.round (hizalama * 10.0f);
        hizalama = (float)ge�ici / 10.0f;

        setAlignmentX (hizalama);
        revalidate();
        repaint(); // paint(..) metodunu �a��r�r...
    } // mousePressed(..) haz�r metodu sonu...
    public void mouseReleased (MouseEvent olay) {} // Ald�rma...
    public void mouseEntered (MouseEvent olay) {}
    public void mouseExited (MouseEvent olay) {}
    public void mouseClicked (MouseEvent olay) {}

    public boolean isOpaque() {return true;}

    public void paint (Graphics g) {
        int en = getWidth();
        int boy = getHeight();
        float hizalamaX = getAlignmentX();

        g.setColor (normalRenk);
        g.fill3DRect (0, 0, en, boy, true);

        // Kutunun yatay hizalanma b�l�nmesi dikey beyaz �izgiyle ayr�ls�n...
        g.setColor (Color.WHITE);
        int x = (int)(hizalamaX * (float)en) - 1;
        g.drawLine (x, 0, x, boy - 1);

        // Hizalanma derece say�s� [0.0->1.0] yaz�ls�n...
        g.setColor(Color.BLACK);
        g.drawString (Float.toString (hizalamaX), 3, boy - 3);

        if (ebatG�sterilsinMi) // De�i�en komponent ebat� dos penceresinden g�sterilsin...
            System.out.println ("KSGKomponenti " + kutuAd� + ": Ebat " + en + "x" + boy +
                    "; Tercihi ebat " + getPreferredSize().width + "x" + getPreferredSize().height);
    } // paint(..) haz�r metodu sonu...

    public Dimension getMaximumSize() {
        if (azamiEbatS�n�rl�M�) return tercihiEbat;
        else return super.getMaximumSize();
    } // Dimension getMaximumSize() haz�r metodu sonu...

    public Dimension getPreferredSize() {return tercihiEbat;}
    public Dimension getMinimumSize() {return tercihiEbat;}
    public void setSizeRestriction (boolean s�n�rl�M�) {azamiEbatS�n�rl�M� = s�n�rl�M�;}
} // J5f_5x s�n�f� sonu...