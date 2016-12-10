package com.yaphetzhao.peargreendao.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.jakewharton.rxbinding.view.RxView;
import com.yaphetzhao.peargreendao.R;
import com.yaphetzhao.peargreendao.adapter.PearAdapter;
import com.yaphetzhao.peargreendao.application.BaseApplication;
import com.yaphetzhao.peargreendao.bean.PearNote;
import com.yaphetzhao.peargreendao.presenter.IPearPresenter;
import com.yaphetzhao.peargreendao.presenter.PearPresenter;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private IPearPresenter presenter = new PearPresenter();
    private PearAdapter adapter;

    @BindView(R.id.edt_insert_key)
    EditText edtInsertKey;
    @BindView(R.id.edt_insert_value)
    EditText edtInsertValue;
    @BindView(R.id.btn_insert)
    Button btnInsert;
    @BindView(R.id.btn_delete)
    Button btnDelete;
    @BindView(R.id.btn_searchall)
    Button btnSearchall;
    @BindView(R.id.list_show)
    ListView listShow;
    @BindView(R.id.activity_main)
    LinearLayout activityMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initAdapter();
        initListener();
    }

    private void initAdapter() {
        adapter = new PearAdapter(this);
        adapter.setList(presenter.searchAll());
        listShow.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        listShow.setOnItemLongClickListener((adapterView, view, i, l) -> {
            presenter.delete((PearNote) adapter.getItem(i));
            Toast.makeText(BaseApplication.getInstance(), "Delete Item Success!", Toast.LENGTH_SHORT).show();
            adapter.setList(presenter.searchAll());
            adapter.notifyDataSetChanged();
            return false;
        });
    }

    private void initListener() {
        RxView.clicks(btnInsert)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    String key = edtInsertKey.getText().toString();
                    String value = edtInsertValue.getText().toString();
                    presenter.insert(key, value);
                    adapter.setList(presenter.searchAll());
                    adapter.notifyDataSetChanged();
                });
        RxView.clicks(btnDelete)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    presenter.deleteAll();
                    adapter.setList(presenter.searchAll());
                    adapter.notifyDataSetChanged();
                });
        RxView.clicks(btnSearchall)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    Toast.makeText(BaseApplication.getInstance(), "ToDo -> Comming Soon!", Toast.LENGTH_SHORT).show();
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}