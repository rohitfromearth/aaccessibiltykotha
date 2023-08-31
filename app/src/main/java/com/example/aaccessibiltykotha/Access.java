package com.example.aaccessibiltykotha;

import static android.content.ContentValues.TAG;

import android.accessibilityservice.AccessibilityService;
import android.accessibilityservice.AccessibilityServiceInfo;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import androidx.annotation.RequiresApi;
import androidx.core.accessibilityservice.AccessibilityServiceInfoCompat;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

public class Access extends AccessibilityService  {
    String Tag ="flowdata";
    private static final String NOTIFICATION_CHANNEL_ID = "MyChannelId";
    private static final int NOTIFICATION_ID = 12345;

    @Override
    public void onCreate() {
        super.onCreate();

        // Create the notification channel
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
                    "My Notification Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
        }
        NotificationManager notificationManager = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            notificationManager = getSystemService(NotificationManager.class);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }

        // Create the notification and set it to be ongoing
        Notification notification = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setContentTitle("My App")
                .setContentText("Running in the background")
                .setSmallIcon(R.drawable.ic_stat_accessibilty)
                .setOngoing(true)
                .build();

        // Show the notification
        startForeground(NOTIFICATION_ID, notification);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onAccessibilityEvent(AccessibilityEvent event) {


        try {
    Log.e(Tag, "genrete");
    final Calendar c = Calendar.getInstance();
    String str_ty = c.get(Calendar.YEAR) + "-" + String.valueOf(c.get(Calendar.MONTH) + 1) + "-" + c.get(Calendar.DATE) + ":" + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + ":" + c.get(Calendar.MILLISECOND);
    AccessibilityNodeInfo source = event.getSource();
    if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
        String Text = String.valueOf(event.getText());
        String Content_Desc = String.valueOf(event.getContentDescription());
        String Pack_name = String.valueOf(event.getPackageName());
        String Event_type = String.valueOf(event.getEventType());
        String Data_str = "~NewEvent:event_info^" + Pack_name + "*" + Event_type + "^data^" + "^text:" + Text + "^text:" + Content_Desc + "^event_time^" + str_ty;
        Log.e(Tag,"new_string_click:"+ Data_str);
    }
    if (source != null) {
        AccessibilityNodeInfo rowNode = AccessibilityNodeInfo.obtain(source);
        if (rowNode != null) {
            ///////////new change
            String Pack_name = String.valueOf(rowNode.getPackageName());
            String Event_type = String.valueOf(event.getEventType());
            String event_str = "~NewEvent:event_info^" + Pack_name + "*" + Event_type + "^data^";
            Log.e(Tag,"new_string:"+ event_str);
//            AccessibilityNodeInfo root = getRootInActiveWindow(); // Get the root AccessibilityNodeInfo of the active window
//                String targetViewId = "com.balaji.alt:id/aston_band_img_p"; // Replace with your specific view ID
//                List<AccessibilityNodeInfo> list = root.findAccessibilityNodeInfosByViewId(targetViewId);
//                if(list != null && !list.isEmpty()) {
//                    AccessibilityNodeInfo rowNode1 = list.get(0);
//                }


            ////optional new change
                    String Text = null;
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                        Text = String.valueOf(rowNode.getUniqueId());
                    }
                    String Content_Desc = String.valueOf(rowNode.getContentDescription());
            String ID = String.valueOf(rowNode.getViewIdResourceName());
            String text = "";

            if (!Text.equals("null") && !Text.equals("")) {
                text += "^text:" + Text+ "---ID:" + ID;
                Log.e(Tag, "new_string:"+text);
            }
            if (!Content_Desc.equals("null") && !Content_Desc.equals("")) {
                text += "^text1:" + Content_Desc+ "---ID:" + ID;
                Log.e(Tag, "new_string:"+text);
            }
            if (!text.equals("")) {
                Log.e(Tag, "new_string:"+text);
//                    fw.writeFile(text, s2,c, dir);
            }

            ////optional new change end

            /////new change end
//              Log.e("better version pp ",  "~NewEvent:" + "event_info^" +rowNode.getPackageName()+ "*" + rowNode.getClassName()+"*" +event.getEventType()+"^text^" +rowNode.getText()+ "^description^" +rowNode.getContentDescription());
            int count = rowNode.getChildCount();
            for (int i = 0; i < count; i++) {
                AccessibilityNodeInfo completeNode = rowNode.getChild(i);

                recur(completeNode, c, 1, event);
            }

////new change
            String event_end = "^event_time^" + str_ty;
            Log.e(Tag,"new_string_end:"+ event_end);
            ////new change end
        }
    }

} catch (Exception e) {
Log.e(Tag,"error:"+ e.toString());
    e.printStackTrace();
}
    }
    private void recur(AccessibilityNodeInfo completeNode,Calendar c, int step,AccessibilityEvent event) {

        if(completeNode!= null){
            int cout =  completeNode.getChildCount();
        try {
            if (cout == 0) {
//                AccessibilityNodeInfo root = getRootInActiveWindow(); // Get the root AccessibilityNodeInfo of the active window
//
//                String targetViewId = "com.balaji.alt:id/aston_band_img_p"; // Replace with your specific view ID
//
//                List<AccessibilityNodeInfo> list = root.findAccessibilityNodeInfosByViewId(targetViewId);
//                if(list != null && !list.isEmpty()) {
//
//                    AccessibilityNodeInfo datasr = list.get(0);
//                }

                String Text = String.valueOf(completeNode.getText());
                    String Content_Desc = String.valueOf(completeNode.getContentDescription());
////new change
                    String ID = String.valueOf(completeNode.getViewIdResourceName());

                    String text = "";
                    if (!Text.equals("null") && !Text.equals("")) {
                        text += "^text:" + Text + "---ID:" + ID;
                        Log.e(Tag, "new_string:"+text);
                    }
                    if (!Content_Desc.equals("null") && !Content_Desc.equals("")) {
                        text += "^text:" + Content_Desc + "---ID:" + ID;
                        Log.e(Tag, "new_string:"+text);
                    }

                    if (!text.equals("")) {
                        Log.e(Tag, "new_string:" + text);
//                    fw.writeFile(text, s2,c, dir);
                    }


                /////new change end
////old working
//                if((Text!="null")||(Content_Desc!="null")) {
//                    String Pack_name = String.valueOf(completeNode.getPackageName());
//
//                    String Class_name = String.valueOf(completeNode.getClassName());
//
//                    String Event_type = String.valueOf(event.getEventType());
//
//                    String Data_str = "~NewEvent:" + "event_info^" + Pack_name + "*" + Class_name + "*" + Event_type + "^text^" + Text + "^description^" + Content_Desc + "^event_time^"+completeNode.getViewIdResourceName();
//                    Log.e("SINGLE ELEMENT ", Data_str);
//                    Log.e("better version ",  "~NewEvent:" + "event_info^" +completeNode.getPackageName()+ "*" + completeNode.getClassName()+"*" +event.getEventType()+"^text^" +completeNode.getText()+ "^description^" +completeNode.getContentDescription());
//                    fw.writeFile(Data_str, c, dir);
//                    Data_str = "";
//
//                }
                ///////old working end
                        }
             else {


                for(int i=0; i<cout ; i++){
                    AccessibilityNodeInfo completeNod = completeNode.getChild(i);
                  recur(completeNod,c, step,event);
                }
            }

        }catch(Exception e)
        {
            Log.e(Tag,"Exeption:"+e.getMessage());

        }}

    }


    @Override
    public void onInterrupt() {
        Log.e(Tag, "onInterrupt: Someting went wrong" );
    }


    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();

        AccessibilityServiceInfo info = new AccessibilityServiceInfo();
        info.eventTypes = AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED | AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED;

        info.feedbackType = AccessibilityServiceInfo.FEEDBACK_ALL_MASK;
        info.notificationTimeout = 1000;

        info.flags = AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS | AccessibilityServiceInfo.DEFAULT| AccessibilityServiceInfo.FLAG_REPORT_VIEW_IDS;
        this.setServiceInfo(info);

        Log.e(TAG, "onServiceConnected: ");


    }

}