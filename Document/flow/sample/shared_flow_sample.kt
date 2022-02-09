import kotlinx.coroutines.*
import kotlin.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val _sharedFlow = MutableSharedFlow<Int>()
    
    scope.launch {
        _sharedFlow.collect {
            println("collect_1: $it")
        }
    }
    
    scope.launch {
        _sharedFlow.collect {
            println("collect_2: $it")
        }
    }
    
    scope.launch {
        _sharedFlow.emit(1)
        _sharedFlow.emit(2)
        _sharedFlow.emit(3)
    }
    
    scope.launch {
        _sharedFlow.collect {
            println("collect_2: $it")
        }
    }
}