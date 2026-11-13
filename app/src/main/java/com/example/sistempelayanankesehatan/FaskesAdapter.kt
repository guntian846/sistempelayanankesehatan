package com.example.sistempelayanankesehatan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sistempelayanankesehatan.databinding.ItemFaskesBinding

class FaskesAdapter(
    private var listFaskes: List<Faskes>,
    private val onItemClick: (Faskes) -> Unit
) : RecyclerView.Adapter<FaskesAdapter.FaskesViewHolder>() {

    inner class FaskesViewHolder(private val binding: ItemFaskesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(faskes: Faskes) {
            binding.tvNamaFaskes.text = faskes.nama
            binding.tvKategori.text = faskes.kategori
            binding.tvAlamat.text = faskes.alamat
            itemView.setOnClickListener { onItemClick(faskes) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FaskesViewHolder {
        val binding = ItemFaskesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FaskesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FaskesViewHolder, position: Int) {
        holder.bind(listFaskes[position])
    }

    override fun getItemCount(): Int = listFaskes.size

    fun updateData(newList: List<Faskes>) {
        listFaskes = newList
        notifyDataSetChanged()
    }
}