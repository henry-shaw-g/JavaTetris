package main;

import java.lang.System;
import java.awt.event.KeyEvent;
import input.*;
import graphics.*;
import scene.*;
import tetris.TetrisConfig;

enum GameStatus {
	Running,
}

public class Main implements Runnable, InputReciever {
	// overall game config
	private static long THREAD_SLEEP_DURATION = 10;
	
	private Window window;
	private Thread thread;
	private long lastUpdate;
	private GameStatus status; 
	
	private InputManager inputManager;
	
	public Scene currentScene;
	public Scene startMenuScene;
	public Scene tetrisScene;
	public Scene endMenuScene;
	
	
	Main() {
		// load resources
		ResourceLoader.load();
		
		// get graphics
		window = new Window(TetrisConfig.getTetrisCanvasWidth(), TetrisConfig.getTetrisCanvasHeight(), "Game");
		
		// get scenes
		startMenuScene = new StartMenuScene(this, window);
		endMenuScene = null;
		tetrisScene = new TetrisScene(this, window);
		changeScene(startMenuScene);
		
		// get input
		inputManager = new InputManager(this);
		inputManager.attach(window.getCanvas());
		
		// run thread
		thread = new Thread(this);
		status = GameStatus.Running;
		thread.start(); 
	}
	
	public void run() {
		lastUpdate = System.currentTimeMillis();
		while (status == GameStatus.Running) {
			long now = System.currentTimeMillis();
			update((double)(now - lastUpdate)/1000.0);
			lastUpdate = now;
			try {
			Thread.sleep(THREAD_SLEEP_DURATION);
			} catch(Exception e) {
				System.out.println("Error performing thread sleep.");
			}
		}
	}
	
	private void update(double deltaTime) {
		if (currentScene != null) {
			currentScene.update(deltaTime);
		}
	}

	public void onKeyPressed(KeyEvent event) {
		if (currentScene != null)
			currentScene.onKeyPressed(event);
	};
	
	public void onKeyReleased(KeyEvent event) {
		if (currentScene != null)
			currentScene.onKeyReleased(event);
	};
	
	public void changeScene(Scene targetScene) {
		if (currentScene != null)
			currentScene.setActive(false);
		currentScene = targetScene;
		currentScene.setActive(true);
	}
	
	public Window getWindow() {
		return window;
	}
	
	public static void main(String[] args) {
		new Main();
	}
}
