package farrel.tugasakhir.apkmenumakanan.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import farrel.tugasakhir.apkmenumakanan.R;

public class RegisterActivity extends AppCompatActivity {
    EditText edtEmailreg, edtPasswordreg, edtPasswordconfreg;
    Button btnRegister;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtEmailreg = findViewById(R.id.edtEmailreg);
        edtPasswordreg = findViewById(R.id.edtPasswordreg);
        edtPasswordconfreg = findViewById(R.id.edtPasswordConfreg);
        btnRegister    = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesRegister();
            }
        });
    }

    private void prosesRegister() {
        String email = edtEmailreg.getText().toString();
        String password = edtPasswordreg.getText().toString();
        String passwordConf = edtPasswordconfreg.getText().toString();

        if(email.isEmpty()){
            edtEmailreg.setError("Data tidak boleh kosong");
            edtEmailreg.requestFocus();
        }else if(password.isEmpty()){
            edtPasswordreg.setError("Data tidak boleh kosong");
            edtPasswordreg.requestFocus();
        }else if(passwordConf.isEmpty()){
            edtPasswordconfreg.setError("Data tidak boleh kosong");
            edtPasswordconfreg.requestFocus();
        }else {
            if (password.equals(passwordConf)) {
                firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(RegisterActivity.this, "Berhasil Register", Toast.LENGTH_SHORT).show();
                            Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(login);
                            finish();
                        }
                    }
                });
            }else{
                Toast.makeText(this, "Password tidak sama!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}