package UI.Renderers;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * This class is used to change the font of the texts inside a JTable
 **/

public class FontRenderer extends DefaultTableCellRenderer {
    private Font font;

    public FontRenderer(Font font) {
        this.font = font;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        Component rendererComponent = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row,
                column);
        rendererComponent.setFont(font);
        return rendererComponent;
    }
}