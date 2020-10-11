package com.jatin.materialretrofitkotlinapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
        val service = retrofit.create(PostApi::class.java)
        service.getPosts().enqueue(
            object : Callback<String>{
                override fun onFailure(call: Call<String>, t: Throwable) {
                    Toast.makeText(applicationContext,t.message.toString(),Toast.LENGTH_LONG).show()
                }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                Toast.makeText(applicationContext,response.body().toString(),Toast.LENGTH_LONG).show()
            }

        })


        btnAlert.setOnClickListener {
            MaterialAlertDialogBuilder(this).setTitle("Downloading")
                .setMessage("Do you want to continue?")
                .setPositiveButton("Yes"){dialog, which ->
                    Toast.makeText(this,"YES",Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){dialog, which ->
                    Toast.makeText(this,"NO",Toast.LENGTH_SHORT).show()
                }
                .setNeutralButton("Cancel"){dialog, which ->
                    Toast.makeText(this,"Cancel",Toast.LENGTH_SHORT).show()
                }.show()
        }

        fab.setOnClickListener {

            Snackbar.make (it,"This is Snackbar",Snackbar.LENGTH_SHORT).show()

        }

    }

    interface PostApi{
        @GET("posts")
        fun getPosts(): Call<String>
    }
}