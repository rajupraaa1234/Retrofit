package com.example.retrofit

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*

class RetrofitClass : AppCompatActivity() {
    var urls = "https://jsonplaceholder.typicode.com/"
    var recyclerView: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_retrofit_class)
        recyclerView = findViewById(R.id.retrofituserlist)
        call
        putMethod()
        //patchMethod();
    }

    // Call<List<User1>> call=retrofitInterface.getUser();
    val call: Unit
        //Call<List<User1>> call=retrofitInterface.getUsers(5,2);
        //Call<List<User1>> call=retrofitInterface.getUser(1);
        get() {
            val retrofit = Retrofit.Builder()
                .baseUrl(urls)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            val retrofitInterface = retrofit.create(
                RetrofitInterface::class.java
            )
            // Call<List<User1>> call=retrofitInterface.getUser();
            val map = HashMap<String, String>()
            map["userId"] = "1"
            map["_sort"] = "id"
            map["_order"] = "desc"
            val call = retrofitInterface.user
            //Call<List<User1>> call=retrofitInterface.getUsers(5,2);
            //Call<List<User1>> call=retrofitInterface.getUser(1);
            call.enqueue(object : Callback<List<User1?>> {
                override fun onResponse(
                    call: Call<List<User1?>>,
                    response: Response<List<User1?>>
                ) {
                    val res = response.body()!!
                    var use: Array<User1?>? = arrayOfNulls(res.size)
                    Log.i("MyArraySize","" + res)
//                    use = res.toArray(use)
                    val recyclerViewAdapter = RecyclerViewAdapter(this@RetrofitClass, res)
                    recyclerView!!.layoutManager = LinearLayoutManager(this@RetrofitClass)
                    recyclerView!!.adapter = recyclerViewAdapter
                }

                override fun onFailure(call: Call<List<User1?>>, t: Throwable) {
                    Toast.makeText(this@RetrofitClass, "Failed...", Toast.LENGTH_SHORT).show()
                }
            })
        }

    fun putMethod() {
        val user = User1(221, "Ram", "Put method called")
        val retrofit = Retrofit.Builder()
            .baseUrl(urls)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitInterface = retrofit.create(
            RetrofitInterface::class.java
        )
        val call = retrofitInterface.putpost("123", 1, user)
        call.enqueue(object : Callback<User1?> {
            override fun onResponse(call: Call<User1?>, response: Response<User1?>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RetrofitClass, "" + response.code(), Toast.LENGTH_SHORT)
                        .show()
                }
                val res = response.body()
                Toast.makeText(
                    this@RetrofitClass,
                    res!!.id.toString() + " " + res.title,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User1?>, t: Throwable) {}
        })
    }

    fun patchMethod() {
        val user = User1(221, "Ram", "Put method called")
        val retrofit = Retrofit.Builder()
            .baseUrl(urls)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitInterface = retrofit.create(
            RetrofitInterface::class.java
        )
        val call = retrofitInterface.patchpost(1, user)
        call.enqueue(object : Callback<User1?> {
            override fun onResponse(call: Call<User1?>, response: Response<User1?>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@RetrofitClass, "" + response.code(), Toast.LENGTH_SHORT)
                        .show()
                }
                val res = response.body()
                Toast.makeText(
                    this@RetrofitClass,
                    res!!.id.toString() + " " + res.title,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User1?>, t: Throwable) {}
        })
    }
}
