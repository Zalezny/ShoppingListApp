package com.example.shooppingg.view.fragments

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.shooppingg.R
import com.example.shooppingg.databinding.FragmentLoginBinding

import com.example.shooppingg.view.utils.isValidEmail
import com.example.shooppingg.view.utils.isValidPassword
import com.example.shooppingg.viewmodel.CreatorViewModel
import com.example.shooppingg.viewmodel.LoginViewModel
import com.google.firebase.auth.FirebaseAuth

class LoginFragment : Fragment() {

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by activityViewModels()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentLoginBinding.inflate(layoutInflater)
        //init auth
        auth = FirebaseAuth.getInstance()

        initObservers()

        val loginBtn = binding.btnLogin
        val tvRegister = binding.tvRegisterLogin

        loginBtn.setOnClickListener {
            viewModel.setEtEmail(binding.etLogin.text.toString())
            val emailValue = viewModel.getEtEmail().value
            //check etEmail is empty or have patterns for e-mails
            if(!(emailValue.isValidEmail()))
            {
                binding.etLogin.error = "Incorrect e-mail!"
            }
            else
            {
                binding.etLogin.error = null
            }

            viewModel.setEtPassword(binding.etPasswordLogin.text.toString())
            val passwordValue = viewModel.getEtPassword().value

            if(passwordValue!!.length < 8)
            {
                binding.etPasswordLogin.error = "Too short! Must be least 8 signs"
            }
            else
            {
                binding.etPasswordLogin.error = null
            }

            if(emailValue.isValidEmail() && passwordValue.isValidPassword() && passwordValue.length >= 8)
            {
                login()
            }

        }

        tvRegister.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }

        return binding.root
    }

    private fun login() {
        val loginText = binding.etLogin.text.toString()
        val passwordText = binding.etPasswordLogin.text.toString()

        auth.signInWithEmailAndPassword(loginText, passwordText).addOnCompleteListener(requireActivity()) {
            if (it.isSuccessful) {
                Toast.makeText(requireActivity().baseContext, "Successfully Logged", Toast.LENGTH_SHORT).show()
                //finish activity
                requireActivity().finish()
            } else
                Toast.makeText(requireActivity().baseContext, "Log In failed ", Toast.LENGTH_SHORT).show()
        }
    }

    private fun initObservers() {
        binding.etLogin.setText(viewModel.getEtEmail().value)

        viewModel.getEtEmail().observe(viewLifecycleOwner){
            binding.etLogin.setText(it)
        }
    }

}