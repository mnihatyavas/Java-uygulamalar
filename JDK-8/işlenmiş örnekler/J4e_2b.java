// J4e_2b.java: ScriptRunnerApplication (JS�al��t�rmaUygulamas�) �rne�i.

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
    private JTextArea script��erikleri;
    private JTextArea scriptSonu�lar�;

    void yaratVeG�sterGUI() {
        Box butonKutusu = Box.createHorizontalBox();

        JButton y�kleButonu = new JButton ("Y�kle");
        y�kleButonu.addActionListener (this::tasla��Y�kle);
        butonKutusu.add (y�kleButonu);

        JButton saklaButonu = new JButton ("Sakla");
        saklaButonu.addActionListener (this::tasla��Sakla);
        butonKutusu.add (saklaButonu);
        
        JButton �al��t�rButonu = new JButton ("�al��t�r");
        �al��t�rButonu.addActionListener (this::tasla���al��t�r);
        butonKutusu.add (�al��t�rButonu);

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
                boolean se�ildiMi, 
                boolean h�creyeOdakl�M�) -> {
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
        
        script��erikleri = new JTextArea();
        script��erikleri.setRows (8);
        script��erikleri.setColumns (40);

        scriptSonu�lar� = new JTextArea();
        scriptSonu�lar�.setEditable (false);
        scriptSonu�lar�.setRows (8);
        scriptSonu�lar�.setColumns (40);

        JSplitPane jsp = new JSplitPane (JSplitPane.VERTICAL_SPLIT, script��erikleri, scriptSonu�lar�);

        JFrame �er�eve = new JFrame ("Script �al��t�r�c�s�");
        �er�eve.add (butonKutusu, BorderLayout.NORTH);
        �er�eve.add (jsp, BorderLayout.CENTER);

        �er�eve.pack();
        �er�eve.addWindowListener (new WindowAdapter() {
            @Override
            public void windowClosing (WindowEvent olay) {System.exit(0);}
        }); // �er.. ifadesi sonu...
        �er�eve.setVisible (true);
    } // yaratVeG�sterGUI() metodu sonu...

    private void tasla��Y�kle (ActionEvent olay) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog (script��erikleri);
        if (jfc.getSelectedFile() != null) tasla��Y�kle (jfc.getSelectedFile());
    } // tasla��Y�kle(..) metodu sonu...

    void tasla��Y�kle (File dosya) {
        try {script��erikleri.setText (String.join ("\n", Files.readAllLines (dosya.toPath())));

            String ad� = dosya.getName();
            int sonNokta = ad�.lastIndexOf ('.');
            if (sonNokta >= 0) {
                String uzant� = ad�.substring (sonNokta + 1);
                for (int i = 0; i < dilModeli.getSize(); i++) {
                    ScriptEngine se = dilModeli.getElementAt (i);
                    if (se.getFactory().getExtensions().indexOf (uzant�) >= 0) {
                        dilModeli.setSelectedItem (se);
                        break;
                    } // if karar� sonu...
                } // for d�ng�s� sonu...
            } // if karar� sonu...
        }catch (IOException ist) {
            JOptionPane.showMessageDialog (script��erikleri, ist.getMessage(), "Script Y�kleme Hatas�", JOptionPane.ERROR_MESSAGE);
        } // try-catch blo�u sonu...
    } // tasla��Y�kle(..) metodu sonu...

    private void tasla��Sakla (ActionEvent olay) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog (script��erikleri);
        File dosya = jfc.getSelectedFile();
        if (dosya != null) {
            try {Files.write (dosya.toPath(), script��erikleri.getText().getBytes());
            }catch (IOException ist) {
                JOptionPane.showMessageDialog (script��erikleri, ist.getMessage(), "Script Saklama Hatas�", JOptionPane.ERROR_MESSAGE);
            } // try-catch blo�u sonu...
        } // if karar� sonu...
    } // tasla��Sakla(..) metodu sonu...

    private void tasla���al��t�r (ActionEvent olay) {
        ScriptEngine se = (ScriptEngine) dilModeli.getSelectedItem();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
                Writer w = new OutputStreamWriter (baos);) {
            se.getContext().setWriter (w);

            Optional<Object> sonu� = Optional.ofNullable (se.eval (script��erikleri.getText()));

            scriptSonu�lar�.setText (baos.toString() + "\n>>>>>>\n" + 
                sonu�.orElse ("-null-").toString());
        }catch (Exception ist) {
            ist.printStackTrace (System.out);
            scriptSonu�lar�.setText (ist.getClass().getName() + " - " + ist.getMessage());
        } // try-catch blo�u sonu...
    } // tasla���al��t�r(..) metodu sonu...

    public static void main (String [] args) {
        SwingUtilities.invokeLater(() -> {
            J4e_2b uygulama = new J4e_2b();
            uygulama.yaratVeG�sterGUI();
            if (args.length > 0) {
                File dosya = new File (args[0]);
                if (dosya.isFile()) uygulama.tasla��Y�kle (dosya);
            } // if-arg.. karar� sonu...
        }); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu..
} // J4e_2b s�n�f� sonu...