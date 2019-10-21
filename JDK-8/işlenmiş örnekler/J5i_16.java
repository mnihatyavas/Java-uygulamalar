// J5i_16.java: TreeExpandEventDemo2 (AðaçGeniþlemeOlayýGösterisi2) örneði.

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Insets;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.ExpandVetoException;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeWillExpandListener;

public class J5i_16 extends JPanel {
    GösteriAlaný aðaçGösterisiAlaný;
    JTextArea metinAlaný;
    final static String yeniSatýr = "\n";

    public J5i_16() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ýzgaraÇantaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 1.0;

        sýnýrlayýcýlar.insets = new Insets (5,5,5,5);
        aðaçGösterisiAlaný = new GösteriAlaný(); // Kurucusunu çaðýrýr...
        ýzgaraÇantaSerilim.setConstraints (aðaçGösterisiAlaný, sýnýrlayýcýlar);
        add (aðaçGösterisiAlaný);

        sýnýrlayýcýlar.insets = new Insets (1,1,1,1);
        metinAlaný = new JTextArea();
        metinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        metinAlaný.setForeground (Color.YELLOW);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane kaydýraç = new JScrollPane (metinAlaný);
        kaydýraç.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kaydýraç.setPreferredSize (new Dimension (200, 75));
        ýzgaraÇantaSerilim.setConstraints (kaydýraç, sýnýrlayýcýlar);
        add (kaydýraç);

        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_16() kurucusu sonu...

    class GösteriAlaný extends JScrollPane
            implements TreeExpansionListener, TreeWillExpandListener {
        Dimension asgariBoyut = new Dimension (100, 100);
        JTree aðaç;
        Object[] geniþleyecekSeçenekleri = {"Geniþletmeyi Ýptal et", "Geniþlet"};
        String geniþleyecekMetni = "Bir gövde dalý geniþlemek üzere.\n"
                + "Önleyeceksen \"Geniþletmeyi Ýptal Et\"i týklayýn.";
        String geniþleyecekBaþlýðý = "Aðaç Geniþleyecek";

        public GösteriAlaný() {// Kurucu...
            TreeNode kökBoðumu = dallarýYarat();
            aðaç = new JTree (kökBoðumu);
            aðaç.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            aðaç.addTreeExpansionListener (this); // Aðaç 2 ayrý dinleyiciye duyarlý...
            aðaç.addTreeWillExpandListener (this);

            setViewportView (aðaç);
        } // GösteriAlaný() kurucusu sonu...

        private TreeNode dallarýYarat() {
            DefaultMutableTreeNode kök;
            DefaultMutableTreeNode büyükEbeveyn;
            DefaultMutableTreeNode ebeveyn;
            DefaultMutableTreeNode çocuk;

            kök = new DefaultMutableTreeNode ("San Francisco");

            büyükEbeveyn = new DefaultMutableTreeNode ("Potrero Tepesi");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Tayland Barbeküsü");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Keçi Tepesi Pizzasý");
            ebeveyn.add (çocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("Süper Marketler");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Müreffeh Yaþam Marketi");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Helal Alýþveriþ");
            ebeveyn.add (çocuk);

            büyükEbeveyn = new DefaultMutableTreeNode ("Noe Vadisi");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Hamano Suþisi");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Hahn'ýn Hibaçisi");
            ebeveyn.add (çocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("Süper Marketler");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Hakiki Gýdalar");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Cýngýraklý Market");
            ebeveyn.add (çocuk);

            büyükEbeveyn = new DefaultMutableTreeNode ("Çýrmýðtý Vadisi");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Yelköprü Çeþnisi");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Uzundaþ Düþüsü Mangalý");
            ebeveyn.add (çocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("Süper Marketler");
            büyükEbeveyn.add (ebeveyn);
            çocuk = new DefaultMutableTreeNode ("Yeþilyurt BÝM");
            ebeveyn.add (çocuk);
            çocuk = new DefaultMutableTreeNode ("Banazý Gadiði A101");
            ebeveyn.add (çocuk);

            return kök;
        } // dallarýYarat() metodu sonu...

        public Dimension getMinimumSize() {return asgariBoyut;}
        public Dimension getPreferredSize() {return asgariBoyut;}

        // Sonraki hazýr metodlar TreeWillExpandListener dinleyicisinindir...
        public void treeWillExpand (TreeExpansionEvent olay) throws ExpandVetoException {
            mesaj ("Aðaç-Geniþleyecek olayý saptandý", olay);
            int n = JOptionPane.showOptionDialog (this,
                    geniþleyecekMetni,
                    geniþleyecekBaþlýðý,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    geniþleyecekSeçenekleri,
                    geniþleyecekSeçenekleri[1]);
            if (n == 0) {// Geniþleme iptali var...
                mesaj ("Aðacý geniþletme iptal edildi", olay);
                throw new ExpandVetoException (olay);
            } // if kararý sonu...
        } // treeWillExpand(..) hazýr metodu sonu...

        public void treeWillCollapse (TreeExpansionEvent olay) {mesaj ("Aðaç-Daraltýlacak olayý saptandý", olay);}
        public void treeExpanded (TreeExpansionEvent olay) {mesaj ("Aðaç-Geniþletilecek olayý saptandý", olay);}
        public void treeCollapsed (TreeExpansionEvent olay) {mesaj ("Aðaç-Daraltýldý olayý saptandý", olay);}
    } // GösteriAlaný sýnýfý sonu...

    void mesaj (String dizge, TreeExpansionEvent olay) {
        metinAlaný.append (dizge + "; adres = " + olay.getPath() + yeniSatýr);
    } // mesaj(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Aðaç Geniþleme Olayý Gösterisi 2");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_16(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_16 sýnýfý sonu...