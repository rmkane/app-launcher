package app.component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.filechooser.FileSystemView;

import app.component.action.AppAction;
import app.config.dto.AppConfig;

public class AppButton extends JButton {
	private static final long serialVersionUID = -730609116042003884L;
	private static final FileSystemView fs = FileSystemView.getFileSystemView();

	private AppConfig config;

	public AppButton(AppConfig config) {
		super();

		this.config = config;

		initialize();
	}

	private void initialize() {
		setAction(new AppAction(this));
		setEnabled(isApplicationValid());
	}

	protected File getApplication(String filename) {
		try {
			return new File(filename);
		} catch (NullPointerException e) {
			return null;
		}
	}

	public boolean isApplicationValid() {
		return isApplicationValid(config.getPath());
	}

	protected boolean isApplicationValid(String filename) {
		try {
			return isApplicationValid(getApplication(filename));
		} catch (NullPointerException e) {
			return false;
		}
	}

	protected boolean isApplicationValid(File file) {
		return file.isFile() && file.exists();
	}

	public Icon getApplicationIcon() {
		return getApplicationIcon(config.getPath());
	}

	protected Icon getApplicationIcon(String filename) {
		if (isApplicationValid(filename)) {
			return fs.getSystemIcon(getApplication(filename));
		} else {
			return UIManager.getIcon("FileView.fileIcon");
		}
	}

	public Icon getIcon() {
		return getApplicationIcon(config.getPath());
	}

	public String getText() {
		return config != null ? config.getName() : "";
	}

	private String[] configArguments() {
		List<String> commands = config.getArguments();

		commands.add(0, config.getPath());

		return commands.toArray(new String[commands.size()]);
	}

	public void launch() throws IOException {
		String[] args = configArguments();
		Process process = new ProcessBuilder(args).start();
		InputStream is = process.getInputStream();
		InputStreamReader isr = new InputStreamReader(is);
		BufferedReader br = new BufferedReader(isr);
		String line;

		System.out.printf("Output of running %s is:", Arrays.toString(args));

		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}
