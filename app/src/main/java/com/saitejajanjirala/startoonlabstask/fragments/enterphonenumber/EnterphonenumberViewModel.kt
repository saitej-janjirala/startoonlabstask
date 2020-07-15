package com.saitejajanjirala.startoonlabstask.fragments.enterphonenumber

import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel

class EnterphonenumberViewModel:ViewModel() {
    fun checkifnumbervalid(number:String):Boolean{
        var bool=false
        if(number.length==10){
            bool=true
        }
        return bool
    }
}