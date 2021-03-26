package com.shapee.sample.utils.extensions

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import androidx.core.content.FileProvider
import java.io.File
import java.io.IOException
import java.text.DateFormat
import java.util.*


/** ContextExt.kt
 *
 * Author(s): Alaa Moataz, Amr Elzawawy
 * Organization: Blink22
 * Date: 14/9/20
 */

internal fun Context.toSp(px: Float): Float {
    return px / resources.displayMetrics.scaledDensity
}

internal fun Context.toDp(px: Float): Int =
    (px * resources.displayMetrics.density + 0.5f).toInt()


internal fun Context.getQuantityStringZero(
    resId: Int,
    zeroResId: Int,
    quantity: Int
): String? {
    return if (quantity == 0) {
        resources.getString(zeroResId)
    } else {
        resources.getQuantityString(resId, quantity, quantity)
    }
}

internal fun Context.getQuantityStringZero(
    resId: Int,
    zeroResId: Int,
    quantity: Int,
    vararg formatArgs: Any?
): String? {
    return if (quantity == 0) {
        resources.getString(zeroResId)
    } else {
        resources.getQuantityString(resId, quantity, formatArgs)
    }
}

internal fun Context.getPhotoPickerIntent(): Intent =
    Intent(Intent.ACTION_OPEN_DOCUMENT).apply { type = "image/*" }

internal fun Context.getCameraIntentWithSavingFile(): Intent =
    Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
        // Ensure that there's a camera activity to handle the intent
        takePictureIntent.resolveActivity(packageManager)?.also {
            // Create the File where the photo should go
            val photoFile: File? = try {
                createFileWithCollisionResistantName()
            } catch (ex: IOException) {
                // Error occurred while creating the File
                null
            }
            // Continue only if the File was successfully created
            photoFile?.also {
                val photoUri = FileProvider.getUriForFile(
                    this,
                    "com.app.counterbook.fileprovider",
                    it
                )
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri)
                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    val resInfoList = packageManager.queryIntentActivities(
                        takePictureIntent,
                        PackageManager.MATCH_DEFAULT_ONLY
                    )

                    for (resolveInfo in resInfoList) {
                        val packageName = resolveInfo.activityInfo.packageName
                        grantUriPermission(
                            packageName, photoUri,
                            Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                        )
                    }
                }
            }
        }
    }

@Throws(IOException::class)
internal fun Context.createFileWithCollisionResistantName(): File {
    // Create an image file name, must remove the `:` and `?` characters whenever exists for APIs lower than 28 support.
    val timeStamp: String = DateFormat.getDateTimeInstance().format(Date())
        .replace(":", "")
        .replace("?", "")
    val storageDir: File? = this.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
    return File.createTempFile(
        "JPEG_${timeStamp}_", /* prefix */
        ".jpg", /* suffix */
        storageDir /* directory */
    )
}