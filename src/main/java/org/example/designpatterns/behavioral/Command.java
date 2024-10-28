package org.example.designpatterns.behavioral;

/*
   An object is used to encapsulate all information needed to perform an action or trigger an event at a later time.

   It separates the logic
   - Receiver
   - Invoker
   - Command

   This allows us to execute command, and also provides undo flexibility.
   This is similar to state pattern, but instead of modifying the object/Receiver states
   It changes the behaviour by providing functionalities and features.

 */
// text Editor Pattern

import java.util.Stack;

class Editor {
    private StringBuilder text;

    private int cursor;
    Editor(){
        text = new StringBuilder("");
        cursor =0;
    }

    public int getCursor(){
        return  cursor;
    }
    public void setCursor(int indx){
        cursor = indx <0? 0: (indx<text.length()?indx:text.length()-1);
    }
    public String selectText(int start, int end){
        int i = Math.max(start,0);
        int j = Math.min(cursor,end);
        setCursor(j);
        return text.substring(start,end);
    }

    public void deleteSection(int start, int end){
        int i = Math.max(start,0);
        int j = Math.min(cursor,end);
        text = text.delete(start,end);
        setCursor(i);
    }

    public void insert(String text){
        this.text.insert(cursor,text);
        setCursor(cursor+text.length());
    }

    public String getText(){
        return text.toString();
    }

    public void SetText(String text){
        this.text = new StringBuilder(text);
    }

}


abstract class CommandI {
    protected Editor editor;
    protected String backup;
    protected int backupCursor;
    protected Application application;
    CommandI(Application application, Editor editor){
        this.application = application;
        this.editor =editor;
        backup = "";
        backupCursor =0;
    }

    abstract public boolean executeCommand();

    protected void backupAll(){
        backup = editor.getText();
        backupCursor = editor.getCursor();
    }
    protected void undo(){
        editor.setCursor(backupCursor);
        editor.SetText(backup);
    }

}

class CopyCommand extends CommandI {

    CopyCommand(Application application,Editor editor) {
        super(application,editor);
    }

    @Override
    public boolean executeCommand() {
        application.clipBoard = editor.selectText(application.startSelection,application.endSelection);
        return  false;
    }
}

class CutCommand extends CommandI {

    CutCommand(Application application, Editor editor) {
        super(application,editor);
    }

    @Override
    public boolean executeCommand() {
        backupAll();
        application.clipBoard = editor.selectText(application.startSelection,application.endSelection);
        editor.deleteSection(application.startSelection,application.endSelection);
        return  true;
    }
}

class PasteCommand extends CommandI {

    PasteCommand(Application application,Editor editor) {
        super(application,editor);
    }

    @Override
    public boolean executeCommand() {
        backupAll();
        editor.insert(application.clipBoard);
        return true;
    }
}


class CommandHistory {
    Stack<CommandI> commands;
    CommandHistory(){
        commands = new Stack<>();
    }
    public void push(CommandI command){
        commands.push(command);
    }

    public CommandI pop(){
        if(commands.isEmpty())
            return null;
        return commands.pop();
    }

    public boolean isEmpty(){
        return commands.isEmpty();
    }
}

class Application{
    String clipBoard;
    int startSelection, endSelection;
    CommandHistory history;

    Editor editor;

    Application(){
        clipBoard ="";
        startSelection =-1;
        endSelection=-1;
        history = new CommandHistory();
        editor = new Editor();
    }

    public void executeCommand(CommandI command){
        if(command.executeCommand())
            history.push(command);

    }
    public void setStartSelection(int start, int end){
        this.startSelection = start;
        this.endSelection = end;
    }

    public void undo(){
        if(history.isEmpty())
            return;
        history.pop().undo();
    }

}

// working code

public class Command {
    public static void main(String[] args) {
        Application app = new Application();
        System.out.println(app.editor.getText());

        app.clipBoard ="Hello World!";
        app.executeCommand(new PasteCommand(app,app.editor));
        System.out.println(app.editor.getText());

        app.setStartSelection(3,5);
        app.executeCommand(new CutCommand(app,app.editor));
        System.out.println(app.editor.getText());
        System.out.println(app.clipBoard);

        app.undo();
        System.out.println(app.editor.getText());
        System.out.println(app.clipBoard);
    }
}
