package org.example.model.showInfo;

import javax.swing.*;
import java.util.List;

public interface IGetInput {

    String getInput(JTextField txt1);

    List<String> getInput(JTextField txt1, JTextField txt2);

}
