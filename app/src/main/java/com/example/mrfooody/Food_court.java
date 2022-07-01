package com.example.mrfooody;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class Food_court extends AppCompatActivity {

    //for google sign In
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    ImageView signOut,person;


    RecyclerView recyclerListing;
    RecyclerView recyclerItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_court);

        recyclerListing =findViewById(R.id.recycle);
        recyclerItems =findViewById(R.id.recycle_food);

        signOut = findViewById(R.id.sign_out);
        person = findViewById(R.id.person);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
// Build a GoogleSignInClient with the options specified by gso.
        gsc = GoogleSignIn.getClient(this, gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();

            Picasso.get().load(acct.getPhotoUrl()).placeholder(R.drawable.person).into(person);

        }
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signOut();
            }
        });

        setListing();
        setFoodItem(0);
    }
    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete( Task<Void> task) {
                finish();
                startActivity(new Intent(Food_court.this,LoginActivity.class));
            }
        });
    }


    private void setListing(){

        List<FoodListing> data = new ArrayList<>();

        FoodListing foodListing = new FoodListing("Burger ",R.drawable.burger);
        FoodListing foodListing2 = new FoodListing("Chicken",R.drawable.chicken);
        FoodListing foodListing3 = new FoodListing("Pizza",R.drawable.pizza);
        FoodListing foodListing4 = new FoodListing("Ice Cream",R.drawable.ice_cream);
        FoodListing foodListing5 = new FoodListing("Doughnut",R.drawable.doughnut);

        data.add(foodListing);
        data.add(foodListing2);
        data.add(foodListing3);
        data.add(foodListing4);
        data.add(foodListing5);

        FoodListAdapter foodListAdapter= new FoodListAdapter(data, Food_court.this, new FoodListAdapter.OnListingClick() {
            @Override
            public void onClick(int pos) {
                setFoodItem(pos);
            }
        });
    recyclerListing.setLayoutManager(new LinearLayoutManager(Food_court.this,RecyclerView.HORIZONTAL,false));
    recyclerListing.setAdapter(foodListAdapter);
    foodListAdapter.notifyDataSetChanged();
    }
    private void setFoodItem(int pos){
        List<FoodItem> foodItems = new ArrayList<>();
        switch (pos){
            case 2:
                FoodItem foodItem= new FoodItem("Normal Pizza",4.5f,120,R.drawable.piz1);
                FoodItem foodItem2= new FoodItem("spicy Pizza",5f,200,R.drawable.piz3);
                FoodItem foodItem3= new FoodItem("Red Chilli Pizza",3.5f,90,R.drawable.piz4);
                FoodItem foodItem4= new FoodItem("Veg Pizza",4.5f,150,R.drawable.piz6);
                FoodItem foodItem5= new FoodItem("Cheese Pizza",4f,100,R.drawable.cheez_piz);
                FoodItem foodItem6= new FoodItem("Mini Pizza",4.5f,130,R.drawable.veg_piz);
                FoodItem foodItem7= new FoodItem("Tomato Pizza",3f,80,R.drawable.tom_piz);


                foodItems.add(foodItem);
                foodItems.add(foodItem2);
                foodItems.add(foodItem3);
                foodItems.add(foodItem4);
                foodItems.add(foodItem5);
                foodItems.add(foodItem6);
                foodItems.add(foodItem7);
                break;
            case 1:
                FoodItem foodItem8= new FoodItem("Normal chicken",4.5f,120,R.drawable.chick1);
                FoodItem foodItem9= new FoodItem("cooked chicken",5f,200,R.drawable.cooked_chick);
                FoodItem foodItem10= new FoodItem("Drumstick chicken",3.5f,90,R.drawable.drum_chick);
                FoodItem foodItem11= new FoodItem("popeys chicken",4.5f,150,R.drawable.popeys_chick);
                FoodItem foodItem12= new FoodItem("Wings legs piece",4f,100,R.drawable.wing_legs_chick);
                FoodItem foodItem13= new FoodItem("Fried Chicken Wing",4.5f,130,R.drawable.fried_chick_wing);
                FoodItem foodItem14= new FoodItem("grill",3f,80,R.drawable.grill_chick);


                foodItems.add(foodItem8);
                foodItems.add(foodItem9);
                foodItems.add(foodItem10);
                foodItems.add(foodItem11);
                foodItems.add(foodItem12);
                foodItems.add(foodItem13);
                foodItems.add(foodItem14);
                break;
            case 0:
                FoodItem foodItem15= new FoodItem("Normal Burger",4.5f,120,R.drawable.bur1);
                FoodItem foodItem16= new FoodItem("spicy Burger",5f,200,R.drawable.bur2);
                FoodItem foodItem17= new FoodItem("Red Chilli Burger",3.5f,90,R.drawable.bur3);
                FoodItem foodItem18= new FoodItem("cheesy Burger",4.5f,150,R.drawable.cheez_bur);
                FoodItem foodItem19= new FoodItem("veg Burger",4f,100,R.drawable.veg_bur);
                FoodItem foodItem20= new FoodItem("Non veg Burger",4.5f,130,R.drawable.non_bur);
                FoodItem foodItem21= new FoodItem("Chilly Burger",3f,80,R.drawable.bur_fre);


                foodItems.add(foodItem15);
                foodItems.add(foodItem16);
                foodItems.add(foodItem17);
                foodItems.add(foodItem18);
                foodItems.add(foodItem19);
                foodItems.add(foodItem20);
                foodItems.add(foodItem21);
                break;
            case 3:
                FoodItem foodItem22= new FoodItem("Normal Ice Cream",4.5f,120,R.drawable.ice1);
                FoodItem foodItem23= new FoodItem("Strawberry",5f,200,R.drawable.ice2);
                FoodItem foodItem24= new FoodItem("3 in one",3.5f,90,R.drawable.ice3);
                FoodItem foodItem25= new FoodItem("choco",4.5f,150,R.drawable.choc_ice);
                FoodItem foodItem26= new FoodItem("Cone",4f,100,R.drawable.cone_ice);
                FoodItem foodItem27= new FoodItem("mixed",4.5f,130,R.drawable.cup_ice);
                FoodItem foodItem28= new FoodItem("choco Bar",3f,80,R.drawable.ice4);


                foodItems.add(foodItem22);
                foodItems.add(foodItem23);
                foodItems.add(foodItem24);
                foodItems.add(foodItem25);
                foodItems.add(foodItem26);
                foodItems.add(foodItem27);
                foodItems.add(foodItem28);
                break;
            case 4:
                FoodItem foodItem29= new FoodItem("Strawberry Doughnut",4.5f,120,R.drawable.dough);
                FoodItem foodItem30= new FoodItem("Choco Doughnut",5f,200,R.drawable.choc_dough);
                FoodItem foodItem31= new FoodItem("Six in One",3.5f,90,R.drawable.six_in_one);



                foodItems.add(foodItem29);
                foodItems.add(foodItem30);
                foodItems.add(foodItem31);

                break;

        }


        FoodAdapter foodAdapter = new FoodAdapter(foodItems);
        recyclerItems.setLayoutManager(new LinearLayoutManager(Food_court.this,RecyclerView.HORIZONTAL,false));
        recyclerItems.setAdapter(foodAdapter);

    }
}