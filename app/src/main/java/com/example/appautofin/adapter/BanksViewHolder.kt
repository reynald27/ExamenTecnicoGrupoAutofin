package com.example.appautofin.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.example.appautofin.R
import com.example.appautofin.model.Banks

class BanksViewHolder(val view: View):RecyclerView.ViewHolder(view) {

    val name = view.findViewById<TextView>(R.id.textViewBankName)
    val antiquity = view.findViewById<TextView>(R.id.textViewAge)
    val description = view.findViewById<TextView>(R.id.textViewDescription)
    val image = view.findViewById<ImageView>(R.id.imageViewBankLogo)

    fun render(banks: Banks){
        name.text = banks.name
        antiquity.text = banks.antiquity
        description.text = banks.description
        Glide.with(view.context).load(banks.imageUrl).transform(
            CenterCrop(),
            RoundedCorners(40)
        ) .into(image)

    }
}