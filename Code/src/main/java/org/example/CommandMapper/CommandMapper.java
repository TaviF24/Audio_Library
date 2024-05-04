package org.example.CommandMapper;

import org.example.Commands.AbstractCommand;
import org.example.Commands.Command;

import java.util.Optional;

public interface CommandMapper {

    /*
    quit
    login <numeUtilizator> <parolă>
    register <numeUtilizator> <parolă>
    logout
    promote <numeUtilizator>
    create song "<numeMelodie>" "<numeAutor>" <anLansare>
    create playlist <numePlaylist>
    list playlists
    add byName "<playlistName>" <identificatorUnicMelodie>[ <identificatorUnicMelodie2> <identificatorUnicMelodie3> ...]
    add byId "<identificatorUnicPlaylist>" <identificatorUnicMelodie>[ <identificatorUnicMelodie2> <identificatorUnicMelodie3> ...]
    search <tipCriteriu> "<criteriuCăutare>"
    export playlist <numePlaylist> <format>
    audit <numeUtilizator>

    */


    Optional<AbstractCommand> tryMapCommand(String command, String[] args);

}
