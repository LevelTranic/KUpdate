package one.tranic.kupdate

interface Updater {
    fun getUpdate(resourceId: String, localVersion: String): Entry<Int, String>?
    fun updater(resourceId: String)
}