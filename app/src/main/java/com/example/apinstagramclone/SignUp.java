package com.example.apinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import org.w3c.dom.Text;

public class SignUp extends AppCompatActivity {

    private TextView edtText;
    private EditText edtKickBoxerName,edtKickBoxerSpeed,edtKickBoxerHeight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtText = findViewById(R.id.txtHelloWorld);
        edtKickBoxerName=findViewById(R.id.edtKickBoxerName);
        edtKickBoxerSpeed=findViewById(R.id.edtKickBoxerSpeed);
        edtKickBoxerHeight=findViewById(R.id.edtKickBoxerHeight);


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
}
