package com.example.applicationtest

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.CompoundButton
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_buy.*

class BuyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buy)

        button4.setOnClickListener({
            val builder = AlertDialog.Builder(this)
                .setMessage("구매 확인에 동의해주세요.")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                    })
            builder.show()
        })

        buy_check.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                button4.setOnClickListener({
                    val intent = Intent(this, CheckBuyActivity::class.java)
                    startActivity(intent)
                })
            }
            else {
                button4.setOnClickListener({
                    val builder = AlertDialog.Builder(this)
                        .setMessage("구매 확인에 동의해주세요.")
                        .setPositiveButton("확인",
                            DialogInterface.OnClickListener { dialog, id ->
                            })
                    builder.show()
                })
            }
        }
    }
}