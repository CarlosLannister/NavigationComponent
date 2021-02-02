package com.example.navigationcomponent.views

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.navigationcomponent.R
import com.example.navigationcomponent.views.SecondFragmentArgs
import com.example.navigationcomponent.databinding.FragmentSecondBinding
import com.example.navigationcomponent.model.User
import com.example.navigationcomponent.viewModels.UserViewModel


class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private lateinit var mUserViewModel: UserViewModel
    val args: SecondFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        val view = binding.root

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        val myNumber = args.number
        binding.textView2.setText(myNumber.toString())
        binding.textView2.setOnClickListener{
            Navigation.findNavController(view).navigate(R.id.action_secondFragment_to_firstFragment)
        }

        binding.button.setOnClickListener{
            insertDataToDatabase()
        }
        return view
    }

    private  fun insertDataToDatabase() {
        val firstName = binding.editFirstName.text.toString()
        val lastName = binding.editLastName.text.toString()
        val age = binding.editAge.text

        if(inputCheck(firstName, lastName, age)){
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()))
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Student added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }else{
            Toast.makeText(requireContext(),"Fill all fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }
}