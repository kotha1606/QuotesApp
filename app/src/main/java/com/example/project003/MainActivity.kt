package com.example.project003

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.project003.databinding.ActivityMainBinding
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getquote()

        binding.button.setOnClickListener {
            getquote()
        }

    }

    private fun inprogress(progress: Boolean) {
        if(progress) {
            binding.progressBar.visibility = View.VISIBLE
            binding.button.visibility = View.GONE
        }else{
            binding.progressBar.visibility = View.GONE
            binding.button.visibility = View.VISIBLE
        }
    }

    @SuppressLint("SuspiciousIndentation")
    private fun getquote() {
        inprogress(true)

        GlobalScope.launch {

            try {
                val response= RetrofitInstance.quoteapi.randomQuote()
                    runOnUiThread {
                        inprogress(false)
                        response.body()?.first()?.let {
                            setui(it)
                        }
                    }
            }catch (e:Exception){

            }
        }


    }

    private fun setui(quoteModelItem: QuoteModelItem) {
       binding.apply {
           quotetxt.text=quoteModelItem.q
           authortxt.text=quoteModelItem.a
       }
    }
}