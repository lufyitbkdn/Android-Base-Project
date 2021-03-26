package com.shapee.sample.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.os.Environment
import android.view.View
import android.widget.Toast
import com.shapee.sample.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

object ViewUtils {
    private const val COMPRESSING_QUALITY = 100

    fun saveViewAsImage(context: Context, view: View): File? {
        val imageFileDirectory =
            File(
                context.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString(),
                context.getString(
                    R.string.app_name
                )
            )
        imageFileDirectory.mkdirs()

        val imageFile = File(imageFileDirectory, "${System.currentTimeMillis()}.jpeg")
        val imageBitMap = getBitmapFromView(view)

        try {
            val oStream = FileOutputStream(imageFile)
            imageBitMap.compress(
                Bitmap.CompressFormat.PNG,
                COMPRESSING_QUALITY, oStream
            )
            oStream.apply {
                flush()
                close()
            }
        } catch (e: IOException) {
            Toast.makeText(
                context,
                context.getString(R.string.saving_image_fail_msg),
                Toast.LENGTH_LONG
            ).show()
        }
        return imageFile
    }

    private fun getBitmapFromView(view: View): Bitmap {
        val imageBitMap = Bitmap.createBitmap(view.width, view.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(imageBitMap)
        val bgDrawable = view.background
        when (bgDrawable == null) {
            true -> canvas.drawColor(Color.WHITE)
            false -> bgDrawable.draw(canvas)
        }
        view.draw(canvas)
        return imageBitMap
    }
}