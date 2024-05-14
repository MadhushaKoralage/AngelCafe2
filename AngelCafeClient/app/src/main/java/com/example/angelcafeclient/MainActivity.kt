package com.example.angelcafeclient

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.example.angelcafeclient.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.searchButton.setOnClickListener {
            val searchCode : String = binding.searchButton.text.toString()
            if  (searchCode.isNotEmpty()){
                readData(searchCode)
            }else{
                Toast.makeText(this,"PLease enter the Coffee Code",Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(code: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(code).get().addOnSuccessListener {
            if (it.exists()){
                val name = it.child("name").value
                val code = it.child("code").value
                val price = it.child("price").value
                val time = it.child("time").value
                Toast.makeText(this,"Results Found",Toast.LENGTH_SHORT).show()
                binding.searchCoffee.text.clear()
                binding.readName.text = name.toString()
                binding.readCode.text = code.toString()
                binding.readPrice.text = price.toString()
                binding.readTime.text = time.toString()
            }else{
                Toast.makeText(this,"Coffee does not exist",Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this,"Something went wrong",Toast.LENGTH_SHORT).show()
        }
    }
}