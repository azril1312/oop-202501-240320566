# Laporan Praktikum Minggu 7 (sesuaikan minggu ke berapa?)
Topik: [Bab 7 – Collections dan Implementasi Keranjang Belanja]

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
1.Menjelaskan konsep collection dalam Java (List, Map, Set).
2.Menggunakan ArrayList untuk menyimpan dan mengelola objek.
3.Mengimplementasikan Map atau Set sesuai kebutuhan pengelolaan data.
4.Melakukan operasi dasar pada collection: tambah, hapus, dan hitung total.
5.Menganalisis efisiensi penggunaan collection dalam konteks sistem Agri-POS.

---

## Dasar Teori
1. Collections Framework
Java Collections Framework menyediakan struktur data untuk mengelola objek secara dinamis dan efisien.

Struktur utama:

>List (implementasi: ArrayList) — Terurut, dapat menyimpan elemen duplikat.
>Map (implementasi: HashMap) — Menyimpan pasangan key–value, akses cepat berdasarkan key.
>Set (implementasi: HashSet) — Tidak menerima duplikat dan tidak mempertahankan urutan.  
2. Studi Kasus: Keranjang Belanja Agri-POS
Keranjang belanja harus dapat:

>Menambahkan produk
>Menghapus produk
>Menampilkan isi keranjang
>Menghitung total nilai transaksi
>Menangani jumlah (quantity) menggunakan Map
>Kasus ini mencerminkan penggunaan struktur data dalam aplikasi nyata seperti POS.  
3. Enkapsulasi digunakan untuk menyembunyikan data.)

---

## Langkah Praktikum
1. Membuat Class Product
2. Implementasi Keranjang dengan ArrayList
3. Main Program (WAJIB DIISI)
4. Implementasi Alternatif Menggunakan Map (Dengan Quantity)
5. Commit message: week7-collection

---

## Kode Program
kode utama yang dibuat:  

package com.upb.agripos;

public class MainCart {
    public static void main(String[] args) {
        System.out.println("Hello, I am Bunga Maura Aulya-240320562 (Week7-collections)");

        Product p1 = new Product("P01", "Beras", 50000);
        Product p2 = new Product("P02", "Pupuk", 30000);
       
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();
        cart.removeProduct(p1);
        cart.printCart();
    }
}

## Hasil eksekusi
<img width="863" height="226" alt="Screenshot 2025-12-31 102931" src="https://github.com/user-attachments/assets/595ed974-4d5e-43e0-8813-f8b8dd6aeaab" />



## Analisis
(
- Jelaskan bagaimana kode berjalan:
Program dijalankan melalui kelas MainCart yang memiliki method main() sebagai titik awal eksekusi. Pada tahap awal, program menampilkan identitas praktikan sebagai penanda bahwa proses eksekusi telah dimulai. Selanjutnya, program membuat dua buah objek Product yang masing-masing merepresentasikan data produk dengan atribut berupa kode produk, nama produk, dan harga.

Objek-objek produk tersebut kemudian dimasukkan ke dalam objek ShoppingCart, yang berperan sebagai keranjang belanja. Kelas ShoppingCart memanfaatkan struktur Collection seperti List atau Map untuk menyimpan kumpulan objek Product. Proses penambahan produk dilakukan melalui method addProduct(), sedangkan method printCart() digunakan untuk menampilkan seluruh data produk yang tersimpan di dalam keranjang. Setelah itu, salah satu produk dihapus menggunakan method removeProduct(). Dengan alur tersebut, program memperlihatkan proses manipulasi data koleksi secara dinamis, meliputi penambahan, penampilan, dan penghapusan objek selama program berjalan.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya:
Pendekatan yang digunakan pada praktikum minggu ini berbeda dari minggu sebelumnya karena telah menerapkan Java Collections Framework sebagai media penyimpanan data. Pada praktikum minggu sebelumnya, pengelolaan data masih bersifat statis atau terbatas pada penggunaan objek secara individual tanpa struktur pengelompokan data yang terorganisasi.

Pada praktikum minggu ini, penggunaan Collection seperti ArrayList atau Map memungkinkan penyimpanan banyak objek Product dalam satu struktur data yang terintegrasi. Hal ini membuat program menjadi lebih fleksibel, mudah dikembangkan, dan mendekati implementasi sistem nyata, seperti sistem keranjang belanja yang jumlah datanya dapat berubah-ubah sesuai kebutuhan pengguna.

- Kendala yang dihadapi dan cara mengatasinya:
Kendala utama yang ditemukan dalam pengerjaan praktikum ini adalah munculnya error cannot find symbol, yang disebabkan oleh ketidaksesuaian antara deklarasi package, struktur direktori, dan pemanggilan class antar file. Akibatnya, compiler Java tidak dapat mengenali class Product dan ShoppingCart saat digunakan di dalam kelas MainCart.

Permasalahan tersebut diatasi dengan menyeragamkan deklarasi package pada seluruh file Java serta memastikan struktur folder telah sesuai dengan package yang digunakan. Selain itu, dilakukan pengecekan ulang terhadap penulisan nama class, constructor, dan method agar sesuai dengan aturan penulisan Java. Setelah perbaikan dilakukan, program berhasil dikompilasi dan dijalankan dengan baik tanpa mengalami error. 

---

## Kesimpulan
Berdasarkan hasil praktikum yang telah dilaksanakan, penerapan Java Collections Framework terbukti mampu meningkatkan keteraturan, fleksibilitas, dan kemudahan pengembangan dalam pengelolaan data. Melalui pemanfaatan struktur data seperti List pada implementasi keranjang belanja, program dapat melakukan penyimpanan, penambahan, penampilan, serta penghapusan objek secara dinamis. Pendekatan ini memberikan ilustrasi yang jelas mengenai penerapan konsep Object-Oriented Programming (OOP) dalam mengelola kumpulan data secara lebih efektif serta sesuai dengan kebutuhan sistem yang bersifat dinamis di dunia nyata.

---

## Quiz
1.Jelaskan perbedaan mendasar antara List, Map, dan Set.
Jawaban:List merupakan struktur data yang menyimpan elemen secara berurutan dan memungkinkan adanya data yang sama (duplikasi). Setiap elemen di dalam List dapat diakses menggunakan indeks sesuai urutan penyimpanannya.
Set adalah struktur data yang tidak mengizinkan adanya elemen duplikat, sehingga setiap data yang tersimpan bersifat unik.
Sementara itu, Map menyimpan data dalam bentuk pasangan key–value, di mana setiap key harus bersifat unik dan digunakan sebagai pengenal untuk mengakses nilai tertentu.

2.Mengapa ArrayList cocok digunakan untuk keranjang belanja sederhana?
Jawaban:ArrayList sesuai digunakan dalam implementasi keranjang belanja sederhana karena mampu menyimpan data secara dinamis serta mempertahankan urutan elemen sesuai dengan proses penambahannya. Selain itu, ArrayList memungkinkan penambahan dan penghapusan data dilakukan dengan mudah tanpa harus menentukan kapasitas awal. Karakteristik ini sangat sesuai dengan kebutuhan keranjang belanja, di mana jumlah item dapat berubah sewaktu-waktu

3.Bagaimana struktur Set mencegah duplikasi data?
Jawaban:Struktur Set mencegah terjadinya duplikasi data dengan melakukan pengecekan terhadap setiap elemen yang akan ditambahkan. Apabila elemen tersebut sudah terdapat di dalam Set, maka data tersebut tidak akan disimpan kembali. Proses ini bergantung pada implementasi method equals() dan hashCode() pada objek yang digunakan untuk memastikan keunikan setiap elemen.

4.Kapan sebaiknya menggunakan Map dibandingkan List? Jelaskan dengan contoh.
Jawaban:Map lebih tepat digunakan ketika data perlu diakses berdasarkan kunci tertentu, bukan berdasarkan posisi atau indeks. Sebagai contoh, dalam sistem keranjang belanja, Map dapat digunakan untuk menyimpan data produk dengan kode produk sebagai key dan objek produk sebagai value. Dengan pendekatan ini, proses pencarian produk berdasarkan kode dapat dilakukan dengan lebih cepat dan efisien dibandingkan menggunakan struktur List.



