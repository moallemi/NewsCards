package me.moallemi.newscards.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.moallemi.newscards.R
import me.moallemi.newscards.ui.headline.HeadlinesFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, HeadlinesFragment())
            .commit()
    }
}
