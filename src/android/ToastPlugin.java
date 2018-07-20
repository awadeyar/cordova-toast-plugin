package com.amwadeyar.cordova.plugin.ToastPlugin;

import android.widget.Toast;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

public class ToastPlugin extends CordovaPlugin {
    private static final String DURATION_LONG = "long";

    public boolean execute(String action, JSONArray args,
                           final CallbackContext callbackContext) {
        // Verify that the user sent a 'show' action
        if (!action.equals("show")) {
            callbackContext.error("\"" + action + "\" is not a recognized action.");
            return false;
        }

        String message;
        String duration;
        try{
            JSONObject options = args.getJSONObject(0);
            message = options.getString("message");
            duration = options.getString("duration");


        }catch (Exception e){
            callbackContext.error("Error encountered: " + e.getMessage());
            return false;
        }

        Toast.makeText(cordova.getActivity(),message,DURATION_LONG.equals(duration)? Toast.LENGTH_LONG:Toast.LENGTH_SHORT).show();
        PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
        callbackContext.sendPluginResult(pluginResult);
        return true;
    }
}
