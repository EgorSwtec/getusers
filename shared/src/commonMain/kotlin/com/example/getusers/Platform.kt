package com.example.getusers

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform