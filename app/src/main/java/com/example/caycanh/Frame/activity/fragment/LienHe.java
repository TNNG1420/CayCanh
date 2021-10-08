package com.example.caycanh.Frame.activity.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import androidx.databinding.DataBindingUtil;

import com.example.caycanh.R;
import com.example.caycanh.databinding.FragmentLienHeBinding;

public class LienHe extends Fragment {

    FragmentLienHeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_lien_he, container, false);
        View view = binding.getRoot();
        SetEven();
        return view;
    }

    private void SetEven() {
        binding.callHanoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("");
                builder.setMessage("Bạn có muốn gọi cho chi nhánh Hà Nội?");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //call
                        Intent callIntent=new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+"0396172418" ));//đổi sdt
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE)
                                    != PackageManager.PERMISSION_GRANTED)
                            {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                            }
                            else
                            {
                                startActivity(callIntent);
                            }
                        }
                        else
                        {
                            startActivity(callIntent);
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });


        binding.callHcm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("");
                builder.setMessage("Bạn có muốn gọi cho chi nhánh Hồ Chí Minh?");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //call
                        Intent callIntent=new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+"0396172418" ));//đổi sdt
                        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
                            {
                                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE},1);
                            }
                            else
                            {
                                startActivity(callIntent);
                            }
                        }
                        else
                        {
                            startActivity(callIntent);
                        }
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();
            }
        });

        binding.website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("");
                builder.setMessage("Bạn có muốn truy cập website https://webcaycanh.com");
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //call
                Intent website = new Intent(Intent.ACTION_VIEW,Uri.parse("https://webcaycanh.com"));
                startActivity(website);
                    }
                });
                Dialog dialog = builder.create();
                dialog.show();

            }
        });
    }
}