package com.example.thurs14;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView l1;
    ArrayAdapter<String> adp;
    EditText e;
    Button b;//we are using it for strings so we use of the type string
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        l1 = findViewById(R.id.lv);//we need a string array
        e = findViewById(R.id.ed1);
        b = findViewById(R.id.b1);
        ArrayList<String> str= new ArrayList<String>();//
        str.add("India");
        str.add("Norway");
        adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,str);
        l1.setAdapter(adp);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = e.getText().toString();
                if(s.equals(""))
                {
                    Toast.makeText(MainActivity.this, "Name not entered!!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    if(str.contains(s))
                    {
                        Toast.makeText(MainActivity.this, "Already exists!!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        str.add(s);//this adds it to the string arr but not to the list we need to update the listview
                        adp.notifyDataSetChanged();
                    }
                }
            }
        });
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String ss = str.get(i);
                Toast.makeText(MainActivity.this,ss, Toast.LENGTH_SHORT).show();
            }
        });
        l1.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //int i indicates the id of the item that is selected
                AlertDialog.Builder ab = new AlertDialog.Builder(MainActivity.this);
                ab.setMessage("Do you want to delete the message?");
                ab.setTitle("ALERT");
                ab.setCancelable(false);
                //ab.setCancelable()->the pop up goes away
                ab.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i1) {
                        str.remove(i);
                        adp.notifyDataSetChanged();
                    }
                });
                ab.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });//till here the builder was created
//                now we make an object of that builder.... it makes the box we will be seeing
                AlertDialog a = ab.create();
                a.show();
                return false;//Anything is fine
            }
        });
        /*
        String[] str={"Wakanda","India","Rajnagar"};
        //array adapter is used to take the string array values into the list view
        //we need this array to populate the list view but this can be done with the use of an adapter
        adp = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1,str);
                //android.R.layout uses the inbuilt layout resoureses we can make it but it will be complicated
        l1.setAdapter(adp);//linked the adapter to the list view
         */
    }
}