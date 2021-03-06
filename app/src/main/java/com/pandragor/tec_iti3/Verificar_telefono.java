package com.pandragor.tec_iti3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Verificar_telefono extends AppCompatActivity {

    private EditText etxtPhone;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private EditText etxtPhoneCode;
    private String mVerificationId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verificar_telefono);
        etxtPhone = (EditText) findViewById(R.id.etxtPhone);
        etxtPhoneCode = (EditText) findViewById(R.id.etxtPhoneCode);


        mAuth=FirebaseAuth.getInstance();
        mAuthListener= new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                Intent intent = new Intent(Verificar_telefono.this, WELCOMe.class);
                startActivity(intent);
                finish();
            }
        };
    }
    public void requestCode(View view) {
        String phoneNumber = etxtPhone.getText().toString();
        if (TextUtils.isEmpty(phoneNumber))
            return;
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                phoneNumber, 60, TimeUnit.SECONDS, Verificar_telefono.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                    @Override
                    public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                        //Called if it is not needed to enter verification code
                        signInWithCredential(phoneAuthCredential);
                    }

                    @Override
                    public void onVerificationFailed(FirebaseException e) {
                        //incorrect phone number, verification code, emulator, etc.
                        Toast.makeText(Verificar_telefono.this, "Verificaci??n de identificaci??n fallida " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCodeSent(String verificationId, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                        //now the code has been sent, save the verificationId we may need it

                        super.onCodeSent(verificationId, forceResendingToken);

                        mVerificationId = verificationId;

                    }

                    @Override
                    public void onCodeAutoRetrievalTimeOut(String verificationId) {
                        //called after timeout if onVerificationCompleted has not been called
                        super.onCodeAutoRetrievalTimeOut(verificationId);
                        Toast.makeText(Verificar_telefono.this, "", Toast.LENGTH_SHORT).show();
                    }
                }
        );

    }

    private void signInWithCredential(PhoneAuthCredential phoneAuthCredential) {

        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Verificar_telefono.this, "VERIFICACION EXITOSA", Toast.LENGTH_SHORT).show();

                        } else {
                            Toast.makeText(Verificar_telefono.this, "VERIFICACION NO EXITOSA", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void signIn(View view) {

        String code = etxtPhoneCode.getText().toString();
        if (TextUtils.isEmpty(code))
            return;
        signInWithCredential(PhoneAuthProvider.getCredential(mVerificationId, code));
        Intent intent=new Intent(Verificar_telefono.this,WELCOMe.class);

        startActivity(intent);

    }

}
