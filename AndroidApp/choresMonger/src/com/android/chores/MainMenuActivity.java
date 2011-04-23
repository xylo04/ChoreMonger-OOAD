package com.android.chores;

import java.util.Date;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.choremonger.shared.User;

public class MainMenuActivity extends Activity implements OnClickListener {
	private static String user_id;
	private User current_user;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
        user_id=sharedprefs.getString("USER_ID", "");
        if(user_id==null||user_id==""){
        	Intent intent=new Intent(this,WelcomeActivity.class);
        	intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        	startActivity(intent);
        }
        else
        {
        	current_user=UserImpl.getUser(user_id);
        
        	setContentView(R.layout.main_menu);
        
        

        	//  Display user name and points
        	TextView txvwWelcomeView=(TextView)findViewById(R.id.TextView_greeting);
        	txvwWelcomeView.setText(getGreetingExpression());
        	TextView txvwDisplayNameVal=(TextView)findViewById(R.id.TextView_DisplayNameVal);
        	txvwDisplayNameVal.setText(current_user.getName());
        	TextView txvwAssingedChoresVal=(TextView)findViewById(R.id.TextView_assignedChoresVal);
        	//TODO: Get the number of chores assigned to this user 
        	txvwAssingedChoresVal.setText("");
        
        	TextView txvwEarnedPointsVal=(TextView)findViewById(R.id.TextView_usrEarnedPointsVal);
        	txvwEarnedPointsVal.setText(Double.toString(current_user.getRewardPoints()));
        
        	View ButtonMyChores=findViewById(R.id.Button_myChores);
        	View ButtonChoresManagement=findViewById(R.id.Button_ChoresManagement);
        	View ButtonRewardsCenter=findViewById(R.id.Button_RewardsCenter);
        	View ButtonProfile=findViewById(R.id.Button_Profile);
        	View ButtonSettings=findViewById(R.id.Button_Settings);
       
        	ButtonMyChores.setOnClickListener(this);
        	ButtonChoresManagement.setOnClickListener(this);
        	ButtonRewardsCenter.setOnClickListener(this);
        	ButtonProfile.setOnClickListener(this);
        	ButtonSettings.setOnClickListener(this);
        }
    }
    
    //prevent this activity from going to the welcome in activity if the user dosen't log out
   /* @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (  Integer.valueOf(android.os.Build.VERSION.SDK) < 7
                && keyCode == KeyEvent.KEYCODE_BACK
                && event.getRepeatCount() == 0) {
            onBackPressed();
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
    	finish();
    }
*/
    @Override
	public void onClick(View v) {
    	switch(v.getId()){
    		case R.id.Button_myChores:
    			// launch my chores activity here
    		break;
    		case R.id.Button_ChoresManagement:
    			startActivity(new Intent(this,ChoreManagementActivity.class));
    			// launch chores management activity here
    		break;
    		case R.id.Button_RewardsCenter:
    			startActivity(new Intent(this,RewardsCenterActivity.class));
    			// launch rewards center activity here
    		break;
    		case R.id.Button_Profile:
    			// launch profile activity here
    		break;
    		case R.id.Button_Settings:
    			// launch settings activity here
    		break;
    	}
	}
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
        case R.id.logout:
            logout();
            return true;
        case R.id.help:
            showHelp();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    private void logout(){
		SharedPreferences sharedprefs=getSharedPreferences(SignInActivity.PREFS_NAME, Context.MODE_PRIVATE);
		if(sharedprefs.getString("USER_ID", "")!=null)	{
			SharedPreferences.Editor editor=sharedprefs.edit().remove("USER_ID");
			editor.commit();
			Intent logoutIntent=new Intent(this,WelcomeActivity.class);
			logoutIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
			startActivity(logoutIntent);
		}
			
    }
    private void showHelp(){
    	
    }
    private String getGreetingExpression(){
    	
    	 Date dt = new Date();
         int hours = dt.getHours();
         
    	if(hours>=12 && hours<=18)
    		return "Good afternoon  ";
    	else if(hours>18 && hours<23)
    		return "Good evening  ";
    	else 
    		return "Good morning  ";
    		
    	}

    }
   



