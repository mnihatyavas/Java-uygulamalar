// J4a_6x2.java: DynamicTree (DinamikAðaç) alt-örneði.

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
    protected DefaultMutableTreeNode kökDalý;
    protected DefaultTreeModel aðaçModeli;
    protected JTree aðaç;
    private Toolkit aletTakýmý = Toolkit.getDefaultToolkit();

    public J4a_6x2() {
        super (new GridLayout (1, 0));

        kökDalý = new DefaultMutableTreeNode ("Kök Dalý");
        aðaçModeli = new DefaultTreeModel (kökDalý);

        aðaç = new JTree (aðaçModeli);
        aðaç.setEditable (true);
        aðaç.getSelectionModel().setSelectionMode
            (TreeSelectionModel.SINGLE_TREE_SELECTION);
        aðaç.setShowsRootHandles (true);

        JScrollPane kaydýrmaPanosu = new JScrollPane (aðaç);
        add (kaydýrmaPanosu);
    } // J4a_6x2() kurucusu sonu...

    // Seçili dalý silelim...
    public void seçilenDalýSil() {
        TreePath seçilen = aðaç.getSelectionPath();
        if (seçilen != null) {
            DefaultMutableTreeNode seçilenDal =
                (DefaultMutableTreeNode) (seçilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(seçilenDal.getParent());
            if (ebeveyn != null) {
                aðaçModeli.removeNodeFromParent (seçilenDal);
                return;
            } // if-ebe.. kararý sonu...
        } // if-seç.. kararý sonu...

        // Ya silinecek seçme yapýlmadý ya da kök dal seçildi; ikaz sinyali...
        aletTakýmý.beep(); aletTakýmý.beep(); aletTakýmý.beep();
    } // seçilenDalýSil() metodu sonu...

    // Kök hariç tüm dallarý silelim...
    public void temizle() {
        kökDalý.removeAllChildren();
        aðaçModeli.reload();
    } // temizle() metodu sonu...

    // Seçilen dala yeni bir (çocuk) dalý ekleyelim...
    public DefaultMutableTreeNode nesneyiEkle (Object yavru) {
        DefaultMutableTreeNode ebeveynDalý = null;
        TreePath ebeveynYolu = aðaç.getSelectionPath();

        if (ebeveynYolu == null) ebeveynDalý = kökDalý;
        else ebeveynDalý = (DefaultMutableTreeNode) (ebeveynYolu.getLastPathComponent());

        return nesneyiEkle (ebeveynDalý, yavru, true);
    } // nesneyiEkle(..1) metodu sonu...

    public DefaultMutableTreeNode nesneyiEkle (DefaultMutableTreeNode ebeveyn, Object yavru) {
        return nesneyiEkle (ebeveyn, yavru, false);
    } // nesneyiEkle(..2) metodu sonu...

    public DefaultMutableTreeNode nesneyiEkle (DefaultMutableTreeNode üstteki, Object alttaki, boolean görülmeli) {
        DefaultMutableTreeNode altDal = new DefaultMutableTreeNode (alttaki);

        if (üstteki == null) üstteki = kökDalý;
	
        // Bu iþlemin TreeModel üzerinde iþletilmesi ipucudur, DefaultMutableTreeNode üzerinde DEÐÝL...
        aðaçModeli.insertNodeInto (altDal, üstteki, üstteki.getChildCount());

        // Kullanýcýnýn sevimli yeni dalý göreceðinden emin olun...
        if (görülmeli) aðaç.scrollPathToVisible (new TreePath (altDal.getPath()));

        return altDal;
    } // nesneyiEkle(..3) metodu sonu...

    class AðaçModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            /* Eðer olay çocuklarý listelerse, bu durumda deðiþen dal, mevcut daldýr;
             * aksi halde deðiþen dal ve seçilen dal ayný demektir...
             */

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullanýcýdalý deðiþtirmeyi bitirdi.");
            System.out.println ("Yeni deðer: [" + dal.getUserObject() + "]");
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved (TreeModelEvent olay) {}
        public void treeStructureChanged (TreeModelEvent olay) {}
    } // AðaçModeliDinleyicim sýnýfý sonu...
} // J4a_6x2 sýnýfý sonu...