// J4e_1a.java: DynamicTreeApplication (DinamikAðaçUygulamasý) örneði.

import javax.swing.JFrame;

// Gereken alt-program: J4c_1x1.java=DynamicTreePanel
public class J4e_1a extends JFrame {
    public static void main (String [] args) {
        J4e_1a uygulama = new J4e_1a();
        uygulama.yaratVeGösterGUI();
    } // main(..) metodu sonu...

    private void yaratVeGösterGUI() {
        // Ýçerik panosunu yarat ve kur...
        J4e_1x1 yeniÝçerikPanosu = new J4e_1x1(); // J4e_1x1=DynamicTreePanel/DinamikAðaçPaneli...
        yeniÝçerikPanosu.setOpaque (true); // Opak; transparan/þeffaf deðil...
        setContentPane (yeniÝçerikPanosu);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        pack();
        setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...
} // J4e_1a sýnýfý sonu...