package com.varguss.dao.filter;

import com.varguss.domain.ComputerPart;

import java.util.List;

public interface Strategy {
    Integer lastPageNumber();
    List<ComputerPart> page(int pageNumber);
    List<ComputerPart> parts();
    List<ComputerPart> search(String partOfName);
}
