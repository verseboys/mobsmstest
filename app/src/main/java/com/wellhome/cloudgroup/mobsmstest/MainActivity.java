package com.wellhome.cloudgroup.mobsmstest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Random;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;


public class MainActivity extends AppCompatActivity {


    Button mBtnBindPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //初始化


        mBtnBindPhone = (Button) this.findViewById(R.id.btn_bind_phone);
        //设置点击事件
        mBtnBindPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册手机号
                RegisterPage registerPage = new RegisterPage();

                //注册回调事件
                registerPage.setRegisterCallback(new EventHandler() {
                    //事件完成后调用
                    @Override
                    public void afterEvent(int i, int result, Object data) {
                        //判断结果是否完成
                        super.afterEvent(i, result, data);
                        if(result==SMSSDK.RESULT_COMPLETE){
                            HashMap<String,Object> maps=(HashMap<String,Object> )data;

                           String country= (String) maps.get("country");
                            String phone= (String) maps.get("phone");

                            submitUserinfo(country,phone);
                        }

                    }
                });
                //显示注册界面
                registerPage.show(MainActivity.this);
            }
        });


    }


    // 如果希望在读取通信录的时候提示用户，可以添加下面的代码，并且必须在其他代码调用之前，否则不起作用；如果没这个需求，可以不加这行代码


//        // 创建EventHandler对象
//        eventHandler = new EventHandler() {
//            @Override
//            public void afterEvent(int event, int result, Object data) {
//                if (data instanceof Throwable) {
//                    Throwable throwable = (Throwable) data;
//                    String msg = throwable.getMessage();
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
//                        }
//                    });
//                } else {
//                    if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                        // 这里是验证成功的回调，可以处理验证成功后您自己的逻辑，需要注意的是这里不是主线程
//                    }
//                }
//            }
//        };
//
//        // 注册监听器
//        SMSSDK.registerEventHandler(eventHandler);



    public  void  submitUserinfo(String country,String phone){

        Random r=new Random();
        String uid=Math.abs(r.nextInt())+"";
        String nickn="ver";


        SMSSDK.submitUserInfo(uid,nickn,null,country,phone);



    }



}


