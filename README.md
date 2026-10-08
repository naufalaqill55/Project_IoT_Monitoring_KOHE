🌿 Project IoT Monitoring KOHE

Proyek ini adalah sistem monitoring berbasis Internet of Things (IoT) yang dirancang untuk memantau kapasitas/volume kotoran hewan (KOHE) serta mendeteksi kadar gas amonia secara real-time. Proyek ini terdiri dari dua bagian utama: perangkat keras (mikrokontroler/sensor) dan aplikasi seluler (Android).

🚀 Fitur Utama

Pemantauan Kapasitas (Volume): Menggunakan sensor ultrasonik (JSN-SR04T) untuk mengukur ketinggian/jarak secara presisi.

Deteksi Bau / Gas Amonia: Menggunakan sensor gas (MQ-137 / MQ Series) untuk memantau kualitas udara di sekitar penampungan.

Sistem Catu Daya Mandiri: Menggunakan Panel Surya, Solar Charge Controller (SCC), dan Aki 12V yang diturunkan tegangannya (Step-Down) menjadi 5V untuk menyuplai daya ke mikrokontroler.

Kontrol Otomatis: Dilengkapi modul Relay untuk mengontrol Solenoid Valve secara otomatis berdasarkan data pembacaan sensor.

Aplikasi Android: Aplikasi berbasis Android untuk memantau data secara jarak jauh.

📁 Struktur Direktori

Repository ini mencakup seluruh source code aplikasi Android dan firmware Arduino:

/app, /gradle, dll: Source code untuk aplikasi Android (dibangun menggunakan Android Studio).

/IoT_KOHE/: Direktori yang berisi kode untuk perangkat keras dan dokumentasi sistem.

IoT_KOHE.ino: Kode program utama untuk mikrokontroler (Arduino IDE).

Alur IoT: Penjelasan logika dan alur kerja sistem.

Flowchart 1: Diagram alir dari sistem.

Wireingnya: Dokumentasi diagram pengkabelan (wiring) komponen hardware.

🛠️ Persiapan & Instalasi

1. Bagian IoT (Arduino)

Buka folder IoT_KOHE dan buka file IoT_KOHE.ino menggunakan Arduino IDE.

Pastikan Anda sudah menginstal library yang dibutuhkan (misalnya library untuk WiFi jika menggunakan modul ESP/WiFi bawaan).

Sesuaikan konfigurasi nama WiFi (ssid) dan kata sandi (password) di dalam kode.

Upload kode ke papan mikrokontroler Anda (misal: Arduino Mega + ESP / NodeMCU).

Rangkai perangkat keras sesuai panduan di file Wireingnya.

2. Bagian Aplikasi (Android)

Clone atau download repository ini.

Buka folder utama repository menggunakan Android Studio.

Tunggu proses Gradle Sync selesai.

Hubungkan smartphone Anda atau gunakan emulator, lalu klik Run (Shift + F10) untuk menginstal aplikasi.

Dibuat untuk Tugas Kuliah - Monitoring KOHE.
