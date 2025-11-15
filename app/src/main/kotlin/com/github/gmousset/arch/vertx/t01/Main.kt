package com.github.gmousset.arch.vertx.t01

import com.github.gmousset.arch.vertx.t01.http01.HttpApi01Verticle
import com.github.gmousset.arch.vertx.t01.s01.FileVerticle
import io.opentelemetry.api.trace.propagation.W3CTraceContextPropagator
import io.opentelemetry.context.propagation.ContextPropagators
import io.opentelemetry.exporter.otlp.trace.OtlpGrpcSpanExporter
import io.opentelemetry.sdk.OpenTelemetrySdk
import io.opentelemetry.sdk.resources.Resource
import io.opentelemetry.sdk.trace.SdkTracerProvider
import io.opentelemetry.sdk.trace.export.BatchSpanProcessor
import io.opentelemetry.semconv.ServiceAttributes
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.tracing.opentelemetry.OpenTelemetryOptions

fun main() {
    val exporter = OtlpGrpcSpanExporter.builder().setEndpoint("http://localhost:4317").build()
    val tracerProvider =
        SdkTracerProvider
            .builder()
            .setResource(
                Resource
                    .builder()
                    .put(ServiceAttributes.SERVICE_NAME, "arch-vertx-01")
                    .put(ServiceAttributes.SERVICE_VERSION, "1.0.0")
                    .build(),
            ).addSpanProcessor(
                BatchSpanProcessor.builder(exporter).build(),
            ).build()
    val openTelemetry =
        OpenTelemetrySdk
            .builder()
            .setTracerProvider(tracerProvider)
            .setPropagators(ContextPropagators.create(W3CTraceContextPropagator.getInstance()))
            .buildAndRegisterGlobal()
    val vertx = Vertx.vertx(VertxOptions().setTracingOptions(OpenTelemetryOptions(openTelemetry)))
    vertx.deployVerticle(FileVerticle())
    vertx.deployVerticle(HttpApi01Verticle())
}
