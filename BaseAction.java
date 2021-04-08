/*package com.fullerton.presentation.test;

import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fullerton.presentation.model.Rule;
import com.fullerton.presentation.pattern.cor.IAction;

public abstract class BaseAction implements IAction {

	public List<Rule> rules;

	
	@Autowired
	public ApplicationContext context;

	public IAction processHelper(String output) {
		IAction action = null;
		for (Rule rule : rules) {
			if (rule.getPredicate().equals(output)) {
				try { 
					action = (IAction) context.getBean(rule.getBean_name());
				} catch (BeansException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
		}
		return action;
	}



}
*/