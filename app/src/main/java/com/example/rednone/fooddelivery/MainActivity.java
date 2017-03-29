package com.example.rednone.fooddelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    List<DataModel> dataModels;
    FragmentTransaction fragmentTransaction;


    private static final String TAG = "myLogs";
    private MenuFragment menuFragment = null;
    BasketFragment basketFragment = null;
    FragmentManager manager;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        menuFragment = new MenuFragment();
        basketFragment = new BasketFragment();
        manager = getSupportFragmentManager();

        fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.mainContainer, menuFragment, menuFragment.TAG);
        fragmentTransaction.commitNow();

        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(false);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dataModels = new ArrayList<>();
        App.setMenuAdapter(new MenuAdapter(dataModels));
        downloadData();
        progressDialog.show();



    }

    protected  void downloadData()
    {
        if(!dataModels.isEmpty()){dataModels.clear();}

        menuFragment = (MenuFragment) manager.findFragmentByTag(MenuFragment.TAG);

        App.getIntfObj().getData().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                Log.d(TAG, "Main Activity: DataCome");
                dataModels.addAll(response.body());
                App.getMenuAdapter().notifyDataSetChanged();
                menuFragment.callBack();
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }

            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Load Error.Please refreshe it", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Main Activity: DataError");
                menuFragment.callBack();
                if(progressDialog.isShowing())
                {
                    progressDialog.dismiss();
                }

            }
        });

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        fragmentTransaction = manager.beginTransaction();
        int id = item.getItemId();

        if (id == R.id.drawerMenu) {
            if(manager.findFragmentByTag(MenuFragment.TAG) == null)
            {
                fragmentTransaction.replace(R.id.mainContainer, menuFragment, menuFragment.TAG);
            }
        } else if (id == R.id.basket) {

            if(manager.findFragmentByTag(BasketFragment.TAG) == null)
            {
                fragmentTransaction.replace(R.id.mainContainer, basketFragment, basketFragment.TAG);
            }

        } else if (id == R.id.help) {

        }
        else if (id == R.id.logout) {
            new DbModelUser().deleteAll(DbModelUser.class);
            startActivity(new Intent(this,LoginActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP));
            finish();
        }
        fragmentTransaction.commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
