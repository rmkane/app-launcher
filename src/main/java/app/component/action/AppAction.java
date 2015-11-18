package app.component.action;

import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.AbstractAction;

import app.component.AppButton;

public class AppAction extends AbstractAction {
	private static final long serialVersionUID = -3468638356057875630L;

	private AppButton observer;
	
	public AppAction(AppButton observer) {
		this.observer = observer;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (observer != null) {
			try {
				observer.launch();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

}
