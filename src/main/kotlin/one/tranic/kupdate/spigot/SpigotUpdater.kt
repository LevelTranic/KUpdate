package one.tranic.kupdate.spigot

import okhttp3.OkHttpClient
import okhttp3.Request
import one.tranic.kupdate.Entry
import one.tranic.kupdate.UpdateUtils
import one.tranic.kupdate.Updater

class SpigotUpdater(private val resourceId: String, private val localVersion: String) : Updater {
    private val client = OkHttpClient()

    override fun getUpdate(): Entry<Int, String> {
        val request = Request.Builder()
            .url("https://api.spigotmc.org/legacy/update.php?resource=$resourceId")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36"
            )
            .build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) return@use Entry(-9999, it.body!!.string())
            Entry(UpdateUtils.cmpVer(localVersion, it.body!!.string()), "")
        }
    }
}