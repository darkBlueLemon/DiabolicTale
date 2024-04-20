package com.invincible.diabolictale

data class UIState(
    val isAddingPost: Boolean = false,
    val isDoneAnimationEnabled: Boolean = false,
    val isViewingTradeFintechDetails: Boolean = false,
    val isViewingSMEDetails: Boolean = false,
    val isSigningIn: Boolean = false,
    val isSigningUp: Boolean = false,
    val isFirstTime: Boolean = true,
    val isViewingSMEMarket: Boolean = false,
    val isViewingFTMarket: Boolean = true,
)
