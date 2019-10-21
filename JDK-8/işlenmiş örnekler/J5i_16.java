// J5i_16.java: TreeExpandEventDemo2 (A�a�Geni�lemeOlay�G�sterisi2) �rne�i.

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
    G�steriAlan� a�a�G�sterisiAlan�;
    JTextArea metinAlan�;
    final static String yeniSat�r = "\n";

    public J5i_16() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout �zgara�antaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 1.0;

        s�n�rlay�c�lar.insets = new Insets (5,5,5,5);
        a�a�G�sterisiAlan� = new G�steriAlan�(); // Kurucusunu �a��r�r...
        �zgara�antaSerilim.setConstraints (a�a�G�sterisiAlan�, s�n�rlay�c�lar);
        add (a�a�G�sterisiAlan�);

        s�n�rlay�c�lar.insets = new Insets (1,1,1,1);
        metinAlan� = new JTextArea();
        metinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        metinAlan�.setForeground (Color.YELLOW);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�ra� = new JScrollPane (metinAlan�);
        kayd�ra�.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        kayd�ra�.setPreferredSize (new Dimension (200, 75));
        �zgara�antaSerilim.setConstraints (kayd�ra�, s�n�rlay�c�lar);
        add (kayd�ra�);

        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_16() kurucusu sonu...

    class G�steriAlan� extends JScrollPane
            implements TreeExpansionListener, TreeWillExpandListener {
        Dimension asgariBoyut = new Dimension (100, 100);
        JTree a�a�;
        Object[] geni�leyecekSe�enekleri = {"Geni�letmeyi �ptal et", "Geni�let"};
        String geni�leyecekMetni = "Bir g�vde dal� geni�lemek �zere.\n"
                + "�nleyeceksen \"Geni�letmeyi �ptal Et\"i t�klay�n.";
        String geni�leyecekBa�l��� = "A�a� Geni�leyecek";

        public G�steriAlan�() {// Kurucu...
            TreeNode k�kBo�umu = dallar�Yarat();
            a�a� = new JTree (k�kBo�umu);
            a�a�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            a�a�.addTreeExpansionListener (this); // A�a� 2 ayr� dinleyiciye duyarl�...
            a�a�.addTreeWillExpandListener (this);

            setViewportView (a�a�);
        } // G�steriAlan�() kurucusu sonu...

        private TreeNode dallar�Yarat() {
            DefaultMutableTreeNode k�k;
            DefaultMutableTreeNode b�y�kEbeveyn;
            DefaultMutableTreeNode ebeveyn;
            DefaultMutableTreeNode �ocuk;

            k�k = new DefaultMutableTreeNode ("San Francisco");

            b�y�kEbeveyn = new DefaultMutableTreeNode ("Potrero Tepesi");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("Tayland Barbek�s�");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("Ke�i Tepesi Pizzas�");
            ebeveyn.add (�ocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("S�per Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("M�reffeh Ya�am Marketi");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("Helal Al��veri�");
            ebeveyn.add (�ocuk);

            b�y�kEbeveyn = new DefaultMutableTreeNode ("Noe Vadisi");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("Hamano Su�isi");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("Hahn'�n Hiba�isi");
            ebeveyn.add (�ocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("S�per Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("Hakiki G�dalar");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("C�ng�rakl� Market");
            ebeveyn.add (�ocuk);

            b�y�kEbeveyn = new DefaultMutableTreeNode ("��rm��t� Vadisi");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("Yelk�pr� �e�nisi");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("Uzunda� D���s� Mangal�");
            ebeveyn.add (�ocuk);
            //
            ebeveyn = new DefaultMutableTreeNode ("S�per Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            �ocuk = new DefaultMutableTreeNode ("Ye�ilyurt B�M");
            ebeveyn.add (�ocuk);
            �ocuk = new DefaultMutableTreeNode ("Banaz� Gadi�i A101");
            ebeveyn.add (�ocuk);

            return k�k;
        } // dallar�Yarat() metodu sonu...

        public Dimension getMinimumSize() {return asgariBoyut;}
        public Dimension getPreferredSize() {return asgariBoyut;}

        // Sonraki haz�r metodlar TreeWillExpandListener dinleyicisinindir...
        public void treeWillExpand (TreeExpansionEvent olay) throws ExpandVetoException {
            mesaj ("A�a�-Geni�leyecek olay� saptand�", olay);
            int n = JOptionPane.showOptionDialog (this,
                    geni�leyecekMetni,
                    geni�leyecekBa�l���,
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    geni�leyecekSe�enekleri,
                    geni�leyecekSe�enekleri[1]);
            if (n == 0) {// Geni�leme iptali var...
                mesaj ("A�ac� geni�letme iptal edildi", olay);
                throw new ExpandVetoException (olay);
            } // if karar� sonu...
        } // treeWillExpand(..) haz�r metodu sonu...

        public void treeWillCollapse (TreeExpansionEvent olay) {mesaj ("A�a�-Daralt�lacak olay� saptand�", olay);}
        public void treeExpanded (TreeExpansionEvent olay) {mesaj ("A�a�-Geni�letilecek olay� saptand�", olay);}
        public void treeCollapsed (TreeExpansionEvent olay) {mesaj ("A�a�-Daralt�ld� olay� saptand�", olay);}
    } // G�steriAlan� s�n�f� sonu...

    void mesaj (String dizge, TreeExpansionEvent olay) {
        metinAlan�.append (dizge + "; adres = " + olay.getPath() + yeniSat�r);
    } // mesaj(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A�a� Geni�leme Olay� G�sterisi 2");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_16(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_16 s�n�f� sonu...