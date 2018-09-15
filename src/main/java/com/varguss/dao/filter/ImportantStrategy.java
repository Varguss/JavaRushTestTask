package com.varguss.dao.filter;

import com.varguss.domain.ComputerPart;
import org.hibernate.SessionFactory;

import java.util.List;

public class ImportantStrategy implements Strategy {
    private SessionFactory sessionFactory;

    ImportantStrategy(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Integer lastPageNumber() {
        Long partsCount = sessionFactory.getCurrentSession().createQuery("SELECT COUNT(part) FROM ComputerPart part WHERE part.isImportant = TRUE", Long.class).getSingleResult();

        if (partsCount == 0L)
            return 1;

        return (int) Math.ceil((double)partsCount/10);
    }

    @Override
    public List<ComputerPart> page(int pageNumber) {
        return sessionFactory.getCurrentSession().createQuery("FROM ComputerPart part WHERE part.isImportant = TRUE ORDER BY part.count DESC", ComputerPart.class)
                .setMaxResults(10)
                .setFirstResult((pageNumber-1)*10)
                .getResultList();
    }

    @Override
    public List<ComputerPart> parts() {
        return sessionFactory.getCurrentSession().createQuery("FROM ComputerPart part WHERE part.isImportant = TRUE ORDER BY part.count DESC", ComputerPart.class).getResultList();
    }

    @Override
    public List<ComputerPart> search(String partOfName) {
        return sessionFactory.getCurrentSession().createQuery("FROM ComputerPart part WHERE part.isImportant = TRUE AND part.name LIKE CONCAT('%', ?1, '%') ORDER BY part.count DESC", ComputerPart.class).setParameter(1, partOfName).getResultList();
    }
}
