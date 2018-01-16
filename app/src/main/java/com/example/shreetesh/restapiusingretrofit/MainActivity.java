package com.example.shreetesh.restapiusingretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shreetesh.restapiusingretrofit.domain.User;
import com.example.shreetesh.restapiusingretrofit.domain.UserList;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = " <= CreateActivity => ";

    APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

    @BindView(R.id.responseText)
    TextView responseText;
    @BindView(R.id.fullName_text)
    EditText _fullNameTxt;
    @BindView(R.id.job_text)
    EditText _jobTxt;
    @BindView(R.id.createdAt_text)
    EditText _createdAtTxt;
    @BindView(R.id.btn_submit)
    Button _submitBtn;
    @BindView(R.id.btn_userList)
    Button _userListBtn;

    //get users
    Call<UserList> call2 = apiInterface.doGetUserList("2");


//Get List Resources
      /*  Call<MultipleResource> call = apiInterface.doGetListResources();
        call.enqueue(new Callback<MultipleResource>() {
            @Override
            public void onResponse(Call<MultipleResource> call, Response<MultipleResource> response) {
                Log.d("TAG", response.code() + "");
                String displayResponse = "";
                MultipleResource resource = response.body();
                Integer text = resource.page;
                Integer total = resource.total;
                Integer totalPages = resource.total_pages;
                List<MultipleResource.Datum> datumList = resource.data;

                displayResponse += text + "Page\n " + total + "Total\n" + totalPages + "Total Pages\n";

                for (MultipleResource.Datum datum : datumList) {
                    displayResponse += datum.id + "" + datum.name + "" + datum.pantoneValue + "" + datum.year + "\n";
                }
                responseText.setText(displayResponse);
            }

            @Override
            public void onFailure(Call<MultipleResource> call, Throwable t) {
              t.printStackTrace();
            }
        });
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_userlist);
        ButterKnife.bind(this);

        _submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User("morpheus", "leader");
                Call<User> call1 = apiInterface.createUser(user);
                call1.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        Log.d(TAG, response.body() + "");
                        if (response.body().getClass() != null) {
                            Log.d(TAG, response.body() + "");
                            _fullNameTxt.setText(response.body().getClass().getName());
                            _jobTxt.setText(response.body().toString());
                            _createdAtTxt.setText(response.body().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

        _userListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call2.enqueue(new Callback<UserList>() {
                    @Override
                    public void onResponse(Call<UserList> call, Response<UserList> response) {
                        UserList userList = response.body();
                        Integer text = userList.page;
                        Integer total = userList.total;
                        Integer totalPages = userList.totalPages;
                        List<UserList.Datum> datumList = userList.data;

                        Toast.makeText(getApplicationContext(), text + "page\n" + total + "total\n" + totalPages + "totalPages\n", Toast.LENGTH_SHORT).show();
                        for (UserList.Datum datum : datumList) {
                            Toast.makeText(getApplicationContext(), "id: " + datum.id + "name: " + datum.first_name + "" + datum.last_name + "avatar:" + datum.avatar, Toast.LENGTH_SHORT);

                        }
                    }

                    @Override
                    public void onFailure(Call<UserList> call, Throwable t) {
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}

/*
        //post name and job url encoded
        Call<UserList> call3 = apiInterface.doCreateUserWithField("morpheus", "leader");
        call3.enqueue(new Callback<UserList>() {
            @Override
            public void onResponse(Call<UserList> call, Response<UserList> response) {
                UserList userList = response.body();
                Integer text = userList.page;
                Integer total = userList.total;
                Integer totalPages = userList.totalPages;
                List<UserList.Datum> datumList = userList.data;
                Toast.makeText(getApplicationContext(), text + " page\n" + total + " total\n" + totalPages + " totalPages\n", Toast.LENGTH_SHORT).show();

                for (UserList.Datum datum : datumList) {
                    Toast.makeText(getApplicationContext(), "id : " + datum.id + " name: " + datum.first_name + " " + datum.last_name + " avatar: " + datum.avatar, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserList> call, Throwable t) {
               t.printStackTrace();
            }
        });


    }*/
//}