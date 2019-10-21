// J5i_3.java: ContainerEventDemo (KabOlay�G�sterisi) �rne�i.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ContainerListener;
import java.awt.event.ContainerEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.BorderFactory;

import java.util.Vector;

public class J5i_3 extends JPanel implements ContainerListener, ActionListener {
    JTextArea metinAlan�;
    JPanel butonPaneli;
    JButton ekleD��mesi, silD��mesi, temizleD��mesi;
    Vector<JButton> d��meListesi;
    static final String EKLE = "ekle";
    static final String S�L = "sil";
    static final String TEM�ZLE = "temizle";
    static final String yeniSat�r = "\n";

    public J5i_3() {// Kurucu...
        super (new GridBagLayout());
        GridBagLayout �zgara�antas� = (GridBagLayout)getLayout();
        GridBagConstraints s�n�rlay�c�lar = new GridBagConstraints();

        // Bo� bir d��me listesi yaratal�m...
        d��meListesi = new Vector<JButton>();

        // T�m komponent par�alar� (ekle-sil-temizle d��meleri, d��me-paneli, kayd�rmal� metin alan�) yaratal�m...
        ekleD��mesi = new JButton ("Alt panele yeni bir d��me ekle");
        ekleD��mesi.setActionCommand (EKLE);
        ekleD��mesi.addActionListener (this); // Dinleyiciye duyarlayal�m...

        silD��mesi = new JButton ("Mevcut sondaki bir d��meyi sil");
        silD��mesi.setActionCommand (S�L);
        silD��mesi.addActionListener (this); // Dinleyiciye duyarlayal�m...

        butonPaneli = new JPanel (new GridLayout (1,1));
        butonPaneli.setPreferredSize (new Dimension (200, 75));
        butonPaneli.addContainerListener (this); // Dinleyiciye duyarlayal�m...

        metinAlan� = new JTextArea();
        metinAlan�.setBackground (Color.CYAN);
        metinAlan�.setEditable (false); // M�dahalesiz...
        JScrollPane kayd�rma = new JScrollPane (metinAlan�);
        kayd�rma.setPreferredSize (new Dimension (200, 75));

        temizleD��mesi = new JButton ("Metin alan� i�eriklerini tamamen temizle");
        temizleD��mesi.setActionCommand (TEM�ZLE);
        temizleD��mesi.addActionListener (this); // Dinleyiciye duyarlayal�m...

        s�n�rlay�c�lar.fill = GridBagConstraints.BOTH; // Fill entire cell.
        s�n�rlay�c�lar.weighty = 1.0;  // Y dikey boyu kayd�rma ve buton paneline e�it b�l�necek...
        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER; // Payla��ms�z sat�r sonu...
        �zgara�antas�.setConstraints (kayd�rma, s�n�rlay�c�lar);
        add (kayd�rma);

        s�n�rlay�c�lar.weighty = 0.0;
        �zgara�antas�.setConstraints (temizleD��mesi, s�n�rlay�c�lar);
        add (temizleD��mesi);

        s�n�rlay�c�lar.weightx = 1.0;  // X yatay eni ekle ve sil d��mesine e�it b�l�necek...
        s�n�rlay�c�lar.gridwidth = 1;  // Ence hen�z sat�r sonu DE��L...
        �zgara�antas�.setConstraints (ekleD��mesi, s�n�rlay�c�lar);
        add (ekleD��mesi);

        s�n�rlay�c�lar.gridwidth = GridBagConstraints.REMAINDER; // Ence sat�r sonu...
        �zgara�antas�.setConstraints (silD��mesi, s�n�rlay�c�lar);
        add (silD��mesi);

        s�n�rlay�c�lar.weighty = 1.0; // Kayd�rma ve buton paneline e�it y�kseklik...
        �zgara�antas�.setConstraints (butonPaneli, s�n�rlay�c�lar);
        add (butonPaneli);

        setPreferredSize (new Dimension(450, 400));
        setBorder (BorderFactory.createEmptyBorder (20,20,20,20));
    } // J5i_3() kurucusu sonu...

    // Ekle-sil-temizle d��me olaylar�na duyarl�...
    public void actionPerformed (ActionEvent olay) {
        String komut = olay.getActionCommand();

        if (EKLE.equals (komut)) {
            JButton d��me = new JButton ("JButton #"
                                          + (d��meListesi.size() + 1));
            d��meListesi.addElement (d��me);
            butonPaneli.add (d��me);
            butonPaneli.revalidate(); // Yeni eklenen d��me g�r�ns�n...

        }else if (S�L.equals (komut)) {
            int sonEndeks = d��meListesi.size() - 1;
            try {JButton d��me = d��meListesi.elementAt (sonEndeks);
                butonPaneli.remove (d��me);
                d��meListesi.removeElementAt (sonEndeks);
                butonPaneli.revalidate(); // Silinen d��me g�r�nt�den ��ks�n...
                //butonPaneli.repaint();
            }catch (ArrayIndexOutOfBoundsException ist) {} // Olmayan d��meyi sileceksen ald�rma...
        }else if (TEM�ZLE.equals (komut)) metinAlan�.setText("");
    } // actionPerformed(..) haz�r metodu sonu...

    public void componentAdded (ContainerEvent olay) {mesajYaz ("'e eklendi...", olay);}
    public void componentRemoved (ContainerEvent olay) {mesajYaz ("'den silindi...", olay);}

    void mesajYaz (String hareket, ContainerEvent olay) {
        metinAlan�.append (((JButton)olay.getChild()).getText() + "==>"
                + olay.getContainer().getClass().getName() + hareket + yeniSat�r);
        metinAlan�.setCaretPosition (metinAlan�.getDocument().getLength());
    } // mesajYaz(..) metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Kab Olaylar� G�sterisi");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        JComponent yeni��erikPanosu = new J5i_3(); // Kurucuyu �a��r�r...
        yeni��erikPanosu.setOpaque (true);
        �er�eve.setContentPane (yeni��erikPanosu);
        �er�eve.setLocation (500,100);
        �er�eve.pack();
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {public void run() {yaratVeG�sterGUI();}});
    } // main(..) metodu sonu...
} // J5i_3 s�n�f� sonu...