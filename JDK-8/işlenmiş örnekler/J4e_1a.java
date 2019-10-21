// J4e_1a.java: DynamicTreeApplication (DinamikA�a�Uygulamas�) �rne�i.

import javax.swing.JFrame;

// Gereken alt-program: J4c_1x1.java=DynamicTreePanel
public class J4e_1a extends JFrame {
    public static void main (String [] args) {
        J4e_1a uygulama = new J4e_1a();
        uygulama.yaratVeG�sterGUI();
    } // main(..) metodu sonu...

    private void yaratVeG�sterGUI() {
        // ��erik panosunu yarat ve kur...
        J4e_1x1 yeni��erikPanosu = new J4e_1x1(); // J4e_1x1=DynamicTreePanel/DinamikA�a�Paneli...
        yeni��erikPanosu.setOpaque (true); // Opak; transparan/�effaf de�il...
        setContentPane (yeni��erikPanosu);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        pack();
        setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...
} // J4e_1a s�n�f� sonu...