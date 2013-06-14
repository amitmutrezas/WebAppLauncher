package com.nokia.webapp.training;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import com.nokia.mid.ui.orientation.Orientation;
import com.nokia.mid.ui.orientation.OrientationListener;

public class WebAppLauncherMidlet extends MIDlet implements CommandListener, OrientationListener {
	private Command exit;
	private Command launch;
	private ChoiceGroup urlPOP;
	private TextField urlKey;
	private Form form;
	private StringItem text;
	private LaunchNokiaBrowser launchBrowser;
	private String url;
	
	public WebAppLauncherMidlet() {
		text = new StringItem("", "");
		form = new Form("WebAppLauncher");
		urlPOP = new ChoiceGroup ("Pop Up choice", Choice.POPUP, new String[] {"http://goo.gl/", "https://bitly.com/","http://tinyurl.com/"}, null);
		urlKey = new TextField("Enter Key: ", "", 10, TextField.ANY);
		exit = new Command("Exit", Command.EXIT, 1);
		launch = new Command("Launch", Command.SCREEN, 1);
		launchBrowser = new LaunchNokiaBrowser(this);
		
		form.addCommand(exit);
		form.addCommand(launch);
		form.append(urlPOP);
		form.append(urlKey);
		form.setCommandListener(this);
		
		Orientation.addOrientationListener(this);
	}
	public void commandAction(Command command, Displayable displayable) {
		if(command == exit) {
			this.notifyDestroyed();
		}
		else if(command == launch){
			url = urlPOP.getString(urlPOP.getSelectedIndex()) + urlKey.getString();
			launchBrowser.launchClient(url);
		}
	}

	protected void destroyApp(boolean unconditional)
			throws MIDletStateChangeException {
	}

	protected void pauseApp() {
	}

	protected void startApp() throws MIDletStateChangeException {
		Display.getDisplay(this).setCurrent(form);
	}

	public void displayOrientationChanged(int newDisplayOrientation) {
		if (newDisplayOrientation == Orientation.ORIENTATION_LANDSCAPE || newDisplayOrientation == Orientation.ORIENTATION_PORTRAIT) {
			Orientation.setAppOrientation(newDisplayOrientation);
		}
	}
	
	public void log(String message){
		text.setText(message);
	}
}
