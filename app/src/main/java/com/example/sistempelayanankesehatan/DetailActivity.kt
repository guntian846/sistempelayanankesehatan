package com.example.sistempelayanankesehatan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.sistempelayanankesehatan.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val faskes = intent.getSerializableExtra("EXTRA_FASKES") as? Faskes

        faskes?.let {
            binding.tvDetailNama.text = it.nama
            binding.tvDetailKategori.text = "Jenis: ${it.kategori}"
            binding.tvDetailAlamat.text = "Alamat: ${it.alamat}"
            binding.tvDetailJam.text = "Jam Operasional: ${it.jamOperasional}"
            binding.tvDetailDokter.text = "Dokter Bertugas: ${it.dokter}"
            binding.tvDetailDeskripsi.text = it.deskripsi
        }

        binding.btnDaftar.setOnClickListener {
            val intent = Intent(this, ConsultationActivity::class.java)
            intent.putExtra("NAMA_FASKES", faskes?.nama)
            startActivity(intent)
        }
    }
}