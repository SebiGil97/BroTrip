package at.fhooe.mc.android;

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

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ActivityPurchase extends Activity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    List<String> nameList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_purchase);

        nameList=new LinkedList<String>();

        ArrayList<Person> persons = (ArrayList<Person>) getIntent().getExtras().getSerializable("purchasePerson");

        for(int index = 0; index < persons.size(); index++){
            nameList.add(persons.get(index).getName());
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
                Integer purchasePrice = Integer.parseInt(price.getText().toString());
                Spinner payer = (Spinner)findViewById(R.id.activity_purchase_pick_payer);
                String purchasePayer = (String) payer.getSelectedItem();

                /*--------- build new Refuel-Object ----------*/
                Purchase newPurchase = new Purchase(purchaseShop, purchasePrice, purchasePayer);
                Toast.makeText(this, "Purchase " + newPurchase.getmNameShop(), Toast.LENGTH_SHORT).show();

                /* ---------- return Intent (Refuel-Object) ---------- */
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newPurchase", newPurchase);
                setResult(Activity.RESULT_OK,returnIntent);
                finish();
            }break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> _parent, View _view, int _position, long _id) {
        String name = _parent.getItemAtPosition(_position).toString();

        Toast.makeText(this, "selected " + name, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> _parent) {
        Toast.makeText(this, "Please select friend!", Toast.LENGTH_SHORT).show();
    }
}
