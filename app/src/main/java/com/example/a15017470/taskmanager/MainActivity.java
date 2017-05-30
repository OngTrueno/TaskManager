package com.example.a15017470.taskmanager;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    ArrayList<Task> tasks;
    int requestCode = 1;
    TasksArrayAdapter aa = null;
    Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(getTitle().toString());

        btnAdd = (Button)findViewById(R.id.btnAdd);
        lv = (ListView)findViewById(R.id.lv);
        DBHelper dbh = new DBHelper(this);
        this.tasks=dbh.getAllTasks();
        aa = new TasksArrayAdapter(this, R.layout.row, tasks);
        lv.setAdapter(aa);
        dbh.close();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(i);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == this.requestCode && resultCode == -1) {
            DBHelper dbh = new DBHelper(this);
            this.tasks.clear();
            this.tasks.addAll(dbh.getAllTasks());
            dbh.close();
            this.aa.notifyDataSetChanged();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
