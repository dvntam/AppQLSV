package com.example.appqlsv;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appqlsv.database.database;
import com.example.appqlsv.model.Subject;

public class ActivityAddSubject extends AppCompatActivity {
    Button buttonAddSubject;
    EditText edtSubjectTitle, edtSubjectCredit, edtSubjectTime, edtSubjectPlace;
    com.example.appqlsv.database.database database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);

        buttonAddSubject = findViewById(R.id.buttonAddSubject);
        edtSubjectCredit = findViewById(R.id.EditTextSubjectCredit);
        edtSubjectPlace = findViewById(R.id.EditTextSubjectPlace);
        edtSubjectTime = findViewById(R.id.EditTextSubjectTime);
        edtSubjectTitle = findViewById(R.id.EditTextSubjectTitle);

        database = new database(this);

        buttonAddSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogAdd();
            }
        });
    }

    private void DialogAdd() {
        // tạo đối tượng cửa sổ
        Dialog dialog = new Dialog(this);
// nạp layout vào dialog
        dialog.setContentView(R.layout.dialogadd);
// tắt lick ngoài là thoát
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = dialog.findViewById(R.id.buttonyes);
        Button btnNo = dialog.findViewById(R.id.buttonNo);
        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjecttitle = edtSubjectTitle.getText().toString().trim();
                String credit = edtSubjectCredit.toString().trim();
                String time = edtSubjectTime.getText().toString().trim();
                String place = edtSubjectPlace.getText().toString().trim();

                //Nếu dữ liệu chưa nhập đầy đủ
                if (subjecttitle.equals("") || credit.equals("") || time.equals("") || place.equals("")) {
                    Toast.makeText(ActivityAddSubject.this, "Did not enter enough information", Toast.LENGTH_SHORT).show();
                }
                else {
                    // gán cho đối tượng subject giá trị được nhập vào
                    Subject subject = CreatSubject();
                    // add trong database
                    database.AddSubject(subject);

                    // thành công thì chuyển sang ggiao diện subject
                    Intent intent = new Intent(ActivityAddSubject.this,ActivitySubject.class);
                    startActivity(intent);

                    Toast.makeText(ActivityAddSubject.this, "more success", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //Nếu không add nữa
        btnNo.setOnClickListener(new View.OnClickListener(){
            public  void  onClick(View view) {
                dialog.cancel();
            }
        });
        // show dialog
        dialog.show();
    }



    //create subject
    private Subject CreatSubject(){

        String subjecttitle = edtSubjectTitle.getText().toString().trim();
        int Credit = Integer.parseInt(edtSubjectCredit.getText().toString().trim());
        String time = edtSubjectTime.getText().toString().trim();
        String place = edtSubjectPlace .getText().toString().trim();

        Subject subject = new Subject(subjecttitle, Credit, time, place);
        return subject;
    }


}    
    
    







