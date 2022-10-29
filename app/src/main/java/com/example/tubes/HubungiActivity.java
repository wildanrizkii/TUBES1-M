package com.example.tubes;

import static android.content.Intent.ACTION_DIAL;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HubungiActivity extends AppCompatActivity {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = new Bundle();

        Uri uri = Uri.parse("tel:089646436360");
        Intent intent = new Intent(ACTION_DIAL, uri);
        startActivity(intent);

    }
}
