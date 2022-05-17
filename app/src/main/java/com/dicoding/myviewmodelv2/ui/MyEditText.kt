package com.dicoding.myviewmodelv2.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.content.ContextCompat
import com.dicoding.myviewmodelv2.R

//Custome View
class MyEditText: AppCompatEditText, View.OnTouchListener {
    internal lateinit var mClearButtonImage: Drawable

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    //metode onDraw() untuk menambahkan attribute lain pada kelas yaitu kelas MyEditText
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        hint = "Masukkan Plaintext Atau Chipertext"
        textAlignment = View.TEXT_ALIGNMENT_VIEW_START
    }

    private fun init() {
        mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
        setOnTouchListener(this)

        // Kode untuk menampilkan clear button ketika ada perubahan teks
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // Do nothing.
            }
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (s.toString().isNotEmpty()) showClearButton() else hideClearButton()
            }
            override fun afterTextChanged(s: Editable) {
                // Do nothing.
            }
        })
    }

    //Fungsi Untuk Menampilkan Clear Button
    private fun showClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null,
            mClearButtonImage, null)
    }

    //Fungsi Untuk Menyembunyikan Clear Button
    private fun hideClearButton() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null)
    }

    //Kode untuk aksi ketika tombol clear,ketika komponen ditekan, maka akan terbaca pada OnTouch.  Kemudian dilakukan pengecekan apakah area yang ditekan adalah area tempat tombol silang berada. Jika iya maka isClearButtonClick akan kita tandai true
    override fun onTouch(v: View?, event: MotionEvent): Boolean {
        if (compoundDrawables[2] != null) {
            val clearButtonStart: Float
            val clearButtonEnd: Float
            var isClearButtonClicked = false
            when (layoutDirection) {
                View.LAYOUT_DIRECTION_RTL -> {
                    clearButtonEnd = (mClearButtonImage.intrinsicWidth + paddingStart).toFloat()
                    when {
                        event.x < clearButtonEnd -> isClearButtonClicked = true
                    }
                }
                else -> {
                    clearButtonStart = (width - paddingEnd - mClearButtonImage.intrinsicWidth).toFloat()
                    when {
                        event.x > clearButtonStart -> isClearButtonClicked = true
                    }
                }
            }
            when {
                isClearButtonClicked -> when {
                    event.action == MotionEvent.ACTION_DOWN -> {
                        mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
                        showClearButton()
                        return true
                    }
                    event.action == MotionEvent.ACTION_UP -> {
                        mClearButtonImage = ContextCompat.getDrawable(context, R.drawable.ic_baseline_close_24) as Drawable
                        when {
                            text != null -> text?.clear()
                        }
                        hideClearButton()
                        return true
                    }
                    else -> return false
                }
                else -> return false
            }
        }
        return false
    }



}