package design_pattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽象主体角色
 * Created by Len on 29/01/2017.
 */
public class Subject {

	private List<Observer> list = new ArrayList<>();

	public void addObserver(Observer observer){
		list.add(observer);
		System.out.println("add observer = [" + observer + "]");
	}

	public void nodifyObservers(String newState){
		list.forEach(observer -> observer.update(newState));
	}
}
