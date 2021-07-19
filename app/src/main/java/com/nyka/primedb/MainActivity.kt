package com.nyka.primedb

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.fragment.app.Fragment
import com.nyka.primedb.databinding.ActivityMainBinding
import com.nyka.primedb.controller.MovieFragment
import com.nyka.primedb.controller.NewsFragment
import com.nyka.primedb.controller.SettingsFragment
import com.nyka.primedb.controller.TvShowFragment

class MainActivity : BaseClass() {
    private lateinit var binding: ActivityMainBinding
    lateinit var toggle: ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, MovieFragment()).commit()
        val movieFragment = MovieFragment()
        val tvShowFragment = TvShowFragment()
        val newsFragment = NewsFragment()
        val settingsFragment = SettingsFragment()
        toggle = ActionBarDrawerToggle(this, binding.mdrawerLayout, R.string.open, R.string.close)
        toggle.syncState()
        binding.bottomNavigation.setOnNavigationItemSelectedListener{
            when(it.itemId){
                R.id.nav_movie -> makeFragment(movieFragment)
                R.id.nav_tv -> makeFragment(tvShowFragment)
                R.id.nav_news -> makeFragment(newsFragment)
                R.id.nav_settings -> makeFragment(settingsFragment)
            }
            true
        }

        //Toggle button: when open will display back arrow
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.navView.setNavigationItemSelectedListener {
            when(it.itemId){
                R.id.miItem1 -> Toast.makeText(applicationContext,"Item One clicked",Toast.LENGTH_LONG).show()
                R.id.miItem2 -> Toast.makeText(applicationContext,"Item 2 clicked",Toast.LENGTH_LONG).show()
                R.id.miItem3 -> Toast.makeText(applicationContext,"Item 3 clicked",Toast.LENGTH_LONG).show()
                R.id.miItem4 -> Toast.makeText(applicationContext,"Item 4 clicked",Toast.LENGTH_LONG).show()
                R.id.miItem5 -> Toast.makeText(applicationContext,"Item 5 clicked",Toast.LENGTH_LONG).show()
                R.id.miItem6 -> Toast.makeText(applicationContext,"Item 6 clicked",Toast.LENGTH_LONG).show()
                R.id.miItem7 -> Toast.makeText(applicationContext,"Item 7 clicked",Toast.LENGTH_LONG).show()
            }
            true
        }
        setContentView(binding.root)
    }

    private fun makeFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply{replace(R.id.fragment_container, fragment).commit()}
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}