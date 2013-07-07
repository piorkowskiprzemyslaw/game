package balls.view.scores;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 * Przechowuje komponent GamePlayWindow pokazujacy aktualne wyniki
 */
public class Scores
{
	/** Główny panel */
	private final JPanel panel;
	/** Napis Suma punktow */
	private final JLabel pointsSum;
	/** Napis ostatnie punkty */
	private final JLabel lastPoints;
	/** Napis Gracz : */
	private final JLabel userLabel;
	/** Wskaźnik liczby punktow */
	private final JLabel pointsSumIndicator;
	/** Wskaźnik liczby punktów ostaniego ruchu */
	private final JLabel lastPointsIndicator;
	/** Wskaźnik aktualnej nazwy gracza */
	private final JLabel user;

	public Scores()
	{
		this.panel = new JPanel();
		this.pointsSum = new JLabel("Suma punktów");
		this.lastPoints = new JLabel("Ostatni ruch");
		this.userLabel = new JLabel("Gracz :");
		this.pointsSumIndicator = new JLabel("0");
		this.lastPointsIndicator = new JLabel("0");
		this.user = new JLabel();
		initializePanel();
	}
	
	/**
	 * Inicjalizacja wyglądu panelu.
	 */
	private void initializePanel()
	{
		this.panel.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		this.panel.setBounds(25, 15, 175, 170);
		this.panel.setLayout(null);
		pointsSum.setBounds(10, 10, 107, 25);
		this.panel.add(pointsSum);
		pointsSumIndicator.setBounds(127, 10, 30, 25);
		this.panel.add(pointsSumIndicator);
		lastPoints.setBounds(10, 67, 107, 25);
		this.panel.add(lastPoints);
		lastPointsIndicator.setBounds(127, 67, 30, 25);
		this.panel.add(lastPointsIndicator);
		userLabel.setBounds(10, 124, 60, 25);
		this.panel.add(userLabel);
		user.setBounds(70, 124, 100, 25);
		this.panel.add(user);
	}
	
	/**
	 * Pobranie panelu do dodania.
	 * @return panel
	 */
	public JPanel getPanel()
	{
		return panel;
	}

	/**
	 * Ustawia aktualną liczbę ostatnio zdobytych punktów.
	 * 
	 * @param lastPoints
	 *            wartość ustawiana.
	 */
	public void setLastPointsIndicator(final int lastPoints)
	{
		lastPointsIndicator.setText("" + lastPoints);
	}

	/**
	 * Ustawia ilość punków zdobytych.
	 * 
	 * @param points
	 *            wartość ustawiana.
	 */
	public void setPointsSumIndicator(final int points)
	{
		pointsSumIndicator.setText("" + points);
	}

	/**
	 * Ustawia imię gracza.
	 * 
	 * @param name
	 *            ustawiane imie.
	 */
	public void setUserName(final String name)
	{
		user.setText(name);
	}
}
