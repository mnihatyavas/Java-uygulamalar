// J4a_6x2.java: DynamicTree (DinamikA�a�) alt-�rne�i.

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

public class J4a_6x2 extends JPanel {
    protected DefaultMutableTreeNode k�kDal�;
    protected DefaultTreeModel a�a�Modeli;
    protected JTree a�a�;
    private Toolkit aletTak�m� = Toolkit.getDefaultToolkit();

    public J4a_6x2() {
        super (new GridLayout (1, 0));

        k�kDal� = new DefaultMutableTreeNode ("K�k Dal�");
        a�a�Modeli = new DefaultTreeModel (k�kDal�);

        a�a� = new JTree (a�a�Modeli);
        a�a�.setEditable (true);
        a�a�.getSelectionModel().setSelectionMode
            (TreeSelectionModel.SINGLE_TREE_SELECTION);
        a�a�.setShowsRootHandles (true);

        JScrollPane kayd�rmaPanosu = new JScrollPane (a�a�);
        add (kayd�rmaPanosu);
    } // J4a_6x2() kurucusu sonu...

    // Se�ili dal� silelim...
    public void se�ilenDal�Sil() {
        TreePath se�ilen = a�a�.getSelectionPath();
        if (se�ilen != null) {
            DefaultMutableTreeNode se�ilenDal =
                (DefaultMutableTreeNode) (se�ilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(se�ilenDal.getParent());
            if (ebeveyn != null) {
                a�a�Modeli.removeNodeFromParent (se�ilenDal);
                return;
            } // if-ebe.. karar� sonu...
        } // if-se�.. karar� sonu...

        // Ya silinecek se�me yap�lmad� ya da k�k dal se�ildi; ikaz sinyali...
        aletTak�m�.beep(); aletTak�m�.beep(); aletTak�m�.beep();
    } // se�ilenDal�Sil() metodu sonu...

    // K�k hari� t�m dallar� silelim...
    public void temizle() {
        k�kDal�.removeAllChildren();
        a�a�Modeli.reload();
    } // temizle() metodu sonu...

    // Se�ilen dala yeni bir (�ocuk) dal� ekleyelim...
    public DefaultMutableTreeNode nesneyiEkle (Object yavru) {
        DefaultMutableTreeNode ebeveynDal� = null;
        TreePath ebeveynYolu = a�a�.getSelectionPath();

        if (ebeveynYolu == null) ebeveynDal� = k�kDal�;
        else ebeveynDal� = (DefaultMutableTreeNode) (ebeveynYolu.getLastPathComponent());

        return nesneyiEkle (ebeveynDal�, yavru, true);
    } // nesneyiEkle(..1) metodu sonu...

    public DefaultMutableTreeNode nesneyiEkle (DefaultMutableTreeNode ebeveyn, Object yavru) {
        return nesneyiEkle (ebeveyn, yavru, false);
    } // nesneyiEkle(..2) metodu sonu...

    public DefaultMutableTreeNode nesneyiEkle (DefaultMutableTreeNode �stteki, Object alttaki, boolean g�r�lmeli) {
        DefaultMutableTreeNode altDal = new DefaultMutableTreeNode (alttaki);

        if (�stteki == null) �stteki = k�kDal�;
	
        // Bu i�lemin TreeModel �zerinde i�letilmesi ipucudur, DefaultMutableTreeNode �zerinde DE��L...
        a�a�Modeli.insertNodeInto (altDal, �stteki, �stteki.getChildCount());

        // Kullan�c�n�n sevimli yeni dal� g�rece�inden emin olun...
        if (g�r�lmeli) a�a�.scrollPathToVisible (new TreePath (altDal.getPath()));

        return altDal;
    } // nesneyiEkle(..3) metodu sonu...

    class A�a�ModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            /* E�er olay �ocuklar� listelerse, bu durumda de�i�en dal, mevcut dald�r;
             * aksi halde de�i�en dal ve se�ilen dal ayn� demektir...
             */

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullan�c�dal� de�i�tirmeyi bitirdi.");
            System.out.println ("Yeni de�er: [" + dal.getUserObject() + "]");
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved (TreeModelEvent olay) {}
        public void treeStructureChanged (TreeModelEvent olay) {}
    } // A�a�ModeliDinleyicim s�n�f� sonu...
} // J4a_6x2 s�n�f� sonu...