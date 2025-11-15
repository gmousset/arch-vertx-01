package com.github.gmousset.arch.vertx.t01.commons.dto

import java.io.Serializable

data class File(
    val id: String,
    val name: String,
    val size: Long,
) : Serializable
