package com.invincible.diabolictale

interface UIEvent {
//    object SaveTask: TaskEvent
//    data class SetTaskName(val taskName: String): TaskEvent
//    data class SetNote(val note: String): TaskEvent
//    data class SetDate(val date: String): TaskEvent
//    data class SetTime(val time: String): TaskEvent
//    data class SetPriority(val priority: Int): TaskEvent
//    object ShowAddTaskDialog: TaskEvent
//    object HideAddTaskDialog: TaskEvent
//    object ShowSettingsDialog: TaskEvent
//    object HideSettingsDialog: TaskEvent
//    data class SortTasks(val sortType: SortType): TaskEvent
//    data class DeleteTask(val task: Task): TaskEvent

    object ShowAddPost: UIEvent
    object HideAddPost: UIEvent
    object ShowDoneAnimation: UIEvent
    object HideDoneAnimation: UIEvent
    object ShowTradeFintech: UIEvent
    object HideTradeFintech: UIEvent
    object ShowSME: UIEvent
    object HideSME: UIEvent
    object ShowSignIn: UIEvent
    object HideSignIn: UIEvent
    object ShowSignUp: UIEvent
    object HideSignUp: UIEvent
    object ShowFirstTime: UIEvent
    object HideFirstTime: UIEvent
}