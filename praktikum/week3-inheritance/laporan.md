# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : Azril Rabbani Fawa
- NIM   : 240320566
- Kelas : 3DSRA
---

## Tujuan
Praktikum minggu ini bertujuan untuk memahami dan menerapkan konsep inheritance dalam OOP melalui pembuatan superclass dan subclass produk pertanian, penggunaan keyword *super*, serta demonstrasi hierarki class. Dengan ini, mahasiswa dapat melihat keunggulan inheritance dalam menyederhanakan kode dan membedakannya dari penggunaan class tunggal.


---

## Dasar Teori
1. Inheritance (pewarisan) adalah konsep dalam pemrograman berorientasi objek yang memungkinkan suatu class mewarisi atribut dan metode dari class lain
2. Inheritance adalah mekanisme pewarisan sifat dan perilaku dari suatu class induk (superclass) ke class turunan (subclass).

3. Konsep ini digunakan untuk mengurangi duplikasi kode dan memudahkan pemeliharaan program.

4. Subclass dapat menggunakan serta memodifikasi atribut dan metode yang dimiliki oleh superclass.

5. Pewarisan memungkinkan penerapan konsep reusability dan hierarki antar class dalam pemrograman berorientasi objek.


---

## Langkah Praktikum
1. Buat project baru dengan nama sesuai topik, misalnya Agripos.

2. Buat class induk (superclass) dengan nama Produk yang berisi atribut umum seperti kode, nama, harga, dan stok.

3. Buat class turunan (subclass) seperti BenihKacang, PupukUrea, dan Alat yang mewarisi dari class Produk dan menambahkan atribut khusus sesuai kebutuhan.

4. Buat class utama bernama MainInheritance untuk menguji konsep pewarisan dengan membuat objek dari masing-masing subclass dan menampilkan data melalui metode getter.

5. Tambahkan class utilitas seperti CreditBy untuk menampilkan identitas mahasiswa (nama dan NIM).

6. Jalankan program dan amati hasil output di konsol untuk memastikan pewarisan berjalan dengan benar.

7. lalu commit dan push


---

## Kode Program
java
// AlatPertanian
public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }
// BenihKacang
 public BenihKacang(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }
// Produk
public static void main(String[] args) {
    Produk p1 = new Produk("ALT-011", "Sabit Padi", 9000, 100);
// PupukKompos
public PupukKompos(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
// CreditBy
public class CreditBy {
     public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);
    }
// MainInheritance
 public static void main(String[] args) {
        BenihKacang b = new BenihKacang("BNH-001", "Benih Kacang IR64", 25000, 100, "IR64");
        PupukUrea p = new PupukUrea("PPU-101", "Pupuk Urea", 350000, 40, "Urea");
        Alat a = new Alat("ALT-011", "Cangkul Baja", 90000, 15, "Baja");


---

## Hasil Eksekusi
![alt text](<Screenshot 2025-10-26 180814.png>)
---

## Analisis
(
Program diawali dengan pembuatan class induk Produk yang berisi atribut dasar seperti kode, nama, harga, dan stok. Selanjutnya, dibuat beberapa class turunan (BenihKacang, PupukUrea, dan Alat) yang menggunakan kata kunci extends untuk mewarisi properti dari Produk. Setiap subclass menambahkan atribut dan perilaku khusus sesuai jenis produknya.
Pada MainInheritance.java, objek dari masing-masing subclass dibuat dan dipanggil metode untuk menampilkan data. Proses ini menunjukkan bagaimana subclass dapat menggunakan atribut dan metode dari superclass melalui pewarisan.

Perbedaan Pendekatan Minggu Ini:
Minggu ini fokus pada konsep inheritance (pewarisan), sedangkan minggu sebelumnya mungkin hanya membahas enkapsulasi atau pembuatan class dan object secara terpisah tanpa hubungan antar class.
Pada praktikum kali ini, kode menjadi lebih efisien karena tidak perlu menulis ulang atribut dan metode yang sama di setiap class — cukup diwariskan dari Produk.

Kendala yang Dihadapi dan Cara Mengatasinya:
Beberapa kendala yang muncul antara lain kesalahan sintaks seperti penggunaan parameter dengan format nama:"Taufik" yang tidak valid di Java, serta error saat pemanggilan konstruktor superclass.
Solusinya adalah memastikan urutan parameter konstruktor sesuai definisi di superclass dan menggunakan format pemanggilan yang benar, misalnya new BenihKacang("BNH-001", "Benih Kacang IR64", 25000, 50, "IR64");.
Selain itu, perlu memastikan setiap file class berada dalam package yang sama agar bisa saling dikenali oleh compiler
 
)
---

## Kesimpulan
Pada praktikum minggu ini, mahasiswa telah mempelajari dan menerapkan konsep inheritance (pewarisan) dalam pemrograman berorientasi objek menggunakan bahasa Java. Melalui pembuatan class induk Produk dan beberapa class turunan seperti BenihKacang, PupukUrea, dan Alat, mahasiswa memahami bagaimana subclass dapat mewarisi atribut dan metode dari superclass.
Penerapan inheritance membantu mengurangi duplikasi kode, membuat program lebih terstruktur, mudah dikembangkan, serta menunjukkan hubungan hierarki antar objek. Dengan demikian, mahasiswa mampu mengimplementasikan prinsip pewarisan secara efektif dalam pembuatan program berbasis objek.

---

## Quiz
(1. [Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?]  
   **Jawaban:Inheritance memungkinkan reuse (penggunaan kembali) kode dari superclass sehingga mengurangi duplikasi, membuat program lebih terstruktur, mudah dikembangkan, dan konsisten karena perubahan pada superclass otomatis berlaku pada seluruh subclass.** …  

2. [TBagaimana cara subclass memanggil konstruktor superclass?]  
   **Jawaban:Subclass memanggil konstruktor superclass menggunakan keyword super(...) di baris pertama konstruktor, dengan parameter yang sesuai dengan konstruktor superclass.** …  

3. [Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.]  
   **Jawaban:Contoh lainnya adalah Pestisida, Bibit Ikan, Pakan Ternak, atau Produk Olahan seperti Beras Kemasan yang semuanya dapat mewarisi atribut dari class Produk seperti kode, nama, harga, dan stok.** …  )
