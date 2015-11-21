package com.uet.quantity.uethackathon2015_team7.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.uet.quantity.uethackathon2015_team7.LockScreenActivity;

public class LockScreenReceiver extends BroadcastReceiver {

	Context context;

	@Override
	public void onReceive(Context context, Intent intent) {

		this.context = context;
		if (intent.getAction().equals("android.intent.action.SCREEN_OFF")) {

			startLock();
			return;
		}
	}
	
	public void startLock(){
		Intent intent1 = new Intent(context, LockScreenActivity.class);
		intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent1);
	}

}
