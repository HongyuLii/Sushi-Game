package sushigame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;
import sushigame.model.SushiGameModel;

public class ScoreboardWidget extends JPanel implements BeltObserver {

	private SushiGameModel game_model;
	private JLabel display;
	private JPanel panel;
	private JButton balance;
	private JButton food_consumed;
	private JButton food_boiled;
	
	public ScoreboardWidget(SushiGameModel gm) {
		game_model = gm;
		game_model.getBelt().registerBeltObserver(this);
		
		balance = new JButton("balance");
		food_consumed = new JButton("food_consumed");
		food_boiled = new JButton("food_boiled");
		
		display = new JLabel();
		display.setVerticalAlignment(SwingConstants.TOP);
		setLayout(new BorderLayout());
		add(display, BorderLayout.CENTER);
		display.setText(makeScoreboardHTML());
	}

	private String makeScoreboardHTML() {
		String sb_html = "<html>";
		sb_html += "<h1>Scoreboard</h1>";

		// Create an array of all chefs and sort by balance.
		Chef[] opponent_chefs= game_model.getOpponentChefs();
		Chef[] chefs = new Chef[opponent_chefs.length+1];
		chefs[0] = game_model.getPlayerChef();
		for (int i=1; i<chefs.length; i++) {
			chefs[i] = opponent_chefs[i-1];
		}
		
		
		Arrays.sort(chefs, new HighToLowBalanceComparator());
		for (Chef c : chefs) {
			sb_html += c.getName() + " (Balance $ " + Math.round(c.getBalance()*100.0)/100.0 + ") <br>";
			
		}
		
		Arrays.sort(chefs, new HighToLowFoodConsumedComparator());
		for (Chef c : chefs) {
			sb_html += c.getName() + " (Food consumed " + Math.round(c.getFoodConsumed()*100.0)/100.0 +" ounces "+ ") <br>";
			
		}
		

		Arrays.sort(chefs, new HighToLowFoodSpoiledComparator());
		for (Chef c : chefs) {
			sb_html += c.getName() + " (Food spoiled " + Math.round(c.getFoodSpoiled()*100.0)/100.0 + " ounces"+ ") <br>";
			
		}
		return sb_html;
	}

	public void refresh() {
		display.setText(makeScoreboardHTML());		
	}
	
	@Override
	public void handleBeltEvent(BeltEvent e) {
		if (e.getType() == BeltEvent.EventType.ROTATE) {
			refresh();
		}		
	}



}
