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

    companion object {
        const val PICS_FRAGMENT_TAG = "picsFragment"
        const val LOGIN_FRAGMENT_TAG = "loginFragment"
        const val IS_LOGIN_SELECTED_KEY = "isLoginSelected"
    }

    private lateinit var navView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navView = findViewById(R.id.nav_view)

        val picsFragment = if (savedInstanceState == null) PicsFragment()
        else supportFragmentManager.findFragmentByTag(PICS_FRAGMENT_TAG)!!
        val loginFragment = if (savedInstanceState == null) LoginFragment()
        else supportFragmentManager.findFragmentByTag(LOGIN_FRAGMENT_TAG)!!

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .add(R.id.container, loginFragment, LOGIN_FRAGMENT_TAG)
                .hide(loginFragment)
                .commit()
            supportFragmentManager.beginTransaction()
                .add(R.id.container, picsFragment, PICS_FRAGMENT_TAG)
                .commit()
        } else
            navView.selectedItemId =
                if (savedInstanceState.getBoolean(IS_LOGIN_SELECTED_KEY)) R.id.navigation_login else R.id.navigation_pics

        navView.setOnNavigationItemSelectedListener {
            val showPics = it.itemId == R.id.navigation_pics
            supportFragmentManager.beginTransaction()
                .hide(if (showPics) loginFragment else picsFragment)
                .show(if (showPics) picsFragment else loginFragment)
                .commit()

            true
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean(IS_LOGIN_SELECTED_KEY, navView.selectedItemId == R.id.navigation_login)
    }
}