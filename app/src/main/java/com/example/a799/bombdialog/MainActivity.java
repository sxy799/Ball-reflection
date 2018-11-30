package com.example.a799.bombdialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    public int selectedIndex = 0;
    TextView textView;
    @Override
    public  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final String items[]={"20","40","60","80"};
        builder.setTitle("请选择小球半径");
       // builder.setIcon(R.drawable.hrbust);
        builder.setSingleChoiceItems(items, 0, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                selectedIndex = which;
            }
        });
        //yes
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity.this, items[selectedIndex], Toast.LENGTH_SHORT).show();
                Intent call_intent = new Intent(MainActivity.this,InputActivity.class);
                System.out.println("selectedIndex = " + selectedIndex);

                System.out.println("items[selectedIndex] = " + items[selectedIndex]);
                call_intent.putExtra("Index",items[selectedIndex]);
                startActivity(call_intent);
            }
        });
        //no
        builder.setNegativeButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        });

        builder.show();
    }

}
