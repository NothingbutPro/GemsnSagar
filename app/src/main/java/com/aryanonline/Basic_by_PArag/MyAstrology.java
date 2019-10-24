package com.aryanonline.Basic_by_PArag;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.aryanonline.Adapter.DetailsAdapter;
import com.aryanonline.AllCateActivity;
import com.aryanonline.HttpHandler;
import com.aryanonline.Model.DetailsModel;
import com.aryanonline.R;
import com.aryanonline.util.DatabaseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAstrology extends AppCompatActivity {
    GridLayout astrogrid;
    TextView textGrid;
    private String server_url;
    RelativeLayout lifront;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_astrology);
        astrogrid = findViewById(R.id.astrogrid);
        textGrid = findViewById(R.id.textGrid);
        lifront = findViewById(R.id.lifront);
        lifront.setFocusable(true);
        //Set Event
        setSingleEvent(astrogrid);

    }

    private void setSingleEvent(GridLayout astrogrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < astrogrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) astrogrid.getChildAt(i);
            final int finalI = i;

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                if(finalI ==0)
                {
                    new PredictMyHoroscope("Aries").execute();
                }else if(finalI ==1)
                {
                    new PredictMyHoroscope("Taurus").execute();
                }else if(finalI ==2)
                {
                    new PredictMyHoroscope("Gemini").execute();
                }else if(finalI ==3)
                {
                    new PredictMyHoroscope("Cancer").execute();
                }else if(finalI ==4)
                {
                    new PredictMyHoroscope("Leo").execute();
                }else if(finalI ==5)
                {
                    new PredictMyHoroscope("Virgo").execute();
                }else if(finalI ==6)
                {
                    new PredictMyHoroscope("Libra").execute();
                }else if(finalI ==7)
                {
                    new PredictMyHoroscope("Scorpio").execute();
                }else if(finalI ==8)
                {
                    new PredictMyHoroscope("Sagittarius").execute();
                }else if(finalI ==9)
                {
                    new PredictMyHoroscope("Capricorn").execute();
                }else if(finalI ==10)
                {
                    new PredictMyHoroscope("Aquarius").execute();
                }else if(finalI ==11)
                {
                    new PredictMyHoroscope("Pisces").execute();
                }

                }
            });
        }
    }

    private class PredictMyHoroscope extends AsyncTask<String, String, String> {
        String output = "";
        ProgressDialog dialog;
        String mySign;
        public PredictMyHoroscope(String mySign) {
            this.mySign = mySign;
        }

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(MyAstrology.this);
            dialog.setMessage("Processing");
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            try {
                server_url = "http://horoscope-api.herokuapp.com/horoscope/today/"+mySign;
            } catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("sever_url>>>>>>>>>", server_url);
            output = HttpHandler.makeServiceCall(server_url);
            //   Log.e("getcomment_url", output);
            System.out.println("getcomment_url" + output);
            return output;
        }

        @Override
        protected void onPostExecute(String output) {
            if (output == null) {
                dialog.dismiss();
            } else {
                try {
                    dialog.dismiss();
                    JSONObject obj = new JSONObject(output);
                    String date = obj.getString("date");
                    String Horoscope = obj.getString("horoscope");
                    textGrid.setText(Horoscope);
                    textGrid.requestFocus();
                    getSupportActionBar().setTitle(" Sign "+mySign);
                    lifront.requestFocus();

                } catch (JSONException e) {
                    e.printStackTrace();
                    //  dialog.dismiss();
                }
                super.onPostExecute(output);
            }
        }
    }
}
