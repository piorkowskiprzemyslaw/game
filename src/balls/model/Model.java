/**
 * 
 */
package balls.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import balls.common.Coordinates;
import balls.model.mockups.BallColor;
import balls.model.mockups.Mockups;

/**
 * @author Przemysław Piórkowski
 * 
 */
public final class Model
{
	/** Rozmiar planszy */
	private int size;
	/** Punkty rozgrywki */
	private Points points;
	/** Punkty ostatniego ruchu rozgrywki */
	private LastMovePoints lastMovePoints;
	/** Nazwa gracza */
	private String userName;
	/** Flaga sprawdzająca koniec gry */
	private boolean isEnd;
	/** Strategia trybów gry */
	private Map<Gravity, Cleaning> gameMode = new HashMap<Gravity, Cleaning>();
	/** Gravitacja w grze */
	private Gravity gravity;
	/** Zbiór kolumn na planszy */
	private ColumnSet columnSet;
	/** Zbiór wierszy na planszy */
	private RowSet rowSet;
	/** Stos ostatnich ruchów urzytkownika */
	private final Stack<HistoryContainer> backup;
	/** Stos cofnietych ruchów urzytkownika */
	private final Stack<HistoryContainer> nextMoves;
	/** Jednokolorowe grupy kulek znajdujace sie na planszy */
	private BallSetGroup ballSetGroup;

	/**
	 * Enumerator do wskazywania trybu grawitacji.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private enum Gravity
	{
		NORMAL, BACK
	}

	/**
	 * Domyślny konstruktor.
	 */
	public Model()
	{
		this(15);
	}

	/**
	 * Konstruktor z parametrem rozmiaru generowanej planszy.
	 * 
	 * @param size
	 */
	public Model(final int size)
	{
		Generator generator = new Generator(size);
		generator.uniformDistribution();
		this.gameMode = init();
		this.columnSet = generator.getColumnSet();
		this.rowSet = generator.getRowSet();
		this.gravity = Gravity.NORMAL;
		this.size = size;
		this.userName = "Default";
		this.points = new Points();
		this.lastMovePoints = new LastMovePoints();
		this.backup = new Stack<HistoryContainer>();
		this.nextMoves = new Stack<HistoryContainer>();
		this.ballSetGroup = (new FindColorClusters(columnSet, rowSet))
				.getGroups();
	}

	/**
	 * Inicjalizacja mapy trybów gry.
	 */
	private Map<Gravity, Cleaning> init()
	{
		HashMap<Gravity, Cleaning> returnedMap = new HashMap<Gravity, Cleaning>();
		returnedMap.put(Gravity.NORMAL, new CleaningWithNormalGravity());
		returnedMap.put(Gravity.BACK, new CleaningWithBackGravity());
		return Collections.unmodifiableMap(returnedMap);
	}

	/**
	 * Ustawienie nowej planszy do gry na nowe, losowe wartości.
	 */
	public void setRandomly()
	{
		Generator generator = new Generator(size);
		generator.uniformDistribution();

		this.backup.clear();
		this.nextMoves.clear();
		this.points = new Points();
		this.lastMovePoints = new LastMovePoints();
		this.isEnd = false;
		this.columnSet = generator.getColumnSet();
		this.rowSet = generator.getRowSet();
		this.ballSetGroup = (new FindColorClusters(columnSet, rowSet))
				.getGroups();
	}

	/**
	 * Wygenerowanie makiety modelu na podstawie jego aktualnego stanu. Trzeba
	 * wygenerować tablice dwuwymiarową do makiety na podstawie aktualnego stanu
	 * wiec troche zabawy z indeksami tablicy. Zapełnianie tablicy dwuwymiarowej
	 * musi rozpoczynać sie od prawego dolnego rogu dlatego kolejnosc wzrastania
	 * indeksów nienaturalna!
	 * 
	 * @return mockups with actual state of model
	 */
	public Mockups getMockup()
	{
		BallColor[][] mockupArray = new BallColor[this.size][this.size];
		int i = this.size - 1;
		int j = this.size - 1;

		for (int k = 0; k < this.size; ++k)
		{
			for (int l = 0; l < this.size; ++l)
			{
				mockupArray[k][l] = BallColor.EMPTY;
			}
		}

		// kolejnosc zapelniania tablicy od prawego dolnego rogu.
		for (Column column : columnSet)
		{
			for (Ball ball : column)
			{
				mockupArray[i][j] = ball.getBallColor();
				j = j - 1;
			}
			j = this.size - 1;
			i = i - 1;
		}

		return new Mockups(mockupArray, userName, isEnd, points.getPoints(),
				lastMovePoints.getLastMovePoints());
	}

	/**
	 * Dotkniecie zadanej kulki o danych współrzędnych.
	 * 
	 * @param coordinates
	 *            of touched ball
	 */
	public void touch(final Coordinates coordinates)
	{
		Ball touchedBall;
		int x = this.size - 1 - coordinates.getX();
		int y = this.size - 1 - coordinates.getY();

		if (columnSet.size() <= x)
		{
			return;
		}

		if (columnSet.get(x).size() <= y)
		{
			return;
		}

		touchedBall = columnSet.get(x).get(y);

		for (BallSet ballSet : ballSetGroup)
		{
			if (ballSet.contains(touchedBall))
			{

				if (ballSet.size() < 2)
				{
					return;
				}

				nextMoves.clear();
				backup.push(new HistoryContainer(columnSet, rowSet, points,
						lastMovePoints));

				if (this.lastMovePoints.setLastMovePoints(PointsCounter
						.computePoint(ballSet.size())))
				{
					this.points.addPoints(this.lastMovePoints);
				}

				gameMode.get(gravity).setVariables(columnSet, rowSet, ballSet);
				gameMode.get(gravity).doCleaning();
				rowSet = gameMode.get(gravity).getActualRowSet();
				columnSet = gameMode.get(gravity).getActualColumnSet();
				break;
			}
		}

		this.ballSetGroup = (new FindColorClusters(columnSet, rowSet))
				.getGroups();
		this.isEnd = (new EndChecker(this.ballSetGroup)).check();
	}

	/**
	 * Cofnięcie ostatniego ruchu.
	 * 
	 * @return true if action was successful
	 */
	public boolean backOne()
	{
		if (backup.size() != 0)
		{
			nextMoves.push(new HistoryContainer(columnSet, rowSet, points,
					lastMovePoints));
			HistoryContainer historyContainer = backup.pop();
			this.columnSet = historyContainer.getColumnSet();
			this.rowSet = columnSet.getRowReprezentation();
			this.points = historyContainer.getPoints();
			this.lastMovePoints = historyContainer.getLastMovePoints();
			this.ballSetGroup = (new FindColorClusters(this.columnSet,
					this.rowSet)).getGroups();
			return true;
		}
		return false;
	}

	/**
	 * Przywrócenie ostanio cofniętego ruchu.
	 * 
	 * @return true if action was successful.
	 */
	public boolean nextOne()
	{
		if (nextMoves.size() != 0)
		{
			HistoryContainer historyContainer = nextMoves.pop();
			backup.push(new HistoryContainer(columnSet, rowSet, points,
					lastMovePoints));
			this.columnSet = historyContainer.getColumnSet();
			this.rowSet = columnSet.getRowReprezentation();
			this.points = historyContainer.getPoints();
			this.lastMovePoints = historyContainer.getLastMovePoints();
			this.ballSetGroup = (new FindColorClusters(this.columnSet,
					this.rowSet)).getGroups();
			return true;
		}
		return false;
	}

	/**
	 * Przestawianie grawitacji na normalna.
	 */
	public void setGravityToNormal()
	{
		this.gravity = Gravity.NORMAL;
	}

	/**
	 * Przestawienie grawitacji na boczną.
	 */
	public void setGravityToBack()
	{
		this.gravity = Gravity.BACK;
	}

	/**
	 * Ustaw nazwę użytkownika na nową.
	 * 
	 * @param name
	 */
	public void setNewUserName(final String name)
	{
		this.userName = name;
	}

	/**
	 * Ustawia rozmiar planszy na nowy;
	 * 
	 * @param size
	 * @return true if action was successful
	 */
	public boolean setSize(final int size)
	{
		if (size == this.size)
			return false;
		this.size = size;
		return true;
	}
}
