package com.evanram.dukpad.desktop.ui;

import java.util.LinkedList;
import java.util.Queue;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import lib.misc.SyntaxTextArea;

public final class Workspace extends Application {
	private Parent root;
	private Stage stage;
	private SyntaxTextArea syntaxTextArea;
	private Queue<Runnable> runLaterQueue = new LinkedList<>();

	public static void launch(String... args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		this.stage = stage;
		JavafxUtils.stage = stage;
		
		stage.addEventHandler(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
			Platform.exit();
			System.exit(0);
		});

		Controller.setWorkspace(this);
		this.root = FXMLLoader.load(ClassLoader.getSystemResource("ui/workspace.fxml"));
		
		Scene scene = new Scene(root);
		stage.setTitle("Workspace");
		stage.setScene(scene);
		
		stage.show();
		
		runLaterQueue.forEach(task -> task.run());
		
		runLaterQueue = null;
	}

	public Parent getRoot() {
		return root;
	}
	
	public Stage getStage() {
		return stage;
	}

	public SyntaxTextArea getSyntaxTextArea() {
		return syntaxTextArea;
	}
	
	public void setSyntaxTextArea(SyntaxTextArea syntaxTextArea) {
		this.syntaxTextArea = syntaxTextArea;
	}
	
	public void runLater(Runnable r) {
		if(runLaterQueue == null) {
			r.run();
		} else {
			runLaterQueue.add(r);
		}
	}
}
