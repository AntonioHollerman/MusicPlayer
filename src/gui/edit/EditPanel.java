package gui.edit;

import music.Editable;


public class EditPanel <T extends Editable>{
    private final T obj;
    public EditPanel(T obj){
        this.obj = obj;
    }
    public T getObj(){
        return obj;
    }
}
