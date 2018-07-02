package com.cool.todayheadline.fragments;

import android.content.Context;
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
import com.cool.todayheadline.bean.MonitorEditText;
import com.cool.todayheadline.utils.Const;
import com.cool.todayheadline.utils.HttpThread;


public class LoginFragment extends Fragment {


    private OnFragmentInteractionListener mListener;

    private EditText mUsername;
    private String url ="http://happymmall.houjunjie.site/user/login.do?";
    private EditText mPassword;
    private int status;
    private TextView textView;
    private int id;
    private ImageView backButton;
    private TextView response;
    private TextView jump;

    private Handler handler = new Handler();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        mUsername =  view.findViewById(R.id.username);
        mPassword =  view.findViewById(R.id.password);
        response = view.findViewById(R.id.response);
        jump = view.findViewById(R.id.tv_register);

        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFragmentInteraction();
//                android.support.v4.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
//                Fragment registerFra = new RegisterFragment();
//                fragmentManager.beginTransaction().replace(R.id.login_layout, registerFra).commit();

            }
        });



        textView = view.findViewById(R.id.login_tv);
        String str="点此查看<font color='#5CACEE'>“用户协议”</font>和<font color='#5CACEE'>“隐私政策”</font>";
        textView.setTextSize(12);
        textView.setText(Html.fromHtml(str));



        backButton = view.findViewById(R.id.back_btn);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("确认放弃登录?");
                builder.setPositiveButton("放弃", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getActivity().finish();
                    }
                }).setNegativeButton("继续登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }).show();


            }
        });




        final Button mEmailSignInButton = view.findViewById(R.id.email_sign_in_button);
        new MonitorEditText().SetMonitorEditText(mEmailSignInButton, mUsername, mPassword);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String username = mUsername.getText().toString().trim();
                final String password = mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {


                    Toast.makeText(getActivity(), "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                HttpThread httpThread = new HttpThread(url,username, password, response, handler , status ,id);
                httpThread.run();
                status = httpThread.getStatus();
                id = httpThread.getId();
                //new HttpThread(url,username, password, response, handler,status).run();

                //  Log.e("flag的值是",String.valueOf(status));

                if(status == 0){
                    Intent intent = new Intent(getActivity(),MainActivity.class);

                    Const.USER_NAME="欢迎您"+username;
                    Const.USER_ID = id;
                    startActivity(intent);
                    getActivity().finish();
                }else{
                    mPassword.setText("");
                    mUsername.setText("");
                    Toast.makeText(getActivity(),"您输入的信息有误",Toast.LENGTH_LONG).show();
                }
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }
    public interface OnFragmentInteractionListener
    {
        void onFragmentInteraction();
    }



}
