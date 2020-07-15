package com.saitejajanjirala.startoonlabstask.fragments.sendemail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.saitejajanjirala.startoonlabstask.R
import com.saitejajanjirala.startoonlabstask.activities.notice.NoticeActivity
import kotlinx.android.synthetic.main.activity_notice.*
import kotlinx.android.synthetic.main.fragment_sendemail.*


class SendemailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view= inflater.inflate(R.layout.fragment_sendemail, container, false)
        try {
            view.findViewById<Button>(R.id.sendemail).setOnClickListener {
                val emailIntent = Intent()
                emailIntent.action = android.content.Intent.ACTION_SEND
                emailIntent.type = "plain/text";
                emailIntent.putExtra(
                    android.content.Intent.EXTRA_EMAIL,
                    arrayOf("support@pheezee.com")
                );
                emailIntent.putExtra(
                    android.content.Intent.EXTRA_SUBJECT,
                    "Access Code Not Found"
                );

                emailIntent.putExtra(
                    android.content.Intent.EXTRA_TEXT,
                    "User name: Jitender Rate"
                );

               startActivityForResult(
                    Intent.createChooser(
                        emailIntent, "Send mail..."
                    ), 1234
                )
            }
        }
        catch (e:Exception){
            Log.i("error",e.message.toString())
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==1234){
            val intent=Intent(requireContext(),NoticeActivity::class.java)
            intent.putExtra("type","sentemail")
            startActivity(intent)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}