package com.gh.ewmdemo;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xys.libzxing.zxing.activity.CaptureActivity;
import com.xys.libzxing.zxing.encoding.EncodingUtils;

public class MainActivity extends AppCompatActivity {
    private TextView mTextView;
    private EditText mEditText;
    private ImageView mImageView;
    private CheckBox mCheckBox;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        mTextView = (TextView) this.findViewById(R.id.tv_showResult);
        mEditText = (EditText) this.findViewById(R.id.et_text);
        mImageView = (ImageView) this.findViewById(R.id.img_shouw);
        mCheckBox = (CheckBox) this.findViewById(R.id.cb_logo);
    }

    public void scan(View view) {

        startActivityForResult(new Intent(this, CaptureActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Bundle bundle = data.getExtras();
            if (bundle != null) {
                String result = bundle.getString("result");
                mTextView.setText(result);
            }
        }

    }

    //鐢熸垚浜岀淮鐮?鍙互璁剧疆Logo
    public void make(View view) {

        String input = mEditText.getText().toString();
        if (input.equals("")) {
            Toast.makeText(this, "杈撳叆涓嶈兘涓虹┖", Toast.LENGTH_SHORT).show();
        } else {
            Bitmap qrCode = EncodingUtils.createQRCode(input, 500, 500,
                    mCheckBox.isChecked() ? BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher) : null);
            mImageView.setImageBitmap(qrCode);
        }
    }


}
