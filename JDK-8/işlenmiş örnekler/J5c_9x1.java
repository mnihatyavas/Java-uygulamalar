// J5c_9x1.java: ConversionPanel (De�i�imPaneli) alt-�rne�i.

import javax.swing.JPanel;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;
import javax.swing.JSlider;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.text.NumberFormat;
import javax.swing.text.NumberFormatter;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class J5c_9x1 extends JPanel implements ActionListener, ChangeListener, PropertyChangeListener {
    JFormattedTextField metinSat�r�;
    JComboBox birimSe�ici;
    JSlider s�rg�;
    J5c_9x3 s�rg�Modeli;
    J5c_9 kontrol�r;
    J5c_9x2[] birimler;
    String ba�l�k;
    NumberFormat say�Bi�imi;

    final static boolean �OKRENKL� = true;
    final static int AZAM� = 10000;

    J5c_9x1 (J5c_9 kontrol�r�m, String ba�l���m, J5c_9x2[] birimlerim, J5c_9x3 modelim) {
        if (�OKRENKL�) {setOpaque (true);
            setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if karar� sonu...
        setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createTitledBorder (ba�l���m),
                BorderFactory.createEmptyBorder (5,5,5,5)));

        // Arg�manlar� tip-de�i�kenlerinde al�koyal�m...
        kontrol�r = kontrol�r�m;
        birimler = birimlerim;
        ba�l�k = ba�l���m;
        s�rg�Modeli = modelim;

        // Say� bi�imini ve metin sat�r�n� yaratal�m...
        say�Bi�imi = NumberFormat.getNumberInstance();
        say�Bi�imi.setMaximumFractionDigits (2);
        NumberFormatter bi�imleyici = new NumberFormatter(say�Bi�imi);
        bi�imleyici.setAllowsInvalid (false);
        bi�imleyici.setCommitsOnValidEdit (true); // Zaten ge�ersiz veri bu metodun anl�k kontrol�yle girilemiyor, bipliyor!..
        metinSat�r� = new JFormattedTextField (bi�imleyici);
        metinSat�r�.setColumns (10);
        metinSat�r�.setValue (new Double (s�rg�Modeli.getDoubleValue()));
        metinSat�r�.addPropertyChangeListener (this);

        // A��l�r kutuyu yarat�p, birimlerini atay�p dinleyiciye ekleyelim...
        birimSe�ici = new JComboBox();
        for (int i = 0; i < birimler.length; i++) birimSe�ici.addItem (birimler[i].izah);
        birimSe�ici.setSelectedIndex (1); // �lk anda metre ve fit g�r�ns�n...
        s�rg�Modeli.setMultiplier (birimler[1].�arpan);
        birimSe�ici.addActionListener (this);

        // S�rg�y� yarat�p dinleyicisine ba�layal�m...
        s�rg� = new JSlider (s�rg�Modeli);
        s�rg�Modeli.addChangeListener (this);

        // Metin sat�r� ve s�rg�y� bir panelde gruplay�p, ebatlay�p, kutulay�p, rasgele renklendirip ekleyelim...
        JPanel de�erlerPaneli = new JPanel() {
            public Dimension getMinimumSize() {return getPreferredSize();}
            public Dimension getPreferredSize() {return new Dimension (150, super.getPreferredSize().height);}
            public Dimension getMaximumSize() {return getPreferredSize();}
        }; // JP.. ifadesi sonu...
        de�erlerPaneli.setLayout (new BoxLayout (de�erlerPaneli, BoxLayout.PAGE_AXIS));
        if (�OKRENKL�) {de�erlerPaneli.setOpaque (true);
            de�erlerPaneli.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if karar� sonu...
        de�erlerPaneli.setBorder (BorderFactory.createEmptyBorder (0,0,0,5));
        de�erlerPaneli.add (metinSat�r�);
        de�erlerPaneli.add (s�rg�);

        // Di�er bir panele de birimSe�ici'yi de�erlerPaneli ebatlar�yla uyumlu yarat�p, tesad�fi renklendirip, ekleyelim...
        JPanel birimlerPaneli = new JPanel();
        birimlerPaneli.setLayout (new BoxLayout (birimlerPaneli, BoxLayout.PAGE_AXIS));
        if (�OKRENKL�) {birimlerPaneli.setOpaque (true);
            birimlerPaneli.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if karar� sonu...
        birimlerPaneli.add (birimSe�ici);
        birimlerPaneli.add (Box.createHorizontalStrut (100));

        // Heriki paneli de i�erik panosuna ekleyelim...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));
        add (de�erlerPaneli);
        add (birimlerPaneli);
        de�erlerPaneli.setAlignmentY (TOP_ALIGNMENT);
        birimlerPaneli.setAlignmentY (TOP_ALIGNMENT);
    } // J5c_9x1() kurucusu sonu...

    // Bu panelin tercih edilen ebattan b�y�k olmas�na izin vermeyelim...
    public Dimension getMaximumSize() {
        return new Dimension (Integer.MAX_VALUE,getPreferredSize().height);
    } // getMaximumSize() metodu sonu...

    public double getMultiplier() {return s�rg�Modeli.getMultiplier();}
    public double getValue() {return s�rg�Modeli.getDoubleValue();}

    // Metin sat�r�n�n s�rg� de�i�en de�erleriyle uyumunu sa�layal�m...
    public void stateChanged (ChangeEvent olay) {
        int asgari = s�rg�Modeli.getMinimum();
        int azami = s�rg�Modeli.getMaximum();
        double de�er = s�rg�Modeli.getDoubleValue();
        NumberFormatter bi�imleyici = (NumberFormatter)metinSat�r�.getFormatter();

        bi�imleyici.setMinimum (new Double (asgari));
        bi�imleyici.setMaximum (new Double (azami));
        metinSat�r�.setValue (new Double (de�er));
    } // stateChanged (..) metodu sonu...

    /**
     * Responds to the user choosing a new unit from the combo box.
     */
    public void actionPerformed (ActionEvent olay) {
        // A��l�r kutu birimi de�i�ti�inde s�rg�lerin de�erleri yeni �arpanla g�ncellensin...
        int i = birimSe�ici.getSelectedIndex();
        s�rg�Modeli.setMultiplier (birimler[i].�arpan);
        kontrol�r.azamiDe�erleriKoy (false);
    } // actionPerformed(..) metodu sonu...

    // Metin sat�r� de�er de�i�mesine duyarl�d�r...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("value".equals (olay.getPropertyName())) {
            Number de�er = (Number)olay.getNewValue();
            s�rg�Modeli.setDoubleValue (de�er.doubleValue());
        } // if karar� sonu...
    } // propertyChange(..) metodu sonu...
} // J5c_9x1 s�n�f� sonu...