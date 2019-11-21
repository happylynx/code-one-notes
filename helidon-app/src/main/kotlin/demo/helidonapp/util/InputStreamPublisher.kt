package demo.helidonapp.util

import io.helidon.common.http.DataChunk
import io.helidon.common.reactive.Flow
import io.helidon.common.reactive.Flow.Subscription
import java.io.BufferedInputStream
import java.io.InputStream
import java.lang.IllegalStateException
import java.nio.ByteBuffer
import java.util.concurrent.atomic.AtomicLong

class InputStreamPublisher(inputStream: InputStream) : Flow.Publisher<DataChunk> {
    val requested =  AtomicLong()
    var cancelled = false
    var completed = false
    val inputStream = BufferedInputStream(inputStream, 1024 * 1024)
    lateinit var subscriber: Flow.Subscriber<in DataChunk>
    override fun subscribe(subscriber: Flow.Subscriber<in DataChunk>) {
        if (this::subscriber.isInitialized) {
            throw IllegalStateException()
        }
        this.subscriber = subscriber
        subscriber.onSubscribe(object : Subscription {
            override fun cancel() {
                cancelled = true
                inputStream.close()
            }

            override fun request(n: Long) {
                if (n < 0) {
                    throw IllegalStateException()
                }
                requested.addAndGet(n)
                publish()
            }
        })
    }

    private fun publish() {
        if (cancelled) {
            return
        }
        if (completed) {
            return
        }
        if (requested.getAndUpdate { if (it <= 0) 0 else { it - 1 } } <= 0) {
            return
        }
        val buffer = ByteArray(1024 * 1024)
        val bytesRead = inputStream.read(buffer)
        if (bytesRead < 1) {
            complete()
        }
        val chunk = DataChunk.create(ByteBuffer.wrap(buffer, 0, bytesRead))
        subscriber.onNext(chunk)
    }

    @Synchronized
    private fun complete() {
        if (completed) {
            return
        }
        completed = true
        inputStream.close()
        subscriber.onComplete()
    }
}
