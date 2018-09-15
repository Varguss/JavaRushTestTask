package com.varguss.controller;

import com.varguss.dao.ComputerPartDAO;
import com.varguss.dao.filter.StrategyType;
import com.varguss.domain.ComputerPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.atomic.AtomicInteger;

@Controller
@SessionAttributes("lastPage")
public class StorageController {
    private ComputerPartDAO dao;

    @Autowired
    public void setDao(ComputerPartDAO dao) {
        this.dao = dao;
    }

    @GetMapping("/")
    public String redirect() {
        return "redirect:/show/1";
    }

    @GetMapping("/show/{page}")
    public String show(Model model,
                       @PathVariable(name = "page") Integer page,
                       @ModelAttribute(name = "lastPage") AtomicInteger pageHolder) {

        Integer lastPage = dao.lastPageNumber();

        if (page <= lastPage || page > 0) {
            if (page <= 0) {
                model.addAttribute("details", dao.page(1));
                pageHolder.set(1);
            } else {
                model.addAttribute("details", dao.page(page));
                pageHolder.set(page);
            }
        } else {
            model.addAttribute("details", dao.page(lastPage));
            pageHolder.set(lastPage);
        }

        return "index";
    }

    @PostMapping("/savePart")
    public String addPart(@ModelAttribute("computerPart") ComputerPart computerPart,
                          @ModelAttribute("lastPage") AtomicInteger pageHolder) {
        if (computerPart.getId() != null) {
            ComputerPart retrievedComputerPart = dao.part(computerPart.getId());

            if (retrievedComputerPart != null) {
                if (!computerPart.getCount().equals(retrievedComputerPart.getCount()))
                    dao.updateCount(computerPart.getId(), computerPart.getCount());
                if (!computerPart.getName().equals(retrievedComputerPart.getName()))
                    dao.updateName(computerPart.getId(), computerPart.getName());
                if (computerPart.isImportant() != retrievedComputerPart.isImportant())
                    dao.updateImportance(computerPart.getId(), computerPart.isImportant());
            } else {
                dao.save(computerPart.getName(), computerPart.isImportant(), computerPart.getCount());
            }
        } else {
            dao.save(computerPart.getName(), computerPart.isImportant(), computerPart.getCount());
        }

        return "redirect:/show/"+pageHolder.get();
    }

    @PostMapping("/removePart")
    public String removePart(@RequestParam(name = "partId") Long partId,
                             @ModelAttribute("lastPage") AtomicInteger pageHolder) {
        dao.remove(partId);

        return "redirect:/show/"+pageHolder.get();
    }

    @GetMapping("/search")
    public String search(Model model,
                         @RequestParam(name = "partOfName") String name,
                         @ModelAttribute("lastPage") AtomicInteger pageHolder) {

        model.addAttribute("searchResults", dao.search(name));

        return "forward:/show/"+pageHolder.get();
    }

    @GetMapping("/filter")
    public String setFilter(@RequestParam("filter") String filter,
                            @ModelAttribute(name = "lastPage") AtomicInteger pageHolder) {

        switch (filter) {
            case "none": {
                dao.changeStrategy(StrategyType.ALL);
            } break;
            case "important": {
                dao.changeStrategy(StrategyType.IMPORTANT);
            } break;
            case "optional": {
                dao.changeStrategy(StrategyType.OPTIONAL);
            } break;
        }

        return "redirect:/show/"+pageHolder.get();
    }

    @ModelAttribute("lastPage")
    public AtomicInteger pageHolder() {
        return new AtomicInteger(1);
    }

    @ModelAttribute("computerPart")
    public ComputerPart getComputerPart() {
        return new ComputerPart();
    }

    @ModelAttribute("strategy")
    public String strategy() {
        return dao.getCurrentStrategy();
    }

    @ModelAttribute("lastPageNumber")
    public Integer lastPageNumber() {
        return dao.lastPageNumber();
    }

    @ModelAttribute("totalCollectedComputers")
    public Long totalCollectedComputers() {
        return dao.getMaxCollectedComputersCount();
    }
}