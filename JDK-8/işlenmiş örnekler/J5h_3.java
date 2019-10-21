// J5h_3.java: DropDemo (B�rakG�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.BorderLayout;
//import java.awt.datatransfer.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JComponent;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.BorderFactory;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

// Gereken dosya: J5h_3x.java=ListTransferHandler.java
public class J5h_3 extends JPanel implements ActionListener {
    private JComboBox a��l�rKutu;
    private JList liste;
    private JPanel panel;

    public J5h_3() {
        super (new GridLayout (2,1)); // Tek kolonda 2 alt-alta par�al� serilim...
        add (metinAlan�n�Yarat()); // �stteki (metin alanl�) ilk panelli par�a...
        add (listeyiYarat()); // Alttaki (listeli ve a��l�r kutulu) ikinci panelli par�a...
        setBackground (Color.CYAN);
    } // J5h_3() kurucusu sonu...

    private JPanel metinAlan�n�Yarat() {
        String metin = "Bu alan-dan/a se�iliyi s�r�kle veya b�rak.\nVarsay�l� hareket MOVE/Ta��'d�r;\nCOPY/Kopya'lama i�in ise Ctrl tu�unu bas�l� tutun.";
        
        JTextArea metinAlan� = new JTextArea();
        metinAlan�.setText (metin);
        metinAlan�.setDragEnabled (true);
        metinAlan�.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.95f));
        JScrollPane kayd�rmaPanosu = new JScrollPane (metinAlan�);
        kayd�rmaPanosu.setPreferredSize (new Dimension (400, 100));
        panel = new JPanel (new BorderLayout());
        panel.add (kayd�rmaPanosu, BorderLayout.CENTER);
        panel.setBorder (BorderFactory.createTitledBorder ("JText Area"));
        panel.setBackground (Color.CYAN);
        return panel;
    } // metinAlan�n�Yarat() metodu sonu...

    private JPanel listeyiYarat() {
        DefaultListModel listeModeli = new DefaultListModel();
        listeModeli.addElement ("Bu liste-den/ye se�iliyi s�r�kle veya b�rak.");            
        for (int i = 1; i <= 10; i++) listeModeli.addElement ("Liste Birimi " + i);
        // Veya==> for (int i = 10; i >= 0; i--) listeModeli.addElement (0, "Liste Birimi " + i);
        liste = new JList (listeModeli);
        liste.setSelectionMode (ListSelectionModel.SINGLE_INTERVAL_SELECTION); // Tekli ama icab�nda aral�kl� �oklu...
        liste.setDragEnabled (true);
        liste.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.75f));
        liste.setTransferHandler (new J5h_3x());
        JScrollPane kayd�rmaPanosu = new JScrollPane (liste);
        kayd�rmaPanosu.setPreferredSize (new Dimension (400, 100));

        a��l�rKutu = new JComboBox (new String[] {"SE��L�Y�_KULLAN", "�ST�NE", "ARAYA", "�ST�NE_VEYA_ARAYA"});
        a��l�rKutu.setBackground (Color.getHSBColor ((float)Math.random(), (float)Math.random(), 0.85f));
        a��l�rKutu.addActionListener (this); // Dinleyiciye duyarlayal�m...
        panel = new JPanel();
        panel.add (new JLabel ("Listeye B�rakma Kipi:"));
        panel.add (a��l�rKutu);
        panel.setBackground (Color.ORANGE);

        JPanel panel1 = new JPanel (new BorderLayout());
        panel1.add (kayd�rmaPanosu, BorderLayout.CENTER);
        panel1.add (panel, BorderLayout.SOUTH);
        panel1.setBorder (BorderFactory.createTitledBorder ("JList"));
        panel1.setBackground (Color.CYAN);
        return panel1;
    } // listeyiYarat() metodu sonu...

    public void actionPerformed (ActionEvent olay) {
        Object nesne = a��l�rKutu.getSelectedItem(); // import java.lang.Object;
        if (nesne == "SE��L�Y�_KULLAN") liste.setDropMode (DropMode.USE_SELECTION); // �ST�NE gibidir...
        else if (nesne == "�ST�NE") liste.setDropMode (DropMode.ON);
        else if (nesne == "ARAYA") liste.setDropMode (DropMode.INSERT);
        else if (nesne == "�ST�NE_VEYA_ARAYA") liste.setDropMode (DropMode.ON_OR_INSERT);
    } // actionPerformed(..) haz�r metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("B�rak G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5h_3(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI();
            } // run() sicim haz�r metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_3 s�n�f� sonu...