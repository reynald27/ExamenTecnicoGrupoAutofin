package com.example.appautofin.fragments

import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.appautofin.activities.MainActivity
import com.example.appautofin.activities.MapActivity
import com.example.appautofin.databinding.FragmentFourthBinding
import com.google.android.gms.maps.SupportMapFragment

class FragmentFourth : Fragment() {
    private lateinit var binding: FragmentFourthBinding
    private var values: ContentValues? = null
    private var imageUri: Uri? = null
    private var thumbnail: Bitmap? = null
    var imageurl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFourthBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_fourth, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val context = (activity as MainActivity).activityContext

        binding.buttonVerMapa.setOnClickListener {
            val myIntent: Intent = Intent(context, MapActivity::class.java)

            binding.buttonVerMapa.isClickable = false
            binding.buttonVerMapa.text = "Cargando espera..."
            //myIntent.putExtra("editTextLatitude", binding.editTextLatitude.text.toString());
            //myIntent.putExtra("editTextLongitude", binding.editTextLongitude.text.toString());

            //myIntent.getStringExtra(binding.editTextLatitude.text.toString()).toString()
            //myIntent.getStringExtra(binding.editTextLongitude.text.toString()).toString()

            this.startActivity(myIntent)
            context.finish()
        }

    }

}
