import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    val flow = flow {
        emit(1)
        delay(1000L)
        emit(2)
        delay(1000L)
        emit(3)
    }
    
    val flowOf = flowOf(4, 5, 6)
    val asFlow = (7..10).asFlow()
    
    runBlocking {
        launch {
        	flow.collect {
        	    println("flow{}: $it")
    	    }
        }
        launch {
	        flow.collect {
            	println("flow{}: $it")
        	}
        }
        /**
        launch {
        	flowOf.collect {
        	    println("flowOf(): $it")
    	    }
        }
        launch {
	        asFlow.collect {
            	println("asFlow(): $it")
        	}
        }
        **/
    }
}