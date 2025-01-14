package org.example.model.showInfo;

import org.example.model.Info;

import java.util.List;

public interface IProcessInput {

    Info processInput(int option, String search);

    Info processInput(int option, List<String> search);

}
