// J5h_8.java: TopLevelTransferHandlerDemo (ÜstSeviyeAktarmaYönetimiGösterisi) örneði.

import java.awt.Font;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;

import javax.swing.JFrame;
import javax.swing.JSplitPane;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.ListSelectionModel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JTextArea;
import javax.swing.DropMode;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

import java.net.URL;
import java.net.MalformedURLException;

import java.util.List;

import java.beans.PropertyVetoException;

public class J5h_8 extends JFrame {
    private static boolean KÜÇÜK_EBATLA = false;
    private JDesktopPane masaüstüPanosu = new JDesktopPane();
    private DefaultListModel listeModeli = new DefaultListModel();
    private JList liste = new JList(listeModeli);
    private int sol;
    private int üst;
    private JCheckBoxMenuItem kopyaBirimi;
    private JCheckBoxMenuItem silBirimi;
    private JCheckBoxMenuItem yönetimBirimi;
    private TransferHandler yönetim = new TransferHandler() {
        public boolean canImport (TransferHandler.TransferSupport destek) {
            if (! destek.isDataFlavorSupported (DataFlavor.javaFileListFlavor)) return false;
            if (kopyaBirimi.isSelected()) {
                boolean kopyaDesteði = (COPY & destek.getSourceDropActions()) == COPY;
                if (! kopyaDesteði) return false;
                destek.setDropAction (COPY);
            } // if kararý sonu...
            return true;
        } // canImport(..) hazýr fonksiyonu sonu...

        public boolean importData (TransferHandler.TransferSupport destek) {
            if (! canImport (destek)) return false;
            Transferable aktarýlabilir = destek.getTransferable();
            try {List<File> liste = (List<File>)aktarýlabilir.getTransferData (DataFlavor.javaFileListFlavor);
                for (File dosya : liste) new Döküman (dosya);
            }catch (UnsupportedFlavorException ist) {return false;
            }catch (IOException ist) {return false;}
            return true;
        } // importData(..) hazýr fonksiyonu sonu...
    }; // pri.. sýnýf tip deðiþkeni ifadesi sonu...

    public J5h_8 (String[] argümanlar) {// Kurucu...
        super ("Üst Seviye Aktarma Yönetimi Gösterisi");
        setJMenuBar (iþlevsizMenüÇubuðunuYarat());
        getContentPane().add (iþlevsizAletÇubuðunuYarat(), BorderLayout.NORTH);

        JSplitPane bölümlüPano = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, liste, masaüstüPanosu);
        bölümlüPano.setDividerLocation (120); // Bölmelerarasý paravanýn ilk konumu...
        getContentPane().add (bölümlüPano);

        // Komut iletisinden girilecek dosyalarý açýp isimlerini liste, içeriklerini masaüstü pano bölmelerine kuralým...
        for (int i=0; i < argümanlar.length; i++) new Döküman (argümanlar[i]);

        liste.getSelectionModel().setSelectionMode (ListSelectionModel.SINGLE_SELECTION);

        liste.addListSelectionListener (new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent olay) {
                if (olay.getValueIsAdjusting()) return;
                Döküman deðer = (Döküman)liste.getSelectedValue();
                if (deðer != null) deðer.seç();
             } // addListSelectionListener(..) hazýr metodu sonu...
        }); // lis.. ifadesi sonu...

        final TransferHandler aktarmayýYönet = liste.getTransferHandler();

        silBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (silBirimi.isSelected()) liste.setTransferHandler (null);
                else liste.setTransferHandler (aktarmayýYönet);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // sil.. ifadesi sonu...

        yönetimBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (yönetimBirimi.isSelected()) setTransferHandler (yönetim);
                else setTransferHandler (null);
            } // actionPerformed(..) hazýr metodu sonu...
        }); // yön.. ifadesi sonu...
        masaüstüPanosu.setTransferHandler(yönetim);
    } // J5h_8(..) kurucusu sonu...

    private JMenuBar iþlevsizMenüÇubuðunuYarat() {
        JMenuBar menüÇubuðu = new JMenuBar();
        menüÇubuðu.add (iþlevsizMenüYarat ("Dosya"));
        menüÇubuðu.add (iþlevsizMenüYarat ("Düzenle"));
        menüÇubuðu.add (iþlevsizMenüYarat ("Araþtýr"));
        menüÇubuðu.add (iþlevsizMenüYarat ("Göster"));
        menüÇubuðu.add (iþlevsizMenüYarat ("Aletler"));
        menüÇubuðu.add (iþlevsizMenüYarat ("Yardým"));

        JMenu gösteri = new JMenu ("Gösteri");
        gösteri.setMnemonic (KeyEvent.VK_G); // Alt_G
        menüÇubuðu.add (gösteri);

        yönetimBirimi = new JCheckBoxMenuItem ("Üst-Seviye AktarmaYönetimini Kullan");
        yönetimBirimi.setMnemonic (KeyEvent.VK_A); // Alt_A
        gösteri.add (yönetimBirimi);

        silBirimi = new JCheckBoxMenuItem ("AktarmaYönetimini Listeden ve Metinden Sil");
        silBirimi.setMnemonic (KeyEvent.VK_S);
        gösteri.add (silBirimi); // Alt_S

        kopyaBirimi = new JCheckBoxMenuItem ("COPY/Kopya Hareketini Kullan");
        kopyaBirimi.setMnemonic (KeyEvent.VK_K); // Alt_K
        gösteri.add (kopyaBirimi);

        return menüÇubuðu;
    } // iþlevsizMenüÇubuðunuYarat() metodu sonu...
    
    private JMenu iþlevsizMenüYarat (String dizge) {
        JMenu menü = new JMenu (dizge);
        JMenuItem birim = new JMenuItem ("[Boþ]");
        birim.setEnabled (false); // Etkisiz...
        menü.add (birim);
        return menü;
    } // iþlevsizMenüYarat(..) metodu sonu...

    private JToolBar iþlevsizAletÇubuðunuYarat() {
        JToolBar aletÇubuðu = new JToolBar();
        JButton düðme;
        düðme = new JButton ("Yeni");
        düðme.setRequestFocusEnabled (false); // Odaksýz...
        aletÇubuðu.add (düðme);
        düðme = new JButton ("Aç");
        düðme.setRequestFocusEnabled (false);
        aletÇubuðu.add (düðme);
        düðme = new JButton ("Sakla");
        düðme.setRequestFocusEnabled (false);
        aletÇubuðu.add (düðme);
        düðme = new JButton ("Yaz");
        düðme.setRequestFocusEnabled (false);
        aletÇubuðu.add (düðme);
        düðme = new JButton ("Öngöster");
        düðme.setRequestFocusEnabled (false);
        aletÇubuðu.add (düðme);
        aletÇubuðu.setFloatable (false); // Sabit...
        return aletÇubuðu;
    } // iþlevsizAletÇubuðunuYarat() metodu sonu...
    
    private class Döküman extends InternalFrameAdapter implements ActionListener {
        String dosyaAdý;
        JInternalFrame içÇerçeve;
        TransferHandler aktarmaYönetimi;
        JTextArea metinAlaný;

        public Döküman (File dosya) {// Kurucu1...
            this.dosyaAdý = dosya.getName();
            try {baþlat (dosya.toURI().toURL());
            }catch (MalformedURLException ist) {ist.printStackTrace();}
        } // Döküman(..) kurucu1 sonu...
        
        public Döküman (String dosyaAdý) {// Kurucu2...
            this.dosyaAdý = dosyaAdý;
            baþlat (getClass().getResource (dosyaAdý));
        } // Döküman(..) kurucu2 sonu...

        private void baþlat (URL yurel) {
            içÇerçeve = new JInternalFrame (dosyaAdý);
            içÇerçeve.addInternalFrameListener (this); // Dinleyiciye duyarlý...
            listeModeli.add (listeModeli.size(), this);
            metinAlaný = new JTextArea();
            metinAlaný.setMargin (new Insets (5, 5, 5, 5));

            try {BufferedReader okuyucu = new BufferedReader (new InputStreamReader (yurel.openStream()));
                String satýr;
                while ((satýr = okuyucu.readLine()) != null) {
                    metinAlaný.append (satýr);
                    metinAlaný.append ("\n");
                } // while döngüsü sonu...
                okuyucu.close();
            }catch (Exception ist) {ist.printStackTrace(); return;
            } // try-catch bloðu sonu...

            aktarmaYönetimi = metinAlaný.getTransferHandler();
            metinAlaný.setFont (new Font ("monospaced", Font.PLAIN, 12));
            metinAlaný.setCaretPosition (0);
            metinAlaný.setDragEnabled (true); // Sürükle ve araya býrak aktif...
            metinAlaný.setDropMode(DropMode.INSERT);
            içÇerçeve.getContentPane().add (new JScrollPane (metinAlaný));
            masaüstüPanosu.add (içÇerçeve);
            içÇerçeve.show();

            if (KÜÇÜK_EBATLA) içÇerçeve.setSize (300, 200);
            else içÇerçeve.setSize (400, 300);

            içÇerçeve.setResizable (true);
            içÇerçeve.setClosable (true);
            içÇerçeve.setIconifiable (true);
            içÇerçeve.setMaximizable (true);
            içÇerçeve.setLocation (sol, üst);
            artýr();
            SwingUtilities.invokeLater (new Runnable() {public void run() {seç();}});
            silBirimi.addActionListener (this); // Dinleyiciye duyarlý...
            yönetimiSil();
        } // baþlat(..) metodu sonu...

        private void artýr() {
            sol += 30;
            üst += 30;
            if (üst == 150) üst = 0;
        } // artýr() metodu sonu...

        public void seç() {
            try {içÇerçeve.toFront();
                içÇerçeve.setSelected (true);
            }catch (java.beans.PropertyVetoException ist) {}
        } // seç() metodu sonu...

        public void yönetimiSil() {
            if (silBirimi.isSelected()) metinAlaný.setTransferHandler (null);
            else metinAlaný.setTransferHandler (aktarmaYönetimi);
        } // yönetimiSil() metodu sonu...

        public void internalFrameClosing (InternalFrameEvent olay) {
            listeModeli.removeElement (this);
            silBirimi.removeActionListener (this);
        } // internalFrameClosing(..) hazýr metodu sonu...

        public void internalFrameOpened (InternalFrameEvent olay) {
            int endeks = listeModeli.indexOf (this);
            liste.getSelectionModel().setSelectionInterval (endeks, endeks);
        } // internalFrameOpened(..) hazýr metodu sonu...

        public void internalFrameActivated (InternalFrameEvent olay) {
            int endeks = listeModeli.indexOf (this);
            liste.getSelectionModel().setSelectionInterval (endeks, endeks);
        } // internalFrameActivated(..) hazýr metodu sonu...

        public String toString() {return dosyaAdý;}
        public void actionPerformed (ActionEvent olay) {yönetimiSil();}
    } // Döküman sýnýfý sonu...

    private static void yaratVeGösterGUI (String[] argümanlar) {
        J5h_8 gösteri = new J5h_8 (argümanlar); // Kurucuyu çaðýrýr...
        gösteri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        if (KÜÇÜK_EBATLA) gösteri.setSize (493, 307);
        else gösteri.setSize (800, 600);
        gösteri.setLocationRelativeTo (null);
        gösteri.setVisible (true);
        gösteri.liste.requestFocus();
    } // yaratVeGösterGUI(..) metodu sonu...

    public static void main (final String[] args) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {} // Aldýrma, namevcutsa varsayýlý kullanýlýr...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeGösterGUI (args);
            } // run() hazýr sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_8 sýnýfý sonu...

/* Çýktý:
**  >java J5h_8 J5h_8_XML.xml J5h_8_XML.xml J5h_1.java mny1.txt J5h_8.java **
     java.lang.NullPointerExceptionat J5h_8$Döküman.baþlat(J5h_8.java:204)
*/