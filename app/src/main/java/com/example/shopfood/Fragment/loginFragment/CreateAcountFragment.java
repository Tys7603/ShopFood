package com.example.shopfood.Fragment.loginFragment;

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

import com.example.shopfood.Modal.StatusUser;
import com.example.shopfood.Modal.User;
import com.example.shopfood.R;
import com.example.shopfood.api.ApiService;
import com.example.shopfood.api.Constant;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CreateAcountFragment extends Fragment {
    TextInputEditText tvFullName, tvEmail, tvPass;
    CardView btnCreateAccount;
    List<User> list;
    String name, email, pass;
    final String regex = "[^@]{2,64}@[^.]{2,253}\\.[0-9a-z-.]{2,63}";
    public CreateAcountFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_create_acount_, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(view);
        getListUser();
        onClick();
    }
    public void init(View view){
        tvFullName = view.findViewById(R.id.tv_cFullName);
        tvEmail = view.findViewById(R.id.tv_cEmail);
        tvPass = view.findViewById(R.id.tv_cPass);
        btnCreateAccount = view.findViewById(R.id.btnCreateAccount);
    }
    public void onClick(){
        btnCreateAccount.setOnClickListener(view1 -> {

            if (validate()) {
                createUser( email,name, pass);
            }
        });
    }
    public void getListUser() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<List<User>> call = apiService.getListUser();
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
    public void createUser(String email , String name, String pass){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       ApiService apiService = retrofit.create(ApiService.class);
       Call<StatusUser> call = apiService.createUser(email,name,pass);
       call.enqueue(new Callback<StatusUser>() {
           @Override
           public void onResponse(Call<StatusUser> call, Response<StatusUser> response) {
               if (response.body().getStatus().equals("Success")){
                   Toast.makeText(getActivity(), "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                   clearText();
               }else {
                   Toast.makeText(getActivity(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
               }
           }

           @Override
           public void onFailure(Call<StatusUser> call, Throwable t) {

           }
       });
    }
    private void clearText() {
        tvEmail.setText("");
        tvFullName.setText("");
        tvPass.setText("");
    }
    public boolean validate(){
        name = tvFullName.getText().toString();
        email = tvEmail.getText().toString();
        pass = tvPass.getText().toString();

        if (!checkInfo(name, email, pass)){
            return false;
        } else {
            if (!checkEmail(email)){
               return false;
            } else {
                if (!checkExits(email)){
                    return false;
                }
            }
        }
        return true ;
    }
    public boolean checkInfo(String name , String email, String pass){
        if (name.isEmpty() || email.isEmpty() || pass.isEmpty()){
            Toast.makeText(getActivity(), "Bạn cần nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            Toast.makeText(getActivity(), "Email cần đúng định dạng", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
    public boolean checkExits(String email){
        for (User user : list ) {
           if (email.equals(user.getEmail())){
                Toast.makeText(getActivity(), "Email đã tồn tại", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }

}