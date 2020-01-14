package com.example.roomdaggermvvm.view;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.roomdaggermvvm.R;
import com.example.roomdaggermvvm.databinding.ActivityMainBinding;
import com.example.roomdaggermvvm.persistance.ArticleModel;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

import javax.inject.Inject;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainActivityVM> {

    private ActivityMainBinding binding;
    private ArticlesAdapter articlesAdapter;
    private AlertDialog alertDialog;

    @Inject
    MainActivityVM mainActivityVM;

    @Override
    public int getBindingVariable() {
        return com.example.roomdaggermvvm.BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainActivityVM getViewModel() {
        return mainActivityVM;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=getViewDataBinding();

        binding.carsItemsList.setLayoutManager(new LinearLayoutManager(this));

        mainActivityVM.mArticleRepository.getAllArticles().observe(this,articleModels -> {
                if (articleModels!=null) {
                    articlesAdapter=new ArticlesAdapter(this,new ArrayList<>(articleModels));
                    binding.carsItemsList.setAdapter(articlesAdapter);
                }
        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewItem();
            }
        });
    }

    public void addNewItem() {
        androidx.appcompat.app.AlertDialog.Builder dialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(this,R.style.DialogSlideAnim);
        LayoutInflater inflater = this.getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.custom_dialog_with_btns_view, null);
        dialogBuilder.setView(dialogView);
        AppCompatTextView dialogHeader=dialogView.findViewById(R.id.dialogHeader);
        MaterialButton ok_button=dialogView.findViewById(R.id.ok_button);
        MaterialButton cancel_button=dialogView.findViewById(R.id.cancel_button);
        EditText desc_ed=dialogView.findViewById(R.id.desc_ed);
        EditText title_ed=dialogView.findViewById(R.id.title_ed);
        alertDialog = dialogBuilder.create();
        if (alertDialog.getWindow()!=null) {
            alertDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            alertDialog.getWindow().setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        }

        ok_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(title_ed.getText()) && !TextUtils.isEmpty(desc_ed.getText())) {
                    ArticleModel articleModel=new ArticleModel(title_ed.getText().toString(),desc_ed.getText().toString());
                    mainActivityVM.mArticleRepository.insert(articleModel);
                    alertDialog.dismiss();
                }
            }
        });

        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        if (alertDialog.getWindow()!=null) {
//            WindowManager.LayoutParams wmlp = alertDialog.getWindow().getAttributes();
//            wmlp.width = WindowManager.LayoutParams.MATCH_PARENT;
//            wmlp.gravity = Gravity.TOP;
//            wmlp.y = 150;
        }
        alertDialog.setCanceledOnTouchOutside(true);
        alertDialog.show();
    }
}
