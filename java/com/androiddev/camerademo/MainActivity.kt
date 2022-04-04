package com.androiddev.camerademo

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var imageView : ImageView
    private val REQUEST_CODE = 101
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageView = findViewById(R.id.imageView)
        val button : Button = findViewById(R.id.button)

        button.setOnClickListener {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE) // implicit intent
            try{
                startActivityForResult(cameraIntent, REQUEST_CODE )
            } catch (e : ActivityNotFoundException) {
                Toast.makeText(
                    this,
                    "ActivityNotFoundException",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            if(data != null){
                val extras = data.extras
                if(extras != null) {
                    val imageTaken = extras.get("data") as Bitmap
                    imageView.setImageBitmap(imageTaken)
                }
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}