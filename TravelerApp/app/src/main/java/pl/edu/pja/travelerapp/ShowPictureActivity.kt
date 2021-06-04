package pl.edu.pja.travelerapp

import android.graphics.ImageDecoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.net.toUri
import pl.edu.pja.travelerapp.databinding.ActivityShowPictureBinding
import kotlin.concurrent.thread

class ShowPictureActivity : AppCompatActivity() {
    private val binding by lazy {ActivityShowPictureBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        if(intent.hasExtra("id")) intent.extras?.let { fillWithData(it.getLong("id",-1)) }
    }

    private fun fillWithData(i: Long) {
        thread {
            Shared.db?.note?.getById(i).let {
                this.runOnUiThread{
                    if (it != null) {
                        binding.imageView2.setImageBitmap(ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, it.imageName.toUri())))
                        binding.textView2.text = it.note
                    }
                }
            }
        }
    }
}