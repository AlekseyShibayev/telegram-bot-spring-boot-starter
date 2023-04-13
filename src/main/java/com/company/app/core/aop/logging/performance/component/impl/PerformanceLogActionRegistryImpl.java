package com.company.app.core.aop.logging.performance.component.impl;

import com.company.app.core.aop.logging.performance.component.config.PerformanceLogActionType;
import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;
import com.company.app.core.aop.logging.performance.component.api.PerformanceLogActionRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PerformanceLogActionRegistryImpl implements PerformanceLogActionRegistry {

	@Autowired
	List<PerformanceLogAction> actionList;
	private Map<PerformanceLogActionType, PerformanceLogAction> actions;

	@PostConstruct
	public void init() {
		registerActions();
	}

	private void registerActions() {
		actions = new EnumMap<>(PerformanceLogActionType.class);
		for (PerformanceLogAction action : actionList) {
			if (actions.containsKey(action.getType())) {
				throw new DuplicateKeyException(action.getType().toString());
			} else {
				actions.put(action.getType(), action);
			}
		}
	}

	@Override
	public PerformanceLogAction getAction(PerformanceLogActionType actionType) {
		PerformanceLogAction action = actions.get(actionType);
		if (action == null) {
			throw new UnsupportedOperationException(actionType.toString());
		} else {
			return action;
		}
	}
}
