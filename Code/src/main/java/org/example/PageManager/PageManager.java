package org.example.PageManager;

import org.example.Exceptions.Unchecked.InexistentPageException;

import java.util.ArrayList;
import java.util.List;

public class PageManager {
    private final List<String> content;
    private final static int elementsPerPage = 5;
    private final int fieldsPerElement;
    private int nrOfPages = 0;
    private final List<Page> pages;
    public PageManager(ArrayList<Object> content, int fieldsPerElement) {
        this.content = content.stream().map(x -> x.toString()).toList();
        this.fieldsPerElement = fieldsPerElement;
        pages = new ArrayList<>();
    }

    /**
     * Creates pages based on the content list, dividing it into subsets to fit into pages.
     */
    public void createPages(){
        int nrOfTotalElements = content.size() / fieldsPerElement;
        nrOfPages = nrOfTotalElements / elementsPerPage;
        if(nrOfTotalElements%elementsPerPage != 0){
            nrOfPages++;
        }

        int sizeOfAPage = elementsPerPage*fieldsPerElement;
        for (int i=0; i<nrOfPages; i++){
            int inf = i*sizeOfAPage;
            int sup = Math.min(i*sizeOfAPage+sizeOfAPage,content.size());
            pages.add(new Page(content.subList(inf, sup), fieldsPerElement, elementsPerPage));
        }
    }

    /**
     * Shows the result of a command for a specific page index.
     *
     * @param index the index of the page to show
     * @param command the command used to generate the result
     * @return a formatted string showing the command result for the specified page index
     * @throws InexistentPageException if the specified page index is out of bounds
     */
    public String showCommandResult(int index, String command){
        String finalMessage = "To view other pages run the query as follows: " + command +" [pageIndex]";
        if(nrOfPages == 0){
            if(index != 1){
                throw new InexistentPageException();
            }
            index = 0;
            return "Page " + (index) + " of " + nrOfPages + " (" + elementsPerPage + " items per page):";
        }else
        if(1>index || index>nrOfPages){
            throw new InexistentPageException();
        }
        String result = "Page " + (index) + " of " + nrOfPages + " (" + elementsPerPage + " items per page):\n";
        Page page = pages.get(index - 1);
        result += page.getContent() + finalMessage;
        return result;
    }


}
