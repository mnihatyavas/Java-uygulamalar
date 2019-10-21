// J5c_13x.java: DynamicTree (DinamikAðaç) alt-örneði.

import java.awt.Color;
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

import javax.swing.event.TreeModelListener;
import javax.swing.event.TreeModelEvent;

public class J5c_13x extends JPanel {
    protected DefaultMutableTreeNode kökDalý;
    protected DefaultTreeModel aðaçModeli;
    protected JTree aðaç;
    private Toolkit ikazBipi = Toolkit.getDefaultToolkit();

    public J5c_13x() {// Kurucu...
        super (new GridLayout (1,0));
        
        kökDalý = new DefaultMutableTreeNode ("Kök Dalý");
        aðaçModeli = new DefaultTreeModel (kökDalý);
        aðaçModeli.addTreeModelListener (new AðaçModeliDinleyicim());
        aðaç = new JTree (aðaçModeli);
        aðaç.setEditable (true);
        aðaç.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        aðaç.setShowsRootHandles (true);
        aðaç.setBackground (new Color (0.8f, 0.5f, 1.0f));

        JScrollPane kaydýrmaPanosu = new JScrollPane (aðaç);
        add (kaydýrmaPanosu);
    } // J5c_13x() kurucusu sonu...

    // Kök hariç diðer tüm dallarý silelim...
    public void temizle() {
        kökDalý.removeAllChildren();
        aðaçModeli.reload();
    } // temizle() metodu sonu...

    // Aktüel seçili dalý dilelim...
    public void þimdikiDalýSil() {
        TreePath aktüelSeçilen = aðaç.getSelectionPath();
        if (aktüelSeçilen != null) {
            DefaultMutableTreeNode þimdikiDal = (DefaultMutableTreeNode)(aktüelSeçilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(þimdikiDal.getParent());
            if (ebeveyn != null) {
                aðaçModeli.removeNodeFromParent (þimdikiDal);
                return;
            } // if-ebe.. kararý sonu...
        } // if-akt.. kararý sonu...

        // Seçili dal yoksa, veya kök dal seçilmiþse bip ikazý verelim...
        ikazBipi.beep();
    } // þimdikiDalýSil() metodu sonu...

    // Aktüel seçili dala yeni bir (artan sonek sayýlý) çocuk dalý ekleyelim...
    public DefaultMutableTreeNode dalNesnesiEkle (Object çocuk) {
        DefaultMutableTreeNode ebeveynDal = null;
        TreePath ebeveynYol = aðaç.getSelectionPath();

        if (ebeveynYol == null) ebeveynDal = kökDalý;
        else ebeveynDal = (DefaultMutableTreeNode)(ebeveynYol.getLastPathComponent());

        return dalNesnesiEkle (ebeveynDal, çocuk, true);
    } // dalNesnesiEkle(..1) metodu sonu...

    public DefaultMutableTreeNode dalNesnesiEkle (DefaultMutableTreeNode ebeveyn, Object çocuk) {
        return dalNesnesiEkle (ebeveyn, çocuk, false);
    } // dalNesnesiEkle(..2) metodu sonu...

    public DefaultMutableTreeNode dalNesnesiEkle (DefaultMutableTreeNode ebeveyn, Object çocuk, boolean görünmeliMi) {
        DefaultMutableTreeNode çocukDalý = new DefaultMutableTreeNode (çocuk);

        if (ebeveyn == null) ebeveyn = kökDalý;

        aðaçModeli.insertNodeInto (çocukDalý, ebeveyn, ebeveyn.getChildCount());

        // Yeni sevimli çocuk dalýnýn görünürlüðünden emin olalým...
        if (görünmeliMi) aðaç.scrollPathToVisible (new TreePath (çocukDalý.getPath()));

        return çocukDalý;
    } // dalNesnesiEkle(..3) metodu sonu...

    class AðaçModeliDinleyicim implements TreeModelListener {
        // Bu metodlar TreeModelListenet abstract arayüzüne ait gövdesiz override metodlar
        // olup adlarý deðiþtirilemez, ve hepsi de listelenip {..} gövdelenmelidir...
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullanýcý dal deðiþikliði iþlemlerini tamamladý.");
            System.out.println ("Yeni deðer: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay){}
        public void treeNodesRemoved (TreeModelEvent olay){}
        public void treeStructureChanged (TreeModelEvent olay){}
    } // AðaçModeliDinleyicim sýnýfý sonu...
} // J5c_13x sýnýfý sonu...