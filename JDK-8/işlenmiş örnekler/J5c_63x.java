// J5c_63x.java: IntegerEditor (Tamsay�D�zenleyici) alt-�rne�i.

import java.awt.Component;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.DefaultCellEditor;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.AbstractAction;
import javax.swing.SwingUtilities;

import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.NumberFormatter;

import java.text.NumberFormat;
import java.text.ParseException;

// Standart bir h�cre d�zenleyici vas�tas�yla tamsay� veri giri�lerini denetler...
public class J5c_63x extends DefaultCellEditor {
    JFormattedTextField bi�imliMetinSat�r�;
    NumberFormat tamsay�Bi�imi;
    private Integer enk���k, enb�y�k;
    private boolean yaz�ls�nM� = true;

    public J5c_63x (int enk, int enb) {// Kurucu...
        super (new JFormattedTextField());
        bi�imliMetinSat�r� = (JFormattedTextField)getComponent();
        enk���k = new Integer (enk);
        enb�y�k = new Integer (enb);

        // Tamsay� h�creler i�in d�zenleyiciyi kural�m...
        tamsay�Bi�imi = NumberFormat.getIntegerInstance();
        NumberFormatter tamsay�Bi�imleyici = new NumberFormatter (tamsay�Bi�imi);
        tamsay�Bi�imleyici.setFormat (tamsay�Bi�imi);
        tamsay�Bi�imleyici.setMinimum (enk���k);
        tamsay�Bi�imleyici.setMaximum (enb�y�k);

        bi�imliMetinSat�r�.setFormatterFactory (new DefaultFormatterFactory (tamsay�Bi�imleyici));
        bi�imliMetinSat�r�.setValue (enk���k);
        bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.TRAILING); // Sa�a yana��k tamsay�...
        bi�imliMetinSat�r�.setFocusLostBehavior (JFormattedTextField.PERSIST);

        // Enter ve Tab tu�lar� aktif edit�r�n i�erik kontrolunu tetikler...
        bi�imliMetinSat�r�.getInputMap().put (KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0), "kontrol");
        bi�imliMetinSat�r�.getActionMap().put ("kontrol", new AbstractAction() {
            public void actionPerformed (ActionEvent olay) {
                if (!bi�imliMetinSat�r�.isEditValid()) {// Tamsay� giri�i ge�ersizse...
                    if (ilkineD�n()) {// �lk (de�i�iklik �ncesi) de�ere d�n�lecek...
                        bi�imliMetinSat�r�.postActionEvent(); // D�zenlemeyi bitir...
                    } // i�-if karar� sonu...
                }else
                    try {// Tamsay� giri�i ge�erliyse...
                        bi�imliMetinSat�r�.commitEdit(); // De�i�ikli�i i�le...
                        bi�imliMetinSat�r�.postActionEvent(); // D�zenlemeyi bitir...
                    }catch (java.text.ParseException esge�) {}
            } // actionPerformed(..) haz�r metodu sonu...
        }); // bi�.. ifadesi sonu...
    } // J5c_63x(..) kurucusu sonu...

    // Bi�imli metin sat�r� �zerindeki setValue(..) haz�r metodunu override/esge�mede kullan�l�r...
    public Component getTableCellEditorComponent (JTable tablo, Object de�er, boolean se�iliMi, int sat�r, int kolon) {
        JFormattedTextField bi�imliMetinSat�r� = (JFormattedTextField)super.getTableCellEditorComponent (tablo, de�er, se�iliMi, sat�r, kolon);
        bi�imliMetinSat�r�.setValue (de�er);
        return bi�imliMetinSat�r�;
    } // getTableCellEditorComponent(..) haz�r metodu sonu...

    // H�cre de�erinin tamsay� kalmas�n� garantileyen override/esge�me haz�r metodu...
    public Object getCellEditorValue() {
        JFormattedTextField bi�imliMetinSat�r� = (JFormattedTextField)getComponent();
        Object nesne = bi�imliMetinSat�r�.getValue();
        if (nesne instanceof Integer) return nesne;
        else if (nesne instanceof Number) return new Integer (((Number)nesne).intValue());
        else {if (yaz�ls�nM�) System.out.println ("H�cre d�zenleme de�eri: " + nesne + " bir say� de�ildir!");
            try {return tamsay�Bi�imi.parseObject (nesne.toString());
            }catch (ParseException ist) {System.err.println ("H�cre d�zenleme de�eri: " + nesne + " say�sala �evrilemiyor!"); return null;}
        } // else karar� sonu...
    } // getCellEditorValue() haz�r metodu sonu...

    // E�er d�zenleme do�ruysa yeni de�eri koyar, de�ilse ikaz eder.
    // Edit�rden OK'le ��k�laca��nda bu metodun �st s�n�f�n� i�letip etraf� temizleme override/esge�me haz�r metodu.
    public boolean stopCellEditing() {
        JFormattedTextField bi�imliMetinSat�r� = (JFormattedTextField)getComponent();
        if (bi�imliMetinSat�r�.isEditValid()) {// Ge�erli (tam)say� giri�i...
            try {bi�imliMetinSat�r�.commitEdit();
            }catch (java.text.ParseException ald�rma) {}
        }else {// Ge�ersiz tamsay� giri�i...
            if (!ilkineD�n()) // D�zenlemeye devam edilecekse...
                return false; // D�zenleyici edit�r hen�z kals�n...
        } // else karar� sonu...
        return super.stopCellEditing();
    } // stopCellEditing() haz�r metodu sonu...

    /* Girilen tamsay� de�eri k�t� uyar�s� al�n�nca kullan�c� ya ilk de�ere
     * d�necektir (true) ya da hatal� giri�i de�i�tirmeye (false) y�nelecektir...
     */
    protected boolean ilkineD�n() {
        Toolkit.getDefaultToolkit().beep(); // Hatal� giri� sesli "biip" ikaz� verir...
        bi�imliMetinSat�r�.selectAll();
        Object[] se�enekler = {"De�i�tir", "�lkine D�n"};
        int cevap = JOptionPane.showOptionDialog (
            SwingUtilities.getWindowAncestor (bi�imliMetinSat�r�),
            "Girilen tamsay� de�er " + enk���k + " ve " + enb�y�k + " aras�nda olmal�d�r.\n" +
            "De�i�ikli�e devam edebilir veya �nceki ge�erli de�ere d�nebilirsin.",
            "Ge�ersiz Tamsay� Giri�i",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            se�enekler,
            se�enekler [1]);

        if (cevap == 1) {// �nceki de�ere d�nelim...
            bi�imliMetinSat�r�.setValue (bi�imliMetinSat�r�.getValue());
            return true;
        } // if karar� sonu...
        return false; // D�zenlemeye devam...
    } // ilkineD�n() metodu sonu...
} // J5c_63x s�n�f� sonu...