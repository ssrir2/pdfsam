/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 27/nov/2013
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
package org.pdfsam.ui.selection;

import static org.apache.commons.lang3.StringUtils.EMPTY;
import static org.apache.commons.lang3.StringUtils.defaultString;

import java.util.Comparator;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import org.pdfsam.context.DefaultI18nContext;

/**
 * Definition of the {@link String} columns of the selection table
 * 
 * @author Andrea Vacondio
 * 
 */
enum StringColumn implements SelectionTableColumn<String> {
    PAGE_SELECTION {
        public String getColumnTitle() {
            return DefaultI18nContext.getInstance().i18n("Page ranges");
        }

        @Override
        public ObservableValue<String> getObservableValue(SelectionTableRowData data) {
            return new SimpleStringProperty(data.getPageSelection());
        }

        @Override
        public String getTextValue(String item) {
            return defaultString(item, EMPTY);
        }

        public Comparator<String> comparator() {
            return new Comparator<String>() {
                public int compare(String o1, String o2) {
                    return o1.compareTo(o2);
                }
            };
        }
    }
}