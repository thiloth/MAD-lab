package com.example.a4mt19cs171_parse;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button parsexmlbtn,parsejsonbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        parsexmlbtn=(Button) findViewById(R.id.main_parse_xml_data);
                parsexmlbtn.setOnClickListener(this);
        parsejsonbtn=(Button) findViewById(R.id.main_parse_json_data);
                parsejsonbtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.equals(parsexmlbtn))
        {
            Intent intent=new Intent(this,viewdataactivity.class);
            intent.putExtra("datatype","xml");
            startActivity(intent);
        }
        else if(view.equals(parsejsonbtn))
        {
            Intent intent=new Intent(this,viewdataactivity.class);
            intent.putExtra("datatype","json");
            startActivity(intent);
        }

    }
}
