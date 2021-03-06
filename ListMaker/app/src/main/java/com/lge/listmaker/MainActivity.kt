package com.lge.listmaker

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), ListSelectionRecyclerViewAdapter.ListSelectionRecyclerViewClickListerner {

    lateinit var listRecyclerView: RecyclerView;

    val listDataManager: ListDataManager = ListDataManager(this)

    companion object {
        val INTENT_LIST_KEY = "list"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->

            showCreateListDialog()

        }

       
        val lists = listDataManager.readLits()

        listRecyclerView = findViewById<RecyclerView>(R.id.list_recyclerview)
        listRecyclerView.layoutManager = LinearLayoutManager(this)
        listRecyclerView.adapter = ListSelectionRecyclerViewAdapter(lists,this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }


    private fun showCreateListDialog(){
        var dialogTitle = getString(R.string.name_of_list)
        var positiveButtonTitle = getString(R.string.create_list)

        val builder = AlertDialog.Builder(this)
        val listTitleEditText = EditText(this)
        listTitleEditText.inputType = InputType.TYPE_CLASS_TEXT

        builder.setTitle(dialogTitle)
        builder.setView(listTitleEditText)

        builder.setPositiveButton(positiveButtonTitle, {dialog, i->

            val list = TaskList(listTitleEditText.text.toString())
            val recylcerAdapter = listRecyclerView.adapter as ListSelectionRecyclerViewAdapter

            recylcerAdapter.addList(list)
            listDataManager.saveList(list);

            dialog.dismiss()

            showListDetail(list)
        })

        builder.create().show()
    }

    fun showListDetail(list: TaskList){


        val listDetailIntent = Intent(this,ListDetailActivity::class.java)

      //  listDetailIntent.putExtra(INTENT_LIST_KEY, list)

        startActivity(intent)
    }

    override fun listItemClicked(list: TaskList){

        Toast.makeText(this,"Click",Toast.LENGTH_SHORT).show()
        showListDetail(list)
    }
}
