// J5c_13x.java: DynamicTree (DinamikA�a�) alt-�rne�i.

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
    protected DefaultMutableTreeNode k�kDal�;
    protected DefaultTreeModel a�a�Modeli;
    protected JTree a�a�;
    private Toolkit ikazBipi = Toolkit.getDefaultToolkit();

    public J5c_13x() {// Kurucu...
        super (new GridLayout (1,0));
        
        k�kDal� = new DefaultMutableTreeNode ("K�k Dal�");
        a�a�Modeli = new DefaultTreeModel (k�kDal�);
        a�a�Modeli.addTreeModelListener (new A�a�ModeliDinleyicim());
        a�a� = new JTree (a�a�Modeli);
        a�a�.setEditable (true);
        a�a�.getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        a�a�.setShowsRootHandles (true);
        a�a�.setBackground (new Color (0.8f, 0.5f, 1.0f));

        JScrollPane kayd�rmaPanosu = new JScrollPane (a�a�);
        add (kayd�rmaPanosu);
    } // J5c_13x() kurucusu sonu...

    // K�k hari� di�er t�m dallar� silelim...
    public void temizle() {
        k�kDal�.removeAllChildren();
        a�a�Modeli.reload();
    } // temizle() metodu sonu...

    // Akt�el se�ili dal� dilelim...
    public void �imdikiDal�Sil() {
        TreePath akt�elSe�ilen = a�a�.getSelectionPath();
        if (akt�elSe�ilen != null) {
            DefaultMutableTreeNode �imdikiDal = (DefaultMutableTreeNode)(akt�elSe�ilen.getLastPathComponent());
            MutableTreeNode ebeveyn = (MutableTreeNode)(�imdikiDal.getParent());
            if (ebeveyn != null) {
                a�a�Modeli.removeNodeFromParent (�imdikiDal);
                return;
            } // if-ebe.. karar� sonu...
        } // if-akt.. karar� sonu...

        // Se�ili dal yoksa, veya k�k dal se�ilmi�se bip ikaz� verelim...
        ikazBipi.beep();
    } // �imdikiDal�Sil() metodu sonu...

    // Akt�el se�ili dala yeni bir (artan sonek say�l�) �ocuk dal� ekleyelim...
    public DefaultMutableTreeNode dalNesnesiEkle (Object �ocuk) {
        DefaultMutableTreeNode ebeveynDal = null;
        TreePath ebeveynYol = a�a�.getSelectionPath();

        if (ebeveynYol == null) ebeveynDal = k�kDal�;
        else ebeveynDal = (DefaultMutableTreeNode)(ebeveynYol.getLastPathComponent());

        return dalNesnesiEkle (ebeveynDal, �ocuk, true);
    } // dalNesnesiEkle(..1) metodu sonu...

    public DefaultMutableTreeNode dalNesnesiEkle (DefaultMutableTreeNode ebeveyn, Object �ocuk) {
        return dalNesnesiEkle (ebeveyn, �ocuk, false);
    } // dalNesnesiEkle(..2) metodu sonu...

    public DefaultMutableTreeNode dalNesnesiEkle (DefaultMutableTreeNode ebeveyn, Object �ocuk, boolean g�r�nmeliMi) {
        DefaultMutableTreeNode �ocukDal� = new DefaultMutableTreeNode (�ocuk);

        if (ebeveyn == null) ebeveyn = k�kDal�;

        a�a�Modeli.insertNodeInto (�ocukDal�, ebeveyn, ebeveyn.getChildCount());

        // Yeni sevimli �ocuk dal�n�n g�r�n�rl���nden emin olal�m...
        if (g�r�nmeliMi) a�a�.scrollPathToVisible (new TreePath (�ocukDal�.getPath()));

        return �ocukDal�;
    } // dalNesnesiEkle(..3) metodu sonu...

    class A�a�ModeliDinleyicim implements TreeModelListener {
        // Bu metodlar TreeModelListenet abstract aray�z�ne ait g�vdesiz override metodlar
        // olup adlar� de�i�tirilemez, ve hepsi de listelenip {..} g�vdelenmelidir...
        public void treeNodesChanged (TreeModelEvent olay) {
            DefaultMutableTreeNode dal;
            dal = (DefaultMutableTreeNode)(olay.getTreePath().getLastPathComponent());

            int endeks = olay.getChildIndices()[0];
            dal = (DefaultMutableTreeNode)(dal.getChildAt (endeks));

            System.out.println ("Kullan�c� dal de�i�ikli�i i�lemlerini tamamlad�.");
            System.out.println ("Yeni de�er: " + dal.getUserObject());
        } // treeNodesChanged(..) metodu sonu...

        public void treeNodesInserted (TreeModelEvent olay){}
        public void treeNodesRemoved (TreeModelEvent olay){}
        public void treeStructureChanged (TreeModelEvent olay){}
    } // A�a�ModeliDinleyicim s�n�f� sonu...
} // J5c_13x s�n�f� sonu...