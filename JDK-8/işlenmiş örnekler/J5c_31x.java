// J5c_31x.java: ListDialog (ListeDiyalo�u) alt-�rne�i.

import java.awt.Component;
import java.awt.Frame;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.BorderLayout;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.SwingConstants;
import javax.swing.*; // import javax.swing.?.ListSelectionModel; bilemedim...

public class J5c_31x extends JDialog implements ActionListener {
    private static J5c_31x diyalog;
    private static String de�er = "";
    private JList liste;

    // �lk parametre ana �er�eve, ikincisi null'sa diyalo�un sol �st k��esi ekran�n tam ortas�,
    // bir komponentse onunla ba�lant�l� olacakt�r...
    public static String diyalo�uG�ster (// Parametreler...
            Component pencere,
            Component konum,
            String etiket�stMetni,
            String ba�l�k,
            String[] t�mAdlar,
            String ilkAd,
            String uzunAd) {
        Frame �er�eve = JOptionPane.getFrameForComponent (pencere);
        diyalog = new J5c_31x (// Bu parametrelerle kurucuyu �a��r�r...
                �er�eve,
                konum,
                etiket�stMetni,
                ba�l�k,
                t�mAdlar,
                ilkAd,
                uzunAd);
        diyalog.setVisible (true);

        return de�er;
    } // diyalo�uG�ster(..) metodu sonu...

    private J5c_31x (// Kurucu'nun parametreleri...
            Frame �er�eve,
            Component konum,
            String etiket�stMetni,
            String ba�l�k,
            Object[] adVerileri,
            String ilkAd,
            String uzunAd) {// Kurucu...
        super (�er�eve, ba�l�k, true);

        // 2 adet (�ptal ve Kur) d��me yarat ve kural�m...
        JButton iptalD��mesi = new JButton ("�ptal");
        iptalD��mesi.addActionListener (this);
        //
        final JButton kurD��mesi = new JButton("Set");
        kurD��mesi.setActionCommand ("Kur");
        kurD��mesi.addActionListener (this);
        getRootPane().setDefaultButton (kurD��mesi);

        // Diyalo�un bebek adlar� listeleme diyalo�u...
        liste = new JList (adVerileri) {
            // JScrollPane yerine ScrollableUnitIncrement kullan�lacak...
            public int getScrollableUnitIncrement (//Parametreler...
                    Rectangle dikd�rtgen,
                    int dikeyVeyaYatay,
                    int y�n) {
                int sat�r;
                if (dikeyVeyaYatay == SwingConstants.VERTICAL && y�n < 0 && (sat�r = getFirstVisibleIndex()) != -1) {
                    Rectangle kutu = getCellBounds (sat�r, sat�r);
                    if ((kutu.y == dikd�rtgen.y) && (sat�r != 0))  {
                        Point nokta = kutu.getLocation();
                        nokta.y--;
                        int �ncekiEndeks = locationToIndex (nokta);
                        Rectangle �ncekiKutu = getCellBounds (�ncekiEndeks, �ncekiEndeks);

                        if (�ncekiKutu == null || �ncekiKutu.y >= kutu.y) return 0;

                        return �ncekiKutu.height;
                    } // i�-if karar� sonu...
                } // d��-if karar� sonu...

                return super.getScrollableUnitIncrement (dikd�rtgen, dikeyVeyaYatay, y�n);
            } // getScrollableUnitIncremen(..) haz�r metodu sonu...
        }; // liste=.. ifadesi sonu...

        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (uzunAd != null) liste.setPrototypeCellValue (uzunAd); // Fazlal�k bo�luklar� at...
        liste.setLayoutOrientation (JList.HORIZONTAL_WRAP);
        liste.setVisibleRowCount (-1);
        liste.addMouseListener (new MouseAdapter() {
            public void mouseClicked (MouseEvent olay) {
                if (olay.getClickCount() == 2) kurD��mesi.doClick(); // se�ilen ve kur d��mesi t�kland� say�ls�n...
            } // mouseClicked(..) haz�r metodu sonu...
        }); // liste.add.. ifadesi sonu...

        JScrollPane kayd�rmaPanosu = new JScrollPane (liste);
        kayd�rmaPanosu.setPreferredSize (new Dimension (250, 80));
        kayd�rmaPanosu.setAlignmentX (LEFT_ALIGNMENT);

        // Etiket �st metnini, kayd�rmal� listeyi ve 2 d��meyi yerle�tirelim...
        JPanel listePaneli = new JPanel();
        listePaneli.setLayout (new BoxLayout (listePaneli, BoxLayout.PAGE_AXIS));
        JLabel etiket = new JLabel (etiket�stMetni);
        etiket.setLabelFor (liste);
        listePaneli.add (etiket);
        listePaneli.add (Box.createRigidArea (new Dimension (0,5)));
        listePaneli.add (kayd�rmaPanosu);
        listePaneli.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        listePaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        //
        JPanel d��mePaneli = new JPanel();
        d��mePaneli.setLayout (new BoxLayout (d��mePaneli, BoxLayout.LINE_AXIS));
        d��mePaneli.setBorder (BorderFactory.createEmptyBorder (0, 10, 10, 10));
        d��mePaneli.add (Box.createHorizontalGlue());
        d��mePaneli.add (iptalD��mesi);
        d��mePaneli.add (Box.createRigidArea (new Dimension (10, 0)));
        d��mePaneli.add (kurD��mesi);
        d��mePaneli.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        // BorderLayout'l� i�erik panosuna son 2 (liste ve d��me) paneli ekleyelim...
        Container konteyn�r = getContentPane();
        konteyn�r.add (listePaneli, BorderLayout.CENTER);
        konteyn�r.add (d��mePaneli, BorderLayout.PAGE_END);

        // D�nmeden ba�lang�� de�erlerini tekrar koyal�m...
        de�eriKoy (ilkAd);
        pack();
        setLocationRelativeTo (konum);
    } // private J5c_31x(..) kurucusu sonu...

    private void de�eriKoy (String de�er) {
        this.de�er = de�er;
        liste.setSelectedValue (de�er, true);
    } // de�eriKoy(..) metodu sonu...

    // �ptal ve Kur (geri d�n��) d��meleri t�klamalar�n� y�netelim...
    public void actionPerformed (ActionEvent olay) {
        if ("Kur".equals (olay.getActionCommand())) J5c_31x.de�er = (String)(liste.getSelectedValue());
        J5c_31x.diyalog.setVisible (false);
    } // actionPerformed(..) haz�r metodu sonu...
} // J5c_31x s�n�f� sonu...