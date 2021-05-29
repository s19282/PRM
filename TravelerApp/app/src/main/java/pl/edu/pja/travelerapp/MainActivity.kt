package pl.edu.pja.travelerapp

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import androidx.core.content.FileProvider
import pl.edu.pja.travelerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.openCamera.setOnClickListener{
            val uri = generateURI()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE).let {
                it.putExtra(MediaStore.EXTRA_OUTPUT,uri)
            }
            startActivityForResult(intent, 1)
        }
    }

    private fun generateURI(): Uri {
        val file = filesDir.resolve("img.jpg").also {
            it.writeText("")
        }
        return FileProvider.getUriForFile(
            this,
            "pl.edu.pja.travelerApp.FileProvider",
            file
        )
    }

//    override fun onRequestPermissionsResult(
//        requestCode: Int, permissions: Array<String>, grantResults:
//        IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if (requestCode == REQUEST_CODE_PERMISSIONS) {
//            if (allPermissionsGranted()) {
//                startCamera()
//            } else {
//                Toast.makeText(this,
//                    "Permissions not granted by the user.",
//                    Toast.LENGTH_SHORT).show()
//                finish()
//            }
//        }
//    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode == 1 && resultCode == RESULT_OK)
        {
            binding.imageView2.setImageBitmap(
                BitmapFactory.decodeFile(filesDir.resolve("img.jpg").absolutePath)
            )
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}