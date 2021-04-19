package com.fulinlin.setting.ui;

import com.intellij.openapi.ui.DialogWrapper;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class OtherSettingEditor extends DialogWrapper {
    private JPanel myPanel;
    private JTextField keyFiled;
    private JTextField valueField;


    public interface Validator {
        boolean isOK(String name, String value);
    }

    public OtherSettingEditor(String title, String key, String value) {
        super(true);
        setTitle(title);
        keyFiled.setText(key);
        valueField.setText(value);
        init();
    }

    public String getKey() { return keyFiled.getText().trim(); }

    public String getValue() {
        return valueField.getText().trim();
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return myPanel;
    }
}
