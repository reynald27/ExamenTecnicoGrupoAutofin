package com.example.appautofin.fragments

import android.Manifest
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.appautofin.activities.MainActivity
import com.example.appautofin.databinding.FragmentFirstBinding
import java.io.File


class FragmentFirst : Fragment() {
    private lateinit var binding: FragmentFirstBinding
    private var values: ContentValues? = null
    private var imageUri: Uri? = null
    private var thumbnail: Bitmap? = null
    var imageurl: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_first, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val context = (activity as MainActivity).activityContext

        binding.buttonEncenderCamara.setOnClickListener {
            if (hasPermissions()) {
                openCamera()
            } else {
                requestPermissions()
            }

        }
    }

    private fun hasPermissions(): Boolean {
        val context = (activity as MainActivity).activityContext

        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        val context = (activity as MainActivity).activityContext

        ActivityCompat.requestPermissions(
            context,
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            ),
            STORAGE_PERMISSION_CODE
        )
    }

    private fun openCamera() {
        val context = (activity as MainActivity).activityContext
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)

        //Proceso para guardar la imagen en el almacenamiento interno del telefono
        values = ContentValues()
        values!!.put(MediaStore.Images.Media.TITLE, "MyPicture")
        values!!.put(
            MediaStore.Images.Media.DESCRIPTION,
            "Photo taken on " + System.currentTimeMillis()
        )
        imageUri = context.contentResolver.insert(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values
        )

        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val context = (activity as MainActivity).activityContext

        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            CAMERA_REQUEST_CODE -> if (requestCode == CAMERA_REQUEST_CODE) if (resultCode == AppCompatActivity.RESULT_OK) {
                try {
                    thumbnail = MediaStore.Images.Media.getBitmap(context.contentResolver, imageUri)
                    binding.imageVFotoPerfil!!.setImageBitmap(thumbnail)
                    //Obtiene la ruta donde se encuentra guardada la imagen.
                    imageurl = getRealPathFromURI(imageUri)
                    Toast.makeText(context, "Imagen guardada correctamente", Toast.LENGTH_SHORT).show()

                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(context, "La imagen no se pudo guardar. Intentalo nuevamete.", Toast.LENGTH_SHORT).show()

                }
            }
        }
    }

    private fun getRealPathFromURI(contentUri: Uri?): String {
        val context = (activity as MainActivity).activityContext

        val proj = arrayOf(MediaStore.Images.Media.DATA)
        val cursor = context.managedQuery(contentUri, proj, null, null, null)
        val column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        cursor.moveToFirst()
        return cursor.getString(column_index)
    }

    companion object {
        private const val CAMERA_REQUEST_CODE = 101
        private const val STORAGE_PERMISSION_CODE = 102
    }

}
