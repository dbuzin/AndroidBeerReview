package dev.dbuzin.android.core.presentation

interface ViewEffect

data class SnackBarErrorEffect(val errorMessage: String) : ViewEffect