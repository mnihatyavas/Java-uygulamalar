// J5g_1x.java: TestTheme (DenemeTemasý) alt-örneði.

import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;

public class J5g_1x extends DefaultMetalTheme {
    public String getName() {return "Toms";}

    private final ColorUIResource renk1 = new ColorUIResource ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) ); // Tüm renkler...
    private final ColorUIResource renk2 = new ColorUIResource ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) );
    private final ColorUIResource renk3 = new ColorUIResource ( (int)(Math.random()*256), (int)(Math.random()*256), (int)(Math.random()*256) );

    protected ColorUIResource getPrimary1() {return renk1;}
    protected ColorUIResource getPrimary2() {return renk2;}
    protected ColorUIResource getPrimary3() {return renk3;}
} // J5g_1x sýnýfý sonu...