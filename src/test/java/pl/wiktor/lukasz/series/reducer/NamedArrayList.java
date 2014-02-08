package pl.wiktor.lukasz.series.reducer;

import java.util.ArrayList;

public class NamedArrayList<E> extends ArrayList<E>{

    private static final long serialVersionUID = 1L;
    
    private String name;
    
    public NamedArrayList(String name) {
        super();
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return name;
    }

}
