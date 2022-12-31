package farrel.tugasakhir.apkmenumakanan.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import farrel.tugasakhir.apkmenumakanan.R;

import farrel.tugasakhir.apkmenumakanan.MainActivity;

public class LoginActivity extends AppCompatActivity {
    TextView txtRegister;
    EditText edtEmail, edtPassword;
    Button  btnLogin;
    SharedPreferences sharedPref;

    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;

    public static String KEY_USER = "nama_user";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        txtRegister = findViewById(R.id.txtRegister);
        btnLogin    = findViewById(R.id.btnLogin);
        sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        String namaUser = sharedPref.getString(LoginActivity.KEY_USER, null);
        if(namaUser != null){
            startActivity(new Intent(this, MainActivity.class));
        }
        txtRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(it);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosesLogin();
            }
        });
    }

    private void prosesLogin() {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if(email.isEmpty()){
            edtEmail.setError("Data tidak boleh kosong");
            edtEmail.requestFocus();
        }else if(password.isEmpty()){
            edtPassword.setError("Data tidak boleh kosong");
            edtPassword.requestFocus();
        }else {
            firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        SharedPreferences.Editor editor = sharedPref.edit();
                        editor.putString(KEY_USER, email);
                        editor.commit();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "Username / Password salah", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}