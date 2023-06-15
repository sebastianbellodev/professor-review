package com.example.professorperformanceevaluation.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.Test;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.service.TestService;
import com.example.professorperformanceevaluation.service.service.UserService;
import com.example.professorperformanceevaluation.utilities.Utilities;

import java.net.HttpURLConnection;

public class LoginViewModel extends ViewModel {

    private Context context;
    private UserService userService;
    private TestService testService;

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    public void setContext(Context context) {
        this.context = context;
        this.userService = new UserService(context);
        this.testService = new TestService(context);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void onLoginClicked() {;
        String username = this.username.getValue();
        String password = this.password.getValue();
        password = Utilities.computeSHA256Hash(password);
        User user = new User(username, password);
        userService.login(user, new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
            }

            @Override
            public void onFailure(Throwable throwable) {
                Response response = new Response();
                response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
                response.setMessage(throwable.getMessage());
            }
        });
    }

    public void test() {
        testService.ping(new TestService.TestServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                Test test = response.test;
                Toast.makeText(context, "Result " + test.getResult(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(context, "Test failed...", Toast.LENGTH_SHORT).show();
                System.err.println(throwable);
            }
        });
    }

    public void onSignUpClicked() {
        test();
    }

}