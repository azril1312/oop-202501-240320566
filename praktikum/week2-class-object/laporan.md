# Laporan Praktikum Minggu 2 
Topik: ["Class and Object"]

## Identitas
- Nama  : Azril Rabbani Fawa
- NIM   : 240320566
- Kelas : 3DSRA

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
(Tuliskan kode utama yang dibuat, contoh:  

```java
// Contoh
Produk p1 = new Produk("BNH-001", "Benih Padi", 25000, 100);
System.out.println(p1.getNama());
```
)
---

## Hasil Eksekusi
![alt text](<class and object.jpg>)
---

## Analisis
(
>Jalannya Kode
Pada program minggu ini, kode berjalan dengan menggunakan pendekatan class dan object. Class Produk didefinisikan sebagai blueprint (cetakan) dari sebuah produk yang memiliki atribut seperti kode, nama, harga, dan stok. Kemudian, object dapat dibuat dari class ini untuk merepresentasikan produk-produk nyata. Constructor digunakan untuk menginisialisasi data saat object dibuat, dan method getter serta setter digunakan untuk mengakses dan mengubah data tersebut dengan cara yang terkontrol.

>Perbedaan Pendekatan Minggu Ini Dibanding Minggu Sebelumnya
Minggu ini menggunakan pendekatan class dan object, sedangkan minggu sebelumnya masih menggunakan pendekatan prosedural. Dengan OOP, program menjadi lebih modular, terstruktur, dan mudah dikembangkan karena data dan perilaku disatukan dalam satu kesatuan (object). Hal ini berbeda dengan pendekatan prosedural yang cenderung menuliskan logika program secara langsung tanpa struktur yang jelas.

>Kendala yang Dihadapi dan Cara Mengatasinya
Kendala utama yang dihadapi adalah kesalahan penulisan package yang tidak sesuai dengan struktur folder, sehingga menyebabkan error saat kompilasi dan eksekusi program. Selain itu, menjalankan file Java dengan package memerlukan pemahaman tambahan tentang struktur direktori dan perintah javac serta java yang tepat.
Cara mengatasinya adalah dengan memastikan bahwa deklarasi package sesuai dengan folder tempat file berada, dan menjalankan perintah javac serta java dari root folder src/main/java menggunakan path lengkap ke class-nya.
 
)
---

## Kesimpulan
Dengan menggunakan class dan object, program menjadi lebih terstruktur, modular, dan mudah dipahami. Pemisahan data dan perilaku ke dalam class membantu dalam pengorganisasian kode serta memudahkan proses pengembangan, perawatan, dan pengujian program. Konsep ini juga memungkinkan penggunaan kembali kode (reusability) dan penerapan prinsip-prinsip pemrograman berorientasi objek seperti enkapsulasi dan abstraksi.


---

## Quiz
(1. [Tuliskan kembali pertanyaan 1 dari panduan]  
   **Jawaban:** …  

2. [Tuliskan kembali pertanyaan 2 dari panduan]  
   **Jawaban:** …  

3. [Tuliskan kembali pertanyaan 3 dari panduan]  
   **Jawaban:** …  )
