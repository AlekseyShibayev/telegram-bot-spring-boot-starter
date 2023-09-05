package com.company.app.core.aop.logging.performance.component;

import com.company.app.core.aop.logging.performance.component.action.PerformanceLogAction;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class PerformanceLogActionRegistry {

    private final List<PerformanceLogAction> actionList;
    private final Map<PerformanceLogActionType, PerformanceLogAction> actions = new EnumMap<>(PerformanceLogActionType.class);

    @PostConstruct
    public void init() {
        for (PerformanceLogAction action : actionList) {
            if (actions.containsKey(action.getType())) {
                throw new DuplicateKeyException(action.getType().toString());
            } else {
                actions.put(action.getType(), action);
            }
        }
    }

    public PerformanceLogAction getAction(PerformanceLogActionType actionType) {
        PerformanceLogAction action = actions.get(actionType);
        if (action == null) {
            throw new UnsupportedOperationException(actionType.toString());
        } else {
            return action;
        }
    }

}