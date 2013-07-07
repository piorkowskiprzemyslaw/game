/**
 * 
 */
package balls.common.events.game;

import balls.common.Coordinates;

/**
 * Event kliknięcia konkretnej kulki na planszy.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class BallLabelClickedEvent extends GameEvent
{
	private final Coordinates coordinates;

	/**
	 * Konstruktor przyjmujacy wspolrzedne kliknietej kulki.
	 * 
	 * @param coordinates
	 */
	public BallLabelClickedEvent(final Coordinates coordinates)
	{
		this.coordinates = coordinates;
	}

	/**
	 * Domyślny konstruktor bezparametrowy, tworzy Event z wewnetrzynmi
	 * wsplorzednymi o domyślnych wartościach.
	 */
	public BallLabelClickedEvent()
	{
		this.coordinates = new Coordinates();
	}

	/**
	 * Pobranie wspolrzednych przechowywanych w evencie.
	 * 
	 * @return Coordinates.
	 */
	public Coordinates getCoordinates()
	{
		return coordinates;
	}
}
