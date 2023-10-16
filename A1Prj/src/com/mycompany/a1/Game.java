package com.mycompany.a1;

import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import java.lang.String;

public class Game extends Form {
	private GameWorld gw;

	public Game() {
		gw = new GameWorld();
		gw.init();
		play();
	}

	private void play() {
		Label myLabel = new Label("Enter a Command:");
		this.addComponent(myLabel);

		final TextField myTextField = new TextField();
		this.addComponent(myTextField);
		this.show();

		myTextField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {

				String sCommand = myTextField.getText().toString();
				myTextField.clear();
				if (sCommand.length() != 0)
					switch (sCommand.charAt(0)) {
					case 'a':
						gw.accelerate();
						break;
					case 'b':
						gw.brake();
						break;
					case 'c':
						gw.consumptionRate();
						break;
					case 'd':
						gw.display();
						break;
					case 'f':
						gw.foodCollision();
						break;
					case 'g': 
						gw.spiderCollision();
						break;
					case 'm':
						gw.map();
						break;
					case 'r':
						gw.turnRight();
						break;
					case 'l':
						gw.turnLeft();
						break;
					case 't':
						// t for tick which updates the gameWorld status
						gw.tick();
						break;
					case '1':  // 1 - 9 input are the flag numbers
					case '2':
					case '3':
					case '4':
					case '5':
					case '6':
					case '7':
					case '8':
					case '9':
						// 1 - 9 input are the flag numbers
						// each number invokes the flagCollision() command
						int val = (int) (sCommand.charAt(0)) - 48;
						gw.flagCollision(val);
						break;
					case 'x':
						// Set exit flag and prompt user in the console
						if (gw.getExitFlag() == '-') {
							gw.setExitFlag('x');
							System.out.println("Enter one of the following commands: ");
							System.out.println("-> 'y' to exit()");
							System.out.println("-> 'n' to continue playing" + '\n');
						}
						
						break;
					case 'y':
						// if the flag is set then exit otherwise and error will show up
						if (gw.getExitFlag() == 'x') {
							gw.exit();
						}
						
						gw.error();
						break;
					case 'n':
						// if the flag is set then you can continue game otherwise and error will show up
						if (gw.getExitFlag() == 'x') {
							gw.setExitFlag('-');
							System.out.println("Continue playing...!" + '\n');
							break;
						}
						
						gw.error();
						break;
					default:
						gw.setExitFlag('-');
						gw.error();
						break;
					// add code to handle rest of the commands
					} // switch
			} // actionPerformed
		} // new ActionListener()
		); // addActionListener
	}
}
