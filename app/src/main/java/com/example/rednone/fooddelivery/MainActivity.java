package com.example.rednone.fooddelivery;

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
    MenuAdapter menuAdapter;
    FragmentTransaction fragmentTransaction;


    private static final String TAG = "myLogs";
    MenuFragment menuFragment = null;
    BasketFragment basketFragment = null;
    FragmentManager manager;

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






        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        dataModels = new ArrayList<>();


        App.setMenuAdapter(new MenuAdapter(dataModels));
        App.getIntfObj().getData().enqueue(new Callback<List<DataModel>>() {
            @Override
            public void onResponse(Call<List<DataModel>> call, Response<List<DataModel>> response) {
                Log.d(TAG, "Main Activity: DataCome");
                dataModels.addAll(response.body());


                List<DbModel> appList = DbModel.listAll(DbModel.class);;
                DbModel dbModel;
                if(appList.isEmpty())
                {
                    for (DataModel obj : dataModels) {
                        dbModel = new DbModel((obj.getId()), (obj.getCost()), (obj.getName()), (obj.getVersion()));
                        dbModel.save();
                    }
                    //добавить return и adapter


                    App.getMenuAdapter().notifyDataSetChanged();


                    return;

                }

                DataModel dataModel = dataModels.get(0);
                if(dataModel.getVersion() == appList.get(0).getVersion())
                {
                    Log.d(TAG, "Main Activity: DataError2");
                   App.getMenuAdapter().notifyDataSetChanged();




                }
                else
                {
                    for (DataModel obj : dataModels) {
                        dbModel = new DbModel((obj.getId()), (obj.getCost()), (obj.getName()), (obj.getVersion()));
                        dbModel.save();
                    }
                    Log.d(TAG, "Main Activity: DataError3");

                   App.getMenuAdapter().notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<List<DataModel>> call, Throwable t) {

                Toast.makeText(MainActivity.this, "Load Error", Toast.LENGTH_SHORT).show();
                Log.d(TAG, "Main Activity: DataError");
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

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }*/

  /*  @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/
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
        fragmentTransaction.commitNow();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
