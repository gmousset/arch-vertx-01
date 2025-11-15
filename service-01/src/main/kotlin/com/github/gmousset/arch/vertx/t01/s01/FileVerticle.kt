package com.github.gmousset.arch.vertx.t01.s01

import com.github.gmousset.arch.vertx.t01.commons.dto.File
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.EventBusPath
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.GetFile
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.SearchFiles
import com.github.gmousset.arch.vertx.t01.commons.verticles.CommonsVerticle
import io.vertx.core.Future
import io.vertx.core.eventbus.Message
import io.vertx.core.internal.ContextInternal

class FileVerticle : CommonsVerticle("SERVICE-01") {
    override fun startVerticle(): Future<*> {
        val eventBus = vertx.eventBus()
        eventBus.consumer(EventBusPath.File.LIST.path, this::list)
        eventBus.consumer(EventBusPath.File.GET.path, this::get)
        return (context as ContextInternal).succeededFuture<Any>()
    }

    private fun list(message: Message<SearchFiles>) {
        message.reply(
            listOf(
                File(id = "0", name = "f0.txt", size = 103),
                File(id = "1", name = "f1.txt", size = 14),
                File(id = "2", name = "f2.txt", size = 5432),
            ),
        )
    }

    private fun get(message: Message<GetFile>) {
        message.reply(File(id = "0", name = "f0.txt", size = 103))
    }
}
