package com.rabeearkadan.sample.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rabeearkadan.sample.model.BaseModel
import com.rabeearkadan.sample.utils.BaseHolder
import com.rabeearkadan.sample.utils.IActions
import org.json.JSONObject
import kotlin.reflect.full.declaredMemberProperties

class RAdapter : RecyclerView.Adapter<BaseHolder> {

    private var context: Context? = null
    private var _list : ArrayList<BaseModel>? = null
    private var list: ArrayList<BaseModel>? = null
    private var search : Boolean = false
    var iActions : IActions? = null


    constructor(context: Context ){
        this.context = context
        this.list = ArrayList()
        this._list = ArrayList()
    }

    constructor( context: Context , list: ArrayList<BaseModel>){
        this.context = context
        this.list = list
        this._list = ArrayList()
    }

    constructor( context: Context , iActions: IActions){
        this.context = context
        this.iActions = iActions
        this.list = ArrayList()
        this._list = ArrayList()
    }
    constructor( context: Context , list: ArrayList<BaseModel>, iActions: IActions){
        this.context = context
        this.list = list
        this._list = ArrayList()
        this.iActions = iActions
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseHolder {
        return BaseHolder(LayoutInflater.from(context).inflate(viewType,null))
    }

    override fun getItemViewType(position: Int): Int {
        return if ( search ) _list!![position].layout else list!![position].layout
    }
    override fun getItemCount(): Int {
       return if ( search ) _list!!.size else list!!.size
    }

    override fun onBindViewHolder(holder: BaseHolder, position: Int) {
        if ( this._list!!.isEmpty() ){
            this.list!![position].bind(context!!, holder, position ,iActions )
        }else{
            this._list!![position].bind(context!!, holder, position, iActions)
        }
    }

    fun addItem(position: Int, item : BaseModel ){
        list!!.add(position, item)
        notifyItemInserted(position)
    }

    fun addItem( item : BaseModel ){
        list!!.add(item)
        notifyItemInserted( list!!.size -1 )
    }

    fun addItems ( items : ArrayList<BaseModel> , start :Boolean = false){
        if ( !start ){
            list!!.addAll( items )
            notifyItemRangeInserted(list!!.size - items.size - 1 , items.size )
        }else{
            list!!.addAll( 0 , items )
            notifyItemRangeInserted(0, items.size )
        }
    }

    fun setItems ( items: ArrayList<BaseModel> ){
        list!!.clear()
        list!!.addAll(items)
        notifyDataSetChanged()
    }

    fun removeItem ( position: Int ){
        list!!.removeAt( position )
        notifyItemRemoved( position )
    }
    fun removeItem ( item : BaseModel ){
        val position = list!!.indexOf( item )
        list!!.remove( item )
        notifyItemRemoved( position )
    }
    fun removeItems (){
        list!!.clear()
        notifyDataSetChanged()
    }


    fun clearSearch(){
        search = false
        _list!!.clear()
        notifyDataSetChanged()
    }
    fun search( value : Any, key : String? = null , exact : Boolean = false ){
        search = true
        _list!!.clear()
        _list!!.addAll( list!!.filter {
             compare(it.item , value , key , exact)
        })
        notifyDataSetChanged()
    }

    private fun compare ( item: Any , value : Any , key : String? = null , exact: Boolean = false ): Boolean{
        when( item ){
            is String ->{ return if ( !exact ) item.contains( value as String ) else  item ==  value as String  }
            is Int ->{ return  item ==  value }
            is Boolean -> { return  item }
            is Double ->{  return item ==  value }
            is Float ->{ return item ==  value }
            is  JSONObject ->{
                if ( key == null )
                    throw Exception("Undefined Key for Json Object")
                return if ( !exact ) item.optString(key,"undefined").contains( value.toString() )
                else item.optString(key,"undefined") ==  value.toString()
            }
            else ->{

                if ( key == null )
                    throw Exception("Undefined Key for Object")
                try{
                    var res: String ?= null
                    item.javaClass.kotlin.declaredMemberProperties.forEach {
                        if ( it.name == key)
                            res = it.get(item).toString()
                    }
                    if ( res == null )
                        throw  Exception("Key not related to the object")

                    return if ( !exact ) res.toString().contains( value.toString() )
                    else res.toString() ==  value.toString()

                }catch (e : Exception){
                    throw e
                }

                return false
            }
        }

    }

    fun sort (key: String? = null , asc : Boolean = true ){
        if ( this._list!!.isNotEmpty() ){
            if ( asc ){
                list!!.sortBy {
                    sortCompare( it.item , key )
                }

            }else{
                list!!.sortByDescending {
                    sortCompare( it.item , key )
                }
            }
        }else{
            if ( asc ){
                list!!.sortBy {
                    sortCompare( it.item , key )
                }
            }else{
                list!!.sortByDescending {
                    sortCompare( it.item , key )
                }
            }
        }

    }

    private fun sortCompare(item: Any, key: String?): String {
        return if ( item is String || item is Boolean
            || item is Int || item is Double
            || item is Float ){
            item.toString()
        }else
            if ( item is JSONObject ){
                if ( key  == null )
                    throw Exception("Undefined Key for Json Object")
                (item as JSONObject).getString(key)
            }else{
                if ( key == null )
                    throw Exception("Undefined Key for Object")
                try{
                    var res: String ?= null
                    item.javaClass.kotlin.declaredMemberProperties.forEach {
                        if ( it.name == key)
                            res = it.get(item).toString()
                    }
                    if ( res == null )
                        throw  Exception("Key not related to the object")
                    res.toString()
                }catch (e : Exception){
                    throw e
                }
            }

    }

}