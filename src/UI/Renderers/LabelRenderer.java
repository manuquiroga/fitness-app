package UI.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class LabelRenderer extends DefaultTableCellRenderer {
    private JLabel label;

    public LabelRenderer(JLabel label) {
        this.label = label;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        label.setText((String) value);
        label.setFont(table.getFont());
        label.setBackground(isSelected ? table.getSelectionBackground() : table.getBackground());
        label.setForeground(isSelected ? table.getSelectionForeground() : table.getForeground());
        return label;
    }
}
