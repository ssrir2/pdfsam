/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 03/apr/2012
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
package org.pdfsam.gui.about;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.io.FileUtils;
import org.pdfsam.context.DefaultI18nContext;
import org.springframework.core.env.Environment;
import org.swingplus.JHyperlink;

/**
 * Panel displaying About information
 * 
 * @author Andrea Vacondio
 * 
 */
@Named
class AboutPanel extends JPanel {

    @Inject
    private Environment env;

    @PostConstruct
    void init() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        add(Box.createVerticalGlue());
        JLabel appName = new JLabel(String.format("PDF Split and Merge %s", env.getProperty("pdfsam.package")));
        Font f = appName.getFont();
        appName.setFont(f.deriveFont(f.getStyle() | Font.BOLD));
        add(appName);
        add(Box.createRigidArea(new Dimension(0, 10)));
        add(new JLabel(String.format("ver. %s", env.getProperty("pdfsam.version"))));
        Dimension labelSpace = new Dimension(0, 5);
        add(Box.createRigidArea(labelSpace));
        add(new JLabel("Copyright 2012 by Andrea Vacondio"));
        add(Box.createRigidArea(labelSpace));
        add(new JLabel(System.getProperty("java.runtime.name") + " " + System.getProperty("java.runtime.version")));
        add(Box.createRigidArea(labelSpace));
        add(new JLabel(DefaultI18nContext.getInstance().i18n("Max memory {0}",
                FileUtils.byteCountToDisplaySize(Runtime.getRuntime().maxMemory()))));
        add(Box.createRigidArea(labelSpace));
        add(new JHyperlink("www.pdfsam.org", "http://www.pdfsam.org"));
        add(Box.createRigidArea(labelSpace));
        add(Box.createVerticalGlue());
    }
}
