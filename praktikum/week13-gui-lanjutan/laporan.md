# Laporan Praktikum Minggu 13 
Topik: GUI Lanjutan JavaFX (TableView dan Lambda Expression)

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
1. Menampilkan data menggunakan TableView JavaFX.
2. Mengintegrasikan koleksi objek dengan GUI.
3. Menggunakan lambda expression untuk event handling.
4. Menghubungkan GUI dengan DAO secara penuh.
5. Membangun antarmuka GUI Agri-POS yang lebih interaktif.

---

## Dasar Teori
1. JavaFX adalah framework Java yang digunakan untuk membangun aplikasi GUI berbasis desktop secara interaktif dan terstruktur.
2. DAO (Data Access Object) merupakan pola desain yang memisahkan logika akses database dari logika bisnis aplikasi.
3. JDBC (Java Database Connectivity) digunakan sebagai penghubung antara aplikasi Java dan database relasional.
4. PreparedStatement digunakan untuk menjalankan query SQL secara aman dan mencegah SQL Injection.
5. Integrasi GUI dengan database memungkinkan data ditampilkan, ditambah, dan dihapus secara real-time melalui antarmuka pengguna.

---

## Langkah Praktikum
1. Menyiapkan project Maven JavaFX dan menambahkan dependency JavaFX serta JDBC PostgreSQL.
2. Membuat database dan tabel product sebagai penyimpanan data produk.
3. Membuat class Product sebagai model data.
4. Mengimplementasikan pola DAO untuk proses insert, delete, dan tampil data dari database.
5. Menghubungkan DAO dengan GUI JavaFX melalui service.
6. Menjalankan aplikasi dan menguji fitur manajemen produk (tambah, tampil, hapus).
7. Melakukan commit hasil praktikum ke repository Git.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.dao.ProductDAO;
import com.upb.agripos.dao.ProductDAOImpl;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductTableView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {

        // ===== Inisialisasi Layer Backend =====
        ProductDAO productDAO = new ProductDAOImpl();
        ProductService productService = new ProductService(productDAO);

        // ===== Inisialisasi View =====
        ProductTableView view = new ProductTableView();

        // ===== Inisialisasi Controller =====
        new ProductController(productService, view);

        // ===== Scene & Stage =====
        Scene scene = new Scene(view, 700, 450);
        stage.setTitle("Agri-POS | Manajemen Produk");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

```
)
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![alt text](<output week13.jpeg>)
)
---

## Analisis

- Jelaskan bagaimana kode berjalan. 

   Aplikasi dijalankan melalui mekanisme di mana data produk diambil dari basis data menggunakan kelas ProductDAO, kemudian diolah oleh ProductService, dan selanjutnya ditampilkan pada antarmuka JavaFX melalui komponen TableView. Setiap interaksi pengguna, seperti penambahan atau penghapusan data produk, ditangani menggunakan lambda expression pada event handler. Setelah aksi dilakukan, sistem memuat ulang data dari database agar tampilan antarmuka selalu menampilkan informasi terbaru.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  

  Pendekatan pada praktikum minggu ini menitikberatkan pada penggunaan TableView yang terhubung langsung dengan database melalui penerapan pola Data Access Object (DAO). Dengan demikian, data yang ditampilkan bersifat dinamis dan selalu sinkron dengan database. Berbeda dengan praktikum minggu sebelumnya, antarmuka grafis yang digunakan masih sederhana dan belum menampilkan data secara terstruktur dalam bentuk tabel yang terintegrasi dengan database.

- Kendala yang dihadapi dan cara mengatasinya.  
   Kendala utama yang ditemui adalah terjadinya kesalahan kompilasi serta ketidaksesuaian antara method pada interface DAO dengan kelas implementasinya. Permasalahan tersebut diselesaikan dengan menyesuaikan penamaan method, signature, serta memastikan konstruktor dan struktur kelas telah sesuai dengan rancangan sistem yang ditetapkan.

---

## Kesimpulan
Praktikum Week 13 berhasil mengimplementasikan integrasi antara antarmuka JavaFX dan database dengan memanfaatkan TableView serta pola DAO. Penerapan lambda expression dan pemisahan peran yang jelas antara lapisan View, Service, dan DAO membuat aplikasi menjadi lebih terstruktur, mudah dikembangkan, serta sejalan dengan prinsip pemrograman berorientasi objek dan arsitektur berlapis.

---
