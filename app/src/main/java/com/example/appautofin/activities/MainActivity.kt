package com.example.appautofin.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appautofin.R
import com.example.appautofin.databinding.ActivityMainBinding
import com.example.appautofin.fragments.FragmentFirst
import com.example.appautofin.fragments.FragmentSecond
import com.example.appautofin.fragments.FragmentThird
import com.example.appautofin.fragments.FragmentFourth

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    var activityContext = this

    override fun onCreate(savedInstanceState: Bundle?) {
        Thread.sleep(2000)
        setTheme(R.style.AutoFinTheme)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        integrateFragments("f1")
        binding.imageButtonMainMenu2.isClickable = true

        binding.imageButtonMainMenu1.setOnClickListener {
            integrateFragments("f1")
        }

        binding.imageButtonMainMenu2.setOnClickListener {
            integrateFragments("f2")
            /* Opcion 2
            val myIntent: Intent = Intent(this, BankActivity::class.java)
            binding.imageButtonMainMenu2.isClickable = false
            this.startActivity(myIntent)
            finish()
             */
        }

        binding.imageButtonMainMenu3.setOnClickListener {
            integrateFragments("f3")
        }

        binding.imageButtonMainMenu4.setOnClickListener {
            integrateFragments("f4")
        }
    }

    private fun integrateFragments(target: String){
        val fragmentTrans = supportFragmentManager.beginTransaction()
        when (target) {
            "f1" -> {
                val fragment1 = FragmentFirst()
                fragmentTrans.replace(R.id.frameLayBodyContent, fragment1)
                fragmentTrans.commit()
            }
            "f2" -> {
                val fragment2 = FragmentSecond()
                fragmentTrans.replace(R.id.frameLayBodyContent, fragment2)
                fragmentTrans.commit()
            }
            "f3" -> {
                val fragment3 = FragmentThird()
                fragmentTrans.replace(R.id.frameLayBodyContent, fragment3)
                fragmentTrans.commit()
            }
            "f4" -> {
                val fragment4 = FragmentFourth()
                fragmentTrans.replace(R.id.frameLayBodyContent, fragment4)
                fragmentTrans.commit()
            }

            else -> {
            }
        }
    }
}