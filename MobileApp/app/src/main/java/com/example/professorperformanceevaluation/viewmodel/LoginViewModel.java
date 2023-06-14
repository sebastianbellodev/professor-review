package com.example.professorperformanceevaluation.viewmodel;

import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.professorperformanceevaluation.model.Response;
import com.example.professorperformanceevaluation.model.User;
import com.example.professorperformanceevaluation.service.UserService;
import com.example.professorperformanceevaluation.utilities.Utilities;

import java.net.HttpURLConnection;

public class LoginViewModel extends ViewModel {

    private Context context;
    private UserService userService;

    private MutableLiveData<String> username = new MutableLiveData<>();
    private MutableLiveData<String> password = new MutableLiveData<>();

    public void setContext(Context context) {
        this.context = context;
        this.userService = new UserService(context);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public MutableLiveData<String> getPassword() {
        return password;
    }

    public void onLoginClicked() {;
        System.out.println("Processing...");
        String username = this.username.getValue();
        String password = this.password.getValue();
        password = Utilities.computeSHA256Hash(password);
        User user = new User(username, password);
        userService.signUp(new UserService.UserServiceCallback() {
            @Override
            public void onSuccess(Response response) {
                Toast.makeText(context, "Successful login!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Throwable throwable) {
                Response response = new Response();
                response.setCode(HttpURLConnection.HTTP_INTERNAL_ERROR);
                response.setMessage(throwable.getMessage());
                System.out.println(throwable);
                Toast.makeText(context, "Failed request...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onSignUpClicked() {
    }

}