package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    EditText edt_username, edt_password, edt_email;
    Button btn_signUp;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_username = findViewById(R.id.username);
        edt_password = findViewById(R.id.password);
        edt_email = findViewById(R.id.email);
        btn_signUp = findViewById(R.id.signUp);

        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveUser(CreateRequest());
            }
        });
    }

    public UserRequest CreateRequest() {
        UserRequest userRequest = new UserRequest();
        userRequest.setUserName(edt_username.getText().toString());
        userRequest.setEmail(edt_email.getText().toString());
        userRequest.setFirst_name(edt_password.getText().toString());
        return userRequest;
    }

   public void saveUser(UserRequest userRequest){
        Call<UserResponse> userResponseCall= UserClient.getUserService().saveUsers(userRequest);
        userResponseCall.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(MainActivity.this,"Saved Succesfully",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this,"Request failed",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this,"Request failed"+t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
   }

}
