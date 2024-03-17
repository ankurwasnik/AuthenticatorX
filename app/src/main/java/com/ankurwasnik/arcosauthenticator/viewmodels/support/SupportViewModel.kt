package com.ankurwasnik.arcosauthenticator.viewmodels.support

import com.ankurwasnik.arcosauthenticator.model.support.SupportListItemModel

class SupportViewModel {
    val SupportItemList = listOf<SupportListItemModel>(
        SupportListItemModel(
            name = "Step 1",
            desc = "Menu > Settings"
        ),
        SupportListItemModel(
            name = "Step 2",
            desc = "Send Unique ID and Validation Key to ARCON PAM admin"
        ),
        SupportListItemModel(
            name = "Step 3",
            desc = "Enter validation password received from ARCON PAM admin to validate phone and again send validation code to ARCON PAM admin"
        ),
        SupportListItemModel(
            name = "Step 4",
            desc = "Check for phone activation status (if status is Activated, you have successfully validate else contact ARCON PAM Support"
        )
    )
}