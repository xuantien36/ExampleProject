package com.t3h.immunization.activity;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import com.t3h.immunization.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity  {
    @BindView(R.id.webview)
    WebView webView;
    @BindView(R.id.back_detail)
    ImageView imBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        String url=intent.getStringExtra("webview");
        webView.loadUrl(url);
        imBack.setOnClickListener(view -> finish());

    }

}
