// J3c_3.java: ForkBlur (�kifarkl�Kopyalama) �rne�i.

// Hen�z revizyonumu tamamlayamad�m...
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import javax.imageio.ImageIO;

/**
 * J3c_3 implements a simple horizontal image blur. It averages pixels in the
 * source array and writes them to a destination array. The sThreshold value
 * determines whether the blurring will be performed directly or split into two
 * tasks. Kopyada piksel ortalamas�ndan dolay� g�r�nt� oldukca belirsizle�ir.
 *
 * This is not the recommended way to blur images; it is only intended to
 * illustrate the use of the Fork/Join framework.
 */
public class J3c_3 extends RecursiveAction {

    private int[] mSource;
    private int mStart;
    private int mLength;
    private int[] mDestination;
    private int mBlurWidth = 15; // Processing window size, should be odd.

    public J3c_3 (int[] src, int start, int length, int[] dst) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
    } // J3c_3(..) kurucusu sonu...

    // Average pixels from source, write results into destination.
    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0), mSource.length - 1);
                int pixel = mSource[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0) / mBlurWidth;
            }

            // Re-assemble destination pixel.
            int dpixel = (0xff000000)
                    | (((int) rt) << 16)
                    | (((int) gt) << 8)
                    | (((int) bt) << 0);
            mDestination[index] = dpixel;
        }
    }
    protected static int sThreshold = 10000;

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }

        int split = mLength / 2;

        invokeAll(new J3c_3(mSource, mStart, split, mDestination),
                new J3c_3(mSource, mStart + split, mLength - split, 
                mDestination));
    }

    // Plumbing follows.
    public static void main (String[] args) throws Exception {
        String srcName = "lale.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);
        
        System.out.println ("Kaynak resim: [" + srcName + "]");
        
        BufferedImage blurredImage = blur(image);
        
        String dstName = "kopya_lale.jpg";
        File dstFile = new File(dstName);
        ImageIO.write(blurredImage, "jpg", dstFile);
        
        System.out.println ("Kopyalanan resim: [" + dstName + "]");
    } // main(..) metodu sonu...

    public static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Dizin ebat�: [" + src.length + "] adet piksel ortalamas�d�r.");
        System.out.println("Dizin ebad�: [" + sThreshold + "] e�ik de�erinden b�y�kse kopyalama 2 etapta yap�l�r.");

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println ("[" + Integer.toString(processors) + "] adet i�lemci"
                + (processors != 1 ? "ler " : " ")
                + "mevcuttur.");

        J3c_3 fb = new J3c_3(src, 0, src.length, dst);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println ("Resmin kopyalanmas� toplam: [" + (endTime - startTime) + "] milisaniyede tamamland�.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
} // J3c_3 s�n�f� sonu...

/* ��kt�:
**  >java J3c_3  **
Kaynak resim: [lale.jpg]
Dizin ebat�: [786432] adet piksel ortalamas�d�r.
Dizin ebad�: [10000] e�ik de�erinden b�y�kse kopyalama 2 etapta yap�l�r.
[2] adet i�lemciler mevcuttur.
Resmin kopyalanmas� toplam: [319] milisaniyede tamamland�.
Kopyalanan resim: [kopya_lale.jpg]
*/