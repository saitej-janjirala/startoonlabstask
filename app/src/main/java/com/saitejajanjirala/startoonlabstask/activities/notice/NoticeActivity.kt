package com.saitejajanjirala.startoonlabstask.activities.notice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.saitejajanjirala.startoonlabstask.R
import kotlinx.android.synthetic.main.activity_notice.*
import java.text.SimpleDateFormat
import java.util.*

class NoticeActivity : AppCompatActivity() {
    var type=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notice)
        type= intent.getStringExtra("type").toString()
        if(type == "verified"){
            noticeheading.text=resources.getString(R.string.user_verified)
            sentemailtext.visibility=View.GONE
            verifiedimage.visibility=View.VISIBLE
        }
        else{
            noticeheading.text=resources.getString(R.string.thank_you)
            verifiedimage.visibility=View.GONE
            sentemailtext.visibility=View.VISIBLE
        }
        val date=SimpleDateFormat("hh:mm aa, dd MMM yyyy",
            Locale("en","IN")).format(Date())
        noticetime.text=date
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}