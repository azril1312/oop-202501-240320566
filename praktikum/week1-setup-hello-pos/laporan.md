# Laporan Praktikum Minggu 1 (sesuaikan minggu ke berapa?)
Topik: [OOP,Procedural,Functional]

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
1. Mahasiswa mampu memahami konsep dasar dari ketiga paradigma pemrograman tersebut.
2. Mahasiswa mamapu mengenal kelebihan dan kekurangan dari masing-masing paradigma pemrograman.
3. Mahasiswa mampu mengembangkan kemampuan analisis dalam memilih paradigma pemrograman yang         tepat untuk suatu masalah.

---

## Dasar Teori 
1. OOP  (Object-Oriented Programming) pemrograman yang menggunakan objek dan kelas untuk mengembangkan perangkat lunak.
2. Procedural menekankan pada urutan intruksi yang harus dijalan oleh komputer dan dapat dipanggil berulang kali.
3. Funcional pemrograman yang berfokus pada fungsi murni (pure function) dan menghindari perubahan state (immutable).

---

## Langkah Praktikum
(setup, coding, run).

   -setup
menginstal JDK supaya bisa menjalankan program Java.

   -coding
membuat tiga file Java sesuai paradigma yang dipelajari:
HelloOOP.java
HelloFunctional.java
HelloProcedural.java
   
   -run
menjalankan coding dengan output 

   -Commit dan Push

---


## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
// OOP
Produk[] daftar = {
         new Produk("Padi", 10000),
         new Produk("Sayuran", 15000),
         new Produk("Buah", 12000)
      };

// PROCEDURAL 
String nim = "240320566";
      String nama = "Azril Rabbani Fawa";
      System.out.println("Hello World, I am "+ nama + ", "+ nim);

// FUNCTIONAL 
List<String> produk = Arrays.asList("Padi", "Sayuran", "Buah");
      List<Integer> harga = Arrays.asList(10000, 15000, 12000);

---

## Hasil Eksekusi
( !(Screenshots Procedural.png ))
( !(Screenshots HelloOOP.png ))
( !(Screenshots Functional.png ))
---

## Analisis
(
## Bagaimana kode berjalan
  Pada program prosedural, kode jalan langsung dari main lalu print nama dan NIM.
  Pada OOP, saya buat objek mahasiswa, isi data, lalu panggil method untuk menampilkan hasil.
  Pada fungsional, saya pakai lambda (BiConsumer) untuk mendefinisikan fungsi, lalu jalankan dengan accept().

## Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya?
  Karena ini adalah minggu pertama, belum ada perbedaan signifikan dengan materi sebelumnya. Fokus utama adalah memahami dasar-dasar paradigma pemrograman (prosedural, OOP, dan fungsional).

## Kendala yang dihadapi dan cara mengatasinya
  Mengalami kesulitan memahami konsep OOP (konstruktor, metode) dan fungsional (lambda).Solusi saya terus mempelajari contoh kode dan berlatih hingga memahami konsep tersebut.
 
)
---

## Kesimpulan
Setiap paradigma pemrograman memiliki kelebihan dan kekurangan yang berbeda beda. Prosedural efektif untuk proyek kecil dan sederhana, OOP unggul dalam mengelola kompleksitas aplikasi besar dengan struktur objek yang rapi, sedangkan fungsional menawarkan efisiensi dalam pengolahan data dan mengurangi redundansi kode. Pemilihan paradigma yang tepat bergantung pada kebutuhan spesifik aplikasi, tingkat kerumitan, dan tujuan pengembangan untuk memastikan program yang mudah dipelihara dan dikembangkan.

---

## Quiz
1. Apakah OOP selalu lebih baik dari prosedural?  
   **Jawaban: Tidak selalu,karena OOP lebih bagus kalau pogram bagus dan lebih kompleks karena konsep objek nya lebih rapi. Tapi Procedural jika progamnya lebih kecil dan sederhana, procedural lebih cepat dibuat dan lebih mudah.
 
2. Kapan functional programming lebih cocok digunakan dibanding OOP atau prosedural?  
   **Jawaban:Functional progamming cocok dipakai kalau butuh proses yang banyak ngolah data, minyalnya analisis data, perhitungan matematis. Paradigma ini fokus pada fungsi murni dan minim kesalahan,jadi hasilnya lebih bagus dan konsisten.

3. Bagaimana paradigma (prosedural, OOP, fungsional) memengaruhi maintainability dan scalability aplikasi?  
   **Jawaban: >procedural gampang ditulis,tapi susah dirawat kalau programnay makin bedar dan rumit
              >OOP teratur,gampang dikembangkan,dan mudah diperluas
              >functional kode lebih singkat,minim efek samping,dan mudah diparelkan
  
4. Mengapa OOP lebih cocok untuk mengembangkan aplikasi POS dibanding prosedural?
   **Jawaban:OOP lebih cocok untuk aplikasi POS karena memungkinkan modularitas, reusabilitas, dan pengelolaan kompleksitas yang lebih baik. Dengan OOP, kode dapat dibagi menjadi objek-objek independen seperti "Transaksi" dan "Produk", sehingga memudahkan pengembangan, pemeliharaan, dan penambahan fitur baru. Ini membuat aplikasi POS lebih scalable dan maintainable.

5. Bagaimana paradigma fungsional dapat membantu mengurangi kode berulang (boilerplate code)?
   **Jawaban:Paradigma fungsional membantu mengurangi kode berulang dengan menggunakan fungsi murni, fungsi tingkat tinggi, dan immutable data. Ini memungkinkan penulisan kode yang lebih ringkas, modular, dan reusable, sehingga mengurangi kebutuhan akan kode berulang dan meningkatkan efisiensi pengembangan.