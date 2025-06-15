package client

interface Client<out T> {
    fun getClient(): T
}
