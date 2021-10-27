package viewmodel

import Network.RetrofitInstance
import Network.RetrofitService
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.akshayteli.retrofitglidemvvmkotlin.ui.Blog
import retrofit2.Call
import retrofit2.Response

/**
 * Created by Akshay Teli on 27,May,2021
 */
class MainActivityViewModel: ViewModel() {

    lateinit var recyclerLstData:MutableLiveData<Blog>

    init {
        recyclerLstData = MutableLiveData()
    }

    fun getListDataObserver():MutableLiveData<Blog>{
        return recyclerLstData
    }

    fun makeAPICall(input:String){
        val retrofitInstance = RetrofitInstance.getRetroInstance().create(RetrofitService::class.java)
        val call = retrofitInstance.getDataFromAPI(input)
        call.enqueue(object:retrofit2.Callback<Blog>{
            override fun onResponse(call: Call<Blog>, response: Response<Blog>) {
                if(response.isSuccessful){
//                    recyclerAdapter.setListData(response.body()?.items!!)
//                    recyclerAdapter.notifyDataSetChanged()

                    recyclerLstData.postValue(response.body())

                }
                else
                {
                    recyclerLstData.postValue(null)
                }
            }

            override fun onFailure(call: Call<Blog>, t: Throwable) {
//                Toast.makeText(this@MainActivity, "Error in getting data from api.", Toast.LENGTH_LONG).show()
                recyclerLstData.postValue(null)
            }

        })
    }


}