package balls.view.hello;

import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;

import balls.common.events.AppEvent;

/**
 * Klasa reprezentująca powitalne okienko gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public class HelloWindow
{
	private final JFrame window;
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	private final ImageLoader icon;
	private final JButton newGameButton;
	private final JButton bestScoresButton;
	private final JLabel headerLabel;
	private final JLabel bottomImage;
	private final JMenuBar menuBar;
	private final JMenu settings;
	private final JMenu gameMode;
	private final JMenu gamePlaySize;
	private final JMenuItem[] set = { new JMenuItem("Nazwa Użytkownika", KeyEvent.VK_U) };
	private final JMenuItem[] gameModes = { new JMenuItem("Normalna grawitacja"),
			new JMenuItem("Boczna grawitacja") };
	private final JMenuItem[] gamePlaySizes = { new JMenuItem("5"), new JMenuItem("10"), new JMenuItem("15") };
	private final ActionListener newGameButtonClicked;
	private final ActionListener userNameChanged;
	private final LinkedList<ActionListener> gravityModes;
	private final LinkedList<ActionListener> sizeOfGamePlay;

	/**
	 * Domyślny konstruktor dla tej klasy, ustawia sluchaczy zdarzeń etc...
	 */
	public HelloWindow(final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.blockingQueue = blockingQueue;
		this.icon = new ImageLoader();
		this.newGameButton = new JButton("Nowa gra!");
		this.bestScoresButton = new JButton("Zobacz najlepsze wyniki");
		this.headerLabel = new JLabel("Witamy w grze!");
		this.bottomImage = new JLabel(icon.getIcon());
		this.menuBar = new JMenuBar();
		this.settings = new JMenu("Ustawienia");
		this.gameMode = new JMenu("Tryb Gry");
		this.gamePlaySize = new JMenu("Rozmiar planszy");
		this.gravityModes = new LinkedList<ActionListener>();
		this.sizeOfGamePlay = new LinkedList<ActionListener>();
		this.newGameButtonClicked = new NewGameButtonClicked(this.blockingQueue);
		this.userNameChanged = new UserNameChanged(this.blockingQueue);
		this.gravityModes.add(new NormalGravitySet(this.blockingQueue));
		this.gravityModes.add(new BackGravitySet(this.blockingQueue));
		this.sizeOfGamePlay.add(new SmallSizeSet(this.blockingQueue));
		this.sizeOfGamePlay.add(new MediumSizeSet(this.blockingQueue));
		this.sizeOfGamePlay.add(new BigSizeSet(this.blockingQueue));
		this.window = new JFrame();
		initializeWindow();
	}

	/**
	 * Inicjalizacja właściego wyglądu okienka.
	 */
	private void initializeWindow()
	{
		window.setLayout(new BoxLayout(this.window.getContentPane(), BoxLayout.Y_AXIS));
		window.getContentPane().setBackground(Color.white);
		window.setResizable(false);
		menuBar.add(settings);
		for (JMenuItem item : set)
		{
			item.addActionListener(userNameChanged);
			settings.add(item);
		}
		for (int i = 0; i < gameModes.length; ++i)
		{
			gameModes[i].addActionListener(gravityModes.get(i));
			gameMode.add(gameModes[i]);
		}
		settings.add(gameMode);
		for (int i = 0; i < gamePlaySizes.length; ++i)
		{
			gamePlaySizes[i].addActionListener(sizeOfGamePlay.get(i));
			gamePlaySize.add(gamePlaySizes[i]);
		}
		settings.add(gamePlaySize);
		newGameButton.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		newGameButton.setToolTipText("Wciśnij aby rozpocząć nową gre");
		bestScoresButton.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		bestScoresButton.setToolTipText("Wciśnij aby zobaczyć ranking najlepszych wyników");
		headerLabel.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		bottomImage.setAlignmentX(JFrame.CENTER_ALIGNMENT);
		newGameButton.addActionListener(newGameButtonClicked);
		window.setJMenuBar(menuBar);
		window.add(Box.createVerticalStrut(10));
		window.add(headerLabel);
		window.add(Box.createVerticalStrut(25));
		window.add(newGameButton);
		window.add(Box.createVerticalStrut(25));
		window.add(bestScoresButton);
		window.add(Box.createVerticalStrut(25));
		window.add(bottomImage);
		window.pack();
	}

	/**
	 * Pokaż okienko.
	 */
	public void show()
	{
		window.setTitle("Balls");
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setSize(300, 300);
		window.setVisible(true);
	}
	
	/**
	 * Schowaj okno.
	 */
	public void hide()
	{
		window.setVisible(false);
	}
}
