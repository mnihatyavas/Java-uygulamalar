// J5c_70.java: TextAreaDemo (MetinAlan�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;
import javax.swing.WindowConstants;
import javax.swing.LayoutStyle;
import javax.swing.GroupLayout;

import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;

import javax.swing.text.BadLocationException;

import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class J5c_70 extends JFrame implements DocumentListener {
    private JLabel etiket;
    private JScrollPane kayd�rmaPanosu;
    private JTextArea metinAlan�;

    private static final String AKS�YON_YAP = "yap";
    private static enum Kip { SOK, TAMAMLA };
    private final List<String> kelimeler;
    private Kip kip = Kip.SOK;

    public J5c_70() {// Kurucu...
        super ("Metin Alan� G�sterisi");
        komponentleriKur();

        // Metin alan� de�i�ikli�ini dinleyiciye duyarlayal�m...
        metinAlan�.getDocument().addDocumentListener (this);

        InputMap veriGiri�Haritas� = metinAlan�.getInputMap();
        ActionMap aksiyonHaritas� = metinAlan�.getActionMap();
        veriGiri�Haritas�.put (KeyStroke.getKeyStroke ("ENTER"), AKS�YON_YAP);
        aksiyonHaritas�.put (AKS�YON_YAP, new AksiyonYap());

        kelimeler = new ArrayList<String>(5);
        kelimeler.add ("k�v�lc�m");
        kelimeler.add ("�zel");
        kelimeler.add ("d�rb�n");
        kelimeler.add ("muhta�em");
        kelimeler.add ("sal�n�m");
    } // J5c_70() kurucusu sonu...

    private void komponentleriKur() {
        setDefaultCloseOperation (3); // 3=WindowConstants.EXIT_ON_CLOSE
        //0=..DO_NOTHING_ON_CLOSE, 1=..HIDE_ON_CLOSE, 2=..DISPOSE_ON_CLOSE
        etiket = new JLabel ("'muhte�em' veya 'Sal�n�m' vb gibi yatay/dikey kelimeler yazmay� dene:");
        etiket.setForeground (Color.BLUE);

        metinAlan� = new JTextArea();
        metinAlan�.setColumns (20);
        metinAlan�.setLineWrap (true);
        metinAlan�.setRows (5);
        metinAlan�.setWrapStyleWord (true);
        metinAlan�.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // A��k renkler...
        metinAlan�.setForeground (new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) )); // Koyu renkler...

        kayd�rmaPanosu = new JScrollPane (metinAlan�);

        GroupLayout serilim = new GroupLayout (getContentPane());
        getContentPane().setLayout (serilim);

        // Yatay eksen i�in bir paralel grup yaratal�m...
        ParallelGroup yatayParalelGrup = serilim.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir ard���k grup ve bir de yatay grup yaratal�m...
        SequentialGroup yatayArd���kGrup = serilim.createSequentialGroup();
        ParallelGroup yatayParalelGrup2 = serilim.createParallelGroup (GroupLayout.Alignment.TRAILING);
        // Kayd�rma panosunu  ve etiketi yatay paralel grup 2'ye ekleyelim...
        yatayParalelGrup2.addComponent (kayd�rmaPanosu, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
        yatayParalelGrup2.addComponent (etiket, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE);
        // Yatay ard���k gruba bir i�erik bo�lu�u ekleyelim...
        yatayArd���kGrup.addContainerGap();
        // Yatay paralel grup 2'yi ve sonra da bir i�erik bo�lu�unu ard����a ekleyelim...
        yatayArd���kGrup.addGroup (yatayParalelGrup2);
        yatayArd���kGrup.addContainerGap();
        // Yatay ard����� yatay paralele ekleyelim...
        yatayParalelGrup.addGroup (Alignment.TRAILING, yatayArd���kGrup);
        // Sonu� yatay grubumuzu yaratal�m...
        serilim.setHorizontalGroup (yatayParalelGrup);

        // Dikey eksen i�in bir paralel grup yaratal�m...
        ParallelGroup dikeyParalelGrup = serilim.createParallelGroup (GroupLayout.Alignment.LEADING);
        // Bir (dikey) ard���k grup yaratal�m...
        SequentialGroup dikeyArd���kGrup = serilim.createSequentialGroup();
        // Ard����a bo�luk ekleyelim...
        dikeyArd���kGrup.addContainerGap();
        // Etiketi ard����a ekleyelim...
        dikeyArd���kGrup.addComponent (etiket);
        dikeyArd���kGrup.addPreferredGap (LayoutStyle.ComponentPlacement.RELATED);
        // Ard����a kayd�rma panosunu ve bo�lu�u ekleyelim...
        dikeyArd���kGrup.addComponent (kayd�rmaPanosu, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE);
        dikeyArd���kGrup.addContainerGap();
        // Ard����� dikey paralele ekleyelim...
        dikeyParalelGrup.addGroup (dikeyArd���kGrup);
        // Sonu� dikey grubumuzu yaratal�m...
        serilim.setVerticalGroup (dikeyParalelGrup);
        pack();
    } // komponentleriKur() metodu sonu...

    // D�k�man dinleyici haz�r metodlar�:
    public void changedUpdate (DocumentEvent ald�rma) {}
    public void removeUpdate (DocumentEvent ald�rma) { }

    public void insertUpdate (DocumentEvent olay) {
        if (olay.getLength() != 1) return;

        int konum = olay.getOffset();
        String i�erik = null;
        try {i�erik = metinAlan�.getText (0, konum + 1);
        }catch (BadLocationException ist) {ist.printStackTrace();}

        // Kelime ba�lang��lar�n� tespit edelim...
        int k;
        for (k = konum; k >= 0; k--) if (! Character.isLetter (i�erik.charAt (k))) break;

        if (konum - k < 2) // Kelime de�il (harf!)...
            return;

        String �nek = i�erik.substring (k + 1).toLowerCase();
        int n = Collections.binarySearch (kelimeler, �nek);
        if (n < 0 && -n <= kelimeler.size()) {
            String e�le�en = kelimeler.get (-n - 1);
            if (e�le�en.startsWith (�nek)) {
                // Tamamlama tespiti...
                String tamamlama = e�le�en.substring (konum - k);
                // De�i�ikli�i d�k�man� hala tararken yapamay�z, bu y�zden 
                // bu i�lemi sonra yapacak hat�rlatmay� b�rakal�m...
                SwingUtilities.invokeLater (new G�reviTamamla (tamamlama, konum + 1));
            } // i�-if karar� sonu...
        }else // Hi� e�le�me bulunamad�...
            kip = Kip.SOK;
    } // insertUpdate(..) haz�r metodu sonu...

    private class G�reviTamamla implements Runnable {
        String tamamlama;
        int konum;

        G�reviTamamla (String tamamlama, int konum) {
            this.tamamlama = tamamlama;
            this.konum = konum;
        } // G�reviTamamla(..) kurucusu sonu...

        public void run() {// Runnable sicimi otomatik ko�turmas�...
            metinAlan�.insert (tamamlama, konum);
            metinAlan�.setCaretPosition (konum + tamamlama.length());
            metinAlan�.moveCaretPosition (konum);
            kip = Kip.TAMAMLA;
        } // run() haz�r sicim metodu sonu...
    } // G�reviTamamla s�n�f� sonu...

    private class AksiyonYap extends AbstractAction {
        public void actionPerformed (ActionEvent olay) {
            if (kip == Kip.TAMAMLA) {
                int konum = metinAlan�.getSelectionEnd();
                metinAlan�.insert (" ", konum);
                metinAlan�.setCaretPosition (konum + 1);
                kip = Kip.SOK;
            } else metinAlan�.replaceSelection ("\n");
        } // actionPerformed(..) haz�r metodu sonu...
    } // AksiyonYap s�n�f� sonu...

    public static void main (String args[]) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                new J5c_70().setVisible (true); // Kurucuyu �a��r, pencereyi g�ster...
            } // run() haz�r sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_70 s�n�f� sonu...