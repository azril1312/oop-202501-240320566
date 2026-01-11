# Laporan Praktikum Minggu 9
Topik: [Bab 9 – Exception Handling, Custom Exception, dan Penerapan Design Pattern]

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
1. Menjelaskan perbedaan antara error dan exception.
2. Mengimplementasikan try–catch–finally dengan tepat.
3. Membuat custom exception sesuai kebutuhan program.
4. Mengintegrasikan exception handling ke dalam aplikasi sederhana (kasus keranjang belanja).
5. (Opsional) Menerapkan design pattern sederhana (Singleton/MVC) dan unit testing dasar.

---

## Dasar Teori
### 1. Error vs Exception

- Error → kondisi fatal, tidak dapat ditangani (contoh: OutOfMemoryError).
- Exception → kondisi tidak normal yang dapat ditangani oleh program.

### 2. Struktur try–catch–finally

```java
try {
    // kode yang berpotensi menimbulkan kesalahan
} catch (Exception e) {
    // penanganan
} finally {
    // blok yang selalu dijalankan
}
```

### 3. Membuat Custom Exception

```java
package com.upb.agripos;

public class InvalidQuantityException extends Exception {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
```

---

## Studi Kasus Agri-POS: Keranjang Belanja

Keranjang belanja harus memvalidasi:

- Jumlah pembelian > 0
- Produk ada dalam keranjang
- Stok mencukupi

Kesalahan–kesalahan tersebut ditangani menggunakan custom exception.

---

## Langkah Praktikum
1. Membuat Custom Exception
2. Model Product dengan Stok
3. Implementasi ShoppingCart dengan Exception Handling
4. Main Program untuk Menguji Exception Handling
5. Commit (message yang digunakan: week9-exception)

---

## Kode Program
Tuliskan kode utama yang dibuat:

```java
package com.upb.agripos;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am [Bunga Maura Aulya]-[240320562] (Week9-exception)");

        ShoppingCart cart = new ShoppingCart();
        Product p1 = new Product("P01", "Pupuk Organik", 25000, 3);

        try {
            cart.addProduct(p1, -1);
        } catch (InvalidQuantityException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.removeProduct(p1);
        } catch (ProductNotFoundException e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }

        try {
            cart.addProduct(p1, 5);
            cart.checkout();
        } catch (Exception e) {
            System.out.println("Kesalahan: " + e.getMessage());
        }
    }
}

```

---

## Hasil Eksekusi
Screenshot hasil eksekusi program:  
<<<<<<< HEAD
<img width="877" height="119" alt="Screenshot 2026-01-11 204810" src="https://github.com/user-attachments/assets/eb65b914-f9e7-4b4c-a07c-7d8bf6cb5f67" />


=======
![alt text](<Screenshot 2026-01-11 204810.png>)
>>>>>>> 20cbb75 (week10-pattern-testing)
---

## Analisis
- Jelaskan bagaimana kode berjalan. 
    
      Program dieksekusi melalui kelas MainExceptionDemo yang berperan sebagai kelas utama. Saat program mulai dijalankan, sistem terlebih dahulu menampilkan identitas praktikan sebagai penanda awal eksekusi. Selanjutnya, program membentuk sebuah objek ShoppingCart yang berfungsi sebagai keranjang belanja serta sebuah objek Product yang merepresentasikan data produk, meliputi kode produk, nama, harga, dan jumlah stok.

      Setelah inisialisasi objek, program mensimulasikan beberapa kondisi kesalahan dengan memanfaatkan blok try–catch. Pada pengujian pertama, method addProduct() dipanggil dengan jumlah negatif sehingga memicu InvalidQuantityException. Exception ini kemudian ditangkap dan pesan kesalahan ditampilkan ke layar. Pada pengujian berikutnya, method removeProduct() dijalankan ketika produk belum terdapat di dalam keranjang, sehingga menyebabkan ProductNotFoundException. Pada skenario terakhir, produk ditambahkan dengan jumlah yang melebihi stok yang tersedia, lalu proses checkout() dijalankan, yang menghasilkan exception akibat stok tidak mencukupi. Melalui alur tersebut, program memperlihatkan penerapan custom exception dalam memvalidasi proses pada keranjang belanja secara aman dan terstruktur.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya. 

      Pendekatan yang digunakan pada praktikum minggu ini menitikberatkan pada penerapan Exception Handling, khususnya dengan memanfaatkan custom exception untuk menangani berbagai kondisi kesalahan secara lebih spesifik. Berbeda dengan praktikum minggu sebelumnya, proses validasi masih dilakukan menggunakan logika dasar seperti percabangan if–else tanpa adanya mekanisme penanganan kesalahan yang terorganisir.

      Dengan penerapan custom exception, kesalahan tidak hanya dapat diidentifikasi dengan jelas, tetapi juga dipisahkan dari alur logika utama program. Hal ini menjadikan struktur kode lebih rapi, mudah dipahami, serta lebih mudah dalam proses pengembangan dan pemeliharaan. Selain itu, pendekatan ini meningkatkan stabilitas program karena kesalahan dapat ditangani tanpa menghentikan seluruh proses aplikasi.

- Kendala yang dihadapi dan cara mengatasinya.  
      Kendala utama yang ditemui dalam praktikum ini adalah menentukan kondisi validasi yang tepat serta memastikan exception dilemparkan pada method yang sesuai, seperti tambahProduk, hapusProduk, dan checkout. Selain itu, terdapat permasalahan ketika exception belum didefinisikan atau belum ditangani dengan benar, sehingga menyebabkan program berhenti secara tidak terduga.

      Permasalahan tersebut diatasi dengan mendefinisikan custom exception secara terpisah, memperbaiki dan memperjelas pesan kesalahan agar lebih informatif, serta menerapkan blok try–catch pada kelas utama. Dengan penerapan tersebut, setiap kesalahan dapat ditangani dengan baik sehingga program tetap berjalan sesuai dengan alur yang telah dirancang.

---

## Kesimpulan
Berdasarkan praktikum yang telah dilakukan, penerapan custom exception pada sistem keranjang belanja memungkinkan program melakukan validasi kesalahan secara terstruktur dan terkontrol. Dengan memanfaatkan mekanisme try–catch, kesalahan seperti jumlah produk tidak valid, produk tidak ditemukan, dan stok tidak mencukupi dapat ditangani dengan baik tanpa menghentikan jalannya program. Pendekatan ini membuat kode menjadi lebih aman, mudah dipelihara, serta meningkatkan kejelasan alur logika dalam pengembangan aplikasi berbasis Object-Oriented Programming.


---

## Quiz
1. Jelaskan perbedaan error dan exception.

   **Jawaban:** Error merupakan kesalahan serius yang umumnya terjadi di luar kendali program dan sulit atau tidak dapat ditangani, seperti kehabisan memori. Exception adalah kesalahan yang terjadi saat program berjalan dan masih dapat ditangani menggunakan mekanisme try–catch.

2. Apa fungsi finally dalam blok try–catch–finally?

   **Jawaban:** Blok finally digunakan untuk mengeksekusi kode yang harus selalu dijalankan, baik terjadi exception maupun tidak, seperti menutup resource atau membersihkan data sementara.
   
3. Mengapa custom exception diperlukan?

   **Jawaban:** Custom exception diperlukan untuk menangani kesalahan yang spesifik sesuai kebutuhan aplikasi, sehingga pesan kesalahan lebih jelas, logika program lebih terstruktur, dan proses debugging menjadi lebih mudah.
   
4. Berikan contoh kasus bisnis dalam POS yang membutuhkan custom exception.

   **Jawaban:** Contoh kasusnya adalah ketika jumlah barang yang dibeli melebihi stok yang tersedia. Dalam kondisi ini, custom exception digunakan untuk memberikan peringatan bahwa stok tidak mencukupi tanpa menghentikan proses aplikasi.
   
