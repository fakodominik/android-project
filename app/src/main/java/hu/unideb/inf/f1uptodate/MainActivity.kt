package hu.unideb.inf.f1uptodate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import android.content.Intent
import android.net.Uri
import hu.unideb.inf.f1uptodate.fragments.*


class MainActivity : AppCompatActivity() {

    private var f1Url : String = "https://www.formula1.com"
    private lateinit var toggle : ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupDrawer()
        initializeFragment()

    }

    private fun initializeFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.frame_layout,WelcomeFragment()).commit()
    }

    private fun setupDrawer() {
        val drawerLayout : DrawerLayout = findViewById(R.id.drawer_layout)
        val navView : NavigationView = findViewById(R.id.nav_view)

        toggle = ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        navView.setNavigationItemSelectedListener {
            drawerLayout.closeDrawers()
            when(it.itemId) {
                R.id.nav_races -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,RacesFragment()).commit()
                }
                R.id.nav_home -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,WelcomeFragment()).commit()
                }
                R.id.nav_help -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,HelpFragment()).commit()
                }
                R.id.nav_about -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout,AboutFragment()).commit()
                }
                R.id.nav_favourites -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, FavouriteFragment()).commit()
                }
                R.id.nav_championships -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ChampionshipFragment()).commit()
                }
                R.id.nav_constructors -> {
                    supportFragmentManager.beginTransaction().replace(R.id.frame_layout, ConstructorFragment()).commit()
                }
                R.id.nav_site -> {
                    openUrl(f1Url)
                }
            }
            true
        }

    }

    private fun openUrl(url: String?) {
        if (url != null) {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(browserIntent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}