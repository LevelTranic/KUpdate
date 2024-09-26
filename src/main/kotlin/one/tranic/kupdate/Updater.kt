package one.tranic.kupdate

/**
 * The `Updater` interface provides methods for managing updates for resources.
 * It allows you to check for updates and perform the update process for a given resource.
 */
interface Updater {

    /**
     * Retrieves the update information for a specified resource.
     *
     * @param resourceId The unique identifier of the resource to check for updates.
     * @param localVersion The current version of the resource installed locally.
     * @return An `Entry` containing the update version as an `Int` and an error message as a `String`.
     *
     *         If the local version is equal to the remote version, the first parameter will be 0,
     *         and the second parameter will typically be an empty string ("").
     *
     *         If an update is available (i.e., the remote version is higher), the first parameter
     *         will be negative, and the second parameter will typically be an empty string ("").
     *
     *         If the local version is higher than the remote version, the first parameter will be positive,
     *         and the second parameter will typically be an empty string ("").
     *
     *         If an error occurs during the check, the first parameter will be -9999, and the second
     *           parameter will contain the error message.
     */
    fun getUpdate(): Entry<Int, String>?

    /**
     * Initiates the update process for the specified resource.
     *
     * @param resourceId The unique identifier of the resource to be updated.
     */
    fun updater() {
        TODO("Not yet implemented")
    }
}
