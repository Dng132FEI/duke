package Main;

import Storage.Storage;
import Storage.TaskList;
import User.Ui;
import User.Parser;
import Excption.BlankInputException;
import Excption.InvalidInputException;
import Excption.OutOfRangeException;
import Excption.UnknownInputException;

import java.io.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser psr;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load(),ui,storage);
            psr = new Parser(tasks,storage);
        } catch (IOException ioe) {
        	ui.showError(ioe, ". Error during loading.");
        }
    }

	public void run() {
	    ui.showWelcome();
	    while (true) {
	        try {
	            String fullCommand = ui.readCommand();
	            if (fullCommand.equals("bye")) {
	            	ui.bye();
	            	break;
	            }
	            psr.parseInput(fullCommand);
	        } catch (BlankInputException e) {
	            ui.showError(e,"");
	        } catch (UnknownInputException e) {
	            ui.showError(e,"");
	        } catch (InvalidInputException e) {
	        	ui.showError(e,"");
	        } catch (OutOfRangeException e) {
	        	ui.showError(e,"");
	        }
	    }
	}

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
} 	