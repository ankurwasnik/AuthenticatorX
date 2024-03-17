package com.ankurwasnik.arcosauthenticator.model.settings

import androidx.compose.ui.graphics.vector.ImageVector

data class SettingListItemModel(
    val icon : ImageVector?,
    val name :String,
    val onItemClick : ()->Unit = {}
)
