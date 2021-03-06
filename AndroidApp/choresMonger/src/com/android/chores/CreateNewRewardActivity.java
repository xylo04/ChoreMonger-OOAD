package com.android.chores;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import com.choremonger.shared.Reward;

public class CreateNewRewardActivity extends Activity implements OnClickListener {
    
	private static String TAG=CreateNewRewardActivity.class.getName();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_new_reward);
        View ButtonCreateReward= findViewById(R.id.Button_Create_Reward);
        ButtonCreateReward.setOnClickListener(this);
    }
        @Override
    	public void onClick(View v) {
        	switch(v.getId()){
        		case R.id.Button_Create_Reward:
        			try{
        			// create reward 
        			double pointsValue=(Double.parseDouble(((EditText)findViewById(R.id.txviewCreateRewardPointsVal)).getText().toString()));
        			String rewardName=((EditText)findViewById(R.id.editTxtCreateRewardTitleVal)).getText().toString();
        			String description=((EditText)findViewById(R.id.editxtCreateRewardDescrVal)).getText().toString();
        			CheckBox isOneTimeRewardChckBox=(CheckBox)findViewById(R.id.checkboxCreateRewardIsOneTime);
        			
        			boolean isOneTimeReward;
        			if(isOneTimeRewardChckBox.isChecked())
        				isOneTimeReward=true;
        			else
        				isOneTimeReward=false;
        			createReward(description, rewardName, pointsValue, isOneTimeReward);
        			}
        			catch(Exception exception){
        				Log.e(TAG,exception.getMessage());
        			}
        		break;
        	}
    	}
        
        public void createReward(String description,String rewardName,double pointsValue,boolean isOneTimeReward){
        	Reward reward=new RewardImpl("",description,rewardName,pointsValue,isOneTimeReward,"");
        	Reward createdReward=RewardImpl.createReward(reward);
        	if (createdReward==null){
				Log.e(TAG,"Oooops! No rewards created!");
				return;
			}
    		Intent rewardCenterIntent=new Intent(this,RewardsCenterActivity.class);
    		rewardCenterIntent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
    		startActivity(rewardCenterIntent);
			Log.d(TAG,"Reward Created");
			Log.d(TAG,createdReward.getName());
			Log.d(TAG,createdReward.getId());
        }
}
