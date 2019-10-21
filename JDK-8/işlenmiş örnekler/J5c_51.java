// J5c_51.java: SpinnerDemo (Saya�G�sterisi) �rne�i.

/* Gereken Java dosyalar�:
 *   J5c_51x1.java=CyclingSpinnerListModel.java
 *   J5c_51x2.java=SpringUtilities.java
 */
import java.awt.Color;
import java.awt.Container;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JSpinner;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JFormattedTextField;
import javax.swing.SpringLayout;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SpinnerListModel;

import java.util.Date;
import java.util.Calendar;

public class J5c_51 extends JPanel {// Kurucu...
    public J5c_51 (boolean ayY�l�EtkilesinMi) {
        super (new SpringLayout());

        String[] saya�Adlar� = {"Ay (dizge): ", "Y�l (rakam): ", "Ay/Y�l (rakam): "};
        int saya�Say�s� = saya�Adlar�.length;
        Calendar takvim = Calendar.getInstance();
        JFormattedTextField bi�imliMetinSat�r� = null;

        /* Not: Saya� metin sat�r�n� t�klay�p, siz de istedi�iniz de�eri girebilirsiniz;
         * ya da saya� �st/alt d��mesiyle birer basamak art�r�r/azaltabilirsiniz.*/

        // �lk etiket-saya� �iftini ekleyelim...
        String[] ayAdlar� = ayAdlar�n�Al();
        SpinnerListModel ayModeli = null;
        if (ayY�l�EtkilesinMi) ayModeli = new J5c_51x1 (ayAdlar�); // Kendi kodlad���m�z model...
        else ayModeli = new SpinnerListModel (ayAdlar�); // Standart haz�r model...

        JSpinner saya� = etiketliSayac�Ekle (this, saya�Adlar�[0], ayModeli);
        // Sayac�n bi�imli metin alan� uzunlu�unu ve sa�a hizalanmas�n� sa�layal�m...
        bi�imliMetinSat�r� = metinSat�r�n�Al (saya�);
        if (bi�imliMetinSat�r� != null ) {
            //bi�imliMetinSat�r�.setColumns (8); // Ay adlar�ndan daha uzun bir geni�lik...
            bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.RIGHT); // Sa�a hizal�...
        } // if karar� sonu...
        bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...

        // �kinci etiket-saya� �iftini ekleyelim...
        int cariY�l = takvim.get (Calendar.YEAR);
        SpinnerModel y�lModeli = new SpinnerNumberModel (
                cariY�l, // A��l�� de�eri...
                cariY�l - 100, // Enk���k y�l de�eri...
                cariY�l + 100, // Enb�y�k y�l de�eri...
                1); // Art��/azal�� ad�m�...
        // Ay y�l� etkileyecekse bu modeli ay modeliyle ba�lant�layal�m...
        if (ayModeli instanceof J5c_51x1) ((J5c_51x1)ayModeli).setLinkedModel (y�lModeli);
        saya� = etiketliSayac�Ekle (this, saya�Adlar�[1], y�lModeli);
        // Sayac�n y�l rakam� binler ayra�s�z bi�imlensin...
        saya�.setEditor (new JSpinner.NumberEditor (saya�, "#"));
        bi�imliMetinSat�r� = metinSat�r�n�Al (saya�);
        bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...

        // ���nc� etiket-saya� �iftini ekleyelim...
        Date cariTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, -100); Date enk���kTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, 200); Date enb�y�kTarih = takvim.getTime();
        SpinnerModel tarihModeli = new SpinnerDateModel (
                cariTarih, // A��l�� de�eri...
                enk���kTarih, // Enk���k ay/y�l de�eri...
                enb�y�kTarih, // Enb�y�k ay/y�l de�eri...
                Calendar.YEAR); // Metin sat�r� ay/y�l rakam�n� t�klay�p yazabilirsiniz...
        saya� = etiketliSayac�Ekle (this, saya�Adlar�[2], tarihModeli);
        saya�.setEditor (new JSpinner.DateEditor (saya�, "MM/yyyy"));
        bi�imliMetinSat�r� = metinSat�r�n�Al (saya�);
        bi�imliMetinSat�r�.setHorizontalAlignment (JTextField.RIGHT);
        bi�imliMetinSat�r�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...

        // Komponentleri i�erik panosuna serimleyelim...
        J5c_51x2.kesifIzgaraYap (
                this, // ��erik panosu...
                saya�Say�s�, 2, // Sat�r say�s�, s�tun say�s�...
                10, 10, // Panodaki (ilkX, ilkY) konumlar�...
                6, 10); // sol-sa� ve �st-alt (xPad, yPad) tamponlar�...

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_51(..) kurucusu sonu...

    /* Edit�r�n kulland��� bi�imli metin sat�r�n� veya
     * edit�r JSpinner.DefaultEditor soyundan de�ilse null d�nd�r�r.
     */
    public JFormattedTextField metinSat�r�n�Al (JSpinner saya�) {
        JComponent edit�r = saya�.getEditor();
        if (edit�r instanceof JSpinner.DefaultEditor) return ((JSpinner.DefaultEditor)edit�r).getTextField();
        else {System.err.println ("[" + saya�.getEditor().getClass() + "] edit�r� DefaultEditor sonundan olmayanb ilinmeyen bir edit�r �e�ididir!"); return null;}
    } // metinSat�r�n�Al(..) metodu sonu...

    /* java.text.DateFormatSymbols().getMonths() ay adlar�n� al�r.
     * Ancak �ayet sonda bazen bo� bir alan varsa, atlanmal�d�r.
     */
    static protected String[] ayAdlar�n�Al() {
        String[] aylarStr = new java.text.DateFormatSymbols().getMonths();
        int sonEndeks = aylarStr.length - 1;

        if (aylarStr[sonEndeks] == null || aylarStr[sonEndeks].length() <= 0) {// Son birim bo�...ast item empty
            String[] ayAdlar� = new String[sonEndeks];
            System.arraycopy (aylarStr, 0, ayAdlar�, 0, sonEndeks);
            return ayAdlar�;
        }else return aylarStr; // Son birim bo� de�il...
    } // ayAdlar�n�Al() metodu sonu...

    static protected JSpinner etiketliSayac�Ekle (Container i�erikPanosu, String etiketStr, SpinnerModel saya�Modeli) {
        JLabel etiket = new JLabel (etiketStr);
        i�erikPanosu.add (etiket);
        etiket.setForeground (Color.white);

        JSpinner saya� = new JSpinner (saya�Modeli);
        etiket.setLabelFor (saya�);
        i�erikPanosu.add (saya�);

        return saya�;
    } // etiketliSayac�Ekle(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Saya� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        �er�eve.add (new J5c_51 (false)); // Ay sayac� y�l sayac�n� etkilemeksizin (false) kurucuyu �a��r...
        �er�eve.setLocation (550,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yaz�l�m� kapat...
                yaratVeG�sterGUI();
            } // run() haz�r sicim metodu sonu...
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_51 s�n�f� sonu...