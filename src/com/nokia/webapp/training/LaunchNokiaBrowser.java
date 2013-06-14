package com.nokia.webapp.training;

import javax.microedition.io.ConnectionNotFoundException;

public class LaunchNokiaBrowser {
	private WebAppLauncherMidlet webAppLauncherMidlet;
	
	public LaunchNokiaBrowser(WebAppLauncherMidlet webAppLauncherMidlet) {
		this.webAppLauncherMidlet = webAppLauncherMidlet;
	}
	
	/*public void launchNokiaBrwoser(String launchNokiaBrowserURL){
		MIDletRegistry registry = MIDletRegistry.getMIDletRegistry();
	}*/
	
	protected void launchClient(String url){
		try {
			webAppLauncherMidlet.platformRequest(url);
		} catch (ConnectionNotFoundException e) {
			webAppLauncherMidlet.log("Failed to launch native browser using URL");
		}
	}
}