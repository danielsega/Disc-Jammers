package com.disc.jammers.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.disc.jammers.Constant;
import com.disc.jammers.DiscJammersMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
                
                config.width = Constant.WIDTH;
                config.height = Constant.HEIGHT;
                config.title = Constant.TITLE;
                
		new LwjglApplication(new DiscJammersMain(), config);
	}
}
