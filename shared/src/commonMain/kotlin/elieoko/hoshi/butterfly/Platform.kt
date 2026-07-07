package elieoko.hoshi.butterfly

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform