package com.example.tubes;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.tubes.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DrawerLayout drawer;
    HomeFragment homeFragment;
    DokterFragment dokterFragment;
    LeftFragment leftFragment;
    PertemuanFragment pertemuanFragment;
    FragmentTransaction ft;
    ListView listView;

    FragmentManager fragmentManager;

    private boolean isBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        this.dokterFragment = DokterFragment.newInstance("Dokter Fragment");
        this.leftFragment = LeftFragment.newInstance("Left Fragment");
        this.pertemuanFragment = PertemuanFragment.newInstance("Pertemuan Fragment");
        this.homeFragment = HomeFragment.newInstance("Home Fragment");

        fragmentManager = getSupportFragmentManager();

        ft = fragmentManager.beginTransaction();
        ft.add(binding.fragmentContainer.getId(), homeFragment,"Main")
                .setReorderingAllowed(true)
                .commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        drawer = binding.drawerLayout;
        this.getSupportFragmentManager().setFragmentResultListener(
                "changePage", this, new FragmentResultListener() {
                    @Override
                    public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                        int page = result.getInt("page");
                        changePage(page);
                    }
                });
    }


    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen((GravityCompat.START))){
            drawer.closeDrawer(GravityCompat.START);
        }else if (getSupportFragmentManager().getBackStackEntryCount() > 0){
            getSupportFragmentManager().popBackStack();
        }else if(isBackPressedOnce){
            super.onBackPressed();
        }
        else{
            Toast.makeText(this,"Press once again to exit!", Toast.LENGTH_SHORT).show();
            isBackPressedOnce = true;
            new Handler().postDelayed(() -> isBackPressedOnce = false, 1000);
        }
    }

    public void changePage (int page) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(page == 1){
            ft.replace(binding.fragmentContainer.getId(),this.homeFragment).addToBackStack(null).setReorderingAllowed(true);
        }else if(page == 2){
            ft.replace(binding.fragmentContainer.getId(),this.dokterFragment).addToBackStack(null).setReorderingAllowed(true);
        }else if(page == 3){
            ft.replace(binding.fragmentContainer.getId(),this.pertemuanFragment).addToBackStack(null).setReorderingAllowed(true);
        }else{
            closeApplication();
        }
        ft.commit();
        this.drawer.closeDrawers();
    }

    public void closeApplication(){
        this.moveTaskToBack(true);
        this.finish();
    }
}