package com.mind.log

import android.Manifest
import android.Manifest.permission.GET_PACKAGE_SIZE
import android.Manifest.permission.GET_TASKS
import android.content.ComponentName
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.SystemClock
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.permissionx.guolindev.PermissionX
import com.tan.log.Log2FileConfigImpl
import com.tan.log.LogUtils
import com.tan.log.engine.LogFileActionEngineFactory
import com.tan.log.engine.LogFileEngineFactory
import com.tan.log.engine.LogFileHttpEngineFactory
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream
import kotlin.concurrent.thread


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PermissionX.init(this)
            .permissions(
                GET_TASKS,
                GET_PACKAGE_SIZE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            .request { allGranted, grantedList, deniedList ->
                if (allGranted) {
                    allGranted()
                }
            }
        //  Z5KdlIGJpOt1
        thread {
            val name = Thread.currentThread().name
            while (true) {
                SystemClock.sleep(20000L)
                LogUtils.a("${name}:=============我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志============================我是操作打印日志===============")
//                LogUtils.h(
//                    "${name}===================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志===================================我是http日志================"
//                )
//                LogUtils.e("${name}=============我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志============================我是打印错误日志===============")
            }
        }


    }

    private fun allGranted() {
//        val fileDir = this.getExternalFilesDir(null)
//        if (fileDir?.exists() == false) {
//            fileDir.mkdirs()
//        }


//        val sdCard = Environment.getExternalStorageDirectory()
//        val directoryName = "YqLog" // 你想要创建的目录名称
//        val fileDir = File(sdCard, directoryName)
//        if (!fileDir.exists()) {
//            fileDir.mkdirs()
//        }
        val btn = findViewById<Button>(R.id.startBtn)

        btn.postDelayed({
            btn.performClick()
        }, 3000)

        btn.setOnClickListener {
            //zipFile()
//            if (isAppBRunning(this)) {
//                intent.component = ComponentName("com.yq.creation", "com.yq.creation.MainActivity")
//                intent.action = "com.qy.creation.ACTION_START_APPB"
//                intent.data = Uri.parse("http://yq.com/data")
//                intent.putExtra("msg", "hello")
//                startActivity(intent)
//            } else {
//                Toast.makeText(this, "已经在运行了", Toast.LENGTH_LONG).show()
//            }


        }


        val storageDir =
            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

        val folderName = "YqLog"
        val fileDir = File(storageDir, folderName)
        if (!fileDir.exists()) {
            fileDir.mkdirs()
        }

        val path = fileDir.absolutePath
        // 支持输入日志到文件
        LogUtils.getLog2FileConfig()
            .configLog2FileEnable(true)
            .configLog2FilePath("$path/mqtt/log/action")
            .configLog2HttpFilePath("$path/mqtt/log/http")
            .configLogFileEngine(LogFileEngineFactory(this))
            .configHttpLogFileEngine(LogFileHttpEngineFactory(this))
            .configSplitFile(this@MainActivity,1L)
            .configDaysOfExpire(7) // 设置过期天数
            .flushAsync()

    }


    override fun onDestroy() {
        super.onDestroy()
        LogUtils.getLog2FileConfig().release()
        Log2FileConfigImpl.getInstance().cancelSplitFile(this)
    }


    private fun isAppBRunning(context: Context): Boolean {
        val packageName = "com.yq.creation"
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        return intent != null
    }

    fun isAppBInstalled(context: Context, packageName: String?): Boolean {
        return try {
            context.packageManager.getPackageInfo(packageName!!, 0)
            true // 应用程序 B 已安装
        } catch (e: PackageManager.NameNotFoundException) {
            false // 应用程序 B 未安装
        }
    }


    fun zipFile() {
        thread {


            val storageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

            val folderName = "YqLog"
            val fileDir = File(storageDir, folderName)
            if (!fileDir.exists()) {
                fileDir.mkdirs()
            }
            val path = fileDir.absolutePath
            val inputFolderPath = "$path/mqtt/log/http"
            // 输出 zip 文件路径
            val outputZipPath = "$path/logs.zip"

            // 文件列表
            val filesToZip = listOf("20231027.txt", "20231127.txt")


            var fos: FileOutputStream? = null
            var zipOut: ZipOutputStream? = null
            try {
                val outputFile = File(outputZipPath)
                if (outputFile.exists()) {
                    outputFile.delete()
                }
                SystemClock.sleep(200)
                Log.e(TAG, "文件是否存在:" + outputFile.exists())


                // 创建输出流
                fos = FileOutputStream(outputZipPath)
                zipOut = ZipOutputStream(BufferedOutputStream(fos))

                for (file in filesToZip) {
                    // 获取文件路径
                    val fileLog = File(inputFolderPath, file)
                    if (!fileLog.exists()) {
                        continue
                    }
                    // 添加文件到 zip 文件
                    addToZip(fileLog.path, file, zipOut)
                }

                Log.e(TAG, "======文件压缩完成====")

            } catch (e: Exception) {
                e.printStackTrace()
                Log.e(TAG, "======文件压缩====:" + e.message)
            } finally {
                // 关闭流
                zipOut?.close()
                fos?.close()
            }

        }
    }


    private fun addToZip(filePath: String, entryName: String, zipOut: ZipOutputStream) {
        val fis = FileInputStream(filePath)
        val bis = BufferedInputStream(fis)

        try {
            // 添加 ZipEntry
            zipOut.putNextEntry(ZipEntry(entryName))

            // 写入数据
            val buffer = ByteArray(1024)
            var bytesRead: Int
            while (bis.read(buffer).also { bytesRead = it } != -1) {
                zipOut.write(buffer, 0, bytesRead)
            }

        } finally {
            // 关闭流
            bis.close()
            fis.close()
        }
    }


}