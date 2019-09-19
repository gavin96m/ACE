package com.example.experimental_photo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class CalculationActivity  extends Activity {

    private int R_value;
    private int G_value;
    private int B_value;
    private float y_value;
    private ImageView picture;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation);


        picture = (ImageView) findViewById(R.id.fullphoto);

        Intent intent = getIntent();
        if(intent!=null){
            Uri imageUri = intent.getData();
            if (imageUri == null) {
                Log.i("tag", "The uri is not exist.");
                return;
            }

            try {
//                        Bitmap bitmap =BitmapFactory.decodeStream(getClass().getResourceAsStream(imageUri.toString()));
                bitmap = BitmapFactory.decodeStream(getContentResolver()
                        .openInputStream(imageUri));
                picture.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
            picture.setImageBitmap(bitmap);
        }


    picture.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            int x = (int) event.getX();

            int y = (int) event.getY();

            if (event.getAction() == MotionEvent.ACTION_UP) {
                int color = bitmap.getPixel(x, y);
                // 如果你想做的更细致的话 可以把颜色值的R G B 拿到做响应的处理
                R_value = Color.red(color);
                G_value = Color.green(color);
                B_value = Color.blue(color);



                Calculation();
//                int a = Color.alpha(color);
//                Toast.makeText(CalculationActivity.this, "R="+R_value, Toast.LENGTH_SHORT).show();
//                Toast.makeText(CalculationActivity.this, "G="+G_value, Toast.LENGTH_SHORT).show();
//                Toast.makeText(CalculationActivity.this, "B="+B_value, Toast.LENGTH_SHORT).show();



//                btnColor.setTextColor(Color.rgb(r, g, b));

            }
            return true;
        }

        });


    }

    public void Calculation(){
        String a = MySharedPreferences.geta(CalculationActivity.this);
//        Toast.makeText(CalculationActivity.this, "a="+a, Toast.LENGTH_SHORT).show();

        String b = MySharedPreferences.getb(CalculationActivity.this);
//        Toast.makeText(CalculationActivity.this, "b="+b, Toast.LENGTH_SHORT).show();


        int y = MySharedPreferences.gety(CalculationActivity.this);

        //RGB计算
        try {
            switch (y) {
                case R.id.btnRG:
//                    Toast.makeText(CalculationActivity.this, "RG", Toast.LENGTH_SHORT).show();
                    y_value = R_value / G_value;
                    break;

                case R.id.btnRB:
//                    Toast.makeText(CalculationActivity.this, "RB", Toast.LENGTH_SHORT).show();
                    y_value = R_value / B_value;
                    break;

                case R.id.btnGB:
//                    Toast.makeText(CalculationActivity.this, "GB", Toast.LENGTH_SHORT).show();
                    y_value = G_value / B_value;
                    break;

                case R.id.btnGR:
//                    Toast.makeText(CalculationActivity.this, "GR", Toast.LENGTH_SHORT).show();
                    y_value = G_value / R_value;
                    break;

                case R.id.btnBR:
//                    Toast.makeText(CalculationActivity.this, "BR", Toast.LENGTH_SHORT).show();
                    y_value = B_value / R_value;
                    break;

                case R.id.btnBG:
//                    Toast.makeText(CalculationActivity.this, "BG", Toast.LENGTH_SHORT).show();
                    y_value = B_value / G_value;
                    break;
                default:
                    Toast.makeText(this, "what happened", Toast.LENGTH_SHORT).show();
            }
        }catch (ArithmeticException e) {
//            Toast.makeText(this, "除数为0，y=0", Toast.LENGTH_SHORT).show();
            y_value =0;
        }
//        Toast.makeText(this, Float.toString(y_value), Toast.LENGTH_SHORT).show();

//        float a_value = Float.valueOf(a).floatValue();

        if(y_value<=1.0/255||y_value>255){
            Toast.makeText(this, "y值咋了?", Toast.LENGTH_SHORT).show();
        }
        float a_value = Float.parseFloat(a);
        float b_value = Float.parseFloat(b);


        float x = (y_value-b_value)/a_value;


        TextView view_R = findViewById(R.id.R_final);
        view_R.setText("R:"+String.valueOf(R_value));
        TextView view_G = findViewById(R.id.G_final);
        view_G.setText("G:"+String.valueOf(G_value));
        TextView view_B = findViewById(R.id.B_final);
        view_B.setText("B:"+B_value);

        //确保3位小数
        DecimalFormat decimalFormat1=new DecimalFormat(".000");
        DecimalFormat decimalFormat2=new DecimalFormat(".000");
        String x_final = decimalFormat1.format(x);
        String y_final = decimalFormat2.format(y_value);


        TextView view_x = findViewById(R.id.x_final);
        TextView view_y = findViewById(R.id.y_final);
        view_x.setText("X="+x_final);
        view_y.setText("Y="+y_final);

    }



}
