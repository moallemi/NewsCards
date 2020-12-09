package me.moallemi.newscards.ui

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import dagger.android.AndroidInjection
import me.moallemi.newscards.R
import me.moallemi.newscards.extension.getIcon
import me.moallemi.newscards.ui.headline.HeadlinesFragment
import me.moallemi.newscards.util.theme.ThemeManager
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, HeadlinesFragment())
            .commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.theme_option, menu)
        menu?.let { mainMenu ->
            mainMenu.findItem(R.id.change_theme).icon = getIcon()
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item!!.itemId
        if (id == R.id.change_theme) {
            themeManager.changeTheme()
            item.icon = getIcon()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getIcon(): Drawable? {
        return ContextCompat.getDrawable(
            this,
            themeManager.getCurrentTheme().getIcon()
        )
    }
}