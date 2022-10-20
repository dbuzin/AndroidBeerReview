package dev.dbuzin.android.core.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.dbuzin.android.core.coroutines.CompositeJob
import dev.dbuzin.android.core.error.getUserFriendlyText
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected val compositeJob = CompositeJob()

    protected var effectChannel = Channel<ViewEffect>()
    var viewEffects = effectChannel.receiveAsFlow()

    private val actions = MutableSharedFlow<ViewAction>()

    init {
        viewModelScope.launch { actions.collect(::onAction) }
    }

    fun sendAction(action: ViewAction) {
        viewModelScope.launch { actions.emit(action) }
    }

    override fun onCleared() {
        super.onCleared()
        compositeJob.cancelAll()
    }

    open fun showError(throwable: Throwable) {
        launch {
            effectChannel.send(
                SnackBarErrorEffect(
                    errorMessage = throwable.getUserFriendlyText()
                )
            )
        }
    }

    abstract fun onAction(action: ViewAction)

    protected fun sendEffect(effect: ViewEffect) {
        launch {
            effectChannel.send(effect)
        }
    }

    protected inline fun launch(
        crossinline errorHandler: (Throwable) -> Unit = ::showError,
        crossinline procedure: suspend (CoroutineScope) -> Unit,
    ): Job {
        val handler = CoroutineExceptionHandler { _, throwable ->
            when(throwable) {
                //common exceptions
                else -> errorHandler(throwable)
            }
        }
        return viewModelScope.launch(handler) {
            procedure(this)
        }.apply { compositeJob.add(this) }
    }
}