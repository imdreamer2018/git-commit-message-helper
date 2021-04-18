package com.fulinlin.ui;

import com.fulinlin.model.ConvertType;
import com.fulinlin.model.TypeAlias;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;


public class CommitPanel {
    private JPanel mainPanel;
    private JTextField authorName;
    private JTextField cardNumber;
    private JComboBox<TypeAlias> changeTypes;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JComboBox<ConvertType> convertTypes;
    private JButton convertYourInput;
    private JTextField yourShortDescription;
    private JLabel authorNameLabel;
    private JLabel cardNumberLabel;
    private JLabel longDescriptionLabel;
    private JLabel shortDescriptionLabel;
    private JLabel typeOfChangeLabel;
    private JLabel yourShortDescriptionLabel;
    private JLabel typeOfConvertLabel;

    public CommitPanel(Project project, GitCommitMessageHelperSettings settings) {
        for (TypeAlias type : settings.getDateSettings().getTypeAliases()) {
            changeTypes.addItem(type);
        }
        for (ConvertType convertType: settings.getDateSettings().getConvertTypes()) {
            convertTypes.addItem(convertType);
        }
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage(GitCommitMessageHelperSettings settings) {
        return new CommitMessage(
                settings,
                authorName.getText().trim(),
                cardNumber.getText().trim(),
                (TypeAlias) changeTypes.getSelectedItem(),
                shortDescription.getText().trim(),
                longDescription.getText().trim()
        );
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
