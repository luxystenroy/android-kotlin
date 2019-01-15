package com.raywenderlich.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup

class ListItemRecyclerViewAdapter(var list: TaskList) : RecyclerView.Adapter<ListItemViewHolver>() {


    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ListItemViewHolver {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.task_view_holder,parent,false)
        return ListItemViewHolver(view)

    }

    override fun getItemCount(): Int {
        return list.tasks.size
    }

    override fun onBindViewHolder(holder: ListItemViewHolver?, position: Int) {
        if (holder != null){
            holder.taskTextView?.text = list.tasks[position]
        }


    }

}