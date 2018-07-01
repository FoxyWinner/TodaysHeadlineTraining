package com.cool.todayheadline.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.todayheadline.R;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.HttpThread;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {





    private AlertDialog alertDialog;



    // UI references.
   private EditText mUsername;
    private String url ="http://happymmall.houjunjie.site/user/login.do?";
    private EditText mPassword;
    private int status;
    private TextView textView;
    private int id;

    private ImageView backButton;
    private TextView response;
    private Handler handler = new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

     mUsername = (EditText) findViewById(R.id.username);
        mPassword = (EditText) findViewById(R.id.password);
        response = (TextView)findViewById(R.id.response);

        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }


        textView = (TextView)findViewById(R.id.login_tv);
        String str="点此查看<font color='#5CACEE'>“用户协议”</font>和<font color='#5CACEE'>“隐私政策”</font>";
        textView.setTextSize(12);
        textView.setText(Html.fromHtml(str));



        backButton = (ImageView)findViewById(R.id.back_btn);

        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setMessage("确认放弃登录?");
                builder.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                }).setNegativeButton("继续登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();


            }
        });


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mUsername.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {

                    Toast.makeText(LoginActivity.this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                HttpThread httpThread = new HttpThread(url,username, password, response, handler , status ,id);
                httpThread.run();
                status = httpThread.getStatus();
                id = httpThread.getId();
                //new HttpThread(url,username, password, response, handler,status).run();

            //   Log.e("flag的值是",String.valueOf(status));

               if(status == 0){
                   Intent intent = new Intent(LoginActivity.this,MainActivity.class);

                   Const.USER_NAME="欢迎您"+username;
                   Const.USER_ID = id;
                   startActivity(intent);
                   finish();
               }else{
                   mPassword.setText("");
                   mUsername.setText("");
                   Toast.makeText(LoginActivity.this,"您输入的信息有误",Toast.LENGTH_LONG).show();
               }


            }
        });


    }






}

