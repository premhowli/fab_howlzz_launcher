
package com.howlzzz.fab;

import android.app.Activity;
import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Launcher extends Activity {
    private PackageManager manager;
    private List<AppDetail> apps;
    private static int count;
    private void loadApps(){
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities){
            AppDetail app = new AppDetail();
            if(ri.activityInfo.packageName.toString().contains("dialer") || ri.activityInfo.packageName.toString().contains("whatsapp")|| ri.activityInfo.packageName.toString().contains("camera")){
                count++;
                app.label = ri.loadLabel(manager);
                app.name=ri.activityInfo.packageName;
                app.icon = ri.activityInfo.loadIcon(manager);
                apps.add(app);
            }
            else{
                if(count==3) {
                    count++;
                    app.label = ri.loadLabel(manager);
                    app.name = ri.activityInfo.packageName;
                    app.icon = ri.activityInfo.loadIcon(manager);
                    apps.add(app);
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher2);
        ImageView a=(ImageView)findViewById(R.id.item_app_icon);
        ImageView b=(ImageView)findViewById(R.id.item_app_icon1);
        ImageView c=(ImageView)findViewById(R.id.item_app_icon2);
        ImageView d=(ImageView)findViewById(R.id.item_app_icon3);
loadApps();
        a.setImageDrawable(apps.get(0).icon);
        b.setImageDrawable(apps.get(1).icon);
        c.setImageDrawable(apps.get(2).icon);
        d.setImageDrawable(apps.get(3).icon);
//loading icons
        /*Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            *//*if(ri.loadLabel(manager).toString().contains("Phone")){
                //a.setImageDrawable(ri.activityInfo.loadIcon(manager));
            }*//*
            //app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }
*/
    }
    private void loadApps(View v){
        manager = getPackageManager();
        apps = new ArrayList<AppDetail>();

        Intent i = new Intent(Intent.ACTION_MAIN, null);
        i.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> availableActivities = manager.queryIntentActivities(i, 0);
        for(ResolveInfo ri:availableActivities) {
            AppDetail app = new AppDetail();
            app.label = ri.loadLabel(manager);
            if(app.label.toString().contains("Phone")){
                ImageView appIcon=(ImageView)v.findViewById(R.id.item_app_icon);
                appIcon.setImageDrawable(ri.activityInfo.loadIcon(manager));
            }
            //app.name = ri.activityInfo.packageName;
            app.icon = ri.activityInfo.loadIcon(manager);
            apps.add(app);
        }

    }
    public void showApps(View v){
        Intent i=new Intent(this,HomeScreen.class);
        startActivity(i);
    }

    public void addClickListener(View view) {
        Intent i = manager.getLaunchIntentForPackage(apps.get(0).name.toString());
        startActivity(i);

    }
    public void addClickListener1(View view) {
        Intent i = manager.getLaunchIntentForPackage(apps.get(1).name.toString());
        startActivity(i);

    }
    public void addClickListener2(View view) {
        Intent i = manager.getLaunchIntentForPackage(apps.get(2).name.toString());
        startActivity(i);

    }
    public void addClickListener3(View view) {
        Intent i = manager.getLaunchIntentForPackage(apps.get(3).name.toString());
        startActivity(i);

    }
}
