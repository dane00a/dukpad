package com.evanram.dukpad.desktop.ui;

import java.io.File;
import java.util.Optional;

import javafx.scene.Node;
import javafx.scene.control.TextInputDialog;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public final class JavafxUtils {
	public static Stage stage;
	
	private JavafxUtils() {
		throw new UnsupportedOperationException();
	}

	public static File promptForDirectory() {
		return promptForDirectory(stage);
	}
	
	public static File promptForDirectory(Stage stage) {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Choose Folder");
		directoryChooser.setInitialDirectory(new File("."));

		return directoryChooser.showDialog(stage);
	}
	
	public static String showInputDialog(String message, String defaultValue) {
		TextInputDialog dialog = new TextInputDialog(defaultValue);
		dialog.setContentText(message);
		
		Optional<String> value = dialog.showAndWait();
		
		if(value.isPresent()) {
			return value.get();
		} else {
			return defaultValue;
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T extends Node> T getNode(Node root, String id) {
		final Node node = root.lookup(id);
		if(node == null) {
			throw new NullPointerException("cannot find child node fx:id for argument: " + id);
		}

		return (T) node;
	}
}
