package sushigame.view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import comp401sushi.IngredientPortion;
import comp401sushi.Nigiri;
import comp401sushi.Nigiri.NigiriType;
import comp401sushi.Plate;
import comp401sushi.Roll;
import comp401sushi.Sashimi;
import comp401sushi.Sashimi.SashimiType;
import comp401sushi.Sushi;
import sushigame.model.Belt;
import sushigame.model.BeltEvent;
import sushigame.model.BeltObserver;
import sushigame.model.Chef;

public class BeltViewWidget extends JPanel implements ActionListener, BeltObserver {

	private Color color;
	private String type;
	private String name;
	private IngredientPortion[] ingredient;
	private String chef;
	private int age;
	private String info;
	private JButton info_button;
	private JOptionPane info_op;
	private Plate p;
	private Belt b;
	private int index;

	public BeltViewWidget(Belt b, Plate p, int index) {
		this.b=b;
		this.index=index;
		this.p=p;
		b.registerBeltObserver(this);
		
		info_button=new JButton("plate");
		add(info_button);
		info_button.addActionListener(this);

		info_op=new JOptionPane();
		
	
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		age=age++;
		if (p == null) {
			setBackground(Color.GRAY);
		}else {
			System.out.println("hi");
			if(p.getColor()==Plate.Color.RED) {
				this.color=Color.RED;
				setBackground(Color.RED);
			}else if(p.getColor()==Plate.Color.GREEN) {
				this.color=Color.GREEN;
				setBackground(Color.GREEN);
			}else if(p.getColor()==Plate.Color.BLUE) {
				this.color=Color.BLUE;
				setBackground(Color.BLUE);
			}else if(p.getColor()==Plate.Color.GOLD) {
				this.color=Color.YELLOW;
				setBackground(Color.YELLOW);
			}
			
			
			Sashimi sashi  = new Sashimi(SashimiType.EEL);
			Nigiri nigi = new Nigiri(NigiriType.EEL);
			
			
			if(p.getContents() instanceof Roll) {
				String roll_info=" ";
				type="Roll";
				this.name=p.getContents().getName();
				this.ingredient=p.getContents().getIngredients();
				this.chef=p.getChef().getName();
				
				for(int i=0; i<ingredient.length; i++) {
					roll_info=roll_info+ingredient[i].getName()+" ";
					roll_info=roll_info+ingredient[i].getAmount()+" ";
					
				}
				age=b.getAgeOfPlateAtPosition(index);
				info=p.getColor()+type+" "+name + " "+roll_info+" "+chef+" "+"age: "+  age;
			} else {
				this.name=p.getContents().getName();
				this.chef=p.getChef().getName();
				age=b.getAgeOfPlateAtPosition(index);
				info=p.getColor()+" "+name + " "+ chef+" "+"age: "+ age;
			}
		}
		
		JOptionPane.showMessageDialog(info_op, info);
		
	}

	@Override
	public void handleBeltEvent(BeltEvent e) {
		p=b.getPlateAtPosition(index);
	}
}
