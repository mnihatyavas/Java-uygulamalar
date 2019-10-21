// J5c_51.java: SpinnerDemo (SayaçGösterisi) örneði.

/* Gereken Java dosyalarý:
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
    public J5c_51 (boolean ayYýlýEtkilesinMi) {
        super (new SpringLayout());

        String[] sayaçAdlarý = {"Ay (dizge): ", "Yýl (rakam): ", "Ay/Yýl (rakam): "};
        int sayaçSayýsý = sayaçAdlarý.length;
        Calendar takvim = Calendar.getInstance();
        JFormattedTextField biçimliMetinSatýrý = null;

        /* Not: Sayaç metin satýrýný týklayýp, siz de istediðiniz deðeri girebilirsiniz;
         * ya da sayaç üst/alt düðmesiyle birer basamak artýrýr/azaltabilirsiniz.*/

        // Ýlk etiket-sayaç çiftini ekleyelim...
        String[] ayAdlarý = ayAdlarýnýAl();
        SpinnerListModel ayModeli = null;
        if (ayYýlýEtkilesinMi) ayModeli = new J5c_51x1 (ayAdlarý); // Kendi kodladýðýmýz model...
        else ayModeli = new SpinnerListModel (ayAdlarý); // Standart hazýr model...

        JSpinner sayaç = etiketliSayacýEkle (this, sayaçAdlarý[0], ayModeli);
        // Sayacýn biçimli metin alaný uzunluðunu ve saða hizalanmasýný saðlayalým...
        biçimliMetinSatýrý = metinSatýrýnýAl (sayaç);
        if (biçimliMetinSatýrý != null ) {
            //biçimliMetinSatýrý.setColumns (8); // Ay adlarýndan daha uzun bir geniþlik...
            biçimliMetinSatýrý.setHorizontalAlignment (JTextField.RIGHT); // Saða hizalý...
        } // if kararý sonu...
        biçimliMetinSatýrý.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...

        // Ýkinci etiket-sayaç çiftini ekleyelim...
        int cariYýl = takvim.get (Calendar.YEAR);
        SpinnerModel yýlModeli = new SpinnerNumberModel (
                cariYýl, // Açýlýþ deðeri...
                cariYýl - 100, // Enküçük yýl deðeri...
                cariYýl + 100, // Enbüyük yýl deðeri...
                1); // Artýþ/azalýþ adýmý...
        // Ay yýlý etkileyecekse bu modeli ay modeliyle baðlantýlayalým...
        if (ayModeli instanceof J5c_51x1) ((J5c_51x1)ayModeli).setLinkedModel (yýlModeli);
        sayaç = etiketliSayacýEkle (this, sayaçAdlarý[1], yýlModeli);
        // Sayacýn yýl rakamý binler ayraçsýz biçimlensin...
        sayaç.setEditor (new JSpinner.NumberEditor (sayaç, "#"));
        biçimliMetinSatýrý = metinSatýrýnýAl (sayaç);
        biçimliMetinSatýrý.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...

        // Üçüncü etiket-sayaç çiftini ekleyelim...
        Date cariTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, -100); Date enküçükTarih = takvim.getTime();
        takvim.add (Calendar.YEAR, 200); Date enbüyükTarih = takvim.getTime();
        SpinnerModel tarihModeli = new SpinnerDateModel (
                cariTarih, // Açýlýþ deðeri...
                enküçükTarih, // Enküçük ay/yýl deðeri...
                enbüyükTarih, // Enbüyük ay/yýl deðeri...
                Calendar.YEAR); // Metin satýrý ay/yýl rakamýný týklayýp yazabilirsiniz...
        sayaç = etiketliSayacýEkle (this, sayaçAdlarý[2], tarihModeli);
        sayaç.setEditor (new JSpinner.DateEditor (sayaç, "MM/yyyy"));
        biçimliMetinSatýrý = metinSatýrýnýAl (sayaç);
        biçimliMetinSatýrý.setHorizontalAlignment (JTextField.RIGHT);
        biçimliMetinSatýrý.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Açýk renkler...

        // Komponentleri içerik panosuna serimleyelim...
        J5c_51x2.kesifIzgaraYap (
                this, // Ýçerik panosu...
                sayaçSayýsý, 2, // Satýr sayýsý, sütun sayýsý...
                10, 10, // Panodaki (ilkX, ilkY) konumlarý...
                6, 10); // sol-sað ve üst-alt (xPad, yPad) tamponlarý...

        setBackground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...
    } // J5c_51(..) kurucusu sonu...

    /* Editörün kullandýðý biçimli metin satýrýný veya
     * editör JSpinner.DefaultEditor soyundan deðilse null döndürür.
     */
    public JFormattedTextField metinSatýrýnýAl (JSpinner sayaç) {
        JComponent editör = sayaç.getEditor();
        if (editör instanceof JSpinner.DefaultEditor) return ((JSpinner.DefaultEditor)editör).getTextField();
        else {System.err.println ("[" + sayaç.getEditor().getClass() + "] editörü DefaultEditor sonundan olmayanb ilinmeyen bir editör çeþididir!"); return null;}
    } // metinSatýrýnýAl(..) metodu sonu...

    /* java.text.DateFormatSymbols().getMonths() ay adlarýný alýr.
     * Ancak þayet sonda bazen boþ bir alan varsa, atlanmalýdýr.
     */
    static protected String[] ayAdlarýnýAl() {
        String[] aylarStr = new java.text.DateFormatSymbols().getMonths();
        int sonEndeks = aylarStr.length - 1;

        if (aylarStr[sonEndeks] == null || aylarStr[sonEndeks].length() <= 0) {// Son birim boþ...ast item empty
            String[] ayAdlarý = new String[sonEndeks];
            System.arraycopy (aylarStr, 0, ayAdlarý, 0, sonEndeks);
            return ayAdlarý;
        }else return aylarStr; // Son birim boþ deðil...
    } // ayAdlarýnýAl() metodu sonu...

    static protected JSpinner etiketliSayacýEkle (Container içerikPanosu, String etiketStr, SpinnerModel sayaçModeli) {
        JLabel etiket = new JLabel (etiketStr);
        içerikPanosu.add (etiket);
        etiket.setForeground (Color.white);

        JSpinner sayaç = new JSpinner (sayaçModeli);
        etiket.setLabelFor (sayaç);
        içerikPanosu.add (sayaç);

        return sayaç;
    } // etiketliSayacýEkle(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Sayaç Gösterisi");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        çerçeve.add (new J5c_51 (false)); // Ay sayacý yýl sayacýný etkilemeksizin (false) kurucuyu çaðýr...
        çerçeve.setLocation (550,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        javax.swing.SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                javax.swing.UIManager.put ("swing.boldMetal", Boolean.FALSE); // Koyu yazýlýmý kapat...
                yaratVeGösterGUI();
            } // run() hazýr sicim metodu sonu...
        }); // jav.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_51 sýnýfý sonu...