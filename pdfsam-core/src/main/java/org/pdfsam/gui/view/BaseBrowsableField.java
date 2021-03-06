/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 05/feb/2013
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
package org.pdfsam.gui.view;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.lang3.StringUtils;
import org.pdfsam.support.validation.Validator;

import static javax.swing.GroupLayout.Alignment.TRAILING;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

import static org.pdfsam.gui.view.Views.GAP;

/**
 * Abstract implementation of a field where the value can be browsed with a {@link JFileChooser}. Implementors have to specify a {@link JFileChooser}
 * 
 * @author Andrea Vacondio
 * 
 */
public abstract class BaseBrowsableField extends JPanel {
    private MyValidableTextField field = new MyValidableTextField();
    private JLabel label;

    /**
     * Creates a browsable field without label
     */
    protected BaseBrowsableField() {
        this(StringUtils.EMPTY);
    }

    /**
     * Creates a browsable field with label
     */
    protected BaseBrowsableField(String labelText) {
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);

        if (StringUtils.isNotBlank(labelText)) {
            label = new JLabel(labelText);
        }

        JButton browse = new JButton(new BrowseAction());

        ParallelGroup pGroup = layout.createParallelGroup();
        if (label != null) {
            pGroup = pGroup.addGroup(layout.createSequentialGroup().addComponent(label)).addGap(GAP);
        }
        layout.setHorizontalGroup(pGroup.addGroup(layout.createSequentialGroup().addComponent(field).addGap(GAP)
                .addComponent(browse)));

        SequentialGroup sGroup = layout.createSequentialGroup();
        if (label != null) {
            sGroup = sGroup.addGroup(layout.createSequentialGroup().addComponent(label)).addGap(2);
        }
        layout.setVerticalGroup(sGroup.addGroup(layout.createParallelGroup(TRAILING).addComponent(field)
                .addComponent(browse)));
    }

    public void setTooltip(String tooltip) {
        this.setToolTipText(tooltip);
        field.setToolTipText(tooltip);
    }

    public void setDefaultFieldValue(String defaultValue) {
        field.setText(defaultValue);
    }

    public String getFieldValue() {
        return field.getText();
    }

    public void setValidator(Validator<String> validator) {
        field.setValidator(validator);
    }

    /**
     * @return a {@link JFileChooser} initialized for the component.
     */
    protected abstract JFileChooser getChooser();

    /**
     * If a {@link Validator} is set, this callback is executed when a file is selected and the input is valid
     * 
     * @param selected
     */
    protected abstract void onValidInput();

    /**
     * Validable text field calling the callback defined in the subclass
     * 
     * @author Andrea Vacondio
     * 
     */
    private class MyValidableTextField extends AbstractValidableTextField {

        @Override
        protected void onValidInput() {
            BaseBrowsableField.this.onValidInput();
        }

    }

    /**
     * Browse action
     * 
     * @author Andrea Vacondio
     * 
     */
    private class BrowseAction extends AbstractAction {

        BrowseAction() {
            super("Browse");
        }

        public void actionPerformed(ActionEvent e) {
            JFileChooser chooser = getChooser();
            if (isNotBlank(field.getText())) {
                chooser.setCurrentDirectory(new File(field.getText()));
            }
            int retVal = chooser.showOpenDialog(BaseBrowsableField.this);

            if (retVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                BaseBrowsableField.this.field.setText(selectedFile.getAbsolutePath());
                BaseBrowsableField.this.field.requestFocus();
            }
        }
    }
}
