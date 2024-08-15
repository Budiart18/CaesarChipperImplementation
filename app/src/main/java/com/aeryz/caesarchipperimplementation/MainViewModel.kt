package com.aeryz.caesarchipperimplementation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _encryptedText = MutableLiveData<String>()
    val encryptedText: LiveData<String> = _encryptedText

    private val _decryptedText = MutableLiveData<String>()
    val decryptedText: LiveData<String> = _decryptedText

    fun encryptText(plainText: String, shift: Int) {
        val result = caesarEncrypt(plainText, shift)
        _encryptedText.value = result
    }

    fun decryptText(cipherText: String, shift: Int) {
        val result = caesarDecrypt(cipherText, shift)
        _decryptedText.value = result
    }

    private fun caesarEncrypt(text: String, shift: Int): String {
        val result = StringBuilder()
        for (char in text) {
            val base = if (char.isUpperCase()) 'A' else 'a'
            if (char.isLetter()) {
                val shiftedChar = ((char - base + shift) % 26 + base.code).toChar()
                result.append(shiftedChar)
            } else {
                result.append(char)
            }
        }
        return result.toString()
    }

    private fun caesarDecrypt(text: String, shift: Int): String {
        return caesarEncrypt(text, 26 - shift)
    }
}