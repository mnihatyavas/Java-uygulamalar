// J4e_2x.java: ScriptRunnerApplication (JS�al��t�rmaUygulamas�) alt-�rne�i.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.util.Optional;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

public class J4e_2x {//extends javafx.application.Application
    private DefaultComboBoxModel<ScriptEngine> languagesModel;
    private JTextArea scriptContents;
    private JTextArea scriptResults;

    //public void start(Stage s) {}

    void createGUI() {
        Box buttonBox = Box.createHorizontalBox();
        
        JButton loadButton = new JButton("Load");
        loadButton.addActionListener(this::loadScript);
        buttonBox.add(loadButton);

        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(this::saveScript);
        buttonBox.add(saveButton);
        
        JButton executeButton = new JButton("Execute");
        executeButton.addActionListener(this::executeScript);
        buttonBox.add(executeButton);
        
        languagesModel = new DefaultComboBoxModel();

        ScriptEngineManager sem = new ScriptEngineManager();
        for (ScriptEngineFactory sef : sem.getEngineFactories()) {
            languagesModel.addElement(sef.getScriptEngine());
        }       
        
        JComboBox<ScriptEngine> languagesCombo = new JComboBox<>(languagesModel);
        JLabel languageLabel = new JLabel();
        languagesCombo.setRenderer((JList<? extends ScriptEngine> list, 
                ScriptEngine se, int index, boolean isSelected, 
                boolean cellHasFocus)
        -> {
            ScriptEngineFactory sef = se.getFactory();
            languageLabel.setText(
                sef.getEngineName() + " - " + 
                sef.getLanguageName() + 
                " (*." + String.join(", *.", sef.getExtensions()) + ")"
            );
            return languageLabel;
        });
        buttonBox.add(Box.createHorizontalGlue());
        buttonBox.add(languagesCombo);  
        
        scriptContents = new JTextArea();
        scriptContents.setRows(8);
        scriptContents.setColumns(40);
        
        scriptResults = new JTextArea();
        scriptResults.setEditable(false);
        scriptResults.setRows(8);
        scriptResults.setColumns(40);

        JSplitPane jsp = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scriptContents, scriptResults);
        
        JFrame frame = new JFrame("Script Runner");
        frame.add(buttonBox, BorderLayout.NORTH);
        frame.add(jsp, BorderLayout.CENTER);
        
        frame.pack();
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        frame.setVisible(true);
    }
    
    private void loadScript(ActionEvent ae) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(scriptContents);
        if (jfc.getSelectedFile() != null) {
            loadScript(jfc.getSelectedFile());
        }
    }
    
    void loadScript(File f) {
        try {        
            scriptContents.setText(String.join("\n", Files.readAllLines(f.toPath())));

            String name = f.getName();
            int lastDot = name.lastIndexOf('.');
            if (lastDot >= 0) {
                String extension = name.substring(lastDot + 1);
                for (int i = 0; i < languagesModel.getSize(); i++) {
                    ScriptEngine se = languagesModel.getElementAt(i);
                    if (se.getFactory().getExtensions().indexOf(extension) >= 0) {
                        languagesModel.setSelectedItem(se);
                        break;
                    }
                }
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(scriptContents, ex.getMessage(), "Error Loading Script", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void saveScript(ActionEvent ae) {
        JFileChooser jfc = new JFileChooser();
        jfc.showOpenDialog(scriptContents);
        File f = jfc.getSelectedFile();
        if (f != null) {
            try {
                Files.write(f.toPath(), scriptContents.getText().getBytes());
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(scriptContents, ex.getMessage(), "Error Saving Script", JOptionPane.ERROR_MESSAGE);
            }
        }
        
    }
    
    private void executeScript(ActionEvent ae) {
        ScriptEngine se = (ScriptEngine) languagesModel.getSelectedItem();
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Writer w = new OutputStreamWriter(baos);) 
        {
            se.getContext().setWriter(w);
            
            Optional<Object> result = Optional.ofNullable(se.eval(scriptContents.getText()));
            
            scriptResults.setText(baos.toString() + "\n>>>>>>\n" + 
                    result.orElse("-null-").toString());
        } catch (Exception e) {
            e.printStackTrace(System.out);
            scriptResults.setText(e.getClass().getName() + " - " + e.getMessage());
        }
    }

    public static void main(String [] args) {
        SwingUtilities.invokeLater(() -> {
            J4e_2x app = new J4e_2x();
            app.createGUI();
            if (args.length > 0) {
                File f = new File(args[0]);
                if (f.isFile()) {
                    app.loadScript(f);
                }
            }
        });
    } // main(..) metodu sonu..
} // J4e_2x s�n�f� sonu...