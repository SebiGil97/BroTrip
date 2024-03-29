package at.fhooe.mc.android.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import at.fhooe.mc.android.Person;
import at.fhooe.mc.android.Purchase;
import at.fhooe.mc.android.R;

public class ActivityPurchase extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_purchase);

        nameList=new LinkedList<String>();
        nameList.add("select a payer");

        ArrayList<Person> persons = (ArrayList<Person>) getIntent().getExtras().getSerializable("purchasePerson");

        for(int index = 0; index < persons.size(); index++){
            nameList.add(persons.get(index).getmName());
        }

        /*----------------ListSpinner------------*/
        //Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, nameList);
        Spinner spinner = (Spinner) findViewById(R.id.activity_purchase_pick_payer);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button b = null;
        b = (Button) findViewById(R.id.new_purchase_button_save_purchase);
        b.setOnClickListener(this);

    }

    @Override
    public void onClick(View _v) {
        switch(_v.getId()){
            case R.id.new_purchase_button_save_purchase: {
                /*---------- get Integers and Person-Object ----------*/
                EditText shop = (EditText)findViewById(R.id.new_purchase_edit_text_where);
                String purchaseShop = shop.getText().toString();
                EditText price = (EditText)findViewById(R.id.new_purchase_edit_text_paid);
                Spinner payer = (Spinner)findViewById(R.id.activity_purchase_pick_payer);
                String purchasePayer = (String) payer.getSelectedItem();

                //---------- check input ---------
                if(purchaseShop.isEmpty()){
                    Toast.makeText(this, "please enter a shop", Toast.LENGTH_SHORT).show();
                }else if(price.getText().toString().equals("")){
                    Toast.makeText(this, "please enter a price", Toast.LENGTH_SHORT).show();
                }else if( purchasePayer.equals("select a payer")){
                    Toast.makeText(this, "please select a payer", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(this, "new purchase saved", Toast.LENGTH_SHORT).show();

                    String p = price.getText().toString();
                    changeDotToComma(p);
                    Float purchasePrice = Float.parseFloat(p);

                    /*--------- build new Refuel-Object ----------*/
                    Purchase newPurchase = new Purchase(purchaseShop, purchasePrice, purchasePayer, new Date());
                    Toast.makeText(this, "Purchase " + newPurchase.getmNameShop(), Toast.LENGTH_SHORT).show();

                    /* ---------- return Intent (Refuel-Object) ---------- */
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("newPurchase", newPurchase);
                    setResult(Activity.RESULT_OK, returnIntent);
                    finish();
                }
            }break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> _parent, View _view, int _position, long _id) {
        String name = _parent.getItemAtPosition(_position).toString();

        //Toast.makeText(this, "selected " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> _parent) {
        Toast.makeText(this, "Please select friend!", Toast.LENGTH_SHORT).show();
    }

   private void changeDotToComma(String s){
        if(s != null && s != "") {
            if (s.contains(".")) {
                s.replace(".", ",");
            }
        }
   }


}
