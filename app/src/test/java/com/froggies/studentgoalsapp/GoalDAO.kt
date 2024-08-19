package com.froggies.studentgoalsapp

import Goal
import GoalDatabase
import android.content.ContentValues

class GoalDAO(private val db: GoalDatabase) {
    fun createGoal(title: String, description: String): Long {
        val values = ContentValues().apply {
            put("title", title)
            put("description", description)
        }
        return db.writableDatabase.insert("goals", null, values)
    }

    fun getAllGoals(): List<Goal> {
        val cursor = db.readableDatabase.query("goals", null, null, null, null, null, null)
        val goals = mutableListOf<Goal>()
        while (cursor.moveToNext()) {
            goals.add(Goal(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3) == 1
            ))
        }
        cursor.close()
        return goals
    }

    fun updateGoal(id: Int, isCompleted: Boolean) {
        val values = ContentValues().apply {
            put("is_completed", if (isCompleted) 1 else 0)
        }
        db.writableDatabase.update("goals", values, "id = ?", arrayOf(id.toString()))
    }

    fun deleteGoal(id: Int) {
        db.writableDatabase.delete("goals", "id = ?", arrayOf(id.toString()))
    }
}