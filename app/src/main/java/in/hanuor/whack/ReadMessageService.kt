package `in`.hanuor.whack

import android.accessibilityservice.AccessibilityService
import android.accessibilityservice.AccessibilityServiceInfo
import android.app.Notification
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.accessibility.AccessibilityEvent


/*
 * Copyright (C) 2016 Hanuor Inc. by Shantanu Johri(https://hanuor.github.io/shanjohri/)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
class ReadMessageService: AccessibilityService(){
    override fun onInterrupt() {

    }


    override fun onServiceConnected() {
        Log.d("AccessibilityService", "ServiceConnected")
        try {
            val info = AccessibilityServiceInfo()

            info.eventTypes = AccessibilityEvent.TYPE_NOTIFICATION_STATE_CHANGED

            info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK

            info.notificationTimeout = 100

            serviceInfo = info
        } catch (e: Exception) {
            Log.d("ERRORonServiceConnected", e.toString())
        }

    }
    override fun onAccessibilityEvent(event: AccessibilityEvent?) {




        try {
            val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

            val data = event?.getParcelableData()

            if (data != null) {
                Log.d("Data is there", "here is the data")
                val notification = data as Notification

                val lines = notification.extras.getCharSequenceArrayList(Notification.CATEGORY_MESSAGE)
                Log.d("Make it", " " + lines)
                var i = 0
                for (msg in lines) {
                    Log.d("Line " + i, msg as String)
                    i += 1
                }

//                val remoteView = notification.bigContentView
//
//                val localView = inflater.inflate(remoteView.layoutId, null) as ViewGroup
//
//                remoteView.reapply(applicationContext, localView)
//
//                var resources: Resources? = null
//
//                val pkm = packageManager
//
//                try {
//                    resources = pkm.getResourcesForApplication("com.whatsapp")
//                } catch (e: PackageManager.NameNotFoundException) {
//                    e.printStackTrace()
//                }
//
//                if (resources == null)
//                    return
//
//                val TITLE = resources!!.getIdentifier("android:id/title", null, null)
//
//                val INBOX = resources!!.getIdentifier("android:id/big_text", null, null)
//
//                val TEXT = resources!!.getIdentifier("android:id/text", null, null)
//
//                val packagename = (event.getPackageName().toString())
//
//                var title = localView.findViewById<View>(TITLE) as TextView
//
//                var inbox = localView.findViewById<View>(INBOX) as TextView
//
//                var text = localView.findViewById<View>(TEXT) as TextView
//
//                Log.d("NOTIFICATION Package : ", packagename)
//
//                Log.d("NOTIFICATION Title : ", title.getText().toString())
//
//                Log.d("NOTIFICATION You have", text.getText().toString())
//
//                Log.d("NOTIFICATION inbox : ", inbox.getText().toString())
            }
        } catch (e: Exception) {
            Log.e("onAccessibilityEERROR", e.toString())
        }






//        val notification = event?.getParcelableData() as Notification
//        val views = notification.contentView
//        val secretClass = views::class.java
//
//        try {
//            val text = HashMap<Int, String>()
//
//            val outerFields = secretClass.getDeclaredFields()
//            for (i in outerFields.indices) {
//                if (!outerFields[i].getName().equals("mActions")) continue
//
//                outerFields[i].setAccessible(true)
//
//                val actions = outerFields[i]
//                        .get(views) as ArrayList<Any>
//                for (action in actions) {
//                    val innerFields = action.javaClass.declaredFields
//
//                    var value: Any? = null
//                    var type: Int? = null
//                    var viewId: Int? = null
//                    for (field in innerFields) {
//                        field.isAccessible = true
//                        if (field.name.equals("value")) {
//                            value = field.get(action)
//                        } else if (field.name.equals("type")) {
//                            type = field.getInt(action)
//                        } else if (field.name.equals("viewId")) {
//                            viewId = field.getInt(action)
//                        }
//                    }
//
//                    if (type == 9 || type == 10) {
//                        text.put(viewId as Int, value!!.toString())
//                    }
//                }
//Log.d("Heroooo","asdas")
//                println("title is: " + text.get(16908310))
//                println("info is: " + text[16909082])
//                println("text is: " + text[16908358])
//            }
//        } catch (e: Exception) {
//            e.printStackTrace()
//        }


    }

}