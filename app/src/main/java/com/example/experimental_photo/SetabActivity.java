package com.example.experimental_photo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


// 设置变量的地方
public class SetabActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.set_ab);

        Button submit = findViewById(R.id.btnsubmit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                inputDection(view);
            }
        });
    }

    private void inputDection(View view){
        try
        {
            //获取输入的a、b、y的值
            EditText a_string = (EditText)findViewById(R.id.value_a);
            String a = a_string.getText().toString();

            EditText b_string = (EditText)findViewById(R.id.value_b);
            String b = b_string.getText().toString();

            RadioGroup rdg = (RadioGroup)findViewById(R.id.radioGroup);
            int y = rdg.getCheckedRadioButtonId();



            if ((a == null || a.length() <= 0)||(b == null || b.length() <= 0)){

                Toast.makeText(SetabActivity.this, "总感觉少了点啥", Toast.LENGTH_SHORT).show();

            }else{
                //
                MySharedPreferences.seta(a, SetabActivity.this);
                MySharedPreferences.setb(b, SetabActivity.this);
                MySharedPreferences.sety(y, SetabActivity.this);

                Intent toNext = new Intent(SetabActivity.this,CameraActivity.class);
                startActivity(toNext);
            }
        }
        catch(Exception e)
        {
            Toast.makeText(SetabActivity.this,"总感觉哪里不对",Toast.LENGTH_SHORT).show();
        }
    }
};
