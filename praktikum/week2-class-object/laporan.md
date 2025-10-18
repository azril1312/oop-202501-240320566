# Laporan Praktikum Minggu 2
Topik: [ "Class dan Object"]

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
Mahasiswa mampu cara membuat dan mengelola kelas serta objek dalam Java, menerapkan enkapsulasi melalui getter dan setter, mengatur struktur package, dan menambahkan method untuk mengelola data produk secara efisien.
---

## Dasar Teori
1. produk java Kelas Produk dibuat untuk merepresentasikan suatu produk pertanian dengan atribut seperti kode, nama, harga, dan stok, serta menggunakan getter dan setter untuk menerapkan enkapsulasi, yaitu melindungi data agar hanya dapat diakses melalui method tertentu.  
2. CreditBy.java  digunakan untuk menampilkan identitas mahasiswa, sekaligus melatih penggunaan package agar kode program lebih terorganisasi.  
3.  MainProduk.java  berfungsi sebagai kelas utama yang menjalankan program dengan membuat beberapa objek menampilkan data produk ke console, dan untuk menampilkan identitas pembuat program.

---

## Langkah Praktikum
1. Langkah-langkah yang Dilakukan
Melakukan setup struktur folder project dengan mengikuti format src/main/java/com/upb/agripos/model.
Membuat file Java dengan pendekatan class dan object.
Menuliskan class Produk lengkap dengan constructor, atribut, serta method getter dan setter.
Menyesuaikan deklarasi package agar sesuai dengan struktur folder.
Melakukan kompilasi dan menjalankan program menggunakan terminal dengan perintah javac dan java.

2. File/Kode yang Dibuat
Produk.java: berisi class Produk dengan atribut kode, nama, harga, dan stok.
(Opsional) File Main.java untuk menguji pembuatan dan penggunaan object dari class Produk.  
3. Commit message yang digunakan.commit dan push

---

## Kode Program
// Produk.java
 Produk p1 = new Produk("PPK", "Pupuk kompos", 250000, 100);
 System.out.println("Kode: " + p1.kode);

// CreditBy.java
public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);

// MainProduk.java
public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Kedelai IR64", 25000, 100);
        Produk p2 = new Produk("PPU-101", "Pupuk kompos 50kg", 350000, 40);
        Produk p3 = new Produk("PPK-501", "Pupuk Konsentrat 50", 250000, 15);
---

## Hasil Eksekusi
![class and object](https://github.com/user-attachments/assets/371ad2ce-a736-4f74-9b8c-8f4338b21607)

---

## Analisis
(
1. Jalannya Kode
Pada program minggu ini, kode berjalan dengan menggunakan pendekatan class dan object. Class Produk didefinisikan sebagai blueprint (cetakan) dari sebuah produk yang memiliki atribut seperti kode, nama, harga, dan stok. Kemudian, object dapat dibuat dari class ini untuk merepresentasikan produk-produk nyata. Constructor digunakan untuk menginisialisasi data saat object dibuat, dan method getter serta setter digunakan untuk mengakses dan mengubah data tersebut dengan cara yang terkontrol.

2. Perbedaan Pendekatan Minggu Ini Dibanding Minggu Sebelumnya
Minggu ini menggunakan pendekatan class dan object, sedangkan minggu sebelumnya masih menggunakan pendekatan prosedural. Dengan OOP, program menjadi lebih modular, terstruktur, dan mudah dikembangkan karena data dan perilaku disatukan dalam satu kesatuan (object). Hal ini berbeda dengan pendekatan prosedural yang cenderung menuliskan logika program secara langsung tanpa struktur yang jelas.

3. Kendala yang Dihadapi dan Cara Mengatasinya
Kendala utama yang dihadapi adalah kesalahan penulisan package yang tidak sesuai dengan struktur folder, sehingga menyebabkan error saat kompilasi dan eksekusi program. Selain itu, menjalankan file Java dengan package memerlukan pemahaman tambahan tentang struktur direktori dan perintah javac serta java yang tepat.
Cara mengatasinya adalah dengan memastikan bahwa deklarasi package sesuai dengan folder tempat file berada, dan menjalankan perintah javac serta java dari root folder src/main/java menggunakan path lengkap ke class-nya.
 
)
---

## Kesimpulan
Dengan menggunakan class dan object, program menjadi lebih terstruktur, modular, dan mudah dipahami. Pemisahan data dan perilaku ke dalam class membantu dalam pengorganisasian kode serta memudahkan proses pengembangan, perawatan, dan pengujian program. Konsep ini juga memungkinkan penggunaan kembali kode (reusability) dan penerapan prinsip-prinsip pemrograman berorientasi objek seperti enkapsulasi dan abstraksi.

---

## Quiz
1)Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
    Jawaban: Karena atribut yang bersifat private melindungi data agar tidak dapat diakses atau diubah langsung dari luar class, sehingga menjaga keamanan dan konsistensi data sesuai prinsip enkapsulasi.

2)Apa fungsi getter dan setter dalam enkapsulasi?
    Jawaban: Fungsi getter adalah untuk mengambil nilai dari atribut privat, sedangkan setter digunakan untuk mengubah nilainya secara terkontrol agar tetap sesuai aturan yang ditentukan dalam class.

3)Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
    Jawaban: Class Produk dapat menjadi dasar pengelolaan data barang pada aplikasi POS dengan menyimpan informasi produk, mengatur stok, dan memudahkan pengembangan fitur tambahan seperti transaksi penjualan, laporan inventori, dan manajemen harga.  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
