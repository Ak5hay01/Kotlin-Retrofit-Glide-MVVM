package Network

import com.akshayteli.retrofitglidemvvmkotlin.ui.Blog
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Akshay Teli on 27,May,2021
 */
interface RetrofitService {

    @GET("repositories")
    fun getDataFromAPI(@Query("q") query: String): Call<Blog>
}