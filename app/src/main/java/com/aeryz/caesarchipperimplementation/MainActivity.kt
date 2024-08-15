package com.aeryz.caesarchipperimplementation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.aeryz.caesarchipperimplementation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding : ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setClickListener()
        observeViewModel()
    }

    private fun setClickListener() {
        binding.btnEncrypt.setOnClickListener {
            val plainText = binding.etPlainText.text.toString()
            val encryptKey = binding.etEncryptKey.text.toString().toIntOrNull() ?: 0
            viewModel.encryptText(plainText, encryptKey)
        }
        binding.btnDecrypt.setOnClickListener {
            val cipherText = binding.etDecryptedText.text.toString()
            val decryptKey = binding.etDecryptKey.text.toString().toIntOrNull() ?: 0
            viewModel.decryptText(cipherText, decryptKey)
        }
    }

    private fun observeViewModel() {
        viewModel.encryptedText.observe(this, { result ->
            binding.tvEncryptedText.text = "Encrypted Text : $result"
        })

        viewModel.decryptedText.observe(this, { result ->
            binding.tvDecryptedText.text = "Decrypted Text : $result"
        })
    }

}