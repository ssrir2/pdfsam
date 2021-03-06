/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 13/giu/2012
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
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

import org.pdfsam.context.BooleanUserPreference;
import org.pdfsam.context.DefaultUserContext;

/**
 * Checkbox updating the relative preference on item selection/deselection
 * 
 * @author Andrea Vacondio
 * 
 */
class BooleanPreferenceCheckBox extends JCheckBox {

    private final BooleanUserPreference preference;

    BooleanPreferenceCheckBox(BooleanUserPreference pref, String label, boolean selected) {
        super(label);
        setSelected(selected);
        setBackground(Color.WHITE);
        this.preference = pref;
        this.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    DefaultUserContext.getInstance().setBooleanPreference(preference, true);
                } else if (e.getStateChange() == ItemEvent.DESELECTED) {
                    DefaultUserContext.getInstance().setBooleanPreference(preference, false);
                }

            }
        });
    }

}
