// J4e_2a.java: ScriptRunnerApplicationMac (JSÇalýþtýrmaUygulamasýMotoru) örneði.

//import javafx.event.AppEvent;
import javafx.application.Application;
import java.io.File;
import java.util.List;
import javax.swing.SwingUtilities;

// Gereken alt-program: J4e_2x.java=ScriptRunnerApplication
public class J4e_2a extends J4e_2x {
    public static void main (String[] args) {
        SwingUtilities.invokeLater(() -> {
            J4e_2x app = new J4e_2x(); // J4e_2x=ScriptRunnerApplication
            app.createGUI();
            if (args.length > 0) {
                File f = new File(args[0]);
                if (f.isFile()) app.loadScript(f);
            } // if kararý sonu...
/*            
            Application.getApplication().setOpenFileHandler((AppEvent.OpenFilesEvent ofe) -> {
                List<File> files = ofe.getFiles();
                if (files != null && files.size() > 0) {
                    app.loadScript(files.get(0));
                }
            });
*/            
        }); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J4e_2a sýnýfý sonu...