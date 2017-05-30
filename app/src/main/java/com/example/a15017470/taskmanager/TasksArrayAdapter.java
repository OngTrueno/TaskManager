package com.example.a15017470.taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by 15017470 on 30/5/2017.
 */

public class TasksArrayAdapter extends ArrayAdapter<Task>{
    Context context;
    int resource;
    ArrayList<Task> taskList;

    TextView tvId, tvName, tvDesc;

    public TasksArrayAdapter(Context context, int resource, ArrayList<Task> taskList){
        super(context, resource, taskList);
        this.context=context;
        this.taskList=taskList;
        this.resource=resource;
    }

    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(resource, parent, false);

        tvId=(TextView)rowView.findViewById(R.id.tvId);
        tvName=(TextView)rowView.findViewById(R.id.tvName);
        tvDesc=(TextView)rowView.findViewById(R.id.tvDesc);
        Task task=(Task)this.taskList.get(position);
        tvId.setText(task.getId()+" ");
        tvName.setText(task.getName());
        tvDesc.setText(task.getDesc());

        return rowView;
    }
}
