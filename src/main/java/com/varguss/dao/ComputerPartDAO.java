package com.varguss.dao;

import com.varguss.dao.filter.StrategyType;
import com.varguss.domain.ComputerPart;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ComputerPartDAO {
    List<ComputerPart> allParts();
    ComputerPart part(Long id);

    void save(String name, boolean isImportant, Long count);
    void remove(Long id);

    List<ComputerPart> search(String partOfName);

    void updateImportance(Long id, boolean isImportant);
    void updateName(Long id, String name);
    void updateCount(Long id, Long count);

    //filter
    Integer lastPageNumber();
    List<ComputerPart> page(int pageNumber);
    List<ComputerPart> parts();

    void changeStrategy(StrategyType strategyType);
    String getCurrentStrategy();

    Long getMaxCollectedComputersCount();
}
