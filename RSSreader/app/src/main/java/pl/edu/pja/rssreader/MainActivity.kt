package pl.edu.pja.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        auth = FirebaseAuth.getInstance()
    }

    fun crashApp(view: View)
    {
        if(auth.currentUser == null)
        {
            auth.createUserWithEmailAndPassword("asdfasdf@gmail.com","234567")
                .addOnSuccessListener { Toast.makeText(this,"Zarejestrowano",Toast.LENGTH_LONG).show() }
            auth.signInWithEmailAndPassword("asdfasdf@gmail.com", "234567")
                .addOnSuccessListener {
                    Toast.makeText(this, "Zalogowano ${it.user?.uid} ${it.user?.email}",Toast.LENGTH_LONG).show()
                }
        }
        else
        {
            Toast.makeText(this, "Zalogowano ${auth.currentUser?.uid} ${auth.currentUser?.email}",Toast.LENGTH_LONG).show()
            FirebaseDatabase.getInstance()
                .getReference("users")
                .child("${auth.currentUser?.uid}")
                .setValue("${auth.currentUser?.email}")
        }
    }
}