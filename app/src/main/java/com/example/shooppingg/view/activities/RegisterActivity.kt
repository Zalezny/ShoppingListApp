package com.example.shooppingg.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.shooppingg.R
import com.example.shooppingg.databinding.ActivityLoginBinding
import com.example.shooppingg.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding : ActivityRegisterBinding
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init auth
        auth = FirebaseAuth.getInstance()

        val etEmail = binding.etEmailRegister
        val etPassword = binding.etPassRegister
        val etConfirmPassword = binding.etConfirmPassRegister
        val btnRegister = binding.btnRegister

        //todo: check email is correct, check passwords is correct
        btnRegister.setOnClickListener {
            if(etPassword.text.toString() == etConfirmPassword.text.toString())
            {
                register(etEmail.text.toString(), etPassword.text.toString())
            }
        }

    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Successfully created account!",
                        Toast.LENGTH_SHORT).show()
                    finish()
                } else {
                    val msg = task.exception!!.message

                    Toast.makeText(this, "$msg",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }
}