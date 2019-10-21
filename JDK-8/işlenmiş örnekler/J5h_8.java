// J5h_8.java: TopLevelTransferHandlerDemo (�stSeviyeAktarmaY�netimiG�sterisi) �rne�i.

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
    private static boolean K���K_EBATLA = false;
    private JDesktopPane masa�st�Panosu = new JDesktopPane();
    private DefaultListModel listeModeli = new DefaultListModel();
    private JList liste = new JList(listeModeli);
    private int sol;
    private int �st;
    private JCheckBoxMenuItem kopyaBirimi;
    private JCheckBoxMenuItem silBirimi;
    private JCheckBoxMenuItem y�netimBirimi;
    private TransferHandler y�netim = new TransferHandler() {
        public boolean canImport (TransferHandler.TransferSupport destek) {
            if (! destek.isDataFlavorSupported (DataFlavor.javaFileListFlavor)) return false;
            if (kopyaBirimi.isSelected()) {
                boolean kopyaDeste�i = (COPY & destek.getSourceDropActions()) == COPY;
                if (! kopyaDeste�i) return false;
                destek.setDropAction (COPY);
            } // if karar� sonu...
            return true;
        } // canImport(..) haz�r fonksiyonu sonu...

        public boolean importData (TransferHandler.TransferSupport destek) {
            if (! canImport (destek)) return false;
            Transferable aktar�labilir = destek.getTransferable();
            try {List<File> liste = (List<File>)aktar�labilir.getTransferData (DataFlavor.javaFileListFlavor);
                for (File dosya : liste) new D�k�man (dosya);
            }catch (UnsupportedFlavorException ist) {return false;
            }catch (IOException ist) {return false;}
            return true;
        } // importData(..) haz�r fonksiyonu sonu...
    }; // pri.. s�n�f tip de�i�keni ifadesi sonu...

    public J5h_8 (String[] arg�manlar) {// Kurucu...
        super ("�st Seviye Aktarma Y�netimi G�sterisi");
        setJMenuBar (i�levsizMen��ubu�unuYarat());
        getContentPane().add (i�levsizAlet�ubu�unuYarat(), BorderLayout.NORTH);

        JSplitPane b�l�ml�Pano = new JSplitPane (JSplitPane.HORIZONTAL_SPLIT, liste, masa�st�Panosu);
        b�l�ml�Pano.setDividerLocation (120); // B�lmeleraras� paravan�n ilk konumu...
        getContentPane().add (b�l�ml�Pano);

        // Komut iletisinden girilecek dosyalar� a��p isimlerini liste, i�eriklerini masa�st� pano b�lmelerine kural�m...
        for (int i=0; i < arg�manlar.length; i++) new D�k�man (arg�manlar[i]);

        liste.getSelectionModel().setSelectionMode (ListSelectionModel.SINGLE_SELECTION);

        liste.addListSelectionListener (new ListSelectionListener() {
            public void valueChanged (ListSelectionEvent olay) {
                if (olay.getValueIsAdjusting()) return;
                D�k�man de�er = (D�k�man)liste.getSelectedValue();
                if (de�er != null) de�er.se�();
             } // addListSelectionListener(..) haz�r metodu sonu...
        }); // lis.. ifadesi sonu...

        final TransferHandler aktarmay�Y�net = liste.getTransferHandler();

        silBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (silBirimi.isSelected()) liste.setTransferHandler (null);
                else liste.setTransferHandler (aktarmay�Y�net);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // sil.. ifadesi sonu...

        y�netimBirimi.addActionListener (new ActionListener() {
            public void actionPerformed (ActionEvent olay) {
                if (y�netimBirimi.isSelected()) setTransferHandler (y�netim);
                else setTransferHandler (null);
            } // actionPerformed(..) haz�r metodu sonu...
        }); // y�n.. ifadesi sonu...
        masa�st�Panosu.setTransferHandler(y�netim);
    } // J5h_8(..) kurucusu sonu...

    private JMenuBar i�levsizMen��ubu�unuYarat() {
        JMenuBar men��ubu�u = new JMenuBar();
        men��ubu�u.add (i�levsizMen�Yarat ("Dosya"));
        men��ubu�u.add (i�levsizMen�Yarat ("D�zenle"));
        men��ubu�u.add (i�levsizMen�Yarat ("Ara�t�r"));
        men��ubu�u.add (i�levsizMen�Yarat ("G�ster"));
        men��ubu�u.add (i�levsizMen�Yarat ("Aletler"));
        men��ubu�u.add (i�levsizMen�Yarat ("Yard�m"));

        JMenu g�steri = new JMenu ("G�steri");
        g�steri.setMnemonic (KeyEvent.VK_G); // Alt_G
        men��ubu�u.add (g�steri);

        y�netimBirimi = new JCheckBoxMenuItem ("�st-Seviye AktarmaY�netimini Kullan");
        y�netimBirimi.setMnemonic (KeyEvent.VK_A); // Alt_A
        g�steri.add (y�netimBirimi);

        silBirimi = new JCheckBoxMenuItem ("AktarmaY�netimini Listeden ve Metinden Sil");
        silBirimi.setMnemonic (KeyEvent.VK_S);
        g�steri.add (silBirimi); // Alt_S

        kopyaBirimi = new JCheckBoxMenuItem ("COPY/Kopya Hareketini Kullan");
        kopyaBirimi.setMnemonic (KeyEvent.VK_K); // Alt_K
        g�steri.add (kopyaBirimi);

        return men��ubu�u;
    } // i�levsizMen��ubu�unuYarat() metodu sonu...
    
    private JMenu i�levsizMen�Yarat (String dizge) {
        JMenu men� = new JMenu (dizge);
        JMenuItem birim = new JMenuItem ("[Bo�]");
        birim.setEnabled (false); // Etkisiz...
        men�.add (birim);
        return men�;
    } // i�levsizMen�Yarat(..) metodu sonu...

    private JToolBar i�levsizAlet�ubu�unuYarat() {
        JToolBar alet�ubu�u = new JToolBar();
        JButton d��me;
        d��me = new JButton ("Yeni");
        d��me.setRequestFocusEnabled (false); // Odaks�z...
        alet�ubu�u.add (d��me);
        d��me = new JButton ("A�");
        d��me.setRequestFocusEnabled (false);
        alet�ubu�u.add (d��me);
        d��me = new JButton ("Sakla");
        d��me.setRequestFocusEnabled (false);
        alet�ubu�u.add (d��me);
        d��me = new JButton ("Yaz");
        d��me.setRequestFocusEnabled (false);
        alet�ubu�u.add (d��me);
        d��me = new JButton ("�ng�ster");
        d��me.setRequestFocusEnabled (false);
        alet�ubu�u.add (d��me);
        alet�ubu�u.setFloatable (false); // Sabit...
        return alet�ubu�u;
    } // i�levsizAlet�ubu�unuYarat() metodu sonu...
    
    private class D�k�man extends InternalFrameAdapter implements ActionListener {
        String dosyaAd�;
        JInternalFrame i��er�eve;
        TransferHandler aktarmaY�netimi;
        JTextArea metinAlan�;

        public D�k�man (File dosya) {// Kurucu1...
            this.dosyaAd� = dosya.getName();
            try {ba�lat (dosya.toURI().toURL());
            }catch (MalformedURLException ist) {ist.printStackTrace();}
        } // D�k�man(..) kurucu1 sonu...
        
        public D�k�man (String dosyaAd�) {// Kurucu2...
            this.dosyaAd� = dosyaAd�;
            ba�lat (getClass().getResource (dosyaAd�));
        } // D�k�man(..) kurucu2 sonu...

        private void ba�lat (URL yurel) {
            i��er�eve = new JInternalFrame (dosyaAd�);
            i��er�eve.addInternalFrameListener (this); // Dinleyiciye duyarl�...
            listeModeli.add (listeModeli.size(), this);
            metinAlan� = new JTextArea();
            metinAlan�.setMargin (new Insets (5, 5, 5, 5));

            try {BufferedReader okuyucu = new BufferedReader (new InputStreamReader (yurel.openStream()));
                String sat�r;
                while ((sat�r = okuyucu.readLine()) != null) {
                    metinAlan�.append (sat�r);
                    metinAlan�.append ("\n");
                } // while d�ng�s� sonu...
                okuyucu.close();
            }catch (Exception ist) {ist.printStackTrace(); return;
            } // try-catch blo�u sonu...

            aktarmaY�netimi = metinAlan�.getTransferHandler();
            metinAlan�.setFont (new Font ("monospaced", Font.PLAIN, 12));
            metinAlan�.setCaretPosition (0);
            metinAlan�.setDragEnabled (true); // S�r�kle ve araya b�rak aktif...
            metinAlan�.setDropMode(DropMode.INSERT);
            i��er�eve.getContentPane().add (new JScrollPane (metinAlan�));
            masa�st�Panosu.add (i��er�eve);
            i��er�eve.show();

            if (K���K_EBATLA) i��er�eve.setSize (300, 200);
            else i��er�eve.setSize (400, 300);

            i��er�eve.setResizable (true);
            i��er�eve.setClosable (true);
            i��er�eve.setIconifiable (true);
            i��er�eve.setMaximizable (true);
            i��er�eve.setLocation (sol, �st);
            art�r();
            SwingUtilities.invokeLater (new Runnable() {public void run() {se�();}});
            silBirimi.addActionListener (this); // Dinleyiciye duyarl�...
            y�netimiSil();
        } // ba�lat(..) metodu sonu...

        private void art�r() {
            sol += 30;
            �st += 30;
            if (�st == 150) �st = 0;
        } // art�r() metodu sonu...

        public void se�() {
            try {i��er�eve.toFront();
                i��er�eve.setSelected (true);
            }catch (java.beans.PropertyVetoException ist) {}
        } // se�() metodu sonu...

        public void y�netimiSil() {
            if (silBirimi.isSelected()) metinAlan�.setTransferHandler (null);
            else metinAlan�.setTransferHandler (aktarmaY�netimi);
        } // y�netimiSil() metodu sonu...

        public void internalFrameClosing (InternalFrameEvent olay) {
            listeModeli.removeElement (this);
            silBirimi.removeActionListener (this);
        } // internalFrameClosing(..) haz�r metodu sonu...

        public void internalFrameOpened (InternalFrameEvent olay) {
            int endeks = listeModeli.indexOf (this);
            liste.getSelectionModel().setSelectionInterval (endeks, endeks);
        } // internalFrameOpened(..) haz�r metodu sonu...

        public void internalFrameActivated (InternalFrameEvent olay) {
            int endeks = listeModeli.indexOf (this);
            liste.getSelectionModel().setSelectionInterval (endeks, endeks);
        } // internalFrameActivated(..) haz�r metodu sonu...

        public String toString() {return dosyaAd�;}
        public void actionPerformed (ActionEvent olay) {y�netimiSil();}
    } // D�k�man s�n�f� sonu...

    private static void yaratVeG�sterGUI (String[] arg�manlar) {
        J5h_8 g�steri = new J5h_8 (arg�manlar); // Kurucuyu �a��r�r...
        g�steri.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE
        if (K���K_EBATLA) g�steri.setSize (493, 307);
        else g�steri.setSize (800, 600);
        g�steri.setLocationRelativeTo (null);
        g�steri.setVisible (true);
        g�steri.liste.requestFocus();
    } // yaratVeG�sterGUI(..) metodu sonu...

    public static void main (final String[] args) {
        try {UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName());
        }catch (Exception ist) {} // Ald�rma, namevcutsa varsay�l� kullan�l�r...
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                UIManager.put ("swing.boldMetal", Boolean.FALSE);
                yaratVeG�sterGUI (args);
            } // run() haz�r sicim metodu sonu...
        }); // Swi.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5h_8 s�n�f� sonu...

/* ��kt�:
**  >java J5h_8 J5h_8_XML.xml J5h_8_XML.xml J5h_1.java mny1.txt J5h_8.java **
     java.lang.NullPointerExceptionat J5h_8$D�k�man.ba�lat(J5h_8.java:204)
*/