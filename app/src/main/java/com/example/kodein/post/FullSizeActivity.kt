package com.example.kodein.post


import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.kodein.R
import kotlinx.android.synthetic.main.activity_full_size.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class FullSizeActivity : AppCompatActivity() {
    private val PERMISSION_REQUEST_CODE : Int = 1000
    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_size)
        this.hideSystemUI()
        val intent  = intent
        val link :String = intent.getStringExtra("link")
        Glide.with(this).load(link).into(imgAss)
        imgAss.setOnClickListener {
          this.hideSystemUI()
        }
        imgAss.setOnLongClickListener {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.task_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
            //show dialog
            val  mAlertDialog = mBuilder.show()
          //  val tvBack : TextView? = mAlertDialog.findViewById(R.id.tvBack)
            val tvDownload : TextView? = mAlertDialog.findViewById(R.id.tvDownload)
//            tvBack?.setOnClickListener {
//                intent = Intent(this, PostListActivity::class.java)
//                this.startActivity(intent)
//            }
            tvDownload?.setOnClickListener {
                startDownload(link)
            }
            true
        }


    }
    private fun startDownload(url: String){
        Toast.makeText(this,"Downloading..", Toast.LENGTH_SHORT).show()

        val request : DownloadManager.Request = DownloadManager.Request(Uri.parse(url))
        //Allow type of network to download files
        request.setAllowedNetworkTypes(
            DownloadManager.Request.NETWORK_WIFI or
                    DownloadManager.Request.NETWORK_MOBILE)
            .setTitle("Download")
            .setDescription("GalleryApp")
            .setMimeType("image/jpg")
//            .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, "FILE_NAME")

            .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        val downloadManager : DownloadManager = getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)

    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) Toast.makeText(
                    this,
                    "Permission Granted",
                    Toast.LENGTH_SHORT
                ).show() else Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideSystemUI() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }
}
