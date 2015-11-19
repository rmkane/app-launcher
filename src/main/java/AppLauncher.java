import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import app.config.dto.Config;
import app.config.dto.RootConfig;
import app.config.reader.RootConfigReader;
import app.view.AppDashboard;

public class AppLauncher {
	public static final String APP_TITLE = "App Launcher";

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame(APP_TITLE);
				RootConfig config = RootConfigReader.read("appConfig.json");
				AppDashboard dashboard = new AppDashboard(config);

				printConfig(config);

				frame.setContentPane(dashboard);
				frame.pack();
				frame.setLocationRelativeTo(null);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}
		});
	}

	public static void printConfig(Config config) {
		Gson g = new GsonBuilder().setPrettyPrinting().create();

		System.out.printf("Read configuration: %n%n%s%n", g.toJson(config));
	}
}
