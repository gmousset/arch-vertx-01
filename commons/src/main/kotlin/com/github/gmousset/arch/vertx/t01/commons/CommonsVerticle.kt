package com.github.gmousset.arch.vertx.t01.commons

import io.vertx.core.Future
import io.vertx.core.VerticleBase
import org.slf4j.LoggerFactory

abstract class CommonsVerticle(
    val name: String,
) : VerticleBase() {
    private val logger = LoggerFactory.getLogger(CommonsVerticle::class.java)

    abstract fun startVerticle(): Future<*>

    override fun start(): Future<*> {
        this.logger.info("Starting verticle $name...")
        return this.startVerticle().onSuccess {
            this.logger.info("Verticle $name started")
        }
    }
}
