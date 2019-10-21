// J5c_21x3.java: GenealogyModel (�ecereModeli) alt-�rne�i.

import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import java.util.Vector;

public class J5c_21x3 implements TreeModel {
    private boolean atalar�G�stersinMi;
    private Vector<TreeModelListener> a�a�ModeliDinleyicisi = new Vector<TreeModelListener>();
    private J5c_21x1 k�k�ah�s; // J5c_21x1=Person

    public J5c_21x3 (J5c_21x1 k�k) {
        atalar�G�stersinMi = false;
        k�k�ah�s = k�k;
    } // J5c_21x3(..) kurucusu sonu...

    // Atalar� g�ster/T�reyenleri g�ster tercihlerini ve a�a� k�k�n� de�i�tirir....
    public void atalar�G�ster (boolean b, Object yeniK�k) {
        atalar�G�stersinMi = b;
        J5c_21x1 eskiK�k = k�k�ah�s;
        if (yeniK�k != null) k�k�ah�s = (J5c_21x1)yeniK�k;

        a�a�Yap�s�De�i�iminiTetikle (eskiK�k);
    } // atalar�G�ster(..) metodu sonu...

    protected void a�a�Yap�s�De�i�iminiTetikle (J5c_21x1 eskiK�k) {
        int uzunluk = a�a�ModeliDinleyicisi.size();
        TreeModelEvent olay = new TreeModelEvent (this, new Object[] {eskiK�k});
        for (TreeModelListener dinleyici : a�a�ModeliDinleyicisi) dinleyici.treeStructureChanged (olay);
    } // a�a�Yap�s�De�i�iminiTetikle(..) metodu sonu...

    public void addTreeModelListener (TreeModelListener dinleyici) {a�a�ModeliDinleyicisi.addElement (dinleyici);}

    public Object getChild (Object ebeveyn, int endeks) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalar�G�stersinMi) {
            if ((endeks > 0) && (ebe.getFather() != null)) return ebe.getMother();
            return ebe.getFather();
        } // d��-if karar� sonu...
        return ebe.getChildAt (endeks);
    } // getChild(..) metodu sonu...

    public int getChildCount (Object ebeveyn) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalar�G�stersinMi) {
            int saya� = 0;
            if (ebe.getFather() != null) saya�++;
            if (ebe.getMother() != null) saya�++;
            return saya�;
        } // d��-if karar� sonu...
        return ebe.getChildCount();
    } // getChildCount(..) metodu sonu...

    public int getIndexOfChild (Object ebeveyn, Object �ocuk) {
        J5c_21x1 ebe = (J5c_21x1)ebeveyn;
        if (atalar�G�stersinMi) {
            int saya� = 0;
            J5c_21x1 baba = ebe.getFather();
            if (baba != null) {
                saya�++;
                if (baba == �ocuk) return 0;
            } // 3.if karar� sonu...
            if (ebe.getMother() != �ocuk) return saya�;
            return -1;
        } // d�� if karar� sonu...
        return ebe.getIndexOfChild ((J5c_21x1)�ocuk);
    } // getIndexOfChild(..) metodu sonu...

    public Object getRoot() {return k�k�ah�s;}

    public boolean isLeaf (Object dal) {
        J5c_21x1 ebe = (J5c_21x1)dal;
        if (atalar�G�stersinMi)  return ((ebe.getFather() == null) && (ebe.getMother() == null));
        return ebe.getChildCount() == 0;
    } // isLeaf(..) metodu sonu...

    public void removeTreeModelListener (TreeModelListener dinleyici) {a�a�ModeliDinleyicisi.removeElement (dinleyici);}

    public void valueForPathChanged (TreePath yol, Object yeniDe�er) {
        System.out.println ("*** yolDe�i�imDe�eri : " + yol + " --> " + yeniDe�er);
    } // valueForPathChanged(..) metodu sonu...
} // J5c_21x3 s�n�f� sonu...