package com.fulinlin.ui;

import com.fulinlin.model.ConvertType;
import com.fulinlin.model.OtherSetting;
import com.fulinlin.model.TypeAlias;
import com.fulinlin.service.GoogleTranslateService;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.project.Project;
import lombok.extern.slf4j.Slf4j;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

@Slf4j
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

        try {
            authorName.getDocument().insertString(0, settings.getDateSettings().getOtherSettings().stream()
                                                             .filter(setting -> setting.getKey().equals("author"))
                                                             .findFirst()
                                                             .map(OtherSetting::getValue)
                                                             .orElse(""), null
            );
        } catch (BadLocationException locationException) {
            locationException.printStackTrace();
        }
        for (TypeAlias type : settings.getDateSettings().getTypeAliases()) {
            changeTypes.addItem(type);
        }
        for (ConvertType convertType: settings.getDateSettings().getConvertTypes()) {
            convertTypes.addItem(convertType);
        }
        convertYourInput.addActionListener(e -> {
            ConvertType convertTypeSelectedItem = (ConvertType) convertTypes.getSelectedItem();
            if (convertTypeSelectedItem != null) {
                convertShortDescriptionScope(convertTypeSelectedItem);
            }
        });
    }

    private void convertShortDescriptionScope(ConvertType convertTypeSelectedItem) {
        if (convertTypeSelectedItem.title.equals("normal")) {
            convertShortDescription.setDocument(shortDescription.getDocument());
        }
        if (convertTypeSelectedItem.title.equals("translation")) {
            GoogleTranslateService googleTranslateService = new GoogleTranslateService();
            Document document = shortDescription.getDocument();
            String translatedShortDescription = null;
            try {
                translatedShortDescription = googleTranslateService.translate(document.getText(0, document.getLength()));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
            try {
                convertShortDescription.getDocument().remove(0, convertShortDescription.getDocument().getLength());
                convertShortDescription.getDocument().insertString(0, translatedShortDescription, null);
            } catch (BadLocationException badLocationException) {
                badLocationException.printStackTrace();
            }
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
                convertShortDescription.getText().trim(),
                longDescription.getText().trim()
        );
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
