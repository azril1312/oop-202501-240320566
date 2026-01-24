# Laporan Praktikum Minggu 14
Topik: Integrasi Individu (OOP + Database + GUI)

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
Setelah mengikuti praktikum ini, mahasiswa mampu:
1. Mengintegrasikan konsep OOP (Bab 1–5) ke dalam satu aplikasi yang utuh.
2. Mengimplementasikan rancangan UML + SOLID (Bab 6) menjadi kode nyata.
3. Mengintegrasikan Collections + Keranjang (Bab 7) ke alur aplikasi.
4. Menerapkan exception handling (Bab 9) untuk validasi dan error flow.
5. Menerapkan pattern + unit testing (Bab 10) pada bagian yang relevan.
6. Menghubungkan aplikasi dengan database via DAO + JDBC (Bab 11).
7. Menyajikan aplikasi berbasis JavaFX (Bab 12–13) yang terhubung ke backend.

---

## Dasar Teori
1. Object Oriented Programming (OOP) digunakan untuk membangun aplikasi berbasis class dan objek agar kode terstruktur, modular, dan mudah dikembangkan.
2. Arsitektur berlapis (MVC + Service + DAO) diterapkan untuk memisahkan tampilan, logika aplikasi, dan akses data sesuai prinsip SOLID.
3. DAO dan JDBC digunakan untuk menghubungkan aplikasi Java dengan database PostgreSQL dan menjalankan operasi CRUD.
4. Collections Framework dimanfaatkan untuk mengelola data keranjang belanja secara dinamis.
5. Exception handling, design pattern, dan unit testing digunakan untuk validasi, pengelolaan koneksi, dan pengujian logika non-UI.

---

## Langkah Praktikum
1. Melanjutkan proyek dari Bab 1–13 dan menyiapkan struktur folder sesuai ketentuan Bab 14.
2. Mengimplementasikan class model (Product, Cart, CartItem) berbasis OOP.
3. Menerapkan DAO dan Service untuk operasi CRUD produk menggunakan JDBC.
4. Mengimplementasikan fitur keranjang belanja menggunakan Collections.
5. Menerapkan validasi input dan exception handling.
6. Membuat antarmuka aplikasi menggunakan JavaFX dan menghubungkannya dengan Controller.
7. Menerapkan satu design pattern dan membuat satu unit test JUnit.
8. Menjalankan aplikasi dan mendokumentasikan hasil eksekusi.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  

```java
package com.upb.agripos;

import com.upb.agripos.controller.PosController;
import com.upb.agripos.view.PosView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage primaryStage) {
        PosController controller = new PosController();
        PosView view = new PosView(controller, primaryStage);

        Scene scene = new Scene(view.getRoot(), 1000, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("AgriPOS - Azril Rabbani Fawa");
        primaryStage.show();
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
![test](https://github.com/user-attachments/assets/8231d3a7-0244-4088-b0b8-cbc77217f991)


![app_main](https://github.com/user-attachments/assets/cbfb7161-0787-4650-89a9-1b32830a6601)

)

---

## Analisis

- Jelaskan bagaimana kode berjalan.

   Aplikasi Agri-POS dijalankan menggunakan arsitektur berlapis. Proses dimulai dari View berbasis JavaFX yang berfungsi menerima interaksi pengguna. Input tersebut kemudian diproses oleh Controller yang mengatur alur aplikasi dan meneruskannya ke Service. Pada layer Service, dilakukan pengolahan logika bisnis seperti validasi data dan manajemen keranjang. Selanjutnya, DAO bertanggung jawab melakukan operasi Create, Read, Update, dan Delete (CRUD) ke database PostgreSQL menggunakan JDBC. Hasil pemrosesan data kemudian dikembalikan dan ditampilkan kembali pada antarmuka JavaFX.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  

    Pendekatan yang digunakan pada praktikum minggu ini berbeda karena seluruh konsep dari beberapa bab sebelumnya digabungkan menjadi satu aplikasi yang lengkap dan terintegrasi. Pada praktikum minggu sebelumnya, penerapan konsep masih bersifat terpisah dan hanya menitikberatkan pada satu materi tertentu. Sementara itu, pada Bab 14, diterapkan arsitektur aplikasi secara menyeluruh yang mencakup pemisahan layer, penggunaan database, antarmuka grafis (GUI), serta penerapan unit testing.

- Kendala yang dihadapi dan cara mengatasinya.  

    Beberapa kendala yang ditemukan dalam praktikum ini meliputi masalah koneksi database, pengaturan komunikasi antar layer, serta validasi input pada antarmuka pengguna. Kendala tersebut diatasi dengan melakukan pengecekan ulang konfigurasi JDBC, menyesuaikan pemanggilan metode agar sesuai dengan arsitektur MVC + Service + DAO, serta menerapkan exception handling untuk menangani kesalahan input dan error saat runtime.

---

## Kesimpulan
Praktikum Bab 14 berhasil mengintegrasikan konsep Object-Oriented Programming (OOP), database, dan Graphical User Interface (GUI) ke dalam satu aplikasi yang utuh. Dengan penerapan arsitektur berlapis, penggunaan Collections, exception handling, design pattern, serta unit testing, aplikasi menjadi lebih terstruktur, mudah dipelihara, dan memiliki potensi untuk dikembangkan lebih lanjut.

---
