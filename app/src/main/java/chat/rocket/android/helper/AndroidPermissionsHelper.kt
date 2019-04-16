package chat.rocket.android.helper

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.view.ContextThemeWrapper
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object AndroidPermissionsHelper {

    const val WRITE_EXTERNAL_STORAGE_CODE = 1
    const val CAMERA_CODE = 2

    private fun checkPermission(context: Context, permission: String): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            permission
        ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermission(context: Activity, permission: String, requestCode: Int) {
        ActivityCompat.requestPermissions(context, arrayOf(permission), requestCode)
    }

    fun hasCameraPermission(context: Context): Boolean {
        return AndroidPermissionsHelper.checkPermission(context, Manifest.permission.CAMERA)
    }

    fun getCameraPermission(context: Context) {
        if (context is ContextThemeWrapper) {
            val activity = if (context.baseContext is Activity) context.baseContext as Activity else context as Activity
            AndroidPermissionsHelper.requestPermission(
                activity,
                Manifest.permission.CAMERA,
                AndroidPermissionsHelper.WRITE_EXTERNAL_STORAGE_CODE
            )
        }
    }

    fun hasWriteExternalStoragePermission(context: Context): Boolean {
        return checkPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE)
    }

    fun getWriteExternalStoragePermission(context: Context) {
        if (context is ContextThemeWrapper) {
            val activity = if (context.baseContext is Activity) context.baseContext as Activity else context as Activity
            AndroidPermissionsHelper.requestPermission(
                activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                WRITE_EXTERNAL_STORAGE_CODE
            )
        }
    }
}