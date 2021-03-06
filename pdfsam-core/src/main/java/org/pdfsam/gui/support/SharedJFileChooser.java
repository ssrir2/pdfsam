/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 15/dic/2011
 * Copyright 2011 by Andrea Vacondio (andrea.vacondio@gmail.com).
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
package org.pdfsam.gui.support;

import java.io.File;

import javax.swing.JFileChooser;

import org.pdfsam.context.DefaultUserContext;
import org.pdfsam.support.filter.BaseFileChooserFilter;
import org.pdfsam.support.filter.FileFilterType;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

/**
 * Shared {@link JFileChooser} instance.
 * 
 * @author Andrea Vacondio
 * 
 */
public final class SharedJFileChooser {

    private static final JFileChooser INSTANCE = new JFileChooser(DefaultUserContext.getInstance()
            .getDefaultWorkingPath());

    private SharedJFileChooser() {
        // hide
    }

    /**
     * @param type
     * @param mode
     *            {@link JFileChooser#setFileSelectionMode(int)}
     * @param currentDirectory
     *            directory where the {@link JFileChooser} will be opened. It can be null or empty.
     * @return the shared JFileChooser instance.
     */
    public static JFileChooser getInstance(FileFilterType type, int mode, String currentDirectory) {
        INSTANCE.resetChoosableFileFilters();
        INSTANCE.setMultiSelectionEnabled(false);
        INSTANCE.setSelectedFile(new File(""));
        if (isNotBlank(currentDirectory)) {
            INSTANCE.setCurrentDirectory(new File(currentDirectory));
        }
        INSTANCE.setFileFilter(new BaseFileChooserFilter(type));
        INSTANCE.setFileSelectionMode(mode);
        return INSTANCE;
    }

    /**
     * @param type
     * @param mode
     *            {@link JFileChooser#setFileSelectionMode(int)}
     * @return the shared JFileChooser instance.
     */
    public static JFileChooser getInstance(FileFilterType type, int mode) {
        return getInstance(type, mode, "");
    }
}
