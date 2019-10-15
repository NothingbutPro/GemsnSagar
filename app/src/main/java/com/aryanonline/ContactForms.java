package com.aryanonline;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ContactForms extends AppCompatActivity {
    EditText bname , gname,boybirthdate,girlbirthdate,boybirthtime,girlbirthtime,commentboy,commentgirl,boybirthplace,girlbirthplace;
    Button senit;
    DatePickerDialog picker;
    TimePickerDialog  timePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_forms);
        bname = findViewById(R.id.bname);
        gname = findViewById(R.id.gname);
        boybirthdate = findViewById(R.id.boybirthdate);
        senit = findViewById(R.id.senit);
        girlbirthdate = findViewById(R.id.girlbirthdate);
        boybirthtime = findViewById(R.id.boybirthtime);
        girlbirthtime = findViewById(R.id.girlbirthtime);
        commentboy = findViewById(R.id.commentboy);
        commentgirl = findViewById(R.id.commentgirl);
        boybirthplace = findViewById(R.id.boybirthplace);
        girlbirthplace = findViewById(R.id.girlbirthplace);
       // bname = findViewById(R.id.bname);
      //  getCurrentDateboy();
        boybirthtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(ContactForms.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                boybirthtime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
        girlbirthtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int hour = cldr.get(Calendar.HOUR_OF_DAY);
                int minutes = cldr.get(Calendar.MINUTE);
                // time picker dialog
                timePicker = new TimePickerDialog(ContactForms.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker tp, int sHour, int sMinute) {
                                girlbirthtime.setText(sHour + ":" + sMinute);
                            }
                        }, hour, minutes, true);
                timePicker.show();
            }
        });
        boybirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDateboy();
            }
        });
        girlbirthdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentDategirl();
            }
        });
        if(bname.getText().toString().length() !=0&&gname.getText().toString().length() !=0&&boybirthdate.getText().toString().length() !=0
        &&girlbirthdate.getText().toString().length() !=0&&boybirthtime.getText().toString().length() !=0&&girlbirthtime.getText().toString().length() !=0&&
                commentboy.getText().toString().length() !=0&&commentboy.getText().toString().length() !=0&&boybirthplace.getText().toString().length() !=0&&
                girlbirthplace.getText().toString().length() !=0){
//            Intent i = new Intent(Intent.ACTION_SEND);
//            i.setType("message/rfc822");
//            i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@enlighshopping.com"});
//            i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
//            i.putExtra(Intent.EXTRA_TEXT   , "body of email");
//            try {
//                startActivity(Intent.createChooser(i, "Send mail..."));
//            } catch (android.content.ActivityNotFoundException ex) {
//                Toast.makeText(ContactForms.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
//            }
        }else {
            Toast.makeText(this, "Please fill all", Toast.LENGTH_SHORT).show();
        }


        senit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bname.getText().toString().length() !=0&&gname.getText().toString().length() !=0&&boybirthdate.getText().toString().length() !=0
                        &&girlbirthdate.getText().toString().length() !=0&&boybirthtime.getText().toString().length() !=0&&girlbirthtime.getText().toString().length() !=0&&
                        commentboy.getText().toString().length() !=0&&commentboy.getText().toString().length() !=0&&boybirthplace.getText().toString().length() !=0&&
                        girlbirthplace.getText().toString().length() !=0){
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("message/rfc822");
                    i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info@enlighshopping.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, "For Contact");
                    i.putExtra(Intent.EXTRA_TEXT   , "Boy's Name:"+bname.getText().toString()+"\n Girl's Name :"+gname.getText().toString()+"\n\n"+"Boy's Birth Date:"+boybirthdate.getText().toString()
                    +"\n"+"Girl's Birth Date:"+girlbirthdate.getText().toString()+"\n\n"+"Boy's Birth Time:"+boybirthtime.getText().toString()+"\n"+"Girl's Birth TIme"+girlbirthtime.getText().toString()+"\n\n"+"Boy's Birth Place:"+boybirthplace.getText().toString()+"\n"
                    +"Girl's Birth Place:"+girlbirthplace.getText().toString()+"\n\n"+"Comments About Boy:"+commentboy.getText().toString()+"\n"+"Comment about Girl:"+commentgirl.getText().toString());
                    try {
                        startActivity(Intent.createChooser(i, "Send mail...with"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(ContactForms.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(ContactForms.this, "Please fill all", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void getCurrentDategirl() {

        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(ContactForms.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        girlbirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }

    private void getCurrentDateboy() {
        final Calendar cldr = Calendar.getInstance();
        int day = cldr.get(Calendar.DAY_OF_MONTH);
        int month = cldr.get(Calendar.MONTH);
        int year = cldr.get(Calendar.YEAR);
        // date picker dialog
        picker = new DatePickerDialog(ContactForms.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        boybirthdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, year, month, day);
        picker.show();
    }
}
