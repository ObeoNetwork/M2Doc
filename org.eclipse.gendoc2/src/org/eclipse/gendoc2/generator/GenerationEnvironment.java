package org.eclipse.gendoc2.generator;

import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * {@link GenerationEnvironment} instance provide a scoped variable definition
 * service able to hide defintions by pushing a ne scope and reveal hiden
 * definitions by poping a scope.
 * 
 * @author Romain Guider
 *
 */
public class GenerationEnvironment {
	/**
	 * The stack of map that holds the definitions.
	 */
	private Stack<Map<String, Object>> environement;

	/**
	 * Creates a new {@link GenerationEnvironment} instance. The created
	 * instance has a scope pushed.
	 * 
	 */
	public GenerationEnvironment() {
		this.environement = new Stack<Map<String, Object>>();
		this.pushScope();
	}

	/**
	 * Creates a new {@link GenerationEnvironment} instance. The created
	 * instance has a scope pushed.
	 * 
	 */
	public GenerationEnvironment(Map<String, Object> definitions) {
		this.environement = new Stack<Map<String, Object>>();
		this.pushScope();
		this.environement.peek().putAll(definitions);
	}

	/**
	 * pushes a new empty scope onto the generation environment.
	 */
	public void pushScope() {
		this.environement.push(new HashMap<String, Object>());
	}

	/**
	 * pop the topmost scope.
	 * 
	 * @throws EmptyStackException
	 *             if the generation environment doesn't have any scope to pop.
	 */
	public void popScope() {
		this.environement.pop();
	}

	/**
	 * returns the value associated to the specified name. Returns
	 * <code>null</code> if there's no value associated to the name.
	 * 
	 * @param name
	 * @return the value associated to the specified name.
	 */
	public Object getValue(String name) {
		int size = this.environement.size();
		for (int i = size - 1; i >= 0; i--) {
			Object result = this.environement.get(i).get(name);
			if (result != null) {
				return result;
			}
		}
		return null;
	}

	/**
	 * associates a value to the specified name.
	 * 
	 * @param name
	 *            the name to define
	 * @param value
	 *            the value to associate to the name.
	 */
	public void setValue(String name, Object value) {
		this.environement.peek().put(name, value);
	}

	/**
	 * Returns the currently visible definitions.
	 * 
	 * @return the currently visible definitions.
	 */
	public Map<String, Object> getCurrentDefinitions() {
		Map<String, Object> result = new HashMap<String, Object>();
		for (Map<String, Object> scope : this.environement) {
			result.putAll(scope);
		}
		return result;
	}
}
