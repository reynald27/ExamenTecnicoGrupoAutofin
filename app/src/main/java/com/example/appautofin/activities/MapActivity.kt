package com.example.appautofin.activities

import android.content.Intent
import android.os.Bundle
import android.view.KeyEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.appautofin.R
import com.example.appautofin.databinding.ActivityMapBinding
import com.google.android.gms.common.api.Status
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

class MapActivity : AppCompatActivity(), OnMapReadyCallback {
    private lateinit var binding: ActivityMapBinding
    private lateinit var autocompleteFragment: AutocompleteSupportFragment
    private var editTextLatitudeVal: Double = 0.0
    private var editTextLongitudeVal: Double = 0.0
    var activityContext = this
    private var mGoogleMap:GoogleMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AutoFinTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //editTextLatitudeVal = intent.getStringExtra("editTextLatitude")!!.trim().toDouble()
        //editTextLongitudeVal = intent.getStringExtra("editTextLongitude")!!.trim().toDouble()

        binding.buttonBackToActivity.setOnClickListener{
            binding.buttonBackToActivity.isClickable = false
            closeMap()
        }

        Places.initialize(applicationContext, getString(R.string.google_map_api_key))
        autocompleteFragment = supportFragmentManager.findFragmentById(R.id.autocompleteFragment)
                as AutocompleteSupportFragment
        autocompleteFragment.setPlaceFields(listOf(Place.Field.ID, Place.Field.ADDRESS, Place.Field.LAT_LNG))

        autocompleteFragment.setOnPlaceSelectedListener(object :PlaceSelectionListener{
            override fun onError(p0: Status) {
                Toast.makeText(this@MapActivity, "Hubo un error en la busqueda", Toast.LENGTH_SHORT).show()
            }

            override fun onPlaceSelected(place: Place) {
                val latLng = place.latLng!!
                zoomOnMap(latLng)
            }

        })

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentMap) as SupportMapFragment
            mapFragment.getMapAsync(this)
    }

    private fun zoomOnMap(latLng: LatLng){
        val newLatLngZooom = CameraUpdateFactory.newLatLngZoom(latLng, 12f)
        mGoogleMap?.animateCamera(newLatLngZooom)
    }

    private fun closeMap(){
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)

        finish()
    }

    //Metodo nativo de lenguaje java - lo que hace es detectar el momento en que se presiona el boton de regresar
    // del sistema operativo
    @Override
    override fun onBackPressed() {
        super.onBackPressed()
    }

    //Esta funcion complementa a la funcion onBackPressed para deshabilitar el boton de regresar del sistema operativo
    override fun onKeyDown(key_code: Int, key_event: KeyEvent?): Boolean {
        if (key_code == KeyEvent.KEYCODE_BACK) {
            super.onKeyDown(key_code, key_event)
            return true
        }
        return false
    }


    override fun onMapReady(myMap: GoogleMap) {

        mGoogleMap = myMap
        mGoogleMap!!.clear();
        val location = LatLng(19.429246, -99.142386)
        mGoogleMap!!.addMarker(MarkerOptions().position(location).title("Ciudad de México"))
        mGoogleMap!!.moveCamera(CameraUpdateFactory.newLatLng(location))

    }
}
