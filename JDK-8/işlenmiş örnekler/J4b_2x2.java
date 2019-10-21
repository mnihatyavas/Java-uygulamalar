// J4b_2x2.java: DynamicTree (DinamikA�a�) alt-�rne�i.

import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

public class J4b_2x2 extends JPanel {
    protected DefaultMutableTreeNode k�kDal�;
    protected DefaultTreeModel a�a�Modeli;
    protected JTree a�a�;
    private Toolkit bipleyici = Toolkit.getDefaultToolkit();

    public J4b_2x2() {
        super (new GridLayout (1,0));

        k�kDal� = new DefaultMutableTreeNode ("K�k-Dal�");
        a�a�Modeli = new DefaultTreeModel (k�kDal�);

        a�a� = new JTree (a�a�Modeli);
        a�a�.setEditable (true);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        a�a�.setShowsRootHandles (true);

        JScrollPane kayd�rmaPanosu = new JScrollPane (a�a�);
        add (kayd�rmaPanosu); // a�a� mevcut pencereyi ta�arsa kayd�rmaPanosu tebarruz eder...
    } // J4b_2x2() kurucusu sonu...

    // K�k-Dal� hari� a�ac�n t�m dallar� temizlenecek...
    public void temizle() {
        k�kDal�.removeAllChildren();
        a�a�Modeli.reload();
    } // temizle() metodu sonu...

    // Sadece se�ili dal silinecek...
    public void se�iliDal�Sil() {
        TreePath se�ilen = a�a�.getSelectionPath();
        if (se�ilen != null) {
            DefaultMutableTreeNode �imdikiDal = (DefaultMutableTreeNode)(se�ilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(�imdikiDal.getParent());
            if (ebeveyn != null) {// K�k dal'sa silmez...
                a�a�Modeli.removeNodeFromParent (�imdikiDal);
                return;
            } // if-ebe.. karar� sonu...
        } // if-se�.. karar� sonu...

        // Se�ilen yoksa veya k�k dal se�ilmi�se...
        bipleyici.beep();
    } // se�iliDal�Sil() metodu sonu...

    // Se�ili dala �ocuk eklenecek...
    public DefaultMutableTreeNode nesneEkle (Object �ocuk) {
        DefaultMutableTreeNode ebeveynDal� = null;
        TreePath ebeveynYolu = a�a�.getSelectionPath();

        if (ebeveynYolu == null) ebeveynDal� = k�kDal�;
        else ebeveynDal� = (DefaultMutableTreeNode)(ebeveynYolu.getLastPathComponent());

        return nesneEkle (ebeveynDal�, �ocuk, true);
    } // nesneEkle(..1) metodu sonu...

    public DefaultMutableTreeNode nesneEkle (
            DefaultMutableTreeNode ebeveyn,
            Object �ocuk) {
        return nesneEkle (ebeveyn, �ocuk, false);
    } // nesneEkle(..2) metodu sonu...

    public DefaultMutableTreeNode nesneEkle (
            DefaultMutableTreeNode ebeveyn,
            Object �ocuk, 
            boolean g�r�nmeliMi) {
        DefaultMutableTreeNode �ocukDal� = new DefaultMutableTreeNode (�ocuk);

        if (ebeveyn == null) ebeveyn = k�kDal�;

        // Bunun TreeModel �zerinde y�r�t�lmesi anahtard�r, DefaultMutableTreeNode �zerinde DE��L...
        a�a�Modeli.insertNodeInto (�ocukDal�, ebeveyn, ebeveyn.getChildCount());

        // Ta�m��sa, kullan�c� bu sevimli yeni �ocuk dal�n� g�rebilmeli...
        if (g�r�nmeliMi) a�a�.scrollPathToVisible (new TreePath (�ocukDal�.getPath()));

        return �ocukDal�;
    } // nesneEkle(..3) metodu sonu...

    class A�a�ModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            /*
             * E�er olay �ocuklar� listeliyorsa, de�i�en dal elimizdeki dal�n �ocu�udur.
             * Listelemiyorsa, de�i�en ve se�ilen dal ayn�d�r....
             */

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullan�c� dal� de�i�tirmeyi tamamlad�.");
            System.out.println ("Yeni de�er: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved(TreeModelEvent olay) {}
        public void treeStructureChanged(TreeModelEvent olay) {}
    } // A�a�ModeliDinleyicim s�n�f� sonu...
} // J4b_2x2 s�n�f� sonu...