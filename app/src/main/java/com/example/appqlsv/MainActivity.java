package com.example.appqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnSubject, btnAuthor,btnExit ;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAuthor = findViewById(R.id.buttonAuthor);
        btnSubject = findViewById(R.id.buttonSubject);
        btnExit = findViewById(R.id.buttonExit);

        //Click tác giả
        btnAuthor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAuthor();
            }
        });

        //Click mon hoc
        btnSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ActivitySubject.class);
                startActivity(intent);
            }
        });
// click exit app
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialogExit();
            }
        });
    }
//Hàm hiển thị cửa sổ dialog exit
    private void dialogExit() {
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialogexit);

        //Tắt click ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonYes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                startActivity(intent);

                //Thoát
                Intent intent1 = new Intent(Intent.ACTION_MAIN);
                intent1.addCategory(Intent.CATEGORY_HOME);
                startActivity(intent1);
            }
        });
        //Nếu click no thì trở lại main đóng cửa sổ
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //show dialog
        dialog.show();

    }
// Hiển thị thông tin tác giả
    private void DialogAuthor() {
        //Tạo đối tượng cửa sổ dialog
        Dialog dialog = new Dialog(this);

        dialog.setContentView(R.layout.dialoginformation);
        dialog.show();
    }
}