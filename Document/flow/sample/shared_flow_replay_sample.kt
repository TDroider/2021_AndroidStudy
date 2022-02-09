import kotlinx.coroutines.*
import kotlin.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val _sharedFlow = MutableSharedFlow<Int>(replay = 3)
    
    scope.launch {
        _sharedFlow.emit(1)
        _sharedFlow.emit(2)
        _sharedFlow.emit(3)
        _sharedFlow.emit(4)
        _sharedFlow.emit(5)
        
      
        launch {
            _sharedFlow.collect {
                println("collect_1: $it")
            }
        }
        
        delay(100L)
        
        _sharedFlow.emit(4)
        _sharedFlow.emit(5)
    }
}
