package com.froggies.studentgoalsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class CreateGoalActivity : AppCompatActivity() {
    private lateinit var goalTitle: EditText
    private lateinit var goalDescription: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_goal)

        goalTitle = findViewById(R.id.goal_title)
        goalDescription = findViewById(R.id.goal_description)

        findViewById<View>(R.id.save_button).setOnClickListener {
            val goal = "${goalTitle.text}: ${goalDescription.text}"
            val sharedPreferences = getSharedPreferences("goals", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putStringSet("goals", setOf(goal))
            editor.apply()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}