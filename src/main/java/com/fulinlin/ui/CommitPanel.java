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
    private JTextField convertShortDescription;
    private JTextArea longDescription;
    private JComboBox<ConvertType> convertTypes;
    private JButton convertYourInput;
    private JTextField shortDescription;
    private JLabel authorNameLabel;
    private JLabel cardNumberLabel;
    private JLabel longDescriptionLabel;
    private JLabel convertShortDescriptionLabel;
    private JLabel typeOfChangeLabel;
    private JLabel shortDescriptionLabel;
    private JLabel typeOfConvertLabel;

    public CommitPanel(Project project, GitCommitMessageHelperSettings settings) {
        for (TypeAlias type : settings.getDateSettings().getTypeAliases()) {
            changeTypes.addItem(type);
        }
        for (ConvertType convertType: settings.getDateSettings().getConvertTypes()) {
            convertTypes.addItem(convertType);
        }
        convertYourInput.addActionListener(e -> {
            ConvertType convertTypeSelectedItem = (ConvertType) convertTypes.getSelectedItem();
            if (convertTypeSelectedItem.title.equals("normal")) {
                convertShortDescription.setDocument(shortDescription.getDocument());
            }
        });
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
                convertShortDescription.getText().trim(),
                longDescription.getText().trim()
        );
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
