package com.app;

import com.app.listeners.FrameListener;
import com.app.listeners.TabbedPaneChangeListener;
import com.app.listeners.UndoListener;

import javax.swing.*;
import javax.swing.undo.UndoManager;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class View extends JFrame implements ActionListener {
    private Controller controller;
    private JTabbedPane tabbedPane = new JTabbedPane();
    private JTextPane htmlTextPane = new JTextPane();
    private JEditorPane plainTextPane = new JEditorPane();

    private final UndoManager undoManager = new UndoManager();
    private final UndoListener undoListener = new UndoListener(undoManager);

    public View() {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            ExceptionHandler.log(e);
        }
    }


    public Controller getController() {
        return controller;
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "Новый":
                controller.createNewDocument();
                break;
            case "Открыть":
                controller.openDocument();
                break;
            case "Сохранить":
                controller.saveDocument();
                break;
            case "Сохранить как...":
                controller.saveDocumentAs();
                break;
            case "Выход":
                controller.exit();
                break;
            case "О программе":
                showAbout();
                break;
        };
    }

    public void init() {
        initGui();
        FrameListener frameListener = new FrameListener(this);
        this.addWindowListener(frameListener);
        this.setVisible(true);
    }

    public void initMenuBar() {

        JMenuBar menu = new JMenuBar();
        MenuHelper.initFileMenu(this, menu);
        MenuHelper.initEditMenu(this, menu);
        MenuHelper.initStyleMenu(this, menu);
        MenuHelper.initAlignMenu(this, menu);
        MenuHelper.initColorMenu(this, menu);
        MenuHelper.initFontMenu(this, menu);
        MenuHelper.initHelpMenu(this, menu);
        getContentPane().add(menu, BorderLayout.NORTH);

    }

    public void initEditor() {
        htmlTextPane.setContentType("text/html");
        JScrollPane jScrollPaneHtml = new JScrollPane(htmlTextPane);
        tabbedPane.addTab("HTML", jScrollPaneHtml);

        JScrollPane jScrollPanePlane = new JScrollPane(plainTextPane);
        tabbedPane.addTab("Текст", jScrollPanePlane);
        tabbedPane.setPreferredSize(new Dimension(800, 600));
        TabbedPaneChangeListener tabbedPaneChangeListener = new TabbedPaneChangeListener(this);
        tabbedPane.addChangeListener(tabbedPaneChangeListener);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);
//        setLocationRelativeTo(null);
    }

    public void initGui() {
        initMenuBar();
        initEditor();
        pack();
    }


    public void exit() {
        controller.exit();
    }

    public void selectedTabChanged() {
       if (tabbedPane.getSelectedIndex()==0){
           controller.setPlainText(plainTextPane.getText());
       }
       else {
           plainTextPane.setText(controller.getPlainText());
           }
       resetUndo();
    }

    public boolean isHtmlTabSelected() {
        return tabbedPane.getSelectedIndex() == 0;
    }

    public void selectHtmlTab(){
        tabbedPane.setSelectedIndex(0);
        resetUndo();
    }

    public void showAbout(){
        JOptionPane.showMessageDialog(
                this,
                "Редактор HTML. Version 1.0. Snap",
                "Не очень важная информация",
                JOptionPane.INFORMATION_MESSAGE);

    }

    public void update(){
      htmlTextPane.setDocument(controller.getDocument());
    }

    public UndoListener getUndoListener() {
        return undoListener;
    }

    public void undo() {
        try {
            undoManager.undo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void redo() {
        try {
            undoManager.redo();
        } catch (Exception e) {
            ExceptionHandler.log(e);
        }
    }

    public void resetUndo() {
        undoManager.discardAllEdits();
    }

    public boolean canUndo() {
        return undoManager.canUndo();
    }

    public boolean canRedo() {
        return undoManager.canRedo();
    }

}
