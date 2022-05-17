package com.dicoding.myviewmodelv2.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.dicoding.myviewmodelv2.R
import com.dicoding.myviewmodelv2.viewmodel.MainViewModel
import com.dicoding.myviewmodelv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    //private lateinit var viewModel: MainViewModel

    private val viewModel: MainViewModel by viewModels() //Untuk menyingkat lagi kode untuk inisialisasi ViewModel di atas dengan memanfaatkan delegation by viewModels() dari library activity-ktx.
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        displayResult()

        binding.btnEnkripsi.setOnClickListener {
            val text = binding.edtText.text.toString()

            when{
                text.isEmpty() -> {
                    binding.edtText.error = "Masih kosong"
                } else -> {
                    viewModel.enkripsi(text)
                    displayResult()
                }
            }
        }

        binding.btnDeskripsi.setOnClickListener {
            val text = binding.edtText.text.toString()

            when{
                text.isEmpty() -> {
                    binding.edtText.error = "Masih kosong"
                } else -> {
                viewModel.dekripsi(text)
                displayResult()
            }
            }
        }
    }

    private fun displayResult() {
        binding.tvResult.text = viewModel.result
    }

}