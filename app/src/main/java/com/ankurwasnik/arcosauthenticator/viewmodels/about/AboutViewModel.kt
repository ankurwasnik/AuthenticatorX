package com.ankurwasnik.arcosauthenticator.viewmodels.about

import com.ankurwasnik.arcosauthenticator.model.support.SupportListItemModel

class AboutViewModel {
    val AboutItemList = listOf<SupportListItemModel>(
        SupportListItemModel(
            name = "Product",
            desc = "ARCON Authenticator"
        ),
        SupportListItemModel(
            name = "Email",
            desc = "support@arconnet.com"
        ),
        SupportListItemModel(
            name = "Website",
            desc = "http://www.arconnet.com"
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
