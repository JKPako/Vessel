package com.system.vessel

import android.net.Uri
import android.os.AsyncTask
import android.util.Log
import java.io.BufferedWriter
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection


class NetworkAsyncTask() : AsyncTask<Void, Void, String>() {
    override fun doInBackground(vararg params: Void?): String? {
        try {
            val url = URL("https://karitaserver.000webhostapp.com/index.php")
            val conn: HttpURLConnection = url.openConnection() as HttpURLConnection

            conn.setReadTimeout(10000)
            conn.setConnectTimeout(15000)
            conn.setRequestMethod("POST")
            conn.setDoInput(true)
            conn.setDoOutput(true)
            val builder: Uri.Builder = Uri.Builder()
                .appendQueryParameter("name", "1")
            val query: String? = builder.build().getEncodedQuery()
            val os: OutputStream = conn.getOutputStream()
            val writer = BufferedWriter(OutputStreamWriter(os, "UTF-8"))
            writer.write(query)
            writer.flush()
            writer.close()
            os.close()
            conn.connect()
            Log.println(Log.INFO,"DebugInfo","Async Task Done")
        } catch (e: Exception) {

            e.printStackTrace()

        }

        return null
    }

    override fun onPreExecute() {
        super.onPreExecute()
    }

    override fun onPostExecute(result: String?) {
        super.onPostExecute(result)
    }
}