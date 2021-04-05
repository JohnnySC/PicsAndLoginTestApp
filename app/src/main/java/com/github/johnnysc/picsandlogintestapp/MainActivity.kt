package com.github.johnnysc.picsandlogintestapp

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.github.johnnysc.picsandlogintestapp.ui.login.LoginFragment
import com.github.johnnysc.picsandlogintestapp.ui.pics.PicsFragment

/**
 * @author Asatryan on 31.03.21
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val picsFragment = PicsFragment()
        val loginFragment = LoginFragment()

        supportFragmentManager.beginTransaction()
            .add(R.id.container, loginFragment)
            .hide(loginFragment)
            .commit()
        supportFragmentManager.beginTransaction()
            .add(R.id.container, picsFragment)
            .commit()

        navView.setOnNavigationItemSelectedListener {
            val showPics = it.itemId == R.id.navigation_pics
            supportFragmentManager.beginTransaction()
                .hide(if (showPics) loginFragment else picsFragment)
                .show(if (showPics) picsFragment else loginFragment)
                .commit()

            true
        }
    }
}