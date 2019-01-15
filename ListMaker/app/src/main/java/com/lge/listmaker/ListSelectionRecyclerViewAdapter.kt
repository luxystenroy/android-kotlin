package com.lge.listmaker

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ExpandableListView
import android.widget.Toast

class ListSelectionRecyclerViewAdapter(val lists :ArrayList<TaskList>, val clickListener: ListSelectionRecyclerViewClickListerner) : RecyclerView.Adapter<ListSelectionViewHolder>() {

    var listTitles = arrayOf("Shopping list","Shores", "Android tutorials")

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ListSelectionViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_selection_view_holder,parent,false)
        return ListSelectionViewHolder(view)

    }

    override fun getItemCount(): Int {
        return lists.size
    }

    interface ListSelectionRecyclerViewClickListerner{
        fun listItemClicked(list: TaskList)
    }

    override fun onBindViewHolder(holder: ListSelectionViewHolder, position: Int) {
       if(holder != null){
           holder.listPostion.text = (position + 1 ).toString()
           holder.listTitle.text = lists.get(position).name
           holder.itemView.setOnClickListener({


               clickListener.listItemClicked(lists.get(position))
           })
       }
    }

    fun addList(list: TaskList){
        lists.add(list)
        notifyDataSetChanged()
    }
}