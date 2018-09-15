package com.varguss.dao.filter;


import org.hibernate.SessionFactory;

public abstract class StrategyFactory {
    public static Strategy getStrategy(StrategyType type, SessionFactory sessionFactory) {
        switch (type) {
            case ALL: {
                return new AllStrategy(sessionFactory);
            }
            case IMPORTANT: {
                return new ImportantStrategy(sessionFactory);
            }
            case OPTIONAL: {
                return new OptionalStrategy(sessionFactory);
            }

            default: return null;
        }
    }
}
