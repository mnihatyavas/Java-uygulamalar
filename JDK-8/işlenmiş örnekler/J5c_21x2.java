// J5c_21x2.java: GenealogyTree (ÞecereAðacý) alt-örneði.

import javax.swing.Icon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class J5c_21x2 extends JTree {
    J5c_21x3 model; // J5c_21x3=GenealogyModel

    public J5c_21x2 (J5c_21x1 grafikDalý) {// J5c_21x1=Person
        super (new J5c_21x3 (grafikDalý));
        getSelectionModel().setSelectionMode (TreeSelectionModel.SINGLE_TREE_SELECTION);
        DefaultTreeCellRenderer takdimci = new DefaultTreeCellRenderer();
        Icon þahýsÝkonu = null;
        takdimci.setLeafIcon (þahýsÝkonu);
        takdimci.setClosedIcon (þahýsÝkonu);
        takdimci.setOpenIcon (þahýsÝkonu);
        setCellRenderer (takdimci);
    } // J5c_21x2(..) kurucusu sonu...

    // Soyaðacýndan seçili birimi alýp aðaç-modeli sýnýfýndaki atalarýGöster(..) metodunu çaðýrýr...
    public void atalarýGöster (boolean göstersinMi) {
        Object yeniKök = null;
        TreePath yol = getSelectionModel().getSelectionPath();
        if (yol != null) yeniKök = yol.getLastPathComponent();

        ((J5c_21x3)getModel()).atalarýGöster (göstersinMi, yeniKök);
    } // atalarýGöster(..) metodu sonu...
} // J5c_21x2 sýnýfý sonu...