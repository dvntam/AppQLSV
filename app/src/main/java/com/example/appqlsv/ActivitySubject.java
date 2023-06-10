package com.example.appqlsv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Database;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.appqlsv.database.database ;
import com.example.appqlsv.adapter.adaptersubject ;
import com.example.appqlsv.model.Subject;

import java.util.ArrayList;
import java.util.Objects;


public class ActivitySubject extends AppCompatActivity {
    Toolbar toolbar;
    ListView listViewsubject;
    ArrayList<Subject> ArrayListSubject;
    com.example.appqlsv.database.database database;
    com.example.appqlsv.adapter.adaptersubject adaptersubject;
    int count = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);

        toolbar = findViewById(R.id.toolbarSubject);
        listViewsubject = findViewById(R.id.listviewSubject);

        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        database = new database(this);

        ArrayListSubject = new ArrayList<>();

        Cursor cursor = database.getDataSubject();
        while (cursor.moveToNext()) {

            int id = cursor.getInt(0);
            String title = cursor.getString(1);
            int credit = cursor.getInt(2);
            String time = cursor.getString(3);
            String place = cursor.getString(4);

            ArrayListSubject.add(new Subject(id, title, credit, time, place));

        }
        adaptersubject = new adaptersubject(ActivitySubject.this, ArrayListSubject);
        listViewsubject.setAdapter(adaptersubject);
        cursor.moveToFirst();
        cursor.close();
//
/////Tạo sự kiện click vào item sẽ chuyển qua student
//        listViewsubject.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Intent intent = new Intent(ActivitySubject.this, ActivityStudent.class);
//                int id_student = ArrayListSubject.get(i).getId();
//                //Truyền dữ liệu id subject qua activity student
//                intent.putExtra("id_subject", id_student);
//                startActivity(intent);
//            }
//        });

    }

//Thêm một menu là add vào toolbar


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuadd, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menu= item.getItemId() ;{
            //Nếu click vào add thid chuyển qua màn hình add subject
            if (menu == R.id.menuadd) {
                Intent intent1 = new Intent(ActivitySubject.this, ActivityAddSubject.class);
                startActivity(intent1);
            }

            //Nếu click vào nút còn lại là nút back thì quay lại main
            else {
                Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
                startActivity(intent);
            }

        }

        return super.onOptionsItemSelected(item);
    }

    //Nếu click back ở điện thoại sẽ trở về main activity
    @Override
    public void onBackPressed() {
        count++;
        if (count >= 1) {
            Intent intent = new Intent(ActivitySubject.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void information(final int pos) {
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            if (id == pos) {
                Intent intent = new Intent(ActivitySubject.this, ActivitySubjectinformation.class);
                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                intent.putExtra("title", title);
                intent.putExtra("credit", credit);
                intent.putExtra("time", time);
                intent.putExtra("place", place);

                startActivity(intent);
            }
        }
    }

    public void delete(final int position) {
        //Đối tượng cửa sổ
        Dialog dialog = new Dialog(this);

        //nạp Layout vào Dialog
        dialog.setContentView(R.layout.dialogdeletesubject);
        dialog.setCanceledOnTouchOutside(false);

        Button btnYes = findViewById(R.id.buttonYesDeleteSubject);
        Button btnNo = findViewById(R.id.buttonNoDeleteSubject);

        btnYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //database = new database(ActivitySubject.this);
                //Xóa subject trong csdl
                database.DeleteSubject(position);

                //Cập nhật lại activitySubject
                Intent intent = new Intent(  ActivitySubject.this, ActivitySubject.class);
                startActivity(intent);

            }
        });
        //Đóng dialog nếu no
        btnNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        //Show dialog
        dialog.show();

    }
    public void update(final int pos){
        Cursor cursor = database.getDataSubject();

        while (cursor.moveToNext()){
            int id = cursor.getInt(0);

            if (id == pos){
                Intent intent = new Intent(ActivitySubject.this,ActivityUpdateSubject.class);


                String title = cursor.getString(1);
                int credit = cursor.getInt(2);
                String time = cursor.getString(3);
                String place = cursor.getString(4);

                // Gửi dữ liệu qua activity update
                intent.putExtra("id",id);
                intent.putExtra("title",title);
                intent.putExtra("credit",credit);
                intent.putExtra("time",time);
                intent.putExtra("place",place);

                startActivity(intent);

            }
        }
    }


}
