package com.example.shopfood.activity.main;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.shopfood.activity.welcome.OnboardingActivity;
import com.example.shopfood.R;
import com.example.shopfood.interfacee.ProfileInterface;
import com.example.shopfood.modal.User;
import com.example.shopfood.presenter.ProfilePresenter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.gson.Gson;

public class ProfileActivity extends AppCompatActivity implements ProfileInterface {
    TextView tvName, tvEmail, tvPass, tvSDT, tvAddress, tvRole;
    Button btnLogOut, btnCancel, btnUpdate;
    LinearLayout btnBack;
    ImageView ivEditInformation, ivEditContact, ivChangeImage, ivProfile;
    Gson mGson;
    ProfilePresenter mProfilePresenter;
    EditText etDialog1, etDialog2, etDialog3;
    Dialog mDialog;
    Uri mUri;
    StorageReference storageRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        init();
        setText();
        onClick();
    }
    // anh xa view
    public void init(){
        mProfilePresenter = new ProfilePresenter(this, this);
        mGson = new Gson();
        tvName = findViewById(R.id.tv_nameProfile);
        tvEmail = findViewById(R.id.tv_emailProfile);
        tvPass = findViewById(R.id.tv_passProfile);
        tvAddress = findViewById(R.id.tv_addressProfile);
        tvSDT = findViewById(R.id.tv_sdtProfile);
        tvRole = findViewById(R.id.tv_roleProfile);
        btnBack = findViewById(R.id.btn_backMenu);
        btnLogOut = findViewById(R.id.btn_logOut);
        ivEditContact = findViewById(R.id.ivEditContactP);
        ivEditInformation = findViewById(R.id.ivEditInforP);
        ivChangeImage = findViewById(R.id.ivDoiHinhAnh);
        ivProfile = findViewById(R.id.ivProfile);
    }
    public void onClick(){
        btnBack.setOnClickListener(view ->{
            if (mUri != null){
                uploadImgWithFireBase();
            }
            startActivity(new Intent(ProfileActivity.this, MainActivity.class));
        });
        btnLogOut.setOnClickListener(view -> logOut());
        ivEditInformation.setOnClickListener(view -> editInformation());
        ivEditContact.setOnClickListener(view -> editContact());
        ivChangeImage.setOnClickListener(view -> choseImgFromGallery());
    }

    // image
    private void choseImgFromGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        mActivityResultLauncher.launch(Intent.createChooser(intent,"Da chon hinh nah"));
    }

    // set du lieu len form
    public void setText(){
        User user = convertObject();
        tvName.setText(user.getName());
        tvEmail.setText(user.getEmail());
        if(!user.getAvatar().isEmpty()){
            Glide.with(this).load(user.getAvatar()).into(ivProfile);
        }
        tvPass.setText(user.getPassword());
        tvSDT.setText(user.getSdt());
        tvAddress.setText(user.getAddress());
        tvRole.setText(user.getRole());
    }
    // chuyen kieu json -> object
    private User convertObject() {
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", MODE_PRIVATE);
        String strUser  = sharedPreferences.getString("user","");
        User user = mGson.fromJson(strUser, User.class);
        return user;
    }
    // thoat khoi ung dun
    public void logOut(){
        SharedPreferences sharedPreferences = getSharedPreferences("rememberUser", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
       if (mUri != null){
           uploadImgWithFireBase();
       }
        startActivity(new Intent(ProfileActivity.this, OnboardingActivity.class));
    }

    // upload file voi firebase
    private void uploadImgWithFireBase() {
        ProgressDialog mProgressDialog = ProgressDialog.show(this, "","Updating ... ");
        storageRef = FirebaseStorage.getInstance().getReference().child("images/"+mUri.getLastPathSegment());
        storageRef.putFile(mUri)
                .addOnSuccessListener(taskSnapshot -> {
                    ivProfile.setImageURI(null);
                    storageRef.getDownloadUrl().addOnSuccessListener(uri -> {
                        User user = convertObject();
                        String email = user.getEmail();
                        String avatar = uri.toString();
                        // set
                        user.setAvatar(avatar);
                        mProfilePresenter.updateAvatarUser(email, avatar, user);
                        mProgressDialog.dismiss();
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                    })
                   .addOnFailureListener(e -> {

                   });
                })
                .addOnFailureListener(e ->{
                });
    }


    private void editContact() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.layout_edit_profile);
        // anh xa dialog
        initDialog(mDialog);
        // set du lieu len dialog
        User user = convertObject();
        setValueDialogC(user);
        // xu li su kien click
        btnCancel.setOnClickListener(view -> mDialog.dismiss());
        btnUpdate.setOnClickListener(view -> {
            String email = etDialog1.getText().toString();
            String sdt = etDialog2.getText().toString();
            String address = etDialog3.getText().toString();
            user.setSdt(sdt);
            user.setAddress(address);
            mProfilePresenter.updateContactUser(email, sdt, address, user);
            mDialog.dismiss();
        });
        //
        mDialog.show();
    }
    private void editInformation() {
        mDialog = new Dialog(this);
        mDialog.setContentView(R.layout.layout_edit_profile);
        // anh xa dialog
        initDialog(mDialog);
        // set du lieu len dialog
        User user = convertObject();
        setValueDialogI(user);
        // xu li su kien click
        btnCancel.setOnClickListener(view -> mDialog.dismiss());
        btnUpdate.setOnClickListener(view -> {
            String email = user.getEmail();
            String name = etDialog1.getText().toString();
            String pass = etDialog3.getText().toString();
            user.setName(name);
            user.setPassword(pass);
            if (mProfilePresenter.updateInformationUser(email, pass, name, user)){
                mDialog.dismiss();
            }
        });
        //
        mDialog.show();
    }
    private void setValueDialogC(User user){
        etDialog1.setHint("Email");
        etDialog1.setText(user.getEmail());
        etDialog1.setEnabled(false);
        etDialog2.setHint("Số điện thoại");
        etDialog2.setText(user.getSdt());
        etDialog3.setHint("Địa chỉ");
        etDialog3.setText(user.getAddress());

    }
    private void setValueDialogI(User user){
        etDialog1.setHint("Họ và tên");
        etDialog1.setText(user.getName());
        etDialog2.setHint("Vai trò");
        etDialog2.setText(user.getRole());
        etDialog2.setEnabled(false);
        etDialog3.setHint("Mật khẩu");
        etDialog3.setText(user.getPassword());
    }
    public void initDialog(Dialog mDialog){
        etDialog1 = mDialog.findViewById(R.id.etDiaglog1);
        etDialog2 = mDialog.findViewById(R.id.etDialog2);
        etDialog3 = mDialog.findViewById(R.id.etDialog3);
        btnCancel = mDialog.findViewById(R.id.btnCancelD);
        btnUpdate = mDialog.findViewById(R.id.btnUpdateD);
    }


    // lang nghe su kien cua he thong
    ActivityResultLauncher mActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK){
                    Intent data = result.getData();
                    if (data == null){
                        return;
                    }
                    mUri = data.getData();
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), mUri);
                        ivProfile.setImageBitmap(bitmap);
                    }catch (Exception e){

                    }
                }
            });

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onValidate() {
        Toast.makeText(this, "Nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void notificationSuccess(boolean check) {
       if (check){
           recreate();
           Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
       }else {
           Toast.makeText(this, "Cập nhật thất bại", Toast.LENGTH_SHORT).show();
       }
    }

}