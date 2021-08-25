package com.example.retrofit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    var volly: Button? = null
    var retrofit: Button? = null
    var addbtn: Button? = null
    var urls = "https://jsonplaceholder.typicode.com/"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        volly = findViewById(R.id.volley)
        retrofit = findViewById(R.id.retrofit) as Button
        addbtn = findViewById(R.id.add)
        retrofit!!.setOnClickListener(View.OnClickListener {
            val intent = Intent(this@MainActivity, RetrofitClass::class.java)
            startActivity(intent)
        })
        with(addbtn) { this?.setOnClickListener(View.OnClickListener { AddData() }) }
    }

    fun AddData() {
        val user1 = User1(270, "Raju kumar", "Android")
        //        HashMap<String,String> map=new HashMap<String,String>();
//        map.put("userId","270");
//        map.put("id","");
//        map.put("title","Raju kumar");
//        map.put("body","Android");
        val retrofit = Retrofit.Builder()
            .baseUrl(urls)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val retrofitInterface = retrofit.create(
            RetrofitInterface::class.java
        )
        val call = retrofitInterface.createUser(user1)
        call.enqueue(object : Callback<User1?> {
            override fun onResponse(call: Call<User1?>, response: Response<User1?>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@MainActivity, "Successfully created", Toast.LENGTH_SHORT)
                        .show()
                }
                val n = response.body()
                Toast.makeText(
                    this@MainActivity,
                    n!!.id.toString() + " " + n.postId,
                    Toast.LENGTH_SHORT
                ).show()
            }

            override fun onFailure(call: Call<User1?>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Error...", Toast.LENGTH_SHORT).show()
            }
        })
    }
}