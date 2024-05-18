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
