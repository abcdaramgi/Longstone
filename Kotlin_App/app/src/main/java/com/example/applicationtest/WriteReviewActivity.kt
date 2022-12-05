package com.example.applicationtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RatingBar
import kotlinx.android.synthetic.main.activity_review.*
import kotlinx.android.synthetic.main.activity_write_review.*

class WriteReviewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write_review)

        ratingBar4.onRatingBarChangeListener =
            RatingBar.OnRatingBarChangeListener{
            ratingBar, rating, fromUser -> ratingBar4.rating = rating
        }
    }
}