/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 15/giu/2013
 * Copyright 2013 by Andrea Vacondio (andrea.vacondio@gmail.com).
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.pdfsam.gui.view.selection;

import javax.swing.table.TableCellRenderer;

import org.apache.commons.lang3.ObjectUtils;
import org.pdfsam.context.DefaultI18nContext;

/**
 * Definition of the {@link Integer} columns of the selection table
 * 
 * @author Andrea Vacondio
 * 
 */
public enum IntegerColumn implements SelectionTableColumn<Integer> {
    PAGES {
        public String getColumnName() {
            return DefaultI18nContext.getInstance().i18n("Pages");
        }

        public Integer getValueFor(SelectionTableRowData rowData) {
            int pages = rowData.getDocumentDescriptor().getPages();
            if (pages > 0) {
                return pages;
            }
            return 0;
        }

    };

    public TableCellRenderer getRenderer() {
        return new BaseSelectionTableCellRenderer() {

            @Override
            String getStringValue(Object value) {
                return ObjectUtils.toString(value);
            }
        };
    }

    public Class<Integer> getColumnClass() {
        return Integer.class;
    }

    public int compare(SelectionTableRowData o1, SelectionTableRowData o2) {
        return getValueFor(o1).compareTo(getValueFor(o2));
    }
}
