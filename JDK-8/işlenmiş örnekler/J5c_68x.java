// J5c_68x.java: TableSorter (TabloS�ralay�c�) alt-�rne�i.

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.*;

import java.util.*;
import java.util.List;

/* TableSorter verili Tablo modelini artan/azalan s�ralarken, orijinal
 * sat�r endeksini bir map/haritada muhafaza edip tekrar orijinal
 * tabloya d�n�� yapabilmektedir.
 * Fare dinleyicisi kolon ba�l�klar�na duyarlanm�� olup:
 * T�klan�nca artan/azalan/s�ras�z,
 * SHIFT-t�klan�nca azalan/artan/s�ras�z,
 * Ctrl-t�klan�nca ve Ctrl-SHIFT-t�klan�nca yukardakinin ayn�s�n� yapar.
 */
public class J5c_68x extends AbstractTableModel {
    protected TableModel tabloModeli;

    public static final int AZALAN = -1;
    public static final int SIRASIZ = 0;
    public static final int ARTAN = 1;

    private static Talimat BO�_TAL�MAT = new Talimat (-1, SIRASIZ);

    public static final Comparator KAR�ILA�TIRILAB�L�R_KAR�ILA�TIRICI = new Comparator() {
        public int compare (Object nesne1, Object nesne2) {
            return ((Comparable) nesne1).compareTo (nesne2);
        } // compare(..) haz�r metodu sonu...
    }; // pub.. ifadesi sonu...

    public static final Comparator S�ZL�KSEL_KAR�ILA�TIRICI = new Comparator() {
        public int compare (Object nesne1, Object nesne2) {
            return nesne1.toString().compareTo (nesne2.toString());
        } // compare(..) haz�r metodu sonu...
    }; // pub.. ifadesi sonu...

    private Row[] g�r�nt�denModele;
    private int[] modeldenG�r�nt�ye;

    private JTableHeader tabloBa�l���;
    private MouseListener fareDinleyicisi;
    private TableModelListener tabloModeliDinleyicisi;
    private Map kolonKar��la�t�r�c�lar = new HashMap();
    private List kolonlar�nS�ralanmas� = new ArrayList();

    public J5c_68x() {
        this.fareDinleyicisi = new MouseHandler();
        this.tabloModeliDinleyicisi = new TableModelHandler();
    } // J5c_68x() kurucu-1 sonu...

    public J5c_68x (TableModel tabloModeli) {
        this();
        setTableModel (tabloModeli);
    } // J5c_68x(..) kurucu-2 sonu...

    public J5c_68x (TableModel tabloModeli, JTableHeader tabloBa�l���) {
        this();
        setTableHeader (tabloBa�l���);
        setTableModel (tabloModeli);
    } // J5c_68x(..) kurucu-3 sonu...

    private void clearSortingState() {
        g�r�nt�denModele = null;
        modeldenG�r�nt�ye = null;
    }

    public TableModel getTableModel() {
        return tabloModeli;
    }

    public void setTableModel(TableModel tabloModeli) {
        if (this.tabloModeli != null) {
            this.tabloModeli.removeTableModelListener(tabloModeliDinleyicisi);
        }

        this.tabloModeli = tabloModeli;
        if (this.tabloModeli != null) {
            this.tabloModeli.addTableModelListener(tabloModeliDinleyicisi);
        }

        clearSortingState();
        fireTableStructureChanged();
    }

    public JTableHeader getTableHeader() {
        return tabloBa�l���;
    }

    public void setTableHeader(JTableHeader tabloBa�l���) {
        if (this.tabloBa�l��� != null) {
            this.tabloBa�l���.removeMouseListener(fareDinleyicisi);
            TableCellRenderer defaultRenderer = this.tabloBa�l���.getDefaultRenderer();
            if (defaultRenderer instanceof SortableHeaderRenderer) {
                this.tabloBa�l���.setDefaultRenderer(((SortableHeaderRenderer) defaultRenderer).tableCellRenderer);
            }
        }
        this.tabloBa�l��� = tabloBa�l���;
        if (this.tabloBa�l��� != null) {
            this.tabloBa�l���.addMouseListener(fareDinleyicisi);
            this.tabloBa�l���.setDefaultRenderer(
                    new SortableHeaderRenderer(this.tabloBa�l���.getDefaultRenderer()));
        }
    }

    public boolean isSorting() {
        return kolonlar�nS�ralanmas�.size() != 0;
    }

    private Talimat getDirective(int kolon) {
        for (int i = 0; i < kolonlar�nS�ralanmas�.size(); i++) {
            Talimat directive = (Talimat)kolonlar�nS�ralanmas�.get(i);
            if (directive.kolon == kolon) {
                return directive;
            }
        }
        return BO�_TAL�MAT;
    }

    public int getSortingStatus(int kolon) {
        return getDirective(kolon).y�n;
    }

    private void sortingStatusChanged() {
        clearSortingState();
        fireTableDataChanged();
        if (tabloBa�l��� != null) {
            tabloBa�l���.repaint();
        }
    }

    public void setSortingStatus(int kolon, int status) {
        Talimat directive = getDirective(kolon);
        if (directive != BO�_TAL�MAT) {
            kolonlar�nS�ralanmas�.remove(directive);
        }
        if (status != SIRASIZ) {
            kolonlar�nS�ralanmas�.add(new Talimat(kolon, status));
        }
        sortingStatusChanged();
    }

    protected Icon getHeaderRendererIcon(int kolon, int size) {
        Talimat directive = getDirective(kolon);
        if (directive == BO�_TAL�MAT) {
            return null;
        }
        return new Arrow(directive.y�n == AZALAN, size, kolonlar�nS�ralanmas�.indexOf(directive));
    }

    private void cancelSorting() {
        kolonlar�nS�ralanmas�.clear();
        sortingStatusChanged();
    }

    public void setColumnComparator(Class type, Comparator comparator) {
        if (comparator == null) {
            kolonKar��la�t�r�c�lar.remove(type);
        } else {
            kolonKar��la�t�r�c�lar.put(type, comparator);
        }
    }

    protected Comparator getComparator(int kolon) {
        Class columnType = tabloModeli.getColumnClass(kolon);
        Comparator comparator = (Comparator) kolonKar��la�t�r�c�lar.get(columnType);
        if (comparator != null) {
            return comparator;
        }
        if (Comparable.class.isAssignableFrom(columnType)) {
            return KAR�ILA�TIRILAB�L�R_KAR�ILA�TIRICI;
        }
        return S�ZL�KSEL_KAR�ILA�TIRICI;
    }

    private Row[] getViewToModel() {
        if (g�r�nt�denModele == null) {
            int tabloModeliRowCount = tabloModeli.getRowCount();
            g�r�nt�denModele = new Row[tabloModeliRowCount];
            for (int row = 0; row < tabloModeliRowCount; row++) {
                g�r�nt�denModele[row] = new Row(row);
            }

            if (isSorting()) {
                Arrays.sort(g�r�nt�denModele);
            }
        }
        return g�r�nt�denModele;
    }

    public int modelIndex(int viewIndex) {
        return getViewToModel()[viewIndex].modelIndex;
    }

    private int[] getModelToView() {
        if (modeldenG�r�nt�ye == null) {
            int n = getViewToModel().length;
            modeldenG�r�nt�ye = new int[n];
            for (int i = 0; i < n; i++) {
                modeldenG�r�nt�ye[modelIndex(i)] = i;
            }
        }
        return modeldenG�r�nt�ye;
    }

    // TableModel interface methods 

    public int getRowCount() {
        return (tabloModeli == null) ? 0 : tabloModeli.getRowCount();
    }

    public int getColumnCount() {
        return (tabloModeli == null) ? 0 : tabloModeli.getColumnCount();
    }

    public String getColumnName(int kolon) {
        return tabloModeli.getColumnName(kolon);
    }

    public Class getColumnClass(int kolon) {
        return tabloModeli.getColumnClass(kolon);
    }

    public boolean isCellEditable(int row, int kolon) {
        return tabloModeli.isCellEditable(modelIndex(row), kolon);
    }

    public Object getValueAt(int row, int kolon) {
        return tabloModeli.getValueAt(modelIndex(row), kolon);
    }

    public void setValueAt(Object aValue, int row, int kolon) {
        tabloModeli.setValueAt(aValue, modelIndex(row), kolon);
    }

    // Helper classes
    
    private class Row implements Comparable {
        private int modelIndex;

        public Row(int index) {
            this.modelIndex = index;
        }

        public int compareTo(Object o) {
            int row1 = modelIndex;
            int row2 = ((Row) o).modelIndex;

            for (Iterator it = kolonlar�nS�ralanmas�.iterator(); it.hasNext();) {
                Talimat directive = (Talimat) it.next();
                int kolon = directive.kolon;
                Object o1 = tabloModeli.getValueAt(row1, kolon);
                Object o2 = tabloModeli.getValueAt(row2, kolon);

                int comparison = 0;
                // Define null less than everything, except null.
                if (o1 == null && o2 == null) {
                    comparison = 0;
                } else if (o1 == null) {
                    comparison = -1;
                } else if (o2 == null) {
                    comparison = 1;
                } else {
                    comparison = getComparator(kolon).compare(o1, o2);
                }
                if (comparison != 0) {
                    return directive.y�n == AZALAN ? -comparison : comparison;
                }
            }
            return 0;
        }
    }

    private class TableModelHandler implements TableModelListener {
        public void tableChanged(TableModelEvent e) {
            // If we're not sorting by anything, just pass the event along.             
            if (!isSorting()) {
                clearSortingState();
                fireTableChanged(e);
                return;
            }
                
            // If the table structure has changed, cancel the sorting; the             
            // sorting columns may have been either moved or deleted from             
            // the model. 
            if (e.getFirstRow() == TableModelEvent.HEADER_ROW) {
                cancelSorting();
                fireTableChanged(e);
                return;
            }

            // We can map a cell event through to the view without widening             
            // when the following conditions apply: 
            // 
            // a) all the changes are on one row (e.getFirstRow() == e.getLastRow()) and, 
            // b) all the changes are in one column (kolon != TableModelEvent.ALL_COLUMNS) and,
            // c) we are not sorting on that column (getSortingStatus(kolon) == SIRASIZ) and, 
            // d) a reverse lookup will not trigger a sort (modeldenG�r�nt�ye != null)
            //
            // Note: INSERT and DELETE events fail this test as they have column == ALL_COLUMNS.
            // 
            // The last check, for (modeldenG�r�nt�ye != null) is to see if modeldenG�r�nt�ye 
            // is already allocated. If we don't do this check; sorting can become 
            // a performance bottleneck for applications where cells  
            // change rapidly in different parts of the table. If cells 
            // change alternately in the sorting column and then outside of             
            // it this class can end up re-sorting on alternate cell updates - 
            // which can be a performance problem for large tables. The last 
            // clause avoids this problem. 
            int kolon = e.getColumn();
            if (e.getFirstRow() == e.getLastRow()
                    && kolon != TableModelEvent.ALL_COLUMNS
                    && getSortingStatus(kolon) == SIRASIZ
                    && modeldenG�r�nt�ye != null) {
                int viewIndex = getModelToView()[e.getFirstRow()];
                fireTableChanged(new TableModelEvent(J5c_68x.this, 
                                                     viewIndex, viewIndex, 
                                                     kolon, e.getType()));
                return;
            }

            // Something has happened to the data that may have invalidated the row order. 
            clearSortingState();
            fireTableDataChanged();
            return;
        }
    }

    private class MouseHandler extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            JTableHeader h = (JTableHeader) e.getSource();
            TableColumnModel columnModel = h.getColumnModel();
            int viewColumn = h.columnAtPoint(e.getPoint());
            int kolon = columnModel.getColumn(viewColumn).getModelIndex();
            if (kolon != -1) {
                int status = getSortingStatus(kolon);
                if (!e.isControlDown()) {
                    cancelSorting();
                }
                // Cycle the sorting states through {SIRASIZ, ARTAN, AZALAN} or 
                // {SIRASIZ, AZALAN, ARTAN} depending on whether shift is pressed. 
                status = status + (e.isShiftDown() ? -1 : 1);
                status = (status + 4) % 3 - 1; // signed mod, returning {-1, 0, 1}
                setSortingStatus(kolon, status);
            }
        }
    }

    private static class Arrow implements Icon {
        private boolean descending;
        private int size;
        private int priority;

        public Arrow(boolean descending, int size, int priority) {
            this.descending = descending;
            this.size = size;
            this.priority = priority;
        }

        public void paintIcon(Component c, Graphics g, int x, int y) {
            Color color = c == null ? Color.GRAY : c.getBackground();             
            // In a compound sort, make each succesive triangle 20% 
            // smaller than the previous one. 
            int dx = (int)(size/2*Math.pow(0.8, priority));
            int dy = descending ? dx : -dx;
            // Align icon (roughly) with font baseline. 
            y = y + 5*size/6 + (descending ? -dy : 0);
            int shift = descending ? 1 : -1;
            g.translate(x, y);

            // Right diagonal. 
            g.setColor(color.darker());
            g.drawLine(dx / 2, dy, 0, 0);
            g.drawLine(dx / 2, dy + shift, 0, shift);
            
            // Left diagonal. 
            g.setColor(color.brighter());
            g.drawLine(dx / 2, dy, dx, 0);
            g.drawLine(dx / 2, dy + shift, dx, shift);
            
            // Horizontal line. 
            if (descending) {
                g.setColor(color.darker().darker());
            } else {
                g.setColor(color.brighter().brighter());
            }
            g.drawLine(dx, 0, 0, 0);

            g.setColor(color);
            g.translate(-x, -y);
        }

        public int getIconWidth() {
            return size;
        }

        public int getIconHeight() {
            return size;
        }
    }

    private class SortableHeaderRenderer implements TableCellRenderer {
        private TableCellRenderer tableCellRenderer;

        public SortableHeaderRenderer(TableCellRenderer tableCellRenderer) {
            this.tableCellRenderer = tableCellRenderer;
        }

        public Component getTableCellRendererComponent(JTable table, 
                                                       Object value,
                                                       boolean isSelected, 
                                                       boolean hasFocus,
                                                       int row, 
                                                       int kolon) {
            Component c = tableCellRenderer.getTableCellRendererComponent(table, 
                    value, isSelected, hasFocus, row, kolon);
            if (c instanceof JLabel) {
                JLabel l = (JLabel) c;
                l.setHorizontalTextPosition(JLabel.LEFT);
                int modelColumn = table.convertColumnIndexToModel(kolon);
                l.setIcon(getHeaderRendererIcon(modelColumn, l.getFont().getSize()));
            }
            return c;
        }
    }

    private static class Talimat {
        private int kolon;
        private int y�n;

        public Talimat (int kolon, int y�n) {
            this.kolon = kolon;
            this.y�n = y�n;
        } // Talimat(..) kurucusu sonu...
    } // Talimat s�n�f� sonu...
} // J5c_68x s�n�f� sonu...