# Panduan Export Laporan ke Excel

## Deskripsi Fitur
Fitur ini memungkinkan pengguna untuk mengexport laporan penjualan dalam format Excel (.xlsx) yang dapat dibuka dan diedit menggunakan Microsoft Excel atau aplikasi spreadsheet lainnya.

## Cara Menggunakan

### 1. Akses Fitur Export
- Buka aplikasi AgriPOS
- Login sebagai Admin atau user yang memiliki akses ke menu Laporan
- Klik menu **"Laporan"** atau **"Report"**
- Anda akan melihat halaman Laporan Penjualan dengan data transaksi

### 2. Filter Data (Opsional)
Sebelum export, Anda dapat memfilter data berdasarkan:
- **Rentang Tanggal**: Pilih tanggal mulai dan akhir
- **Metode Pembayaran**: Pilih "Semua", "Tunai", atau "E-Wallet"
- Klik tombol **"Tampilkan"** untuk menerapkan filter
- Klik **"Refresh"** untuk memuat ulang data dari awal

### 3. Lakukan Export
1. Klik tombol **"Export Laporan"** di bagian bawah halaman
2. Dialog file chooser akan muncul
3. Pilih lokasi folder tempat Anda ingin menyimpan file
4. Masukkan nama file atau gunakan nama default: `Laporan_Penjualan_[TANGGAL].xlsx`
5. Klik tombol **"Save"** untuk menyimpan file
6. File Excel akan otomatis dibuka di aplikasi default Anda

### 4. Isi File Excel
File Excel yang dihasilkan berisi:

#### Bagian Header
- Judul: "LAPORAN PENJUALAN DETAIL"
- Tanggal export

#### Tabel Data Penjualan
| No | Tanggal | Metode Pembayaran | Detail Produk | Jumlah (Rp) |
|---|---|---|---|---|
| 1 | 2026-01-21 | Tunai | 1. Beras 2kg x2 | 100,000 |
| 2 | 2026-01-21 | E-Wallet | 1. Pupuk x1 | 50,000 |

Kolom-kolom:
- **No**: Nomor urut
- **Tanggal**: Tanggal transaksi
- **Metode Pembayaran**: Tunai atau E-Wallet
- **Detail Produk**: Daftar produk yang dibeli beserta jumlah
- **Jumlah (Rp)**: Total harga transaksi (formatted sebagai currency)

#### Bagian Ringkasan
- **Total Penjualan**: Jumlah total dari semua transaksi
- **Jumlah Transaksi**: Banyaknya transaksi

## Fitur Excel

### Format Professional
- Header dengan warna gelap dan teks putih
- Border pada semua cell
- Format currency (Rp) untuk kolom jumlah
- Font yang jelas dan mudah dibaca

### Lebar Kolom Otomatis
Semua kolom sudah dikonfigurasi dengan lebar optimal untuk menampilkan data dengan baik.

### Dapat Diedit
- File Excel dapat diedit lebih lanjut menggunakan Microsoft Excel atau LibreOffice
- Anda dapat menambahkan rumus, chart, atau analisis tambahan

## File yang Terlibat

### Dependencies (pom.xml)
```xml
<!-- Apache POI for Excel Export -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.5</version>
</dependency>

<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.5</version>
</dependency>
```

### Kelas-kelas
1. **ExcelExportService.java** (`/src/main/java/com/upb/agripos/service/`)
   - Kelas service untuk handle export ke Excel
   - Method: `exportTransactionsToExcel()` dan `exportSummaryToExcel()`

2. **ReportView.java** (`/src/main/java/com/upb/agripos/view/`)
   - View untuk menampilkan laporan
   - Method: `handleExportExcel()` untuk trigger export

## Troubleshooting

### File tidak tersimpan
- Pastikan Anda memiliki permission untuk menulis di folder yang dipilih
- Coba simpan di folder Documents atau Desktop

### File tidak terbuka otomatis
- Meskipun file tidak terbuka otomatis, file sudah disimpan di lokasi yang Anda pilih
- Anda dapat membuka file secara manual dari file manager

### Data tidak lengkap
- Pastikan Anda sudah mengklik "Tampilkan" atau "Refresh" untuk memuat data terbaru
- Jika masih tidak ada data, pastikan ada transaksi di sistem

### Aplikasi Error saat export
- Periksa bahwa folder tempat penyimpanan tidak sedang membuka file Excel yang sama
- Tutup file Excel jika sudah terbuka, lalu coba export lagi

## Contoh Penggunaan Laporan

Laporan Excel ini dapat digunakan untuk:
1. **Analisis Penjualan**: Melihat tren penjualan berdasarkan tanggal dan metode pembayaran
2. **Rekapitulasi**: Membuat ringkasan untuk laporan bulanan atau tahunan
3. **Audit**: Memverifikasi transaksi yang telah dilakukan
4. **Presentasi**: Membuat slide presentasi dengan data penjualan
5. **Backup**: Menyimpan data penjualan dalam format yang dapat diakses di masa depan

## Update Future

Fitur ini dapat dikembangkan lebih lanjut dengan:
- Export ke format PDF
- Export ringkasan dengan chart/grafik
- Schedule export otomatis
- Email export langsung
- Format laporan yang dapat dikustomisasi
