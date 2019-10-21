// J5c_21x2.java: GenealogyTree (�ecereA�ac�) alt-�rne�i.

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class J5c_21x2 extends JTree {
    J5c_21x3 model; // J5c_21x3=GenealogyModel

    public J5c_21x2 (J5c_21x1 grafikDal�) {// J5c_21x1=Person
        super (new J5c_21x3 (grafikDal�));
        getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultTreeCellRenderer takdimci = new DefaultTreeCellRenderer();
        Icon �ah�s�konu = null;
        takdimci.setLeafIcon (�ah�s�konu);
        takdimci.setClosedIcon (�ah�s�konu);
        takdimci.setOpenIcon (�ah�s�konu);
        setCellRenderer (takdimci);
    } // J5c_21x2(..) kurucusu sonu...

    // Soya�ac�ndan se�ili birimi al�p a�a�-modeli s�n�f�ndaki atalar�G�ster(..) metodunu �a��r�r...
    public void atalar�G�ster (boolean g�stersinMi) {
        Object yeniK�k = null;
        TreePath yol = getSelectionModel().getSelectionPath();
        if (yol != null) yeniK�k = yol.getLastPathComponent();

        ((J5c_21x3)getModel()).atalar�G�ster (g�stersinMi, yeniK�k);
    } // atalar�G�ster(..) metodu sonu...
} // J5c_21x2 s�n�f� sonu...