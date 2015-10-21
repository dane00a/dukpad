package lib.misc;

import java.time.Duration;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.concurrent.Task;
import javafx.scene.input.KeyEvent;

import org.apache.commons.lang3.StringUtils;
import org.fxmisc.richtext.CodeArea;
import org.fxmisc.richtext.PlainTextChange;
import org.fxmisc.richtext.StyleSpans;
import org.fxmisc.richtext.StyleSpansBuilder;
import org.reactfx.EventStream;
import org.reactfx.util.Try;

/**
 * @author https://github.com/TomasMikula/RichTextFX/blob/master/richtextfx-demos/src/main/java/org/fxmisc/richtext/demo/JavaKeywords.java
 * @author http://stackoverflow.com/a/24795572 (user: DeepSidhu1313)
 * (Modified)
 */
public class SyntaxTextArea {

	private static final String[] KEYWORDS = new String[] {
		// Java
		"abstract", "assert", "boolean", "break", "byte",
		"case", "catch", "char", "class", "const",
		"continue", "default", "do", "double", "else",
		"enum", "extends", "final", "finally", "float",
		"for", "goto", "if", "implements", "import",
		"instanceof", "int", "interface", "long", "native",
		"new", "package", "private", "protected", "public",
		"return", "short", "static", "strictfp", "super",
		"switch", "synchronized", "this", "throw", "throws",
		"transient", "try", "void", "volatile", "while",
		
		// JavaScript
		"arguments", "debugger", "delete", "eval", "export",
		"function", "in", "let", "typeof", "var", "with", 
		"yield",
	};
	
	// Application-specific
	private static final String[] LAUNCHPLAY_RESERVED;
	static {
		LAUNCHPLAY_RESERVED = new String[8 * 8 /* main btns */ + 8 * 2 /* ctrl btns */ + 1 /* pad */];
		int pos = 0;
		
		// main btns
		for(int col = 1; col <= 8; col++) {
			for(char row = 'A'; row <= 'H'; row++) {
				LAUNCHPLAY_RESERVED[pos++] = "b_" + col + row;
			}
		}
		
		// ctrl 1-8 and ctrl A-H
		for(int i = 1; i <= 8; i++) {
			LAUNCHPLAY_RESERVED[pos++] = "c_" + i;
			LAUNCHPLAY_RESERVED[pos++] = "c_" + (char) (i - 1 + 'A');
		}
		
		LAUNCHPLAY_RESERVED[pos] = "pad";
	}

	private static final String KEYWORD_PATTERN = "\\b(" + String.join("|", KEYWORDS) + '|' + String.join("|", LAUNCHPLAY_RESERVED) + ")\\b";
	private static final String PAREN_PATTERN = "\\(|\\)";
	private static final String BRACE_PATTERN = "\\{|\\}";
	private static final String BRACKET_PATTERN = "\\[|\\]";
	private static final String SEMICOLON_PATTERN = "\\;";
	private static final String STRING_PATTERN = "\"([^\"\\\\]|\\\\.)*\"";
	private static final String COMMENT_PATTERN = "//[^\n]*" + "|" + "/\\*(.|\\R)*?\\*/";

	private static final Pattern PATTERN = Pattern.compile(
					  "(?<KEYWORD>" + KEYWORD_PATTERN + ")"
					+ "|(?<PAREN>" + PAREN_PATTERN + ")"
					+ "|(?<BRACE>" + BRACE_PATTERN + ")"
					+ "|(?<BRACKET>" + BRACKET_PATTERN + ")"
					+ "|(?<SEMICOLON>" + SEMICOLON_PATTERN + ")"
					+ "|(?<STRING>" + STRING_PATTERN + ")"
					+ "|(?<COMMENT>" + COMMENT_PATTERN + ")"
			);
	
	private final CodeArea codeArea;
	private ExecutorService executor;

	public SyntaxTextArea(CodeArea codeArea) {
		this.codeArea = codeArea;
		
		codeArea.addEventFilter(KeyEvent.KEY_TYPED, e -> {
			String cStr = e.getCharacter();
			char c = cStr == null || cStr.length() == 0 ? 0 : cStr.charAt(cStr.length() - 1);
			
			if(c == '\n' || c == '\r') {
				String line = codeArea.getParagraphs().get(Math.max(0, codeArea.getCurrentParagraph() - 1)).toString();
				
				String tabs = tabNextLine(line);
				String end = "";
				
				// auto complete the scope
				if(line.trim().endsWith("{")) {
					end = "\n" + (tabs.length() <= 1 ? "" : tabs.substring(1)) + "}";
				}
				
				codeArea.insertText(codeArea.getCaretPosition(), tabs + end);
				codeArea.moveTo(codeArea.getCaretPosition() - end.length());
			}
		});
		
		executor = Executors.newSingleThreadExecutor();
		EventStream<PlainTextChange> textChanges = codeArea.plainTextChanges();
		textChanges
			.successionEnds(Duration.ofMillis(10))
			.supplyTask(this::computeHighlightingAsync)
			.awaitLatest(textChanges)
			.subscribe(this::applyHighlighting);

		codeArea.getStylesheets().add(ClassLoader.getSystemResource("org/fxmisc/richtext/demo/java-keywords.css").toExternalForm());
	}

	public void setText(String text) {
		codeArea.replaceText(0, 0, text);
	}

	public String getText() {
		return  codeArea.getText();

	}
	public void appendText(String text) {
		codeArea.appendText(text);

	}

	public  CodeArea getNode() {
		return codeArea;
	}
	
	private Task<StyleSpans<Collection<String>>> computeHighlightingAsync() {
		String text = codeArea.getText();
		Task<StyleSpans<Collection<String>>> task = new Task<StyleSpans<Collection<String>>>() {
			@Override
			protected StyleSpans<Collection<String>> call() {
				return computeHighlighting(text);
			}
		};
		
		executor.execute(task);
		return task;
	}

	private void applyHighlighting(Try<StyleSpans<Collection<String>>> highlighting) {
		codeArea.setStyleSpans(0, highlighting.get());
	}
	
	private static StyleSpans<Collection<String>> computeHighlighting(String text) {
		Matcher matcher = PATTERN.matcher(text);
		int lastKwEnd = 0;
		StyleSpansBuilder<Collection<String>> spansBuilder = new StyleSpansBuilder<>();
		
		while(matcher.find()) {
			String styleClass =
					matcher.group("KEYWORD") != null ? "keyword" :
						matcher.group("PAREN") != null ? "paren" :
							matcher.group("BRACE") != null ? "brace" :
								matcher.group("BRACKET") != null ? "bracket" :
									matcher.group("SEMICOLON") != null ? "semicolon" :
										matcher.group("STRING") != null ? "string" :
											matcher.group("COMMENT") != null ? "comment" :
												null; /* never happens */ assert styleClass != null;
												spansBuilder.add(Collections.emptyList(), matcher.start() - lastKwEnd);
												spansBuilder.add(Collections.singleton(styleClass), matcher.end() - matcher.start());
												lastKwEnd = matcher.end();
		}
		
		spansBuilder.add(Collections.emptyList(), text.length() - lastKwEnd);
		return spansBuilder.create();
	}
	
	private static String tabNextLine(String line) {
		return StringUtils.repeat('\t', calculateNextLine(line));
	}
	
	private static int calculateNextLine(String line) {
		if(line == null || line.length() == 0) {
			return 0;
		}
		
		int levels = 0;
		
		// calculate initial tabs
		for(int i = 0; i < line.length(); i++) {
			if(line.charAt(i) == '\t') {
				levels++;
			}
		}
		
		line = line.trim();
		
		if(line.length() != 0) {
			// find last line
			int idx = line.lastIndexOf('{');
			
			if(idx == line.length() - 1) {
				levels++;
			}
		}
		
		return levels;
	}

}