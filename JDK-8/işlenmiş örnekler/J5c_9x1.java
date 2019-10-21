// J5c_9x1.java: ConversionPanel (DeðiþimPaneli) alt-örneði.

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
    JFormattedTextField metinSatýrý;
    JComboBox birimSeçici;
    JSlider sürgü;
    J5c_9x3 sürgüModeli;
    J5c_9 kontrolör;
    J5c_9x2[] birimler;
    String baþlýk;
    NumberFormat sayýBiçimi;

    final static boolean ÇOKRENKLÝ = true;
    final static int AZAMÝ = 10000;

    J5c_9x1 (J5c_9 kontrolörüm, String baþlýðým, J5c_9x2[] birimlerim, J5c_9x3 modelim) {
        if (ÇOKRENKLÝ) {setOpaque (true);
            setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if kararý sonu...
        setBorder (BorderFactory.createCompoundBorder (
                BorderFactory.createTitledBorder (baþlýðým),
                BorderFactory.createEmptyBorder (5,5,5,5)));

        // Argümanlarý tip-deðiþkenlerinde alýkoyalým...
        kontrolör = kontrolörüm;
        birimler = birimlerim;
        baþlýk = baþlýðým;
        sürgüModeli = modelim;

        // Sayý biçimini ve metin satýrýný yaratalým...
        sayýBiçimi = NumberFormat.getNumberInstance();
        sayýBiçimi.setMaximumFractionDigits (2);
        NumberFormatter biçimleyici = new NumberFormatter(sayýBiçimi);
        biçimleyici.setAllowsInvalid (false);
        biçimleyici.setCommitsOnValidEdit (true); // Zaten geçersiz veri bu metodun anlýk kontrolüyle girilemiyor, bipliyor!..
        metinSatýrý = new JFormattedTextField (biçimleyici);
        metinSatýrý.setColumns (10);
        metinSatýrý.setValue (new Double (sürgüModeli.getDoubleValue()));
        metinSatýrý.addPropertyChangeListener (this);

        // Açýlýr kutuyu yaratýp, birimlerini atayýp dinleyiciye ekleyelim...
        birimSeçici = new JComboBox();
        for (int i = 0; i < birimler.length; i++) birimSeçici.addItem (birimler[i].izah);
        birimSeçici.setSelectedIndex (1); // Ýlk anda metre ve fit görünsün...
        sürgüModeli.setMultiplier (birimler[1].çarpan);
        birimSeçici.addActionListener (this);

        // Sürgüyü yaratýp dinleyicisine baðlayalým...
        sürgü = new JSlider (sürgüModeli);
        sürgüModeli.addChangeListener (this);

        // Metin satýrý ve sürgüyü bir panelde gruplayýp, ebatlayýp, kutulayýp, rasgele renklendirip ekleyelim...
        JPanel deðerlerPaneli = new JPanel() {
            public Dimension getMinimumSize() {return getPreferredSize();}
            public Dimension getPreferredSize() {return new Dimension (150, super.getPreferredSize().height);}
            public Dimension getMaximumSize() {return getPreferredSize();}
        }; // JP.. ifadesi sonu...
        deðerlerPaneli.setLayout (new BoxLayout (deðerlerPaneli, BoxLayout.PAGE_AXIS));
        if (ÇOKRENKLÝ) {deðerlerPaneli.setOpaque (true);
            deðerlerPaneli.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if kararý sonu...
        deðerlerPaneli.setBorder (BorderFactory.createEmptyBorder (0,0,0,5));
        deðerlerPaneli.add (metinSatýrý);
        deðerlerPaneli.add (sürgü);

        // Diðer bir panele de birimSeçici'yi deðerlerPaneli ebatlarýyla uyumlu yaratýp, tesadüfi renklendirip, ekleyelim...
        JPanel birimlerPaneli = new JPanel();
        birimlerPaneli.setLayout (new BoxLayout (birimlerPaneli, BoxLayout.PAGE_AXIS));
        if (ÇOKRENKLÝ) {birimlerPaneli.setOpaque (true);
            birimlerPaneli.setBackground (new Color ( (int)(Math.random() * 255), (int)(Math.random() * 255), (int)(Math.random() * 255) ));
        } // if kararý sonu...
        birimlerPaneli.add (birimSeçici);
        birimlerPaneli.add (Box.createHorizontalStrut (100));

        // Heriki paneli de içerik panosuna ekleyelim...
        setLayout (new BoxLayout (this, BoxLayout.LINE_AXIS));
        add (deðerlerPaneli);
        add (birimlerPaneli);
        deðerlerPaneli.setAlignmentY (TOP_ALIGNMENT);
        birimlerPaneli.setAlignmentY (TOP_ALIGNMENT);
    } // J5c_9x1() kurucusu sonu...

    // Bu panelin tercih edilen ebattan büyük olmasýna izin vermeyelim...
    public Dimension getMaximumSize() {
        return new Dimension (Integer.MAX_VALUE,getPreferredSize().height);
    } // getMaximumSize() metodu sonu...

    public double getMultiplier() {return sürgüModeli.getMultiplier();}
    public double getValue() {return sürgüModeli.getDoubleValue();}

    // Metin satýrýnýn sürgü deðiþen deðerleriyle uyumunu saðlayalým...
    public void stateChanged (ChangeEvent olay) {
        int asgari = sürgüModeli.getMinimum();
        int azami = sürgüModeli.getMaximum();
        double deðer = sürgüModeli.getDoubleValue();
        NumberFormatter biçimleyici = (NumberFormatter)metinSatýrý.getFormatter();

        biçimleyici.setMinimum (new Double (asgari));
        biçimleyici.setMaximum (new Double (azami));
        metinSatýrý.setValue (new Double (deðer));
    } // stateChanged (..) metodu sonu...

    /**
     * Responds to the user choosing a new unit from the combo box.
     */
    public void actionPerformed (ActionEvent olay) {
        // Açýlýr kutu birimi deðiþtiðinde sürgülerin deðerleri yeni çarpanla güncellensin...
        int i = birimSeçici.getSelectedIndex();
        sürgüModeli.setMultiplier (birimler[i].çarpan);
        kontrolör.azamiDeðerleriKoy (false);
    } // actionPerformed(..) metodu sonu...

    // Metin satýrý deðer deðiþmesine duyarlýdýr...
    public void propertyChange (PropertyChangeEvent olay) {
        if ("value".equals (olay.getPropertyName())) {
            Number deðer = (Number)olay.getNewValue();
            sürgüModeli.setDoubleValue (deðer.doubleValue());
        } // if kararý sonu...
    } // propertyChange(..) metodu sonu...
} // J5c_9x1 sýnýfý sonu...