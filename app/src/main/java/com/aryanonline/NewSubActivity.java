package com.aryanonline;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aryanonline.Adapter.NewSubAdapter;
import com.aryanonline.Adapter.Sort_Adapter;
import com.aryanonline.Model.NewCategory;
import com.aryanonline.Model.Sorted_Model;
import com.aryanonline.Model.SubCatModel;
import com.aryanonline.util.ConnectivityReceiver;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class NewSubActivity extends AppCompatActivity {
    RecyclerView subCateList;
    String server_url;
    TextView Sort;
    ArrayList<SubCatModel> subC_list;
    ArrayList<Sorted_Model> Sor_List;
    private NewSubAdapter newSubAdapter;
    private Sort_Adapter sort_adapter;
    String prId;
    List<String> slist;
    private int sort_num=0;
    private String type="";
    private String short_by="";
    private String sort_URL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.aryanonline.R.layout.activity_new_sub);

        if (getIntent() != null) {

            prId = getIntent().getStringExtra("NewCategory");

//            NewCategory newCategory = (NewCategory) getIntent().getSerializableExtra("NewCategory");
//            prId = newCategory.getCategoryId();
//            provId = reviewsModel1.getProviderId();
//            prId = reviewsModel1.getId();

        }

        subCateList = (RecyclerView)findViewById(com.aryanonline.R.id.subCateList);
        Sort = (TextView)findViewById(R.id.tx_sort);


        Toolbar toolbar = (Toolbar) findViewById(com.aryanonline.R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getResources().getString(com.aryanonline.R.string.app_name));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        subC_list = new ArrayList<>();
        Sor_List = new ArrayList<>();

        slist = new ArrayList<>();
        slist.add("Name (A - Z)");
        slist.add("Name (Z - A)");
        slist.add("Price (Low > High)");
        slist.add("Price (High > Low)");
        slist.add("Rating (Highest)");
        slist.add("Rating (Lowest)");
        slist.add("Model (A - Z)");
        slist.add("Model (Z - A)");

        if (ConnectivityReceiver.isConnected()) {
            new GetSublist().execute();
        }

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("font/Roboto-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        Sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Dialog dialog = new Dialog(NewSubActivity.this);
                    dialog.setContentView(R.layout.custom_dialog);
                    dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

                    ListView listView = dialog.findViewById(R.id.list_sort);
                    Button ButtnOk = dialog.findViewById(R.id.button_ok);
                    SAdapter sadap = new SAdapter();
                    listView.setAdapter(sadap);

                    ButtnOk.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            GetSort(sort_num);
                            dialog.cancel();
                        }
                    });

                    dialog.show();
            }
        });
    }


    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
        Log.e("Attach Base Context","----------");
    }


    class GetSublist extends AsyncTask<String, String, String> {
        String output = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(NewSubActivity.this);
            dialog.setMessage("Processing");
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            // For Normal List

             try {
                server_url = "https://enlightshopping.com/api/api/getproduct_category?category_id="+prId;
                Log.e("sever_url>>>>>>>>>", server_url);
            } catch (Exception e) {
                e.printStackTrace();         }
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
                    String description="";
                    dialog.dismiss();
                    JSONObject obj = new JSONObject(output);
                    String responce = obj.getString("responce");
                    JSONArray Data_array = obj.getJSONArray("data");
                    for (int i = 0; i < Data_array.length(); i++) {
                        JSONObject c = Data_array.getJSONObject(i);
                        String product_id = c.getString("product_id");
                        String model = c.getString("model");
                        String quantity = c.getString("quantity");
                        String image = c.getString("image");
                        String stockStatus = c.getString("stockStatus");
                        String price = c.getString("price");
                        String productname = c.getString("productname");
                        description = c.getString("description");
                        subC_list.add(new SubCatModel(product_id, model, quantity, image, stockStatus, price,productname,description));
                    }

                    newSubAdapter = new NewSubAdapter(NewSubActivity.this, subC_list);
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(NewSubActivity.this, 2);
//                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(NewSubActivity.this, LinearLayoutManager.VERTICAL, false);
                    subCateList.setLayoutManager(gridLayoutManager);
                    subCateList.setItemAnimator(new DefaultItemAnimator());
                   /* subCateList.addItemDecoration(new DividerItemDecoration(NewSubActivity.this,
                            DividerItemDecoration.VERTICAL));*/
                    subCateList.setAdapter(newSubAdapter);
                    //  rv_top.setNestedScrollingEnabled(false);
                    // adapter.setHasStableIds(new List(top_list.GetRange(0, 4)));


                } catch (JSONException e) {
                    try {
                        JSONObject jsonObject = new JSONObject(output);
                        Toast.makeText(NewSubActivity.this, ""+jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                    //  dialog.dismiss();
                }
                super.onPostExecute(output);
            }
        }
    }


    class GetSortedList extends AsyncTask<String,String,String>
    {
        String output = "";
        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = new ProgressDialog(NewSubActivity.this);
            dialog.setMessage("Processing");
            dialog.setCancelable(true);
            dialog.show();
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

            // For Sorted List

            try {
                sort_URL = "https://enlightshopping.com/api/api/getProductsort?type="+type+"&short_by="+short_by+"&category_id="+prId;
                Log.e("Server URL SORT :",""+sort_URL);}
            catch (Exception ex) {    ex.printStackTrace();    }
            output = HttpHandler.makeServiceCall(sort_URL);
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
                    String responce = obj.getString("responce");
                    JSONArray Data_array = obj.getJSONArray("data");
                    for (int i = 0; i < Data_array.length(); i++) {
                        JSONObject c = Data_array.getJSONObject(i);
                        String product_id = c.getString("product_id");
                        String model = c.getString("model");
                        String quantity = c.getString("quantity");
                        String image = c.getString("image");
                        String stockStatus = c.getString("stockStatus");
                        String price = c.getString("price");
                        String productname = c.getString("productname");
                        String description = c.getString("description");
                        String rating = c.getString("rating");

                        Sor_List.add(new Sorted_Model(product_id, model, quantity, image, stockStatus, price,productname,description,rating));
                    }

                    sort_adapter = new Sort_Adapter(NewSubActivity.this, Sor_List);

                    GridLayoutManager gridLayoutManager = new GridLayoutManager(NewSubActivity.this, 2);

                    //                    LinearLayoutManager mLayoutManager = new LinearLayoutManager(NewSubActivity.this, LinearLayoutManager.VERTICAL, false);

                    subCateList.setLayoutManager(gridLayoutManager);
                    subCateList.setItemAnimator(new DefaultItemAnimator());

                    subCateList.setAdapter(sort_adapter);
                } catch (JSONException e) {
                    try {
                        JSONObject jsonObject = new JSONObject(output);
                        Toast.makeText(NewSubActivity.this, ""+jsonObject.getString("data"), Toast.LENGTH_SHORT).show();
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }
                    e.printStackTrace();
                    //  dialog.dismiss();
                }
                super.onPostExecute(output);
            }
        }
    }

    private void GetSort(int num)
    {
        Sor_List.clear();
        switch (num)
        {
            case 0:
                type = "";
                short_by = "";
                break;

            case 1:
                type = "name";
                short_by = "asc";
                new GetSortedList().execute();
                break;

            case 2:
                type = "name";
                short_by = "desc";
                new GetSortedList().execute();
                break;

            case 3:
                type = "price";
                short_by = "asc";
                new GetSortedList().execute();
                break;

            case 4:
                type = "price";
                short_by = "desc";
                new GetSortedList().execute();
                break;

            case 5:
                type = "rating";
                short_by = "asc";
                new GetSortedList().execute();
                break;

            case 6:
                type = "rating";
                short_by = "desc";
                new GetSortedList().execute();
                break;

            case 7:
                type = "model";
                short_by = "asc";
                new GetSortedList().execute();
                break;

            case 8:
                type = "model";
                short_by = "desc";
                new GetSortedList().execute();
                break;

        }
        Log.e("SORT METHOD : ","type = "+type+"_____ short_by = "+short_by);
    }

    class SAdapter extends ArrayAdapter
    {
        RadioButton selected = null;
        SAdapter()
        {
            super(NewSubActivity.this,R.layout.row_sort,slist);
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            LayoutInflater inflater= getLayoutInflater();

            View rowView=inflater.inflate(R.layout.row_sort, null,true);

            TextView tx = rowView.findViewById(R.id.name1);
            final RadioButton rd = rowView.findViewById(R.id.radio1);

            rd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(selected != null)
                    {
                        selected.setChecked(false);
                    }

                    rd.setChecked(true);
                    selected = rd;
                    sort_num = position+1;
                }
            });

            tx.setText(slist.get(position));

            return rowView;
        }
    }
}
