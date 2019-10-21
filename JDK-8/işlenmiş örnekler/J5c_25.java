// J5c_25.java: InternalFrameDemo (��PencereG�sterimi) �rne�i.

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Toolkit;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/* Gereken dosya:
 * J5c_25x.java=MyInternalFrame.java
 */
public class J5c_25 extends JFrame implements ActionListener {
    JDesktopPane masa�st�Panosu;

    public J5c_25() {// Kurucu...
        super ("�� Pencere G�sterimi"); // B�y�k pencere yarat�l�r...

        // B�y�k penceremiz ekran kenarlar�ndan 50*2=100 piksel i�erden ebatlans�n..
        int ekranMarj� = 50;
        Dimension ekranEbat� = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds (ekranMarj�, ekranMarj�, ekranEbat�.width  - ekranMarj� * 2, ekranEbat�.height - ekranMarj� * 2);

        // GUI/GraphicsUnitInterface/GrafikBirimiAray�z�'m�z� kural�m...
        masa�st�Panosu = new JDesktopPane(); // �� k���k pencereleri ta��rmayacak �zel katmanl� bir pano...
        pencereYarat(); // �lk "k���k" penceremizi yaratal�m...
        setContentPane (masa�st�Panosu); // K���k pencereli masa�st�Panosu'nu b�y�k pencereye kural�m/ekleyelim...
        masa�st�Panosu.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );

        setJMenuBar (men��ubu�uYarat()); // Men� �ubu�unu yarat�p penceremize kural�m/ekleyelim...

        // K���k i� pencereleri b�y�k pencere i�inde (ta��rmadan) s�r�kleme kipini a�al�m...
        masa�st�Panosu.setDragMode (JDesktopPane.OUTLINE_DRAG_MODE);
    } // J5c_25() kurucusu sonu...

    // Yeni bir (�oklu k���k i�) pencere yaratal�m...
    protected void pencereYarat() {
        J5c_25x pencere = new J5c_25x();
        pencere.setVisible (true);
        masa�st�Panosu.add (pencere);
        pencere.setBackground (new Color ((int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256)) );
        try {pencere.setSelected (true);
        } catch (java.beans.PropertyVetoException ist) {}
    } // pencereYarat() metodu sonu...

    protected JMenuBar men��ubu�uYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );

        // D�k�man men�s�n� yaratal�m...
        JMenu men� = new JMenu ("D�k�man");
        men�.setMnemonic (KeyEvent.VK_D);
        men��ubu�u.add (men�);

        // �lk "Yeni" men� birimini kural�m...
        JMenuItem men�Birimi = new JMenuItem ("Yeni");
        men�Birimi.setMnemonic (KeyEvent.VK_Y);
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_Y, ActionEvent.ALT_MASK));
        men�Birimi.setActionCommand ("yeni");
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        // �kinci "��k" men� birimini kural�m...
        men�Birimi = new JMenuItem ("��k");
        men�Birimi.setMnemonic (KeyEvent.VK_K); // T�rk�e �-� reddediliyor...
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_K, ActionEvent.ALT_MASK));
        men�Birimi.setActionCommand ("��k");
        men�Birimi.addActionListener (this);
        men�.add (men�Birimi);

        return men��ubu�u;
    } // men��ubu�uYarat() metodu sonu...

    // Men� se�imlerimlerine duyarl�...
    public void actionPerformed (ActionEvent olay) {
        if ("yeni".equals (olay.getActionCommand())) pencereYarat();
        else ��k();
    } // actionPerformed(..) haz�r metodu sonu...

    // ��karken uygulamay� kapatal�m...
    protected void ��k() {System.exit (0);}

    private static void yaratVeG�sterGUI() {
        // DokunVeHissetS�sl� penceremizi garantileyelim...
        JFrame.setDefaultLookAndFeelDecorated (true);

        // Penceremizi yarat�p, g�r�n�r k�lal�m...
        J5c_25 �er�eve = new J5c_25();
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_25 s�n�f� sonu...