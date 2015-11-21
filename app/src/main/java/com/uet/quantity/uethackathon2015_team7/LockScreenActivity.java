package com.uet.quantity.uethackathon2015_team7;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.uet.quantity.uethackathon2015_team7.database.DatabaseHandler;
import com.uet.quantity.uethackathon2015_team7.model.HistoryItem;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class LockScreenActivity extends Activity {

    private BroadcastReceiver timeReceiver = new BroadcastReceiver() {

        public void onReceive(Context context1, Intent intent) {
            if (intent.getAction().equals("android.intent.action.TIME_TICK")) {
                checkTime();
            }
        }
    };

    private TextView txvLayoutUnlockDay;
    private TextView txvLayoutUnlockTime;
    private Typeface mClock1;
    private Typeface mClock2;
    private String str1;
    private String str2;
    private String str3;
    private String str4;
    private String str5;
    private String str6;
    private String str7;
    private ImageView imgLayoutLockscreenBettery;
    private ImageView imgLayoutLockscreenNetwork;
    private TextView txvLayoutLockscreenBettery;
    private TextView txvLayoutLockscreenNetwork;
    private TextView historyNew;
    BroadcastReceiver batteryInfoReceiver;
    private Bitmap mBitmap;
    ImageView close;
    DatabaseHandler db;
    HistoryItem item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new DatabaseHandler(this);
        item = db.getHistory("02/09");



        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_lock_screen);

        txvLayoutUnlockTime = (TextView) findViewById(R.id.txv_layout_unlock_time);
        txvLayoutUnlockDay = (TextView) findViewById(R.id.txv_layout_unlock_day);
        txvLayoutLockscreenNetwork = (TextView)findViewById(R.id.txv_layout_lockscreen_network);
        txvLayoutLockscreenBettery = (TextView)findViewById(R.id.txv_layout_lockscreen_bettery);
        imgLayoutLockscreenBettery = (ImageView)findViewById(R.id.img_layout_lockscreen_bettery);
        historyNew = (TextView) findViewById(R.id.history_new);
        close = (ImageView) findViewById(R.id.close);

        mClock1 = Typeface.createFromAsset(
                this.getAssets(), "fonts/lightfont.otf");
        mClock2 = Typeface.createFromAsset(
                getAssets(),
                "fonts/HelveticaNeueLTStd-Lt-large-less-greater.otf");

        historyNew.setTypeface(mClock2);
        historyNew.setText(item.getContent());

        checkTime();
        checkBattery();

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LockScreenActivity.this.finish();
            }
        });
    }

    public void checkBattery(){
        batteryInfoReceiver = new BroadcastReceiver() {

            public void onReceive(Context context, Intent intent) {
                int i = intent.getIntExtra("level", 0);
                if (intent.getIntExtra("plugged", 0) == 0) {
                    if (i < 25) {
                        mBitmap = ((BitmapDrawable) getResources().getDrawable(
                                R.drawable.battery1)).getBitmap();
                        imgLayoutLockscreenBettery.setImageBitmap(mBitmap);
                        txvLayoutLockscreenBettery
                                .setText((new StringBuilder()).append(i)
                                        .append("%").toString());
                        return;
                    }
                    if (i > 25 && i < 50) {
                        mBitmap = ((BitmapDrawable) getResources().getDrawable(
                                R.drawable.battery2)).getBitmap();
                        imgLayoutLockscreenBettery.setImageBitmap(mBitmap);
                        txvLayoutLockscreenBettery
                                .setText((new StringBuilder()).append(i)
                                        .append("%").toString());
                        return;
                    }
                    if (i > 50 && i < 75) {
                        mBitmap = ((BitmapDrawable) getResources().getDrawable(
                                R.drawable.battery3)).getBitmap();
                        imgLayoutLockscreenBettery.setImageBitmap(mBitmap);
                        txvLayoutLockscreenBettery
                                .setText((new StringBuilder()).append(i)
                                        .append("%").toString());
                        return;
                    } else {
                        mBitmap = ((BitmapDrawable) getResources().getDrawable(
                                R.drawable.battery4)).getBitmap();
                        imgLayoutLockscreenBettery.setImageBitmap(mBitmap);
                        txvLayoutLockscreenBettery
                                .setText((new StringBuilder()).append(i)
                                        .append("%").toString());
                        return;
                    }
                } else {
                    mBitmap = ((BitmapDrawable) getResources().getDrawable(
                            R.drawable.battery_charging)).getBitmap();
                    imgLayoutLockscreenBettery.setImageBitmap(mBitmap);
                    txvLayoutLockscreenBettery.setText((new StringBuilder())
                            .append(i).append("%").toString());
                    return;
                }
            }
        };

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService("phone");
        txvLayoutLockscreenNetwork.setText(telephonyManager.getNetworkOperatorName().toString());
        txvLayoutLockscreenNetwork.setTypeface(mClock2);
        txvLayoutLockscreenBettery.setTypeface(mClock2);
        registerReceiver(batteryInfoReceiver, new IntentFilter("android.intent.action.BATTERY_CHANGED"));
        registerReceiver(timeReceiver, new IntentFilter("android.intent.action.TIME_TICK"));
    }

    public void checkTime(){

        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(8);
        int j = calendar.get(10);
        calendar.get(7);
        str1 = String.valueOf(calendar.get(5));
        str2 = String.valueOf(i);
        str3 = String.valueOf(j);
        if (str3.length() < 2) {
            (new StringBuilder("0")).append(str3).toString();
        }
        if (str2.length() < 2) {
            (new StringBuilder("0")).append(str2).toString();
        }
        Object obj = new Date();
        SimpleDateFormat simpledateformat = new SimpleDateFormat("h:mm");
        str4 = simpledateformat.format(((Date) (obj)));
        str5 = (new SimpleDateFormat("a")).format(((Date) (obj)));

        obj = new Date();
        SimpleDateFormat simpledateformat1 = new SimpleDateFormat(
                "h:mm");
        str4 = simpledateformat1.format(((Date) (obj)));
        str5 = (new SimpleDateFormat("a")).format(((Date) (obj)));

        SimpleDateFormat siDateFormat = new SimpleDateFormat("MMMMMMMMM");
        str6 = siDateFormat.format(calendar.getTime());
        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("EEEE");
        str7 = simpleDateFormat2.format(new Date());

        txvLayoutUnlockTime.setTypeface(mClock1);
        txvLayoutUnlockDay.setTypeface(mClock2);
        txvLayoutUnlockTime.setText(str4);
        boolean flag = Locale.getDefault().getLanguage()
                .equals("vi");
        txvLayoutUnlockTime.setText(str4);

        if (!flag) {
            txvLayoutUnlockDay
                    .setText((new StringBuilder())
                            .append(str7).append(", ")
                            .append(str6).append(" ")
                            .append(str1).toString());

        } else {
            txvLayoutUnlockDay
                    .setText((new StringBuilder())
                            .append(str7).append(", ")
                            .append(str1).append(" ")
                            .append(str6).toString());
        }

    }

    @Override
    public void onBackPressed() {
     //   super.onBackPressed();
    }
}
