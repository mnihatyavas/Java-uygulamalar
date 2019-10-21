// J4c_2x2.java: FileHandler (DosyaYöneticisi) alt-örneði.

// add javaws.jar to the classpath during compilation: C:\Program Files\Java\jdk1.8.0_121\jre\lib
import javax.jnlp.FileOpenService; // jnlp mevcut deðil...
import javax.jnlp.FileSaveService;
import javax.jnlp.FileContents;
import javax.jnlp.ServiceManager;
import javax.jnlp.UnavailableServiceException;
import java.io.*;

public class J4c_2x2 {

    static private FileOpenService fos = null;
    static private FileSaveService fss = null;
    static private FileContents fc = null;

    // retrieves a reference to the JNLP services
    private static synchronized void ilkÝþlemler() {
        if (fss != null) {
            return;
        }
        try {
            fos = (FileOpenService) ServiceManager.lookup("javax.jnlp.FileOpenService");
            fss = (FileSaveService) ServiceManager.lookup("javax.jnlp.FileSaveService");
        } catch (UnavailableServiceException e) {
            fos = null;
            fss = null;
        }
    } // ilkÝþlemler() metodu sonu...

    // displays open file dialog and reads selected file using FileOpenService
    public static String aç() {
        ilkÝþlemler();
        try {
            fc = fos.openFileDialog(null, null);
            return readFromFile(fc);
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
            return null;
        }
    } // aç() metodu sonu...

    // displays saveFileDialog and saves file using FileSaveService
    public static void sakla (String txt) {
        ilkÝþlemler();
        try {
            // Show save dialog if no name is already given
            if (fc == null) {
                fc = fss.saveFileDialog(null, null,
                        new ByteArrayInputStream(txt.getBytes()), null);
                // file saved, done
                return;
            }
            // use this only when filename is known
            if (fc != null) {
                writeToFile(txt, fc);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    } // sakla(..) metodu sonu...

    // displays saveAsFileDialog and saves file using FileSaveService
    public static void yeniSakla (String txt) {
        ilkÝþlemler();
        try {
            if (fc == null) {
                // If not already saved. Save-as is like save
                save(txt);
            } else {
                fc = fss.saveAsFileDialog(null, null, fc);
                save(txt);
            }
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    } // yeniSakla(..) metodu sonu...

    private static void writeToFile(String txt, FileContents fc) throws IOException {
        int sizeNeeded = txt.length() * 2;
        if (sizeNeeded > fc.getMaxLength()) {
            fc.setMaxLength(sizeNeeded);
        }
        BufferedWriter os = new BufferedWriter(new OutputStreamWriter(fc.getOutputStream(true)));
        os.write(txt);
        os.close();
    }

    private static String readFromFile(FileContents fc) throws IOException {
        if (fc == null) {
            return null;
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(fc.getInputStream()));
        StringBuffer sb = new StringBuffer((int) fc.getLength());
        String line = br.readLine();
        while (line != null) {
            sb.append(line);
            sb.append("\n");
            line = br.readLine();
        }
        br.close();
        return sb.toString();
    }
} // J4c_2x2 sýnýfý sonu...