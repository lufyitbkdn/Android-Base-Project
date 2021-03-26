package com.shapee.sample.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.graphics.Matrix
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import androidx.exifinterface.media.ExifInterface
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

/**
 * Created by Alaa Moataz on 12/24/20.
 */
object ImageUtils {

    fun getImageFromUri(context: Context, uri: Uri): File? {
        context.contentResolver?.let { contentResolver ->
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(
                    contentResolver,
                    uri
                )
            } else {
                val source = ImageDecoder.createSource(contentResolver, uri)
                ImageDecoder.decodeBitmap(source)
            }
            val fileName = "${System.currentTimeMillis()}.jpg"
            val newBitmap =
                modifyOrientation(bitmap, persistImage(bitmap, context, fileName).absolutePath)
            return newBitmap?.let { persistImage(it, context, fileName) }
        }
        return null
    }

    private fun persistImage(bitmap: Bitmap, context: Context, fileName: String): File {
        val filesDir: File = context.filesDir
        val imageFile = File(filesDir, fileName)
        val os: OutputStream
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os)
            os.flush()
            os.close()
        } catch (e: Exception) {
            Log.e(javaClass.simpleName, "Error writing bitmap", e)
        }
        return imageFile
    }

    private fun modifyOrientation(bitmap: Bitmap, imageAbsolutePath: String): Bitmap? {
        val ei = ExifInterface(imageAbsolutePath)
        val orientation: Int =
            ei.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        return when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> rotate(bitmap, 90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> rotate(bitmap, 180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> rotate(bitmap, 270f)
            ExifInterface.ORIENTATION_FLIP_HORIZONTAL -> flip(bitmap, true, false)
            ExifInterface.ORIENTATION_FLIP_VERTICAL -> flip(bitmap, false, true)
            else -> bitmap
        }
    }

    private fun rotate(bitmap: Bitmap, degrees: Float): Bitmap? {
        val matrix = Matrix()
        matrix.postRotate(degrees)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    private fun flip(bitmap: Bitmap, horizontal: Boolean, vertical: Boolean): Bitmap? {
        val matrix = Matrix()
        matrix.preScale(if (horizontal) -1.0f else 1.0f, if (vertical) -1.0f else 1.0f)
        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }
}