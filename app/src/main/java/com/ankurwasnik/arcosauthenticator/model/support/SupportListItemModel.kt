package com.ankurwasnik.arcosauthenticator.model.support

import android.content.Context

data class SupportListItemModel(
    val name :String,
    val desc :String,
    val onClick : (Context)->Unit = {}
)
