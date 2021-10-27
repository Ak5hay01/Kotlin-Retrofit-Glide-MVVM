package com.akshayteli.retrofitglidemvvmkotlin.ui

import Network.RetrofitInstance
import Network.RetrofitService
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.VERTICAL
import com.akshayteli.retrofitglidemvvmkotlin.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import viewmodel.MainActivityViewModel

class MainActivity : AppCompatActivity() {

    lateinit var recyclerAdapter: RecyclerApdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecycler()
        createData()
    }

    private fun initRecycler() {
        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerAdapter = RecyclerApdapter()
            adapter = recyclerAdapter

//            val decorator = DividerItemDecoration(applicationContext,VERTICAL)
//            addItemDecoration(decorator)
        }
    }

    private fun createData(){

//        ----------------------------Static Data Bind to Recycler--------------------------

//        val list = ArrayList<Blog>()
//
//        list.add(Blog("Cricket","Played Outdoor"))
//        list.add(Blog("FootBall","Played Outdoor"))
//        list.add(Blog("Chess","Played indoor"))
//        list.add(Blog("Carrom","Played indoor"))
//
//        recyclerAdapter.setListData(list)
//        recyclerAdapter.notifyDataSetChanged()

//        -------------------------------Normal API CALL--------------------------------

//        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
//        val call = retrofitInstance.getDataFromAPI("newyork")
//        call.enqueue(object:retrofit2.Callback<Blog>{
//            override fun onResponse(call: Call<Blog>, response: Response<Blog>) {
//                if(response.isSuccessful){
//                    recyclerAdapter.setListData(response.body()?.items!!)
//                    recyclerAdapter.notifyDataSetChanged()
//                }
//            }
//
//            override fun onFailure(call: Call<Blog>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
//            }
//
//        })



        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.getListDataObserver().observe(this,Observer<Blog>{

            if(it!= null){
                recyclerAdapter.setListData(it?.items)
                recyclerAdapter.notifyDataSetChanged()
            }
            else
            {
                Toast.makeText(this@MainActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
            }
        })

        btnSearch.setOnClickListener {
            viewModel.makeAPICall(search.text.toString())
            closeKeyBoard()
        }


    }

    override fun onResume() {
        super.onResume()
        createData()
    }

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}