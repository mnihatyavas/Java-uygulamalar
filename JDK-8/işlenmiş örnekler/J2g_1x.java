// J2g_1x.java: Box (Kutu) alt-örneði.

/**
 * J2g_1x/Box sýnýfýnýn tiplemeli sürümü.
 * @param <T> kutulanan deðerin tipi
 */
public class J2g_1x<T> {
    private T t; // T/Type, burada geçirilen tip'dir...

    public void koy (T t) {this.t = t;}
    public T al() {return t;}
} // J2g_1x sýnýfý sonu...