# Laporan Praktikum Minggu 12
Topik: GUI Dasar JavaFX (Event-Driven Programming)

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
1. Menjelaskan konsep event-driven programming.
2. Membangun antarmuka grafis sederhana menggunakan JavaFX.
3. Membuat form input data produk.
4. Menampilkan daftar produk pada GUI.
5. Mengintegrasikan GUI dengan modul backend yang telah dibuat (DAO & Service).

---

## Dasar Teori
1. Event-Driven Programming adalah paradigma pemrograman di mana alur program ditentukan oleh event atau aksi pengguna, seperti klik tombol atau input teks.
2. JavaFX merupakan framework GUI Java yang digunakan untuk membangun antarmuka grafis berbasis komponen (Stage, Scene, Node).
3. MVC (Model–View–Controller) memisahkan logika aplikasi menjadi tiga bagian: Model (data), View (tampilan), dan Controller (pengendali event).
4. Service Layer berfungsi sebagai penghubung antara Controller dan DAO agar logika bisnis tidak bercampur dengan logika tampilan.
5. DAO (Data Access Object) digunakan untuk mengakses dan mengelola data sehingga Controller tidak berinteraksi langsung dengan database (penerapan DIP – SOLID).

---

## Langkah Praktikum
1. Menyiapkan environment Java dan JavaFX serta membuka folder week12-gui-dasar pada VS Code.
2. Membuat struktur proyek berbasis MVC (model, view, controller, service, dao).
3. Mengimplementasikan ProductFormView menggunakan JavaFX sebagai antarmuka input dan tampilan data produk.
4. Membuat ProductController untuk menangani event tombol dan menghubungkan View dengan Service.
5. Menggunakan ProductService sebagai perantara antara Controller dan DAO sesuai prinsip MVC.
6. Menjalankan aplikasi JavaFX dan menguji fitur tambah serta tampil daftar produk.
7. Melakukan commit
---

## Kode Program
(Tuliskan kode utama yang dibuat, 

```java
package com.upb.agripos;

import com.upb.agripos.controller.ProductController;
import com.upb.agripos.service.ProductService;
import com.upb.agripos.view.ProductFormView;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppJavaFX extends Application {

    @Override
    public void start(Stage stage) {
        ProductFormView view = new ProductFormView();
        ProductService service = new ProductService();
        new ProductController(service, view);

        Scene scene = new Scene(view, 450, 500);
        stage.setTitle("Agri-POS - Week 12 (GUI Dasar)");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

```
)
---

## Hasil Eksekusi
<img width="675" height="787" alt="gui" src="https://github.com/user-attachments/assets/1b969cea-00bf-4e74-a902-ac61824e1f97" />

---

## Analisis
(
- Jelaskan bagaimana kode berjalan.  

    Aplikasi ini dijalankan menggunakan pendekatan event-driven. Ketika pengguna mengisi formulir lalu menekan tombol Tambah Produk, sistem akan memicu event handler pada ProductController. Controller tersebut kemudian membentuk objek Product dan meneruskannya ke ProductService untuk diproses dan disimpan. Setelah proses penyimpanan selesai, komponen ListView pada antarmuka diperbarui secara otomatis sehingga menampilkan data produk terbaru.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  

  Perbedaan utama dibandingkan praktikum sebelumnya terletak pada metode interaksi pengguna. Pada pertemuan sebelumnya, aplikasi dijalankan melalui Command Line Interface (CLI), sedangkan pada praktikum ini interaksi dilakukan melalui antarmuka grafis berbasis JavaFX. Selain itu, alur eksekusi program tidak lagi bersifat linier, melainkan bergantung pada event yang dipicu oleh tindakan pengguna, seperti menekan tombol atau mengisi form.

- Kendala yang dihadapi dan cara mengatasinya. 

    Beberapa kendala yang ditemui meliputi pengaturan JavaFX agar dapat dijalankan dengan baik di VS Code serta permasalahan dalam mengakses komponen View dari Controller. Permasalahan tersebut diatasi dengan menambahkan konfigurasi vmArgs JavaFX pada berkas launch.json dan menerapkan metode getter pada View guna menjaga prinsip enkapsulasi tetap terpenuhi.

)
---

## Kesimpulan
Penerapan JavaFX dengan pola arsitektur MVC membuat aplikasi menjadi lebih interaktif, terstruktur, dan mudah untuk dikembangkan. Pemisahan yang jelas antara View, Controller, dan Service membantu merapikan alur logika program serta mempermudah integrasi antara antarmuka pengguna dan proses backend.
