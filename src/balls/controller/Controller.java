/**
 * 
 */
package balls.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;

import balls.common.events.AppEvent;
import balls.common.events.game.BackGravitySetDuringGameEvent;
import balls.common.events.game.BackLabelClickedEvent;
import balls.common.events.game.BallLabelClickedEvent;
import balls.common.events.game.EndGameFoundEvent;
import balls.common.events.game.NextLabelClickedEvent;
import balls.common.events.game.NormalGravitySetDuringGameEvent;
import balls.common.events.game.SetNewGamePlayEvent;
import balls.common.events.game.UserNameChangedDuringGameEvent;
import balls.common.events.nogame.BackGravitySetEvent;
import balls.common.events.nogame.BigSizeSetEvent;
import balls.common.events.nogame.MediumSizeSetEvent;
import balls.common.events.nogame.NewGameButtonClickedEvent;
import balls.common.events.nogame.NormalGravitySetEvent;
import balls.common.events.nogame.SmallSizeSetEvent;
import balls.common.events.nogame.UserNameChangedEvent;
import balls.model.Model;
import balls.view.View;

/**
 * Klasa fasadowa przechowująca całe API kontrolera.
 * 
 * @author Przemysław Piórkowski
 * 
 */
public final class Controller
{
	/** Model */
	private final Model model;
	/** Widok */
	private final View view;
	/** Blokująca kolejka wykorzystywana do komunikacji */
	private final LinkedBlockingQueue<AppEvent> blockingQueue;
	/** Mapa strategii */
	private final Map<Class<? extends AppEvent>, AppStrategy> strategyMap;

	/**
	 * Sparametryzowany konstruktor kontrolera, ustawia finalne referencje na wartości przekazane w argumentach.
	 * 
	 * @param model
	 * @param view
	 * @param blockingQueue
	 */
	public Controller(final Model model, final View view, final LinkedBlockingQueue<AppEvent> blockingQueue)
	{
		this.model = model;
		this.view = view;
		this.blockingQueue = blockingQueue;
		this.strategyMap = init();
	}

	/**
	 * Metoda startujaca i utrzymujaca dzialanie aplikacji
	 */
	public void start()
	{
		AppStrategy strategia;
		view.showHelloWindow();
		while (true)
		{
			try
			{
				AppEvent event = blockingQueue.take();
				strategia = strategyMap.get(event.getClass());
				strategia.doStrategy(event);
			} catch (InterruptedException e)
			{
				System.err.println("" + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/**
	 * Inicjalizacja Mapy strategii
	 */
	private Map<Class<? extends AppEvent>, AppStrategy> init()
	{
		Map<Class<? extends AppEvent>, AppStrategy> returnedMap = new HashMap<Class<? extends AppEvent>, AppStrategy>();
		returnedMap.put(new NewGameButtonClickedEvent().getClass(), new NewGameButtonClickedStrategy());
		returnedMap.put(new SetNewGamePlayEvent().getClass(), new SetNewGamePlayStrategy());
		returnedMap.put(new BallLabelClickedEvent().getClass(), new BallLabelClickedStrategy());
		returnedMap.put(new BackLabelClickedEvent().getClass(), new BackLabelClickedStrategy());
		returnedMap.put(new NextLabelClickedEvent().getClass(), new NextLabelClickedStrategy());
		returnedMap.put(new EndGameFoundEvent().getClass(), new EndGameFoundStrategy());
		returnedMap.put(new UserNameChangedEvent().getClass(), new UserNameChangedStrategy());
		returnedMap.put(new UserNameChangedDuringGameEvent().getClass(), new UserNameChangedDuringGameStrategy());
		returnedMap.put(new NormalGravitySetEvent().getClass(), new NormalGravitySetStrategy());
		returnedMap.put(new BackGravitySetEvent().getClass(), new BackGravitySetStrategy());
		returnedMap
				.put(new NormalGravitySetDuringGameEvent().getClass(), new NormalGravitySetDuringGameStrategy());
		returnedMap.put(new BackGravitySetDuringGameEvent().getClass(), new BackGravitySetDuringGameStrategy());
		returnedMap.put(new SmallSizeSetEvent().getClass(), new SmallSizeSetStrategy());
		returnedMap.put(new MediumSizeSetEvent().getClass(), new MediumSizeSetStrategy());
		returnedMap.put(new BigSizeSetEvent().getClass(), new BigSizeSetStrategy());
		return Collections.unmodifiableMap(returnedMap);
	}

	/**
	 * Abstrakcyjna klasa bedąca korzeniem dla strategii obslugi eventow.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private abstract class AppStrategy
	{
		abstract void doStrategy(AppEvent appEvent);
	}

	/**
	 * Abstrakcyjna klasa wprowadzajaca podzial strategii na te zwiazane z uruchomioną grą.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private abstract class GameStrategy extends AppStrategy
	{

	}

	/**
	 * Strategia zmiany grawitacji na boczą w trakcie działania gry. Najpierw zmieniana jest grawitacja w modelu,
	 * nastepnie resetuje model na koncu wyłanie aktualnej makiety modelu do widoku w celu jej wyswietlenia.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class BackGravitySetDuringGameStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setGravityToBack();
			model.setRandomly();
			view.setRefreshedGamePlayWindow(model.getMockup());
		}

	}

	/**
	 * Strategia cofnięcia ruchu. Cofany jest ruch na modelu, nastepnie wysyłana jest makieta do widoku w celu
	 * wyświetlenia aktualnego stanu modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class BackLabelClickedStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.backOne();
			view.setRefreshedGamePlayWindow(model.getMockup());
		}

	}

	/**
	 * Strategia obsługi wciśnięcia kulki. z argumentów przekazanych, odczytywane sa wspolrzedne kulki, nastepnie
	 * wywolywanie na modelu informacji o dotknietej kulce, potem przesłanie do widoku makiety z aktualnm stanem
	 * modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class BallLabelClickedStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			final BallLabelClickedEvent localEvent = (BallLabelClickedEvent) appEvent;
			model.touch(localEvent.getCoordinates());
			view.setRefreshedGamePlayWindow(model.getMockup());
		}

	}

	/**
	 * Strategia przy znalezionym końcu aktualnej gry. Ustawiana jest na nowo plansza na modelu i wysyłana jej
	 * makieta do widoku w celu wyświetlenia.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class EndGameFoundStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setRandomly();
			view.setNewGamePlayWindow(model.getMockup());
		}

	}

	/**
	 * Strategia obsługi przyciusku przywrócenia ostatnio cofnietej akcji. Najpierw, wywoływane jest przywrócenie
	 * na Modelu, nastepnie widok wyswietla stan modelu na postawie przesłanej aktualnej makiety modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class NextLabelClickedStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.nextOne();
			view.setRefreshedGamePlayWindow(model.getMockup());
		}
	}

	/**
	 * Strategia przestawienia grawitacji na normalna w trakcie dzialania gry. Przestawiana jest najpierw
	 * grawitacja na modelu, nastepnie resetowana jest plansza, tak, zeby nie robić mish mashu w rozgrywce i jej
	 * trybach. Na koncu widok otrzymuje aktualna makiete modelu i na jej podstawie wyswietla stan.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class NormalGravitySetDuringGameStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setGravityToNormal();
			model.setRandomly();
			view.setRefreshedGamePlayWindow(model.getMockup());
		}
	}

	/**
	 * Strategia Ustawienia nowej planszy gracza, Najpierw ustawiana jest nowa plansza, nastepnie widok dostaje
	 * makiete zeby ja wyswietlic.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class SetNewGamePlayStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setRandomly();
			view.setNewGamePlayWindow(model.getMockup());
		}
	}

	/**
	 * Strategia zmiany nazwy uzytkownika w trakcie działania gry. Najpierw na modelu zmianiana nazwa uzytkownika,
	 * zgodnie z arugmentem przekazanym. Nastepnie zgodnie z zasadami, ustawiana jest komplenie nowa plansza, dalej
	 * widok dostaje makiete do wyswietlenia.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class UserNameChangedDuringGameStrategy extends GameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			final UserNameChangedDuringGameEvent localEvent = (UserNameChangedDuringGameEvent) appEvent;
			model.setNewUserName(localEvent.getName());
			model.setRandomly();
			view.setRefreshedGamePlayWindow(model.getMockup());
		}
	}

	/**
	 * Abstrakcyjna klasa wprowadzajaca podzial strategii na te zwiazane z uruchomioną grą.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private abstract class NoGameStrategy extends AppStrategy
	{

	}

	/**
	 * Strategia zmiany grawitacji na boczna. Zmieniana jest tylko grawitacja na modelu, nie ma potrzeby
	 * wprowadzania dodatkowych zmian.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class BackGravitySetStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setGravityToBack();

		}
	}

	/**
	 * Strategia zmiany rozmiaru planszy na duzy. Przyjety duzy rozmiar planszy to 15. Wiec na modelu jest
	 * wprowadzany taki rozmiar. Jśli rozmiar ten wczesniej był już ustawiony to nie ma potrzeby ponownego
	 * odświeżania planszy, natomiast jeśli wczesniej był inny rozmiar, to trzeba jeszcze oświeżyć plansze, w celu
	 * ustawienia nowej, o zadanych rozmiarach.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class BigSizeSetStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			if (model.setSize(15))
			{
				model.setRandomly();
			}
		}
	}

	/**
	 * Strategia zmiany rozmiaru planszy na duzy. Przyjety średni rozmiar planszy to 10. Wiec na modelu jest
	 * wprowadzany taki rozmiar. Jśli rozmiar ten wczesniej był już ustawiony to nie ma potrzeby ponownego
	 * odświeżania planszy, natomiast jeśli wczesniej był inny rozmiar, to trzeba jeszcze oświeżyć plansze, w celu
	 * ustawienia nowej, o zadanych rozmiarach.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class MediumSizeSetStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			if (model.setSize(10))
			{
				model.setRandomly();
			}
		}
	}

	/**
	 * Strategia wciśnięcia przycisku nowej gry. Wywoływana jest funkcja widoku która wyświetla okienko nowej gry.
	 * Jako argument funkcja ta przyjmuje makiete ktora pobierana jest na podstawie obcenego stanu modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class NewGameButtonClickedStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			view.showGamePlayWindow(model.getMockup());
		}
	}

	/**
	 * Strategia ustawienia grawitacj na normlana. Wystarczy tylko przetawic grawitacje na modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class NormalGravitySetStrategy extends NoGameStrategy
	{

		@Override
		void doStrategy(AppEvent appEvent)
		{
			model.setGravityToNormal();
		}
	}

	/**
	 * Strategia zmiany rozmiaru planszy na duzy. Przyjety mały rozmiar planszy to 5. Wiec na modelu jest
	 * wprowadzany taki rozmiar. Jśli rozmiar ten wczesniej był już ustawiony to nie ma potrzeby ponownego
	 * odświeżania planszy, natomiast jeśli wczesniej był inny rozmiar, to trzeba jeszcze oświeżyć plansze, w celu
	 * ustawienia nowej, o zadanych rozmiarach.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class SmallSizeSetStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			if (model.setSize(5))
			{
				model.setRandomly();
			}
		}
	}

	/**
	 * Strategia zmiany nazwy uzytkownika. Wystarczy zmienic nazwe uzytkownika w modelu.
	 * 
	 * @author Przemysław Piórkowski
	 * 
	 */
	private class UserNameChangedStrategy extends NoGameStrategy
	{
		@Override
		void doStrategy(AppEvent appEvent)
		{
			final UserNameChangedEvent localEvent = (UserNameChangedEvent) appEvent;
			model.setNewUserName(localEvent.getName());
		}
	}

}
