package com.rabeearkadan.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabeearkadan.sample.adpater.RAdapter
import kotlinx.android.synthetic.main.activity_basic_sample.*

class BasicSampleActivity : AppCompatActivity() {

    private var adapter : RAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_sample)

        adapter = RAdapter(this@BasicSampleActivity)
        rcv_list.layoutManager = LinearLayoutManager ( this@BasicSampleActivity,
            LinearLayoutManager.VERTICAL, false )
        rcv_list.adapter = adapter

        adapter!!.addItem( RModel(Model(1, "Rabee1",31 , "Paris")))
        adapter!!.addItem( RModel(Model(2, "Rabee2",31 , "Paris")))
        adapter!!.addItem( RModel(Model(3, "Rabee3",31 , "Paris")))
        adapter!!.addItem( R2Model(Model(4, "Rabee4",31 , "Paris"), R.layout.layout_basic_two))
        adapter!!.addItem( RModel(Model(5, "Rabee5",31 , "Paris")))
        adapter!!.addItem( RModel(Model(6, "Rabee6",31 , "Paris")))
        adapter!!.addItem( RModel(Model(7, "Rabee7",31 , "Paris")))
        adapter!!.addItem( R2Model(Model(8, "Rabee8",31 , "Paris"), R.layout.layout_basic_size))
        adapter!!.addItem( RModel(Model(9, "Rabee9",31 , "Paris")))
        adapter!!.addItem( RModel(Model(10, "Rabee10",31 , "Paris")))

        //adapter!!.sort("name", false)

        //adapter!!.search("ee1", "name",true )



    }
}
