package com.dicoding.myviewmodelv2.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import com.dicoding.myviewmodelv2.utils.AesEncryption

class MainViewModel: ViewModel() {
    var result = ""
    val key = "Kelompok 4 Paling Keren"
    val aesEncryption = AesEncryption()



    @RequiresApi(Build.VERSION_CODES.O)
    fun enkripsi (text: String){
        //encode
        val encrypted = aesEncryption.encrypt(
            plaintext = text,
            secret = key
        )
        result = encrypted
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun dekripsi (text: String){
        //encode
        val decrypted = aesEncryption.decrypt(
            ciphertext = text,
            secret = key
        )
        result = decrypted
    }
}