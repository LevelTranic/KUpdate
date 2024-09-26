package one.tranic.kupdate

data class Entry<T, K>(val a: T, val b: K)

object UpdateUtils {
    private fun splitVersion(version: String): List<String> {
        val mainParts = version.split("-").first().split("+").first().split(".")
        val suffix = version.split("-").drop(1).joinToString("-")
        return mainParts + listOf(suffix)
    }

    fun cmpVer(vLocal: String, vRemote: String): Int {
        val vLocSeg = splitVersion(vLocal)
        val vRemSeg = splitVersion(vRemote)

        for (i in 0 until maxOf(vLocSeg.size, vRemSeg.size)) {
            val localPart = vLocSeg.getOrElse(i) { "0" }
            val remotePart = vRemSeg.getOrElse(i) { "0" }

            val cmpResult = when {
                localPart.matches(Regex("\\d+")) && remotePart.matches(Regex("\\d+")) -> localPart.toInt().compareTo(remotePart.toInt())
                localPart.matches(Regex("\\d+")) -> 1
                remotePart.matches(Regex("\\d+")) -> -1
                else -> localPart.compareTo(remotePart, ignoreCase = true)
            }

            if (cmpResult != 0) return cmpResult
        }

        return 0
    }

}