package restaurante.codebrain.com.br.contarestaurante;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView txtValuePerPerson;
    TextView txtTotalBill;
    TextView txtServiceCharge;
    TextView txtService;
    TextView txtBill;
    TextView txtValuePerson;

    EditText edtConsumption;
    EditText edtCouvert;
    EditText edtSplitBill;

    Button btnCalculate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtValuePerson = (TextView) findViewById(R.id.txtValuePerson);
        txtService = (TextView) findViewById(R.id.txtService);
        txtBill = (TextView) findViewById(R.id.txtBill);

        txtValuePerPerson = (TextView) findViewById(R.id.txtValuePerPerson);
        txtTotalBill = (TextView) findViewById(R.id.txtTotalBill);
        txtServiceCharge = (TextView) findViewById(R.id.txtServiceCharge);
        edtConsumption = (EditText) findViewById(R.id.edtConsumption);
        edtCouvert = (EditText) findViewById(R.id.edtCouvert);
        edtSplitBill = (EditText) findViewById(R.id.edtSplitBill);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateView()){
                    calculateBill();
                }
            }
        });
    }

    private void calculateBill(){
        Double consumption = Double.parseDouble(edtConsumption.getText().toString());
        Double couvert = Double.parseDouble(edtCouvert.getText().toString());
        Double split_bill = Double.parseDouble(edtSplitBill.getText().toString());
        try {
            Double total_bill = consumption + couvert;
            Double service_charge = total_bill * 0.1;
            Double value_per_person = total_bill/split_bill;

            showResultView(total_bill,service_charge,value_per_person);
        }catch (Exception e){
            Log.d("Error MainActivity","calculateBill() - " + e.getMessage().toString());
        }
    }

    private void showResultView(Double _total_bill, Double _service_charge,Double _value_per_person){
        txtBill.setVisibility(View.VISIBLE);
        txtService.setVisibility(View.VISIBLE);
        txtValuePerson.setVisibility(View.VISIBLE);

        //Results
        txtTotalBill.setVisibility(View.VISIBLE);
        txtServiceCharge.setVisibility(View.VISIBLE);
        txtValuePerPerson.setVisibility(View.VISIBLE);

        txtTotalBill.setText(_total_bill.toString());
        txtServiceCharge.setText(_service_charge.toString());
        txtValuePerPerson.setText(_value_per_person.toString());
    }


    private boolean validateView(){
        if(TextUtils.isEmpty(edtConsumption.getText().toString())){
            edtConsumption.setError(getString(R.string.value_isempty_BR));
            return false;
        }
        if(TextUtils.isEmpty(edtCouvert.getText().toString())){
            edtCouvert.setError(getString(R.string.value_isempty_BR));
            return false;
        }
        if(TextUtils.isEmpty(edtSplitBill.getText().toString())){
            edtSplitBill.setError(getString(R.string.value_isempty_BR));
            return false;
        }
        return true;
    }
}
