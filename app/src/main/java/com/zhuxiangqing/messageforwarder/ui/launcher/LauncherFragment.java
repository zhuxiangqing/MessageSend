package com.zhuxiangqing.messageforwarder.ui.launcher;

import android.Manifest;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhuxiangqing.messageforwarder.R;
import com.zhuxiangqing.messageforwarder.base.BaseFragment;
import com.zhuxiangqing.messageforwarder.di.Injectable;
import com.zhuxiangqing.messageforwarder.ui.main.MainActivity;

import java.util.List;

import javax.inject.Inject;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by zhuxi on 2017/12/25.
 *
 */

public class LauncherFragment extends BaseFragment
        implements Injectable, LauncherNavigator,
        EasyPermissions.PermissionCallbacks {

    private static final int RC_SMS_PERM = 123;
    @Inject
    ViewModelProvider.Factory factory;

    private ImageView ivLogo;



    private String[] permissions = {Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_PHONE_STATE};

    public static LauncherFragment create() {
        return new LauncherFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_launcher, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivLogo = view.findViewById(R.id.iv_logo);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       ivLogo.postDelayed(new Runnable() {
           @Override
           public void run() {
               smsPermissions();
           }
       },1000);
    }

    void smsPermissions() {
        if (hasSMSPermission()) {
            toMainActivity();
        } else {
            requestPermission();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @Override
    public void requestPermission() {
        EasyPermissions.requestPermissions(this, "本应用需要使用接收短信权限才能使用！", RC_SMS_PERM, permissions);
    }

    @Override
    public void toMainActivity() {
        Intent intent = new Intent(getContext(), MainActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private boolean hasSMSPermission() {
        return EasyPermissions.hasPermissions(getContext(), permissions);
    }

    @Override
    public void onPermissionsGranted(int requestCode, @NonNull List<String> perms) {
        smsPermissions();
    }

    @Override
    public void onPermissionsDenied(int requestCode, @NonNull List<String> perms) {
        getActivity().finish();
    }
}
