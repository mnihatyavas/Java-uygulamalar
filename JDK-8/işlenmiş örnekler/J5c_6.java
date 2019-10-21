// J5c_6.java: ColorChooserDemo2 (RenkSeçiciGösterim2) örneði.

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
    public JColorChooser renkSeçiciTablo;

    public J5c_6() {// Kurucu...
        super (new BorderLayout());

        // renkMetniEtiketi'ni varsayýlý öngörüntü paneli olarak kullanmak için kuralým...
        renkMetniEtiketi = new JLabel ("Eðitim Bölgesine Hoþgeldiniz!", JLabel.CENTER);
        renkMetniEtiketi.setForeground (Color.yellow); // Ýlk metin rengi...
        renkMetniEtiketi.setBackground (Color.blue); // Ýlk zemin rengi...
        renkMetniEtiketi.setOpaque (true);
        renkMetniEtiketi.setFont (new Font ("Arial", Font.BOLD, 24)); // Metnin yazý tipi...
        renkMetniEtiketi.setPreferredSize (new Dimension (100, 65));

        JPanel renkMetniEtiketiPaneli = new JPanel (new BorderLayout());
        renkMetniEtiketiPaneli.add (renkMetniEtiketi, BorderLayout.CENTER);
        renkMetniEtiketiPaneli.setBorder (BorderFactory.createTitledBorder ("Zemin ve Metnin Rengini Deðiþtir"));

        // Zemin rengini deðiþtirmek için renk seçiciyi kuralým...
        JPanel zeminRengiPaneli = new JPanel(); //use FlowLayout
        JButton renkSeçiciButon = new JButton ("Renk Seçiciyi Göster");
        renkSeçiciButon.addActionListener (this);
        zeminRengiPaneli.add (renkSeçiciButon);
        zeminRengiPaneli.setBorder (BorderFactory.createTitledBorder ("Zemin Rengini Seçin"));

        // Metin rengini deðiþtirecek renk seçiciyi kuralým...
        renkSeçiciTablo = new JColorChooser();
        renkSeçiciTablo.getSelectionModel().addChangeListener (this);
        renkSeçiciTablo.setBorder (BorderFactory.createTitledBorder ("Metin Rengini Seçin"));

        // Varsayýlý öngörüntü zeminRengiPaneliini silelim...
        renkSeçiciTablo.setPreviewPanel (new JPanel());

        // Seçici panelleri kendimizinkilerle override/esgeçelim...
        AbstractColorChooserPanel paneller[] = { new J5c_6x() }; // J5c_6x=CrayonPanel/PastelPaneli...
        renkSeçiciTablo.setChooserPanels (paneller);
        renkSeçiciTablo.setColor (renkMetniEtiketi.getForeground());

        add (renkMetniEtiketiPaneli, BorderLayout.PAGE_START);
        add (zeminRengiPaneli, BorderLayout.CENTER);
        add (renkSeçiciTablo, BorderLayout.PAGE_END);
    } // J5c_6() kurucusu sonu...

    // Metin etiketinin zemin rengini deðiþtirelim...
    public void actionPerformed (ActionEvent olay) {
        Color yeniRenk = JColorChooser.showDialog (
                J5c_6.this,
                "Zemin Rengini Seçin",
                renkMetniEtiketi.getBackground());
        if (yeniRenk != null) renkMetniEtiketi.setBackground (yeniRenk); // Deðiþen zemin rengi...
    } // actionPerformed(..) metodu sonu...

    // Metin etiketinin yazý rengini deðiþtirelim...
    public void stateChanged (ChangeEvent olay) {
        Color yeniRenk = renkSeçiciTablo.getColor();
        if (yeniRenk != null) renkMetniEtiketi.setForeground (yeniRenk); // Deðiþen metin rengi...
    } // stateChanged(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Renk Seçici Gösterim 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        JComponent komponent = new J5c_6();
        komponent.setOpaque(true);
        çerçeve.setContentPane (komponent);

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {yaratVeGösterGUI();}
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_6 sýnýfý sonu...