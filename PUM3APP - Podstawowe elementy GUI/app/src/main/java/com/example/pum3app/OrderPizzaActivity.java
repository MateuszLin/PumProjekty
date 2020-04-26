package com.example.pum3app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class OrderPizzaActivity extends AppCompatActivity {

    RadioGroup typeDough;
    RadioButton thinDough;
    RadioButton thickDough;

    RadioGroup sizeOfPizza;
    RadioButton bigSize;
    RadioButton mediumSize;
    RadioButton smallSize;

    CheckBox hamCheckBox;
    CheckBox cheeseCheckBox;
    CheckBox mushroomsCheckBox;
    CheckBox olivesCheckBox;
    CheckBox baconCheckBox;
    CheckBox chickenCheckBox;
    CheckBox onionCheckBox;

    CheckBox garlicCheckBox;
    CheckBox salamiCheckBox;
    CheckBox capersCheckBox;
    CheckBox tunaCheckBox;
    CheckBox tomatoSauceCheckBox;
    CheckBox garlicSauceCheckBox;
    CheckBox oreganoCheckBox;

    int maxExtraIngredients = 3;
    int minBasicIngredients = 3;
    int countMinBasic = 0;
    int countExtra = 0;
    double priceForPizza = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_pizza);

        typeDough = findViewById(R.id.typeOfDough);
        thinDough = findViewById(R.id.rBtnThin);
        thickDough = findViewById(R.id.rBtnFat);

        sizeOfPizza = findViewById(R.id.sizeOfPizza);
        bigSize = findViewById(R.id.rBtnBig);
        mediumSize = findViewById(R.id.rBtnMiddle);
        smallSize = findViewById(R.id.rBtnSmall);

        hamCheckBox = findViewById(R.id.checkHam);
        cheeseCheckBox = findViewById(R.id.checkCheese);
        mushroomsCheckBox = findViewById(R.id.checkMushrroms);
        olivesCheckBox = findViewById(R.id.checkOlives);
        baconCheckBox = findViewById(R.id.checkBacon);
        chickenCheckBox = findViewById(R.id.checkChicken);
        onionCheckBox = findViewById(R.id.checkOnion);
        garlicCheckBox = findViewById(R.id.checkGarlic);
        salamiCheckBox = findViewById(R.id.checkSalami);
        capersCheckBox = findViewById(R.id.checkCapers);
        tunaCheckBox = findViewById(R.id.checkTuna);
        tomatoSauceCheckBox = findViewById(R.id.checkTomatoSauce);
        garlicSauceCheckBox = findViewById(R.id.checkGarlicSauce);
        oreganoCheckBox = findViewById(R.id.checkOregano);

        findViewById(R.id.OrderBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderPizza();
            }
        });
    }

    private void OrderPizza() {
        if(canPizzaBeOrder())
        {
            String orderMsg = getString(R.string.YouOrder);

            priceForPizza = 0;
            String typeOfDough = getMsgOfRadioButton(typeDough.getCheckedRadioButtonId());
            String sizeOfPizeMsg = getMsgOfRadioButton(sizeOfPizza.getCheckedRadioButtonId());

            orderMsg += " " + sizeOfPizeMsg + " " + typeOfDough + " " + getString(R.string.Pizza);
            orderMsg += " " + getString(R.string.with) + " ";

            String basicIngredientsMsg = getMsgOfBasicCheckBoxes();
            orderMsg += basicIngredientsMsg;
            String extraIngredients = getMsgOfExtraIngredients();

            if(!extraIngredients.equals(""))
            {
                orderMsg += getString(R.string.AndExtra) + " " + extraIngredients;
            }

            orderMsg += ". " + getString(R.string.Price) + " " + Double.toString(priceForPizza);

            Toast.makeText(getApplicationContext(), orderMsg, Toast.LENGTH_LONG).show();
        }
    }

    private String getMsgOfExtraIngredients() {
        String msg = "";
        if(garlicCheckBox.isChecked())
        {
            msg += garlicCheckBox.getText();
            priceForPizza += 2.2;
        }
        if(salamiCheckBox.isChecked())
        {
            msg += salamiCheckBox.getText();
            priceForPizza += 2.5;
        }
        if(capersCheckBox.isChecked())
        {
            msg += salamiCheckBox.getText();
            priceForPizza += 6;
        }
        if(tunaCheckBox.isChecked())
        {
            msg += tunaCheckBox.getText();
            priceForPizza += 7.7;
        }
        if(tomatoSauceCheckBox.isChecked())
        {
            msg += tomatoSauceCheckBox.getText();
            priceForPizza += 8;
        }
        if(garlicSauceCheckBox.isChecked())
        {
            msg += garlicSauceCheckBox.getText();
            priceForPizza += 5.5;
        }
        if(oreganoCheckBox.isChecked())
        {
            msg += oreganoCheckBox.getText();
            priceForPizza += 4.6;
        }

        return msg;
    }

    private String getMsgOfBasicCheckBoxes() {
        String msg = "";
        if(hamCheckBox.isChecked())
        {
            msg += hamCheckBox.getText()  +" ";
            priceForPizza += 1.5;
        }
        if(cheeseCheckBox.isChecked())
        {
            msg += cheeseCheckBox.getText() +" ";
            priceForPizza += 2.5;
        }
        if(mushroomsCheckBox.isChecked())
        {
            msg += mushroomsCheckBox.getText() +" ";
            priceForPizza += 3.8;
        }
        if(olivesCheckBox.isChecked())
        {
            msg += olivesCheckBox.getText() +" ";
            priceForPizza += 4;
        }
        if(baconCheckBox.isChecked())
        {
            msg += baconCheckBox.getText() +" ";
            priceForPizza += 5.2;
        }
        if(chickenCheckBox.isChecked())
        {
            msg += chickenCheckBox.getText() +" ";
            priceForPizza += 6.6;
        }
        if(onionCheckBox.isChecked())
        {
            msg += onionCheckBox.getText() +" ";
            priceForPizza += 3;
        }

        return msg;
    }

    private String getMsgOfRadioButton(int radioButtonId) {

            if(thinDough.getId() == radioButtonId)
            {
                priceForPizza += 10;
                return getString(R.string.Thin);
            }
            else if(thickDough.getId() == radioButtonId)
            {
                priceForPizza += 15;
                return getString(R.string.thick);
            }
            else if(bigSize.getId() == radioButtonId)
            {
                priceForPizza += 8.5;
                return getString(R.string.Big);
            }
            else if(mediumSize.getId() == radioButtonId)
            {
                priceForPizza += 7.5;
                return getString(R.string.Middle);
            }
            else if(smallSize.getId() == radioButtonId)
            {
                priceForPizza += 6.5;
                return getString(R.string.Small);
            }

            return "";
    }

    private boolean canPizzaBeOrder() {
        if(countMinBasic < minBasicIngredients)
        {
            Toast.makeText(getApplicationContext(), R.string.Choose3Basic, Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public void onCheckboxClicked(View view) {
        boolean checked = ((CheckBox) view).isChecked();

        switch(view.getId()) {
            case R.id.checkHam:
            case R.id.checkCheese:
            case R.id.checkMushrroms:
            case R.id.checkOlives:
            case R.id.checkBacon:
            case R.id.checkChicken:
            case R.id.checkOnion:
                countMinBasicIngredients(checked);
                break;
            case R.id.checkGarlic:
            case R.id.checkSalami:
            case R.id.checkCapers:
            case R.id.checkTuna:
            case R.id.checkTomatoSauce:
            case R.id.checkGarlicSauce:
            case R.id.checkOregano:
                countMaxExtraIngredients(checked, (CheckBox) view);
                break;
        }
    }

    private void countMaxExtraIngredients(boolean checked, CheckBox view) {
        if(checked && countExtra == maxExtraIngredients)
        {
            view.setChecked(false);
            Toast.makeText(getApplicationContext(), R.string.LimitReached, Toast.LENGTH_SHORT).show();
        }
        else if(checked)
        {
            countExtra++;
        }
        else
        {
            countExtra--;
        }
    }

    private void countMinBasicIngredients(boolean add)
    {
        if(add)
            countMinBasic++;
        else
            countMinBasic--;
    }

}
