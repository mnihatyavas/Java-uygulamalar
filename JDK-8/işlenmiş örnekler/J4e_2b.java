// J4e_2b.java: ScriptRunnerApplication (JSÇalýþtýrmaUygulamasý) örneði.

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.Box;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JFileChooser;
import javax.swing.SwingUtilities;

import java.io.File;
import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.io.IOException;

import java.nio.file.Files;

import java.util.Optional;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;

public class J4e_2b {
    private DefaultComboBoxModel<ScriptEngine> dilModeli;
    private JTextArea scriptÝçerikleri;
    private JTextArea scriptSonuçlarý;

    void yaratVeGösterGUI() {
        Box butonKutusu = Box.createHorizontalBox();

        JButton yükleButonu = new JButton ("Yükle");
        yükleButonu.addActionListener (this::taslaðýYükle);
        butonKutusu.add (yükleButonu);

        JButton saklaButonu = new JButton ("Sakla");
        saklaButonu.addActionListener (this::taslaðýSakla);
        butonKutusu.add (saklaButonu);
        
        JButton çalýþtýrButonu = new JButton ("Çalýþtýr");
        çalýþtýrButonu.addActionListener (this::taslaðýÇalýþtýr);
        butonKutusu.add (çalýþtýrButonu);

        dilModeli = new DefaultComboBoxModel();

        ScriptEngineManager sem = new ScriptEngineManager();
        for (ScriptEngineFactory sef : sem.getEngineFactories())
            dilModeli.addElement (sef.getScriptEngine());

        JComboBox<ScriptEngine> comboDilleri = new JComboBox<>(dilModeli);
        JLabel dilEtiketi = new JLabel();
        comboDilleri.setRenderer ((
                JList<? extends ScriptEngine> liste, 
                ScriptEngine se,
                int endeks,
                boolean seçildiMi, 
                boolean hücreyeOdaklýMý) -> {
            ScriptEngineFactory sef = se.getFactory();
            dilEtiketi.setText (
                sef.getEngineName() + " - " + 
                sef.getLanguageName() + 
                " (*." + String.join (", *.", sef.getExtensions()) + ")"
            ); // dil.. ifadesi sonu...
            return dilEtiketi;
        }); // com.. ifadesi sonu...
        butonKutusu.add (Box.createHorizontalGlue());
        butonKutusu.add (comboDilleri);  
        
        scriptÝçerikleri = new JTextArea();
        scriptÝçerikleri.setRows (8);
        scriptÝçerikleri.setColumns (40);

        scriptSonuçlarý = new JTextArea();
        scriptSonuçlarý.setEditable (false);
        scriptSonuçlarý.setRows (8);
        scriptSonuçlarý.setColumns (40);

        JSplitPane jsp = new JSplitPane (JSplitPane.VERTICAL_SPLIT, scriptÝçerikleri, scriptSonuçlarý);

        JFrame çerçeve = new JFrame ("Script Çalýþtýrýcýsý");
        çerçeve.add (butonKutusu, BorderLayout.NORTH);
        çerçeve.add (jsp, BorderLayout.CENTER);

        çerçeve.pack();
        çerçeve.addWindowListener (new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent olay) {System.exit(0);}
        }); // çer.. ifadesi sonu...
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    private void taslaðýYükle (ActionEvent olay) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog (scriptÝçerikleri);
        if (jfc.getSelectedFile() != null) taslaðýYükle (jfc.getSelectedFile());
    } // taslaðýYükle(..) metodu sonu...

    void taslaðýYükle (File dosya) {
        try {scriptÝçerikleri.setText (String.join ("\n", Files.readAllLines (dosya.toPath())));

            String adý = dosya.getName();
            int sonNokta = adý.lastIndexOf ('.');
            if (sonNokta >= 0) {
                String uzantý = adý.substring (sonNokta + 1);
                for (int i = 0; i < dilModeli.getSize(); i++) {
                    ScriptEngine se = dilModeli.getElementAt (i);
                    if (se.getFactory().getExtensions().indexOf (uzantý) >= 0) {
                        dilModeli.setSelectedItem (se);
                        break;
                    } // if kararý sonu...
                } // for döngüsü sonu...
            } // if kararý sonu...
        }catch (IOException ist) {
            JOptionPane.showMessageDialog (scriptÝçerikleri, ist.getMessage(), "Script Yükleme Hatasý", JOptionPane.ERROR_MESSAGE);
        } // try-catch bloðu sonu...
    } // taslaðýYükle(..) metodu sonu...

    private void taslaðýSakla (ActionEvent olay) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog (scriptÝçerikleri);
        File dosya = jfc.getSelectedFile();
        if (dosya != null) {
            try {Files.write (dosya.toPath(), scriptÝçerikleri.getText().getBytes());
            }catch (IOException ist) {
                JOptionPane.showMessageDialog (scriptÝçerikleri, ist.getMessage(), "Script Saklama Hatasý", JOptionPane.ERROR_MESSAGE);
            } // try-catch bloðu sonu...
        } // if kararý sonu...
    } // taslaðýSakla(..) metodu sonu...

    private void taslaðýÇalýþtýr (ActionEvent olay) {
        ScriptEngine se = (ScriptEngine) dilModeli.getSelectedItem();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Writer w = new OutputStreamWriter (baos);) {
            se.getContext().setWriter (w);

            Optional<Object> sonuç = Optional.ofNullable (se.eval (scriptÝçerikleri.getText()));

            scriptSonuçlarý.setText (baos.toString() + "\n>>>>>>\n" + 
                sonuç.orElse ("-null-").toString());
        }catch (Exception ist) {
            ist.printStackTrace (System.out);
            scriptSonuçlarý.setText (ist.getClass().getName() + " - " + ist.getMessage());
        } // try-catch bloðu sonu...
    } // taslaðýÇalýþtýr(..) metodu sonu...

    public static void main (String [] args) {
        SwingUtilities.invokeLater(() -> {
            J4e_2b uygulama = new J4e_2b();
            uygulama.yaratVeGösterGUI();
            if (args.length > 0) {
                File dosya = new File (args[0]);
                if (dosya.isFile()) uygulama.taslaðýYükle (dosya);
            } // if-arg.. kararý sonu...
        }); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu..
} // J4e_2b sýnýfý sonu...