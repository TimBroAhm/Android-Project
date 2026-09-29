package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoginActivity extends AppCompatActivity {

    TableLayout tableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tableLayout = findViewById(R.id.tableLayout);

        // Fetch data from the server
        new FetchUsersTask().execute();
    }

    // AsyncTask to fetch data from PHP API
    private class FetchUsersTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... voids) {
            String result = "";
            try {
                // PHP endpoint that returns the items in JSON format
                URL url = new URL("http://192.168.80.1/fetch_items.php");  // Update with your actual server URL
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            try {
                // Parse the JSON response
                JSONArray jsonArray = new JSONArray(result);

                // Create a header row dynamically (without the Photo column)
                TableRow headerRow = new TableRow(LoginActivity.this);
                String[] headers = {"SID", "First Name", "Last Name", "Age", "Gender", "Email", "Hospital", "Website", "Status", "Actions"};
                for (String header : headers) {
                    TextView textView = new TextView(LoginActivity.this);
                    textView.setText(header);
                    textView.setPadding(8, 8, 8, 8);
                    headerRow.addView(textView);
                }
                tableLayout.addView(headerRow);  // Add the header row to TableLayout

                // Loop through the JSON data and add rows to the table
                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject userObject = jsonArray.getJSONObject(i);

                    // Get the data from each column
                    String sid = userObject.getString("sid");
                    String fname = userObject.getString("sfname");
                    String lname = userObject.getString("slname");
                    String age = userObject.getString("sage");
                    String gender = userObject.getString("ssex");
                    String email = userObject.getString("semail");
                    String hospital = userObject.getString("hname");
                    String website = userObject.getString("website");
                    String status = userObject.getString("sstatus");

                    // Create a new row for each user and add TextViews with the fetched data
                    TableRow tableRow = new TableRow(LoginActivity.this);

                    addColumnToRow(tableRow, sid);
                    addColumnToRow(tableRow, fname);
                    addColumnToRow(tableRow, lname);
                    addColumnToRow(tableRow, age);
                    addColumnToRow(tableRow, gender);
                    addColumnToRow(tableRow, email);
                    addColumnToRow(tableRow, hospital);
                    addColumnToRow(tableRow, website);
                    addColumnToRow(tableRow, status);

                    // Add actions (Edit, Copy, Delete buttons)
                    TableRow actionRow = new TableRow(LoginActivity.this);
                    Button editButton = new Button(LoginActivity.this);
                    editButton.setText("Edit");
                    Button copyButton = new Button(LoginActivity.this);
                    copyButton.setText("Copy");
                    Button deleteButton = new Button(LoginActivity.this);
                    deleteButton.setText("Delete");

                    actionRow.addView(editButton);
                    actionRow.addView(copyButton);
                    actionRow.addView(deleteButton);
                    tableRow.addView(actionRow); // Add actions to the table row

                    tableLayout.addView(tableRow); // Add the user row to the TableLayout
                }

            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(LoginActivity.this, "Error fetching data", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Helper method to add a TextView to a TableRow
    private void addColumnToRow(TableRow row, String data) {
        TextView textView = new TextView(LoginActivity.this);
        textView.setText(data);
        textView.setPadding(8, 8, 8, 8);
        row.addView(textView);
    }
}
