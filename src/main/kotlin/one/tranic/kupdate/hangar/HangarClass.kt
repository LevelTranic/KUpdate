package one.tranic.kupdate.hangar

import kotlinx.serialization.Serializable

enum class Platform {
    Velocity, Paper, Waterfall;

    override fun toString(): String {
        return when (this) {
            Velocity -> "VELOCITY"
            Waterfall -> "WATERFALL"
            Paper -> "PAPER"
        }
    }

    fun of(data: String): Platform? {
        return when (data) {
            "VELOCITY" -> Velocity
            "WATERFALL" -> Waterfall
            "PAPER" -> Paper
            else -> null
        }
    }
}

@Serializable
data class Pagination(
    val limit: Int,
    val offset: Int,
    val count: Int
)

@Serializable
data class Stats(
    val totalDownloads: Int,
    val platformDownloads: Map<String, Int>
)

@Serializable
data class Channel(
    val createdAt: String,
    val name: String,
    val description: String,
    val color: String,
    val flags: List<String>
)

@Serializable
data class FileInfo(
    val name: String,
    val sizeBytes: Int,
    val sha256Hash: String
)

@Serializable
data class DownloadInfo(
    val fileInfo: FileInfo,
    val externalUrl: String?,
    val downloadUrl: String
)

@Serializable
data class PluginDependency(
    val name: String,
    val required: Boolean,
    val externalUrl: String?,
    val platform: String
)

@Serializable
data class VersionResult(
    val createdAt: String,
    val name: String,
    val visibility: String,
    val description: String,
    val stats: Stats,
    val author: String,
    val reviewState: String,
    val channel: Channel,
    val pinnedStatus: String,
    val downloads: Map<String, DownloadInfo>,
    val pluginDependencies: Map<String, List<PluginDependency>>,
    val platformDependencies: Map<String, List<String>>,
    val platformDependenciesFormatted: Map<String, List<String>>
)

@Serializable
data class CombinedResponse(
    val pagination: Pagination,
    val result: List<VersionResult>
)
