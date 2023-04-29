package com.qadri.jetpackcomposein30days.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Tasks (
    @StringRes val dayRes: Int,
    @StringRes val taskRes: Int,
    @StringRes val descRes: Int,
    @DrawableRes val imgRes: Int
)