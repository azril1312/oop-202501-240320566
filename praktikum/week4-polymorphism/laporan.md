# Laporan Praktikum Minggu 4
Topik: [MainPolymorphism]

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
Mahasiswa memahami konsep polymorphism dalam pemrograman berorientasi objek (OOP) serta mampu mengimplementasikannya pada program yang menampilkan informasi produk dengan menggunakan inheritance dan method overriding. Selain itu, mahasiswa diharapkan dapat membedakan perilaku objek dari kelas turunan yang memiliki method dengan nama sama namun implementasi berbeda.

---

## Dasar Teori
1. Polymorphism adalah konsep dalam pemrograman berorientasi objek yang memungkinkan suatu objek memiliki banyak bentuk atau perilaku berbeda meskipun menggunakan referensi yang sama.

2. Inheritance (pewarisan) digunakan agar suatu kelas dapat mewarisi atribut dan method dari kelas lain, sehingga memudahkan penerapan polymorphism.

3. Method Overriding terjadi ketika subclass mendefinisikan ulang method yang sudah ada di superclass untuk memberikan perilaku yang berbeda.

4. Dengan polymorphism, pemanggilan method akan disesuaikan dengan jenis objek yang digunakan pada saat runtime, bukan hanya berdasarkan tipe referensi.

5. Konsep ini mempermudah pengembangan program karena kode menjadi lebih fleksibel, mudah diperluas, dan mendukung prinsip code reusability.

---

## Langkah Praktikum
1. Menyiapkan Project
Membuat project baru dengan nama InfoProdukPolymorphism.

2. Membuat file Produk.java yang berisi atribut umum seperti nama, harga, dan method tampilkanInfo() untuk menampilkan informasi dasar produk.

3. Membuat Kelas Turunan (Subclass)
Membuat dua subclass, misalnya Buku.java dan Elektronik.java, yang mewarisi dari kelas Produk.
Masing-masing subclass melakukan overriding terhadap method tampilkanInfo() untuk menampilkan detail spesifik sesuai jenis produk.

4. Membuat Kelas Main
Membuat file Main.java yang berisi method main().
Membuat beberapa objek dari kelas Buku dan Elektronik, lalu memanggil method tampilkanInfo() menggunakan referensi bertipe Produk untuk menunjukkan polymorphism.

5. Menjalankan Program
Melakukan compile dan run project untuk memastikan hasil output menampilkan informasi produk dengan perilaku berbeda sesuai jenis objek.

6. Commit dan Dokumentasi

Melakukan commit pada repository dengan pesan:
git commit -m "week4-polymorphism"

Menyimpan hasil kode dan tangkapan layar output sebagai dokumentasi laporan

---

## Kode Program
```java
// Alat.java
@Override
   public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;

// BenihKacang.java
@Override
    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }
// Produk.java
public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }
// PupukUrea.java
 @Override
    public String getInfo() {
        return "Pupuk: " + super.getInfo() + ", Varietas: " + varietas;
    }
// CreditBy.java
public class CreditBy {
     public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);
    }
// MainPolymorphism.java
public static void main(String[] args) {
        Produk[] daftarProduk = {
           Produk[] daftarProduk = {
            new Benih("BNH-001", "Benih Padi IR64", 25000, 100, "IR64"),
            new Pupuk("PPK-101", "Pupuk Urea", 350000, 40, "Urea"),
            new AlatPertanian("ALT-501", "Cangkul Baja", 90000, 15, "Baja")
        };

---

## Hasil Eksekusi
<img width="866" height="183" alt="Screenshot 2025-11-01 105603" src="https://github.com/user-attachments/assets/489d3093-232e-4f13-ae82-8e65dd778ef7" />

---

## Analisis
Jalannya Kode:
Program diawali dengan pembuatan kelas induk Produk yang memiliki atribut umum seperti nama dan harga, serta method tampilkanInfo(). Kemudian dibuat kelas turunan seperti Buku dan Elektronik yang melakukan overriding terhadap method tersebut agar menampilkan informasi sesuai jenis produk masing-masing. Pada kelas Main, objek-objek dari subclass disimpan dalam referensi bertipe Produk, lalu ketika tampilkanInfo() dipanggil, Java akan mengeksekusi method sesuai tipe objek aslinya (dynamic binding).

Perbedaan Pendekatan dengan Minggu Sebelumnya:
Pada minggu sebelumnya, program masih menggunakan konsep inheritance dan enkapsulasi dasar, di mana tiap objek hanya memiliki perilaku tetap sesuai kelasnya. Namun pada minggu ini digunakan polymorphism, yang memungkinkan satu referensi memiliki perilaku berbeda tergantung pada objek yang diacu. Hal ini menjadikan kode lebih dinamis dan fleksibel dalam memperlakukan berbagai jenis produk.

Kendala dan Cara Mengatasinya:
Salah satu kendala yang dihadapi adalah method yang tidak terdeteksi sebagai overriding karena perbedaan penulisan nama method atau parameter. Kendala ini diatasi dengan memastikan tanda @Override digunakan di atas method turunan serta memperhatikan kesesuaian tipe dan jumlah parameter. Selain itu, kesalahan dalam memahami konsep referensi superclass dan objek subclass diatasi dengan mencoba contoh kode sederhana untuk memahami perilaku polymorphism secara bertahap.

---

## Kesimpulan
Dengan menerapkan konsep polymorphism pada program Info Produk, mahasiswa dapat memahami bagaimana satu referensi objek dapat memiliki banyak bentuk perilaku berbeda tergantung pada kelas turunannya. Melalui mekanisme method overriding, program menjadi lebih fleksibel, mudah diperluas, serta mendukung prinsip reusability dalam pemrograman berorientasi objek. Praktikum ini juga menunjukkan bahwa polymorphism membantu membuat kode lebih efisien dan terstruktur dalam mengelola berbagai jenis produk.


---

## Quiz
1. Apa perbedaan overloading dan overriding?  
   *Jawaban:* Overloading terjadi ketika terdapat beberapa method dengan nama yang sama tetapi memiliki parameter berbeda (jumlah atau tipe datanya berbeda) dalam satu kelas. Sedangkan overriding terjadi ketika subclass mendefinisikan ulang method yang sama (nama dan parameter sama) dari superclass untuk memberikan perilaku yang berbeda. 

2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?  
   *Jawaban:* Java menentukan method yang dipanggil berdasarkan tipe objek sebenarnya yang diacu oleh referensi saat program dijalankan (runtime). Meskipun referensinya bertipe superclass, jika objeknya adalah subclass, maka method subclass yang akan dijalankan. Inilah yang disebut dynamic binding atau late binding.

3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.  
   *Jawaban:* Contoh kasus polymorphism dalam sistem POS adalah pada penjualan di toko elektronik, di mana kelas Produk memiliki subclass seperti Laptop, Smartphone, dan Televisi. Masing-masing subclass memiliki cara berbeda untuk menampilkan informasi produknya, tetapi semua bisa dipanggil melalui referensi bertipe Produk.
