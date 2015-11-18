package app.view;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import app.component.AppButton;
import app.config.dto.AppConfig;
import app.config.dto.RootConfig;

public class AppDashboard extends JPanel {
	private static final long serialVersionUID = -3885872923055669204L;

	private RootConfig config;
	private List<AppButton> buttons;
	
	public AppDashboard(RootConfig config) {
		super(new FlowLayout());
		
		this.config = config;
		this.buttons = new ArrayList<AppButton>();

		initialize();
	}
	
	private void initialize() {
		for (AppConfig appConfig : config.getApplications()) {
			buttons.add(new AppButton(appConfig));
		}
		
		createChildren();
	}

	protected void createChildren() {
		for (AppButton button : buttons) {
			this.add(button);
		}
	}
}
