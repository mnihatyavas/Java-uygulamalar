// J5i_2.java: ComponentEventDemo (ParçaOlayýGösterisi) örneði.

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
    static JFrame çerçeve;
    JTextArea metinAlaný;
    JLabel etiket;
    String yeniSatýr = "\n";

    public J5i_2() {// Kurucu...
        super (new BorderLayout());

        metinAlaný = new JTextArea();
        metinAlaný.setEditable (false); // Müdahalesiz; sadece komponent olaylarýný sergiler...
        metinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f));
        metinAlaný.setForeground (Color.WHITE);
        //metinAlaný.addComponentListener (this); // Metinalaný/JTextArea olaylarýný da parça dinleyicisine duyarlayalým==>Sonsuz döngüye giriyor...
        JScrollPane kaydýrmaPanosu = new JScrollPane (metinAlaný);
        kaydýrmaPanosu.setPreferredSize (new Dimension (250, 200));

        JPanel panel = new JPanel (new BorderLayout());
        panel.setBackground (Color.GRAY);
        etiket = new JLabel ("Bu sadece bir etikettir.", JLabel.CENTER);
        etiket.setForeground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        etiket.addComponentListener (this); // Etiket/JLabel'i parça dinleyicisine duyarlayalým...
        panel.add (etiket, BorderLayout.CENTER);

        JCheckBox çentikKutusu = new JCheckBox ("Etiket görünsün mü?", true);
        çentikKutusu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        çentikKutusu.addItemListener (this); // ÇentikKutusu/JCheckBox'nu hem birim dinleyicisine duyarlayalým...
        çentikKutusu.addComponentListener(this); // Ve hem de  parça dinleyicisine duyarlayalým...
        panel.add (çentikKutusu, BorderLayout.PAGE_END);
        panel.addComponentListener (this); // Panel/JPanel olaylarýný da parça dinleyicisine duyarlayalým...

        add (kaydýrmaPanosu, BorderLayout.CENTER);
        add (panel, BorderLayout.PAGE_END);
        çerçeve.addComponentListener (this); // Çerçeve/Frame ebat ve taþýnma deðiþikliklerini parça dinleyicisine duyarlayalým...
    } // J5i_2() kurucusu sonu...

    public void itemStateChanged (ItemEvent olay) {// Sadece çentik kutusu için...
        if (olay.getStateChange() == ItemEvent.SELECTED) {// Çentiklendirildiyse...
            etiket.setVisible (true);
            etiket.revalidate();
            etiket.repaint();
        }else // Çentiksizlendirildiyse...
            etiket.setVisible (false);
    } // itemStateChanged(..) hazýr metodu sonu...

    // Dinlenen komponentler: JFrame, JPanel, JLabel, JCheckBox...
    public void componentHidden (ComponentEvent olay) {metinAlanýMesajý (olay.getComponent().getClass().getName() + " --- Gizlendi");}
    public void componentMoved (ComponentEvent olay) {metinAlanýMesajý (olay.getComponent().getClass().getName() + " --- Taþýndý");}
    public void componentResized (ComponentEvent olay) {metinAlanýMesajý (olay.getComponent().getClass().getName() + " --- Ebatlandýrýldý");}
    public void componentShown (ComponentEvent olay) {metinAlanýMesajý (olay.getComponent().getClass().getName() + " --- Gösterildi");}

    protected void metinAlanýMesajý (String mesaj) {
        if (metinAlaný.isShowing()) {
            metinAlaný.append (mesaj + yeniSatýr);
            metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
        } // if kararý sonu...
    } // metinAlanýMesajý(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        çerçeve = new JFrame ("Komponent Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_2(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i-2 sýnýfý sonu...