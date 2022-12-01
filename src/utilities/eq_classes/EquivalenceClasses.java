package utilities.eq_classes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class EquivalenceClasses<T>{
	
	protected Comparator<T> _comparator;
	
	protected List<LinkedEquivalenceClass<T>> _classes;
	
	
	/**
	 * creates a EquivalenceClasses object and initializes the instance variables. 
	 * 
	 * @param a comparator that is used to initialize the instance variable comparator
	 */
	public EquivalenceClasses(Comparator<T> c) {
		_comparator = c;
		_classes = new ArrayList<LinkedEquivalenceClass<T>>();
	}
	
	
	/**
	 * Adds the passed element to the class it belongs to. Returns false if it does not 
	 * belong to any class. 
	 * 
	 * @param element -- the element added to the class which it belongs to
	 * @return whether the passed element was actually added
	 */
	public boolean add(T element) {
		if (element == null) return false;
		for (int i = 0; i < _classes.size(); i++) {
			if (_classes.get(i).belongs(element)) {
				return _classes.get(i).add(element);
			};
		}
		return false;
	}
	
	/**
	 * Adds a class to the list of equivalence classes
	 * @param eqClass - class to add
	 * @return whether the add was successful
	 */
	public boolean addClass(LinkedEquivalenceClass<T> eqClass) {
		return _classes.add(eqClass);
	}
	
	/**
	 * checks the classes if the passed element belongs to any of 
	 * the equivalence classes
	 * 
	 * @param target -- the element used to check if it belongs in any of the classes
	 * @return whether the passed element belongs to any class
	 */
	public boolean contains(T target) {
		if (target == null) return false;
		for (int i = 0; i < _classes.size(); i++) {
			if (_classes.get(i).contains(target)) return true;
		}
		return false;
	}
	
	
	/**
	 * @return the size of the list
	 */
	public int size() {
		return _classes.size();
	}
	
	
	/**
	 * @return the number of LinkedEquivalenceClasses that rest holds
	 */
	public int numClasses() {
		return _classes.size();
	}
	
	
	/**
	 * finds the class that the element belongs to and returns the index of that class or -1 
	 * if no such class exists.
	 * 
	 * @param element -- the element we are looking for
	 * @return index of the class that element belongs to or -1 if no such class exists
	 */
	protected int indexOfClass(T element) {
		if (element == null) return -1;
		for (int i = 0; i < _classes.size(); i++) {
			if (_classes.get(i).contains(element)) return i;
		}
		return -1;
	}
	
	/**
	 * @return a string containing all LinkedEquivalenceClasses
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (LinkedEquivalenceClass<T> c : this._classes)
		{
			sb.append("index " + _classes.indexOf(c) + " " + c.toString() + "\r\n");
		}
		return sb.toString();
	}
}