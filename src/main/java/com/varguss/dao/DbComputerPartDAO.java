package com.varguss.dao;

import com.varguss.dao.filter.Strategy;
import com.varguss.dao.filter.StrategyFactory;
import com.varguss.dao.filter.StrategyType;
import com.varguss.domain.ComputerPart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;

@Repository
@SessionScope
@Transactional
public class DbComputerPartDAO implements ComputerPartDAO {
    private SessionFactory sessionFactory;
    private Strategy strategy;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        strategy = StrategyFactory.getStrategy(StrategyType.ALL, sessionFactory);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComputerPart> allParts() {
        return sessionFactory.getCurrentSession().createQuery("FROM ComputerPart part ORDER BY part.count DESC", ComputerPart.class).getResultList();
    }

    @Override
    @Transactional(readOnly = true)
    public ComputerPart part(Long id) {
        return sessionFactory.getCurrentSession().find(ComputerPart.class, id);
    }

    @Override
    public Long getMaxCollectedComputersCount() {
        return sessionFactory.getCurrentSession().createQuery("SELECT MIN(part.count) FROM ComputerPart part WHERE part.isImportant = TRUE", Long.class).getSingleResult();
    }

    @Override
    public void save(String name, boolean isImportant, Long count) {
        sessionFactory.getCurrentSession().saveOrUpdate(new ComputerPart(name, isImportant, count));
    }

    @Override
    public void remove(Long id) {
        ComputerPart computerPart = part(id);

        if (computerPart != null)
            sessionFactory.getCurrentSession().delete(computerPart);
    }

    @Override
    public void updateImportance(Long id, boolean isImportant) {
        ComputerPart computerPart = part(id);

        if (computerPart != null)
            computerPart.setImportant(isImportant);
    }

    @Override
    public void updateName(Long id, String name) {
        ComputerPart computerPart = part(id);

        if (computerPart != null)
            computerPart.setName(name);
    }

    @Override
    public void updateCount(Long id, Long count) {
        ComputerPart computerPart = part(id);

        if (computerPart != null)
            computerPart.setCount(count);
    }

    // strategy methods
    @Override
    @Transactional(readOnly = true)
    public List<ComputerPart> page(int pageNumber) {
        return strategy.page(pageNumber);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComputerPart> parts() {
        return strategy.parts();
    }

    @Override
    @Transactional(readOnly = true)
    public Integer lastPageNumber() {
        return strategy.lastPageNumber();
    }

    @Override
    @Transactional(readOnly = true)
    public List<ComputerPart> search(String partOfName) {
        return strategy.search(partOfName);
    }

    @Override
    public void changeStrategy(StrategyType strategyType) {
        this.strategy = StrategyFactory.getStrategy(strategyType, sessionFactory);
    }

    @Override
    public String getCurrentStrategy() {
        return strategy.getClass().getSimpleName();
    }
}
