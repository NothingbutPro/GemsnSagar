package com.aryanonline.Basic_by_PArag;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.aryanonline.Config.BaseURL;
import com.aryanonline.Model.Product_model;
import com.aryanonline.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

import javax.net.ssl.HttpsURLConnection;

public class SerachFurtherActivity extends AppCompatActivity {
    AutoCompleteTextView searchauto;
    RecyclerView searcprorec;
//    public List<Pro> JwellandGemsList = new ArrayList<>();
    ArrayList<String> List_by_city_consultant = new ArrayList<>();
    private GemsandSagarAdapter mAdapter;
    private List<Product_model> JwellandGemsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serach_further);
        searchauto = findViewById(R.id.searchauto);
        searcprorec = findViewById(R.id.searcprorec);
        searchauto.clearListSelection();
        searchauto.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                new SearchMyProduct(s.toString()).execute();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                new SearchMyProduct(s.toString()).execute();
            }

            @Override
            public void afterTextChanged(Editable s) {
                new SearchMyProduct(s.toString()).execute();
            }
        });
    }


    public class SearchMyProduct extends AsyncTask<String, Void, String> {
        String productname;

        public SearchMyProduct(String productname) {
            this.productname = productname;
        }

        protected void onPreExecute() {
            //  dialog = new ProgressDialog(AccountFormActivity.this);
            //  dialog.show();

        }

        @Override
        protected String doInBackground(String... strings) {

            try {

                URL url = new URL("http://enlightshopping.com/api/api/searchProduct?name="+productname);


                JSONObject postDataParams = new JSONObject();
//                postDataParams.put("city", productname);

                Log.e("postDataParams", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000  /*milliseconds*/);
                conn.setConnectTimeout(15000  /*milliseconds*/);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();

                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new
                            InputStreamReader(
                            conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        StringBuffer Ss = sb.append(line);
                        Log.e("Ss", Ss.toString());
                        sb.append(line);
                        break;
                    }

                    in.close();
                    return sb.toString();

                } else {
                    return new String("false : " + responseCode);
                }
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }


        }

        public String getPostDataString(JSONObject params) throws Exception {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }
            return result.toString();
        }

        @Override
        protected void onPostExecute(String result) {
            if (result != null) {
                //  dialog.dismiss();

                JSONObject jsonObject = null;
                Log.e("PostRegistration", result.toString());
                try {

                    jsonObject = new JSONObject(result);
                    String response = jsonObject.getString("responce");
                    JSONArray get_id_array = jsonObject.getJSONArray("data");
                    JwellandGemsList.clear();
                    for (int k = 0; k < get_id_array.length(); k++) {

                        JSONObject get_it = get_id_array.getJSONObject(k);
//                        String id = get_it.getString("product_id");
//                        String quantity = get_it.getString("quantity");
//                        String image = get_it.getString("image");
//                        String Consultant_name = get_it.getString("model");
//
//                        String Consultant_quli = get_it.getString("qalification");
//                        Log.e("Name is", "" + Consultant_name);

                        Product_model c = new Product_model(get_it.getString("product_id")
                        ,get_it.getString("productname") ,get_it.getString("image")
                                ,get_it.getString("model") ,get_it.getString("quantity")
                                ,get_it.getString("stockStatus"),get_it.getString("price"));
                        JwellandGemsList.add(c);
                 //       List_by_city_consultant.add(Consultant_name);

                        mAdapter = new GemsandSagarAdapter(JwellandGemsList,SerachFurtherActivity.this);
                        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                        searcprorec.setLayoutManager(mLayoutManager);
                        searcprorec.setItemAnimator(new DefaultItemAnimator());
                        searcprorec.setAdapter(mAdapter);
                        mAdapter.notifyDataSetChanged();

                    }
//                    recyclerView1.setAdapter(null);


                    if (response.equalsIgnoreCase("True")) {


                        Toast.makeText(SerachFurtherActivity.this, "Success ", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(SerachFurtherActivity.this, "Failed to retrieve", Toast.LENGTH_SHORT).show();

                    }


                } catch (JSONException e) {
                    JwellandGemsList.clear();
                    mAdapter = new GemsandSagarAdapter(JwellandGemsList,SerachFurtherActivity.this);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    searcprorec.setLayoutManager(mLayoutManager);
                    searcprorec.setItemAnimator(new DefaultItemAnimator());
                    searcprorec.setAdapter(mAdapter);
                    mAdapter.notifyDataSetChanged();
//                    mAdapter.notifyDataSetChanged();
                    e.printStackTrace();
                }

            }
        }
    }
}
