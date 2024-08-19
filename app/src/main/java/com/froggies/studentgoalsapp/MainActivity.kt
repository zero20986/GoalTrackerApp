package com.froggies.studentgoalsapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var goalList: ListView
    private lateinit var goalAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        goalList = findViewById(R.id.goal_list)
        goalAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1)

        goalList.adapter = goalAdapter
        goalList.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val goal = goalAdapter.getItem(position)
            val intent = Intent(this, GoalDetailActivity::class.java)
            intent.putExtra("goal", goal)
            startActivity(intent)
        }

        findViewById<View>(R.id.add_goal_button).setOnClickListener {
            val intent = Intent(this, CreateGoalActivity::class.java)
            startActivity(intent)
        }

        findViewById<View>(R.id.settings_button).setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }

        loadGoals()
    }

    private fun loadGoals() {
        // Load goals from shared preferences
        val sharedPreferences = getSharedPreferences("goals", MODE_PRIVATE)
        val goals = sharedPreferences.getStringSet("goals", setOf())
        goalAdapter.clear()
        goals?.let { goalAdapter.addAll(it.toList()) }
    }
}
