package com.dungeonmaster.dashboard.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.dungeonmaster.dashboard.Dashboard;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Dungeon Master Dashboard";
		config.width = 2000;
		config.height = (int) (config.width * 3f / 4f);
		new LwjglApplication(new Dashboard(), config);
	}
}
