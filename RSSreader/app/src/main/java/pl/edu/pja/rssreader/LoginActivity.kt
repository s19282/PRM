package pl.edu.pja.rssreader

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import pl.edu.pja.rssreader.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            Toast.makeText(
                this,
                "You are currently logged (as: ${auth.currentUser?.email} )",
                Toast.LENGTH_LONG
            ).show()
            finish()
        }
    }

    fun loginButton(view: View) {
        auth.signInWithEmailAndPassword(
            binding.emailInput.text.toString(),
            binding.passwordInput.text.toString()
        )
            .addOnSuccessListener {
                Toast.makeText(this, "Logged as ${it.user?.email}", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Login unsuccessful! ${it.localizedMessage}", Toast.LENGTH_LONG).show()
            }
    }

    fun signUpButton(view: View) {
        auth.createUserWithEmailAndPassword(
            binding.emailInput.text.toString(),
            binding.passwordInput.text.toString()
        )
            .addOnSuccessListener {
                Toast.makeText(this, "Registered, logged as ${it.user?.email}", Toast.LENGTH_LONG)
                    .show()
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Register unsuccessful! ${it.localizedMessage}", Toast.LENGTH_LONG).show()

            }
    }
}