package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

import java.util.List;

public class SignUp extends AppCompatActivity {

    private TextView edtText;
    private TextView txtGetData;
    private EditText edtKickBoxerName,edtKickBoxerSpeed,edtKickBoxerHeight;
    private TextView txtAll;
    private Button btnAll;
    private String allKickBoxers;
    private Button btnTransition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = findViewById(R.id.txtHelloWorld);
        edtKickBoxerName=findViewById(R.id.edtKickBoxerName);
        edtKickBoxerSpeed=findViewById(R.id.edtKickBoxerSpeed);
        edtKickBoxerHeight=findViewById(R.id.edtKickBoxerHeight);
        txtGetData = findViewById(R.id.txtGetData);
        txtAll = findViewById(R.id.txtAll);
        btnAll = findViewById(R.id.btnAll);
        btnTransition = findViewById(R.id.btnTransition);

        btnTransition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        txtGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
                parseQuery.getInBackground("9jY8RztKca", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if (object!=null && e==null){
                            txtGetData.setText(object.get("name").toString()+ " "+ "Punch Power: "+ object.get("speed").toString());
                        }
                    }
                });
            }
        });

        btnAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allKickBoxers="";
                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer");
                queryAll.whereGreaterThan("punch_speed",200);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(objects.size()>0){
                            for (ParseObject kickBoxer:objects){
                                allKickBoxers=allKickBoxers+kickBoxer+"\n";
                            }

                            Toast.makeText(SignUp.this,allKickBoxers,Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            Toast.makeText(SignUp.this,"Did not find any kick boxer object",Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    public void helloWorldTapped(View v){

        ParseObject boxer = new ParseObject("Boxer");
        boxer.put("punch_speed", 200);
        boxer.saveInBackground(new SaveCallback() {
            @Override
            public void done(ParseException e) {
                if (e==null){
                    Toast.makeText(SignUp.this,"Boxer Object Saved Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void createKickBoxerClicked(View v)
    {
    try {
        ParseObject KickBoxer = new ParseObject("KickBoxer");
        KickBoxer.put("name", edtKickBoxerName.getText().toString());
        KickBoxer.put("speed", Integer.parseInt(edtKickBoxerSpeed.getText().toString()));
        KickBoxer.put("height", Integer.parseInt(edtKickBoxerHeight.getText().toString()));


        KickBoxer.saveInBackground(new SaveCallback()
        {
            @Override
            public void done(ParseException e)
            {
            if (e == null) {
                String message = "Object KickBoxer " + edtKickBoxerName.getText().toString() + " created successfully";
                FancyToast.makeText(SignUp.this, message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                //Toast.makeText(SignUp.this,"Boxer Object Saved Successfully",Toast.LENGTH_LONG).show();
            }
        }
        });
        }catch(Exception e){
        FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
    }

    }

    public void getData (View v){
        ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("KickBoxer");
        parseQuery.getInBackground("vpt4IUKQYU", new GetCallback<ParseObject>() {
            @Override
            public void done(ParseObject object, ParseException e) {
                if (object!=null && e==null){
                    edtKickBoxerName.setText(object.get("name").toString());
                    edtKickBoxerSpeed.setText(object.get("speed").toString());
                    edtKickBoxerHeight.setText(object.get("height").toString());
                    //FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                    Toast.makeText(SignUp.this,"Object Successfully Retrieved",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public void getDataWithCondition(View v){

    }

    public void createKarateClicked(View v)
    {
        try {
            ParseObject KickBoxer = new ParseObject("Karate");
            KickBoxer.put("name", edtKickBoxerName.getText().toString());
            KickBoxer.put("speed", Integer.parseInt(edtKickBoxerSpeed.getText().toString()));
            KickBoxer.put("height", Integer.parseInt(edtKickBoxerHeight.getText().toString()));


            KickBoxer.saveInBackground(new SaveCallback()
            {
                @Override
                public void done(ParseException e)
                {
                    if (e == null) {
                        String message = "Object KickBoxer " + edtKickBoxerName.getText().toString() + " created successfully";
                        FancyToast.makeText(SignUp.this, message, FancyToast.LENGTH_LONG, FancyToast.SUCCESS, true).show();
                        //Toast.makeText(SignUp.this,"Boxer Object Saved Successfully",Toast.LENGTH_LONG).show();
                    }
                }
            });
        }catch(Exception e){
            FancyToast.makeText(SignUp.this, e.getMessage(), FancyToast.LENGTH_LONG, FancyToast.ERROR, true).show();
        }

    }
}
