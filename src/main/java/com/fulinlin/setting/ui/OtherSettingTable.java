package com.fulinlin.setting.ui;

import com.fulinlin.model.OtherSetting;
import com.fulinlin.storage.GitCommitMessageHelperSettings;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.ui.JBColor;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class OtherSettingTable extends JBTable {
    private static final Logger log = Logger.getInstance(AliasTable.class);
    private static final int NAME_COLUMN = 0;
    private static final int VALUE_COLUMN = 1;
    private final MyTableModel myTableModel = new MyTableModel();

    private List<OtherSetting> otherSettings = new LinkedList<>();


    /**
     * instantiation OtherSettingTable
     */
    public OtherSettingTable() {
        setModel(myTableModel);
        TableColumn column = getColumnModel().getColumn(NAME_COLUMN);
        TableColumn valueColumn = getColumnModel().getColumn(VALUE_COLUMN);
        column.setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                final Component component = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                final String macroValue = getOtherSettingValueAt(row);
                component.setForeground(macroValue.length() == 0
                        ? JBColor.RED
                        : isSelected ? table.getSelectionForeground() : table.getForeground());
                return component;
            }
        });
        setColumnSize(column, 150,250,150);
        setColumnSize(valueColumn, 550,750,550);
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }


    /**
     * Set  Something  ColumnSize
     */
    public static void setColumnSize(TableColumn column, int preferedWidth, int maxWidth, int minWidth) {
        column.setPreferredWidth(preferedWidth);
        column.setMaxWidth(maxWidth);
        column.setMinWidth(minWidth);
    }

    public String getOtherSettingValueAt(int row) {
        return (String) getValueAt(row, VALUE_COLUMN);
    }

    public void commit(GitCommitMessageHelperSettings settings) {
        settings.getDateSettings().setOtherSettings(new LinkedList<>(otherSettings));
    }

    public void addOtherSetting() {}
    public void moveUp(){}
    public void moveDown(){}
    public void removeSelectedOtherSettings(){}

    public void resetDefaultOtherSettings() {
        myTableModel.fireTableDataChanged();
    }

    public void reset(GitCommitMessageHelperSettings settings) {
        obtainOtherSettings(otherSettings, settings);
        myTableModel.fireTableDataChanged();
    }

    private void obtainOtherSettings(@NotNull List<OtherSetting> otherSettings, GitCommitMessageHelperSettings settings) {
        otherSettings.clear();
        otherSettings.addAll(settings.getDateSettings().getOtherSettings());
    }

    public boolean editOtherSetting() {
        if (getSelectedRowCount() != 1) {
            return false;
        }
        final int selectedRow = getSelectedRow();
        final OtherSetting otherSetting = otherSettings.get(selectedRow);
        final OtherSettingEditor editor = new OtherSettingEditor("Edit Setting", otherSetting.getKey(), otherSetting.getValue());
        if (editor.showAndGet()) {
            otherSetting.setKey(editor.getKey());
            otherSetting.setValue(editor.getValue());
            myTableModel.fireTableDataChanged();
        }
        return true;
    }

    public boolean isModified(GitCommitMessageHelperSettings settings) {
        final List<OtherSetting> otherSettingsModified = new LinkedList<>();
        obtainOtherSettings(otherSettingsModified, settings);
        return !otherSettingsModified.equals(otherSettings);
    }

    private boolean isValidRow(int selectedRow) {
        return selectedRow >= 0 && selectedRow < otherSettings.size();
    }

    private static class EditValidator implements OtherSettingEditor.Validator {
        @Override
        public boolean isOK(String name, String value) {
            return !name.isEmpty() && !value.isEmpty();
        }
    }

    /**
     * MyTableModel
     */
    private class MyTableModel extends AbstractTableModel {
        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public int getRowCount() {
            return otherSettings.size();
        }

        @Override
        public Class getColumnClass(int columnIndex) {
            return String.class;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            final OtherSetting pair = otherSettings.get(rowIndex);
            switch (columnIndex) {
                case NAME_COLUMN:
                    return pair.getKey();
                case VALUE_COLUMN:
                    return pair.getValue();
            }
            log.error("Wrong indices");
            return null;
        }

        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        }

        @Override
        public String getColumnName(int columnIndex) {
            switch (columnIndex) {
                case NAME_COLUMN:
                    return "key";
                case VALUE_COLUMN:
                    return "value";
            }
            return null;
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }
    }

}
