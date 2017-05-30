package com.example.a15017470.taskmanager;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    EditText etName, etDesc;
    Button btnAdd, btnCancel;
    int reqCode = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        etName = (EditText)findViewById(R.id.etName);
        etDesc = (EditText)findViewById(R.id.etDesc);
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnCancel = (Button)findViewById(R.id.btnCancel);
        ArrayList<String> al;

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String desc = etDesc.getText().toString();

                DBHelper dbh = new DBHelper(AddTaskActivity.this);
                long row_affected = dbh.insertTask(name, desc);
                dbh.close();

                if (row_affected != -1){
                    Toast.makeText(AddTaskActivity.this, "Insert successful",
                            Toast.LENGTH_SHORT).show();
                }

                Intent i = new Intent();
                setResult(RESULT_OK, i);

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.SECOND, 5);

                Intent intent = new Intent(AddTaskActivity.this,
                        ScheduledNotificationReceiver.class);


                intent.putExtra("name", name);
                intent.putExtra("desc", desc);

                PendingIntent pendingIntent = PendingIntent.getBroadcast(
                        AddTaskActivity.this, reqCode,
                        intent, PendingIntent.FLAG_CANCEL_CURRENT);

                AlarmManager am = (AlarmManager)
                        getSystemService(Activity.ALARM_SERVICE);
                am.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                        pendingIntent);

                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
