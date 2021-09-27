package com.phr.wiki;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.phr.R;

public class DrugInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_info);
        ImageButton return_btn=findViewById(R.id.ib_returnIcon);
        return_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                //Intent it=new Intent();
                //it.setClass(DrugInfoActivity.this,DrugInquiryActivity.class);
                //startActivity(it);
            }
        });
        Bundle extras=getIntent().getExtras();
        String item=extras.getString("drugNameArray");
        TextView title = (TextView) findViewById (R.id.tv_drugname);
        title.setText(item);
        String vertinum_item=extras.getString("vertinumArray");
        TextView vertinum = (TextView) findViewById (R.id.tv_vertinum);
        vertinum.setText(vertinum_item);
        String ingredient_item=extras.getString("ingredientArray");
        TextView ingredient = (TextView) findViewById (R.id.tv_ingredient);
        ingredient.setText(ingredient_item);
        String manifunc_item=extras.getString("manifuncArray");
        TextView manifunc = (TextView) findViewById (R.id.tv_mainfunc);
        manifunc.setText(manifunc_item);
        String dosage_item=extras.getString("dosageArray");
        TextView dosage = (TextView) findViewById (R.id.tv_dosage);
        dosage.setText(dosage_item);
        String tatoo_item=extras.getString("tatooArray");
        TextView tatoo = (TextView) findViewById (R.id.tv_tatoo);
        tatoo.setText(tatoo_item);
        String adverseaction_item=extras.getString("adverseactionArray");
        TextView adverseaction = (TextView) findViewById (R.id.tv_adverseaction);
        adverseaction.setText(adverseaction_item);
        String attention_item=extras.getString("attentionArray");
        TextView attention = (TextView) findViewById (R.id.tv_attention);
        attention.setText(attention_item);
        String standards_item=extras.getString("standardsArray");
        TextView standards = (TextView) findViewById (R.id.tv_standards);
        standards.setText(standards_item);
        String productor_item=extras.getString("productorArray");
        TextView productor = (TextView) findViewById (R.id.tv_productor);
        productor.setText(productor_item);
        String category_item=extras.getString("categoryArray");
        TextView category = (TextView) findViewById (R.id.tv_category);
        category.setText(category_item);
        Double price_item=extras.getDouble("priceArray");
        TextView price = (TextView) findViewById (R.id.tv_price);
        price.setText(price_item.toString());


    }
}