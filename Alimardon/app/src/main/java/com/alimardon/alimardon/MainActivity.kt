package com.alimardon.alimardon

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.alimardon.alimardon.databinding.ActivityMainBinding
import com.yariksoffice.lingver.Lingver

const val CALL_PHONE_CODE = 1

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.telefonimage.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CALL_PHONE),
                    CALL_PHONE_CODE
                )
            } else {
                call()
            }
        }
        binding.tilni.setOnClickListener {
            val checkid = binding.radiogroup.checkedRadioButtonId
            when (checkid){
                R.id.radiobutton1->{
                    Lingver.getInstance().setLocale(this, "uz")
                }
                R.id.radiobutton2->{
                    Lingver.getInstance().setLocale(this, "en")
                }
            }
            restart()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CALL_PHONE_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                call()
        }
    }

    fun call() {
        val intent = Intent()
        intent.action = Intent.ACTION_CALL
            Uri.parse("tel:" + binding.countrycode.selectedCountryCodeWithPlus + binding.edphone.toString())
        startActivity(intent)
    }
    fun restart(){
        val intent:Intent?=this.packageManager.getLaunchIntentForPackage(this.packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        startActivity(intent)
    }
}