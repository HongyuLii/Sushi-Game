package sushigame.view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import comp401sushi.Plate;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;

public class BeltView extends JPanel implements BeltObserver {

	private Belt belt;
	private BeltViewWidget belt_labels[];

	public BeltView(Belt b) {
		this.belt = b;
		belt.registerBeltObserver(this);
		setLayout(new GridLayout(belt.getSize(), 1));
		belt_labels = new BeltViewWidget[belt.getSize()];

//		belt_panel = new BeltViewWidget();
		
		for (int i = 0; i < belt.getSize(); i++) {
			
			BeltViewWidget plabel = new BeltViewWidget(b, b.getPlateAtPosition(i), i);
			plabel.setMinimumSize(new Dimension(300, 20));
			plabel.setPreferredSize(new Dimension(300, 20));
			plabel.setOpaque(true);
			plabel.setBackground(Color.GRAY);
			add(plabel);
			belt_labels[i] = plabel;
			
		}
		refresh();
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {	
		refresh();
	}

	private void refresh() {
		for (int i=0; i<belt.getSize(); i++) {
			Plate p = belt.getPlateAtPosition(i);
			BeltViewWidget plabel = belt_labels[i];	
			if (p == null) {
				//plabel.addButton();
				plabel.setBackground(Color.GRAY);
			} else {
				//plabel.addButton();
				switch (p.getColor()) {
				case RED:
					plabel.setBackground(Color.RED);break;
				case GREEN:
					plabel.setBackground(Color.GREEN);break;
				case BLUE:
					plabel.setBackground(Color.BLUE);break;
				case GOLD:
					plabel.setBackground(Color.YELLOW); break;
				}
			}
		}
	}
}
