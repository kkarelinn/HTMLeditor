package com.app.listeners;

import com.app.View;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.util.Arrays;

public class TextEditMenuListener implements MenuListener {
    private View view;

    public TextEditMenuListener(View view) {
        this.view = view;
    }

    @Override
    public void menuSelected(MenuEvent e) {
        JMenu menu = (JMenu) e.getSource();
        Arrays.stream(menu.getMenuComponents()).forEach(c->c.setEnabled(view.isHtmlTabSelected()));
    }

    @Override
    public void menuDeselected(MenuEvent e) {

    }

    @Override
    public void menuCanceled(MenuEvent e) {

    }
}
