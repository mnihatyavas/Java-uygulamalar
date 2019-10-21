// J4e_1x2.java: J4e_1x2 (DinamikAðaç) alt-örneði.

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
    protected DefaultMutableTreeNode kökDalý;
    protected DefaultTreeModel aðaçModeli;
    protected JTree aðaç;
    private Toolkit bipÝkazý = Toolkit.getDefaultToolkit();

    public J4e_1x2() {
        super (new GridLayout (1,0));

        kökDalý = new DefaultMutableTreeNode ("Kök Dalý");
        aðaçModeli = new DefaultTreeModel (kökDalý);

        aðaç = new JTree (aðaçModeli);
        aðaç.setEditable (true);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        aðaç.setShowsRootHandles (true);

        JScrollPane kaydýrmaPanosu = new JScrollPane (aðaç);
        add (kaydýrmaPanosu);
    } // J4e_1x2() kurucusu sonu...

    // "Kök Dalý" hariç tüm diðer dallarý temizleyelim...
    public void temizle() {
        kökDalý.removeAllChildren();
        aðaçModeli.reload();
    } // temizle() metodu sonu...

    // Seçili dalý silelim...
    public void seçiliDalýSil() {
        TreePath seçiliDal = aðaç.getSelectionPath();
        if (seçiliDal != null) {
            DefaultMutableTreeNode þimdikiDal = (DefaultMutableTreeNode)(seçiliDal.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(þimdikiDal.getParent());
            if (ebeveyn != null) {
                aðaçModeli.removeNodeFromParent (þimdikiDal);
                return;
            } // if-ebe.. kararý sonu...
        } // if-seç.. kararý sonu...

        // Seçilen yok veya "Kök Dalý" seçilmiþse ikaz edelim...
        bipÝkazý.beep();
    } // seçiliDalýSil() metodu sonu...

    // Seçili dala yeni bir çocuk (dalý) ekleyelim...
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

        // Çocuk dalý TreeModel'e eklenmelidir, DefaultMutableTreeNode'a DEÐÝL...
        aðaçModeli.insertNodeInto (çocukDalý, ebeveyn, ebeveyn.getChildCount());

        // Þayet görünür pencerenin altýna taþmýþsa, üste kaydýrarak yeni eklenen sevimli çocuk dalýnýn görülebilirliðinden emin olalým...
        if (görünmeliMi) aðaç.scrollPathToVisible (new TreePath (çocukDalý.getPath()));

        return çocukDalý;
    } // nesneEkle(..3) metodu sonu...

    class AðaçModeliDinleyicim implements TreeModelListener {
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullanýcý dalý deðiþtirmeyi bitirdi.");
            System.out.println ("Yeni deðer: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay) {}
        public void treeNodesRemoved (TreeModelEvent olay) {}
        public void treeStructureChanged (TreeModelEvent olay) {}
    } // AðaçModeliDinleyicim sýnýfý sonu...
} // J4e_1x2 sýnýfý sonu...