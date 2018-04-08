package study.com.progressbarandseekbardemo;

import android.app.Dialog;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvStart;
    private TextView tvEnd;

    private Dialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvStart = (TextView) findViewById(R.id.tv_start);
        tvStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCustomProgressDialog();
            }
        });

        tvEnd = (TextView) findViewById(R.id.tv_end);
        tvEnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopCustomProgressDialog();
            }
        });
    }

    private void showCustomProgressDialog() {
        showCustomProgressDialog("加载中...");
    }

    private void showCustomProgressDialog(String msg) {
        if (progressDialog == null) {
            progressDialog = new Dialog(this, R.style.ProgressDialogStyle);
        }
        progressDialog.setContentView(R.layout.dialog_custom_loading);
        progressDialog.setCancelable(true);
        progressDialog.getWindow().setBackgroundDrawableResource(
                android.R.color.transparent);

        ProgressBar progressBar = (ProgressBar) progressDialog.findViewById(R.id.loading_progress);
        //适配Android6.0及以上系统
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            Drawable drawable = getApplicationContext().getResources().getDrawable(R.drawable.progress_loading_v23);
            progressBar.setIndeterminateDrawable(drawable);
        }

        TextView text = (TextView) progressDialog
                .findViewById(R.id.tv_loading_msg);
        text.setText(msg);
        progressDialog.show();
    }

    private void stopCustomProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }
}
