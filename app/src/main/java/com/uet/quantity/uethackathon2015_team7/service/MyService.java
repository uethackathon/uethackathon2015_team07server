package com.uet.quantity.uethackathon2015_team7.service;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.util.Log;

import com.uet.quantity.uethackathon2015_team7.receiver.LockScreenReceiver;

public class MyService extends Service {
	BroadcastReceiver mReceiver;

	public MyService() {
	}

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		Log.d("service start", "service start");
		((KeyguardManager) getSystemService("keyguard")).newKeyguardLock("IN")
				.disableKeyguard();
		IntentFilter intentfilter = new IntentFilter(
				"android.intent.action.SCREEN_ON");
		intentfilter.addAction("android.intent.action.SCREEN_OFF");
		mReceiver = new LockScreenReceiver();
		registerReceiver(mReceiver, intentfilter);
		super.onCreate();
	}

	public void onDestroy() {
		Log.d("service stop", "service stop");
		unregisterReceiver(mReceiver);
		super.onDestroy();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// TODO Auto-generated method stub
		return START_STICKY;
	}
}
