package pl.edu.pja.travelerapp

import android.content.ContentValues
import android.content.Intent
import android.graphics.*
import android.icu.text.SimpleDateFormat
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.GridLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.recyclerview.widget.GridLayoutManager
import pl.edu.pja.travelerapp.database.AppDatabase
import pl.edu.pja.travelerapp.databinding.ActivityMainBinding
import pl.edu.pja.travelerapp.model.NoteDTO
import java.time.LocalDate
import java.util.*
import java.util.concurrent.Executors
import kotlinx.coroutines.*
import pl.edu.pja.travelerapp.adapter.PictureAdapter
import pl.edu.pja.travelerapp.model.Picture_
import kotlin.concurrent.thread

const val CAMERA_PERMISSIONS_REQUEST = 1
const val CAMERA_INTENT_REQUEST = 2
const val DESCRIPTION_INTENT_REQUEST = 3
class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val pictureAdapter by lazy { PictureAdapter(this) }
    private var uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Shared.db = AppDatabase.open(applicationContext)
        binding.openCamera.setOnClickListener{
            generateURI()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)

            startActivityForResult(intent, CAMERA_INTENT_REQUEST)
        }
        showPhotos()
    }


    private fun generateURI() {
        val tmp = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
        } else {
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        }
        val contentValues = ContentValues().apply {
            put(
                MediaStore.Images.Media.DISPLAY_NAME, SimpleDateFormat(
                "yyyy-MM-dd-HH-mm-ss-SSS"
                , Locale.US)
                    .format(System.currentTimeMillis()) + ".jpg"
            )
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
        }
        uri = contentResolver?.insert(tmp, contentValues)

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
        if(requestCode == CAMERA_INTENT_REQUEST && resultCode == RESULT_OK)
        {
            val bitmap = ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, uri))
                .copy(Bitmap.Config.ARGB_8888,true)
                .drawText()

            saveImage(bitmap)
            showPhotos()
            openDescriptionActivity()
        }
        if(requestCode == DESCRIPTION_INTENT_REQUEST && resultCode == RESULT_OK && data != null)
        {
            val description = data.getStringExtra("note")
//            TODO: something
            val note = NoteDTO(
                note = description.orEmpty(),
                imageName = uri.toString(),
                city = "notImplemented",
                country = "notImplemented"
            )
//            saveToDb(note)
            thread {
                Shared.db?.note?.insert(note)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private fun showPhotos()
    {
        binding.photosList.adapter = pictureAdapter
    }

    override fun onResume() {
        super.onResume()
        thread {
            Shared.db?.note?.selectAll()?.let { it ->
                val list = it.map {
                    val image = ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, it.imageName.toUri()))
                    Picture_(
                        it.id,
                        it.note,
                        it.imageName,
                        image
                    )
                }
                pictureAdapter.pictures = list.toMutableList()
            }
        }
    }

    private fun openDescriptionActivity() {
        startActivityForResult(
            Intent(this, DescriptionActivity::class.java)
                .putExtra("name", uri.toString()),
            DESCRIPTION_INTENT_REQUEST
        )
    }
//    private fun saveToDb(noteDTO: NoteDTO) = runBlocking {
//        launch {
//            Shared.db?.note?.insert(noteDTO)
//        }
//    }

    private fun saveImage(bitmap: Bitmap?) {
        if (bitmap != null) {
            contentResolver.openOutputStream(uri).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    }

    private fun Bitmap.drawText(
        text:String = LocalDate.now().toString(),
        textSize:Float = 55f,
        color:Int = Color.RED
    ):Bitmap?{
        val bitmap = copy(config,true)
        val canvas = Canvas(bitmap)

        Paint().apply {
            flags = Paint.ANTI_ALIAS_FLAG
            this.color = color
            this.textSize = textSize
            typeface = Typeface.SERIF
            setShadowLayer(1f,0f,1f,Color.WHITE)
            canvas.drawText(text,20f,height - 20f,this)
        }

        return bitmap
    }

    fun openShowPictureActivity(id: Int) {
//        startActivityForResult(intent, )
    }
}