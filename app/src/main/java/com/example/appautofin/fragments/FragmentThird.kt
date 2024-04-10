package com.example.appautofin.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.appautofin.R
import com.example.appautofin.activities.MainActivity
import com.example.appautofin.databinding.FragmentFirstBinding
import com.example.appautofin.databinding.FragmentThirdBinding

class FragmentThird : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentThirdBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageButtonSelectPdf.setOnClickListener {
            launcher.launch("application/pdf")
        }
    }


    private val launcher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ){uri ->
        uri?.let{
            binding.pdfView.fromUri(it).load()
            binding.textVTituloPagina.visibility = View.INVISIBLE
            binding.pdfView.visibility = View.VISIBLE
        }
    }

}