package com.froggies.studentgoalsapp

import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AppCompatActivity

class SettingsActivity : AppCompatActivity() {
    private lateinit var darkMode: CheckBox
    private lateinit var notifications: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        darkMode = findViewById(R.id.dark_mode)
        notifications = findViewById(R.id.notifications)

        val sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE)
        darkMode.isChecked = sharedPreferences.getBoolean("dark_mode", false)
        notifications.isChecked = sharedPreferences.getBoolean("notifications", false)

        darkMode.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("dark_mode", darkMode.isChecked)
            editor.apply()
        }

        notifications.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putBoolean("notifications", notifications.isChecked)
            editor.apply()
        }

        findViewById<View>(R.id.back_button).setOnClickListener {
            finish() // or onBackPressed()
        }
    }
}