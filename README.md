# RAdapter
Generic Library that helps to integrate adapters easily to recyclerview with multiple features as ( managing, sort, and filtering ) using multiple and easy to integrate views and layouts 

## Download

Include instructions on how to integrate the library into your projects. For instance install in your build.gradle:

```
dependencies {
  implementation 'com.rabeearkadan.radapter:RAdapter:1.0.5'
}
```

## Usage

### Creating Models
This Library provides multiple Base Model Types:

1- Basic Model: create Kotlin class as instance of BaseModel

```
class RModel(model: Model): BaseModel(model) {

    //set the Layout for this  Model 
    override var layout: Int = R.layout.layout_basic
    /*
    * holder: Generic Holder of Type ViewHolder that can detect the desired type
    * position: The position of the  RModel 
    * Optinal actions that user can send to Activity of Fragment
    */
    override fun bind(holder: BaseHolder, position: Int, iActions: IActions?) {
    
        holder.itemView.txt_name.text = (this.item as Model).name
        holder.itemView.txt_age.text = (this.item as Model).age.toString()
        holder.itemView.txt_address.text = (this.item as Model).address
    }
}
```
2- Regular Model: 

```
// user can use the same model with multiple layouts 
class R2Model(model: Model, layout: Int): BaseModel(model, layout) {

    
    /*
    * holder: Generic Holder of Type ViewHolder that can detect the desired type
    * position: The position of the  RModel 
    * Optinal actions that user can send to Activity of Fragment
    */
    override fun bind(holder: BaseHolder, position: Int, iActions: IActions?) {
        holder.itemView.findViewById<TextView>(R.id.txt_name).text = (this.item as Model).name
        holder.itemView.findViewById<TextView>(R.id.txt_age).text = (this.item as Model).age.toString()
        holder.itemView.findViewById<TextView>(R.id.txt_address).text = (this.item as Model).address
    }
}
```
3- Action Model:
```
class ActionModel(item : Model) : BaseModel( item ) {

    override var layout: Int = R.layout.layout_basic

    override fun bind(holder: BaseHolder, position: Int, iActions: IActions?) {
        holder.itemView.txt_name.text = (item as Model).name
        holder.itemView.txt_age.text = (item as Model).age.toString()
        holder.itemView.txt_address.text = (item as Model).address

        holder.itemView.setOnClickListener {
            iActions!!.onPositiveClick( this , position )
            //iActions!!.onNeutralClick( this , position )
            //iActions!!.onNegativeClick( this , position )
        }
    }
}
```

### Using RAdapter
```
class BasicSampleActivity : AppCompatActivity() {

    private var adapter : RAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_basic_sample)

        adapter = RAdapter(this@BasicSampleActivity)
        rcv_list.layoutManager = LinearLayoutManager ( this@BasicSampleActivity,
            LinearLayoutManager.VERTICAL, false )
        rcv_list.adapter = adapter
        
        // you can add item directly to adapter
        adapter!!.addItem( RModel(Model(1, "Rabee1",31 , "Paris")))
        adapter!!.addItem( R2Model(Model(4, "Rabee2",31 , "Paris"), R.layout.layout_basic_two))
    }

```

### Multiple Constructors 
```
//context : Context
adapter = RAdapter(this@BasicSampleActivity)
//context : Context, items : ArrayList<BaseModel> 
adapter = RAdapter(this@BasicSampleActivity, items)

//context : Context, iActions : IActions 
adapter = RAdapter(this@BasicSampleActivity,  object :IActions{
            override fun onNegativeClick(item: BaseModel, position : Int) {
               
            }

            override fun onNeutralClick(item: BaseModel, position: Int) {

            }

            override fun onPositiveClick(item: BaseModel, position: Int) {

            }
        })
 //context : Context, items : ArrayList<BaseModel>, iActions : IActions     
adapter = RAdapter(this@BasicSampleActivity, items, object :IActions{
            override fun onNegativeClick(item: BaseModel, position : Int) {
               
            }

            override fun onNeutralClick(item: BaseModel, position: Int) {

            }

            override fun onPositiveClick(item: BaseModel, position: Int) {

            }
        })   

```

### Adapter Functions
```
adapter.setItems(list: ArrayList<BaseModel>)
adapter.addItem( position: Int , item : BaseModel )
adpater.addItems (list: ArrayList<BaseModel>, start:Boolean = true)
adapter.removeItem( position: Int )
adapter.removeItem ( item: BaseModel )
adapter.removeItems ()
```

### Adapter Features 
```
// value is any type var, user can search for 
// in case the model is JSONObject or any Object ,  need to specify the key to perform the search on 
adapter.search (value : Any, key : String? = null , exact : Boolean = false )

adapter.clearSearch()

// in case the model is JSONObject or any Object ,  need to specify the key to perform the sort on 
adapter.sort (key: String? = null , asc : Boolean = true )

```


