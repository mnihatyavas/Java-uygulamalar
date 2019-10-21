// J9d_3.java: AttributedText (ÖzelliklendirilenMetin) örneði.

import java.applet.Applet;

import java.awt.Frame;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.font.TextAttribute;

import java.util.Hashtable;

public class J9d_3 extends Applet {
    private static String dizge;

    public void init() {repaint();}
    public void paint (Graphics g) {
        Font yazýFonu = new Font ("arial", Font.BOLD, 40);

        // arial, bold, 40 ve tesadüfi yazý rengi özellikli...
        Hashtable<TextAttribute, Object> kýymalýTabloListesi = new Hashtable<TextAttribute, Object>();
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi);
        g.setFont (yazýFonu);
        g.drawString (dizge, 45, 40);

        // Önceki özelliklere ilaveten, yeni yazý rengi ve kerning netlik özelliði...
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        kýymalýTabloListesi.put (TextAttribute.KERNING, TextAttribute.KERNING_ON);
        yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi);
        g.setFont (yazýFonu);
        g.drawString (dizge, 45, 90);

        // Önceki özelliklere ilaveten, yeni renk ve altçizgi özelliði...
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        kýymalýTabloListesi.put (TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi);
        g.setFont (yazýFonu); 
        g.drawString (dizge, 45, 140);

        // Önceki özelliklere ilaveten, yeni renk ve üstçizgi özelliði...
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, new Color ( (int)(Math.random()*156), (int)(Math.random()*156), (int)(Math.random()*156) ) );
        kýymalýTabloListesi.put (TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
        yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi);
        g.setFont (yazýFonu); 
        g.drawString (dizge, 45, 190);

        // Önceki özelliklere ilaveten, sadece mavi renk özelliði...
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, Color.BLUE);
        yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi);
        g.setFont (yazýFonu);
        g.drawString (dizge, 45, 240);

        // Önceki özellikleri kullanmadan, yeni yazý fonu/ebatý/yatýklýðý deðiþikliði, ve siyah renk özelliði...
        yazýFonu = new Font ("times new roman", Font.ITALIC, 12);
        kýymalýTabloListesi.put (TextAttribute.FOREGROUND, Color.black);
        //yazýFonu = yazýFonu.deriveFont (kýymalýTabloListesi); // Önceki özellikler alýnmýyor...
        g.setFont (yazýFonu);
        g.drawString ("Not: Yazý fonu renk deðiþikliði için çerçeve ebatýyla oynayýn...", 45, 270);
    } // paint(..) hazýr metodu sonu...

    public static void main (String[] args) {
        dizge = args.length > 0? args[0] : "ÇOOK DALGALI!";

        Frame çerçeve = new Frame ("Özelliklendirilmiþ Metin Numunesi");
        çerçeve.addWindowListener (new WindowAdapter() {public void windowClosing (WindowEvent olay) {System.exit (0);}});
        çerçeve.add ("Center",  new J9d_3()); // Parametresiz varsayýlý kuruculu sýnýf nesnesi yaratýlýr...
        çerçeve.setBackground (new Color ( (int)(Math.random()*100+156), (int)(Math.random()*100+156), (int)(Math.random()*100+156) )); // Tesadüfi açýk renkler...
        çerçeve.setSize (new Dimension (400, 320));
        çerçeve.setLocation (500, 50);
        çerçeve.setVisible (true);
    } // main(..) metodu sonu...
} // J9d_3 sýnýfý sonu...