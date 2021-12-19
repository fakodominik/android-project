package hu.unideb.inf.f1uptodate

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.navigation.NavigationView
import hu.unideb.inf.f1uptodate.fragments.RacesFragment
import hu.unideb.inf.f1uptodate.fragments.WelcomeFragment

class MainActivity : AppCompatActivity() {

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
            }
            true
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(toggle.onOptionsItemSelected(item)) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}