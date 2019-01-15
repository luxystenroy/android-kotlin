package com.lge.listmaker


import android.content.Context;
import android.preference.PreferenceManager


class ListDataManager(val context: Context){

    fun saveList(list: TaskList){
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context).edit()
        sharedPreferences.putStringSet(list.name,list.tasks.toHashSet())
        sharedPreferences.apply()
    }


    fun readLits(): ArrayList<TaskList>{

        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val sharedPreferencesContext = sharedPreferences.all
        val taskList = ArrayList<TaskList>()

        for (preferenceItem in sharedPreferencesContext){
            val itemHashSet = preferenceItem.value as HashSet<String>
            val list = TaskList(preferenceItem.key,ArrayList(itemHashSet))
            taskList.add(list)
        }

        return taskList

    }

}