package com.example.blunter;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class BlunterApplication extends Application {
	
	@Override
	public void onCreate(){
	super.onCreate();
	Parse.initialize(this, "puBgWKsabIeCRAf2frAzEeD45KBd4S6QVsccclIa", "2rdQAn0R8dZHyknaFM747P121R0jRiRgyldFLp89");
	}

}
