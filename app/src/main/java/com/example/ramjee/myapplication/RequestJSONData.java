package com.example.ramjee.myapplication;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class RequestJSONData extends Activity {
	String result = null;
	Context context;
	DataSourceManager dsm =new DataSourceManager(context);
	List<FoodRecord> foodList = new ArrayList<>();
	List<ExerciseRecord> exerciseList = new ArrayList<>();
	List<FoodChoiceRecord> foodChList = new ArrayList<>();
	List<ExerciseChoiceRecord> exerciseChList = new ArrayList<>();
	List<LoginRecord> loginList = new ArrayList<>();
	LoginRecord loginRecord = null;

	public List<FoodChoiceRecord> getFoodChList() {
		return foodChList;
	}

	public void setFoodChList(List<FoodChoiceRecord> foodChList) {
		this.foodChList = foodChList;
	}

	public List<ExerciseChoiceRecord> getExerciseChList() {
		return exerciseChList;
	}

	public void setExerciseChList(List<ExerciseChoiceRecord> exerciseChList) {
		this.exerciseChList = exerciseChList;
	}

	public List<LoginRecord> getLoginList() {
		return loginList;
	}

	public List<ExerciseRecord> getExerciseList() {
		return exerciseList;
	}

	public void setExerciseList(List<ExerciseRecord> exerciseList) {
		this.exerciseList = exerciseList;
	}

	public void setLoginList(List<LoginRecord> loginList) {
		this.loginList = loginList;
	}

	public LoginRecord getLoginRecord() {
		return loginRecord;
	}

	public void setLoginRecord(LoginRecord loginRecord) {
		this.loginRecord = loginRecord;
	}

	public List<FoodRecord> getFoodList() {
		return foodList;
	}

	public void setFoodList(List<FoodRecord> foodList) {
		this.foodList = foodList;
	}

	public void getResponse(String URL) {
		InputStream is = null;

			try {

					HttpClient httpclient = new DefaultHttpClient();
					HttpPost httppost = new HttpPost(URL);
					HttpResponse response = httpclient.execute(httppost);
					HttpEntity entity = response.getEntity();
					is = entity.getContent();

				Log.e("log_tag", "connection success");
				//   Toast.makeText(getApplicationContext(), "pass ", Toast.LENGTH_SHORT).show();
			} catch (Exception e) {
				//Log.e("log_tag", "Error in http connection " + e.toString());
				//Toast.makeText(getApplicationContext(), "Connection fail", Toast.LENGTH_SHORT).show();

			}
			//convert response to string
			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(is, "iso-8859-1"), 8);
				StringBuilder sb = new StringBuilder();
				String line = null;
				while ((line = reader.readLine()) != null) {
					sb.append(line + "\n ");
					//  Toast.makeText(getApplicationContext(), "Input Reading pass ", Toast.LENGTH_SHORT).show();
				}
				is.close();

				result = sb.toString();
			} catch (Exception e) {
				Log.e("log_tag ", "Error converting result" + e.toString());
				//Toast.makeText(getApplicationContext(), "Input reading fail ", Toast.LENGTH_SHORT).show();

			}

	}

	public void parseJSON(String option) {
			//parse json data
			try {
				JSONArray jArray = new JSONArray(result);

				int flag = 1;
				for (int i = 0; i < jArray.length()-1 ; i++) {
					JSONObject json_data = jArray.getJSONObject(i);
					switch (option) {
						case "FT":
							setFoodContent(json_data);
							break;
						case "FDCH":
							setFoodChContent(json_data);
							break;
						case "EXCH":
							setExerciseChContent(json_data);
							break;
						case "EX":
							setExerciseContent(json_data);
							break;
						case "LI":
							setLoginContent(json_data);
							break;
					}

					//Log.i("log_tag ",  "id: " + json_data.getInt("Id ") + ", Username: " + json_data.getString("username ") + ", No: " + json_data.getString("comment "));

				}
			} catch (JSONException e) {
				Log.e("log_tag ", "Error parsing data" + e.toString());
				//Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
			}
		}
	public void setFoodContent(JSONObject json_data) {

		try {
			Log.d("Record",json_data.getString("foodName")+""+json_data.getInt("calories"));
			FoodRecord fr = new FoodRecord(json_data.getString("foodName"),json_data.getInt("calories"));
			foodList.add(fr);

		} catch (JSONException e) {
			Log.e("log_tag ", "Error parsing data" + e.toString());
			Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
		}


	}
	public void setFoodChContent(JSONObject json_data) {

		try {
			Log.d("FdRecord", ""+ json_data.getInt("foodID"));
			FoodChoiceRecord fr = new FoodChoiceRecord(json_data.getInt("foodID"));
			foodChList.add(fr);

		} catch (JSONException e) {
			Log.e("log_tag ", "Error parsing data" + e.toString());
			Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
		}


	}
	public void setExerciseChContent(JSONObject json_data) {

		try {
			Log.d("ExRecord",""+json_data.getInt("exID"));
			ExerciseChoiceRecord fr = new ExerciseChoiceRecord(json_data.getInt("exID"));
			exerciseChList.add(fr);

		} catch (JSONException e) {
			Log.e("log_tag ", "Error parsing data" + e.toString());
			Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
		}


	}
	public void setExerciseContent(JSONObject json_data) {

		try {
			Log.d("Record",json_data.getString("exeType")+""+json_data.getDouble("expend"));
			ExerciseRecord fr = new ExerciseRecord(json_data.getString("exeType"),json_data.getDouble("expend"));
			exerciseList.add(fr);

		} catch (JSONException e) {
			Log.e("log_tag ", "Error parsing data" + e.toString());
			Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
		}


	}
	public void setLoginContent(JSONObject json_data) {

		try {
			Log.d("Login Record", json_data.getString("userName") + "" + json_data.getString("password"));
			loginRecord = new LoginRecord(json_data.getString("userID"),json_data.getString("userName"),
					json_data.getString("password"),
					json_data.getString("email"),
					json_data.getDouble("height"),
					json_data.getDouble("weight"),
					json_data.getString("sex"),
					json_data.getString("DOB")
					);
			loginList.add(loginRecord);

		} catch (JSONException e) {
			Log.e("log_tag ", "Error parsing data" + e.toString());
			Toast.makeText(getApplicationContext(), "JsonArray fail ", Toast.LENGTH_SHORT).show();
		}


	}




	}
