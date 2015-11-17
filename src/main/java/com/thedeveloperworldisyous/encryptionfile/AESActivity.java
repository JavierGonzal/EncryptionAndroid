package com.thedeveloperworldisyous.encryptionfile;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.thedeveloperworldisyous.encryptionfile.utils.SymmetricAlgorithmAES;
import com.thedeveloperworldisyous.encryptionfile.utils.Utils;

import javax.crypto.spec.SecretKeySpec;

public class AESActivity extends AppCompatActivity {

    public TextView mInPutTextView, mEncodedTextView, mDecoded;
    public EditText mEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        mEditText = (EditText) findViewById(R.id.activity_aes_edit_text);
        mInPutTextView = (TextView)findViewById(R.id.activity_aes_text_view);
        mEncodedTextView  = (TextView)findViewById(R.id.activity_aes_encode_text_view);
        mDecoded = (TextView)findViewById(R.id.activity_aes_decode_text_view);


        setSupportActionBar(toolbar);
    }

    public void encodeAndDecodeAES(View view) {
        Utils.hideKeyboard(this);
        String stringText = mEditText.getText().toString();
        mEditText.setText("");
        mInPutTextView.setText(stringText);
        SecretKeySpec secretKeySpec = SymmetricAlgorithmAES.setUpSecrectKey();

        byte[] encodedBytes = SymmetricAlgorithmAES.encryption(secretKeySpec, stringText);
        mEncodedTextView.setText(Base64.encodeToString(encodedBytes, Base64.DEFAULT));

        byte[] decodedBytes = SymmetricAlgorithmAES.decryption(secretKeySpec, encodedBytes);
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
