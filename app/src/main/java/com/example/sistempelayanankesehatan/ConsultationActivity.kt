package com.example.sistempelayanankesehatan

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sistempelayanankesehatan.databinding.ActivityConsultationBinding
import kotlin.random.Random

class ConsultationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConsultationBinding
    private val TAG = "LifecycleConsultation"

    private val daftarPoli = arrayOf("Poli Umum", "Poli Gigi", "Poli Anak", "Poli Penyakit Dalam")
    private val daftarDokter = arrayOf("dr. Ahmad, Sp.PD", "dr. Siti Rahma", "dr. Budi Santoso", "dr. Dian, Sp.A")

    private var hasilPendaftaranText: String = ""
    private var nomorAntrean: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate dipanggil")

        binding = ActivityConsultationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set Adapter Spinner Poli
        val adapterPoli = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, daftarPoli)
        binding.spinnerPoli.adapter = adapterPoli

        // Set Adapter Spinner Dokter
        val adapterDokter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, daftarDokter)
        binding.spinnerDokter.adapter = adapterDokter

        // Pemulihan data saat layar dirotasi
        if (savedInstanceState != null) {
            val inputNama = savedInstanceState.getString("KEY_NAMA", "")
            val genderSelectedId = savedInstanceState.getInt("KEY_GENDER", -1)
            val jenisPasienSelectedId = savedInstanceState.getInt("KEY_JENIS_PASIEN", -1)
            val poliPos = savedInstanceState.getInt("KEY_POLI_POS", 0)
            val dokterPos = savedInstanceState.getInt("KEY_DOKTER_POS", 0)
            val isChecked = savedInstanceState.getBoolean("KEY_CHECK", false)
            hasilPendaftaranText = savedInstanceState.getString("KEY_HASIL", "")
            nomorAntrean = savedInstanceState.getString("KEY_ANTREAN", "")

            binding.etNama.setText(inputNama)
            if (genderSelectedId != -1) {
                binding.rgGender.check(genderSelectedId)
            }
            if (jenisPasienSelectedId != -1) {
                binding.rgJenisPasien.check(jenisPasienSelectedId)
            }
            binding.spinnerPoli.setSelection(poliPos)
            binding.spinnerDokter.setSelection(dokterPos)
            binding.cbSetuju.isChecked = isChecked
            binding.tvHasil.text = hasilPendaftaranText
        }

        binding.btnSubmit.setOnClickListener {
            val nama = binding.etNama.text.toString().trim()
            val genderId = binding.rgGender.checkedRadioButtonId
            val jenisPasienId = binding.rgJenisPasien.checkedRadioButtonId
            val poliDipilih = binding.spinnerPoli.selectedItem.toString()
            val dokterDipilih = binding.spinnerDokter.selectedItem.toString()
            val isChecked = binding.cbSetuju.isChecked

            if (nama.isEmpty()) {
                binding.tilNama.error = "Nama pasien wajib diisi!"
                return@setOnClickListener
            } else {
                binding.tilNama.error = null
            }

            if (genderId == -1) {
                Toast.makeText(this, "Pilih jenis kelamin terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (jenisPasienId == -1) {
                Toast.makeText(this, "Pilih jenis pembayaran (BPJS/Umum) terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (!isChecked) {
                Toast.makeText(this, "Centang persetujuan terlebih dahulu!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val genderText = if (genderId == R.id.rbLaki) "Laki-laki" else "Perempuan"
            val jenisPasienText = if (jenisPasienId == R.id.rbBpjs) "BPJS Kesehatan" else "Pasien Umum"

            // Buat nomor antrean otomatis jika belum dibuat
            if (nomorAntrean.isEmpty()) {
                val kodePoli = if (poliDipilih.contains("Gigi")) "G" else if (poliDipilih.contains("Anak")) "A" else "U"
                val angkaRandom = Random.nextInt(10, 99)
                nomorAntrean = "$kodePoli-$angkaRandom"
            }

            hasilPendaftaranText = """
                Pendaftaran Berhasil!
                • Nomor Antrean: $nomorAntrean
                • Nama Pasien: $nama
                • Jenis Kelamin: $genderText
                • Kategori Pasien: $jenisPasienText
                • Poliklinik: $poliDipilih
                • Dokter: $dokterDipilih
                • Status: Terkonfirmasi
            """.trimIndent()

            binding.tvHasil.text = hasilPendaftaranText
            Toast.makeText(this, "Pendaftaran Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        }
    }

    // Menyimpan seluruh data form ke Bundle sebelum terjadi rotasi layar
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("KEY_NAMA", binding.etNama.text.toString())
        outState.putInt("KEY_GENDER", binding.rgGender.checkedRadioButtonId)
        outState.putInt("KEY_JENIS_PASIEN", binding.rgJenisPasien.checkedRadioButtonId)
        outState.putInt("KEY_POLI_POS", binding.spinnerPoli.selectedItemPosition)
        outState.putInt("KEY_DOKTER_POS", binding.spinnerDokter.selectedItemPosition)
        outState.putBoolean("KEY_CHECK", binding.cbSetuju.isChecked)
        outState.putString("KEY_HASIL", hasilPendaftaranText)
        outState.putString("KEY_ANTREAN", nomorAntrean)
        Log.d(TAG, "onSaveInstanceState dipanggil")
    }

    // Lifecycle Callbacks (Logcat)
    override fun onStart() { super.onStart(); Log.d(TAG, "onStart dipanggil") }
    override fun onResume() { super.onResume(); Log.d(TAG, "onResume dipanggil") }
    override fun onPause() { super.onPause(); Log.d(TAG, "onPause dipanggil") }
    override fun onStop() { super.onStop(); Log.d(TAG, "onStop dipanggil") }
    override fun onDestroy() { super.onDestroy(); Log.d(TAG, "onDestroy dipanggil") }
}