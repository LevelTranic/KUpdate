package one.tranic.kupdate.hangar

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import okhttp3.Request
import one.tranic.kupdate.Entry
import one.tranic.kupdate.UpdateUtils
import one.tranic.kupdate.Updater

class HangarUpdater(private val resourceId: String, private val localVersion: String) : Updater {
    private val baseUrl = "https://hangar.papermc.io/api/v1"
    private val client = OkHttpClient()

    override fun getUpdate(): Entry<Int, String> {
        val request = Request.Builder()
            .url("$baseUrl/projects/$resourceId/versions")
            .addHeader(
                "User-Agent",
                "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/129.0.0.0 Safari/537.36"
            )
            .addHeader("Accept", "application/json")
            .build()

        val resp = client.newCall(request).execute().use {
            if (!it.isSuccessful) return@use it.body!!.string()
            Json.decodeFromString<CombinedResponse>(it.body!!.string())
        }

        return if (resp is String) {
            Entry(-9999, resp)
        } else {
            Entry(UpdateUtils.cmpVer(localVersion, (resp as CombinedResponse).result.first().name), "")
        }
    }
}