package org.techtown.retrofit_ex2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView t = (TextView) findViewById(R.id.textview_retrofit);

        t.setOnClickListener(v -> {
            GitHubService gitHubService = GitHubService.retrofit.create(GitHubService.class);
            final Call<List<Contributor>> call = gitHubService.repoContributors("square", "retrofit");
            new NetworkCall().execute(call);
        });

    }

    private class NetworkCall extends AsyncTask<Call, Void, String> {
        @Override
        protected String doInBackground(Call... params) {
            try {
                Call<List<Contributor>> call = params[0];
                Response<List<Contributor>> response = call.execute();
                return response.body().toString();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            final TextView textView = (TextView) findViewById(R.id.textview_retrofit);
            textView.setText(result);
        }
    }

}
