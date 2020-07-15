package com.saitejajanjirala.startoonlabstask.fragments.enterphonenumber

import android.content.Context
import android.inputmethodservice.InputMethodService
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.saitejajanjirala.startoonlabstask.R
import com.saitejajanjirala.startoonlabstask.databinding.FragmentEnternumberBinding
import com.saitejajanjirala.startoonlabstask.utils.Connectivity

class EnternumberFragment : Fragment() {
    private lateinit var binding: FragmentEnternumberBinding
    private lateinit var enterphonenumberViewModel: EnterphonenumberViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,
            R.layout.fragment_enternumber,container,false)
        enterphonenumberViewModel=ViewModelProvider(this)
            .get(EnterphonenumberViewModel::class.java)
        binding.getaccessnumber.setOnClickListener {
            val inputMethodManager=requireContext().getSystemService(Context.INPUT_METHOD_SERVICE)
                                    as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(it.windowToken,0)
            val obj=Connectivity(requireContext())
            if(obj.checkconnectivity()){
                val score=binding.numbertext.text.toString()
                if(enterphonenumberViewModel.checkifnumbervalid(score)){
                    val action=EnternumberFragmentDirections.
                    actionEnternumberFragment2ToEnteracesscodeFragment2(score)
                    NavHostFragment.findNavController(this).navigate(action)
                }
                else{
                    Snackbar.make(binding.topenternumber,"Enter The Correct number",
                    Snackbar.LENGTH_LONG)
                        .setAction("close"){
                        }
                        .show()
                }
            }
            else{
                obj.showdialog()
            }
        }
        return binding.root
    }

}