package com.invincible.diabolictale

data class UIState(
    val isAddingPost: Boolean = false,
    val isDoneAnimationEnabled: Boolean = false,
    val isViewingTradeFintech: Boolean = false,
    val isViewingSME: Boolean = false,
    val isSigningIn: Boolean = false,
    val isSigningUp: Boolean = false,
    val isFirstTime: Boolean = true,
)
