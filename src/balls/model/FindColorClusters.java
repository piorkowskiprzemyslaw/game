/**
 * 
 */
package balls.model;

import java.util.HashSet;
import java.util.Set;

import balls.model.mockups.BallColor;

/**
 * Klasa znajdująca grupy jednokolorowych kulek.
 * 
 * @author Przemysław Piórkowski
 * 
 */
final class FindColorClusters
{
	private final ColumnSet columnSet;
	private final RowSet rowSet;

	/**
	 * Domyślny konstruktor pobierający wyglad planszy.
	 * 
	 * @param columnSet
	 * @param rowSet
	 */
	FindColorClusters(final ColumnSet columnSet, final RowSet rowSet)
	{
		this.columnSet = columnSet;
		this.rowSet = rowSet;
	}

	/**
	 * Zwrócenie GroupSet'a ze wszystkimi grupami znajdującymi się na planszy.
	 * 
	 * @return ballGroupSet
	 */
	BallSetGroup getGroups()
	{
		final BallSetGroup columnClusters = getGroupsInColumns();
		final BallSetGroup rowClusters = getGroupsInRows();
		BallSetGroup returned = new BallSetGroup();
		BallSet setAdd;
		Set<BallSet> setToRemove;

		for (Column column : columnSet)
		{
			for (Ball ball : column)
			{
				if (ball.getBallColor() == BallColor.EMPTY)
				{
					continue;
				}
				setAdd = new BallSet();
				setToRemove = new HashSet<BallSet>();
				for (BallSet ballSet : columnClusters)
				{
					if (ballSet.contains(ball))
					{
						setAdd.add(ballSet);
						break;
					}
				}
				for (BallSet ballSet : rowClusters)
				{
					if (ballSet.contains(ball))
					{
						setAdd.add(ballSet);
						break;
					}
				}
				for (BallSet ballSet : returned)
				{
					if (ballSet.contains(ball))
					{
						setToRemove.add(ballSet);
						setAdd.add(ballSet);
					}
				}
				returned.removeAll(setToRemove);
				returned.add(setAdd);
			}
		}

		return returned;
	}

	/**
	 * Zwraca zbiór grup powstających w kolumnach.
	 * 
	 * @return ballSetGroup
	 */
	private BallSetGroup getGroupsInColumns()
	{
		BallSetGroup returned = new BallSetGroup();
		BallSet ballSet = null;

		for (Column column : columnSet)
		{
			for (Ball ball : column)
			{
				if (ballSet == null)
				{
					ballSet = new BallSet();
				}
				if (ballSet.add(ball) == false)
				{
					returned.add(ballSet);
					ballSet = new BallSet();
					ballSet.add(ball);
				}
			}
			returned.add(ballSet);
			ballSet = null;
		}
		return returned;
	}

	/**
	 * Zwraca zbiór grup powstających w rzędach.
	 * 
	 * @return ballSetGroup
	 */
	private BallSetGroup getGroupsInRows()
	{
		BallSetGroup returned = new BallSetGroup();
		BallSet ballSet = null;

		for (Row row : rowSet)
		{
			for (Ball ball : row)
			{
				if (ballSet == null)
				{
					ballSet = new BallSet();
				}
				if (ballSet.add(ball) == false)
				{
					returned.add(ballSet);
					ballSet = new BallSet();
					ballSet.add(ball);
				}
			}
			returned.add(ballSet);
			ballSet = null;
		}

		return returned;
	}
}
