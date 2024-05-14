package com.example.angelcafeadmin


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Toast
import com.example.angelcafeadmin.databinding.ActivityUpdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUpdateBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.updateButton.setOnClickListener {
            val updateName = binding.updateCoffeeName.text.toString()
            val updateCode = binding.updateCoffeeCode.text.toString()
            val updatePrice = binding.updateCoffeePrice.text.toString()
            val updateTime = binding.updateCoffeeMakeTime.text.toString()
            updateData(updateName,updateCode,updatePrice,updateTime)
        }
    }



    private fun updateData( name: String, code: String, price: String,time: String) {
        database = FirebaseDatabase.getInstance().getReference("Users")
        val user = mapOf<String,String>(
            "name" to name,
            "code" to code,
            "price" to price,
            "time" to time,
        )
        database.child(code).updateChildren(user).addOnSuccessListener {
            binding.updateCoffeeName.text.clear()
            binding.updateCoffeeCode.text.clear()
            binding.updateCoffeePrice.text.clear()
            binding.updateCoffeeMakeTime.text.clear()
            Toast.makeText(this,"Successfully Updated",Toast.LENGTH_SHORT).show()
        }.addOnFailureListener{
            Toast.makeText(this,"Failed to Update",Toast.LENGTH_SHORT).show()
        }}
}
