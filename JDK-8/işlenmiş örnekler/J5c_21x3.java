// J5c_21x3.java: GenealogyModel (ÞecereModeli) alt-örneði.

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import java.util.Vector;

public class J5c_21x3 implements TreeModel {
    private boolean atalarýGöstersinMi;
    private Vector<TreeModelListener> aðaçModeliDinleyicisi = new Vector<TreeModelListener>();
    private J5c_21x1 kökÞahýs; // J5c_21x1=Person

    public J5c_21x3 (J5c_21x1 kök) {
        atalarýGöstersinMi = false;
        kökÞahýs = kök;
    } // J5c_21x3(..) kurucusu sonu...

    // Atalarý göster/Türeyenleri göster tercihlerini ve aðaç kökünü deðiþtirir....
    public void atalarýGöster (boolean b, Object yeniKök) {
        atalarýGöstersinMi = b;
        J5c_21x1 eskiKök = kökÞahýs;
        if (yeniKök != null) kökÞahýs = (J5c_21x1)yeniKök;

        aðaçYapýsýDeðiþiminiTetikle (eskiKök);
    } // atalarýGöster(..) metodu sonu...

    protected void aðaçYapýsýDeðiþiminiTetikle (J5c_21x1 eskiKök) {
        int uzunluk = aðaçModeliDinleyicisi.size();
        TreeModelEvent olay = new TreeModelEvent (this, new Object[] {eskiKök});
        for (TreeModelListener dinleyici : aðaçModeliDinleyicisi) dinleyici.treeStructureChanged (olay);
    } // aðaçYapýsýDeðiþiminiTetikle(..) metodu sonu...

    public void addTreeModelListener (TreeModelListener dinleyici) {aðaçModeliDinleyicisi.addElement (dinleyici);}

    public Object getChild (Object ebeveyn, int endeks) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalarýGöstersinMi) {
            if ((endeks > 0) && (ebe.getFather() != null)) return ebe.getMother();
            return ebe.getFather();
        } // dýþ-if kararý sonu...
        return ebe.getChildAt (endeks);
    } // getChild(..) metodu sonu...

    public int getChildCount (Object ebeveyn) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalarýGöstersinMi) {
            int sayaç = 0;
            if (ebe.getFather() != null) sayaç++;
            if (ebe.getMother() != null) sayaç++;
            return sayaç;
        } // dýþ-if kararý sonu...
        return ebe.getChildCount();
    } // getChildCount(..) metodu sonu...

    public int getIndexOfChild (Object ebeveyn, Object çocuk) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalarýGöstersinMi) {
            int sayaç = 0;
            J5c_21x1 baba = ebe.getFather();
            if (baba != null) {
                sayaç++;
                if (baba == çocuk) return 0;
            } // 3.if kararý sonu...
            if (ebe.getMother() != çocuk) return sayaç;
            return -1;
        } // dýþ if kararý sonu...
        return ebe.getIndexOfChild ((J5c_21x1)çocuk);
    } // getIndexOfChild(..) metodu sonu...

    public Object getRoot() {return kökÞahýs;}

    public boolean isLeaf (Object dal) {
        J5c_21x1 ebe = (J5c_21x1)dal;
        if (atalarýGöstersinMi)  return ((ebe.getFather() == null) && (ebe.getMother() == null));
        return ebe.getChildCount() == 0;
    } // isLeaf(..) metodu sonu...

    public void removeTreeModelListener (TreeModelListener dinleyici) {aðaçModeliDinleyicisi.removeElement (dinleyici);}

    public void valueForPathChanged (TreePath yol, Object yeniDeðer) {
        System.out.println ("*** yolDeðiþimDeðeri : " + yol + " --> " + yeniDeðer);
    } // valueForPathChanged(..) metodu sonu...
} // J5c_21x3 sýnýfý sonu...