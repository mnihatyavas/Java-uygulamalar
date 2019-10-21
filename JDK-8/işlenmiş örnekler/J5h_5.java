// J5h_5.java: ListCutPaste (ListeyiKesYapýþtýr) örneði.

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
 * SürükleVeBýrak'la birlikte KesKopyala/Yapýþtýr'ý da kullanabileceksiniz.
 */
public class J5h_5 extends JPanel {
    J5h_5x1 listeYöneticisi;

    public J5h_5() {// Kurucu...
        super (new BorderLayout());
        listeYöneticisi = new J5h_5x1();

        JPanel panel = new JPanel (new GridLayout (1, 4));
        DefaultListModel birinciListeModeli = new DefaultListModel();
        birinciListeModeli.addElement ("Alpha");
        birinciListeModeli.addElement ("Beta");
        birinciListeModeli.addElement ("Gamma");
        birinciListeModeli.addElement ("Delta");
        birinciListeModeli.addElement ("Epsilon");
        birinciListeModeli.addElement ("Zeta");
        birinciListeModeli.addElement ("[Sürükle ile: Üste ve Araya girilebilir]");
        birinciListeModeli.addElement ("[Yapýþtýr ile: Daima Araya girilebilir]");
        JList liste1 = new JList (birinciListeModeli);
        liste1.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        JScrollPane kaydýrma1 = new JScrollPane (liste1);
        kaydýrma1.setPreferredSize (new Dimension (200, 100));
        liste1.setDragEnabled (true);
        liste1.setTransferHandler (listeYöneticisi);
        liste1.setDropMode (DropMode.ON_OR_INSERT);
        haritalamayýKur (liste1);
        JPanel panel1 = new JPanel (new BorderLayout());
        panel1.add (kaydýrma1, BorderLayout.CENTER);
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
        JScrollPane kaydýrma2 = new JScrollPane (liste2);
        kaydýrma2.setPreferredSize (new Dimension (200, 100));
        liste2.setTransferHandler (listeYöneticisi);
        liste2.setDropMode (DropMode.INSERT);
        haritalamayýKur (liste2);
        JPanel panel2 = new JPanel (new BorderLayout());
        panel2.add (kaydýrma2, BorderLayout.CENTER);
        panel2.setBorder (BorderFactory.createTitledBorder ("Fransýz Sayýlarý"));
        panel.add (panel2);

        DefaultListModel üçüncüListeModeli = new DefaultListModel();
        üçüncüListeModeli.addElement ("Odin");
        üçüncüListeModeli.addElement ("Dva");
        üçüncüListeModeli.addElement ("Tri");
        üçüncüListeModeli.addElement ("Çyetir");
        üçüncüListeModeli.addElement ("Pyat");
        üçüncüListeModeli.addElement ("Þest");
        üçüncüListeModeli.addElement ("[Üste girilebilir]");
        JList liste3 = new JList (üçüncüListeModeli);
        liste3.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste3.setDragEnabled (true);
        JScrollPane kaydýrma3 = new JScrollPane (liste3);
        kaydýrma3.setPreferredSize (new Dimension (200, 100));
        liste3.setTransferHandler (listeYöneticisi);
        liste3.setDropMode (DropMode.ON);
        haritalamayýKur (liste3);
        JPanel panel3 = new JPanel (new BorderLayout());
        panel3.add (kaydýrma3, BorderLayout.CENTER);
        panel3.setBorder (BorderFactory.createTitledBorder ("Rus Sayýlarý"));
        panel.add (panel3);

        DefaultListModel dördüncüListeModeli = new DefaultListModel();
        dördüncüListeModeli.addElement ("Bir");
        dördüncüListeModeli.addElement ("Ýki");
        dördüncüListeModeli.addElement ("Üç");
        dördüncüListeModeli.addElement ("Dört");
        dördüncüListeModeli.addElement ("Beþ");
        dördüncüListeModeli.addElement ("Altý");
        dördüncüListeModeli.addElement ("[Üste girilebilir]");
        JList liste4 = new JList (dördüncüListeModeli);
        liste4.setSelectionMode (ListSelectionModel.SINGLE_SELECTION);
        liste4.setDragEnabled (true);
        JScrollPane kaydýrma4 = new JScrollPane (liste4);
        kaydýrma4.setPreferredSize (new Dimension (200, 100));
        liste4.setTransferHandler (listeYöneticisi);
        liste4.setDropMode (DropMode.ON);
        haritalamayýKur (liste4);
        JPanel panel4 = new JPanel (new BorderLayout());
        panel4.add (kaydýrma4, BorderLayout.CENTER);
        panel4.setBorder (BorderFactory.createTitledBorder ("Türk Sayýlarý"));
        panel.add (panel4);

        setPreferredSize (new Dimension (500, 200));
        add (panel, BorderLayout.CENTER);
    } // J5h_5() kurucusu sonu...

    // Kes, Kopyala ve Yapýþtýr'lý bir menü yaratýr...
    public JMenuBar menüÇubuðunuYarat() {
        JMenuBar menüÇubuðu = new JMenuBar();
        JMenu anaMenü = new JMenu ("Düzenle");
        JMenuItem menüBirimi = null;

        anaMenü.setMnemonic (KeyEvent.VK_D);
        J5h_5x2 dinleyici = new J5h_5x2();

        menüBirimi = new JMenuItem ("Cut/Kes");
        menüBirimi.setActionCommand ((String)TransferHandler.getCutAction().getValue (Action.NAME));
        menüBirimi.addActionListener (dinleyici);
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_X,  ActionEvent.CTRL_MASK));
        menüBirimi.setMnemonic (KeyEvent.VK_K);
        anaMenü.add (menüBirimi);
        
        menüBirimi = new JMenuItem ("Copy/Kopyala");
        menüBirimi.setActionCommand ((String)TransferHandler.getCopyAction().getValue (Action.NAME));
        menüBirimi.addActionListener (dinleyici);
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_C,  ActionEvent.CTRL_MASK));
        menüBirimi.setMnemonic (KeyEvent.VK_P);
        anaMenü.add (menüBirimi);
        
        menüBirimi = new JMenuItem ("Paste/Yapýþtýr");
        menüBirimi.setActionCommand ((String)TransferHandler.getPasteAction().getValue (Action.NAME));
        menüBirimi.addActionListener (dinleyici);
        menüBirimi.setAccelerator (KeyStroke.getKeyStroke (KeyEvent.VK_V,  ActionEvent.CTRL_MASK));
        menüBirimi.setMnemonic (KeyEvent.VK_Y);
        anaMenü.add (menüBirimi);

        menüÇubuðu.add (anaMenü);
        return menüÇubuðu;
    } // menüÇubuðunuYarat() metodu sonu...
    
    // hareketHaritasý'na kes/kopyala/yapýþtýr eklenecek...
    private void haritalamayýKur (JList liste) {
        ActionMap hareketHaritasý = liste.getActionMap();
        hareketHaritasý.put (TransferHandler.getCutAction().getValue (Action.NAME), TransferHandler.getCutAction());
        hareketHaritasý.put (TransferHandler.getCopyAction().getValue (Action.NAME), TransferHandler.getCopyAction());
        hareketHaritasý.put (TransferHandler.getPasteAction().getValue (Action.NAME), TransferHandler.getPasteAction());
    } // haritalamayýKur() metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Listeyi Kes ve Yapýþtýr");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        J5h_5 gösteri = new J5h_5(); // Kurucuyu çaðýrýr...
        çerçeve.setJMenuBar (gösteri.menüÇubuðunuYarat());
        gösteri.setOpaque (true);
        çerçeve.setContentPane (gösteri);
        çerçeve.setLocation (500,100);
        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {           
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI();
            } // run() sicim hazýr metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_5 sýnýfý sonu...