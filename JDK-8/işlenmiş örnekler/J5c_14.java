// J5c_14.java: FileChooserDemo (DosyaSeçiciGösterimi) örneði.

import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Color;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.io.File;

/* Þu resim dosyalarý gerekiyor:
 * resim/Aç.gif
 * resim/Sakla.gif
 */
public class J5c_14 extends JPanel implements ActionListener {
    static private final String yeniSatýr = "\n";
    JButton açDüðmesi, saklaDüðmesi;
    JTextArea metinAlaný;
    JFileChooser dosyaSeçici;

    public J5c_14() {// Kurucu...
        super (new BorderLayout());

        // Evvela metinAlaný yaratalým, zira dinleyici aç/sakla sonuçlarýný oraya beyanlayacak...
        metinAlaný = new JTextArea (5,20);
        metinAlaný.setMargin (new Insets (5,5,5,5));
        metinAlaný.setEditable (false); // Metin alanýna müdahele etme...
        metinAlaný.setBackground (new Color (0.5f, 0.5f, 1.0f)); // HSB/HugeSaturationBrightness/RenkDoygunlukParlaklýk
        JScrollPane metinAlanlýKaydýrmaPaneli = new JScrollPane (metinAlaný);

        // Varsayýlý kipi FILES_ONLY olan dosya seçicimizi yaratalým...
        dosyaSeçici = new JFileChooser();
        //dosyaSeçici.setFileSelectionMode (JFileChooser.DIRECTORIES_ONLY);
        //dosyaSeçici.setFileSelectionMode (JFileChooser.FILES_AND_DIRECTORIES);

        // Ýkonlu Aç düðmesi yaratalým...
        açDüðmesi = new JButton ("Yeni bir Dosya Aç", resimÝkonuYarat ("resim/Aç.gif"));
        açDüðmesi.addActionListener (this);
        açDüðmesi.setBackground (new Color (0.9f, 0.5f, 0.9f));

        // Ýkonlu Sakla düðmesi yaratalým...
        saklaDüðmesi = new JButton ("Mevcut Dosyayý Sakla", resimÝkonuYarat ("resim/Sakla.gif"));
        saklaDüðmesi.addActionListener (this);
        saklaDüðmesi.setBackground (new Color (0.7f, 0.5f, 0.9f));

        // Serim amaçlý, düðmeleri ayrý bir panele koyalým...
        JPanel düðmePaneli = new JPanel(); // Varsayýlý FlowLayout kullanýlmakta...
        düðmePaneli.add (açDüðmesi);
        düðmePaneli.add (saklaDüðmesi);
        düðmePaneli.setBackground (new Color (0.0f, 0.0f, 0.0f));

        // Düðme panelini ve kaydýrmalý metin alanýný çerçevemize ekleyelim...
        add (düðmePaneli, BorderLayout.PAGE_START);
        add (metinAlanlýKaydýrmaPaneli, BorderLayout.CENTER);
    } // J5c_14() kurucusu sonu...

    public void actionPerformed (ActionEvent olay) {
        // Dosya aç aksiyonunu yönetelim...
        if (olay.getSource() == açDüðmesi) {
            int dönüþDeðeri = dosyaSeçici.showOpenDialog (J5c_14.this);

            if (dönüþDeðeri == JFileChooser.APPROVE_OPTION) {
                File dosya = dosyaSeçici.getSelectedFile();
                // Buraya açýlan dosya içeriklerini okuyup yansýtacak kodlamalarý koyabilirsiniz...
                metinAlaný.append ("[" + dosya.getName() + "] dosyasý açýlýyor (dese de inanma)!.." + yeniSatýr);
            }else metinAlaný.append ("Aç komutu kullanýcý tarafýndan iptal edildi." + yeniSatýr);
            metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());

        // Dosya sakla aksiyonunu yönetelim...
        }else if (olay.getSource() == saklaDüðmesi) {
            int dönüþDeðeri = dosyaSeçici.showSaveDialog (J5c_14.this);
            if (dönüþDeðeri == JFileChooser.APPROVE_OPTION) {
                File dosya = dosyaSeçici.getSelectedFile();
                // Buraya içerikleri ayný/farklý adlý dosyaya saklayacak kodlamalarý koyabilirsiniz...
                metinAlaný.append ("[" + dosya.getName() + "] dosyasý saklanýyor (dese de inanma)!.." + yeniSatýr);
            }else metinAlaný.append ("Sakla komutu kullanýcý tarafýndan iptal edildi." + yeniSatýr);
            metinAlaný.setCaretPosition (metinAlaný.getDocument().getLength());
        } // dýþ if-else kararý sonu...
    } // actionPerformed(..) hazýr metodu sonu...

    protected static ImageIcon resimÝkonuYarat (String yol) {
        java.net.URL resimYureli = J5c_14.class.getResource (yol);
        if (resimYureli != null) return new ImageIcon (resimYureli);
        else {System.err.println ("[" + yol + "] adlý resim dosyasýný bulamadým!"); return null;}
    } // resimÝkonuYarat(..) metodu sonu...

    private static void yaratVeGösterGUI() {
        JFrame çerçeve = new JFrame ("Dosya Seçici Gösterim");
        çerçeve.setDefaultCloseOperation (3); // 3=JFrame.EXIT_ON_CLOSE

        // extends JPanel'li içeriði penceremize ekleyelim...
        çerçeve.add (new J5c_14());

        çerçeve.pack();
        çerçeve.setVisible (true);
    } // yaratVeGösterGUI() metodu sonu...

    public static void main (String[] args) {
        SwingUtilities.invokeLater (new Runnable() {
            public void run() {
                // GörHisset metal'ýn bold yazý fonunu kapatalým...
                UIManager.put ("swing.boldMetal", Boolean.FALSE); 
                yaratVeGösterGUI();
        }}); // Sw.. ifadesi sonu...
    } // main(..) metodu sonu...
} // J5c_14 sýnýfý sonu...