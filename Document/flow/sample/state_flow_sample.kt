import kotlinx.coroutines.*
import kotlin.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val scope = CoroutineScope(EmptyCoroutineContext)
    val _stateFlow = MutableStateFlow<Int>(0)
    
    scope.launch {
        _stateFlow.collect {
            println("collect_1: $it")
        }
    }
    
    // 連続して変更した場合、最後のみ通知
    _stateFlow.value = 1
    _stateFlow.value = 2
    
    scope.launch{
        delay(100L)
        // 同じ値は流れない
        _stateFlow.value = 2
        delay(100L)
        _stateFlow.value = 3        
    }
}
