package com.saitejajanjirala.startoonlabstask.fragments.enteraccesscode

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.initialize
import com.saitejajanjirala.startoonlabstask.R
import com.saitejajanjirala.startoonlabstask.activities.notice.NoticeActivity
import com.saitejajanjirala.startoonlabstask.databinding.FragmentEnteracesscodeBinding
import java.lang.Exception
import java.util.concurrent.TimeUnit


class EnteracesscodeFragment : Fragment() {
    private lateinit var binding: FragmentEnteracesscodeBinding
    private lateinit var enteraccesscodeViewModel: EnteraccesscodeViewModel
    var phonenumber = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_enteracesscode,
            container, false
        )
        binding.donthaveaccesscode.setOnClickListener {
            try {
                val action =
                    EnteracesscodeFragmentDirections.actionEnteracesscodeFragment2ToSendemailFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
            catch (e:Exception){
                Log.i("errpr",e.message.toString())
            }
        }
        enteraccesscodeViewModel = ViewModelProvider(this)
            .get(EnteraccesscodeViewModel::class.java)
        phonenumber = EnteracesscodeFragmentArgs.fromBundle(requireArguments()).phonenumber
        binding.enteraccescodeviewmodel = enteraccesscodeViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        enteraccesscodeViewModel.otp.observe(viewLifecycleOwner, Observer {
            binding.accesscodetext.setText(it, TextView.BufferType.EDITABLE)
        })
        enteraccesscodeViewModel.codesent.observe(viewLifecycleOwner, Observer {
            if (it) {
                Toast.makeText(requireActivity(), "codesent", Toast.LENGTH_SHORT).show()
            }
        })
        enteraccesscodeViewModel.failedmessage.observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireActivity(), it, Toast.LENGTH_SHORT).show()
        })
        binding.checkvalidaccesscode.setOnClickListener {
            val code=binding.accesscodetext.text.toString()
            if(code.length!=6){
                Snackbar.make(binding.topenteraccesscode,"Enter valid code",
                    Snackbar.LENGTH_SHORT)
                    .setAction("close"){
                    }
            }
            else{
                enteraccesscodeViewModel.checkifotpisvalid(code)
            }
        }
        enteraccesscodeViewModel.otpsuccess.observe(viewLifecycleOwner, Observer {
            if(it){
                Toast.makeText(requireActivity(), "welcome", Toast.LENGTH_SHORT).show()
                val intent= Intent(requireContext(), NoticeActivity::class.java)
                intent.putExtra("type","verified")
                startActivity(intent)
            }
            else{
                Toast.makeText(requireActivity(), enteraccesscodeViewModel.otpfailure.value,
                                            Toast.LENGTH_SHORT).show()
            }
        })
        val mcallbacks=object:PhoneAuthProvider.OnVerificationStateChangedCallbacks(){
            override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                if(p0.smsCode!=null){
                    enteraccesscodeViewModel._otp.value=p0.smsCode
                }
            }
            override fun onVerificationFailed(p0: FirebaseException) {
                enteraccesscodeViewModel._failedmessage.value=p0.message.toString()
            }
            override fun onCodeSent(p0: String, p1: PhoneAuthProvider.ForceResendingToken) {
                enteraccesscodeViewModel._codesent.value=true
                enteraccesscodeViewModel.verificationid=p0
                super.onCodeSent(p0, p1)
            }
        }
        FirebaseApp.initializeApp(requireContext().applicationContext)
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
            "+91$phonenumber",
            60,
            TimeUnit.SECONDS,
            requireActivity(),
            mcallbacks
        )
        return binding.root
    }


}