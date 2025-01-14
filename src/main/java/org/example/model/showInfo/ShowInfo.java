package org.example.model.showInfo;

import org.example.model.Info;
import org.example.service.InfoService;

import javax.swing.*;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ShowInfo implements IGetInput, ICheckInput, IProcessInput, IShowInputResult {

    public static void showInfo(int option, JTextField txt1, JTextArea textArea, JLabel lblResultados) {
        new ShowInfo().showInputResult(option, txt1, textArea, lblResultados);
    }

    public static void showInfo(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JLabel lblResultados) {
        new ShowInfo().showInputResult(option, txt1, txt2, textArea, lblResultados);
    }

    @Override
    public String getInput(JTextField txt1) {
        return txt1.getText().isEmpty() ? "Sem dados" : txt1.getText();
    }

    @Override
    public List<String> getInput(JTextField txt1, JTextField txt2) {
        return (txt1.getText().isEmpty() || txt2.getText().isEmpty()) ? Arrays.asList("Sem dados", "Sem dados") : Arrays.asList(txt1.getText(), txt2.getText());
    }

    @Override
    public List<Integer> checkInput(List<String> input) {
        if (input.getFirst().matches("^[0-9]+$") && input.getLast().matches("^[0-9]+$")) {
            return Arrays.asList(Integer.parseInt(input.getFirst()), Integer.parseInt(input.getLast()));
        } else {
            return Arrays.asList(-1, -1);
        }
    }

    @Override
    public Info processInput(int option, String search) {
        String data = "";
        int nRows = 0;
        Info info = new Info();
        switch (option) {
            case 1:
                try {
                    data = new InfoService().getInfoMunicipio(search);
                    nRows = new InfoService().getRowsMunicipio(search);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 2:
                try {
                    data = new InfoService().getInfoEstado(search);
                    nRows = new InfoService().getRowsEstado(search);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        if (data.isEmpty() && nRows == 0) {
            info.setInfoData("Sem dados");
            info.setInfoRows(0 + " resultado(s)");
        } else {
            info.setInfoData(data);
            info.setInfoRows(nRows + " resultado(s)");
        }
        return info;
    }

    @Override
    public Info processInput(int option, List<String> search) {
        List<Integer> input = checkInput(search);
        String data = "";
        int nRows = 0;
        Info info = new Info();

        switch (option) {
            case 3:
                try {
                    data = new InfoService().getInfoPopulacao(input.getFirst(), input.getLast());
                    nRows = new InfoService().getRowsPopulacao(input.getFirst(), input.getLast());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
            case 4:
                try {
                    data = new InfoService().getInfoArea(input.getFirst(), input.getLast());
                    nRows = new InfoService().getRowsArea(input.getFirst(), input.getLast());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                break;
        }
        if (data.isEmpty() && nRows == 0) {
            info.setInfoData("Sem dados");
            info.setInfoRows(0 + " resultado(s)");
        } else {
            info.setInfoData(data);
            info.setInfoRows(nRows + " resultado(s)");
        }
        return info;
    }

    @Override
    public void showInputResult(int option, JTextField txt1, JTextArea textArea, JLabel lblResultados) {
        Info info = new Info();
        if (option == 1) {
            info = this.processInput(1, this.getInput(txt1));
        } else if (option == 2) {
            info = this.processInput(2, this.getInput(txt1));
        }
        textArea.setText(info.getInfoData());
        lblResultados.setText(info.getInfoRows());
    }

    @Override
    public void showInputResult(int option, JTextField txt1, JTextField txt2, JTextArea textArea, JLabel lblResultados) {
        Info info = new Info();
        if (option == 3) {
            info = this.processInput(3, this.getInput(txt1, txt2));
        } else if (option == 4) {
            info = this.processInput(4, this.getInput(txt1, txt2));
        }
        textArea.setText(info.getInfoData());
        lblResultados.setText(info.getInfoRows());
    }

}
