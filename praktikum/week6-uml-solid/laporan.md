# Laporan Praktikum Minggu 6
Topik: Desain Arsitektur Sistem dengan UML dan Prinsip SOLID

## Identitas
- Nama  : Azril Rabbani Fawa
- NIM   : 240320566
- Kelas : 3DSRA

---

1.Deskripsi singkat sistem:

Sistem yang dirancang merupakan Sistem Point of Sale (POS) yang berfungsi untuk mendukung proses transaksi penjualan pada suatu toko. Sistem ini mencakup pengelolaan data produk, proses transaksi penjualan, perhitungan total pembelian, pemrosesan pembayaran menggunakan metode tunai (cash) maupun non-tunai (e-wallet), pencetakan struk, serta penyajian laporan penjualan.
Pengguna sistem terdiri atas Admin sebagai pengelola data dan laporan, Kasir sebagai pelaksana transaksi, Customer sebagai pihak yang melakukan pembelian, serta Payment Gateway sebagai pihak eksternal yang memvalidasi pembayaran e-wallet.

2.Penjelasan setiap diagram (fungsi dan keterkaitan antar diagram):

1) Use Case Diagram

> Use Case Diagram digunakan untuk menggambarkan interaksi antara aktor dengan sistem serta fungsi-fungsi utama yang disediakan oleh sistem.

> Admin memiliki hak akses untuk melakukan login, mengelola data produk (create, update, delete, dan view), serta melihat laporan penjualan.
> Kasir berperan dalam menjalankan proses transaksi penjualan, mulai dari menambahkan produk ke keranjang, menghitung total pembelian, memproses pembayaran, hingga mencetak struk.
> Customer berinteraksi dengan sistem dalam konteks melakukan pembelian dan pembayaran.
> Payment Gateway berperan dalam memvalidasi transaksi pembayaran non-tunai menggunakan e-wallet.
> Use Case Diagram memberikan gambaran umum sistem yang menjadi dasar perancangan diagram lainnya.

2) Activity Diagram

> Activity Diagram digunakan untuk menggambarkan alur aktivitas dan pengambilan keputusan dalam proses pembayaran.
> Proses dimulai dari kasir melakukan checkout, kemudian sistem menghitung total pembelian. Selanjutnya, customer memilih metode pembayaran, yaitu tunai (cash) atau e-wallet.
> Pada metode tunai, sistem akan memeriksa kecukupan uang yang diberikan. Apabila uang mencukupi, proses pembayaran dilanjutkan dan struk dicetak. Apabila tidak mencukupi, sistem akan menolak transaksi dan meminta input ulang.
> Pada metode e-wallet, sistem akan melakukan validasi pembayaran. Jika validasi berhasil, transaksi diproses dan struk dicetak. Sebaliknya, jika validasi gagal, sistem menampilkan pesan kesalahan.
> Activity Diagram memperjelas logika bisnis yang terjadi di dalam sistem.

3) Sequence Diagram

> Sequence Diagram menggambarkan urutan interaksi antar objek dalam sistem secara kronologis selama proses transaksi berlangsung.
> Alur dimulai ketika customer menyerahkan produk kepada kasir. Kasir memulai transaksi dan melakukan pemindaian barcode produk. Sistem kemudian mencari data produk, memeriksa ketersediaan stok, serta menambahkan produk ke dalam keranjang. Setelah seluruh produk diproses, sistem menghitung total pembayaran.
> Selanjutnya, sistem memproses pembayaran sesuai metode yang dipilih, baik melalui validasi uang tunai maupun melalui permintaan validasi ke payment gateway untuk e-wallet. Setelah pembayaran berhasil, sistem melakukan pembaruan stok, menyimpan data transaksi ke database, dan mencetak struk.
> Sequence Diagram menunjukkan hubungan teknis antara komponen sistem dalam menjalankan proses bisnis.

4) Class Diagram

> Class Diagram digunakan untuk menggambarkan struktur kelas dalam sistem beserta atribut, method, dan relasi antar kelas.
> Kelas User berperan sebagai superclass yang diturunkan ke kelas Admin dan Kasir. Kelas Keranjang memiliki relasi komposisi dengan DetailKeranjang yang merepresentasikan item pembelian. Kelas Produk menyimpan informasi produk dan dikelola oleh admin.
> Kelas Transaksi berhubungan dengan kelas Pembayaran, yang selanjutnya memiliki turunan EWallet. Kelas Struk dihasilkan dari proses transaksi, sedangkan kelas LaporanPenjualan digunakan oleh admin untuk melihat hasil penjualan.
> Class Diagram menjadi dasar perancangan struktur sistem yang mendukung seluruh proses bisnis.

3.Penjelasan penerapan prinsip SOLID:

1) Single Responsibility Principle (SRP)
Setiap kelas dalam sistem memiliki satu tanggung jawab yang jelas. Sebagai contoh, kelas Produk hanya bertanggung jawab terhadap pengelolaan data produk, kelas Pembayaran menangani proses pembayaran, dan kelas Struk bertanggung jawab dalam pencetakan struk.

2) Open/Closed Principle (OCP)
Sistem dirancang agar terbuka untuk pengembangan namun tertutup terhadap perubahan. Penambahan metode pembayaran baru dapat dilakukan dengan membuat subclass baru tanpa mengubah kelas Pembayaran yang sudah ada.

3) Liskov Substitution Principle (LSP)
Subclass seperti EWallet dapat menggantikan kelas induk Pembayaran tanpa memengaruhi perilaku sistem secara keseluruhan, sehingga menjaga konsistensi fungsionalitas.

4) Interface Segregation Principle (ISP)
Setiap kelas hanya memiliki method yang sesuai dengan perannya. Admin tidak dibebani dengan fungsi transaksi, sedangkan kasir tidak memiliki akses untuk mengelola data produk.

5) Dependency Inversion Principle (DIP)
Sistem bergantung pada abstraksi, bukan pada implementasi konkret. Dengan demikian, integrasi dengan payment gateway tertentu dapat diganti tanpa memengaruhi struktur utama sistem.

4.Kesimpulan dan refleksi singkat (keunggulan serta potensi pengembangan sistem):

Berdasarkan hasil perancangan, dapat disimpulkan bahwa sistem POS yang dikembangkan telah memiliki struktur yang terorganisir dengan baik dan mendukung proses bisnis penjualan secara efektif. Penggunaan berbagai diagram UML membantu dalam memvisualisasikan kebutuhan sistem serta hubungan antar komponen.
Penerapan prinsip SOLID menjadikan sistem lebih modular, mudah dikembangkan, dan mudah dipelihara.

Sebagai pengembangan ke depan, sistem ini masih dapat ditingkatkan dengan penambahan fitur seperti manajemen diskon, laporan penjualan secara real-time, integrasi dengan sistem berbasis cloud, serta pengembangan aplikasi berbasis mobile untuk meningkatkan fleksibilitas penggunaan sistem.

Quiz (Jawaban)
1. Jelaskan perbedaan aggregation dan composition serta berikan contoh penerapannya pada desain Anda.
Jawaban:

Aggregation adalah relasi has-a yang bersifat lemah, di mana objek bagian masih dapat berdiri sendiri meskipun objek induknya tidak ada.
Contoh pada desain Agri-POS:
Relasi Admin – Produk merupakan aggregation, karena produk tetap ada di sistem meskipun admin yang mengelolanya tidak aktif.

Composition adalah relasi yang bersifat kuat, di mana siklus hidup objek bagian bergantung pada objek induknya.
Contoh pada desain:
Relasi Keranjang – DetailKeranjang merupakan composition, karena DetailKeranjang tidak dapat berdiri sendiri tanpa adanya Keranjang dan akan hilang ketika keranjang dihapus..




2. Bagaimana prinsip Open/Closed dapat memastikan sistem mudah dikembangkan?
Jawaban:
Prinsip Open/Closed memastikan sistem mudah dikembangkan dengan cara memungkinkan penambahan fitur baru tanpa mengubah kode yang sudah ada. Dalam desain Agri-POS, hal ini diterapkan pada proses pembayaran menggunakan interface PaymentMethod. Ketika metode pembayaran baru ditambahkan, cukup membuat class baru yang mengimplementasikan interface tersebut tanpa perlu memodifikasi PaymentService, sehingga risiko bug pada fitur lama dapat diminimalkan.


3. Mengapa Dependency Inversion Principle (DIP) meningkatkan testability? Berikan contoh penerapannya.
Jawaban:
DIP membuat high-level module bergantung pada abstraksi (interface), bukan implementasi konkret. Ini meningkatkan testability karena:
Contoh:
java// Tanpa DIP - sulit di-test
class ProductService {
    private JdbcProductRepository repository = new JdbcProductRepository();
    // Butuh database real untuk testing
}

// Dengan DIP - mudah di-test
class ProductService {
    private ProductRepository repository; // Interface
    
    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }
}
