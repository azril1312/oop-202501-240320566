# Laporan Praktikum Minggu 5 
Topik: Abstraction (Abstract Class & Interface)

## Identitas
- Nama  : [Azril Rabbani Fawa]
- NIM   : [240320566]
- Kelas : [3DSRA]

---

## Tujuan
Tujuan praktikum Abstraction (Abstract Class & Interface) adalah untuk memahami penerapan konsep abstraksi dalam pemrograman berorientasi objek (OOP), di mana mahasiswa belajar bagaimana menyembunyikan detail implementasi dan menampilkan hanya fitur penting dari suatu objek. Melalui penggunaan abstract class dan interface, mahasiswa diharapkan mampu merancang struktur program yang modular, fleksibel, serta mudah dikembangkan dengan menerapkan prinsip pewarisan dan kontrak perilaku antar kelas secara tepat.

---

## Dasar Teori
Abstraksi adalah proses menyederhanakan kompleksitas dengan menampilkan elemen penting dan menyembunyikan detail implementasi. 
1. Abstract class: tidak dapat diinstansiasi, dapat memiliki method abstrak (tanpa badan) dan non-abstrak. Dapat menyimpan state (field).
  
2. Interface: kumpulan kontrak (method tanpa implementasi konkret). Sejak Java 8 mendukung default method. Mendukung multiple inheritance (class dapat mengimplementasikan banyak interface).
  
3. Gunakan abstract class bila ada shared state dan perilaku dasar; gunakan interface untuk mendefinisikan kemampuan/kontrak lintas hierarki.
Dalam konteks Agri-POS, Pembayaran dapat dimodelkan sebagai abstract class dengan method abstrak prosesPembayaran() dan biaya(). Implementasi konkritnya: Cash dan EWallet. Kemudian, interface seperti Validatable (mis. verifikasi OTP) dan Receiptable (mencetak bukti) dapat diimplementasikan oleh jenis pembayaran yang relevan.

---

## Langkah Praktikum
Tuliskan Langkah-langkah dalam prakrikum
1. Abstract Class – Pembayaran
Buat Pembayaran (abstract) dengan field invoiceNo, total dan method:
>double biaya() (abstrak) → biaya tambahan (fee).
>boolean prosesPembayaran() (abstrak) → mengembalikan status berhasil/gagal.
>double totalBayar() (konkrit) → return total + biaya();.  
2. Subclass Konkret
>Cash → biaya = 0, proses = selalu berhasil jika tunai >= totalBayar().
>EWallet → biaya = 1.5% dari total; proses = membutuhkan validasi.  
3. Interface
>Validatable → boolean validasi(); (contoh: OTP).
>Receiptable → String cetakStruk();
4. CMultiple Inheritance via Interface
>EWallet mengimplementasikan dua interface: Validatable, Receiptable.
>Cash setidaknya mengimplementasikan Receiptable.
5. Main Class
>Buat MainAbstraction.java untuk mendemonstrasikan pemakaian Pembayaran (polimorfik).
>Tampilkan hasil proses dan struk. Di akhir, panggil CreditBy.print("[NIM]", "[Nama]").
6. Commit dan Push
Commit dengan pesan: week5-abstraction-interface.

---

## Kode Program
(Tuliskan kode utama yang dibuat, contoh:  
// receiptable 
public interface Receiptable {
    String cetakStruk();
}

// validatable
public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}

// cash 
public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

// ewallet
public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

// pembayaran
public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

// transferbank
public TransferBank(String invoiceNo, double total, String bankTujuan, boolean otpValid) {
        super(invoiceNo, total);
        this.bankTujuan = bankTujuan;
        this.otpValid = otpValid;
    }

// CreditBy
public class CreditBy {
    public static void print(String nama, String nim) {
        System.out.println("\ncredit by: " + nama + " - " + nim);
    }

// abstraction
public static void main(String[] args) {
        Pembayaran cash = new Cash("INV-001", 87000, 100000);
        Pembayaran ew = new EWallet("INV-002", 175000, "azrilrabbani@ewallet", "123456");
        Pembayaran transfer = new TransferBank("INV-003", 150000, "BCA", true);

)
---

## Hasil Eksekusi
c:\Users\ASUS\Documents\abstraction interface\Screenshot 2025-11-10 184916.png
---

## Analisis
(
- Jelaskan bagaimana kode berjalan:  
Pada praktikum ini, program berjalan dengan memanfaatkan konsep abstraksi dalam OOP, di mana kelas Pembayaran dijadikan sebagai abstract class yang berisi kerangka dasar metode prosesPembayaran(), validasiPembayaran(), dan cetakStruk(). Kelas turunan seperti Cash dan EWallet mengimplementasikan metode tersebut sesuai jenis pembayarannya masing-masing. Selain itu, interface Receiptable dan Validatable digunakan sebagai kontrak yang memastikan setiap kelas yang menggunakannya memiliki fungsi yang sama. Saat program dijalankan melalui MainAbstraction.java, pengguna memilih metode pembayaran dan memasukkan nominal, kemudian program memanggil objek kelas yang sesuai dan menampilkan hasil pembayaran serta struk.

- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya: 
Pendekatan pada minggu ini berbeda dengan minggu sebelumnya karena menggunakan abstraksi dan interface, sedangkan minggu sebelumnya lebih berfokus pada pewarisan (inheritance) dan polimorfisme. Pada praktikum kali ini, fokus utamanya adalah menyembunyikan detail implementasi dan membuat kerangka umum yang dapat digunakan oleh berbagai kelas turunan dengan cara yang lebih fleksibel.

- Kendala yang dihadapi dan cara mengatasinya: 
Kendala yang dihadapi selama praktikum adalah error saat kompilasi di terminal Windows, terutama karena penggunaan wildcard ** pada perintah javac tidak dikenali oleh PowerShell. Kendala ini diatasi dengan menggunakan perintah alternatif javac $(dir -Recurse -Filter *.java | % { $_.FullName }) yang mampu mengompilasi semua file Java secara rekursif. Setelah itu, program berhasil dijalankan dengan benar dan menampilkan hasil sesuai yang diharapkan. 
)
---

## Kesimpulan
Dengan menggunakan abstract class dan interface, program menjadi lebih terstruktur, fleksibel, dan mudah dikembangkan karena setiap kelas memiliki tanggung jawab yang jelas. Konsep abstraksi membantu menyembunyikan detail implementasi dan hanya menampilkan fungsionalitas penting yang dibutuhkan, sehingga kode lebih rapi, mudah dikelola, serta mendukung prinsip modularitas dalam pemrograman berorientasi objek (OOP).

---

## Quiz
(1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.
Jawaban: Abstract class digunakan sebagai kerangka dasar bagi kelas turunan yang memiliki hubungan hierarki dan berbagi perilaku umum. Di dalamnya dapat berisi metode abstrak (tanpa isi) maupun metode dengan implementasi. Sementara itu, interface digunakan sebagai kontrak perilaku yang harus diimplementasikan oleh kelas mana pun, tanpa memperhatikan hubungan pewarisan. Interface hanya berisi deklarasi metode tanpa implementasi (sebelum Java 8) dan bersifat murni abstrak. Dengan demikian, abstract class cocok untuk menggambarkan “apa yang dimiliki bersama”, sedangkan interface untuk menggambarkan “apa yang harus dilakukan”.
 

2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?
Jawaban: Java tidak mengizinkan pewarisan ganda dari class karena dapat menimbulkan konflik pewarisan (seperti diamond problem), tetapi mengizinkan implementasi ganda dari beberapa interface. Hal ini aman karena interface tidak membawa implementasi konkret (hanya kontrak perilaku), sehingga tidak menimbulkan benturan antar-metode atau properti. Dengan menggunakan beberapa interface, sebuah kelas dapat mewarisi banyak perilaku tanpa menimbulkan ambiguitas dalam kode.

3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.
Jawaban: Dalam contoh program Agri-POS, kelas Pembayaran paling tepat dijadikan abstract class karena menjadi dasar bagi berbagai jenis pembayaran seperti Cash dan EWallet, serta memiliki logika umum yang bisa diwariskan. Sementara Receiptable dan Validatable lebih tepat dijadikan interface karena hanya berisi kontrak perilaku (seperti cetakStruk() dan validasiPembayaran()) yang wajib diimplementasikan oleh setiap kelas pembayaran, tanpa memerlukan implementasi dasar dari superclass.
)
