package com.takasima.simplechatapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class Signup : AppCompatActivity() {
    private lateinit var etEmail : EditText
    private lateinit var etPassword : EditText
    private lateinit var btnSignUp : Button
    private lateinit var etName : EditText

    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDBRef: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        mAuth = FirebaseAuth.getInstance()

        etName = findViewById(R.id.et_name)
        etEmail = findViewById(R.id.et_email)
        etPassword = findViewById(R.id.et_password)
        btnSignUp = findViewById(R.id.btn_signup)

        btnSignUp.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val password = etPassword.text.toString()

            signUp(name, email, password) //new method for signup
        }
    }

    private fun signUp(name: String, email:String, password:String) {
        //algoritma for creating user
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, go to home
                    addUserToDB(name, email, mAuth.currentUser?.uid!!)
                    intent = Intent(this@Signup, MainActivity::class.java)
                    startActivity(intent)
                    finish()

                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@Signup, "Some error occureed", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun addUserToDB(name:String, email: String, uid: String) {
        mDBRef = FirebaseDatabase.getInstance().getReference()
        mDBRef.child("user").child(uid).setValue(User(name, email, uid))
    }
}