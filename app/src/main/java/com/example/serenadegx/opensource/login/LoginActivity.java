package com.example.serenadegx.opensource.login;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.serenadegx.opensource.MainActivity;
import com.example.serenadegx.opensource.R;

public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView {

    private ProgressDialog progressDialog;
    private LoginContract.Presenter loginPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        TextInputLayout tilPhone = findViewById(R.id.til_phone);
        TextInputLayout tilPwd = findViewById(R.id.til_pwd);
        final TextInputEditText etPhone = findViewById(R.id.et_phone);
        final TextInputEditText etPwd = findViewById(R.id.et_pwd);
        Button btSure = findViewById(R.id.bt_sure);

        loginPresenter = new LoginPresenter(this);

        btSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginPresenter.login(etPhone.getText().toString(), etPwd.getText().toString());
            }
        });

    }

    public static void start2LoginActivity(Context context) {
        //http://10.10.10.205:7109/mdd-papp-web/base/web/login
        /*
        map.put("userLoginParam", "");
        map.put("loginType", "PASSWORD_TYPE");
        map.put("flowNo", "");
        map.put("checkCode", "");
        map.put("passWord", "");
        map.put("userType", "1");
         */
        context.startActivity(new Intent(context, LoginActivity.class));
    }

    @Override
    public void loginSuccess(LoginResult data) {
        Toast.makeText(this, "登陆成功！", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        MainActivity.start2MainActivity(this);
    }

    @Override
    public void unregistered() {

    }

    @Override
    public void serverError(int code) {
        Toast.makeText(this, "服务器错误：" + code, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void netWorkError() {
        Toast.makeText(this, "网络错误", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        progressDialog = ProgressDialog.show(this, "", "正在登陆...", false, false);
    }

    @Override
    public void hideLoading() {
        if (progressDialog != null)
            progressDialog.cancel();
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
