// J2c_19b.java: DeltaOne (DeltaBir) örneği.

public class J2c_19b {
    public static void main (String[] args) {
        J2c_19a alfaNesnesi = new J2c_19a();

      //System.out.format ("özelDeğişken: [%1d]%n", alfaNesnesi.özelDeğişken); // kuralsız...
        System.out.format ("paketDeğişkeni: [%1d]%n", alfaNesnesi.paketDeğişkeni); // kurallı...
        System.out.format ("korumalıDeğişken: [%1d]%n", alfaNesnesi.korumalıDeğişken); // kurallı...
        System.out.format ("genelDeğişken: [%1d]%n%n", alfaNesnesi.genelDeğişken); // kurallı...

      //alfaNesnesi.özelMetod(); // kuralsız...
        alfaNesnesi.paketMetodu(); // kurallı...
        alfaNesnesi.korumalıMetod(); // kurallı...
        alfaNesnesi.genelMetod(); // kurallı...
    } // main(..) metodu sonu...
} // J2c_19b sınıfı sonu...

/* Çıktı:
**  >java J2c_19b  **
paketDeğişkeni: [2]
korumalıDeğişken: [3]
genelDeğişken: [4]

paketMetodu çağrıldı.
korumalıMetod çağrıldı.
genelMetod çağrıldı.
*/