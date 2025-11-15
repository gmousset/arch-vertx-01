package com.github.gmousset.arch.vertx.t01.commons.eb.messages

class EventBusPath {
    enum class File(
        val path: String,
    ) {
        LIST("file.list"),
        GET("file.get"),
        CREATE("file.create"),
        DELETE("file.delete"),
    }
}
