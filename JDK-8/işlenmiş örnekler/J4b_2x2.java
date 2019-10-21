// J4b_2x2.java: DynamicTree (DinamikAðaç) alt-örneði.

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
    protected DefaultMutableTreeNode kökDalý;
    protected DefaultTreeModel aðaçModeli;
    protected JTree aðaç;
    private Toolkit bipleyici = Toolkit.getDefaultToolkit();

    public J4b_2x2() {
        super (new GridLayout (1,0));

        kökDalý = new DefaultMutableTreeNode ("Kök-Dalý");
        aðaçModeli = new DefaultTreeModel (kökDalý);

        aðaç = new JTree (aðaçModeli);
        aðaç.setEditable (true);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        aðaç.setShowsRootHandles (true);

        JScrollPane kaydýrmaPanosu = new JScrollPane (aðaç);
        add (kaydýrmaPanosu); // aðaç mevcut pencereyi taþarsa kaydýrmaPanosu tebarruz eder...
    } // J4b_2x2() kurucusu sonu...

    // Kök-Dalý hariç aðacýn tüm dallarý temizlenecek...
    public void temizle() {
        kökDalý.removeAllChildren();
        aðaçModeli.reload();
    } // temizle() metodu sonu...

    // Sadece seçili dal silinecek...
    public void seçiliDalýSil() {
        TreePath seçilen = aðaç.getSelectionPath();
        if (seçilen != null) {
            DefaultMutableTreeNode þimdikiDal = (DefaultMutableTreeNode)(seçilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(þimdikiDal.getParent());
            if (ebeveyn != null) {// Kök dal'sa silmez...
                aðaçModeli.removeNodeFromParent (þimdikiDal);
                return;
            } // if-ebe.. kararý sonu...
        } // if-seç.. kararý sonu...

        // Seçilen yoksa veya kök dal seçilmiþse...
        bipleyici.beep();
    } // seçiliDalýSil() metodu sonu...

    // Seçili dala çocuk eklenecek...
    public DefaultMutableTreeNode nesneEkle (Object çocuk) {
        DefaultMutableTreeNode ebeveynDalý = null;
        TreePath ebeveynYolu = aðaç.getSelectionPath();

        if (ebeveynYolu == null) ebeveynDalý = kökDalý;
        else ebeveynDalý = (DefaultMutableTreeNode)(ebeveynYolu.getLastPathComponent());

        return nesneEkle (ebeveynDalý, çocuk, true);
    } // nesneEkle(..1) metodu sonu...

    public DefaultMutableTreeNode nesneEkle (
            DefaultMutableTreeNode ebeveyn,
            Object çocuk) {
        return nesneEkle (ebeveyn, çocuk, false);
    } // nesneEkle(..2) metodu sonu...

    public DefaultMutableTreeNode nesneEkle (
            DefaultMutableTreeNode ebeveyn,
            Object çocuk, 
            boolean görünmeliMi) {
        DefaultMutableTreeNode çocukDalý = new DefaultMutableTreeNode (çocuk);

        if (ebeveyn == null) ebeveyn = kökDalý;

        // Bunun TreeModel üzerinde yürütülmesi anahtardýr, DefaultMutableTreeNode üzerinde DEÐÝL...
        aðaçModeli.insertNodeInto (çocukDalý, ebeveyn, ebeveyn.getChildCount());

        // Taþmýþsa, kullanýcý bu sevimli yeni çocuk dalýný görebilmeli...
        if (görünmeliMi) aðaç.scrollPathToVisible (new TreePath (çocukDalý.getPath()));

        return çocukDalý;
    } // nesneEkle(..3) metodu sonu...

    class AðaçModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            /*
             * Eðer olay çocuklarý listeliyorsa, deðiþen dal elimizdeki dalýn çocuðudur.
             * Listelemiyorsa, deðiþen ve seçilen dal aynýdýr....
             */

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullanýcý dalý deðiþtirmeyi tamamladý.");
            System.out.println ("Yeni deðer: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved(TreeModelEvent olay) {}
        public void treeStructureChanged(TreeModelEvent olay) {}
    } // AðaçModeliDinleyicim sýnýfý sonu...
} // J4b_2x2 sýnýfý sonu...