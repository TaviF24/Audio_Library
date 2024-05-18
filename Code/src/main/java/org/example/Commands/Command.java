package org.example.Commands;

import org.example.Exceptions.Checked.AudioLibraryCheckedException;
import org.example.Exceptions.Checked.UnknownFormatException;

public interface Command {
    boolean execute() throws AudioLibraryCheckedException;
}
