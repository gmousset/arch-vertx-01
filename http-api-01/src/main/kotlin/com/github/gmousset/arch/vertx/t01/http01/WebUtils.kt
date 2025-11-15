package com.github.gmousset.arch.vertx.t01.http01

import com.fasterxml.jackson.databind.ObjectMapper
import io.vertx.core.Handler
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.RoutingContext
import org.slf4j.Logger

interface WebUtils {
    val logger: Logger
    val objectMapper: ObjectMapper

    fun addRoute(
        router: Router,
        method: HttpMethod = HttpMethod.GET,
        path: String,
        handler: Handler<RoutingContext>,
    ) {
        logger.debug("Add route ${method.name()} $path")
        router
            .route()
            .method(method)
            .path(path)
            .handler { reqIntercept(it, handler) }
    }

    fun reqIntercept(
        ctx: RoutingContext,
        handler: Handler<RoutingContext>,
    ) {
        handler.handle(ctx)
        logger.debug("${ctx.request().method()} ${ctx.request().path()}")
    }

    fun writeResponse(
        ctx: RoutingContext,
        body: Any?,
        contentType: String = "application/json",
    ) {
        val json = objectMapper.writeValueAsString(body)
        val response = ctx.response()
        response.putHeader("Content-Type", contentType)
        response.setStatusCode(200).end(json)
    }
}
