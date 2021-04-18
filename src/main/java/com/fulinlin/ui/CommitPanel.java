package com.fulinlin.ui;

import com.fulinlin.model.TypeAlias;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.util.List;


public class CommitPanel {
    private JPanel mainPanel;
    private JTextField authorName;
    private JTextField cardNumber;
    private JComboBox changeType;
    private JTextField shortDescription;
    private JTextArea longDescription;
    private JLabel authorNameLabel;
    private JLabel cardNumberLabel;
    private JLabel longDescriptionLabel;
    private JLabel shortDescriptionLabel;
    private JLabel typeOfChangeLabel;

    public CommitPanel(Project project, GitCommitMessageHelperSettings settings) {
        //parameter
        List<TypeAlias> typeAliases = settings.getDateSettings().getTypeAliases();
        for (TypeAlias type : typeAliases) {
            changeType.addItem(type);
        }
       /* fix fulin  File workingDirectory = VfsUtil.virtualToIoFile(project.getBaseDir());
        Command.Result result = new Command(workingDirectory, "git log --all --format=%s | grep -Eo '^[a-z]+(\\(.*\\)):.*$' | sed 's/^.*(\\(.*\\)):.*$/\\1/' | sort -n | uniq").execute();
        if (result.isSuccess()) {
            result.getOutput().forEach(changeScope::addItem);
        }*/
    }

    JPanel getMainPanel() {
        return mainPanel;
    }

    CommitMessage getCommitMessage(GitCommitMessageHelperSettings settings) {
        return new CommitMessage(
                settings,
                authorName.getText().trim(),
                cardNumber.getText().trim(),
                (TypeAlias) changeType.getSelectedItem(),
                shortDescription.getText().trim(),
                longDescription.getText().trim()
        );
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
