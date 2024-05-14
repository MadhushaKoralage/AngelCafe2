package com.example.angelcafeadmin




import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.angelcafeadmin.databinding.ActivityUploadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class UploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    private lateinit var database: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.saveButton.setOnClickListener {
            val name = binding.uploadCoffeeName.text.toString()
            val operator = binding.uploadCoffeeCode.text.toString()
            val location = binding.uploadCoffeePrice.text.toString()
            val phone = binding.uploadCoffeeMakeTime.text.toString()
            database = FirebaseDatabase.getInstance().getReference("Users")
            val users = User(name,operator,location,phone)
            database.child(phone).setValue(users).addOnSuccessListener {
                binding.uploadCoffeeName.text.clear()
                binding.uploadCoffeeCode.text.clear()
                binding.uploadCoffeePrice.text.clear()
                binding.uploadCoffeeMakeTime.text.clear()
                Toast.makeText(this,"Saved",Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this,"Failed",Toast.LENGTH_SHORT).show()
            }
        }
    }
}