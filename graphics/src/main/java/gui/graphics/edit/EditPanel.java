package gui.graphics.edit;

import content.Editable;

public class EditPanel <T extends Editable>{
    private final T content;
    public EditPanel(T content){
        this.content = content;
    }
    public T getContent(){
        return content;
    }
}
