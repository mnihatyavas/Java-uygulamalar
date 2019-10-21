// J5c_63x.java: IntegerEditor (TamsayýDüzenleyici) alt-örneði.

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

// Standart bir hücre düzenleyici vasýtasýyla tamsayý veri giriþlerini denetler...
public class J5c_63x extends DefaultCellEditor {
    JFormattedTextField biçimliMetinSatýrý;
    NumberFormat tamsayýBiçimi;
    private Integer enküçük, enbüyük;
    private boolean yazýlsýnMý = true;

    public J5c_63x (int enk, int enb) {// Kurucu...
        super (new JFormattedTextField());
        biçimliMetinSatýrý = (JFormattedTextField)getComponent();
        enküçük = new Integer (enk);
        enbüyük = new Integer (enb);

        // Tamsayý hücreler için düzenleyiciyi kuralým...
        tamsayýBiçimi = NumberFormat.getIntegerInstance();
        NumberFormatter tamsayýBiçimleyici = new NumberFormatter (tamsayýBiçimi);
        tamsayýBiçimleyici.setFormat (tamsayýBiçimi);
        tamsayýBiçimleyici.setMinimum (enküçük);
        tamsayýBiçimleyici.setMaximum (enbüyük);

        biçimliMetinSatýrý.setFormatterFactory (new DefaultFormatterFactory (tamsayýBiçimleyici));
        biçimliMetinSatýrý.setValue (enküçük);
        biçimliMetinSatýrý.setHorizontalAlignment (JTextField.TRAILING); // Saða yanaþýk tamsayý...
        biçimliMetinSatýrý.setFocusLostBehavior (JFormattedTextField.PERSIST);

        // Enter ve Tab tuþlarý aktif editörün içerik kontrolunu tetikler...
        biçimliMetinSatýrý.getInputMap().put (KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0), "kontrol");
        biçimliMetinSatýrý.getActionMap().put ("kontrol", new AbstractAction() {
            public void actionPerformed (ActionEvent olay) {
                if (!biçimliMetinSatýrý.isEditValid()) {// Tamsayý giriþi geçersizse...
                    if (ilkineDön()) {// Ýlk (deðiþiklik öncesi) deðere dönülecek...
                        biçimliMetinSatýrý.postActionEvent(); // Düzenlemeyi bitir...
                    } // iç-if kararý sonu...
                }else
                    try {// Tamsayý giriþi geçerliyse...
                        biçimliMetinSatýrý.commitEdit(); // Deðiþikliði iþle...
                        biçimliMetinSatýrý.postActionEvent(); // Düzenlemeyi bitir...
                    }catch (java.text.ParseException esgeç) {}
            } // actionPerformed(..) hazýr metodu sonu...
        }); // biç.. ifadesi sonu...
    } // J5c_63x(..) kurucusu sonu...

    // Biçimli metin satýrý üzerindeki setValue(..) hazýr metodunu override/esgeçmede kullanýlýr...
    public Component getTableCellEditorComponent (JTable tablo, Object deðer, boolean seçiliMi, int satýr, int kolon) {
        JFormattedTextField biçimliMetinSatýrý = (JFormattedTextField)super.getTableCellEditorComponent (tablo, deðer, seçiliMi, satýr, kolon);
        biçimliMetinSatýrý.setValue (deðer);
        return biçimliMetinSatýrý;
    } // getTableCellEditorComponent(..) hazýr metodu sonu...

    // Hücre deðerinin tamsayý kalmasýný garantileyen override/esgeçme hazýr metodu...
    public Object getCellEditorValue() {
        JFormattedTextField biçimliMetinSatýrý = (JFormattedTextField)getComponent();
        Object nesne = biçimliMetinSatýrý.getValue();
        if (nesne instanceof Integer) return nesne;
        else if (nesne instanceof Number) return new Integer (((Number)nesne).intValue());
        else {if (yazýlsýnMý) System.out.println ("Hücre düzenleme deðeri: " + nesne + " bir sayý deðildir!");
            try {return tamsayýBiçimi.parseObject (nesne.toString());
            }catch (ParseException ist) {System.err.println ("Hücre düzenleme deðeri: " + nesne + " sayýsala çevrilemiyor!"); return null;}
        } // else kararý sonu...
    } // getCellEditorValue() hazýr metodu sonu...

    // Eðer düzenleme doðruysa yeni deðeri koyar, deðilse ikaz eder.
    // Editörden OK'le çýkýlacaðýnda bu metodun üst sýnýfýný iþletip etrafý temizleme override/esgeçme hazýr metodu.
    public boolean stopCellEditing() {
        JFormattedTextField biçimliMetinSatýrý = (JFormattedTextField)getComponent();
        if (biçimliMetinSatýrý.isEditValid()) {// Geçerli (tam)sayý giriþi...
            try {biçimliMetinSatýrý.commitEdit();
            }catch (java.text.ParseException aldýrma) {}
        }else {// Geçersiz tamsayý giriþi...
            if (!ilkineDön()) // Düzenlemeye devam edilecekse...
                return false; // Düzenleyici editör henüz kalsýn...
        } // else kararý sonu...
        return super.stopCellEditing();
    } // stopCellEditing() hazýr metodu sonu...

    /* Girilen tamsayý deðeri kötü uyarýsý alýnýnca kullanýcý ya ilk deðere
     * dönecektir (true) ya da hatalý giriþi deðiþtirmeye (false) yönelecektir...
     */
    protected boolean ilkineDön() {
        Toolkit.getDefaultToolkit().beep(); // Hatalý giriþ sesli "biip" ikazý verir...
        biçimliMetinSatýrý.selectAll();
        Object[] seçenekler = {"Deðiþtir", "Ýlkine Dön"};
        int cevap = JOptionPane.showOptionDialog (
            SwingUtilities.getWindowAncestor (biçimliMetinSatýrý),
            "Girilen tamsayý deðer " + enküçük + " ve " + enbüyük + " arasýnda olmalýdýr.\n" +
            "Deðiþikliðe devam edebilir veya önceki geçerli deðere dönebilirsin.",
            "Geçersiz Tamsayý Giriþi",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.ERROR_MESSAGE,
            null,
            seçenekler,
            seçenekler [1]);

        if (cevap == 1) {// Önceki deðere dönelim...
            biçimliMetinSatýrý.setValue (biçimliMetinSatýrý.getValue());
            return true;
        } // if kararý sonu...
        return false; // Düzenlemeye devam...
    } // ilkineDön() metodu sonu...
} // J5c_63x sýnýfý sonu...