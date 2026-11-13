# Sistem Pelayanan Kesehatan Pringsewu (Android)

Aplikasi mobile berbasis Android yang menyediakan informasi daftar fasilitas kesehatan (Rumah Sakit dan Puskesmas) di wilayah Kabupaten Pringsewu serta fitur pendaftaran layanan pasien secara digital.

## Fitur Utama
* **Daftar Fasilitas Kesehatan:** Menampilkan informasi lengkap Rumah Sakit dan Puskesmas di Pringsewu (seperti RSUD Pringsewu, RS Umum Surya Asih, RSU Mitra Husada Pringsewu, dan Puskesmas Pringsewu) beserta kategori dan alamat lengkapnya.
* **Filter Kategori Layanan:** Menyaring tampilan daftar fasilitas kesehatan berdasarkan jenis layanan melalui menu *dropdown*.
* **Form Pendaftaran Layanan:** 
  * Input data nama lengkap pasien dan pilihan jenis kelamin.
  * Pilihan kategori pasien / pembayaran (**BPJS Kesehatan** atau **Pasien Umum**).
  * Pemilihan **Poliklinik** (seperti Poli Umum) dan **Dokter Spesialis**.
  * Validasi persetujuan syarat & ketentuan pendaftaran.
* **Nomor Antrean Otomatis:** Menampilkan ringkasan konfirmasi pendaftaran berhasil beserta nomor antrean pasien secara langsung setelah formulir dikirim.

## Teknologi yang Digunakan
* **Bahasa Pemrograman:** Kotlin
* **IDE:** Android Studio
* **UI Components:** Material Design Components (TextInputLayout, RadioButton, Spinner/Dropdown, CheckBox, CardView/RecyclerView)
* **Build System:** Gradle (Kotlin DSL)

## Cara Menjalankan Proyek
1. *Clone* atau unduh repository ini ke komputer Anda.
2. Buka folder proyek menggunakan **Android Studio**.
3. Tunggu hingga proses *Gradle Sync* selesai.
4. Jalankan aplikasi melalui Emulator Android atau perangkat fisik (*USB Debugging*).
