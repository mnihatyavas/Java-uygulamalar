// J5i_2.java: ComponentEventDemo (Par�aOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ComponentListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;

public class J5i_2 extends JPanel implements ComponentListener, ItemListener {
    static JFrame �er�eve;
    JTextArea metinAlan�;
    JLabel etiket;
    String yeniSat�r = "\n";

    public J5i_2() {// Kurucu...
        super (new BorderLayout());

        metinAlan� = new JTextArea();
        metinAlan�.setEditable (false); // M�dahalesiz; sadece komponent olaylar�n� sergiler...
        metinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        metinAlan�.setForeground (Color.WHITE);
        //metinAlan�.addComponentListener (this); // Metinalan�/JTextArea olaylar�n� da par�a dinleyicisine duyarlayal�m==>Sonsuz d�ng�ye giriyor...
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        kayd�rmaPanosu.setPreferredSize (new Dimension (250, 200));

        JPanel panel = new JPanel (new BorderLayout());
        panel.setBackground (Color.GRAY);
        etiket = new JLabel ("Bu sadece bir etikettir.", JLabel.CENTER);
        etiket.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        etiket.addComponentListener (this); // Etiket/JLabel'i par�a dinleyicisine duyarlayal�m...
        panel.add (etiket, BorderLayout.CENTER);

        JCheckBox �entikKutusu = new JCheckBox ("Etiket g�r�ns�n m�?", true);
        �entikKutusu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        �entikKutusu.addItemListener (this); // �entikKutusu/JCheckBox'nu hem birim dinleyicisine duyarlayal�m...
        �entikKutusu.addComponentListener(this); // Ve hem de  par�a dinleyicisine duyarlayal�m...
        panel.add (�entikKutusu, BorderLayout.PAGE_END);
        panel.addComponentListener (this); // Panel/JPanel olaylar�n� da par�a dinleyicisine duyarlayal�m...

        add (kayd�rmaPanosu, BorderLayout.CENTER);
        add (panel, BorderLayout.PAGE_END);
        �er�eve.addComponentListener (this); // �er�eve/Frame ebat ve ta��nma de�i�ikliklerini par�a dinleyicisine duyarlayal�m...
    } // J5i_2() kurucusu sonu...

    public void itemStateChanged (ItemEvent olay) {// Sadece �entik kutusu i�in...
        if (olay.getStateChange() == ItemEvent.SELECTED) {// �entiklendirildiyse...
            etiket.setVisible (true);
            etiket.revalidate();
            etiket.repaint();
        }else // �entiksizlendirildiyse...
            etiket.setVisible (false);
    } // itemStateChanged(..) haz�r metodu sonu...

    // Dinlenen komponentler: JFrame, JPanel, JLabel, JCheckBox...
    public void componentHidden (ComponentEvent olay) {metinAlan�Mesaj� (olay.getComponent().getClass().getName() + " --- Gizlendi");}
    public void componentMoved (ComponentEvent olay) {metinAlan�Mesaj� (olay.getComponent().getClass().getName() + " --- Ta��nd�");}
    public void componentResized (ComponentEvent olay) {metinAlan�Mesaj� (olay.getComponent().getClass().getName() + " --- Ebatland�r�ld�");}
    public void componentShown (ComponentEvent olay) {metinAlan�Mesaj� (olay.getComponent().getClass().getName() + " --- G�sterildi");}

    protected void metinAlan�Mesaj� (String mesaj) {
        if (metinAlan�.isShowing()) {
            metinAlan�.append (mesaj + yeniSat�r);
            metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
        } // if karar� sonu...
    } // metinAlan�Mesaj�(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        �er�eve = new JFrame ("Komponent Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_2(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i-2 s�n�f� sonu...