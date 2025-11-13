package com.github.gmousset.arch.vertx.t01.http01

import com.github.gmousset.arch.vertx.t01.commons.CommonsVerticle
import io.vertx.core.Future
import io.vertx.core.internal.logging.LoggerFactory

class HttpApi01Verticle : CommonsVerticle("HTTP-API-01") {
    private val logger = LoggerFactory.getLogger(HttpApi01Verticle::class.java)

    override fun startVerticle(): Future<*> =
        vertx
            .createHttpServer()
            .requestHandler { req ->
                this.logger.info("received http request: ${req.method()} ${req.path()}")
                req.response().end("coucou")
            }.listen(8080)
}
