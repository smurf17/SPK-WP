# SPK-WP (Sistem Pendukung Keputusan - Weighted Product)

SPK-WP adalah aplikasi web berbasis PHP yang mengimplementasikan metode **Weighted Product (WP)** untuk sistem pendukung keputusan. Aplikasi ini dikembangkan untuk membantu pemilihan alternatif terbaik berdasarkan sejumlah kriteria dan bobot yang ditentukan.

---

## 📁 Struktur Proyek

```
/
├─ assets/       # Berkas CSS, JavaScript, gambar
├─ config/       # Pengaturan koneksi database
├─ misc/         # File utilitas atau library tambahan
├─ page/         # Halaman web (form, tabel, dsb.)
├─ index.php     # Halaman utama / entry point aplikasi
└─ README.md     # Dokumentasi proyek
```

---

## ⚙️ Instalasi & Setup

1. **Clone repo**

   ```bash
   git clone https://github.com/smurf17/SPK-WP.git
   cd SPK-WP
   ```

2. **Siapkan database**

   * Buat database MySQL, misalnya: `spk_wp`
   * Impor struktur dan data jika tersedia (file .sql)

3. **Konfigurasi database**

   * Buka file di `config/` dan sesuaikan pengaturan koneksi database (host, user, password, dbname)

4. **Jalankan aplikasi**

   * Buka browser dan akses: `http://localhost/SPK-WP`

---

## 🖥️ Panduan Penggunaan

1. **Kelola Kriteria**

   * Tambah nama kriteria, bobot, dan jenis (benefit/cost)

2. **Kelola Alternatif**

   * Input alternatif yang akan dievaluasi

3. **Perhitungan WP**

   * Jalankan metode WP untuk menghitung nilai akhir setiap alternatif
   * Tampilkan ranking dari yang terbaik ke terendah

---

## 📌 Ringkasan Metode Weighted Product

* Tiap nilai alternatif dipangkatkan dengan bobot kriteria.
* Hasil pangkat dikalikan antar kriteria untuk mendapatkan skor akhir.
* Skor tertinggi adalah alternatif terbaik.

---

## 🛠️ Kustomisasi & Pengembangan

* **Tambah kriteria/alternatif**: edit file di folder `page/`
* **UI/UX**: sesuaikan file CSS/JS di folder `assets/`
* **Keamanan**: tambahkan validasi input dan prepared statements untuk keamanan data

---

## 🔧 Teknologi Digunakan

* PHP (>=7.x)
* MySQL
* HTML, CSS, JavaScript

---

## 🚀 Tips Tambahan

* Backup database sebelum melakukan perubahan besar
* Tambahkan autentikasi untuk multi-user
* Tambahkan filter dan pagination untuk data besar

---

## 📌 Contoh Alur Penggunaan

1. Masuk ke aplikasi
2. Tambahkan beberapa kriteria dan bobotnya
3. Tambahkan alternatif dengan nilai-nilai untuk masing-masing kriteria
4. Jalankan perhitungan WP dan lihat hasil ranking

---

## 📬 Kontribusi

1. Fork repo
2. Buat branch fitur (`feature-x`)
3. Push dan buat pull request
4. Tambahkan deskripsi perubahan

---

## 📄 Lisensi

Proyek ini dibuat untuk tujuan pembelajaran dan bebas digunakan serta dimodifikasi.

---

Untuk pertanyaan atau pengembangan lebih lanjut, silakan buka issue di repositori GitHub.
