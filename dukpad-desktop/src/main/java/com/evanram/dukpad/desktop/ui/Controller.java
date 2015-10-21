package com.evanram.dukpad.desktop.ui;

import java.net.URL;
import java.util.ResourceBundle;

import org.fxmisc.richtext.CodeArea;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import lib.misc.SyntaxTextArea;

public final class Controller implements Initializable {
	private static Workspace workspace;
	
	public static Workspace getWorkspace() {
		return workspace;
	}
	
	public static void setWorkspace(Workspace workspace) {
		Controller.workspace = workspace;
	}
	
	// FXML //
	
	@FXML
	private CodeArea codeArea;
	
	@FXML
	private MenuItem 
		// File ...
		menuFileNew, menuFileOpen, menuFileSave, menuFileSaveAs,
		
		// Project ...
		menuProjectGenerateDiagram, menuProjectClose,
		
		// Help ...
		menuHelpMidiSettings, menuHelpAbout;
	
	@FXML
	private TableView<Object> scriptObjectTable;
	
	@FXML
	private TableColumn<Object, Object> scriptObjectTable_Object, scriptObjectTable_Comment;
	
	@Override
	public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
		setupCodeArea();
		setupMenuItems();
	}
	
	private void setupCodeArea() {
		final SyntaxTextArea syntaxTextArea = new SyntaxTextArea(codeArea);
		workspace.setSyntaxTextArea(syntaxTextArea);
		syntaxTextArea.appendText("function run() {");
		
		// focus the code area after stage.show()
		workspace.runLater(() -> codeArea.requestFocus());
	}
	
	private void setupMenuItems() {
		// File ...
		menuFileNew.setOnAction(event -> {
			System.out.println("File: New");
			
			// TODO file: new
		});
		
		menuFileOpen.setOnAction(event -> {
			System.out.println("File: Open");
			
			/*File projectDirectory = JavafxUtils.promptForDirectory();
			
			Project loadedProject = Project.load(projectDirectory);
			
			if(loadedProject == null) {
				Alert alert = new Alert(AlertType.ERROR, "This directory does not contain a project or the project is corrupted.");
				alert.setHeaderText("Error loading project!");
				alert.showAndWait();
			}
			
			workspace.setProject(loadedProject);*/
		});
		
		menuFileSave.setOnAction(event -> {
			System.out.println("File: Save");
			
			// TODO file: save
		});
		
		menuFileSaveAs.setOnAction(event -> {
			System.out.println("File: Save As");
			
			// TODO file: save as
		});
		
		// Project ...
		menuProjectGenerateDiagram.setOnAction(event -> {
			System.out.println("Project: Generate Diagram");
			
			// TODO project: generate diagram
		});
		
		menuProjectClose.setOnAction(event -> {
			System.out.println("Project: Close");
			
			// TODO project: close
		});
		
		// Help ...
		menuHelpMidiSettings.setOnAction(event -> {
			System.out.println("Help: Midi Settings");
			
			// TODO help: midi settings
		});
		
		menuHelpAbout.setOnAction(event -> {
			System.out.println("Help: About");
			
			Alert alert = new Alert(AlertType.INFORMATION, 
					"An application for creating and running JavaScript on the Launchpad.");
			alert.setHeaderText("LaunchPlay");
			alert.showAndWait();
		});
	}
}
