package com.invincible.diabolictale

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update

class ViewModel: ViewModel() {
    private var _navBarIndex by mutableStateOf(2)
    val navBarIndex: Int get() = _navBarIndex
    fun setNavBarIndex(newIndex: Int) {
        _navBarIndex = newIndex
    }

    private val _state = MutableStateFlow(UIState())
    val state: MutableStateFlow<UIState> get() = _state

    fun onEvent(event: UIEvent) {
        when(event) {
            UIEvent.ShowAddPost -> {
                _state.update { it.copy(
                    isAddingPost = true
                )
                }
            }
            UIEvent.HideAddPost -> {
                _state.update { it.copy(
                    isAddingPost = false
                )
                }
            }
            UIEvent.ShowDoneAnimation -> {
                _state.update { it.copy(
                    isDoneAnimationEnabled = true,
                    isViewingFTMarket = false,
                    isViewingSMEMarket = false,
                    isAddingPost = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideDoneAnimation -> {
                _state.update { it.copy(
                    isDoneAnimationEnabled = false,
                )
                }
            }
            UIEvent.ShowLoadingAnimation -> {
                _state.update { it.copy(
                    isLoadingAnimationEnabled = true,
                    isAddingPost = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false,
                    isViewingFTMarket = false,
                    isViewingSMEMarket = false,
                )
                }
            }
            UIEvent.HideLoadingAnimation -> {
                if(_state.value.isRoleSME) {
                    _state.update {
                        it.copy(
                            isLoadingAnimationEnabled = false,
                            isViewingSMEMarket = true
                        )
                    }
                } else {
                    _state.update {
                        it.copy(
                            isLoadingAnimationEnabled = false,
                            isViewingFTMarket = true
                        )
                    }
                }
            }
            UIEvent.ShowTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintechDetails = true,
                )
                }
            }
            UIEvent.HideTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintechDetails = false,
                )
                }
            }
            UIEvent.ShowSME -> {
                _state.update { it.copy(
                    isViewingSMEDetails = true,
                )
                }
            }
            UIEvent.HideSME -> {
                _state.update { it.copy(
                    isViewingSMEDetails = false,
                )
                }
            }
            UIEvent.ShowSignIn -> {
                _state.update { it.copy(
                    isSigningIn = true,
                )
                }
            }
            UIEvent.HideSignIn -> {
                _state.update { it.copy(
                    isSigningIn = false,
                )
                }
            }
            UIEvent.ShowSignUp -> {
                _state.update { it.copy(
                    isSigningUp = true,
                )
                }
            }
            UIEvent.HideSignUp -> {
                _state.update { it.copy(
                    isSigningUp = false,
                )
                }
            }
            UIEvent.ShowFirstTime -> {
                _state.update { it.copy(
                    isFirstTime = true,
                )
                }
            }
            UIEvent.HideFirstTime -> {
                _state.update { it.copy(
                    isFirstTime = false,
                )
                }
            }
            UIEvent.ShowFTMarket -> {
                _state.update { it.copy(
                    isViewingFTMarket = true,
                    isViewingSMEMarket = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideFTMarket -> {
                _state.update { it.copy(
                    isViewingFTMarket = false,
                )
                }
            }
            UIEvent.ShowSMEMarket -> {
                _state.update { it.copy(
                    isViewingSMEMarket = true,
                    isViewingFTMarket = false,
                    isViewingSMEDetails = false,
                    isViewingTradeFintechDetails = false
                )
                }
            }
            UIEvent.HideSMEMarket -> {
                _state.update { it.copy(
                    isViewingSMEMarket = false,
                )
                }
            }
            UIEvent.RoleSME -> {
                _state.update { it.copy(
                    isRoleSME = !_state.value.isRoleSME
                )
                }
            }
            UIEvent.RoleFT -> {
                _state.update { it.copy(
                    isRoleSME = false
                )
                }
            }
            UIEvent.RoleNotFT -> {
                _state.update { it.copy(
                    isRoleSME = true
                )
                }
            }
        }
    }
}