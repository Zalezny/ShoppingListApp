package com.example.shooppingg.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shooppingg.R
import com.example.shooppingg.databinding.FragmentLoginBinding
import com.example.shooppingg.view.activities.RegisterActivity
import com.example.shooppingg.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentLoginBinding.inflate(layoutInflater)

        //init auth
        auth = FirebaseAuth.getInstance()

        val loginBtn = binding.btnLogin

        loginBtn.setOnClickListener {
            login()
        }

        val tvRegister = binding.tvRegisterLogin
        tvRegister.setOnClickListener {
            val intent = Intent(activity, RegisterActivity::class.java)
            startActivity(intent)
        }

        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    private fun login() {
        val loginText = binding.etLogin.text.toString()
        val passwordText = binding.etPasswordLogin.text.toString()

        auth.signInWithEmailAndPassword(loginText, passwordText).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                Toast.makeText(activity, "Successfully Logged", Toast.LENGTH_SHORT).show()
                //finish activity
                requireActivity().finish()
            } else
                Toast.makeText(activity, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        // TODO: Use the ViewModel
    }

}