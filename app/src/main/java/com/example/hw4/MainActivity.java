package com.example.hw4;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static int MODE = MODE_PRIVATE;
    public static final String PREFERENCE_NAME = "SaveSetting";
    private Button btnLogin;
    private EditText etAccount, etPassword;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLogin = findViewById(R.id.btnLogin); // 确保ID与XML布局文件中的相符
        etAccount = findViewById(R.id.account_input);
        etPassword = findViewById(R.id.password_input);
        btnLogin.setOnClickListener((View.OnClickListener) this);

        // 获取SharedPreferences对象
        sharedPreferences = getSharedPreferences(PREFERENCE_NAME, MODE);

        // 读取账户和密码
        String savedAccount = sharedPreferences.getString("account", "");
        String savedPassword = sharedPreferences.getString("password", "");

        // 如果账户和密码不为空，填充到输入框
        if (!savedAccount.isEmpty() && !savedPassword.isEmpty()) {
            etAccount.setText(savedAccount);
            etPassword.setText(savedPassword);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnLogin) {
            String account = etAccount.getText().toString();
            String password = etPassword.getText().toString();

            // 保存账户和密码到SharedPreferences
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("account", account);
            editor.putString("password", password);
            editor.apply();

            // 显示登录成功消息
            Toast.makeText(this, "登录成功，账户和密码已保存", Toast.LENGTH_LONG).show();
        }
    }
}