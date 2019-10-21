// J4b_2a.java: DynamicTreeApplication (DinamikAðaçUygulamasý) örneði.

import javax.swing.JFrame;

// Gereken alt-program: J4b_2x1.java=DynamicTreePanel
public class J4b_2a extends JFrame {
    public static void main (String [] args) {
        J4b_2a uygulama = new J4b_2a();
        uygulama.yaratGUI();
    } // main(..) metodu sonu...

    private void yaratGUI() {
        // Ýçerik panosunu yaratýp kuralým...
        J4b_2x1 yeniÝçerikPanosu = new J4b_2x1();
        yeniÝçerikPanosu.setOpaque (true); 
        setContentPane (yeniÝçerikPanosu);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        pack();
        setVisible (true);
    } // yaratGUI() metodu sonu...
} // J4b_2a sýnýfý sonu...