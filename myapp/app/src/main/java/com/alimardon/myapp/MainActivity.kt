package com.alimardon.myapp

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.alimardon.myapp.databinding.ActivityMainBinding
const val image_code=9
class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btntoast.setOnClickListener {
            val openGalleryIntent=Intent()
            openGalleryIntent.action=Intent.ACTION_GET_CONTENT
            openGalleryIntent.type="image/*"
            startActivityForResult(openGalleryIntent, image_code)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode== RESULT_OK){
            if (resultCode== image_code){
                val imur:Uri = data?.data as Uri
                binding.imageview.setImageURI(imur)
            }
        }
    }
}