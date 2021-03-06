// J5i_13.java: MultiListener (ÇokluDinleyici) örneği.

import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

public class J5i_13 extends JPanel implements ActionListener {
    JTextArea üstMetinAlanı;
    JTextArea altMetinAlanı;
    JButton düğme1, düğme2;
    final static String yeniSatır = "\n";

    public J5i_13() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ızgaraÇantaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints sınırlayıcılar = new GridBagConstraints();

        JLabel etiket = null;

        sınırlayıcılar.fill = GridBagConstraints.BOTH;
        sınırlayıcılar.gridwidth = GridBagConstraints.REMAINDER;
        etiket = new JLabel ("Senin duydukların:");
        ızgaraÇantaSerilim.setConstraints (etiket, sınırlayıcılar);
        add (etiket);

        sınırlayıcılar.weighty = 1.0;
        üstMetinAlanı = new JTextArea();
        üstMetinAlanı.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        üstMetinAlanı.setForeground (Color.YELLOW);
        üstMetinAlanı.setEditable (false); // Müdahalesiz...
        JScrollPane üstKaydıraç = new JScrollPane (üstMetinAlanı);
        Dimension tercihiEbat = new Dimension (200, 75);
        üstKaydıraç.setPreferredSize (tercihiEbat);
        ızgaraÇantaSerilim.setConstraints (üstKaydıraç, sınırlayıcılar);
        add (üstKaydıraç);

        sınırlayıcılar.weightx = 0.0;
        sınırlayıcılar.weighty = 0.0;
        etiket = new JLabel ("Dam üstündeki saksağanın duydukları:");
        ızgaraÇantaSerilim.setConstraints (etiket, sınırlayıcılar);
        add (etiket);

        sınırlayıcılar.weighty = 1.0;
        altMetinAlanı = new JTextArea();
        altMetinAlanı.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.45f) );
        altMetinAlanı.setForeground (Color.CYAN);
        altMetinAlanı.setEditable (false); // Müdahalesiz...
        JScrollPane altKaydıraç = new JScrollPane (altMetinAlanı);
        altKaydıraç.setPreferredSize (tercihiEbat);
        ızgaraÇantaSerilim.setConstraints (altKaydıraç, sınırlayıcılar);
        add (altKaydıraç);

        sınırlayıcılar.weightx = 1.0;
        sınırlayıcılar.weighty = 0.0;
        sınırlayıcılar.gridwidth = 1;
        sınırlayıcılar.insets = new Insets (1,1, 0,1);
        düğme1 = new JButton ("Lak lak lak");
        ızgaraÇantaSerilim.setConstraints (düğme1, sınırlayıcılar);
        add (düğme1);

        sınırlayıcılar.gridwidth = GridBagConstraints.REMAINDER;
        düğme2 = new JButton ("Deme yahu!");
        ızgaraÇantaSerilim.setConstraints (düğme2, sınırlayıcılar);
        add (düğme2);

        düğme1.addActionListener (this); // Her iki düğme de dinleyiciye duyarlı...
        düğme2.addActionListener (this);
        // düğme2, ayrıca alt metin alanı için özel dinleyiciye de duyarlı...
        düğme2.addActionListener (new DamÜstüSaksağanı (altMetinAlanı));

        setPreferredSize (new Dimension (250, 300));
        setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createMatteBorder (2,2,5,5,Color.ORANGE),
                BorderFactory.createEmptyBorder (5,5,5,5)));
    } // J5i_13() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        üstMetinAlanı.append (olay.getActionCommand() + yeniSatır);
        üstMetinAlanı.setCaretPosition (üstMetinAlanı.getDocument().getLength());
    } // actionPerformed(..) hazır metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Çoklu Dinleyici");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniİçerikPanosu = new J5i_13(); // Kurucuyu çağırır...
        yeniİçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniİçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_13 sınıfı sonu...

class DamÜstüSaksağanı implements ActionListener {
    JTextArea metinAlanım;
    public DamÜstüSaksağanı (JTextArea ma) {metinAlanım = ma;} // Kurucu...

    public void actionPerformed (ActionEvent olay) {
        metinAlanım.append (olay.getActionCommand() + J5i_13.yeniSatır);
        metinAlanım.setCaretPosition (metinAlanım.getDocument().getLength());
    } // actionPerformed(..) hazır metodu sonu...
} // DamÜstüSaksağanı sınıfı sonu...