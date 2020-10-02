package ais;

import java.awt.Label;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class ListenerUtils {
	
	private ListenerUtils() {
		/**
		 * Utilitary class do not need to instanciate.
		 */
	}

	
	public static MouseListener buildMouseListener(int value, Label label) {
		return new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setText(""+value);
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	}
}