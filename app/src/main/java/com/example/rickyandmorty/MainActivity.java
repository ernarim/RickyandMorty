package com.example.rickyandmorty;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.example.rickyandmorty.Interface.RVDataPass;
import com.example.rickyandmorty.ViewModel.MainActivityViewModel;
import com.example.rickyandmorty.databinding.ActivityMainBinding;
import com.example.rickyandmorty.retrofit.ApiUtils;
import com.example.rickyandmorty.retrofit.CharactersDAOInterface;
import com.example.rickyandmorty.retrofit.LocationsDAOInterface;

public class MainActivity extends AppCompatActivity {
    private LocationsDAOInterface locationsDAOInterface;
    private CharactersDAOInterface charactersDAOInterface;

    private ActivityMainBinding dataBinder;
    private MainActivityViewModel viewModel;

    private RecyclerView horizontalRV;
    private RecyclerView verticalRV;
    private  NestedScrollView nestedScrollView;
    private Context context;

    private String characterIds;

    private int page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinder = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);


        locationsDAOInterface = ApiUtils.getLocationsDAOInterface();
        charactersDAOInterface = ApiUtils.getCharactersDAOInterface();

        horizontalRV = dataBinder.horizontalRV;
        horizontalRV.setHasFixedSize(true);
        horizontalRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));

        verticalRV = dataBinder.verticalRV;
        verticalRV.setHasFixedSize(true);
        verticalRV.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

        RVDataPass rvDataPass = new RVDataPass() {
            @Override
            public void passData(String data) {
                characterIds = data;
                Log.e("TAG_X", "characterIdsss: " + characterIds);
                viewModel.getCharacters(verticalRV, characterIds, context);
            }
        };



        viewModel.allLocations(horizontalRV, context, rvDataPass, page);

        /*LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        horizontalRV.setLayoutManager(manager);

        nestedScrollView = dataBinder.nestedSV;

        nestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener(){
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int maxScrollx = nestedScrollView.getChildAt(0).getMeasuredWidth() - v.getMeasuredWidth();
                Log.e("TAG_X", "maxScrollx: " + maxScrollx);
                Log.e("TAG_X", "scrollX: " + scrollX);
                if (scrollX == maxScrollx) {


                }
            }
        });*/








    }


}