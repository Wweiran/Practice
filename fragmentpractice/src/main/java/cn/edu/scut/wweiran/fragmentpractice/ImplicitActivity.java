package cn.edu.scut.wweiran.fragmentpractice;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.provider.ContactsContract.CommonDataKinds;
import static android.provider.ContactsContract.Contacts;
import static android.provider.ContactsContract.Intents;

public class ImplicitActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int REQUEST_SELECT_CONTACT = 5;
    private static final int REQUEST_SPECIAL_CONTACT = 6;
    private static final int REQUEST_DETAILS_CONTACT = 7;
    private Button mMapButton;
    private Button mAlarmButton, mTimerButton, mShowAlarmButton, mInsertCalendarButton, mPickContactButton;
    private Button mSpecialButton, mDetailsContactButton,mEditContactButton;

    private Uri contactUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit);

        mMapButton = (Button) findViewById(R.id.btn_map);
        mMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(mapIntent, 0);
                if (activities.size() > 0) {
                    startActivity(mapIntent);
                }*/
//                Intent intent = new Intent(Intent.ACTION_SEND);
//                Intent chooser = Intent.createChooser(intent, "tel:2222");
//                if (intent.resolveActivity(getPackageManager()) != null) {
//                    startActivity(chooser);
//                }
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http:www.baidu.com"));
//                intent.putExtra(Intent.EXTRA_TEXT, "共享内容");
//                intent.setType("text/plain");
                startActivity(intent);
            }
        });

        mAlarmButton = (Button) findViewById(R.id.btn_alarm);
        mAlarmButton.setOnClickListener(this);

        mTimerButton = (Button) findViewById(R.id.btn_timer);
        mTimerButton.setOnClickListener(this);

        mShowAlarmButton = (Button) findViewById(R.id.btn_show_alarm);
        mShowAlarmButton.setOnClickListener(this);

        mInsertCalendarButton = (Button) findViewById(R.id.btn_add_calendar);
        mInsertCalendarButton.setOnClickListener(this);

        mPickContactButton = (Button) findViewById(R.id.btn_pick_contact);
        mPickContactButton.setOnClickListener(this);

        mSpecialButton = (Button) findViewById(R.id.btn_pick_special_contact);
        mSpecialButton.setOnClickListener(this);

        mDetailsContactButton = (Button) findViewById(R.id.btn_contact_details);
        mDetailsContactButton.setOnClickListener(this);

        mEditContactButton = (Button) findViewById(R.id.btn_edit_contact);
        mEditContactButton.setOnClickListener(this);
    }

    @Override
    public void onClick(@NonNull View v) {
        switch (v.getId()) {
            case R.id.btn_alarm:
                Intent alarmIntent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "测试闹钟")
                        .putExtra(AlarmClock.EXTRA_HOUR, 18)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 0);
                if (alarmIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(alarmIntent);
                }
                break;
            case R.id.btn_timer:
                Intent timerIntent = new Intent(AlarmClock.ACTION_SET_TIMER)
                        .putExtra(AlarmClock.EXTRA_LENGTH, 30)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "测试计时器");
                if (timerIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(timerIntent);
                    Toast.makeText(this, "timer", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_show_alarm:
                Intent showAlarmIntent = new Intent(AlarmClock.ACTION_SHOW_ALARMS);
                if (showAlarmIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(showAlarmIntent);
                }
                break;
            case R.id.btn_add_calendar:
                Intent insertIntent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, "test_title")
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, "test_location")
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, 11)
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, 12);
                if (insertIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(insertIntent);
                }
                break;
            case R.id.btn_pick_contact:
                Intent pickContactIntent = new Intent(Intent.ACTION_PICK);
                pickContactIntent.setType(Contacts.CONTENT_TYPE);
                if (pickContactIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(pickContactIntent, REQUEST_SELECT_CONTACT);
                }
                break;
            case R.id.btn_pick_special_contact:
                Intent specialIntent = new Intent(Intent.ACTION_PICK);
                specialIntent.setType(CommonDataKinds.Phone.CONTENT_TYPE);
                if (specialIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(specialIntent, REQUEST_SPECIAL_CONTACT);
                }
                break;
            case R.id.btn_contact_details:
                //这里和上面的访问联系人的方法一样，主要是回传特定的 Uri
                /*Intent contactDetailsIntent = new Intent(Intent.ACTION_PICK);
                contactDetailsIntent.setType(ContactsContract.Contacts.CONTENT_TYPE);
                if (contactDetailsIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(contactDetailsIntent,REQUEST_DETAILS_CONTACT);
                }*/

                //查看联系人详情方式二
                if (contactUri != null) {
                    Intent contactDetailsIntent = new Intent(Intent.ACTION_VIEW, contactUri);
                    if (contactDetailsIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(contactDetailsIntent);
                    }
                } else {
                    Toast.makeText(this, "联系人Uri为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btn_edit_contact:
                if (contactUri != null) {
                    Intent editContactIntent = new Intent(Intent.ACTION_EDIT);
                    editContactIntent.setData(contactUri);
                    editContactIntent.putExtra(Intents.Insert.EMAIL, "@qq.com");
                    if (editContactIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(editContactIntent);
                    }
                } else {
                    Toast.makeText(this, "联系人Uri为空", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        switch (requestCode) {
            case REQUEST_SELECT_CONTACT:
                if (resultCode == RESULT_OK) {
                    //得到 Uri，然后可以通过 Uri 访问联系人的各项数据
                    contactUri = data.getData();
                    Toast.makeText(this, contactUri.toString(), Toast.LENGTH_SHORT).show();
                }
                break;
            case REQUEST_SPECIAL_CONTACT:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = new String[]{CommonDataKinds.Phone.NUMBER};
                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

                    if (cursor != null && cursor.moveToFirst()) {
                        int numberIndex = cursor.getColumnIndex(CommonDataKinds.Phone
                                .NUMBER);
                        String number = cursor.getString(numberIndex);
                        Toast.makeText(this, "电话号码是：" + number, Toast.LENGTH_SHORT).show();
                    }
                }
            case REQUEST_DETAILS_CONTACT:
                //利用前面访问联系人得到的 Uri 然后去访问联系人详情页
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            default:
                break;

        }
    }
}
