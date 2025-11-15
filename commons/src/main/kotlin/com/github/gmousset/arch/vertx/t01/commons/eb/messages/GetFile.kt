package com.github.gmousset.arch.vertx.t01.commons.eb.messages

import java.io.Serializable

data class GetFile(
    val id: String,
) : Serializable
