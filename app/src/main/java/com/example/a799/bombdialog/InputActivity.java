package com.example.a799.bombdialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.*;



import java.util.Random;


public class InputActivity extends Activity {
    TextView textView;
    public  void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //弹球画布
        textView = (TextView) findViewById(R.id.text);
        LinearLayout layout = (LinearLayout) findViewById(R.id.layout);
       // maintext = (TextView)findViewById(R.id.percent);
        tu1 view1 = new tu1(this);
        Intent i = getIntent();

        int index = Integer.valueOf(i.getStringExtra("Index")).intValue();
        System.out.println("index = " + index);

        view1.SetRR(index);
        layout.addView(view1, 1000, 1000);
    }

}

class tu1 extends SurfaceView  implements SurfaceHolder.Callback,Runnable{
    Thread thread;
    Paint paint;
    boolean die;
    int rr ;
    SurfaceHolder holder;
    public tu1(Context c){
        super(c);
        setMinimumHeight(160);
        setMinimumWidth(100);
        paint=new Paint();
        holder=getHolder();
        holder.addCallback(this);
        thread=new Thread(this);
    }
    public void SetRR(int num){
        this.rr = num;
    }
    public void surfaceChanged(SurfaceHolder holder,int format,int width,int height){}
    public void surfaceCreated(SurfaceHolder holder){
        thread.start();
    }
    public void surfaceDestroyed(SurfaceHolder holder) {
        die=true;
    }
    public void run(){
        Random random=new Random();
        int r = this.rr;
        System.out.println("r = " + r);

        int x=(random.nextInt(4)+1)*100;
        int y=(random.nextInt(4)+1)*101;
        int a=random.nextInt(4)+1;//随机产生四个方向
        int d = 10;
        while (true){
            if(die==true)return;
            Canvas canvas=holder.lockCanvas();
            drawCircle(canvas,x,y,r);
            holder.unlockCanvasAndPost(canvas);

            System.out.println("x = " + x);
            System.out.println("y = " + y);

            if(a==1){//315
                x += d;
                y += d;
                if(x>=1000-r){
                    a=3;

                }
                if(y>=1000-r){
                    a=4;
                }
                if(x>=1000-r&&y>=1000-r)
                    break;
            }
            if(a==2){//135
                x -= d;
                y -= d;
                if(x <= 0+r)
                    a=4;
                if(y <= 0+r)
                    a=3;
                if(x<=0+r&y<=0+r) break;
            }
            if(a==3){//225
                x -= d;
                y += d;
                if(x <= 0+r)
                    a=1;
                if(y >= 1000-r)
                    a=2;
                if(x <= 0+r && y >= 1000-r)
                    break;
            }
            if(a==4){//45
                x += d;
                y -= d;
                if(x >= 1000-r)
                    a=2;
                if(y <= 0+r)
                    a=1;
                if(x >= 1000-r && y <= 0+r)
                    break;
            }
        }
    }
    private  void drawCircle(Canvas canvas,  int x,int y,int r){
        //paint.setColor(Color.GRAY); 调整小球的颜色
        paint.setARGB(255, 187, 187, 189);
        Bitmap bm= BitmapFactory.decodeResource(getResources(),R.drawable.d);
        canvas.drawBitmap(bm,0,0,paint);
        //  canvas.drawColor(Color.GRAY);
        canvas.drawCircle(x,y,r,paint);
    }

}

