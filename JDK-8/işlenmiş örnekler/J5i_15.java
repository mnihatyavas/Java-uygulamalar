// J5i_15.java: TreeExpandEventDemo (A�a�Geni�lemeOlay�G�sterisi) �rne�i.

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
    G�steriAlan� a�a�G�steriAlan�;
    JTextArea metinAlan�;
    final static String yeniSat�r = "\n";

    public J5i_15() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout �zgara�antaSerilim = (GridBagLayout)getLayout();
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH;
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER;
        s�n�rlay�c�lar.weightx = 1.0;
        s�n�rlay�c�lar.weighty = 1.0;

        s�n�rlay�c�lar.insets = new Insets (5,5,5,5);
        a�a�G�steriAlan� = new G�steriAlan�(); // S�n�f kurucusunu �a��r�r...
        �zgara�antaSerilim.setConstraints (a�a�G�steriAlan�, s�n�rlay�c�lar);
        add (a�a�G�steriAlan�);

        s�n�rlay�c�lar.insets = new Insets (1,1,1,1);
        metinAlan� = new JTextArea();
        metinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.55f) );
        metinAlan�.setForeground (Color.YELLOW);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane d�k�mKayd�rac� = new JScrollPane (metinAlan�);
        d�k�mKayd�rac�.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        d�k�mKayd�rac�.setPreferredSize (new Dimension (200, 75));
        �zgara�antaSerilim.setConstraints (d�k�mKayd�rac�, s�n�rlay�c�lar);
        add (d�k�mKayd�rac�);

        setPreferredSize (new Dimension (450, 450));
        setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
    } // J5i_15() kurucusu sonu...

    class G�steriAlan� extends JScrollPane implements TreeExpansionListener {
        // extends JScrollPane oldu�undan, ayr�ca a�a�'l� kurmaya gerek duyulmam��...
        Dimension asgariEbat = new Dimension (100, 100);
        JTree a�a�;
    
        public G�steriAlan�() {// Kurucu...
            TreeNode k�kBo�umu = dallar�Yarat();
            a�a� = new JTree (k�kBo�umu);
            a�a�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 1.0f) );
            a�a�.addTreeExpansionListener (this); // Geni�leme-daralmaya duyarlayal�m...
            setViewportView (a�a�);
        } // G�steriAlan�() kurucusu sonu...

        private TreeNode dallar�Yarat() {
            DefaultMutableTreeNode k�k;
            DefaultMutableTreeNode b�y�kEbeveyn;
            DefaultMutableTreeNode ebeveyn;
            DefaultMutableTreeNode yavru;

            k�k = new DefaultMutableTreeNode ("San Francisco");

            b�y�kEbeveyn = new DefaultMutableTreeNode ("Potrero Tepesi");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Thai Barbek�");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Ke�i Tepesi Pizzas�");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Refah Ya�am Marketi");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("G�venli Al��veri�");
            ebeveyn.add (yavru);

            b�y�kEbeveyn = new DefaultMutableTreeNode ("Noe Vadisi");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Hamano Su�isi");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Hahn'�n Hiba�isi");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Helal G�dalar");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("Zilli  Market");
            ebeveyn.add (yavru);

            b�y�kEbeveyn = new DefaultMutableTreeNode ("Malatya Ye�ilyurt");
            k�k.add (b�y�kEbeveyn);
            //
            ebeveyn = new DefaultMutableTreeNode ("Lokantalar");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Yelk�pr� �e�nisi");
            ebeveyn.add(yavru);
            yavru = new DefaultMutableTreeNode ("Uzunta�-D��� Mangal�");
            ebeveyn.add (yavru);
            //
            ebeveyn = new DefaultMutableTreeNode ("Marketler");
            b�y�kEbeveyn.add (ebeveyn);
            yavru = new DefaultMutableTreeNode ("Ye�ilyurt B�M");
            ebeveyn.add (yavru);
            yavru = new DefaultMutableTreeNode ("��rm��t� A101");
            ebeveyn.add (yavru);

            return k�k;
        } // dallar�Yarat() metodu sonu...

        // Haz�r metodlar...
        public Dimension getMinimumSize() {return asgariEbat;}
        public Dimension getPreferredSize() {return asgariEbat;}
        public void treeExpanded (TreeExpansionEvent olay) {d�k�m ("A�a�-geni�leme olay� saptand�", olay);}
        public void treeCollapsed (TreeExpansionEvent olay) {d�k�m ("A�a�-daralma olay� saptand�", olay);}
    } // G�steriAlan� s�n�f� sonu...

    void d�k�m (String msj, TreeExpansionEvent olay) {
        metinAlan�.append (msj + "; " + "adres = " + olay.getPath() + yeniSat�r);
    } // d�k�m(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("A�a� Geni�leme Olay� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_15(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,50);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_15 s�n�f� sonu...