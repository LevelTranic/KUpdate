package one.tranic.kupdate.modrinth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Version(
    val name: String,

    @SerialName("version_number")
    val versionNumber: String,
    val changelog: String,
    val dependencies: List<Dependency>,

    @SerialName("game_versions")
    val gameVersions: List<String>,

    @SerialName("version_type")
    val versionType: String,
    val loaders: List<String>,
    val featured: Boolean,
    val status: String,

    @SerialName("requested_status")
    val requestedStatus: String,
    val id: String,

    @SerialName("project_id")
    val projectId: String,

    @SerialName("author_id")
    val authorId: String,

    @SerialName("date_published")
    val datePublished: String,
    val downloads: Int,

    @SerialName("changelog_url")
    val changelogUrl: String?,
    val files: List<File>
)

@Serializable
data class Dependency(
    @SerialName("version_id")
    val versionId: String,

    @SerialName("project_id")
    val projectId: String,

    @SerialName("file_name")
    val fileName: String,

    @SerialName("dependency_type")
    val dependencyType: String
)

@Serializable
data class File(
    val hashes: Hashes,
    val url: String,
    val filename: String,
    val primary: Boolean,
    val size: Int,

    @SerialName("file_type")
    val fileType: String
)

@Serializable
data class Hashes(
    val sha512: String,
    val sha1: String
)
