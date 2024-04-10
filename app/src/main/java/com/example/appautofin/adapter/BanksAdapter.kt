package com.example.appautofin.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.appautofin.R
import com.example.appautofin.model.Banks

class BanksAdapter(private var banksList:List<Banks>) : RecyclerView.Adapter<BanksViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BanksViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return BanksViewHolder(layoutInflater.inflate(R.layout.adapter_bank, parent, false))
    }


    override fun onBindViewHolder(holder: BanksViewHolder, position: Int) {
        val item = banksList[position]
        holder.render(item)
    }

    override fun getItemCount(): Int = banksList.size

    @SuppressLint("NotifyDataSetChanged")
    fun refreshBanks(banks:List<Banks>){
        this.banksList = banks
        notifyDataSetChanged()
    }
}