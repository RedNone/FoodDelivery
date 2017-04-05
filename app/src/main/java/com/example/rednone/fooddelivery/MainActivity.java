package com.example.rednone.fooddelivery;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
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
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener{



    FragmentTransaction fragmentTransaction;


    private static final String TAG = "myLogs";
    private FragmentManager manager;
    private ProgressDialog progressDialog;
    private  getJS intfObj;
    private Retrofit retrofit;
    private Toolbar toolbar;
    private MainFragment mainFragment;
    private BasketFragment basketFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Меню");

        mainFragment = new MainFragment();
        basketFragment = new BasketFragment();


        manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.mainConteiner,mainFragment,mainFragment.TAG);
        transaction.commitNow();


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


        retrofit = new Retrofit.Builder()
                .baseUrl("http://raw.githubusercontent.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        intfObj = retrofit.create(getJS.class);

    }

    @Override
    protected void onStart() {
        super.onStart();
        downloadData();
        progressDialog.show();

    }

    protected  void downloadData()
    {
            mainFragment = (MainFragment) manager.findFragmentByTag(MainFragment.TAG);
            intfObj.getData().enqueue(new Callback<List<DataModel>>() {
                @Override
                public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {


                    Log.d(TAG, "Main Activity: DataCome");
                    Log.d(TAG, "AAAAAdd");
                    if (App.getDataModels().isEmpty()) {
                        App.getDataModels().addAll(response.body());
                    } else {
                        List<DataModel> list = new ArrayList<>();
                        list.addAll(response.body());

                        for (DataModel objList : list) {
                            for (DataModel objData : App.getDataModels()) {
                                if (objList.getId() == objData.getId()) {
                                    objList.setBasket(objData.getBasket());
                                    break;
                                }
                            }
                        }
                        App.setDataModels(list);
                    }


                   mainFragment.getFragmentsCallbacks();
                   mainFragment.setFragmentsData();

                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }

                }

                @Override
                public void onFailure(Call<List<DataModel>> call, Throwable t) {

                    Toast.makeText(MainActivity.this, "Load Error.Please refreshe it", Toast.LENGTH_SHORT).show();
                    Log.d(TAG, "Main Activity: DataError");
                    mainFragment.getFragmentsCallbacks();
                    if (progressDialog.isShowing()) {
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
            toolbar.setTitle("Меню");

            if(manager.findFragmentByTag(MenuFragment.TAG) == null)
            {
                fragmentTransaction.replace(R.id.mainConteiner, mainFragment, mainFragment.TAG);
            }
        } else if (id == R.id.basket) {
            toolbar.setTitle("Корзина");

            if(manager.findFragmentByTag(BasketFragment.TAG) == null)
            {
                fragmentTransaction.replace(R.id.mainConteiner, basketFragment, basketFragment.TAG);
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
