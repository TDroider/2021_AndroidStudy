import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*

fun main() {
    runBlocking {
        // 1 ~ 10までの整数を流すストリーム
        val stream = (1..10).asFlow()
        
		val resultStream = stream.filter{data ->
            // 偶数のみ絞り込む
            data % 2 == 0
	    }
        .map{data ->
            // filter後のストリームに対して、それぞれ100倍する
            data * 100
        }
        
        // collect: 購読
		resultStream.collect() {
            // データが通知されたら表示する
	        println(it)
    	}
        
        /**
        println("------------")
        
        // collect: 購読
		resultStream.collect() {
            // データが通知されたら表示する
	        println(it)
    	}
        */
    }
}
