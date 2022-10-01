package com.example.shooppingg.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.shooppingg.databinding.FragmentRegisterBinding
import com.example.shooppingg.viewmodel.RegisterViewModel
import com.google.firebase.auth.FirebaseAuth

class RegisterFragment : Fragment() {

    private lateinit var binding : FragmentRegisterBinding
    private lateinit var auth : FirebaseAuth


    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentRegisterBinding.inflate(layoutInflater)

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


        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        // TODO: Use the ViewModel
    }

    private fun register(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    Toast.makeText(activity, "Successfully created account!",
                        Toast.LENGTH_SHORT).show()
                    //todo: replace on login fragment
                } else {
                    val msg = task.exception!!.message

                    Toast.makeText(activity, "$msg",
                        Toast.LENGTH_SHORT).show()
                }
            }
    }



}