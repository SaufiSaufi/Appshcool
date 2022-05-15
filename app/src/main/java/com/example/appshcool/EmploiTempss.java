package com.example.appshcool;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.URLUtil;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class EmploiTempss extends AppCompatActivity {

    private ImageView dowlond;
    String getUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emploi_tempss);
        dowlond =findViewById(R.id.download);
        getUrl= "https://firebasestorage.googleapis.com/v0/b/newmind-f5d72.appspot.com/o/S2-Sem11-2022.pdf?alt=media&token=466213a1-1af7-44b3-8a84-a3e6a7f37e49" ;
        dowlond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(getUrl));
                String title = URLUtil.guessFileName(getUrl,null,null);
                request.setTitle(title);
                request.setDescription("Dowloading file please .........");
                String cookies = CookieManager.getInstance().getCookie(getUrl);
                request.addRequestHeader("cookie",cookies);
                request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,title);
                DownloadManager downloadManager=(DownloadManager) getSystemService(DOWNLOAD_SERVICE);
                downloadManager.enqueue(request);
                Toast.makeText(EmploiTempss.this, "Dowloading started", Toast.LENGTH_SHORT).show();
            }
        });


        // After Clicking on this we will be
        // redirected to choose pdf


    }

        }

