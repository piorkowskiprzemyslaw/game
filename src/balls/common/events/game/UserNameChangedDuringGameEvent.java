/**
 * 
 */
package balls.common.events.game;

/**
 * Event zmiany nazwy uzytkownika w trakcie działania gry.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class UserNameChangedDuringGameEvent extends GameEvent
{
	private final String name;

	/**
	 * Konstruktor przyjmujacy jako argument nowe imie gracza.
	 * 
	 * @param name
	 */
	public UserNameChangedDuringGameEvent(final String name)
	{
		this.name = name;
	}

	/**
	 * Konstrutor bez parametrowy tworzy Event o domyślnej wartości
	 * przechowywanego imienia.
	 */
	public UserNameChangedDuringGameEvent()
	{
		this.name = new String();
	}

	/**
	 * Pobranie imienia zwiazanego z tym eventem.
	 * 
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

}
