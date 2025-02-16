package com.fulinlin.setting.ui;

import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.EditorSettings;
import com.intellij.openapi.fileTypes.FileTypeManager;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.ui.AnActionButton;
import com.intellij.ui.DoubleClickListener;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBScrollPane;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Optional;


public class TemplateEditPane {
    private JPanel mainPanel;
    private JTabbedPane tabbedPane;
    private JPanel templateEditPanel;
    private JPanel typeEditPanel;
    private JPanel otherSettingPanel;

    //my  attribute
    protected GitCommitMessageHelperSettings settings;
    private final AliasTable aliasTable;
    private final Editor templateEditor;
    private final OtherSettingTable otherSettingTable;


    public TemplateEditPane(GitCommitMessageHelperSettings settings) {
        //get setting
        this.settings = settings.clone();
        aliasTable = new AliasTable();
        otherSettingTable = new OtherSettingTable();
        String template = Optional.of(settings.getDateSettings().getTemplate()).orElse("");
        //init  templateEditor
        templateEditor = EditorFactory.getInstance().createEditor(
                EditorFactory.getInstance().createDocument(""),
                null,
                FileTypeManager.getInstance().getFileTypeByExtension("vm"),
                false);
        EditorSettings templateEditorSettings = templateEditor.getSettings();
        templateEditorSettings.setAdditionalLinesCount(0);
        templateEditorSettings.setAdditionalColumnsCount(0);
        templateEditorSettings.setLineMarkerAreaShown(false);
        templateEditorSettings.setVirtualSpace(false);
        JBScrollPane jbScrollPane = new JBScrollPane(templateEditor.getComponent());
        jbScrollPane.setMaximumSize(new Dimension(150, 50));
        templateEditPanel.add(jbScrollPane);
        ApplicationManager.getApplication().runWriteAction(() -> templateEditor.getDocument().setText(template));

        //init   typeEditPenel
        typeEditPanel.add(
                ToolbarDecorator.createDecorator(aliasTable)
                        .setAddAction(button -> aliasTable.addAlias())
                        .setRemoveAction(button -> aliasTable.removeSelectedAliases())
                        .setEditAction(button -> aliasTable.editAlias())
                        .setMoveUpAction(anActionButton -> aliasTable.moveUp())
                        .setMoveDownAction(anActionButton -> aliasTable.moveDown())
                        .addExtraAction
                                (new AnActionButton("Reset Default Aliases", AllIcons.Actions.Rollback) {
                                    @Override
                                    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
                                        aliasTable.resetDefaultAliases();
                                    }
                                }).createPanel(), BorderLayout.CENTER);
        new DoubleClickListener() {
            @Override
            protected boolean onDoubleClick(MouseEvent e) {
                return aliasTable.editAlias();
            }
        }.installOn(aliasTable);

        //init otherSettingPanel
        otherSettingPanel.add(
                ToolbarDecorator.createDecorator(otherSettingTable)
                            .setAddAction(button -> otherSettingTable.addOtherSetting())
                            .setRemoveAction(button -> otherSettingTable.removeSelectedOtherSettings())
                            .setEditAction(button -> otherSettingTable.editOtherSetting())
                            .setMoveUpAction(button -> otherSettingTable.moveUp())
                            .setMoveDownAction(button -> otherSettingTable.moveDown())
                            .addExtraAction
                                    (new AnActionButton("Reset Default Other Setting", AllIcons.Actions.Rollback) {
                                        @Override
                                        public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
                                            otherSettingTable.resetDefaultOtherSettings();
                                        }
                                    }).createPanel(), BorderLayout.CENTER);
        new DoubleClickListener() {
            @Override
            protected boolean onDoubleClick(MouseEvent e) {
                return otherSettingTable.editOtherSetting();
            }
        }.installOn(otherSettingTable);
    }


    public GitCommitMessageHelperSettings getSettings() {
        aliasTable.commit(settings);
        settings.getDateSettings().setTemplate(templateEditor.getDocument().getText());
        otherSettingTable.commit(settings);
        return settings;
    }

    public void reset(GitCommitMessageHelperSettings settings ) {
        this.settings = settings.clone();
        aliasTable.reset(settings);
        ApplicationManager.getApplication().runWriteAction(() -> templateEditor.getDocument().setText(settings.getDateSettings().getTemplate()));
        otherSettingTable.reset(settings);
    }


    public boolean isSettingsModified(GitCommitMessageHelperSettings settings) {
        if (aliasTable.isModified(settings)) return true;
        return isModified(settings);
    }

    public boolean isModified(GitCommitMessageHelperSettings data) {
        if (!StringUtil.equals(settings.getDateSettings().getTemplate(), templateEditor.getDocument().getText())) {
            return true;
        }
        if (settings.getDateSettings().getTypeAliases() == data.getDateSettings().getTypeAliases()) {
            return true;
        }
        return settings.getDateSettings().getOtherSettings() == data.getDateSettings().getOtherSettings();
    }


    public JPanel getMainPanel() {
        return mainPanel;
    }


}
