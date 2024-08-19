package com.froggies.studentgoalsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GoalDetailActivity : AppCompatActivity() {
    private lateinit var goalTitle: TextView
    private lateinit var goalDescription: TextView
    private lateinit var goalCompleted: CheckBox

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        goalTitle = findViewById(R.id.goal_title)
        goalDescription = findViewById(R.id.goal_description)
        goalCompleted = findViewById(R.id.goal_completed)

        val goal = intent.getStringExtra("goal")
        val goalParts = goal!!.split(":")
        goalTitle.text = goalParts[0]
        goalDescription.text = goalParts[1]

        goalCompleted.setOnClickListener {
            // Mark goal as completed
            val sharedPreferences = getSharedPreferences("goals", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putStringSet("goals", setOf(goal + ":completed"))
            editor.apply()
            finish()
        }
    }
}