package com.github.gmousset.arch.vertx.t01.http01

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.gmousset.arch.vertx.t01.commons.dto.File
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.EventBusPath
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.GetFile
import com.github.gmousset.arch.vertx.t01.commons.eb.messages.SearchFiles
import com.github.gmousset.arch.vertx.t01.commons.verticles.CommonsVerticle
import io.vertx.core.Future
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class HttpApi01Verticle :
    CommonsVerticle("HTTP-API-01"),
    WebUtils {
    override val logger: Logger = LoggerFactory.getLogger(HttpApi01Verticle::class.java)
    override val objectMapper = jacksonObjectMapper()

    override fun startVerticle(): Future<*> {
        val server = vertx.createHttpServer()
        val router = Router.router(vertx)
        this.addRoute(router = router, path = "/api/files", handler = this::getFiles)
        this.addRoute(router = router, path = "/api/files/:id", handler = this::getFile)
        this.addRoute(router = router, path = "/api/files", handler = this::postFile, method = HttpMethod.POST)
        this.addRoute(router = router, path = "/api/files/:id", handler = this::deleteFile, method = HttpMethod.DELETE)
        return server.requestHandler(router).listen(8080)
    }

    private fun getFiles(ctx: RoutingContext) {
        vertx.eventBus().request<List<File>>(EventBusPath.File.LIST.path, SearchFiles()).onComplete {
            this.writeResponse(ctx, it.result().body())
        }
    }

    private fun getFile(ctx: RoutingContext) {
        vertx.eventBus().request<File>(EventBusPath.File.GET.path, GetFile(id = "1")).onComplete {
            this.writeResponse(ctx, it.result().body())
        }
    }

    private fun postFile(ctx: RoutingContext) {
        ctx.response().end("Hello")
    }

    private fun deleteFile(ctx: RoutingContext) {
        ctx.response().end("Bye")
    }
}
