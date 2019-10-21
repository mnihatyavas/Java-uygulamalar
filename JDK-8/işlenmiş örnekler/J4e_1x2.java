// J4e_1x2.java: J4e_1x2 (DinamikA�a�) alt-�rne�i.

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

public class J4e_1x2 extends JPanel {
    protected DefaultMutableTreeNode k�kDal�;
    protected DefaultTreeModel a�a�Modeli;
    protected JTree a�a�;
    private Toolkit bip�kaz� = Toolkit.getDefaultToolkit();

    public J4e_1x2() {
        super (new GridLayout (1,0));

        k�kDal� = new DefaultMutableTreeNode ("K�k Dal�");
        a�a�Modeli = new DefaultTreeModel (k�kDal�);

        a�a� = new JTree (a�a�Modeli);
        a�a�.setEditable (true);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        a�a�.setShowsRootHandles (true);

        JScrollPane kayd�rmaPanosu = new JScrollPane (a�a�);
        add (kayd�rmaPanosu);
    } // J4e_1x2() kurucusu sonu...

    // "K�k Dal�" hari� t�m di�er dallar� temizleyelim...
    public void temizle() {
        k�kDal�.removeAllChildren();
        a�a�Modeli.reload();
    } // temizle() metodu sonu...

    // Se�ili dal� silelim...
    public void se�iliDal�Sil() {
        TreePath se�iliDal = a�a�.getSelectionPath();
        if (se�iliDal != null) {
            DefaultMutableTreeNode �imdikiDal = (DefaultMutableTreeNode)(se�iliDal.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(�imdikiDal.getParent());
            if (ebeveyn != null) {
                a�a�Modeli.removeNodeFromParent (�imdikiDal);
                return;
            } // if-ebe.. karar� sonu...
        } // if-se�.. karar� sonu...

        // Se�ilen yok veya "K�k Dal�" se�ilmi�se ikaz edelim...
        bip�kaz�.beep();
    } // se�iliDal�Sil() metodu sonu...

    // Se�ili dala yeni bir �ocuk (dal�) ekleyelim...
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

        // �ocuk dal� TreeModel'e eklenmelidir, DefaultMutableTreeNode'a DE��L...
        a�a�Modeli.insertNodeInto (�ocukDal�, ebeveyn, ebeveyn.getChildCount());

        // �ayet g�r�n�r pencerenin alt�na ta�m��sa, �ste kayd�rarak yeni eklenen sevimli �ocuk dal�n�n g�r�lebilirli�inden emin olal�m...
        if (g�r�nmeliMi) a�a�.scrollPathToVisible (new TreePath (�ocukDal�.getPath()));

        return �ocukDal�;
    } // nesneEkle(..3) metodu sonu...

    class A�a�ModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullan�c� dal� de�i�tirmeyi bitirdi.");
            System.out.println ("Yeni de�er: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved (TreeModelEvent olay) {}
        public void treeStructureChanged (TreeModelEvent olay) {}
    } // A�a�ModeliDinleyicim s�n�f� sonu...
} // J4e_1x2 s�n�f� sonu...