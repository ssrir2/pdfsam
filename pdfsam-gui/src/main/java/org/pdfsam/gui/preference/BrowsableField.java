/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 14/giu/2012
 * Copyright 2012 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.pdfsam.gui.preference;

import java.awt.Color;

import org.pdfsam.context.DefaultUserContext;
import org.pdfsam.context.StringUserPreference;
import org.pdfsam.gui.view.BaseBrowsableField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Preference field where the value of the field can be browsed.
 * 
 * @author Andrea Vacondio
 * 
 */
abstract class BrowsableField extends BaseBrowsableField {
    private static final Logger LOG = LoggerFactory.getLogger(BrowsableField.class);
    private StringUserPreference preference;

    BrowsableField(String labelText, StringUserPreference preference) {
        super(labelText);
        setBackground(Color.WHITE);
        this.preference = preference;
    }

    @Override
    protected void onValidInput() {
        DefaultUserContext.getInstance().setStringPreference(BrowsableField.this.preference,
                BrowsableField.this.getFieldValue());
        LOG.trace("Preference set to {}", BrowsableField.this.getFieldValue());
    }
}
