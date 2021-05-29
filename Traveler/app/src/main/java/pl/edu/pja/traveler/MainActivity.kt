package pl.edu.pja.traveler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import pl.edu.pja.traveler.databinding.ActivityMainBinding

private const val REQUEST_CAMERA_TRANSFER = 1

class MainActivity : AppCompatActivity()
{
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.title = "Your photos"
        setContentView(binding.root)
        binding.cameraButton.setOnClickListener(openCameraActivity())
    }

    private fun openCameraActivity() : View.OnClickListener
    {
        return View.OnClickListener {
            startActivityForResult(
                Intent(this, CameraActivity::class.java),
                REQUEST_CAMERA_TRANSFER
            ) }
    }
}