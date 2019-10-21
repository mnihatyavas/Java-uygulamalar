// J5c_12x.java: CustomDialog (GelenekselDiyalog) alt-örneði.

import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JTextField;
import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

class  J5c_12x extends JDialog implements ActionListener, PropertyChangeListener {
    private String girilenMetin = null;
    private JTextField metinSatýrý;
    private J5c_12 diyalogDemo; // J5c_12/DialogDemo...

    private String sihirliKelime;
    private JOptionPane tercihPanosu;

    private String butonDizgesi1 = "Gir";
    private String butonDizgesi2 = "Ýptal";

    public J5c_12x (Frame çerçeve, String kelime, J5c_12 ebeveyn) {// Kurucu...
        super (çerçeve, true);
        diyalogDemo = ebeveyn;

        sihirliKelime = kelime.toUpperCase();
        setTitle ("Sýnav");

        metinSatýrý = new JTextField (10);

        String mesajDizgesi1 = "M.NÝHAT'ýn en hakiki soyadý nedir?";
        String mesajDizgesi2 = "(Cevap: \"" + sihirliKelime+ "\".)";
        Object[] dizin = {mesajDizgesi1, mesajDizgesi2, metinSatýrý};

        Object[] tercihler = {butonDizgesi1, butonDizgesi2};

        tercihPanosu = new JOptionPane (dizin, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION, null, tercihler, tercihler[0]);
        setContentPane (tercihPanosu);

        setDefaultCloseOperation (DO_NOTHING_ON_CLOSE);

        addWindowListener (new WindowAdapter() {
            public void windowClosing (WindowEvent olay) {
                tercihPanosu.setValue (new Integer (JOptionPane.CLOSED_OPTION));
        }}); // add.. ifadesi sonu...

        addComponentListener (new ComponentAdapter() {
            public void componentShown (ComponentEvent olay) {
                metinSatýrý.requestFocusInWindow();
        }}); // add.. ifadesi sonu...

        metinSatýrý.addActionListener (this);

        tercihPanosu.addPropertyChangeListener (this);
    } // J5c_12x(..) kurucusu sonu...

    public String geçerliMetniAl() {return girilenMetin;}
    public void actionPerformed (ActionEvent olay) {tercihPanosu.setValue (butonDizgesi1);}

    public void propertyChange (PropertyChangeEvent olay) {
        String özellik = olay.getPropertyName();

        if (isVisible() && (olay.getSource() == tercihPanosu) && (JOptionPane.VALUE_PROPERTY.equals (özellik) || JOptionPane.INPUT_VALUE_PROPERTY.equals (özellik))) {
            Object deðer = tercihPanosu.getValue();

            if (deðer == JOptionPane.UNINITIALIZED_VALUE) return;

            tercihPanosu.setValue (JOptionPane.UNINITIALIZED_VALUE);

            if (butonDizgesi1.equals (deðer)) {
                girilenMetin = metinSatýrý.getText();
                String metinBüyükHarf = girilenMetin.toUpperCase();

                if (sihirliKelime.equals (metinBüyükHarf)) temizleVeGizle();
                else {metinSatýrý.selectAll();
                    JOptionPane.showMessageDialog (J5c_12x.this, "Üzgünüm, \"" + girilenMetin + "\" geçerli cevap deðil.\nLütfen " + sihirliKelime + "'ý girin.", "Tekrar dene", JOptionPane.ERROR_MESSAGE);
                    girilenMetin = null;
                    metinSatýrý.requestFocusInWindow();
                } // iç if-else kararý sonu...
            }else {// Cevapsýz pencere kapatýlýr veya iptal týklanýrsa...
                diyalogDemo.etiketiKoy ("PEKALA. Sizi " + sihirliKelime + "'yi girmeye zorlayamayýz!");
                girilenMetin = null;
                temizleVeGizle();
            } // dýþ if-else kararý sonu...
        } // if-vis.. kararý sonu...
    } // propertyChange(..) metodu sonu...

    public void temizleVeGizle() {
        metinSatýrý.setText (null);
        setVisible (false);
    } // temizleVeGizle() metodu sonu...
} // J5c_12x sýnýfý sonu...