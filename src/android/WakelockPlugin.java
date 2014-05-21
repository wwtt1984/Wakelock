package com.wakelock.wakelock;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import android.util.Log;
import android.content.Context;
import android.os.PowerManager;
import android.widget.Toast;

public class WakelockPlugin extends CordovaPlugin {

    private PowerManager.WakeLock mWakelock = null;

    public boolean execute(String action, JSONArray data,
            CallbackContext callbackContext) throws JSONException {
        if (action.equals("acquireWakeLock")) {
             acquireWakeLock(data.getString(0));
        }
        else if (action.equals("releaseWakeLock")) {
            releaseWakeLock(data.getString(0));
        }
        return false;
    }
    public synchronized void acquireWakeLock(final String str) {

           final CordovaInterface cordova = this.cordova;
           Runnable runnable = new Runnable() {
               public void run() {

   				if (mWakelock == null) {

   				    PowerManager pm = (PowerManager)cordova.getActivity().getSystemService(Context.POWER_SERVICE);
   					mWakelock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK|PowerManager.ON_AFTER_RELEASE, aWakeLock);
   			   		mWakelock.acquire();
   				}
                }
           };

   		this.cordova.getActivity().runOnUiThread(runnable);
    }

   	public synchronized void releaseWakeLock(final String str) {
           final CordovaInterface cordova = this.cordova;
           Runnable runnable = new Runnable() {
               public void run() {

   				if (mWakelock != null && mWakelock.isHeld()) {
   					mWakelock.release();
   					mWakelock = null;
   				}

                }
           };
           this.cordova.getActivity().runOnUiThread(runnable);
    }
}