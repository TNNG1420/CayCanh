package com.example.caycanh.Frame;

import static com.example.caycanh.R.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.example.caycanh.Frame.SQLite.GioHangSQLite;
import com.example.caycanh.Frame.ShowInfor.CountNumberProduct;
import com.example.caycanh.databinding.ActivityMainBinding;

import java.util.Objects;

public class Main extends AppCompatActivity {

    ActivityMainBinding binding;
    private TrangChu trangChu;
    private TinTuc tinTuc;
    private ChamSoc chamSoc;
    private LienHe lienHe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);


        trangChu = new TrangChu();
        tinTuc = new TinTuc();
        chamSoc = new ChamSoc();
        lienHe = new LienHe();

        binding = DataBindingUtil.setContentView(this, layout.activity_main);
        setNumber();

        Objects.requireNonNull(getSupportActionBar()).hide();
        getSupportFragmentManager().beginTransaction().add(id.main_frame, trangChu).commit();
        binding.bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case id.trang_chu:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(id.main_frame,trangChu)
                            .addToBackStack(null)
                            .commit();
                    break;
                case id.tin_tuc:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(id.main_frame,tinTuc)
                            .addToBackStack(null)
                            .commit();
                    break;
                case id.cham_soc:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(id.main_frame,chamSoc)
                            .addToBackStack(null)
                            .commit();
                    break;
                case id.lien_he:
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(id.main_frame,lienHe)
                            .addToBackStack(null)
                            .commit();
                    break;
            }
            return true;
        });



        binding.rlIconGh.setOnClickListener(v -> {
            Intent intent = new Intent(Main.this, GioHang.class);
            startActivity(intent);
            System.out.println("hi1");
        });

//        binding.rlIconGh.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(Main.this, GioHang.class);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    public void setNumber() {

        CountNumberProduct countNumberProduct = new CountNumberProduct(findViewById(id.icon_gh));
        GioHangSQLite gioHangSQLite = new GioHangSQLite(getApplicationContext());

        countNumberProduct.setNumberProduct(gioHangSQLite.getItemsCount() );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setNumber();
//        countNumberProduct.setNumberProduct(gioHangSQLite.getItemsCount() );
    }

}