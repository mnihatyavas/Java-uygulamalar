// J5c_31x.java: ListDialog (ListeDiyaloðu) alt-örneði.

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
    private static String deðer = "";
    private JList liste;

    // Ýlk parametre ana çerçeve, ikincisi null'sa diyaloðun sol üst köþesi ekranýn tam ortasý,
    // bir komponentse onunla baðlantýlý olacaktýr...
    public static String diyaloðuGöster (// Parametreler...
            Component pencere,
            Component konum,
            String etiketÜstMetni,
            String baþlýk,
            String[] tümAdlar,
            String ilkAd,
            String uzunAd) {
        Frame çerçeve = JOptionPane.getFrameForComponent (pencere);
        diyalog = new J5c_31x (// Bu parametrelerle kurucuyu çaðýrýr...
                çerçeve,
                konum,
                etiketÜstMetni,
                baþlýk,
                tümAdlar,
                ilkAd,
                uzunAd);
        diyalog.setVisible (true);

        return deðer;
    } // diyaloðuGöster(..) metodu sonu...

    private J5c_31x (// Kurucu'nun parametreleri...
            Frame çerçeve,
            Component konum,
            String etiketÜstMetni,
            String baþlýk,
            Object[] adVerileri,
            String ilkAd,
            String uzunAd) {// Kurucu...
        super (çerçeve, baþlýk, true);

        // 2 adet (Ýptal ve Kur) düðme yarat ve kuralým...
        JButton iptalDüðmesi = new JButton ("Ýptal");
        iptalDüðmesi.addActionListener (this);
        //
        final JButton kurDüðmesi = new JButton("Set");
        kurDüðmesi.setActionCommand ("Kur");
        kurDüðmesi.addActionListener (this);
        getRootPane().setDefaultButton (kurDüðmesi);

        // Diyaloðun bebek adlarý listeleme diyaloðu...
        liste = new JList (adVerileri) {
            // JScrollPane yerine ScrollableUnitIncrement kullanýlacak...
            public int getScrollableUnitIncrement (//Parametreler...
                    Rectangle dikdörtgen,
                    int dikeyVeyaYatay,
                    int yön) {
                int satýr;
                if (dikeyVeyaYatay == SwingConstants.VERTICAL && yön < 0 && (satýr = getFirstVisibleIndex()) != -1) {
                    Rectangle kutu = getCellBounds (satýr, satýr);
                    if ((kutu.y == dikdörtgen.y) && (satýr != 0))  {
                        Point nokta = kutu.getLocation();
                        nokta.y--;
                        int öncekiEndeks = locationToIndex (nokta);
                        Rectangle öncekiKutu = getCellBounds (öncekiEndeks, öncekiEndeks);

                        if (öncekiKutu == null || öncekiKutu.y >= kutu.y) return 0;

                        return öncekiKutu.height;
                    } // iç-if kararý sonu...
                } // dýþ-if kararý sonu...

                return super.getScrollableUnitIncrement (dikdörtgen, dikeyVeyaYatay, yön);
            } // getScrollableUnitIncremen(..) hazýr metodu sonu...
        }; // liste=.. ifadesi sonu...

        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        if (uzunAd != null) liste.setPrototypeCellValue (uzunAd); // Fazlalýk boþluklarý at...
        liste.setLayoutOrientation (JList.HORIZONTAL_WRAP);
        liste.setVisibleRowCount (-1);
        liste.addMouseListener (new MouseAdapter() {
            public void mouseClicked (MouseEvent olay) {
                if (olay.getClickCount() == 2) kurDüðmesi.doClick(); // seçilen ve kur düðmesi týklandý sayýlsýn...
            } // mouseClicked(..) hazýr metodu sonu...
        }); // liste.add.. ifadesi sonu...

        JScrollPane kaydýrmaPanosu = new JScrollPane (liste);
        kaydýrmaPanosu.setPreferredSize (new Dimension (250, 80));
        kaydýrmaPanosu.setAlignmentX (LEFT_ALIGNMENT);

        // Etiket üst metnini, kaydýrmalý listeyi ve 2 düðmeyi yerleþtirelim...
        JPanel listePaneli = new JPanel();
        listePaneli.setLayout (new BoxLayout (listePaneli, BoxLayout.PAGE_AXIS));
        JLabel etiket = new JLabel (etiketÜstMetni);
        etiket.setLabelFor (liste);
        listePaneli.add (etiket);
        listePaneli.add (Box.createRigidArea (new Dimension (0,5)));
        listePaneli.add (kaydýrmaPanosu);
        listePaneli.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        listePaneli.setBackground (new Color ((int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156)) );
        //
        JPanel düðmePaneli = new JPanel();
        düðmePaneli.setLayout (new BoxLayout (düðmePaneli, BoxLayout.LINE_AXIS));
        düðmePaneli.setBorder (BorderFactory.createEmptyBorder (0, 10, 10, 10));
        düðmePaneli.add (Box.createHorizontalGlue());
        düðmePaneli.add (iptalDüðmesi);
        düðmePaneli.add (Box.createRigidArea (new Dimension (10, 0)));
        düðmePaneli.add (kurDüðmesi);
        düðmePaneli.setBackground (new Color ((int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156)) );

        // BorderLayout'lý içerik panosuna son 2 (liste ve düðme) paneli ekleyelim...
        Container konteynýr = getContentPane();
        konteynýr.add (listePaneli, BorderLayout.CENTER);
        konteynýr.add (düðmePaneli, BorderLayout.PAGE_END);

        // Dönmeden baþlangýç deðerlerini tekrar koyalým...
        deðeriKoy (ilkAd);
        pack();
        setLocationRelativeTo (konum);
    } // private J5c_31x(..) kurucusu sonu...

    private void deðeriKoy (String deðer) {
        this.deðer = deðer;
        liste.setSelectedValue (deðer, true);
    } // deðeriKoy(..) metodu sonu...

    // Ýptal ve Kur (geri dönüþ) düðmeleri týklamalarýný yönetelim...
    public void actionPerformed (ActionEvent olay) {
        if ("Kur".equals (olay.getActionCommand())) J5c_31x.deðer = (String)(liste.getSelectedValue());
        J5c_31x.diyalog.setVisible (false);
    } // actionPerformed(..) hazýr metodu sonu...
} // J5c_31x sýnýfý sonu...