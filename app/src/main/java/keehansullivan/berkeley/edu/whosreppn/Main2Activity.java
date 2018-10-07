package keehansullivan.berkeley.edu.whosreppn;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;

public class Main2Activity extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {
    Intent intent;
    Bundle bundle;
    Double longitude;
    Double latitude;
    Integer zip;
    String source;
    TextView txt;
    //TextView txt;
    //"https://api.geocod.io/v1.3/reverse?q=38.9002898,-76.9990361&api_key=YOUR_API_KEY"
    String geocodia;
    RequestQueue mQueue;

    String districtName;
    HashSet<CongressMemberInfo> members = new HashSet<>();
    ArrayList<CongressMemberInfo> congressMembers = new ArrayList<>();
    TextView txts;
    JSONArray kk;
    RecyclerView recyclerView;
    String fullName;
    String Party;
    String contactlink;
    String websitelink;






    MyRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        recyclerView = findViewById(R.id.congressMembers);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mQueue = Volley.newRequestQueue(this);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();


        //System.out.println(longitude +"/" + latitude);
        source = bundle.getString("source");
        //System.out.println(source);
        if (source.equals("zipButton")) {
            zip = bundle.getInt("zip");
            geocodia = "https://api.geocod.io/v1.3/geocode?q=" + zip.toString() + "&fields=cd&api_key=5ab730b7b1a544713a925135c75bbdb75d78d99";
            System.out.println(geocodia);
            jsonParse();


        }
        //https://api.geocod.io/v1.3/reverse?q=19.422,100.084&fields=cd&api_key=5ab730b7b1a544713a925135c75bbdb75d78d99
        //https://api.geocod.io/v1.3/reverse?q=19.422,100.084&fields=cd&api_key=5ab730b7b1a544713a925135c75bbdb75d78d99
        if (source.equals("curButton")){

            latitude = bundle.getDouble("latitude");
            longitude = bundle.getDouble("longitude");
            geocodia = "https://api.geocod.io/v1.3/reverse?q=" +latitude.toString() +"," +longitude.toString() + "&fields=cd&api_key=5ab730b7b1a544713a925135c75bbdb75d78d99";
            System.out.println(geocodia);
            jsonParse();
        }
        if (source.equals("randButton")) {
            zip = bundle.getInt("zip");
            geocodia = "https://api.geocod.io/v1.3/geocode?q=" + zip.toString() + "&fields=cd&api_key=5ab730b7b1a544713a925135c75bbdb75d78d99";
            System.out.println(geocodia);
            jsonParse();

        }




        //txts = (TextView) findViewById(R.id.textView4);








        // data to populate the RecyclerView with


        //for (String item:members ) congressMembers.add(item);
        //System.out.println(congressMembers);

        //animalNames.add("Horse");
        //animalNames.add("Cow");
        //animalNames.add("Camel");
        //animalNames.add("Sheep");
        //animalNames.add("Goat");

        // set up the RecyclerView

        adapter = new MyRecyclerViewAdapter(this, congressMembers);
        adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);







    }
        private void adapterssss(HashSet<CongressMemberInfo> mem, RecyclerView recyclerView) {
            for (CongressMemberInfo x:mem) {
                congressMembers.add(x);
                adapter = new MyRecyclerViewAdapter(this, congressMembers);
                adapter.setClickListener(this);
                recyclerView.setAdapter(adapter);
            }

        }


        private void jsonParse() {

        String url = geocodia;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            JSONArray jsonArray = response.getJSONArray("results");

                            String state = jsonArray.getJSONObject(0).getJSONObject("address_components").getString("state");
                            String zipcoded = jsonArray.getJSONObject(0).getJSONObject("address_components").getString("zip");
                            String county = jsonArray.getJSONObject(0).getJSONObject("address_components").getString("county");





                            //txts.setText(jsonArray.toString());
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject result = jsonArray.getJSONObject(i);
                                //System.out.println(result.toString());

                                JSONObject fields = result.getJSONObject("fields");
                                //System.out.println(fields.length());

                                JSONArray congr = fields.getJSONArray("congressional_districts");

                                for (int k = 0; k < congr.length(); k++) {
                                    //JSONArray congressional_district = congr.getJSONArray();
                                    JSONObject district = congr.getJSONObject(k);
                                    JSONArray current_legislatures = district.getJSONArray("current_legislators");





                                    for (int j = 0; j < current_legislatures.length(); j++) {
                                        JSONObject curentLeg = current_legislatures.getJSONObject(j);
                                        JSONObject contact = curentLeg.getJSONObject("contact");

                                        JSONObject bio = curentLeg.getJSONObject("bio");
                                        String firstname = bio.getString("first_name");
                                        String lastname = bio.getString("last_name");
                                        CongressMemberInfo memb = new CongressMemberInfo(firstname, lastname, curentLeg.getString("type"), bio.getString("party"),
                                                state, district.getString("name"), contact.getString("url"), contact.getString("phone"),
                                                contact.getString("contact_form"));

                                        members.add(memb);
                                        setTitle(county + ", "+ zipcoded + ", " + state);



                                    }

                                    //districtName = congressional_district.getString(0);
                                }
                            }
                            adapterssss(members, recyclerView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);



    }





    @Override
    public void onItemClick(View view, int position) {
        fullName = adapter.getItem(position).firstName + " " + adapter.getItem(position).lastName;
        Party = adapter.getItem(position).party;
        contactlink = adapter.getItem(position).contactForm;
        websitelink = adapter.getItem(position).url;
        intent = new Intent(Main2Activity.this, Main3Activity.class);

        bundle = new Bundle();

        bundle.putString("Party",Party);
        bundle.putString("contactlink", contactlink);
        bundle.putString("websitelink", websitelink);
        bundle.putString("fullName", fullName);

        //bundle.putString("source", source);
        intent.putExtras(bundle);
        startActivity(intent);

        //Toast.makeText(this, "You clicked " + adapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }


}

