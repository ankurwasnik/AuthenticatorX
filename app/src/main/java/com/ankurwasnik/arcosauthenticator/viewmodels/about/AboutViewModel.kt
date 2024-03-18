package com.ankurwasnik.arcosauthenticator.viewmodels.about

import android.content.Context
import android.content.Intent
import android.net.Uri
import com.ankurwasnik.arcosauthenticator.model.support.SupportListItemModel

class AboutViewModel {
    val AboutItemList = listOf<SupportListItemModel>(
        SupportListItemModel(
            name = "Product",
            desc = "ARCON Authenticator"
        ),
        SupportListItemModel(
            name = "Email",
            desc = "support@arconnet.com",
            onClick = {
                it.startActivity(Intent(Intent.ACTION_SENDTO).apply {
                    this.type = "text/plain"
                })
            }
        ),
        SupportListItemModel(
            name = "Website",
            desc = "http://www.arconnet.com",
            onClick = {
                it.startActivity(Intent(Intent.ACTION_VIEW).apply {
                    this.data = Uri.parse("http://www.arconnet.com")
                })
            }
        ),
        SupportListItemModel(
            name = "Version",
            desc = "1.0"
        ),
        SupportListItemModel(
            name = "Copyright",
            desc = "&copy; ARCON 2019"
        )
    )
}
