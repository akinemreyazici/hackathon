package com.works.hackathon.view


import androidx.fragment.app.Fragment
import com.works.hackathon.databinding.FragmentRegisterBinding
import com.works.hackathon.viewmodel.RegisterViewModel

class RegisterFragment : Fragment() {

    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: RegisterViewModel


}