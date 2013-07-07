/**
 * 
 */
package balls.common.events.nogame;

/**
 * @author Przemysław Piórkowski
 * 
 */
public final class UserNameChangedEvent extends NoGameEvent
{
	private final String name;

	/**
	 * Konstruktor przyjmujący jako argument nowe imie gracza.
	 * 
	 * @param name
	 */
	public UserNameChangedEvent(final String name)
	{
		this.name = name;
	}

	/**
	 * Domyślny konstruktor bez parametowy tworzy Event o domyślnej wartości
	 * przechowywanego imienia.
	 */
	public UserNameChangedEvent()
	{
		this.name = new String();
	}

	/**
	 * Zwraca przechowywane w Evencie imie.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
}
