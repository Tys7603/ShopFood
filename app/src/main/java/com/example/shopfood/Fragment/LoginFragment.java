package com.example.shopfood.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.shopfood.Activity.MainActivity;
import com.example.shopfood.Activity.ProfileActivity;
import com.example.shopfood.Modal.User;
import com.example.shopfood.R;
import com.example.shopfood.api.CallApi;
import com.example.shopfood.api.ManagerUrl;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class LoginFragment extends Fragment {
    CardView btnLogin;
    List<User> list;
    TextInputEditText  tvEmail, tvPass;
    String email, pass;
    public LoginFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         getListLogin();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPass = view.findViewById(R.id.tv_pass);
        btnLogin.setOnClickListener(view1 -> startActivity(new Intent(getActivity(), MainActivity.class)));
    }

    public void getListLogin() {
        CallApi.callApi.getListUser().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                Log.e("TAG", response.body().toString());
                list = response.body();
                if (list == null){
                    Toast.makeText(getActivity(), "List null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {

            }
        });
    }


    public void validate(){
        email = tvEmail.getText().toString();
        pass = tvPass.getText().toString();
        if (email.isEmpty() || pass.isEmpty() ){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        }else {
            for (int i = 0 ; i < list.size() ; i++){
                if (email.equals(list.get(i).getEmail()) && pass.equals(list.get(i).getPassword())){
                    Toast.makeText(getActivity(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    rememberUser(list.get(i).getFullName(), email, pass);
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    return;
                } else {
                    Toast.makeText(getActivity(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void rememberUser(String name, String email, String pass){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name);
        editor.putString("email", email);
        editor.putString("pass", pass);
        editor.apply();

    }

}