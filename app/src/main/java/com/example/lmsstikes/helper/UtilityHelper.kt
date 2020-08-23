package com.example.lmsstikes.helper

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.text.format.DateUtils
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lmsstikes.R
import com.google.android.material.snackbar.Snackbar
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Matcher
import java.util.regex.Pattern


class UtilityHelper {

    companion object {


        fun setImage(context: Context, url: String, img: ImageView) {
            if (url.isNotEmpty()) {
                Glide.with(context)
                    .load(url)
                    .placeholder(android.R.color.transparent)
                    .into(img)
            } else {
                img.setBackgroundResource(android.R.color.transparent)
            }
        }

        fun alertDialogNotCancelable(
            context: Context?,
            text: String,
            listener: DialogInterface.OnClickListener
        ) {
            context?.let {
                val alertDialog = AlertDialog.Builder(context).create()
                alertDialog.setMessage(text)
                alertDialog.setCancelable(false)
                alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "OK", listener)
                alertDialog.show()
            }
        }

        fun rpFormatter(value: Int): String {
            val localeID = Locale("in", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localeID)
            return formatRupiah.format(value.toDouble())
                .replace("Rp", "Rp ")
                .replace(",00", "")
        }

        @SuppressLint("SimpleDateFormat")
        fun getSdfDayMonthYear(date: String?): String {
            val stringToDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date)
            return if (stringToDate != null) SimpleDateFormat("dd MMMM yyyy").format(stringToDate) else ""
        }

        fun snackbarLong(view: View, text: String) {
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG)
                .setAction("OK", View.OnClickListener { })

            val viewSnackbar = snackbar.view
            val textView =
                viewSnackbar.findViewById(R.id.snackbar_text) as TextView
            textView.maxLines = 5
            textView.setPadding(0, 0, 0, 0)

            snackbar.show()
        }

        fun snackbarIndefinite(view: View, text: String) {
            val snackbar = Snackbar.make(view, text, Snackbar.LENGTH_INDEFINITE)
                .setAction("OK", View.OnClickListener { })

            val viewSnackbar = snackbar.view
            val textView =
                viewSnackbar.findViewById(R.id.snackbar_text) as TextView
            textView.maxLines = 5
            textView.setPadding(10, 0, 10, 0)

            snackbar.show()
        }


        fun checkAppInstalled(context: Context, packageName: String): Boolean {
            val packageManager = context.packageManager
            var applicationInfo: ApplicationInfo? = null
            try {
                applicationInfo = packageManager.getApplicationInfo(packageName, 0)
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            }

            return applicationInfo != null
        }

        fun hideSoftKeyboard(activity: Activity) {
            val mgr = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            mgr.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
        }

        fun isValidPassword(password: String?): Boolean {
            val pattern: Pattern
            val matcher: Matcher
            val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=\\S+$).{8,}$"
            pattern = Pattern.compile(PASSWORD_PATTERN)
            matcher = pattern.matcher(password)
            return matcher.matches()
        }
    }
}