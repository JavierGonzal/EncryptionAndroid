package com.thedeveloperworldisyous.encryptionfile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thedeveloperworldisyous.encryptionfile.utils.AsymmetricAlgorithmRSA;
import com.thedeveloperworldisyous.encryptionfile.utils.Utils;

import java.security.Key;
import java.security.KeyPair;

public class RSAActivity extends AppCompatActivity {

    public TextView mInPutTextView, mEncodedTextView, mDecoded;
    public EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditText = (EditText) findViewById(R.id.activity_rsa_edit_text);
        mInPutTextView = (TextView)findViewById(R.id.activity_rsa_text_view);
        mEncodedTextView  = (TextView)findViewById(R.id.activity_rsa_encode_text_view);
        mDecoded = (TextView)findViewById(R.id.activity_rsa_decode_text_view);

        setSupportActionBar(toolbar);

    }

    public void encodeAndDecodeRSA(View view) {

        Utils.hideKeyboard(this);
        String stringText = mEditText.getText().toString();
        mEditText.setText("");
        mInPutTextView.setText(stringText);

        KeyPair keyPair = AsymmetricAlgorithmRSA.generateKey();
        Key publicKey = keyPair.getPublic();
        Key privateKey = keyPair.getPrivate();


        byte[] encodedBytes = AsymmetricAlgorithmRSA.encryption(privateKey, stringText);
        mEncodedTextView.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT));

        byte[] decodedBytes = AsymmetricAlgorithmRSA.decryption(publicKey, encodedBytes);
        mDecoded.setText(new String(decodedBytes));
    }
    @Override
    public void onBackPressed()
    {
        cameback();
    }

    /**
     * this method was created for animation between activities
     */
    public void cameback()
    {
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
