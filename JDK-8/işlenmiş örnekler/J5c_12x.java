// J5c_12x.java: CustomDialog (GelenekselDiyalog) alt-�rne�i.

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
    private JTextField metinSat�r�;
    private J5c_12 diyalogDemo; // J5c_12/DialogDemo...

    private String sihirliKelime;
    private JOptionPane tercihPanosu;

    private String butonDizgesi1 = "Gir";
    private String butonDizgesi2 = "�ptal";

    public J5c_12x (Frame �er�eve, String kelime, J5c_12 ebeveyn) {// Kurucu...
        super (�er�eve, true);
        diyalogDemo = ebeveyn;

        sihirliKelime = kelime.toUpperCase();
        setTitle ("S�nav");

        metinSat�r� = new JTextField (10);

        String mesajDizgesi1 = "M.N�HAT'�n en hakiki soyad� nedir?";
        String mesajDizgesi2 = "(Cevap: \"" + sihirliKelime+ "\".)";
        Object[] dizin = {mesajDizgesi1, mesajDizgesi2, metinSat�r�};

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
                metinSat�r�.requestFocusInWindow();
        }}); // add.. ifadesi sonu...

        metinSat�r�.addActionListener (this);

        tercihPanosu.addPropertyChangeListener (this);
    } // J5c_12x(..) kurucusu sonu...

    public String ge�erliMetniAl() {return girilenMetin;}
    public void actionPerformed (ActionEvent olay) {tercihPanosu.setValue (butonDizgesi1);}

    public void propertyChange (PropertyChangeEvent olay) {
        String �zellik = olay.getPropertyName();

        if (isVisible() && (olay.getSource() == tercihPanosu) && (JOptionPane.VALUE_PROPERTY.equals (�zellik) || JOptionPane.INPUT_VALUE_PROPERTY.equals (�zellik))) {
            Object de�er = tercihPanosu.getValue();

            if (de�er == JOptionPane.UNINITIALIZED_VALUE) return;

            tercihPanosu.setValue (JOptionPane.UNINITIALIZED_VALUE);

            if (butonDizgesi1.equals (de�er)) {
                girilenMetin = metinSat�r�.getText();
                String metinB�y�kHarf = girilenMetin.toUpperCase();

                if (sihirliKelime.equals (metinB�y�kHarf)) temizleVeGizle();
                else {metinSat�r�.selectAll();
                    JOptionPane.showMessageDialog (J5c_12x.this, "�zg�n�m, \"" + girilenMetin + "\" ge�erli cevap de�il.\nL�tfen " + sihirliKelime + "'� girin.", "Tekrar dene", JOptionPane.ERROR_MESSAGE);
                    girilenMetin = null;
                    metinSat�r�.requestFocusInWindow();
                } // i� if-else karar� sonu...
            }else {// Cevaps�z pencere kapat�l�r veya iptal t�klan�rsa...
                diyalogDemo.etiketiKoy ("PEKALA. Sizi " + sihirliKelime + "'yi girmeye zorlayamay�z!");
                girilenMetin = null;
                temizleVeGizle();
            } // d�� if-else karar� sonu...
        } // if-vis.. karar� sonu...
    } // propertyChange(..) metodu sonu...

    public void temizleVeGizle() {
        metinSat�r�.setText (null);
        setVisible (false);
    } // temizleVeGizle() metodu sonu...
} // J5c_12x s�n�f� sonu...