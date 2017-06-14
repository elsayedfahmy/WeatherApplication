package com.example.elsayedfahmy_pc.myweather_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
   static ArrayAdapter<String> adapter = null;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        String[] data =
                {
                        "Mon   - Sunny    - 88/63",
                        "Tue   - Foggy    - 70/50",
                        "Weds  - Cloudy   - 72/63",
                        "Thurs - Raining  - 50/30",
                        "Fri   - Raining  - 40/25",
                        "Sat   - Sunny    - 31/17",
                        "Sun   - Foggy    - 21/10"
                };
        List<String> Weather_list_Data = new ArrayList<String>(
                Arrays.asList(data));
        adapter = new ArrayAdapter<String>(getActivity(), R.layout.listview_forecast, R.id.list_item_forecast_textview, Weather_list_Data);
        ListView Weather_list = (ListView) rootView.findViewById(R.id.listview_forecast);
        Weather_list.setAdapter(adapter);

        Weather_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String value = adapter.getItem(i);
                Intent intent = new Intent(MainActivityFragment.super.getActivity(), Details.class);
                intent.putExtra("C", value);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return super.onOptionsItemSelected(item);
    }

    public boolean Print()
    {
        FetchWeatherTask f = new FetchWeatherTask();
        try {
            String[] data =  f.execute().get();
            adapter.clear();
            for (String res : data) {
                adapter.add(res);
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

