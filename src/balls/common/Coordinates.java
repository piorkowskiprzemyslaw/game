package balls.common;

/**
 * Klasa do wskazywania wspolrzednych wybranej kulki i przekazywania tych danych
 * w sformalizowany sposob pomiedzy modulami programu.
 * 
 * @author Przemysław Piórkowski
 */
public final class Coordinates
{
	private final int x;
	private final int y;

	/**
	 * Konstruktor przyjmujący wartości początkowe.
	 * 
	 * @param x
	 * @param y
	 */
	public Coordinates(final int x, final int y)
	{
		this.x = x;
		this.y = y;
	}

	/**
	 * Domyślny konstruktor ustawiający wartości x i y na 0.
	 */
	public Coordinates()
	{
		this.x = 0;
		this.y = 0;
	}

	/**
	 * Pobranie wartości x.
	 * 
	 * @return x
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * Pobranie wartości y.
	 * 
	 * @return y
	 */
	public int getY()
	{
		return y;
	}
}
