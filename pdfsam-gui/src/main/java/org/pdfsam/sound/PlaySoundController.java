/* 
 * This file is part of the PDF Split And Merge source code
 * Created on 22/nov/2012
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
package org.pdfsam.sound;

import javax.inject.Named;

import org.bushe.swing.event.annotation.AnnotationProcessor;
import org.bushe.swing.event.annotation.EventSubscriber;
import org.bushe.swing.event.annotation.ReferenceStrength;
import org.pdfsam.context.DefaultUserContext;
import org.sejda.model.notification.event.TaskExecutionCompletedEvent;
import org.sejda.model.notification.event.TaskExecutionFailedEvent;

/**
 * Controller responding to sound related events.
 * 
 * @author Andrea Vacondio
 * 
 */
@Named
public class PlaySoundController {

    private SoundPlayer player = new DefaultSoundPlayer();

    public PlaySoundController() {
        AnnotationProcessor.process(this);
    }

    @EventSubscriber(referenceStrength = ReferenceStrength.STRONG)
    public void playFailed(TaskExecutionFailedEvent event) {
        playSound(Sound.NEGATIVE);
    }

    @EventSubscriber(referenceStrength = ReferenceStrength.STRONG)
    public void playCompleted(TaskExecutionCompletedEvent event) {
        playSound(Sound.POSITIVE);
    }

    private void playSound(Sound sound) {
        if (DefaultUserContext.getInstance().isPlaySounds()) {
            player.play(sound);
        }
    }
}
