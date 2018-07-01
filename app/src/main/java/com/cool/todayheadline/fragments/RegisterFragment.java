package com.cool.todayheadline.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cool.todayheadline.R;
import com.cool.todayheadline.activities.MainActivity;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.HttpThread_Register;

public class RegisterFragment extends Fragment {

    private EditText mEmail;
    private EditText mUsername;
    private String url ="http://happymmall.houjunjie.site/user/register.do?";
    private EditText mPassword;
    private int status;
    private TextView textView;
    private int id;

    private ImageView backButton;
    private TextView response;
    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        mEmail = view.findViewById(R.id.reg_email);
        mUsername =  view.findViewById(R.id.reg_username);
        mPassword =  view.findViewById(R.id.reg_password);
        response = view.findViewById(R.id.reg_response);

        textView = view.findViewById(R.id.reg_tv);
        String str="老子同意<font color='#5CACEE'>“用户协议”</font>和<font color='#5CACEE'>“隐私政策”</font>";
        textView.setTextSize(13);
        textView.setText(Html.fromHtml(str));

        backButton = view.findViewById(R.id.reg_back);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //   Log.e("flag的值是","trtrtrtr");
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确认放弃注册?");
                builder.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                }).setNegativeButton("继续注册", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();


            }
        });



        Button mEmailSignInButton = view.findViewById(R.id.reg_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mUsername.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();
                final String email = mEmail.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(email)) {

                    Toast.makeText(getActivity(), "信息不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                HttpThread_Register httpThread = new HttpThread_Register(url,username, password,email, response, handler , status ,id);
                httpThread.run();
                status = httpThread.getStatus();

                //new HttpThread(url,username, password, response, handler,status).run();

                //  Log.e("flag的值是",String.valueOf(status));

                if(status == 0){
                    Intent intent = new Intent(getActivity(),MainActivity.class);

                    Const.USER_NAME="欢迎您"+username;
                    Const.USER_ID = id;
                    startActivity(intent);
                    getActivity().finish();
                }else{

                    Toast.makeText(getActivity(),"用户名已存在",Toast.LENGTH_LONG).show();
                }


            }
        });
        return view;
    }

}
