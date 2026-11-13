package com.example.sistempelayanankesehatan

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sistempelayanankesehatan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: FaskesAdapter
    private val TAG = "LifecycleMain"

    private val dataFaskesLengkap = listOf(
        Faskes(
            id = 1,
            nama = "RSUD Pringsewu",
            kategori = "Rumah Sakit",
            alamat = "Jalan Ahmad Yani, Pajar Agung, Kecamatan Pringsewu, Kabupaten Pringsewu",
            jamOperasional = "24 Jam",
            dokter = "dr. Ahmad, Sp.PD",
            deskripsi = "RSUD Tipe B rujukan utama Kota Pringsewu."
        ),
        Faskes(
            id = 2,
            nama = "RS Umum Surya Asih",
            kategori = "Rumah Sakit",
            alamat = "Jl. Jenderal Ahmad Yani No. 51, Pekon Sidoharjo, Kecamatan Pringsewu",
            jamOperasional = "24 Jam",
            dokter = "dr. Budi Santoso",
            deskripsi = "Rumah sakit swasta dengan fasilitas rawat inap lengkap."
        ),
        Faskes(
            id = 3,
            nama = "Rumah Sakit Umum (RSU) Mitra Husada Pringsewu",
            kategori = "Rumah Sakit",
            alamat = "Jl. Jenderal Ahmad Yani No. 147, Pringsewu",
            jamOperasional = "24 Jam",
            dokter = "dr. Dian, Sp.A",
            deskripsi = "Rumah sakit pelayanan medis spesialis."
        ),
        Faskes(
            id = 4,
            nama = "Puskesmas Pringsewu",
            kategori = "Puskesmas",
            alamat = "Jl. Johar 2 No.15, Pringsewu Timur, Kecamatan Pringsewu, Kabupaten Pringsewu",
            jamOperasional = "08:00 - 15:00 WIB",
            dokter = "dr. Siti Rahma",
            deskripsi = "Puskesmas Induk pelayanan umum dan KIA."
        ),
        Faskes(
            id = 5,
            nama = "Puskesmas Sukoharjo",
            kategori = "Puskesmas",
            alamat = "Jl. Raya Kesehatan, Sukoharjo III, Kec. Sukoharjo, Kabupaten Pringsewu",
            jamOperasional = "08:00 - 14:00 WIB",
            dokter = "dr. Rina",
            deskripsi = "Puskesmas pelayanan masyarakat wilayah Sukoharjo."
        ),
        Faskes(
            id = 6,
            nama = "Klinik Sae Waras",
            kategori = "Klinik",
            alamat = "Jl. Raya Sukoyoso, Sukoharjo III, Kecamatan Sukoharjo, Kabupaten Pringsewu",
            jamOperasional = "08:00 - 20:00 WIB",
            dokter = "dr. Hendra",
            deskripsi = "Klinik pratama rawat jalan dan pengobatan umum."
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate dipanggil")

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inisialisasi RecyclerView, Adapter, dan ViewHolder
        adapter = FaskesAdapter(dataFaskesLengkap) { faskes ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("EXTRA_FASKES", faskes)
            startActivity(intent)
        }

        binding.rvFaskes.layoutManager = LinearLayoutManager(this)
        binding.rvFaskes.adapter = adapter

        // Filter Spinner
        val kategoriList = arrayOf("Semua", "Rumah Sakit", "Puskesmas", "Klinik")
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, kategoriList)
        binding.spinnerFilter.adapter = spinnerAdapter

        binding.spinnerFilter.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val kategoriDipilih = kategoriList[position]
                if (kategoriDipilih == "Semua") {
                    adapter.updateData(dataFaskesLengkap)
                } else {
                    val filteredList = dataFaskesLengkap.filter { it.kategori == kategoriDipilih }
                    adapter.updateData(filteredList)
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    // Log Lifecycle Lengkap (onCreate - onDestroy)
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart dipanggil") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume dipanggil") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause dipanggil") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop dipanggil") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy dipanggil") }
}