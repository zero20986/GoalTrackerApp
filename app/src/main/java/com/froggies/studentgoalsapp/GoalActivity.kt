package com.froggies.studentgoalsapp

import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class GoalActivity : AppCompatActivity() {
    private lateinit var goalDAO: GoalDAO
    private lateinit var goalList: ListView
    private lateinit var addGoalButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal)

        goalDAO = GoalDAO(GoalDatabase(this))
        goalList = findViewById(R.id.goal_list)
        addGoalButton = findViewById(R.id.add_goal_button)

        addGoalButton.setOnClickListener {
            val title = findViewById<EditText>(R.id.title_edit_text).text.toString()
            val description = findViewById<EditText>(R.id.description_edit_text).text.toString()
            goalDAO.createGoal(title, description)
            refreshGoalList()
        }

        goalList.setOnItemClickListener { _, _, position, _ ->
            val goal = goalDAO.getAllGoals()[position]
            goalDAO.updateGoal(goal.id, !goal.isCompleted)
            refreshGoalList()
        }

        goalList.setOnItemLongClickListener { _, _, position, _ ->
            val goal = goalDAO.getAllGoals()[position]
            goalDAO.deleteGoal(goal.id)
            refreshGoalList()
            true
        }
    }

    private fun refreshGoalList() {
        val goals = goalDAO.getAllGoals()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, goals.map { it.title })
        goalList.adapter = adapter
    }
}