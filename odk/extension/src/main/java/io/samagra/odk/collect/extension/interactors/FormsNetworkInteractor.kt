package io.samagra.odk.collect.extension.interactors

import io.samagra.odk.collect.extension.listeners.FileDownloadListener
import org.odk.collect.android.formmanagement.ServerFormDetails
import org.odk.collect.android.listeners.DownloadFormsTaskListener
import org.odk.collect.android.listeners.FormListDownloaderListener

/** Main interface to carry out network interactions. Clubs together different Network based
 * modules into a single unit with some additional functionality.
 * Depends on [NetworkStorageInteractor] and [NetworkFormsInteractor].
 * Generally, all the network related tasks should be carried out through this interface
 * instead of individually using [NetworkStorageInteractor] or [NetworkFormsInteractor].
 */
interface FormsNetworkInteractor {

    /** Checks if there are new forms available for download from the server. */
    fun getNewForms(listener: FormListDownloaderListener)

    /** Checks if there is a new forms zip available for download.
     *  Note: This should be called inside a coroutine to avoid UI freezes. */
    fun hasZipChanged(zipPath: String?): Boolean

    /** Automatically checks which forms are needed and downloads them.
     *  First checks if there is a zip update and then checks individual forms. */
    fun downloadRequiredForms(listener: FileDownloadListener)

    /** Takes a list of forms as input and downloads them only if necessary. */
    fun downloadFormsFromList(formsList: ArrayList<ServerFormDetails>, listener: DownloadFormsTaskListener)

    /** Takes a list of form ids and downloads them only if necessary. */
    fun downloadFormsFromIdList(formIds: ArrayList<String>, listener: DownloadFormsTaskListener)

    /** Downloads forms from the server, given a list of forms details. */
    fun downloadFormsList(listener: FormListDownloaderListener)

    /** Downloads the latest version of a form present on server based on formId. */
    fun downloadFormById(formId: String, listener: FileDownloadListener)

    /** Takes a ServerFormDetails object as input and downloads the corresponding
     *  form only if necessary. */
    fun downloadFormWithServerDetail(form: ServerFormDetails, listener: DownloadFormsTaskListener)
}