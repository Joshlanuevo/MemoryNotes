package com.example.core.data

data class Note(
    var id: Long = 0L,
    var title: String,
    var content: String,
    var creationTime: Long,
    var updateTime: Long,
)