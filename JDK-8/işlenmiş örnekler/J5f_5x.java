// J5f_5x.java: BLDComponent (BoxLayoutDemoKSGKomponenti) alt-örneði.

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

class J5f_5x extends JComponent implements MouseListener {
    private Color normalRenk;
    private final Dimension tercihiEbat;
    private String kutuAdý;
    private boolean azamiEbatSýnýrlýMý;
    private boolean ebatGösterilsinMi;

    public J5f_5x (// Kurucu argümanlarý listesi...
            float hizalamaX,
            float renk,
            int boy,
            boolean sýnýrlýMý,
            boolean ebatGösterilsinMi,
            String kutuAdý) {
        this.kutuAdý = kutuAdý;
        this.azamiEbatSýnýrlýMý = sýnýrlýMý;
        this.ebatGösterilsinMi = ebatGösterilsinMi;
        setAlignmentX (hizalamaX);
        normalRenk = Color.getHSBColor (renk, 0.84f, 0.65f); // HSB:HueSaturationBrightness...
        tercihiEbat = new Dimension (boy*2, boy); // En boyun 2 misli olsun...
        addMouseListener (this); // Kutu komponentlerini fareye (mousePressed) duyarlayalým...
    } // J5f_5x(..) kurucusu sonu...

    public void mousePressed (MouseEvent olay) {
        int en = getWidth();
        float hizalama = (float)(olay.getX()) / (float)en;

        // Yatay yeniden hizalanmalar 0.1'er deðiþsin...
        int geçici = Math.round (hizalama * 10.0f);
        hizalama = (float)geçici / 10.0f;

        setAlignmentX (hizalama);
        revalidate();
        repaint(); // paint(..) metodunu çaðýrýr...
    } // mousePressed(..) hazýr metodu sonu...
    public void mouseReleased (MouseEvent olay) {} // Aldýrma...
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

        // Kutunun yatay hizalanma bölünmesi dikey beyaz çizgiyle ayrýlsýn...
        g.setColor (Color.WHITE);
        int x = (int)(hizalamaX * (float)en) - 1;
        g.drawLine (x, 0, x, boy - 1);

        // Hizalanma derece sayýsý [0.0->1.0] yazýlsýn...
        g.setColor(Color.BLACK);
        g.drawString (Float.toString (hizalamaX), 3, boy - 3);

        if (ebatGösterilsinMi) // Deðiþen komponent ebatý dos penceresinden gösterilsin...
            System.out.println ("KSGKomponenti " + kutuAdý + ": Ebat " + en + "x" + boy +
                    "; Tercihi ebat " + getPreferredSize().width + "x" + getPreferredSize().height);
    } // paint(..) hazýr metodu sonu...

    public Dimension getMaximumSize() {
        if (azamiEbatSýnýrlýMý) return tercihiEbat;
        else return super.getMaximumSize();
    } // Dimension getMaximumSize() hazýr metodu sonu...

    public Dimension getPreferredSize() {return tercihiEbat;}
    public Dimension getMinimumSize() {return tercihiEbat;}
    public void setSizeRestriction (boolean sýnýrlýMý) {azamiEbatSýnýrlýMý = sýnýrlýMý;}
} // J5f_5x sýnýfý sonu...