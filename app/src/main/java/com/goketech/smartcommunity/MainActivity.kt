package com.goketech.smartcommunity

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import com.goketech.smartcommunity.view.home.HomeFragment

class MainActivity : AppCompatActivity() {

    lateinit var homeFragemnt: HomeFragment

    //private lateinit var textMessage: TextView
    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { tm ->
        when (tm.itemId) {
            R.id.navigation_home -> {
                //textMessage.setText(R.string.title_home)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                //textMessage.setText(R.string.title_dashboard)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                //textMessage.setText(R.string.title_notifications)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        initFragment()

        var login  = Login()
        login.startLogin()
    }

    fun initFragment(){
        homeFragemnt = HomeFragment()

        var bt = supportFragmentManager.beginTransaction()
        bt.replace(R.id.layout_page,homeFragemnt).commit()
    }
}
