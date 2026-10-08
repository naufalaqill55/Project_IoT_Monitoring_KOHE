package com.example.monitoringkohe

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var tvSuhu: TextView
    private lateinit var tvAlertSuhu: TextView
    private lateinit var tvKelembapan: TextView
    private lateinit var tvAlertKelembapan: TextView
    private lateinit var tvAmonia: TextView
    private lateinit var tvAlertAmonia: TextView
    private lateinit var tvLogHeater: TextView
    private lateinit var tvLogKran: TextView
    private lateinit var tvStatusKoneksi: TextView

    private lateinit var switchHeater: Switch
    private lateinit var switchValve: Switch
    private lateinit var btnBukaLaporan: CardView

    private var countHeater = 0
    private var countKran = 0

    private val handler = Handler(Looper.getMainLooper())
    private lateinit var updateRunnable: Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Binding ID dari XML
        tvSuhu = findViewById(R.id.tvSuhu)
        tvAlertSuhu = findViewById(R.id.tvAlertSuhu)
        tvKelembapan = findViewById(R.id.tvKelembapan)
        tvAlertKelembapan = findViewById(R.id.tvAlertKelembapan)
        tvAmonia = findViewById(R.id.tvAmonia)
        tvAlertAmonia = findViewById(R.id.tvAlertAmonia)
        tvLogHeater = findViewById(R.id.tvLogHeater)
        tvLogKran = findViewById(R.id.tvLogKran)
        tvStatusKoneksi = findViewById(R.id.tvStatusKoneksi)

        switchHeater = findViewById(R.id.switchHeater)
        switchValve = findViewById(R.id.switchValve)
        btnBukaLaporan = findViewById(R.id.btnBukaLaporan)

        // Indikator status aplikasi berjalan lancar
        tvStatusKoneksi.text = "● Simulasi Aktif"
        tvStatusKoneksi.setBackgroundColor(Color.parseColor("#388E3C"))

        // Tombol pindah ke Halaman Laporan
        btnBukaLaporan.setOnClickListener {
            val intent = Intent(this, ReportActivity::class.java)
            startActivity(intent)
        }

        // Simulasi perubahan angka sensor secara live setiap 3 detik
        updateRunnable = object : Runnable {
            override fun run() {
                val suhu = (28.0 + Math.random() * 22.0).toFloat()
                val kelembapan = (35.0 + Math.random() * 35.0).toFloat()
                val amonia = (5.0 + Math.random() * 22.0).toFloat()

                // Suhu Logic
                tvSuhu.text = String.format(Locale.US, "%.1f °C", suhu)
                if (suhu < 30.0f) {
                    tvAlertSuhu.text = "⚠️ Suhu Terlalu Dingin (< 30°C)"
                    tvAlertSuhu.setTextColor(Color.parseColor("#F57C00"))
                } else if (suhu > 45.0f) {
                    tvAlertSuhu.text = "🚨 Suhu Terlalu Panas (> 45°C)!"
                    tvAlertSuhu.setTextColor(Color.parseColor("#D32F2F"))
                } else {
                    tvAlertSuhu.text = "✅ Status: Suhu Normal & Ideal"
                    tvAlertSuhu.setTextColor(Color.parseColor("#388E3C"))
                }

                // Kelembapan Logic
                tvKelembapan.text = String.format(Locale.US, "%.1f %%", kelembapan)
                if (kelembapan < 40.0f) {
                    tvAlertKelembapan.text = "⚠️ Kompos Terlalu Kering (< 40%)"
                    tvAlertKelembapan.setTextColor(Color.parseColor("#F57C00"))
                } else if (kelembapan > 60.0f) {
                    tvAlertKelembapan.text = "⚠️ Kompos Terlalu Basah (> 60%)"
                    tvAlertKelembapan.setTextColor(Color.parseColor("#F57C00"))
                } else {
                    tvAlertKelembapan.text = "✅ Status: Kelembapan Ideal"
                    tvAlertKelembapan.setTextColor(Color.parseColor("#388E3C"))
                }

                // Amonia Logic
                tvAmonia.text = String.format(Locale.US, "%.1f ppm", amonia)
                if (amonia > 20.0f) {
                    tvAlertAmonia.text = "🚨 BAHAYA: Amonia Tinggi! Buka Kran!"
                    tvAlertAmonia.setTextColor(Color.parseColor("#D32F2F"))
                } else {
                    tvAlertAmonia.text = "✅ Status: Kadar Amonia Aman"
                    tvAlertAmonia.setTextColor(Color.parseColor("#388E3C"))
                }

                handler.postDelayed(this, 3000)
            }
        }
        handler.post(updateRunnable)

        // Log Tombol Heater
        switchHeater.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                countHeater++
                tvLogHeater.text = "Total Aktif: $countHeater kali hari ini"
            }
        }

        // Log Tombol Kran
        switchValve.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                countKran++
                tvLogKran.text = "Total Terbuka: $countKran kali hari ini"
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(updateRunnable)

    }
}