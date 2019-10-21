// J5c_6.java: ColorChooserDemo2 (RenkSe�iciG�sterim2) �rne�i.

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.BorderFactory;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;
import javax.swing.colorchooser.AbstractColorChooserPanel;

// Gereken alt-program: J5c_6x.java=CrayonPanel
public class J5c_6 extends JPanel implements ActionListener, ChangeListener {
    public JLabel renkMetniEtiketi;
    public JColorChooser renkSe�iciTablo;

    public J5c_6() {// Kurucu...
        super (new BorderLayout());

        // renkMetniEtiketi'ni varsay�l� �ng�r�nt� paneli olarak kullanmak i�in kural�m...
        renkMetniEtiketi = new JLabel ("E�itim B�lgesine Ho�geldiniz!", JLabel.CENTER);
        renkMetniEtiketi.setForeground (Color.yellow); // �lk metin rengi...
        renkMetniEtiketi.setBackground (Color.blue); // �lk zemin rengi...
        renkMetniEtiketi.setOpaque (true);
        renkMetniEtiketi.setFont (new Font ("Arial", Font.BOLD, 24)); // Metnin yaz� tipi...
        renkMetniEtiketi.setPreferredSize (new Dimension (100, 65));

        JPanel renkMetniEtiketiPaneli = new JPanel (new BorderLayout());
        renkMetniEtiketiPaneli.add (renkMetniEtiketi, BorderLayout.CENTER);
        renkMetniEtiketiPaneli.setBorder (BorderFactory.createTitledBorder ("Zemin ve Metnin Rengini De�i�tir"));

        // Zemin rengini de�i�tirmek i�in renk se�iciyi kural�m...
        JPanel zeminRengiPaneli = new JPanel(); //use FlowLayout
        JButton renkSe�iciButon = new JButton ("Renk Se�iciyi G�ster");
        renkSe�iciButon.addActionListener (this);
        zeminRengiPaneli.add (renkSe�iciButon);
        zeminRengiPaneli.setBorder (BorderFactory.createTitledBorder ("Zemin Rengini Se�in"));

        // Metin rengini de�i�tirecek renk se�iciyi kural�m...
        renkSe�iciTablo = new JColorChooser();
        renkSe�iciTablo.getSelectionModel().addChangeListener (this);
        renkSe�iciTablo.setBorder (BorderFactory.createTitledBorder ("Metin Rengini Se�in"));

        // Varsay�l� �ng�r�nt� zeminRengiPaneliini silelim...
        renkSe�iciTablo.setPreviewPanel (new JPanel());

        // Se�ici panelleri kendimizinkilerle override/esge�elim...
        AbstractColorChooserPanel paneller[] = { new J5c_6x() }; // J5c_6x=CrayonPanel/PastelPaneli...
        renkSe�iciTablo.setChooserPanels (paneller);
        renkSe�iciTablo.setColor (renkMetniEtiketi.getForeground());

        add (renkMetniEtiketiPaneli, BorderLayout.PAGE_START);
        add (zeminRengiPaneli, BorderLayout.CENTER);
        add (renkSe�iciTablo, BorderLayout.PAGE_END);
    } // J5c_6() kurucusu sonu...

    // Metin etiketinin zemin rengini de�i�tirelim...
    public void actionPerformed (ActionEvent olay) {
        Color yeniRenk = JColorChooser.showDialog (
                J5c_6.this,
                "Zemin Rengini Se�in",
                renkMetniEtiketi.getBackground());
        if (yeniRenk != null) renkMetniEtiketi.setBackground (yeniRenk); // De�i�en zemin rengi...
    } // actionPerformed(..) metodu sonu...

    // Metin etiketinin yaz� rengini de�i�tirelim...
    public void stateChanged (ChangeEvent olay) {
        Color yeniRenk = renkSe�iciTablo.getColor();
        if (yeniRenk != null) renkMetniEtiketi.setForeground (yeniRenk); // De�i�en metin rengi...
    } // stateChanged(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Renk Se�ici G�sterim 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_6();
        komponent.setOpaque(true);
        �er�eve.setContentPane (komponent);

        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeG�sterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_6 s�n�f� sonu...