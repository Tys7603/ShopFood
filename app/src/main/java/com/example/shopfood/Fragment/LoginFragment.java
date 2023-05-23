package com.example.shopfood.Fragment;

import android.content.Intent;
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
        Login();
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnLogin = view.findViewById(R.id.btnLogin);
        tvEmail = view.findViewById(R.id.tv_email);
        tvPass = view.findViewById(R.id.tv_pass);
        btnLogin.setOnClickListener(view1 -> validate());
    }


    public void Login() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ManagerUrl.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        CallApi callApi = retrofit.create(CallApi.class);
        Call<List<User>> call = callApi.getListUser();
        call.enqueue(new Callback<List<User>>() {
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
                Log.d("ERR", t.toString());
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
                    startActivity(new Intent(getActivity(), MainActivity.class));
                    return;
                } else {
                    Toast.makeText(getActivity(), "Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}