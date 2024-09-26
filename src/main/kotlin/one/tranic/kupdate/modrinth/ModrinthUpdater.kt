package one.tranic.kupdate.modrinth

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import one.tranic.kupdate.UpdateUtils
import one.tranic.kupdate.Updater
import one.tranic.kupdate.Entry

class ModrinthUpdater: Updater {
    val baseUrl = "https://api.modrinth.com/v2/"
    private val client = OkHttpClient()

    private fun getVersions(slug: String): List<Version>? {
        val request = Request.Builder()
            .url("$baseUrl/project/$slug/version")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36")
            .build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) return@use null
            Json.decodeFromString<List<Version>>(it.body!!.string())
        }
    }

    override fun getUpdate(resourceId: String, localVersion: String): Entry<Int, String>? {
        val versions = getVersions(resourceId) ?: return null
        val ver = versions.first().versionNumber
        return Entry(UpdateUtils.cmpVer(localVersion, ver), "")
    }

    override fun updater(resourceId: String) {
        TODO("Not yet implemented")
    }

}