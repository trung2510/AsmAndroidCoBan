package polytechnic.fpt.com.qlsv_assignment1.view;

import android.app.Dialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import polytechnic.fpt.com.qlsv_assignment1.R;
import polytechnic.fpt.com.qlsv_assignment1.data.ClassDAO;
import polytechnic.fpt.com.qlsv_assignment1.data.DbHelper;
import polytechnic.fpt.com.qlsv_assignment1.model.Class_model;

public class MainActivity extends AppCompatActivity {
    private Button btnAddClass;
    private Button btnShowListClass;
    private Button btnQlsv;
    private Button btnClearForm;
    private Button btnSaveForm;
    private EditText edtClassName;
    private EditText edtClassKey;
    private Dialog dialog;
    private ClassDAO classDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addWidget();
        final DbHelper dbHelper = new DbHelper(this);
        classDAO = new ClassDAO(this);
        btnAddClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(classDAO);
            }
        });
        btnShowListClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(MainActivity.this , List_Class.class);
                startActivity(in);
            }
        });
        btnQlsv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List_Student.class);
                startActivity(intent);
            }
        });
    }

    public void addWidget(){
        btnAddClass = (Button) findViewById(R.id.btn_addClass);
        btnShowListClass = (Button) findViewById(R.id.btn_viewlist_class);
        btnQlsv = (Button) findViewById(R.id.btn_QLSV);
    }
     public void showDialog(final ClassDAO classDAO){
        dialog = new Dialog(MainActivity.this);
        dialog.setCancelable(true);
        dialog.setTitle("THÊM LỚP");
        dialog.setContentView(R.layout.dialog_add_class);
        dialog.show();
        btnClearForm = (Button) dialog.findViewById(R.id.btn_dialog_clear);
        btnSaveForm = (Button) dialog.findViewById(R.id.btn_dialog_save);
         edtClassKey = (EditText) dialog.findViewById(R.id.edt_key_class);
         edtClassName = (EditText) dialog.findViewById(R.id.edt_name_class);
         btnSaveForm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 String keyClass = edtClassKey.getText().toString();
                 String NameClass = edtClassName.getText().toString();
                 if (TextUtils.isEmpty(keyClass)){
                     Toast.makeText(MainActivity.this, "Mã Lớp không để trống", Toast.LENGTH_SHORT).show();
                     dialog.setCancelable(true);
                 }else if (TextUtils.isEmpty(NameClass)){
                     Toast.makeText(MainActivity.this, "Tên Lớp Không Được trống ", Toast.LENGTH_SHORT).show();
                     dialog.setCancelable(true);
                 }else {
                     Class_model classModel = new Class_model(keyClass,NameClass);
                     classDAO.addClass(classModel);
                     Toast.makeText(MainActivity.this, "Tạo Class Mới OK", Toast.LENGTH_SHORT).show();
                     dialog.dismiss();
                 }
             }
         });
         btnClearForm.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                edtClassKey.setText("");
                edtClassName.setText("");
             }
         });

     }
     public Class_model createClass(){
        String keyClass = edtClassKey.getText().toString();
        String NameClass = edtClassName.getText().toString();
        Class_model classModel = new Class_model(keyClass,NameClass);
        return classModel;
     }
}
