package hu.dpal.phonegap.plugins;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.MotionEvent;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;

public class CallbackProgressDialog extends ProgressDialog {

	public static CallbackContext callbackContext;

	public CallbackProgressDialog(Context context) {
		super(context);
	}

	public static CallbackProgressDialog show(Context context,
			CharSequence title, CharSequence message, boolean indeterminate,
			boolean cancelable, OnCancelListener cancelListener,
			CallbackContext callbackContext) {
		CallbackProgressDialog.callbackContext = callbackContext;
		CallbackProgressDialog dialog = new CallbackProgressDialog(context);
		dialog.setTitle(title);
		dialog.setMessage(message);
		dialog.setIndeterminate(indeterminate);
		dialog.setCancelable(cancelable);
		dialog.setOnCancelListener(cancelListener);
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.rgb(21, 52, 67)));  // color code is #153443
		dialog.show();
		return dialog;
	}

	private void sendCallback() {
		PluginResult pluginResult = new PluginResult(PluginResult.Status.OK);
		pluginResult.setKeepCallback(true);
		callbackContext.sendPluginResult(pluginResult);
	}

	@Override
	public void onBackPressed() {
		sendCallback();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			sendCallback();
			return true;
		}
		return false;
	}

}
