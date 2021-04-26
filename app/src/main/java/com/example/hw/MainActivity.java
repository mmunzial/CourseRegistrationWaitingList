package com.example.hw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
 //initalize objects
    private Button add_button, show_button, clear_button;
    private EditText name, priority;
    private ListView sv_student_list;
    private String name_string;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //add objects
        add_button = (Button)findViewById(R.id.add_button);
        show_button = (Button)findViewById(R.id.show_button);
        clear_button = (Button)findViewById(R.id.clear_button);
        name = findViewById(R.id.studentname_text);
        priority = findViewById(R.id.priority_num);
        sv_student_list = (ListView)findViewById(R.id.student_list);


        //add button listener

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentModel student_list;
                student_list = new studentModel(-1,name.getText().toString(), priority.getText().toString());
                Toast.makeText(MainActivity.this, student_list.toString(),Toast.LENGTH_SHORT).show();

                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);

                boolean success = dataBaseHelper.addOne(student_list);
                //Toast.makeText(MainActivity.this, "Success= "+ success,Toast.LENGTH_SHORT).show();


            }


        });
        //show students button
        show_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                 List<studentModel> everyone = dataBaseHelper.getEveryone();

                //Toast.makeText(MainActivity.this, everyone.toString(),Toast.LENGTH_SHORT).show();

                ArrayAdapter studentArrayAdapter = new ArrayAdapter<studentModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                sv_student_list.setAdapter(studentArrayAdapter);

            }
        });
        //clears database
        clear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDatabase("student_list.db");
                Toast.makeText(MainActivity.this, "Cleared",Toast.LENGTH_SHORT).show();

            }
        });

        //deletes student from database
        sv_student_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                studentModel clickedStudent = (studentModel) parent.getItemAtPosition(position);
                DataBaseHelper dataBaseHelper = new DataBaseHelper(MainActivity.this);
                dataBaseHelper.deleteOne(clickedStudent);
                Toast.makeText(MainActivity.this, "Deleted " + clickedStudent.toString(),Toast.LENGTH_SHORT).show();

                DataBaseHelper dataBaseHelper1 = new DataBaseHelper(MainActivity.this);
                List<studentModel> everyone = dataBaseHelper1.getEveryone();

                //Toast.makeText(MainActivity.this, everyone.toString(),Toast.LENGTH_SHORT).show();

                ArrayAdapter studentArrayAdapter = new ArrayAdapter<studentModel>(MainActivity.this, android.R.layout.simple_list_item_1, everyone);
                sv_student_list.setAdapter(studentArrayAdapter);


            }
        });











    }
}