package `in`.hanuor.whack

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity




class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//
//        val c = contentResolver.query(ContactsContract.Data.CONTENT_URI,
//                arrayOf(ContactsContract.Contacts.Data._ID), ContactsContract.Data.DATA1 + "=?",
//                null, null)
//        c!!.moveToFirst()
//
//        Log.d("Yo man", c.getString(0))
        //val i = Intent(Intent.ACTION_VIEW, Uri.parse("content://com.android.contacts/data/" + c.getString(0)))
//
//        startActivity(i)
//        c.close()

        openWhatsApp()
    }

    private fun openWhatsApp() {
        val smsNumber = "919910830309" // E164 format without '+' sign
        val sendIntent = Intent(Intent.ACTION_SEND  )
        sendIntent.type = "text/plain"
        sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.")
        sendIntent.putExtra("jid", smsNumber + "@s.whatsapp.net") //phone number without "+" prefix
        sendIntent.`package` = "com.whatsapp"
        if (intent.resolveActivity(this.getPackageManager()) == null) {
            //Toast.makeText(this, "Error/n" + e.toString(), Toast.LENGTH_SHORT).show()
            return
        }
        startActivity(sendIntent)
    }
}
