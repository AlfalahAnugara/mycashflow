package org.aplas.buku_kas_nusantara;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.aplas.buku_kas_nusantara.Database.DBHelper;

public class RegisterActivity extends AppCompatActivity {

    EditText idUsernameRegister, idPasswordRegister, idPassword2Register ;
    Button buttonRegister, buttonBack ;
    DBHelper DB ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        idUsernameRegister = (EditText) findViewById(R.id.idUsernameRegister);
        idPasswordRegister = (EditText) findViewById(R.id.idPasswordRegister);
        idPassword2Register = (EditText) findViewById(R.id.idPassword2Register);
        buttonBack = (Button) findViewById(R.id.buttonBack);
        buttonRegister = (Button) findViewById(R.id.buttonRegister);
        DB = new DBHelper(this);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = idUsernameRegister.getText().toString();
                String pass = idUsernameRegister.getText().toString();
                String repass = idPassword2Register.getText().toString();

                if (user.equals("")||pass.equals("")||repass.equals(""))
                    Toast.makeText(RegisterActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser = false) {
                            Boolean insert = DB.insertData(user, pass);
                            if (insert == true) {
                                Toast.makeText(RegisterActivity.this, "Berhasil Mendaftar", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(RegisterActivity.this, "Daftar Gagal", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                        Toast.makeText(RegisterActivity.this, "User Sudah ada", Toast.LENGTH_SHORT).show();
                    }
                } else {
                        Toast.makeText(RegisterActivity.this, "Password Tidak Sama", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }


}