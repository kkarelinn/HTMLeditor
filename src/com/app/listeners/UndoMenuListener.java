package com.app.listeners;

import com.app.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

public class UndoMenuListener implements MenuListener {
    private View view;
    private JMenuItem undoMenuItem;
    private JMenuItem redoMenuItem;

    public UndoMenuListener(View view, JMenuItem undoMenuItem, JMenuItem redoMenuItem) {
        this.view = view;
        this.undoMenuItem = undoMenuItem;
        this.redoMenuItem = redoMenuItem;
    }
/*
Реализуй метод menuSelected(MenuEvent menuEvent). Он будет вызываться перед показом меню. Он должен:
10.3.1. Спрашивать у представления можем ли мы отменить действие с помощью метода boolean canUndo().
Пока у представления нет такого метода, поэтому добавь заглушку, которая всегда возвращает false.
10.3.2. Делать доступным или не доступным пункт меню undoMenuItem в зависимости от того, что нам вернуло представление.

Подсказка: используй метод setEnabled().
*/


    @Override
    public void menuSelected(MenuEvent e) {
        undoMenuItem.setEnabled(view.canUndo());
        redoMenuItem.setEnabled(view.canRedo());
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {
    }
}
