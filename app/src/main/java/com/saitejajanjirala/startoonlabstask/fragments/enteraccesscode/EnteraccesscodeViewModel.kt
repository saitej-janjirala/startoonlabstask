package com.saitejajanjirala.startoonlabstask.fragments.enteraccesscode

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit

class EnteraccesscodeViewModel: ViewModel() {
    private lateinit var resendtoken:PhoneAuthProvider.ForceResendingToken
    var _otp=MutableLiveData<String>()
    val otp:LiveData<String>
       get() = _otp
    var _codesent=MutableLiveData<Boolean>()
    val codesent:LiveData<Boolean>
        get()=_codesent
    var _failedmessage= MutableLiveData<String>()
    val failedmessage:LiveData<String>
        get()=_failedmessage
    var verificationid=""
    var _otpsuccess=MutableLiveData<Boolean>()
    val otpsuccess:LiveData<Boolean>
        get() = _otpsuccess
    var _otpfailure= MutableLiveData<String>()
    val otpfailure:LiveData<String>
        get() = _otpfailure
    fun checkifotpisvalid(code:String){
        val phoneAuthCredential:PhoneAuthCredential=PhoneAuthProvider
            .getCredential(verificationid,code)
        FirebaseAuth.getInstance().signInWithCredential(phoneAuthCredential)
            .addOnSuccessListener {
                _otpsuccess.value=true
            }
            .addOnFailureListener {
                _otpsuccess.value=false
                _otpfailure.value=it.message.toString()
            }
    }
}