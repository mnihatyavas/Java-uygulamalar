// J4b_2a.java: DynamicTreeApplication (DinamikA�a�Uygulamas�) �rne�i.

import javax.swing.JFrame;

// Gereken alt-program: J4b_2x1.java=DynamicTreePanel
public class J4b_2a extends JFrame {
    public static void main (String [] args) {
        J4b_2a uygulama = new J4b_2a();
        uygulama.yaratGUI();
    } // main(..) metodu sonu...

    private void yaratGUI() {
        // ��erik panosunu yarat�p kural�m...
        J4b_2x1 yeni��erikPanosu = new J4b_2x1();
        yeni��erikPanosu.setOpaque (true); 
        setContentPane (yeni��erikPanosu);
        setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        pack();
        setVisible (true);
    } // yaratGUI() metodu sonu...
} // J4b_2a s�n�f� sonu...