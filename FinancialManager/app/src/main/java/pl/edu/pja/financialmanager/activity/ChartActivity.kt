package pl.edu.pja.financialmanager.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pl.edu.pja.financialmanager.R

class ChartActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart)
        supportActionBar?.title = "Monthly chart"
    }
}