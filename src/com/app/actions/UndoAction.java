package com.app.actions;

import com.app.View;

import javax.swing.*;
import java.awt.event.ActionEvent;

//класс отмены действия
public class UndoAction extends AbstractAction {

    private View view;

    public UndoAction(View view) {
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        view.undo();
    }

}
