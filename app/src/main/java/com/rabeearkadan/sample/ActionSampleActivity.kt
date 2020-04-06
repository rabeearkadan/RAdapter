package com.rabeearkadan.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.rabeearkadan.sample.adpater.RAdapter
import com.rabeearkadan.sample.model.BaseModel
import com.rabeearkadan.sample.utils.IActions
import kotlinx.android.synthetic.main.activity_basic_sample.*

class ActionSampleActivity : AppCompatActivity() {

    private var adapter : RAdapter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_sample)

        adapter = RAdapter(this@ActionSampleActivity, object :IActions{
            override fun onNegativeClick(item: BaseModel, position : Int) {
                Toast.makeText( this@ActionSampleActivity , "HERE", Toast.LENGTH_LONG).show()
            }

            override fun onNeutralClick(item: BaseModel, position: Int) {

            }

            override fun onPositiveClick(item: BaseModel, position: Int) {

            }
        })
        rcv_list.layoutManager = LinearLayoutManager ( this@ActionSampleActivity,
            LinearLayoutManager.VERTICAL, false )
        rcv_list.adapter = adapter

        adapter!!.addItem( RModel(Model(1, "Rabee1",31 , "Paris")))
        adapter!!.addItem( RModel(Model(2, "Rabee2",31 , "Paris")))
        adapter!!.addItem( RModel(Model(3, "Rabee3",31 , "Paris")))
        adapter!!.addItem( RModel(Model(4, "Rabee4",31 , "Paris")))
        adapter!!.addItem( RModel(Model(5, "Rabee5",31 , "Paris")))
        adapter!!.addItem( RModel(Model(6, "Rabee6",31 , "Paris")))
        adapter!!.addItem( RModel(Model(7, "Rabee7",31 , "Paris")))
        adapter!!.addItem( RModel(Model(8, "Rabee8",31 , "Paris")))
        adapter!!.addItem( RModel(Model(9, "Rabee9",31 , "Paris")))
        adapter!!.addItem( RModel(Model(10, "Rabee10",31 , "Paris")))
    }
}
