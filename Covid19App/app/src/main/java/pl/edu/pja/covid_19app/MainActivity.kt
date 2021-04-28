package pl.edu.pja.covid_19app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val button = Button(this).apply {
            layoutParams = ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT)
            setText(R.string.buton_text)
            setOnClickListener{
                setText(R.string.buton_text_after_click)
            }
        }
        setContentView(button)
        println(resources.getString(R.string.buton_text))
    }
}