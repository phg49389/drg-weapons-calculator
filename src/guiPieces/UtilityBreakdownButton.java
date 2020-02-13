package guiPieces;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import modelPieces.StatsRow;
import modelPieces.Weapon;

public class UtilityBreakdownButton extends JButton implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private Weapon toDisplay;

	public UtilityBreakdownButton(String textToDisplay, Weapon weaponWithStats) {
		toDisplay = weaponWithStats;
		
		// Font color will be set by the parent WeaponTab, in constructCalculationsPanel()
		this.setBackground(GuiConstants.drgBackgroundBrown);
		this.setBorder(GuiConstants.orangeLine);
		
		this.setText(textToDisplay);
		this.setHorizontalAlignment(SwingConstants.LEFT);
		this.addActionListener(this);
	}
	
	private JPanel getUtilityBreakdownPanel() {
		StatsRow[] utilityStats = toDisplay.utilityExplanation();
		
		JPanel toReturn = new JPanel();
		toReturn.setBackground(GuiConstants.drgBackgroundBrown);
		toReturn.setBorder(GuiConstants.blackLine);
		toReturn.setLayout(new BoxLayout(toReturn, BoxLayout.Y_AXIS));
		
		JPanel row;
		JLabel statLabel, statValue;
		int paddingPixels = 2*GuiConstants.paddingPixels;
		for (int i = 0; i < utilityStats.length; i++) {
			row = new JPanel();
			row.setOpaque(false);
			row.setLayout(new BorderLayout());
			
			statLabel = new JLabel(utilityStats[i].getName());
			statLabel.setForeground(Color.white);
			// Left-pad the label text
			statLabel.setBorder(new EmptyBorder(0, paddingPixels, 0, 0));
			row.add(statLabel, BorderLayout.LINE_START);
			
			statValue = new JLabel(utilityStats[i].getValue());
			if (utilityStats[i].shouldValueBeHighlighted()) {
				statValue.setForeground(GuiConstants.drgHighlightedYellow);
			}
			else {
				statValue.setForeground(GuiConstants.drgRegularOrange);
			}
			// Right-pad the value text
			statValue.setBorder(new EmptyBorder(0, 0, 0, paddingPixels));
			row.add(statValue, BorderLayout.LINE_END);
			
			toReturn.add(row);
		}
		
		return toReturn;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JOptionPane.showMessageDialog(null, getUtilityBreakdownPanel(), "Utility Score Breakdown", JOptionPane.INFORMATION_MESSAGE);
	}
}
