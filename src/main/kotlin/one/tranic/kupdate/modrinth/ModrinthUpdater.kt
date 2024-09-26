package one.tranic.kupdate.modrinth

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import one.tranic.kupdate.Entry
import one.tranic.kupdate.UpdateUtils
import one.tranic.kupdate.Updater

class ModrinthUpdater : Updater {
    private val baseUrl = "https://api.modrinth.com/v2/"
    private val client = OkHttpClient()

    private fun getVersions(slug: String): Entry<List<Version>?, String?> {
        val request = Request.Builder()
            .url("$baseUrl/project/$slug/version")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36"
            )
            .build()

        return client.newCall(request).execute().use {
            if (!it.isSuccessful) return@use Entry(null, it.body!!.string())
            Entry(Json.decodeFromString<List<Version>>(it.body!!.string()), null)
        }
    }

    override fun getUpdate(resourceId: String, localVersion: String): Entry<Int, String> {
        val vet = getVersions(resourceId)
        if (vet.a == null) return Entry(0, vet.b!!)
        return Entry(UpdateUtils.cmpVer(localVersion, vet.a.first().versionNumber), "")
    }

    override fun updater(resourceId: String) {
        TODO("Not yet implemented")
    }

}