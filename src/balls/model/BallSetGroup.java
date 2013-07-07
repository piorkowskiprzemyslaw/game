/**
 * 
 */
package balls.model;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Klasa reprezentująca zbiór Grupę kulek o jednakowych kolorach.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class BallSetGroup implements Iterable<BallSet>
{
	private final Set<BallSet> ballSetGroup;

	/**
	 * Tworzy pustą grupe zbiorów kulek.
	 */
	BallSetGroup()
	{
		this.ballSetGroup = new HashSet<BallSet>();
	}

	/**
	 * Dodaje zbiór kulek do obecnej grupy.
	 * 
	 * @param ballSet
	 * @return true if adding was successful
	 */
	boolean add(final BallSet ballSet)
	{
		return this.ballSetGroup.add(ballSet);
	}

	/**
	 * Dodanie do grupy wszyskich zbiorów z grupy another.
	 * 
	 * @param another
	 * @return true if adding was successful
	 */
	boolean add(final BallSetGroup another)
	{
		return this.ballSetGroup.addAll(another.ballSetGroup);
	}

	/**
	 * sprawdza czy dana kulka znajduje sie w grupie zbiorów.
	 * 
	 * @param ball
	 * @return ture if group contain ball
	 */
	boolean contains(final Ball ball)
	{
		for (BallSet ballSet : ballSetGroup)
		{
			if (ballSet.contains(ball))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * sprawdza czy dany zbiór znajduje sie w grupie.
	 * 
	 * @param ballSet
	 * @return true if group contain set
	 */
	boolean contains(final BallSet ballSet)
	{
		return this.ballSetGroup.contains(ballSet);
	}

	/**
	 * Reprezentacja w postaci łancucha znaków.
	 */
	public String toString()
	{
		StringBuilder returned = new StringBuilder();
		for (BallSet ballSet : ballSetGroup)
		{
			returned.append(ballSet.toString() + "\n");
		}
		return returned.toString();
	}

	/**
	 * Zwraca ilosc zbiorow w tej grupie.
	 * 
	 * @return number of sets in group
	 */
	int size()
	{
		return ballSetGroup.size();
	}

	/**
	 * Zwraca ilosc kulek przechowywanych we wszystkich grupach.
	 * 
	 * @retur number of balls in all groups in set.
	 */
	int ballNumber()
	{
		int counter = 0;
		for (BallSet ballSet : ballSetGroup)
		{
			counter += ballSet.size();
		}
		return counter;
	}

	/**
	 * Usuwa ballSet z grupy.
	 * 
	 * @param ballSet
	 * @return true if removing was successful
	 */
	boolean remove(final BallSet ballSet)
	{
		return ballSetGroup.remove(ballSet);
	}

	/**
	 * Usuwa wszystkie ballSety z przekazanej w argumencie listy
	 * 
	 * @param listToRemove
	 * @return true if removing was successful
	 */
	boolean removeAll(final Set<BallSet> listToRemove)
	{
		this.ballSetGroup.removeAll(listToRemove);
		return true;
	}
	
	@Override
	public Iterator<BallSet> iterator()
	{
		return ballSetGroup.iterator();
	}
}
