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
                    isAddingPost = false,
                    isViewingSME = false,
                    isViewingTradeFintech = false
                )
                }
            }
            UIEvent.HideDoneAnimation -> {
                _state.update { it.copy(
                    isDoneAnimationEnabled = false,
                )
                }
            }
            UIEvent.ShowTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintech = true,
                )
                }
            }
            UIEvent.HideTradeFintech -> {
                _state.update { it.copy(
                    isViewingTradeFintech = false,
                )
                }
            }
            UIEvent.ShowSME -> {
                _state.update { it.copy(
                    isViewingSME = true,
                )
                }
            }
            UIEvent.HideSME -> {
                _state.update { it.copy(
                    isViewingSME = false,
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
        }
    }
}