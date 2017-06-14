package com.example.elsayedfahmy_pc.myweather_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this,SettingActivity.class));

        }
        if(id==R.id.action_Refresh)
        {
            FetchWeatherTask f = new FetchWeatherTask();
            try {
                String[] data =  f.execute().get();
                MainActivityFragment.adapter.clear();
                for (String res : data) {
                    MainActivityFragment.adapter.add(res);
                }

                //arrayAdapter.notifyDataSetChanged();

            } catch (InterruptedException e) {
                Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
                e.printStackTrace();
            } catch (ExecutionException e) {
                Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
                e.printStackTrace();
            } catch (Exception e) {
                Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
                e.printStackTrace();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public boolean Print()
    {
        FetchWeatherTask f = new FetchWeatherTask();
        try {
            String[] data =  f.execute().get();
          MainActivityFragment.adapter.clear();
            for (String res : data) {
                MainActivityFragment.adapter.add(res);
            }

            //arrayAdapter.notifyDataSetChanged();

        } catch (InterruptedException e) {
            Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
            e.printStackTrace();
        } catch (ExecutionException e) {
            Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
            e.printStackTrace();
        } catch (Exception e) {
            Log.e(MainActivityFragment.class.getSimpleName(), e.toString());
            e.printStackTrace();
        }

        return true;
    }
}
