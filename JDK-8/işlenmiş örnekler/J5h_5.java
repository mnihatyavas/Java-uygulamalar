// J5h_5.java: ListCutPaste (ListeyiKesYap��t�r) �rne�i.

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.DropMode;
import javax.swing.BorderFactory;
import javax.swing.KeyStroke;
import javax.swing.TransferHandler;
import javax.swing.ActionMap;
import javax.swing.Action;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

/* Gerekli dosyalar:
 *   J5h_5x1.java = ListTransferHandler.java
 *   J5h_5x2.java = TransferActionListener.java
 *
 * S�r�kleVeB�rak'la birlikte KesKopyala/Yap��t�r'� da kullanabileceksiniz.
 */
public class J5h_5 extends JPanel {
    J5h_5x1 listeY�neticisi;

    public J5h_5() {// Kurucu...
        super (new BorderLayout());
        listeY�neticisi = new J5h_5x1();

        JPanel panel = new JPanel (new GridLayout (1, 4));
        DefaultListModel birinciListeModeli = new DefaultListModel();
        birinciListeModeli.addElement ("Alpha");
        birinciListeModeli.addElement ("Beta");
        birinciListeModeli.addElement ("Gamma");
        birinciListeModeli.addElement ("Delta");
        birinciListeModeli.addElement ("Epsilon");
        birinciListeModeli.addElement ("Zeta");
        birinciListeModeli.addElement ("[S�r�kle ile: �ste ve Araya girilebilir]");
        birinciListeModeli.addElement ("[Yap��t�r ile: Daima Araya girilebilir]");
        JList liste1 = new JList (birinciListeModeli);
        liste1.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        JScrollPane kayd�rma1 = new JScrollPane (liste1);
        kayd�rma1.setPreferredSize (new Dimension (200, 100));
        liste1.setDragEnabled (true);
        liste1.setTransferHandler (listeY�neticisi);
        liste1.setDropMode (DropMode.ON_OR_INSERT);
        haritalamay�Kur (liste1);
        JPanel panel1 = new JPanel (new BorderLayout());
        panel1.add (kayd�rma1, BorderLayout.CENTER);
        panel1.setBorder (BorderFactory.createTitledBorder ("Yunan Alfabesi"));
        panel.add (panel1);

        DefaultListModel ikinciListeModeli = new DefaultListModel();
        ikinciListeModeli.addElement ("Une");
        ikinciListeModeli.addElement ("Deux");
        ikinciListeModeli.addElement ("Trois");
        ikinciListeModeli.addElement ("Quatre");
        ikinciListeModeli.addElement ("Cinq");
        ikinciListeModeli.addElement ("Six");
        ikinciListeModeli.addElement ("[Araya girilebilir]");
        JList liste2 = new JList (ikinciListeModeli);
        liste2.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste2.setDragEnabled (true);
        JScrollPane kayd�rma2 = new JScrollPane (liste2);
        kayd�rma2.setPreferredSize (new Dimension (200, 100));
        liste2.setTransferHandler (listeY�neticisi);
        liste2.setDropMode (DropMode.INSERT);
        haritalamay�Kur (liste2);
        JPanel panel2 = new JPanel (new BorderLayout());
        panel2.add (kayd�rma2, BorderLayout.CENTER);
        panel2.setBorder (BorderFactory.createTitledBorder ("Frans�z Say�lar�"));
        panel.add (panel2);

        DefaultListModel ���nc�ListeModeli = new DefaultListModel();
        ���nc�ListeModeli.addElement ("Odin");
        ���nc�ListeModeli.addElement ("Dva");
        ���nc�ListeModeli.addElement ("Tri");
        ���nc�ListeModeli.addElement ("�yetir");
        ���nc�ListeModeli.addElement ("Pyat");
        ���nc�ListeModeli.addElement ("�est");
        ���nc�ListeModeli.addElement ("[�ste girilebilir]");
        JList liste3 = new JList (���nc�ListeModeli);
        liste3.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste3.setDragEnabled (true);
        JScrollPane kayd�rma3 = new JScrollPane (liste3);
        kayd�rma3.setPreferredSize (new Dimension (200, 100));
        liste3.setTransferHandler (listeY�neticisi);
        liste3.setDropMode (DropMode.ON);
        haritalamay�Kur (liste3);
        JPanel panel3 = new JPanel (new BorderLayout());
        panel3.add (kayd�rma3, BorderLayout.CENTER);
        panel3.setBorder (BorderFactory.createTitledBorder ("Rus Say�lar�"));
        panel.add (panel3);

        DefaultListModel d�rd�nc�ListeModeli = new DefaultListModel();
        d�rd�nc�ListeModeli.addElement ("Bir");
        d�rd�nc�ListeModeli.addElement ("�ki");
        d�rd�nc�ListeModeli.addElement ("��");
        d�rd�nc�ListeModeli.addElement ("D�rt");
        d�rd�nc�ListeModeli.addElement ("Be�");
        d�rd�nc�ListeModeli.addElement ("Alt�");
        d�rd�nc�ListeModeli.addElement ("[�ste girilebilir]");
        JList liste4 = new JList (d�rd�nc�ListeModeli);
        liste4.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste4.setDragEnabled (true);
        JScrollPane kayd�rma4 = new JScrollPane (liste4);
        kayd�rma4.setPreferredSize (new Dimension (200, 100));
        liste4.setTransferHandler (listeY�neticisi);
        liste4.setDropMode (DropMode.ON);
        haritalamay�Kur (liste4);
        JPanel panel4 = new JPanel (new BorderLayout());
        panel4.add (kayd�rma4, BorderLayout.CENTER);
        panel4.setBorder (BorderFactory.createTitledBorder ("T�rk Say�lar�"));
        panel.add (panel4);

        setPreferredSize (new Dimension (500, 200));
        add (panel, BorderLayout.CENTER);
    } // J5h_5() kurucusu sonu...

    // Kes, Kopyala ve Yap��t�r'l� bir men� yarat�r...
    public JMenuBar men��ubu�unuYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        JMenu anaMen� = new JMenu ("D�zenle");
        JMenuItem men�Birimi = null;

        anaMen�.setMnemonic (KeyEvent.VK_D);
        J5h_5x2 dinleyici = new J5h_5x2();

        men�Birimi = new JMenuItem ("Cut/Kes");
        men�Birimi.setActionCommand ((String)TransferHandler.getCutAction().getValue (Action.NAME));
        men�Birimi.addActionListener (dinleyici);
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_X,  ActionEvent.CTRL_MASK));
        men�Birimi.setMnemonic (KeyEvent.VK_K);
        anaMen�.add (men�Birimi);
        
        men�Birimi = new JMenuItem ("Copy/Kopyala");
        men�Birimi.setActionCommand ((String)TransferHandler.getCopyAction().getValue (Action.NAME));
        men�Birimi.addActionListener (dinleyici);
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
        men�Birimi.setMnemonic (KeyEvent.VK_P);
        anaMen�.add (men�Birimi);
        
        men�Birimi = new JMenuItem ("Paste/Yap��t�r");
        men�Birimi.setActionCommand ((String)TransferHandler.getPasteAction().getValue (Action.NAME));
        men�Birimi.addActionListener (dinleyici);
        men�Birimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_V,  ActionEvent.CTRL_MASK));
        men�Birimi.setMnemonic (KeyEvent.VK_Y);
        anaMen�.add (men�Birimi);

        men��ubu�u.add (anaMen�);
        return men��ubu�u;
    } // men��ubu�unuYarat() metodu sonu...
    
    // hareketHaritas�'na kes/kopyala/yap��t�r eklenecek...
    private void haritalamay�Kur (JList liste) {
        ActionMap hareketHaritas� = liste.getActionMap();
        hareketHaritas�.put (TransferHandler.getCutAction().getValue (Action.NAME), TransferHandler.getCutAction());
        hareketHaritas�.put (TransferHandler.getCopyAction().getValue (Action.NAME), TransferHandler.getCopyAction());
        hareketHaritas�.put (TransferHandler.getPasteAction().getValue (Action.NAME), TransferHandler.getPasteAction());
    } // haritalamay�Kur() metodu sonu...

    private static void yaratVeG�sterGUI() {
        JFrame �er�eve = new JFrame ("Listeyi Kes ve Yap��t�r");
        �er�eve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5h_5 g�steri = new J5h_5(); // Kurucuyu �a��r�r...
        �er�eve.setJMenuBar (g�steri.men��ubu�unuYarat());
        g�steri.setOpaque (true);
        �er�eve.setContentPane (g�steri);
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
} // J5h_5 s�n�f� sonu...