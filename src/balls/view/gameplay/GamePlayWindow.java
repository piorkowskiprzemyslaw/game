/** Okno widoku dzialajacej gry. */

package balls.view.gameplay;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.*;

import balls.common.events.AppEvent;
import balls.model.mockups.Mockups;
import balls.view.arrayofballs.ArrayOfBalls;
import balls.view.scores.Scores;

/**
 * Klasa reprezentująca okno uruchomionej gry.
 * 
 * @author Przemek
 */
public class GamePlayWindow
{
	private final JFrame frame;
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	private final ImageLoader imageLoader;
	private final JLabel back;
	private final JLabel next;
	private final JLabel newGame;
	private final JPanel contentPane;
	private final Scores scores;
	private final ArrayOfBalls arrayOfBalls;
	private final JMenuBar menuBar;
	private final JMenu menu;
	private final JMenu gameMode;
	private final JMenuItem[] set = { new JMenuItem("Ustaw nową nazwę użytkownika", KeyEvent.VK_U),
			new JMenuItem("Wyświetln najlepsze wyniki", KeyEvent.VK_N) };
	private final JMenuItem[] mode = { new JMenuItem("Przestaw grawitacje na normalną"),
			new JMenuItem("Przestaw grawitacje na boczną") };
	private final MouseListener setNewGamePlay;
	private final MouseListener backLabelClicked;
	private final MouseListener nextLabelClicked;
	private final ActionListener userNameChangedDuringGame;
	private final ActionListener normalGravitySetDuringGame;
	private final ActionListener backGravitySetDuringGame;

	/**
	 * Domyślny konstruktor ustwiający ArrayOfBalls według danych zawartych w makiecie.
	 * 
	 * @param mockup
	 */
	public GamePlayWindow(final LinkedBlockingQueue<AppEvent> blockingQueue, final Mockups mockup)
	{
		this.frame = new JFrame();
		this.blockingQueue = blockingQueue;
		this.imageLoader = new ImageLoader();
		this.back = new JLabel(imageLoader.getUndo());
		this.next = new JLabel(imageLoader.getRedo());
		this.newGame = new JLabel(imageLoader.getReload());
		this.contentPane = new JPanel();
		this.scores = new Scores();
		this.arrayOfBalls = new ArrayOfBalls(blockingQueue, mockup);
		this.menuBar = new JMenuBar();
		this.menu = new JMenu("Menu");
		this.gameMode = new JMenu("Zmień tryb gry");
		this.setNewGamePlay = new SetNewGamePlay(this.blockingQueue);
		this.backLabelClicked = new BackLabelClicked(this.blockingQueue);
		this.nextLabelClicked = new NextLabelClicked(this.blockingQueue);
		this.userNameChangedDuringGame = new UserNameChangedDuringGame(this.blockingQueue);
		this.normalGravitySetDuringGame = new NormalGravitySetDuringGame(this.blockingQueue);
		this.backGravitySetDuringGame = new BackGravitySetDuringGame(this.blockingQueue);
		initializeFrame();
	}

	/**
	 * Inicjalizacja wygladu ramki.
	 */
	private void initializeFrame()
	{
		this.frame.setContentPane(contentPane);
		menuBar.add(menu);
		for (JMenuItem item : set)
		{
			menu.add(item);
		}
		set[0].addActionListener(userNameChangedDuringGame);
		for (JMenuItem item : mode)
		{
			gameMode.add(item);
		}
		mode[0].addActionListener(normalGravitySetDuringGame);
		mode[1].addActionListener(backGravitySetDuringGame);
		menu.add(gameMode);
		this.frame.setJMenuBar(menuBar);
		contentPane.setLayout(null);
		contentPane.add(scores.getPanel());
		contentPane.add(arrayOfBalls.getPanel());
		back.setBounds(81, 258, 32, 32);
		back.addMouseListener(backLabelClicked);
		back.setToolTipText("Cofnij ostatni ruch");
		contentPane.add(back);
		next.setBounds(81, 306, 32, 32);
		next.addMouseListener(nextLabelClicked);
		next.setToolTipText("Przywroc ostatnio cofniety ruch");
		contentPane.add(next);
		newGame.setBounds(81, 354, 32, 32);
		newGame.addMouseListener(setNewGamePlay);
		newGame.setToolTipText("Nowa rozgrywka");
		contentPane.add(newGame);
		this.frame.pack();

	}

	/**
	 * Pokaż okienko.
	 */
	public void show()
	{
		this.frame.setResizable(false);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setTitle("Gra");
		this.frame.setSize(695, 495);
		this.frame.setVisible(true);
	}

	/**
	 * Ustawienie kompletnie nowego GamePlay'a.
	 * 
	 * @param mockup
	 *            makieta według której mają zostać ustawione informacje.
	 */
	public void setNewGamePlay(final Mockups mockup)
	{
		((Scores) scores).setPointsSumIndicator(mockup.getPoints());
		((Scores) scores).setLastPointsIndicator(mockup.getLastMovePoints());
		((Scores) scores).setUserName(mockup.getUserName());
		((ArrayOfBalls) arrayOfBalls).setNewArrayOfBalls(mockup);
	}

	/**
	 * Uaktualnienie już istniejącego GamePlay'a, wykonywane szybciej niż setNewGamePlay ze wzgledu na wewnętrzenie
	 * przeprowadzane sprawdzenie różnic w aktualnej i poprzedniej tablicy zawierajacej kulki.
	 * 
	 * @param mockup
	 *            makieta według której ma zostać ustawione informacje.
	 */
	public void setGamePlay(final Mockups mockup)
	{
		((Scores) scores).setPointsSumIndicator(mockup.getPoints());
		((Scores) scores).setLastPointsIndicator(mockup.getLastMovePoints());
		((Scores) scores).setUserName(mockup.getUserName());
		((ArrayOfBalls) arrayOfBalls).setArrayOfBalls(mockup);
	}
}
