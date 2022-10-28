package com.example.tubes;

import android.os.Bundle;
import android.widget.ListView;

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
    TambahDokterFragment tambahDokterFragment;

    ListView listView;

    FragmentManager fragmentManager;

    private boolean isBackPressedOnce = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.tambahDokterFragment = TambahDokterFragment.newInstance("Tambah Dokter Fragment");
        this.dokterFragment = DokterFragment.newInstance("Dokter Fragment");
        this.leftFragment = LeftFragment.newInstance("Left Fragment");
        this.pertemuanFragment = PertemuanFragment.newInstance("Pertemuan Fragment");
        this.homeFragment = HomeFragment.newInstance("Home Fragment");

        fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();
        ft.add(binding.fragmentContainer.getId(), homeFragment)
                .commit();
        Toolbar toolbar = findViewById(R.id.toolbar);
        this.setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);

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
        } else{
            super.onBackPressed();
        }


        if(pertemuanFragment.isInLayout()){
            System.out.println(fragmentManager.getBackStackEntryCount());
        }
//        if (isBackPressedOnce){
//            super.onBackPressed();
//            return;
//        }
//        Toast.makeText(this,"Press once again to exit!", Toast.LENGTH_SHORT).show();
//        isBackPressedOnce = true;
//
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                isBackPressedOnce = false;
//            }
//        }, 2000);

    }

    public void changePage (int page) {
        FragmentTransaction ft = fragmentManager.beginTransaction();
        if(page == 1){
            if(this.homeFragment.isAdded()){
                ft.show(homeFragment);
            }else{
                ft.add(binding.fragmentContainer.getId(),this.homeFragment).addToBackStack(null);
            }
            if(this.dokterFragment.isAdded()){
                ft.hide(this.dokterFragment);
            }if(this.pertemuanFragment.isAdded()) {
                ft.hide(this.pertemuanFragment);
            }
        }else if(page == 2){
            if(this.dokterFragment.isAdded()) {
                ft.show(dokterFragment);
            }else{
                ft.add(binding.fragmentContainer.getId(),this.dokterFragment).addToBackStack(null);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }if(this.pertemuanFragment.isAdded()) {
                ft.hide(this.pertemuanFragment);
            }
        }else if(page == 3){
            if(this.pertemuanFragment.isAdded()) {
                ft.show(pertemuanFragment);
            }else{
                ft.add(binding.fragmentContainer.getId(),this.pertemuanFragment).addToBackStack(null);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }if(this.dokterFragment.isAdded()) {
                ft.hide(this.dokterFragment);
            }
        }else if(page == 4){
            if(this.tambahDokterFragment.isAdded()) {
                ft.show(tambahDokterFragment);
            }else{
                ft.add(binding.fragmentContainer.getId(),this.tambahDokterFragment).addToBackStack(null);
            }
            if(this.homeFragment.isAdded()){
                ft.hide(this.homeFragment);
            }
            if(this.dokterFragment.isAdded()) {
                ft.hide(this.dokterFragment);
            }
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