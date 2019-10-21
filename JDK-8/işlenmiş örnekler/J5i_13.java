// J5i_13.java: MultiListener (�okluDinleyici) �rne�i.

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
    JTextArea �stMetinAlan�;
    JTextArea altMetinAlan�;
    JButton d��me1, d��me2;
    final static String yeniSat�r = "\n";

    public J5i_13() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout �zgara�antaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        JLabel etiket = null;

        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        etiket = new JLabel ("Senin duyduklar�n:");
        �zgara�antaSerilim.setConstraints (etiket, s�n�rlay�c�lar);
        add (etiket);

        s�n�rlay�c�lar.weighty = 1.0;
        �stMetinAlan� = new JTextArea();
        �stMetinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.65f) );
        �stMetinAlan�.setForeground (Color.YELLOW);
        �stMetinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane �stKayd�ra� = new JScrollPane (�stMetinAlan�);
        Dimension tercihiEbat = new Dimension (200, 75);
        �stKayd�ra�.setPreferredSize (tercihiEbat);
        �zgara�antaSerilim.setConstraints (�stKayd�ra�, s�n�rlay�c�lar);
        add (�stKayd�ra�);

        s�n�rlay�c�lar.weightx = 0.0;
        s�n�rlay�c�lar.weighty = 0.0;
        etiket = new JLabel ("Dam �st�ndeki saksa�an�n duyduklar�:");
        �zgara�antaSerilim.setConstraints (etiket, s�n�rlay�c�lar);
        add (etiket);

        s�n�rlay�c�lar.weighty = 1.0;
        altMetinAlan� = new JTextArea();
        altMetinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.45f) );
        altMetinAlan�.setForeground (Color.CYAN);
        altMetinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane altKayd�ra� = new JScrollPane (altMetinAlan�);
        altKayd�ra�.setPreferredSize (tercihiEbat);
        �zgara�antaSerilim.setConstraints (altKayd�ra�, s�n�rlay�c�lar);
        add (altKayd�ra�);

        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 0.0;
        s�n�rlay�c�lar.gridwidth = 1;
        s�n�rlay�c�lar.insets = new Insets (1,1, 0,1);
        d��me1 = new JButton ("Lak lak lak");
        �zgara�antaSerilim.setConstraints (d��me1, s�n�rlay�c�lar);
        add (d��me1);

        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        d��me2 = new JButton ("Deme yahu!");
        �zgara�antaSerilim.setConstraints (d��me2, s�n�rlay�c�lar);
        add (d��me2);

        d��me1.addActionListener (this); // Her iki d��me de dinleyiciye duyarl�...
        d��me2.addActionListener (this);
        // d��me2, ayr�ca alt metin alan� i�in �zel dinleyiciye de duyarl�...
        d��me2.addActionListener (new Dam�st�Saksa�an� (altMetinAlan�));

        setPreferredSize (new Dimension (250, 300));
        setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createMatteBorder (2,2,5,5,Color.ORANGE),
                BorderFactory.createEmptyBorder (5,5,5,5)));
    } // J5i_13() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        �stMetinAlan�.append (olay.getActionCommand() + yeniSat�r);
        �stMetinAlan�.setCaretPosition (�stMetinAlan�.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("�oklu Dinleyici");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_13(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_13 s�n�f� sonu...

class Dam�st�Saksa�an� implements ActionListener {
    JTextArea metinAlan�m;
    public Dam�st�Saksa�an� (JTextArea ma) {metinAlan�m = ma;} // Kurucu...

    public void actionPerformed (ActionEvent olay) {
        metinAlan�m.append (olay.getActionCommand() + J5i_13.yeniSat�r);
        metinAlan�m.setCaretPosition (metinAlan�m.getDocument().getLength());
    } // actionPerformed(..) haz�r metodu sonu...
} // Dam�st�Saksa�an� s�n�f� sonu...