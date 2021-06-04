package pl.edu.pja.travelerapp

import android.Manifest
import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.*
import android.icu.text.SimpleDateFormat
import android.location.Geocoder
import android.location.Location
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.provider.MediaStore
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.google.android.gms.location.*
import pl.edu.pja.travelerapp.database.AppDatabase
import pl.edu.pja.travelerapp.databinding.ActivityMainBinding
import pl.edu.pja.travelerapp.model.NoteDTO
import java.time.LocalDate
import java.util.*
import pl.edu.pja.travelerapp.adapter.PictureAdapter
import pl.edu.pja.travelerapp.model.Picture_
import java.io.File
import java.io.FileNotFoundException
import kotlin.concurrent.thread

const val CAMERA_PERMISSIONS_REQUEST = 1
const val LOCATION_PERMISSIONS_REQUEST = 4
const val CAMERA_INTENT_REQUEST = 2
const val DESCRIPTION_INTENT_REQUEST = 3
const val SHOW_PICTURE_INTENT_REQUEST = 5
const val SETTINGS_INTENT_REQUEST = 6

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val pictureAdapter by lazy { PictureAdapter(this) }
    private val settings by lazy { getSharedPreferences("settings", Context.MODE_PRIVATE) }
    private val locClient by lazy { LocationServices.getFusedLocationProviderClient(this) }
    val geofencingClient: GeofencingClient by lazy { LocationServices.getGeofencingClient(this) }
    private lateinit var loc: Location
    private var uri = Uri.EMPTY

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        Shared.db = AppDatabase.open(applicationContext)
        binding.openCamera.setOnClickListener{
          startCamera()
        }
        binding.settingsButton.setOnClickListener{
            startActivityForResult(Intent(applicationContext,SettingsActivity::class.java),
                SETTINGS_INTENT_REQUEST)
        }
        askLocationPermission()
        startRequesting()
        registerChannel()
        showPhotos()
        settings.edit().putString("textColor","Black").apply()
        settings.edit().putString("textSize","Medium").apply()
        settings.edit().putInt("radius",1).apply()
    }

    fun deletePhoto(name: String)
    {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            contentResolver.delete(Uri.parse(name),null)
        } else {
            val file = File(name)
            if (file.exists()) {
                file.delete()
            }
        }
    }

    private fun askLocationPermission() {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSIONS_REQUEST)
        }
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

    private fun startCamera() {
        if(ContextCompat.checkSelfPermission(applicationContext,Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), CAMERA_PERMISSIONS_REQUEST)
        }
        else
        {
            generateURI()
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.putExtra(MediaStore.EXTRA_OUTPUT,uri)

            startActivityForResult(intent, CAMERA_INTENT_REQUEST)
        }
    }


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
            var city = ""
            var country = ""
            if (Geocoder.isPresent()) {
                val geo = Geocoder(applicationContext).getFromLocation(loc.latitude,loc.longitude, 1).first()
                country = geo.countryName
                city = geo.locality
            }
            val description = data.getStringExtra("note")
            val note = NoteDTO(
                note = description.orEmpty(),
                imageName = uri.toString(),
                city = city,
                country = country
            )
            thread {
                Shared.db?.note?.insert(note)
                Shared.db?.note?.selectByImageName(uri.toString()).let {
                    it?.id?.toInt()?.let { it1 -> setGeofence(it1,loc.latitude,loc.longitude) }}
            }
        }
        if(requestCode == SETTINGS_INTENT_REQUEST && resultCode == RESULT_OK)
        {

            if (data != null) {
                data.getStringExtra("textColor").let {
                    settings.edit().putString("textColor",it).apply()
                }
                data.getStringExtra("textSize").let {
                    settings.edit().putString("textSize",it).apply()
                }
                data.getIntExtra("radius",1).let {
                    settings.edit().putInt("radius",it).apply()
                }
            }
            println(settings.getString("textColor","abc"))
            println(settings.getString("textSize","cba"))
            println(settings.getInt("radius",-1))
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
                val list = mutableListOf<Picture_>()
                it.forEach {
                    try{
                        val image = ImageDecoder.decodeBitmap(ImageDecoder.createSource(this.contentResolver, it.imageName.toUri()))
                        val picture_ = Picture_(
                            it.id,
                            it.note,
                            it.imageName,
                            image
                        )
                        list.add(picture_)
                    }catch (e: FileNotFoundException)
                    {
                        Shared.db?.note?.delete(it.id)
                    }
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

private fun registerChannel() {
    getSystemService(NotificationManager::class.java).let {
        val notificationChannel = NotificationChannel(
            "pl.edu.pja.travelerapp.Geofence",
            "Geofences",
            NotificationManager.IMPORTANCE_DEFAULT
        )
        it.createNotificationChannel(notificationChannel)
    }
}

    private fun saveImage(bitmap: Bitmap?) {
        if (bitmap != null) {
            contentResolver.openOutputStream(uri).use {
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            }
        }
    }

    private val locationCallback = object : LocationCallback() {
        @SuppressLint("SetTextI18n")
        override fun onLocationResult(locationResult: LocationResult?) {
            locationResult ?: return
            for (location in locationResult.locations){
                loc = location
            }
        }
    }

    @SuppressLint("MissingPermission")
    fun startRequesting() {
        val locationRequest = LocationRequest.create().apply {
            interval = 5000
            numUpdates = 10
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }
        locClient.requestLocationUpdates(
            locationRequest,
            locationCallback,
            Looper.getMainLooper()
        )
    }

    private fun Bitmap.drawText():Bitmap?{
        var text = ""
        if (Geocoder.isPresent()) {
            val geo = Geocoder(applicationContext).getFromLocation(loc.latitude,loc.longitude, 1).first()
            text = "${geo.locality}, ${geo.countryName} ${LocalDate.now()}"
        }
        val textSizeString = settings.getString("textSize","Medium")
        val textSize = if(textSizeString=="Big") 60f else if(textSizeString=="Medium") 40f else 20f
        val color:Int = Color.parseColor(settings.getString("textColor","Purple"))
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

    fun openShowPictureActivity(id: Long) {
        startActivityForResult(
            Intent(this, ShowPictureActivity::class.java)
                .putExtra("id",id),
                SHOW_PICTURE_INTENT_REQUEST
        )
    }

    private fun setGeofence(requestCode: Int, latitude: Double, longitude: Double) {
        if (ActivityCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        )
        {
            geofencingClient.addGeofences(
                generateRequest(requestCode,latitude, longitude),
                generatePendingIntent(requestCode)
            )
        }
    }

    private fun generateRequest(requestCode: Int, latitude: Double, longitude: Double): GeofencingRequest {
        val geofence = Geofence.Builder()
            .setCircularRegion(latitude, longitude, settings.getInt("radius", 1)*1000f)
            .setRequestId(requestCode.toString())
            .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER)
            .setExpirationDuration(Geofence.NEVER_EXPIRE)
            .build()
        println("aaaaaaaaaaaaaaaaaaaa $requestCode")
        return GeofencingRequest.Builder()
            .addGeofence(geofence)
            .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_EXIT)
            .build()
    }

    private fun generatePendingIntent(requestCode: Int): PendingIntent {
        return PendingIntent.getBroadcast(
            applicationContext,
            requestCode,
            Intent(this, Notifier::class.java)
                .putExtra("requestCode",requestCode),
            PendingIntent.FLAG_UPDATE_CURRENT
        )
    }
}