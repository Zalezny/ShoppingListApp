package com.example.shooppingg.view.utils

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern


fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(this).matches()
fun CharSequence?.isValidPassword(): Boolean {
    val PASSWORD_PATTERN: Pattern = Pattern.compile(
        "^(?=.*[A-Za-z])(?=.*\\\\d)(?=.*[$@$!%*#?&])[A-Za-z\\\\d$@$!%*#?&]{8,}$"
    )
    return !TextUtils.isEmpty(this) && PASSWORD_PATTERN.matcher(this).matches()
}
