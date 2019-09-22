package sushigame.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JSlider;

import comp401sushi.AvocadoPortion;
import comp401sushi.CrabPortion;
import comp401sushi.EelPortion;
import comp401sushi.Ingredient;
import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Plate;
import comp401sushi.Plate.Color;
import comp401sushi.RedPlate;
import comp401sushi.RicePortion;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.Sashimi.SashimiType;
import comp401sushi.SeaweedPortion;
import comp401sushi.ShrimpPortion;
import comp401sushi.Sushi;
import comp401sushi.TunaPortion;
import comp401sushi.YellowtailPortion;

public class PlayerChefView extends JPanel implements ActionListener {

	private List<ChefViewListener> listeners;
	private Sushi kmp_roll;
	private Sushi crab_sashimi;	
	private Sushi eel_nigiri;
	
	private int belt_size;
	private int position;
	
	boolean colored=false;
	boolean typed=false;
	boolean named=false;
	boolean button_created=false;
	String color_chosen="";
	String type_chosen="";
	String ingredient_chosen="";
	Sushi sushi_chosen=null;
	JSlider PositionBar;
	JSlider price_bar;
	private double price;
	private ArrayList<IngredientPortion> myRollIngredients;

	

	
	public PlayerChefView(int belt_size) {
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel position__=new JLabel("position");
		add(position__);
		PositionBar = new JSlider();
		PositionBar = new JSlider(0, belt_size, 0);
		PositionBar.setPaintTicks(true);
		PositionBar.setSnapToTicks(true);
		PositionBar.setPaintLabels(true);
		PositionBar.setMajorTickSpacing(1);
		add(PositionBar);
		position=PositionBar.getValue();
		
		price_bar = new JSlider();
		price_bar = new JSlider(5, 10, 5);
		price_bar.setPaintTicks(true);
		price_bar.setSnapToTicks(true);
		price_bar.setPaintLabels(true);
		price_bar.setMajorTickSpacing(1);
		
		Hashtable<Integer, JLabel> table_price = new Hashtable<Integer, JLabel>();
		table_price.put (5, new JLabel("$"));
		table_price.put (6, new JLabel("6"));
		table_price.put (7, new JLabel("7"));
		table_price.put (8, new JLabel("8"));
		table_price.put (9, new JLabel("9"));
		table_price.put (10, new JLabel("10"));
		price_bar.setLabelTable (table_price);
		add(price_bar);
		

	
		JButton red_button = new JButton("Red plate");
		red_button.setActionCommand("red");
		red_button.addActionListener(this);
		
		JButton green_button = new JButton("green plate");
		green_button.setActionCommand("green");
		green_button.addActionListener(this);
		
		JButton blue_button = new JButton("blue plate");
		blue_button.setActionCommand("blue");
		blue_button.addActionListener(this);
		
		JButton gold_button = new JButton("gold plate");
		gold_button.setActionCommand("gold");
		gold_button.addActionListener(this);
		
		add(red_button);
		add(green_button);
		add(blue_button);
		add(gold_button);
	}

	public void registerChefListener(ChefViewListener cl) {
		listeners.add(cl);
	}

	private void makeRedPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleRedPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeGreenPlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleGreenPlateRequest(plate_sushi, plate_position);
		}
	}

	private void makeBluePlateRequest(Sushi plate_sushi, int plate_position) {
		for (ChefViewListener l : listeners) {
			l.handleBluePlateRequest(plate_sushi, plate_position);
		}
	}
	
	private void makeGoldPlateRequest(Sushi plate_sushi, int plate_position, double price) {
		for (ChefViewListener l : listeners) {
			l.handleGoldPlateRequest(plate_sushi, plate_position, price);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		position=PositionBar.getValue();
		price=price_bar.getValue();

		JButton sashimi = new JButton("sashimi");
		sashimi.setActionCommand("sashimi");
		sashimi.addActionListener(this);
		
		JButton nigiri = new JButton("nigiri");
		nigiri.setActionCommand("nigiri");
		nigiri.addActionListener(this);
		
		JButton roll = new JButton("roll");
		roll.setActionCommand("roll");
		roll.addActionListener(this);
		
		JButton eel = new JButton("eel");
		eel.setActionCommand("eel");
		eel.addActionListener(this);
		
		JButton tuna = new JButton("tuna");
		tuna.setActionCommand("tuna");
		tuna.addActionListener(this);
		
		JButton crab = new JButton("crab");
		crab.setActionCommand("crab");
		crab.addActionListener(this);
		
		JButton shrimp = new JButton("shrimp");
		shrimp.setActionCommand("tuna");
		shrimp.addActionListener(this);
		
		JButton yellowtail = new JButton("yellowtail");
		yellowtail.setActionCommand("tuna");
		yellowtail.addActionListener(this);
		
		JButton enter = new JButton("enter");
		enter.setActionCommand("enter");
		enter.addActionListener(this);
		
		 JSlider shrimp_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_shrimp = new Hashtable<Integer, JLabel>();
		 table_shrimp.put (0, new JLabel("shrimp"));
		 table_shrimp.put (50, new JLabel("0.50"));
		 table_shrimp.put (100, new JLabel("1.00"));
		 table_shrimp.put (150, new JLabel("1.50"));
		 shrimp_bar.setLabelTable (table_shrimp);
		 shrimp_bar.setPaintLabels(true);
		 shrimp_bar.setSnapToTicks(true);
		 shrimp_bar.setMajorTickSpacing(1);
		 
		

		
		JSlider tuna_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_tuna = new Hashtable<Integer, JLabel>();
		 table_tuna.put (0, new JLabel("tuna"));
		 table_tuna.put (50, new JLabel("0.50"));
		 table_tuna.put (100, new JLabel("1.00"));
		 table_tuna.put (150, new JLabel("1.50"));
		 tuna_bar.setLabelTable (table_tuna);
		tuna_bar.setSnapToTicks(true);
		tuna_bar.setPaintLabels(true);
		tuna_bar.setMajorTickSpacing(1);
		
		JSlider eel_bar = new JSlider(0, 150);
		Hashtable<Integer, JLabel> table_eel = new Hashtable<Integer, JLabel>();
		table_eel.put (0, new JLabel("eel"));
		table_eel.put (50, new JLabel("0.50"));
		table_eel.put (100, new JLabel("1.00"));
		table_eel.put (150, new JLabel("1.50"));
		eel_bar.setLabelTable (table_eel);
		eel_bar.setPaintTicks(true);
		eel_bar.setSnapToTicks(true);
		eel_bar.setPaintLabels(true);
		eel_bar.setMajorTickSpacing(1);
		
		JSlider crab_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_crab = new Hashtable<Integer, JLabel>();
		 table_crab.put (0, new JLabel("crab"));
		 table_crab.put (50, new JLabel("0.50"));
		 table_crab.put (100, new JLabel("1.00"));
		 table_crab.put (150, new JLabel("1.50"));
		 crab_bar.setLabelTable (table_crab);
		crab_bar.setPaintTicks(true);
		crab_bar.setSnapToTicks(true);
		crab_bar.setPaintLabels(true);
		crab_bar.setMajorTickSpacing(1);
		
		JSlider yellowtail_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_yellowtail = new Hashtable<Integer, JLabel>();
		 table_yellowtail.put (0, new JLabel("yellowtail"));
		 table_yellowtail.put (50, new JLabel("0.50"));
		 table_yellowtail.put (100, new JLabel("1.00"));
		 table_yellowtail.put (150, new JLabel("1.50"));
		 yellowtail_bar.setLabelTable (table_yellowtail);
		yellowtail_bar.setPaintTicks(true);
		yellowtail_bar.setSnapToTicks(true);
		yellowtail_bar.setPaintLabels(true);
		yellowtail_bar.setMajorTickSpacing(1);
		
		JSlider rice_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_rice = new Hashtable<Integer, JLabel>();
		 table_rice.put (0, new JLabel("rice"));
		 table_rice.put (50, new JLabel("0.50"));
		 table_rice.put (100, new JLabel("1.00"));
		 table_rice.put (150, new JLabel("1.50"));
		 rice_bar.setLabelTable (table_rice);
		rice_bar.setPaintTicks(true);
		rice_bar.setSnapToTicks(true);
		rice_bar.setPaintLabels(true);
		rice_bar.setMajorTickSpacing(1);
		
		JSlider avocado_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_avocado = new Hashtable<Integer, JLabel>();
		 table_avocado.put (0, new JLabel("avocado"));
		 table_avocado.put (50, new JLabel("0.50"));
		 table_avocado.put (100, new JLabel("1.00"));
		 table_avocado.put (150, new JLabel("1.50"));
		 avocado_bar.setLabelTable (table_avocado);
		avocado_bar.setPaintTicks(true);
		avocado_bar.setSnapToTicks(true);
		avocado_bar.setPaintLabels(true);
		avocado_bar.setMajorTickSpacing(1);
		
		JSlider seaweed_bar = new JSlider(0, 150);
		 Hashtable<Integer, JLabel> table_seaweed = new Hashtable<Integer, JLabel>();
		 table_seaweed.put (0, new JLabel("seaweed"));
		 table_seaweed.put (50, new JLabel("0.50"));
		 table_seaweed.put (100, new JLabel("1.00"));
		 table_seaweed.put (150, new JLabel("1.50"));
		 seaweed_bar.setLabelTable (table_seaweed);
		seaweed_bar.setPaintTicks(true);
		seaweed_bar.setSnapToTicks(true);
		seaweed_bar.setPaintLabels(true);
		seaweed_bar.setMajorTickSpacing(1);
		
		switch (e.getActionCommand()) {
		case "red":
			if(colored==true) {
				color_chosen="red";
			} else {
				add(sashimi);
				add(nigiri);
				add(roll);
				color_chosen="red";
				colored=true;
				revalidate();
				repaint();	
			}
			
			break;
		case "green":
			if(colored==true) {
				color_chosen="green";
			} 
			if(colored!=true){
				add(sashimi);
				add(nigiri);
				add(roll);
				color_chosen="green";
				colored=true;
				revalidate();
				repaint();	
			}
			break;
		case "blue":
			if(colored==true) {
				color_chosen="blue";
			} else {
				add(sashimi);
				add(nigiri);
				add(roll);
				color_chosen="blue";
				colored=true;
				revalidate();
				repaint();	
			}
			break;
		case "gold":
			if(colored==true) {
				color_chosen="gold";
			} else {
				
				add(roll);
				color_chosen="gold";
				colored=true;
				revalidate();
				repaint();	
			}
			
			break;
			
		case "sashimi":
			if(typed==true) {
				type_chosen="sashimi";
			} else {
				add(eel);
				add(crab);
				add(yellowtail);
				add(tuna);
				add(shrimp);
				type_chosen="sashimi";
				typed=true;
				revalidate();
				repaint();	
			}
			
			break;
		case "nigiri":
			if(typed==true) {
				type_chosen="nigiri";
			} else {
				add(eel);
				add(crab);
				add(yellowtail);
				add(tuna);
				add(shrimp);
				type_chosen="nigiri";
				typed=true;
				revalidate();
				repaint();	
			}
			
			break;
		case "roll":
			if(button_created==true) {
				type_chosen="roll";
			} else {
				add(seaweed_bar);
				add(shrimp_bar);
				add(tuna_bar);
				add(avocado_bar);
				add(yellowtail_bar);
				add(crab_bar);
				add(rice_bar);
				add(eel_bar);
				add(enter);
				type_chosen="roll";
				button_created=true;
				revalidate();
				repaint();
			}
			break;
			
		case "eel":
			if(named==true) {
				ingredient_chosen="eel";
			} else {
				add(enter);
				ingredient_chosen="eel";
				named=true;
				revalidate();
				repaint();	
			}
	
			break;
		case "shrimp":
			if(named==true) {
				ingredient_chosen="shrimp";
			} else {
				add(enter);
				ingredient_chosen="shrimp";
				named=true;
				revalidate();
				repaint();	
			}
			break;
		case "tuna":
			if(named==true) {
				ingredient_chosen="tuna";
			} else {
				add(enter);
				ingredient_chosen="tuna";
				named=true;
				revalidate();
				repaint();	
			}
			break;
		case "crab":
			if(named==true) {
				ingredient_chosen="crab";
			} else {
				add(enter);
				ingredient_chosen="crab";
				named=true;
				revalidate();
				repaint();	
			}
			break;
		case "yellowtail":
			if(named==true) {
				ingredient_chosen="yellowtail";
			} else {
				add(enter);
				ingredient_chosen="yellowtail";
				named=true;
				revalidate();
				repaint();	
			}
			break;
			
		case "enter":
			
			if(type_chosen=="sashimi") {
				switch(ingredient_chosen) {
					case "eel":
						sushi_chosen=new Sashimi(Sashimi.SashimiType.EEL);
						break;
					case "yellow":
						sushi_chosen=new Sashimi(Sashimi.SashimiType.YELLOWTAIL);
						break;
					case "tuna":
						sushi_chosen=new Sashimi(Sashimi.SashimiType.TUNA);
						break;
					case "crab":
						sushi_chosen=new Sashimi(Sashimi.SashimiType.CRAB);
						break;
					case "shrimp":
						sushi_chosen=new Sashimi(Sashimi.SashimiType.SHRIMP);
						break;
				}
				switch (color_chosen) {
					case "red":
						makeRedPlateRequest(sushi_chosen, position);
						break;
					case "green":
						makeGreenPlateRequest(sushi_chosen, position);
						break;
					case "blue":
						makeBluePlateRequest(sushi_chosen, position);
						break;
					case "gold":
						makeGoldPlateRequest(sushi_chosen, position, 5);
						break;
				}
				
			} else if(type_chosen=="nigiri") {
				switch(ingredient_chosen) {
				case "eel":
					sushi_chosen=new Nigiri(Nigiri.NigiriType.EEL);
					break;
				case "yellow":
					sushi_chosen=new Nigiri(Nigiri.NigiriType.YELLOWTAIL);
					break;
				case "tuna":
					sushi_chosen=new Nigiri(Nigiri.NigiriType.TUNA);
					break;
				case "crab":
					sushi_chosen=new Nigiri(Nigiri.NigiriType.CRAB);
					break;
				case "shrimp":
					sushi_chosen=new Nigiri(Nigiri.NigiriType.SHRIMP);
					break;
			}
				switch (color_chosen) {
				case "red":
					makeRedPlateRequest(sushi_chosen, position);
					break;
				case "green":
					makeGreenPlateRequest(sushi_chosen, position);
					break;
				case "blue":
					makeBluePlateRequest(sushi_chosen, position);
					break;
				case "gold":
					makeGoldPlateRequest(sushi_chosen, position, 5);
					break;
				
			}
			} else {
				myRollIngredients = new ArrayList<IngredientPortion>();
				double shrimp_portion=shrimp_bar.getValue()/100;
				double eel_portion=eel_bar.getValue()/100;
			    double crab_portion=crab_bar.getValue()/100;
				double tuna_portion=tuna_bar.getValue()/100;
				double yellowtail_portion=yellowtail_bar.getValue()/100;
				double avocado_portion=avocado_bar.getValue()/100;
				double seaweed_portion=avocado_bar.getValue()/100;
				double rice_portion=rice_bar.getValue()/100;
				
			
				EelPortion eelportion;
				if(eel_portion!=0) {
					eelportion=new EelPortion(eel_portion);
					myRollIngredients.add(eelportion);
				} 
				
				ShrimpPortion shrimpportion;
				if(shrimp_portion!=0) {
					shrimpportion = new ShrimpPortion(shrimp_portion);
					myRollIngredients.add(shrimpportion);
				} 
				TunaPortion tunaportion;
				if(tuna_portion!=0) {
					tunaportion = new TunaPortion(tuna_portion);
					myRollIngredients.add(tunaportion);
				} 
				AvocadoPortion avocadoportion;
				if(avocado_portion!=0) {
					avocadoportion = new AvocadoPortion(avocado_portion);
					myRollIngredients.add(avocadoportion);
				} 
				YellowtailPortion yellowtailportion;
				if(yellowtail_portion!=0) {
					yellowtailportion = new YellowtailPortion(yellowtail_portion);
					myRollIngredients.add(yellowtailportion);
				} 
				CrabPortion crabportion;
				if(crab_portion!=0) {
					crabportion = new CrabPortion(crab_portion);
					myRollIngredients.add(crabportion);
				} 
				SeaweedPortion seaweedportion;
				if(seaweed_portion!=0) {
					seaweedportion = new SeaweedPortion(seaweed_portion);
					myRollIngredients.add(seaweedportion);
				} 
				RicePortion riceportion;;
				if(rice_portion!=0) {
					riceportion = new RicePortion(rice_portion);
					myRollIngredients.add(riceportion);
				} 

				 IngredientPortion[] ing = new IngredientPortion[myRollIngredients.size()];
				 for(int i = 0 ; i < myRollIngredients.size(); i++) {
					 ing[i] = myRollIngredients.get(i);
				 }

						sushi_chosen=new Roll("KMP_Roll",ing);
						
						switch (color_chosen) {
						case "red":
							makeRedPlateRequest(sushi_chosen, position);
							break;
						case "green":
							makeGreenPlateRequest(sushi_chosen, position);
							break;
						case "blue":
							makeBluePlateRequest(sushi_chosen, position);
							break;
						case "gold":
							makeGoldPlateRequest(sushi_chosen, position, price);
							break;
			}
			}

		}

}
	
	private void resetToDefault() {
		removeAll();
		this.belt_size = belt_size;
		listeners = new ArrayList<ChefViewListener>();

		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		JLabel position__=new JLabel("position");
		add(position__);
		PositionBar = new JSlider();
		PositionBar = new JSlider(0, belt_size, 0);
		PositionBar.setPaintTicks(true);
		PositionBar.setSnapToTicks(true);
		PositionBar.setPaintLabels(true);
		PositionBar.setMajorTickSpacing(1);
		add(PositionBar);
		position=PositionBar.getValue();
		
		price_bar = new JSlider();
		price_bar = new JSlider(5, 10, 5);
		price_bar.setPaintTicks(true);
		price_bar.setSnapToTicks(true);
		price_bar.setPaintLabels(true);
		price_bar.setMajorTickSpacing(1);
		
		Hashtable<Integer, JLabel> table_price = new Hashtable<Integer, JLabel>();
		table_price.put (5, new JLabel("$"));
		table_price.put (6, new JLabel("6"));
		table_price.put (7, new JLabel("7"));
		table_price.put (8, new JLabel("8"));
		table_price.put (9, new JLabel("9"));
		table_price.put (10, new JLabel("10"));
		price_bar.setLabelTable (table_price);
		add(price_bar);
		

	
		JButton red_button = new JButton("Red plate");
		red_button.setActionCommand("red");
		red_button.addActionListener(this);
		
		JButton green_button = new JButton("green plate");
		green_button.setActionCommand("green");
		green_button.addActionListener(this);
		
		JButton blue_button = new JButton("blue plate");
		blue_button.setActionCommand("blue");
		blue_button.addActionListener(this);
		
		JButton gold_button = new JButton("gold plate");
		gold_button.setActionCommand("gold");
		gold_button.addActionListener(this);
		
		add(red_button);
		add(green_button);
		add(blue_button);
		add(gold_button);
		
		revalidate();
	}
}

