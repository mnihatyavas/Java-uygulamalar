// J5i_15.java: TreeExpandEventDemo (AðaçGeniþlemeOlayýGösterisi) örneði.

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.JTree;
import javax.swing.tree.TreeNode;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.event.TreeExpansionListener;
import javax.swing.event.TreeExpansionEvent;

public class J5i_15 extends JPanel {
    GösteriAlaný aðaçGösteriAlaný;
    JTextArea metinAlaný;
    final static String yeniSatýr = "\n";

    public J5i_15() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout ýzgaraÇantaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints sýnýrlayýcýlar = new GridBagConstraints();

        sýnýrlayýcýlar.fill = GridBagConstraints.BOTH;
        sýnýrlayýcýlar.gridwidth = GridBagConstraints.REMAINDER;
        sýnýrlayýcýlar.weightx = 1.0;
        sýnýrlayýcýlar.weighty = 1.0;

        sýnýrlayýcýlar.insets = new Insets (5,5,5,5);
        aðaçGösteriAlaný = new GösteriAlaný(); // Sýnýf kurucusunu çaðýrýr...
        ýzgaraÇantaSerilim.setConstraints (aðaçGösteriAlaný, sýnýrlayýcýlar);
        add (aðaçGösteriAlaný);

        sýnýrlayýcýlar.insets = new Insets (1,1,1,1);
        metinAlaný = new JTextArea();
        metinAlaný.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        metinAlaný.setForeground (Color.YELLOW);
        metinAlaný.setEditable (false); // Müdahalesiz...
        JScrollPane dökümKaydýracý = new JScrollPane (metinAlaný);
        dökümKaydýracý.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        dökümKaydýracý.setPreferredSize (new Dimension (200, 75));
        ýzgaraÇantaSerilim.setConstraints (dökümKaydýracý, sýnýrlayýcýlar);
        add (dökümKaydýracý);

        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_15() kurucusu sonu...

    class GösteriAlaný extends JScrollPane implements TreeExpansionListener {
        // extends JScrollPane olduðundan, ayrýca aðaç'lý kurmaya gerek duyulmamýþ...
        Dimension asgariEbat = new Dimension (100, 100);
        JTree aðaç;
    
        public GösteriAlaný() {// Kurucu...
            TreeNode kökBoðumu = dallarýYarat();
            aðaç = new JTree (kökBoðumu);
            aðaç.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            aðaç.addTreeExpansionListener (this); // Geniþleme-daralmaya duyarlayalým...
            setViewportView (aðaç);
        } // GösteriAlaný() kurucusu sonu...

        private TreeNode dallarýYarat() {
            DefaultMutableTreeNode kök;
            DefaultMutableTreeNode büyükEbeveyn;
            DefaultMutableTreeNode ebeveyn;
            DefaultMutableTreeNode yavru;

            kök = new DefaultMutableTreeNode ("San Francisco");

            büyükEbeveyn = new DefaultMutableTreeNode ("Potrero Tepesi");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Thai Barbekü");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Keçi Tepesi Pizzasý");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Refah Yaþam Marketi");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Güvenli Alýþveriþ");
            ebeveyn.add (yavru);

            büyükEbeveyn = new DefaultMutableTreeNode ("Noe Vadisi");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Hamano Suþisi");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Hahn'ýn Hibaçisi");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Helal Gýdalar");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Zilli  Market");
            ebeveyn.add (yavru);

            büyükEbeveyn = new DefaultMutableTreeNode ("Malatya Yeþilyurt");
            kök.add (büyükEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Yelköprü Çeþnisi");
            ebeveyn.add(yavru);
            yavru = new DefaultMutableTreeNode ("Uzuntaþ-Düþü Mangalý");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            büyükEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Yeþilyurt BÝM");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Çýrmýðtý A101");
            ebeveyn.add (yavru);

            return kök;
        } // dallarýYarat() metodu sonu...

        // Hazýr metodlar...
        public Dimension getMinimumSize() {return asgariEbat;}
        public Dimension getPreferredSize() {return asgariEbat;}
        public void treeExpanded (TreeExpansionEvent olay) {döküm ("Aðaç-geniþleme olayý saptandý", olay);}
        public void treeCollapsed (TreeExpansionEvent olay) {döküm ("Aðaç-daralma olayý saptandý", olay);}
    } // GösteriAlaný sýnýfý sonu...

    void döküm (String msj, TreeExpansionEvent olay) {
        metinAlaný.append (msj + "; " + "adres = " + olay.getPath() + yeniSatýr);
    } // döküm(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Aðaç Geniþleme Olayý Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeniÝçerikPanosu = new J5i_15(); // Kurucuyu çaðýrýr...
        yeniÝçerikPanosu.setOpaque (true);
        çerçeve.setContentPane (yeniÝçerikPanosu);
        çerçeve.setLocation (500,50);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeGösterGUI();}});
    } // main(..) metodu sonu...
} // J5i_15 sýnýfý sonu...