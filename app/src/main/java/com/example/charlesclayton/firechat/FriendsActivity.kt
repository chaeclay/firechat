package com.example.charlesclayton.firechat

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import kotlinx.android.synthetic.main.activity_friends.*
import kotlinx.android.synthetic.main.content_main.*

class FriendsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friends)

        var ab = getSupportActionBar()
        ab?.setTitle("FireChat")
        ab?.setSubtitle("Friends List")

        ab?.setDisplayHomeAsUpEnabled(true)

        if(this.intent.hasExtra("userEmail")){
            mUserEmail = this.intent.getStringExtra("userEmail");
            mUserImageUrl = this.intent.getStringExtra("userImageUrl");
        }
        else{
            Log.w("debug", "Activity requires a logged in user");
        }

        attachRecyclerView()

        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "Person 1"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "Person 2"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "Person 3"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "Person 4"))
        addCellToRecyclerView(CellData(mUserEmail, mUserImageUrl, "Person 5"))

    }

    private var mUserEmail: String = ""
    private var mUserImageUrl: String = ""

    lateinit var adapter: CellViewAdapter

    private fun attachRecyclerView(){
        val manager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))

        initializeRecyclerView()
    }

    private fun initializeRecyclerView(){
        adapter = CellViewAdapter( {view, position -> rowTapped(position)})
        recyclerView.adapter = adapter
    }

    private fun rowTapped(position: Int){
        Log.d("debug", adapter.rows[position].headerTxt +  " " + adapter.rows[position].messageText)
    }

    private fun addCellToRecyclerView(cellData: CellData){
        adapter.addCellData(cellData)
        recyclerView.smoothScrollToPosition(adapter.getCellCount() - 1)
    }

}
